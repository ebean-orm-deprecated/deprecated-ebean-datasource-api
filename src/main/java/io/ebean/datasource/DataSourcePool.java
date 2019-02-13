package io.ebean.datasource;

import javax.sql.DataSource;
import java.sql.SQLException;

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
   * Bring the DataSource online ensuring min connections and start heart beat checking.
   */
  void online() throws SQLException;

  /**
   * Take the DataSource offline closing all connections and stopping heart beat checking.
   */
  void offline();

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

  /**
   * Returns false when the dataSource is down.
   */
  boolean isDataSourceUp();

  /**
   * Returns the reason, why the dataSource is down.
   */
  SQLException getDataSourceDownReason();

  /**
   * Set a new maximum size. The pool should respect this new maximum
   * immediately and not require a restart. You may want to increase the
   * maxConnections if the pool gets large and hits the warning level.
   */
  void setMaxSize(int max);

  /**
   * Set a new maximum size. The pool should respect this new maximum immediately
   * and not require a restart. You may want to increase the maxConnections if the
   * pool gets large and hits the warning and or alert levels.
   */
  void setWarningSize(int warningSize);

  /**
   * Return the warning size. When the pool hits this size it can send a
   * notify message to an administrator.
   */
  int getWarningSize();

}
