package org.avaje.datasource;

/**
 * Factory that creates DataSourcePool's.
 */
public interface DataSourceFactory {

  /**
   * Create the DataSourcePool with the given configuration.
   *
   * @param name     The name of the pool.
   * @param config   The configuration options.
   * @param alert    An alert notification handler (can be null).
   * @param listener A pool listener (can be null).
   * @return The created DataSourcePool
   */
  DataSourcePool createPool(String name, DataSourceConfig config, DataSourceAlert alert, DataSourcePoolListener listener);
}
