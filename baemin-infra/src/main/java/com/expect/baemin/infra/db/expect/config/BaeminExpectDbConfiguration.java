package com.expect.baemin.infra.db.expect.config;

import com.expect.baemin.infra.db.expect.BaeminExpectDbPropertyBinder;
import com.expect.baemin.infra.db.expect.domain.BaeminExpectDbDomain;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.expect.baemin.infra.db.expect.BaeminExpectDbConstants.*;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = BaeminExpectDbDomain.class,
        entityManagerFactoryRef = ENTITY_MANAGER_FACTORY,
        transactionManagerRef = TRANSACTION_MANAGER
)
@EnableJpaAuditing
public class BaeminExpectDbConfiguration {

    private final BaeminExpectDbPropertyBinder baeminExpectDbPropertyBinder;
    private final Environment env;

    @Primary
    @Bean
    public DataSource baeminExpectDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(baeminExpectDbPropertyBinder.getJdbcUrl());
        hikariConfig.setUsername(baeminExpectDbPropertyBinder.getUsername());
        hikariConfig.setPassword(baeminExpectDbPropertyBinder.getPassword());
        hikariConfig.setDriverClassName(baeminExpectDbPropertyBinder.getDriverClassName());
        hikariConfig.setMaximumPoolSize(baeminExpectDbPropertyBinder.getPoolSize());
        return new HikariDataSource(hikariConfig);
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean baeminExpectEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(baeminExpectDataSource());
        factory.setPackagesToScan(PACKAGES_TO_SCAN);
        factory.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        factory.setJpaVendorAdapter(jpaVendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.show_sql", env.getProperty("spring.jpa.properties.hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
        properties.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.properties.hibernate.ddl-auto"));
        factory.setJpaPropertyMap(properties);

        return factory;
    }

    @Primary
    @Bean
    public PlatformTransactionManager baeminExpectTransactionManager(
            @Qualifier(ENTITY_MANAGER_FACTORY) LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
    ) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
        return transactionManager;
    }
    
}
