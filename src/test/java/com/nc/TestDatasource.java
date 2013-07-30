package com.nc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDatasource /*extends DriverManagerDataSource implements ApplicationListener<ContextRefreshedEvent>*/ {
	private static final Logger log = LoggerFactory.getLogger(TestDatasource.class);

	/**
	 * Initialize database schema.
	 */
	public void init() {
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(this);
//
//		ScriptRunner sr = new ScriptRunner(jdbcTemplate);
//		sr.runScript(new InputStreamReader(getClass().getResourceAsStream("/sql/1.sql")));
	}

	/**
	 * Clear database schema.
	 */
	public void destroy() {
		// nothing nowtest-persistence.xml
	}

	/**
	 * Initialize the initial state of the database. This action is running when test application context is ready
	 * because we need some beans from application context.
	 * 
	 * We also here running some sql scripts.
	 * 
	 * @param event context refreshed event
	 */
	/*@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("database setup finished");
	}*/
}
