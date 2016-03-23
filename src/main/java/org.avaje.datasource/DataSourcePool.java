package org.avaje.datasource;

import javax.sql.DataSource;

/**
 * DataSource pool API.
 */
public interface DataSourcePool extends DataSource {

  /**
   * Return true if the pool defaults to using autocommit.
   */
  boolean isAutoCommit();

  /**
   * Shutdown the pool with the option to deregister the driver.
   */
  void shutdown(boolean deregisterDriver);

  /**
   * Return the Runnable to execute for heartbeat testing.
   */
  Runnable getHeartbeatRunnable();

  /**
   * Return the frequency to run the heart bean test.
   */
  int getHeartbeatFreqSecs();
}