package com.expect.baemin.api.config;

import com.expect.baemin.infra.db.expect.BaeminExpectDbInfra;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {BaeminExpectDbInfra.class})
public class JpaConfig {

}
