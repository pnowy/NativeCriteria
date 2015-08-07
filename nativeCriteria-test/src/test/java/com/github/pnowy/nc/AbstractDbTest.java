package com.github.pnowy.nc;

import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.jpa.JpaQueryProvider;
import com.github.pnowy.nc.spring.SpringNativeCriteria;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@Transactional(readOnly = true)
@Category(DefaultGroup.class)
public abstract class AbstractDbTest {

    @Autowired
    protected EntityManager entityManager;

    protected NativeCriteria createNativeCriteria(String tableName, String tableAlias) {
        return new NativeCriteria(new JpaQueryProvider(entityManager), tableName, tableAlias);
    }

    protected SpringNativeCriteria createSpringNativeCriteria(String tableName, String tableAlias) {
        return new SpringNativeCriteria(new JpaQueryProvider(entityManager), tableName, tableAlias);
    }

}
