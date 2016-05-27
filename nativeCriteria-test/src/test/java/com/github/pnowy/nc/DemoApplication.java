package com.github.pnowy.nc;

import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@SpringBootApplication
@Configuration
public class DemoApplication implements EnvironmentAware {
    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    static final String PROFILE_POSTGRESQL = "postgresql";
    static final String PROFILE_SQLSERVER = "sqlserver";

    public static void main(String[] args) {
        new SpringApplicationBuilder().run(args);
    }

    private RelaxedPropertyResolver liquiBasePropertyResolver;

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        this.liquiBasePropertyResolver = new RelaxedPropertyResolver(environment, "liquiBase.");
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:liquibase/master.xml");
        liquibase.setContexts(liquiBasePropertyResolver.getProperty("context"));
        log.debug("Configuring Liquibase");
        return liquibase;
    }

}
