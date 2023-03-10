package com.expect.baemin.infra.db.expect.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.expect.baemin.infra.db.expect.BaeminExpectDbConstants.ENTITY_MANAGER;
import static com.expect.baemin.infra.db.expect.BaeminExpectDbConstants.JPA_QUERY_FACTORY;


@Configuration
@RequiredArgsConstructor
public class BaeminExpectDbQueryDslConfiguration {

    @PersistenceContext(name = ENTITY_MANAGER)
    private final EntityManager entityManager;

    @Bean(name = JPA_QUERY_FACTORY)
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }


}
