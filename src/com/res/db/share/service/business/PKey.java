package com.res.db.share.service.business;

public class PKey {
	private String tableName;
	private String columnName;

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String toString() {
		return "PKey [tableName=" + this.tableName + ", columnName=" + this.columnName + "]";
	}
}
