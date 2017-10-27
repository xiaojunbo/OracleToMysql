package com.res.db.share.service.business;

public class RKey {
	private String P_tablename;
	private String p_column;
	private String r_tablename;
	private String r_column;

	public String getP_tablename() {
		return this.P_tablename;
	}

	public void setP_tablename(String p_tablename) {
		this.P_tablename = p_tablename;
	}

	public String getP_column() {
		return this.p_column;
	}

	public void setP_column(String p_column) {
		this.p_column = p_column;
	}

	public String getR_tablename() {
		return this.r_tablename;
	}

	public void setR_tablename(String r_tablename) {
		this.r_tablename = r_tablename;
	}

	public String getR_column() {
		return this.r_column;
	}

	public void setR_column(String r_column) {
		this.r_column = r_column;
	}

	public String toString() {
		return "RKey [P_tablename=" + this.P_tablename + ", p_column=" + this.p_column + ", r_tablename="
				+ this.r_tablename + ", r_column=" + this.r_column + "]";
	}
}
