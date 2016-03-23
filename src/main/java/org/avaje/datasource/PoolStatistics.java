package org.avaje.datasource;

/**
 * Execution statistics from the DataSourcePool.
 */
public interface PoolStatistics {

  /**
   * Return the epoch millis when these statistics start from.
   */
  long getCollectionStart();

  /**
   * Return the hit count against the pool.
   */
  long getCount();

  /**
   * Return the error count against the pool.
   */
  long getErrorCount();

  /**
   * Return the high water mark for connection use in micros.
   */
  long getHwmMicros();

  /**
   * Return the total connection use in micros.
   */
  long getTotalMicros();

  /**
   * Return the average connection use in micros.
   */
  long getAvgMicros();
}
