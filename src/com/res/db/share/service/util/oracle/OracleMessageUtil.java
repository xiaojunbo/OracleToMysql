package com.res.db.share.service.util.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.res.db.share.service.business.Column;
import com.res.db.share.service.business.Table;
import com.res.db.share.service.util.DbMessageUtil;

public class OracleMessageUtil extends DbMessageUtil {

	@Override
	public Table getTable(String tablename, String tableType, Connection conn) throws SQLException {
		String sql = "select Column_name,data_type,Data_length,data_precision,data_scale ,nullable from cols where table_name = ?";
		PreparedStatement p = conn.prepareStatement(sql);
		p.setString(1, tablename.toUpperCase());
		ResultSet rs = p.executeQuery();
		Table table = new Table();
		table.setTableName(tablename);
		table.setTableType(tableType);
		table.setColumns(new ArrayList());

		while (rs.next()) {
			Column c = new Column();
			c.setColumnName(rs.getString(1));
			String ss = rs.getString(2).toUpperCase();
			int[] length = (int[]) null;
			if (ss.indexOf("NUMBER") != -1) {
				length = new int[2];
				length[0] = rs.getInt(4);
				if (length[0] == 0)
					length[0] = rs.getInt(3);
				length[1] = rs.getInt(5);
			} else if (ss.indexOf("VARCHAR") != -1) {
				length = new int[1];
				length[0] = rs.getInt(3);
			}
			c.setIs_null(rs.getString(6));

			c.setColumnType(ss);
			c.setLength(length);

			table.getColumns().add(c);
		}
		p.close();
		rs.close();
		return table;
	}

	@Override
	public List<Table> getTables(Connection conn) throws SQLException {
		//String sql = "select tname from tab";
		String sql = "select tname from tab where tab.tname not like '%BIN%'";
	    List<Table> list = new ArrayList<>();
	    List list1 = new ArrayList();
	    PreparedStatement p = null;
	    ResultSet rs = null;
	    try {
	      p = conn.prepareStatement(sql);
	      rs = p.executeQuery();
	      while (rs.next()) {
	        Table t = new Table();
	        t.setTableName(rs.getString(1));
	        list.add(t);
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      rs.close();
	      p.close();
	    }
	    for (Table t : list) {
	      t = getTable(t.getTableName(), "", conn);
	      list1.add(t);
	    }
	    return list1;
	}

	public List<String> getTableName(Connection conn) throws SQLException {
		String sql = "select tname from tab";
		List list = new ArrayList();
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = conn.prepareStatement(sql);
			rs = p.executeQuery();
			while (rs.next())
				list.add(rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			p.close();
		}
		return list;
	}

}
