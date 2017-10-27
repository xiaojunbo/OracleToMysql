package com.res.db.share.service.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.res.db.share.service.business.Table;

public abstract class DbMessageUtil {
	public abstract Table getTable(String paramString1, String paramString2, Connection paramConnection)
			throws SQLException;

	public abstract List<Table> getTables(Connection paramConnection) throws SQLException;
}
