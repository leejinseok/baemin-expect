package com.expect.baemin.infra.db.expect.config;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class BaeminExpectQueryDslConfiguration {

    private final EntityManager baeminExpectEntityManager;

// /*   @Bean
//    public JPAQueryFactory baeminExpectJpaQueryFactory() {
//        return new JPAQueryFactory(baeminExpectEntityManager);
//    }*/


}
