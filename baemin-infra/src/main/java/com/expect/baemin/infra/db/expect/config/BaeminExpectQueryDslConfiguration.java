package com.expect.baemin.infra.db.expect.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class BaeminExpectQueryDslConfiguration {

    private final EntityManager baeminExpectEntityManager;

    @Bean
    public JPAQueryFactory kpopJpaQueryFactory() {
        return new JPAQueryFactory(baeminExpectEntityManager);
    }



}
