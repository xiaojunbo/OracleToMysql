package com.res.db.share.service.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.res.db.share.service.business.PKey;
import com.res.db.share.service.business.RKey;

public abstract class Checkutil {
	public abstract List<PKey> getPKey(String paramString, Connection paramConnection) throws SQLException;

	public abstract List<RKey> getRKey(String paramString, Connection paramConnection) throws SQLException;
}
