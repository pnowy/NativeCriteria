package com.github.pnowy.nc;

import com.github.pnowy.nc.core.jpa.JpaQueryProvider;
import com.github.pnowy.nc.spring.SpringNativeCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@Transactional
public class ApplicationTests {

    @Autowired
    private EntityManager entityManager;

	@Test
	public void contextLoads() {
		System.out.println("test");
        SpringNativeCriteria nc = new SpringNativeCriteria(new JpaQueryProvider(entityManager), "car", "c");
        nc.criteriaResult();
	}

}
