package com.res.db.share.service.business;

import java.util.Arrays;

public class Column {
	private String ColumnName;
	private String ColumnType;
	private int[] length;
	private boolean is_null;

	public String getColumnName() {
		return this.ColumnName;
	}

	public void setColumnName(String columnName) {
		this.ColumnName = columnName;
	}

	public String getColumnType() {
		return this.ColumnType;
	}

	public void setColumnType(String columnType) {
		this.ColumnType = columnType;
	}

	public int[] getLength() {
		return this.length;
	}

	public void setLength(int[] length) {
		this.length = length;
	}

	public boolean isIs_null() {
		return this.is_null;
	}

	public void setIs_null(String s) {
		boolean flog = false;
		if (s.equalsIgnoreCase("n")) {
			flog = true;
		}
		this.is_null = flog;
	}

	public String toString() {
		return "Column [ColumnName=" + this.ColumnName + ", ColumnType=" + this.ColumnType + ", length="
				+ Arrays.toString(this.length) + ", is_null=" + this.is_null + "]";
	}
}
