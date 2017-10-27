package com.res.db.share.service.util.mysql;

import java.util.List;

import com.res.db.share.service.business.Column;
import com.res.db.share.service.business.Table;
import com.res.db.share.service.util.SqlUtil;

public class MysqlSqlUtil implements SqlUtil {

	@Override
	public String getCreateSql(Table table) {
		StringBuffer sb = new StringBuffer();
		sb.append("create table ");
		sb.append(table.getTableName());
		sb.append(" (");
		List<Column> list = table.getColumns();
		for (Column c : list) {
			sb.append("`" + c.getColumnName()).append("` ");
			sb.append(c.getColumnType()).append(" ");
			if (c.getLength() != null) {
				if (c.getColumnType().equalsIgnoreCase("NUMERIC")) {
					sb.append("( ");
					sb.append(c.getLength()[0] + "," + c.getLength()[1]);
					sb.append(" ) ");
				} else if (c.getColumnType().equalsIgnoreCase("varchar")) {
					sb.append("( ");
					sb.append(c.getLength()[0]);
					sb.append(" ) ");
				}
			}

			if (c.isIs_null()) {
				sb.append("  NOT NULL ");
			}
			if (c != list.get(list.size() - 1)) {
				sb.append(" , ");
			}
		}
		sb.append(" )");

		return sb.toString();
	}

}
