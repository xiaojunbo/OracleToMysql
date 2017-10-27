package com.res.db.share.service.util;

import com.res.db.share.service.business.Table;

public abstract interface SqlUtil {
	public abstract String getCreateSql(Table paramTable);
}
