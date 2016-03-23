package org.avaje.datasource;

/**
 * Factory that creates DataSourcePool's.
 */
public interface DataSourceFactory {

  DataSourcePool createPool(String name, DataSourceConfig config, DataSourceAlert alert, DataSourcePoolListener listener);
}
