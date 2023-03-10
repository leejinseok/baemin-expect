package com.expect.baemin.infra.db.expect.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.expect.baemin.infra.db.expect.BaeminExpectDbConstants.PERSISTENCE_UNIT_NAME;


@Configuration
@RequiredArgsConstructor
public class BaeminExpectDbQueryDslConfiguration {

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    private final EntityManager entityManager;

    @Bean
    public JPAQueryFactory baeminExpectJpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }


}
