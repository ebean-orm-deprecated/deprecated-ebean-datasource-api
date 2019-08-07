package io.ebean.datasource;

import org.junit.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class DataSourceConfigTest {

  @Test
  public void parseCustom() {

    DataSourceConfig config = new DataSourceConfig();
    Map<String, String> map = config.parseCustom("a=1;b=2;c=3");

    assertThat(map).hasSize(3);
    assertThat(map.get("a")).isEqualTo("1");
    assertThat(map.get("b")).isEqualTo("2");
    assertThat(map.get("c")).isEqualTo("3");
  }

  @Test
  public void isEmpty() {
    DataSourceConfig config = new DataSourceConfig();
    assertThat(config.isEmpty()).isTrue();

    config.setUrl("foo");
    assertThat(config.isEmpty()).isFalse();

    config = new DataSourceConfig();
    config.setUsername("foo");
    assertThat(config.isEmpty()).isFalse();

    config = new DataSourceConfig();
    config.setPassword("foo");
    assertThat(config.isEmpty()).isFalse();

    config = new DataSourceConfig();
    config.setDriver("foo");
    assertThat(config.isEmpty()).isFalse();
  }

  @Test
  public void copy() {

    DataSourceConfig  source = new DataSourceConfig();
    source.setMinConnections(42);
    source.setMaxConnections(45);
    source.setUsername("un");
    source.setPassword("pw");
    source.setUrl("url");
    source.setSchema("sch");

    Map<String,String> customSource = new LinkedHashMap<>();
    customSource.put("a","a");
    customSource.put("b","b");
    source.setCustomProperties(customSource);


    DataSourceConfig copy = source.copy();
    assertEquals("un", copy.getUsername());
    assertEquals("pw", copy.getPassword());
    assertEquals("url", copy.getUrl());
    assertEquals("sch", copy.getSchema());
    assertEquals(42, copy.getMinConnections());
    assertEquals(45, copy.getMaxConnections());

    customSource.put("a","modifiedA");
    customSource.put("c","newC");

    assertEquals("a", copy.getCustomProperties().get("a"));
    assertEquals("b", copy.getCustomProperties().get("b"));
    assertNull(copy.getCustomProperties().get("c"));
  }

  @Test
  public void defaults() {

    DataSourceConfig config = create();

    DataSourceConfig readOnly = new DataSourceConfig();
    readOnly.setDefaults(config);

    assertThat(readOnly.getDriver()).isEqualTo(config.getDriver());
    assertThat(readOnly.getUrl()).isEqualTo(config.getUrl());
    assertThat(readOnly.getUsername()).isEqualTo(config.getUsername());
    assertThat(readOnly.getPassword()).isEqualTo(config.getPassword());
    assertThat(readOnly.getSchema()).isEqualTo(config.getSchema());

  }

  @Test
  public void defaults_someOverride() {

    DataSourceConfig readOnly = new DataSourceConfig();
    readOnly.setUsername("foo2");
    readOnly.setUrl("jdbc:postgresql://127.0.0.2:5432/unit");

    DataSourceConfig config = create();
    readOnly.setDefaults(config);

    assertThat(readOnly.getPassword()).isEqualTo(config.getPassword());
    assertThat(readOnly.getDriver()).isEqualTo(config.getDriver());
    assertThat(readOnly.getUrl()).isEqualTo("jdbc:postgresql://127.0.0.2:5432/unit");
    assertThat(readOnly.getUsername()).isEqualTo("foo2");

  }

  private DataSourceConfig create() {
    DataSourceConfig config = new DataSourceConfig();
    config.setDriver("org.postgresql.Driver");
    config.setUrl("jdbc:postgresql://127.0.0.1:5432/unit");
    config.setUsername("foo");
    config.setPassword("bar");
    return config;
  }

  @Test
  public void loadSettings() throws IOException {

    DataSourceConfig config = new DataSourceConfig();

    Properties props = new Properties();
    props.load(getClass().getResourceAsStream("/example.properties"));
    config.loadSettings(props, "db");

    assertThat(config.getUsername()).isEqualTo("myusername");
    assertThat(config.getPassword()).isEqualTo("mypassword");
    assertThat(config.getSchema()).isEqualTo("myschema");
  }
}
