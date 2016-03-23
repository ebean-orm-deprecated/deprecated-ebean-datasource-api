package com.avaje.ebean.config;

import java.util.Properties;

class DataSourcePropertiesWrapper {

  private final Properties properties;

  private final String prefix;

  private final String serverName;

  /**
   * Construct with a prefix, serverName and properties.
   */
  DataSourcePropertiesWrapper(String prefix, String serverName, Properties properties) {
    this.serverName = serverName;
    this.prefix = prefix;
    this.properties = properties;
  }

  /**
   * Get a property with no default value.
   */
  private String read(String key) {
    String val = properties.getProperty(key.toLowerCase());
    if (val == null) {
      return properties.getProperty(key);
    }
    return val;
  }

  /**
   * Get a property with a default value.
   * <p>
   * This performs a search using the prefix and server name (if supplied) to search for the property
   * value in order based on:
   * <pre>{@code
   *   prefix.serverName.key
   *   prefix.key
   *   key
   * }</pre>
   * </p>
   */
  String get(String key, String defaultValue) {

    String value = null;
    if (serverName != null && prefix != null) {
      value = read(prefix + "." + serverName + "." + key);
    }
    if (value == null && prefix != null) {
      value = read(prefix + "." + key);
    }
    if (value == null) {
      value = read(key);
    }
    return value == null ? defaultValue : value;
  }

//  /**
//   * Return a double property value.
//   */
//  public double getDouble(String key, double defaultValue) {
//
//    String value = get(key, String.valueOf(defaultValue));
//    return Double.parseDouble(value);
//  }

  /**
   * Return an int property value.
   */
  int getInt(String key, int defaultValue) {

    String value = get(key, String.valueOf(defaultValue));
    return Integer.parseInt(value);
  }

//  /**
//   * Return a long property value.
//   */
//  public long getLong(String key, long defaultValue) {
//
//    String value = get(key, String.valueOf(defaultValue));
//    return Long.parseLong(value);
//  }

  /**
   * Return a boolean property value.
   */
  boolean getBoolean(String key, boolean defaultValue) {

    String value = get(key, String.valueOf(defaultValue));
    return Boolean.parseBoolean(value);
  }

//  /**
//   * Return a Enum property value.
//   */
//  public <T extends Enum<T>> T getEnum(Class<T> enumType, String key, T defaultValue) {
//    String level = get(key, null);
//    return (level == null) ? defaultValue : Enum.valueOf(enumType, level.toUpperCase());
//  }

}
