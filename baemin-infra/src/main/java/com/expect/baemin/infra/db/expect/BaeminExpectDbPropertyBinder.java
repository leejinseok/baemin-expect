package com.expect.baemin.infra.db.expect;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
//@Configuration
//@ConfigurationProperties(prefix="spring.datasource.baemin-expect")
public class BaeminExpectDbPropertyBinder {

    private String driverClassName;
    private String username;
    private String jdbcUrl;
    private String type;
    private String password;
    private Integer poolSize;

}
