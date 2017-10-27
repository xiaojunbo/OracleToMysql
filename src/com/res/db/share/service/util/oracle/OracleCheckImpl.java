package com.res.db.share.service.util.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.res.db.share.service.business.PKey;
import com.res.db.share.service.business.RKey;
import com.res.db.share.service.util.Checkutil;

public class OracleCheckImpl extends Checkutil {

	@Override
	public List<PKey> getPKey(String tableNmae, Connection conn) throws SQLException {
		String sql = "select a.table_name,  a.column_name  from user_cons_columns a, user_constraints b  where a.constraint_name = b.constraint_name and b.constraint_type = 'P' and a.table_name=?";

		PreparedStatement p = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			p = conn.prepareStatement(sql);
			p.setString(1, tableNmae);
			rs = p.executeQuery();

			while (rs.next()) {
				PKey key = new PKey();
				key.setTableName(tableNmae);
				key.setColumnName(rs.getString("column_name"));
				list.add(key);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			p.close();
			rs.close();
		}
		return list;
	}

	@Override
	public List<RKey> getRKey(String tableNmae, Connection conn) throws SQLException {
		String sql = "select b.table_name,d.column_name,a.table_name,c.column_name  from  user_constraints   a,  user_constraints   b,  user_cons_columns   c,  user_cons_columns   d  where a.r_constraint_name=b.constraint_name    and   a.constraint_type= 'R'  and   b.constraint_type= 'P'  and   a.r_owner=b.owner     and   a.constraint_name=c.constraint_name  and   b.constraint_name=d.constraint_name    and   a.owner=c.owner  and   a.table_name=c.table_name  and   b.owner=d.owner  and   b.table_name=d.table_name  and   b.table_name=?";

		PreparedStatement p = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			p = conn.prepareStatement(sql);
			p.setString(1, tableNmae);
			rs = p.executeQuery();

			while (rs.next()) {
				RKey key = new RKey();
				key.setP_tablename(rs.getString(1));
				key.setP_column(rs.getString(2));
				key.setR_tablename(rs.getString(3));
				key.setR_column(rs.getString(4));
				list.add(key);
			}
		} finally {
			p.close();
			rs.close();
		}

		return list;
	}

}
