package com.codeforge.demo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
@ConfigurationProperties("myconfig")
public class MyConfiguration {
    private String prop;
    private boolean feature;

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public boolean getFeature() {
        return feature;
    }

    public void setFeature(boolean flag) {
        this.feature = flag;
    }
}
