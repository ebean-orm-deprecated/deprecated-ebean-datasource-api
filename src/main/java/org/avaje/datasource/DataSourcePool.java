package org.avaje.datasource;

import javax.sql.DataSource;

/**
 * DataSource pool API.
 */
public interface DataSourcePool extends DataSource {

  /**
   * Return the dataSource name.
   */
  String getName();

  /**
   * Return true if the pool defaults to using autocommit.
   */
  boolean isAutoCommit();

  /**
   * Shutdown the pool with the option to deregister the driver.
   */
  void shutdown(boolean deregisterDriver);

  /**
   * Return the current status of the connection pool.
   * <p>
   * This is cheaper than getStatistics() in that it just the counts of free, busy,
   * wait etc and does not included times (total connection time etc).
   * </p>
   * <p>
   * If you pass reset = true then the counters are reset.
   * </p>
   */
  PoolStatus getStatus(boolean reset);

  /**
   * Return the aggregated execution statistics collected on all the connections in the pool.
   * <p>
   * If reset is set to true the counters are reset once the statistics have been collected.
   * </p>
   */
  PoolStatistics getStatistics(boolean reset);

}