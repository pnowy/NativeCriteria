package com.github.pnowy.nc;

import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.jpa.JpaQueryProvider;
import com.github.pnowy.nc.spring.SpringNativeCriteria;

@DataJpaTest
@Category(DefaultGroup.class)
@RunWith(SpringRunner.class)
@Transactional(readOnly = true)
public abstract class AbstractDbTest {

    static final String PROFILE_POSTGRESQL = "postgresql";
    static final String PROFILE_SQLSERVER = "sqlserver";
    static final String PROFILE_MYSQL = "mysql";

    @Autowired
    protected TestEntityManager entityManager;

    protected NativeCriteria createNativeCriteria(String tableName, String tableAlias) {
        return new NativeCriteria(new JpaQueryProvider(entityManager.getEntityManager()), tableName, tableAlias);
    }

    protected SpringNativeCriteria createSpringNativeCriteria(String tableName, String tableAlias) {
        return new SpringNativeCriteria(new JpaQueryProvider(entityManager.getEntityManager()), tableName, tableAlias);
    }

}
