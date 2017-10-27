package com.res.db.share.service.business;

import java.util.List;

public class Table {
	private String tableName;
	private String tableType;
	private List<Column> columns;

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<Column> getColumns() {
		return this.columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String getTableType() {
		return this.tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Column c : this.columns) {
			sb.append(c.toString());
		}
		return "Table [tableName=" + this.tableName + ", tableType=" + this.tableType + ", columns=" + this.columns
				+ "]";
	}
}
