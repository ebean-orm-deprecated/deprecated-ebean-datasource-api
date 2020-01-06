package io.ebean.datasource;

/**
 * Factory that creates DataSourcePool's.
 *
 * <pre>{@code
 *
 *     DataSourceConfig config = new DataSourceConfig();
 *     config.setDriver("org.h2.Driver");
 *     config.setUrl("jdbc:h2:mem:tests2");
 *     config.setUsername("sa");
 *     config.setPassword("");
 *
 *     DataSourcePool pool = DataSourceFactory.create("test", config);
 *
 *     Connection connection = pool.getConnection();
 *
 * }</pre>
 */
public interface DataSourceFactory {

  /**
   * Create the DataSourcePool given the name and configuration.
   */
  static DataSourcePool create(String name, DataSourceConfig config) {
    return get().createPool(name, config);
  }

  /**
   * Return the DataSourceFactory.
   * <p>
   * The implementation is obtained via standard service loader mechanism requiring an
   * implementation to be in the classpath.
   * </p>
   */
  static DataSourceFactory get() {
    return DSManager.get();
  }

  /**
   * Create the DataSourcePool with the given configuration.
   *
   * @param name   The name of the pool.
   * @param config The configuration options.
   * @return The created DataSourcePool
   */
  DataSourcePool createPool(String name, DataSourceConfig config);
}
