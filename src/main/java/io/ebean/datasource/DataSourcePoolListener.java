package io.ebean.datasource;

import java.sql.Connection;


/**
 * A {@link DataSourcePool} listener which allows you to hook on the
 * borrow/return process of getting or returning connections from the pool.
 * <p>
 * In the configuration use the poolListener key to configure which listener to
 * use.
 * </p>
 * <p>
 * Example: datasource.ora10.poolListener=my.very.fancy.PoolListener
 * </p>
 * <p>
 * <p>
 * Notice: This listener only works if you are using the default Avaje
 * {@link DataSourcePool}.
 * </p>
 */
public interface DataSourcePoolListener {

  /**
   * Called after a connection has been retrieved from the connection pool
   */
  void onAfterBorrowConnection(Connection connection);

  /**
   * Called before a connection will be put back to the connection pool
   */
  void onBeforeReturnConnection(Connection connection);

}
