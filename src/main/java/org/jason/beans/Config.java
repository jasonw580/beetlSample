package org.jason.beans;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix="database")
public class Config {
    private String driver;
    private String url;
    private String name;
    private String passwd;
}
