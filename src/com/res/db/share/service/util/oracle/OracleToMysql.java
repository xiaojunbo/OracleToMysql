package com.res.db.share.service.util.oracle;

import java.util.List;

import com.res.db.share.service.business.Column;
import com.res.db.share.service.business.Table;

public class OracleToMysql {

	public Table oracleAdapter(String oldDb, String newDb, Table table) {
		List<Column> list = table.getColumns();
		for (Column c : list) {
			String type = c.getColumnType();
			System.out.print(type);
			if ("VARCHAR2".equalsIgnoreCase(type))
				c.setColumnType("VARCHAR");
			else if ("Number".equalsIgnoreCase(type))
				c.setColumnType("NUMERIC");
			else if ("clob".equalsIgnoreCase(type)) {
				c.setColumnType("LONGTEXT");
			}
		}
		return table;
	}
}
