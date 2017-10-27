package com.res.db.share.service.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.res.db.share.service.business.PKey;
import com.res.db.share.service.business.RKey;

public class AddKey {
	public void addPKey(List<PKey> list, Connection conn) throws SQLException {
		if (list.size() == 0)
			return;
		StringBuffer sb = new StringBuffer();
		sb.append("ALTER TABLE " + ((PKey) list.get(0)).getTableName() + " ADD PRIMARY KEY (");
		for (PKey p : list) {
			sb.append(p.getColumnName());
			if (p != list.get(list.size() - 1)) {
				sb.append(",");
			}
		}
		sb.append(") ");
		System.out.println(sb);
		conn.prepareStatement(sb.toString()).execute();
	}

	public void addRKey(RKey r, Connection conn) throws SQLException {
		if (r == null)
			return;
		String sql = "ALTER TABLE " + r.getR_tablename() + " ADD FOREIGN KEY (" + r.getR_column() + ") REFERENCES "
				+ r.getP_tablename() + "(" + r.getP_column() + ")";
		System.out.println(sql);
		conn.prepareStatement(sql).execute();
	}
}
