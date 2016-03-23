package com.avaje.ebean.config;

import org.testng.annotations.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class DataSourceConfigTest {

  DataSourceConfig config = new DataSourceConfig();

  @Test
  public void parseCustom() {

    Map<String, String> map = config.parseCustom("a=1;b=2;c=3");

    assertThat(map).hasSize(3);
    assertThat(map.get("a")).isEqualTo("1");
    assertThat(map.get("b")).isEqualTo("2");
    assertThat(map.get("c")).isEqualTo("3");
  }
}