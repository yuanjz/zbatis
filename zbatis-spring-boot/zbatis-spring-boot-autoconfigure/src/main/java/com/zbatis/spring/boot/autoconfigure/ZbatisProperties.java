package com.zbatis.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: yuanjz
 * @Date: 2022-08-11 16:06
 * @Description:
 */
@ConfigurationProperties(prefix = "zbatis")
public class ZbatisProperties {

    private String configLocation;

    public String getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }
}
