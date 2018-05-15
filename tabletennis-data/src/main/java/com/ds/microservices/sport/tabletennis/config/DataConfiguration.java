package com.ds.microservices.sport.tabletennis.config;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@ComponentScan("com.ds.microservices.sport.tabletennis")
@EntityScan("com.ds.microservices.sport.tabletennis.model")
@EnableJpaRepositories(basePackages = {"com.ds.microservices.sport.tabletennis.model", "com.ds.microservices.sport.tabletennis.repository"})
@PropertySource("classpath:db-config.properties")
public class DataConfiguration {

	protected Logger logger = Logger.getLogger(DataConfiguration.class.getName());
	
	@Autowired
	public DataSource dataSource;
	
//	@Bean
////	@ConfigurationProperties(prefix="spring.datasource")
//	public DataSource dataSource() {
//		
//		logger.info("dataSource() invoked");
//
////		DataSource dataSource = (DataSource)(new DataSourceAutoConfiguration());
////				.addScript("classpath:testdb/schema.sql")
////				.addScript("classpath:testdb/data.sql")
////				.build();
//
////		JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
////		DataSource dataSource = jndiDataSourceLookup.getDataSource("java:comp/env/dejan")
//		
//		DataSource dataSource = DataSourceBuilder.create().build();
//		logger.info("dataSource = " + dataSource);
//
//		// Sanity check
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		List<Map<String, Object>> accounts = jdbcTemplate.queryForList("SELECT number FROM T_ACCOUNT");
//		logger.info("System has " + accounts.size() + " accounts");
//		
//		// Populate with random balances
//		Random random = new Random();
//		
//		for (Map<String, Object> item : accounts) {
//			String number = (String)item.get("number");
//			BigDecimal balance = new BigDecimal(random.nextInt(10000000) / 100.0).setScale(2, BigDecimal.ROUND_HALF_UP);
//			jdbcTemplate.update("UPDATE T_ACCOUNT SET balance = ? WHERE number = ?", balance, number);
//			
//		}
//
//		
//		logger.info("dataSource = " + dataSource);
//
//		return dataSource;
//	}

}
