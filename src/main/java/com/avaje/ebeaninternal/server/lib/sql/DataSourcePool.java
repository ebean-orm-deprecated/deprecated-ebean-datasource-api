package com.avaje.ebeaninternal.server.lib.sql;

import javax.sql.DataSource;

/**
 * Created by rob on 23/03/16.
 */
public interface DataSourcePool extends DataSource {

  boolean getAutoCommit();

  void shutdown(boolean deregisterDriver);

  Runnable getHeartbeatRunnable();

  int getHeartbeatFreqSecs();
}