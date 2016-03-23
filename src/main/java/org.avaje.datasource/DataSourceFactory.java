package org.avaje.datasource;

import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebeaninternal.server.lib.sql.DataSourceAlert;
import com.avaje.ebeaninternal.server.lib.sql.DataSourcePool;
import com.avaje.ebeaninternal.server.lib.sql.DataSourcePoolListener;

/**
 * Factory that creates DataSourcePool's.
 */
public interface DataSourceFactory {

  DataSourcePool createPool(String name, DataSourceConfig config, DataSourceAlert alert, DataSourcePoolListener listener);
}
