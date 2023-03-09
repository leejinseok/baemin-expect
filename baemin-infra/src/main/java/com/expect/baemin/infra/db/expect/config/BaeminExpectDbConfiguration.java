package com.expect.baemin.infra.db.expect.config;

import com.expect.baemin.infra.db.expect.BaeminExpectDbPropertyBinder;
import com.expect.baemin.infra.db.expect.domain.BaeminExpectDomain;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
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
        basePackageClasses = BaeminExpectDomain.class,
        entityManagerFactoryRef = ENTITY_MANAGER,
        transactionManagerRef = TRANSACTION_MANAGER
)
public class BaeminExpectDbConfiguration {

//    private final BaeminExpectDbPropertyBinder baeminExpectDbPropertyBinder;
    private final Environment env;

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.baemin-expect")
    public DataSource baeminExpectDataSource() {
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setUsername(baeminExpectDbPropertyBinder.getUsername());
//        hikariConfig.setPassword(baeminExpectDbPropertyBinder.getPassword());
//        hikariConfig.setJdbcUrl(baeminExpectDbPropertyBinder.getJdbcUrl());
//        hikariConfig.setMaximumPoolSize(baeminExpectDbPropertyBinder.getPoolSize());
////        hikariConfig.setDriverClassName(baeminExpectDbPropertyBinder.getDriverClassName());
//        return new HikariDataSource(hikariConfig);

        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean baeminExpectEntityManager(
            @Qualifier(DATA_SOURCE) DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(PACKAGES_TO_SCAN);
        em.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.show_sql", env.getProperty("spring.jpa.properties.hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
        properties.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.properties.hibernate.ddl-auto"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean
    public PlatformTransactionManager baeminExpectTransactionManager(
            @Qualifier(ENTITY_MANAGER) LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
    ) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
        return transactionManager;
    }

}
