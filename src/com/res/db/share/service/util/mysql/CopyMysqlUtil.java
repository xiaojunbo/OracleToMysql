package com.res.db.share.service.util.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class CopyMysqlUtil {
	private List<LinkedHashMap<String, Object>> tool(ResultSet rs, List<LinkedHashMap<String, Object>> list)
			throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		List<String> names = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			names.add(rsmd.getColumnName(i + 1));
		}
		List values = new ArrayList();

		while (rs.next()) {
			ResultSetMetaData metaData = rs.getMetaData();
			int i = 1;
			LinkedHashMap one = new LinkedHashMap();
			for (String name : names) {
				int type = metaData.getColumnType(i);
				Object value = null;

				value = rs.getObject(name);

				i++;

				one.put(name, value);
			}
			values.add(one);
		}

		return values;
	}

	public List<LinkedHashMap<String, Object>> select(Connection conn, String tablename) throws SQLException {
		String sql = "select * from " + tablename + " where rownum< 100000";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = null;
		try {
			pstmt = conn.prepareStatement(sql, 1004, 1007);
			rs = pstmt.executeQuery();
			list = new ArrayList();
			list = tool(rs, list);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
		}

		return list;
	}

	public void insert(List<LinkedHashMap<String, Object>> list, String tablename, Connection conn)
			throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into ");
		sql.append(tablename);
		sql.append(" (");
		Iterator localIterator1 = list.iterator();
		Set<String> set;
		Object ss;
		if (localIterator1.hasNext()) {
			LinkedHashMap obj = (LinkedHashMap) localIterator1.next();
			set = obj.keySet();
			int i = set.size();
			for (String ssa : set) {
				sql.append("`" + ssa + "`");
				i--;
				if (i != 0)
					sql.append(",");
			}
			sql.append(" ) VALUES (");
			int j = set.size();
			for (Iterator localIterator3 = set.iterator(); localIterator3.hasNext();) {
				ss = (String) localIterator3.next();
				sql.append("?");
				j--;
				if (j != 0)
					sql.append(",");
			}
			sql.append(" )");
		}

		System.out.println(sql);
		PreparedStatement stmt = conn.prepareStatement(sql.toString());
		for (LinkedHashMap obj : list) {
			int index = 1;
			for (ss = obj.values().iterator(); ((Iterator) ss).hasNext();) {
				Object o = ((Iterator) ss).next();

				stmt.setObject(index++, o);
			}

			stmt.execute();
		}
		stmt.close();
	}
}
