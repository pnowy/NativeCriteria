package com.github.pnowy.nc;

import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.jpa.JpaQueryProvider;
import com.github.pnowy.nc.spring.SpringNativeCriteria;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Category(DefaultGroup.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DataJpaTest
@Transactional(readOnly = true)
public abstract class AbstractDbTest {

    @Autowired
    protected TestEntityManager entityManager;

    protected NativeCriteria createNativeCriteria(String tableName, String tableAlias) {
        return new NativeCriteria(new JpaQueryProvider(entityManager.getEntityManager()), tableName, tableAlias);
    }

    protected SpringNativeCriteria createSpringNativeCriteria(String tableName, String tableAlias) {
        return new SpringNativeCriteria(new JpaQueryProvider(entityManager.getEntityManager()), tableName, tableAlias);
    }

}
