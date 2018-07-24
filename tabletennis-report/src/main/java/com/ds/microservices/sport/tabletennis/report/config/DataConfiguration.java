package com.ds.microservices.sport.tabletennis.report.config;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@ComponentScan("com.ds.microservices.sport.tabletennis.report")
@EntityScan("com.ds.microservices.sport.tabletennis.report.entity")
@EnableJpaRepositories(basePackages = {"com.ds.microservices.sport.tabletennis.report.entity", "com.ds.microservices.sport.tabletennis.report.repository"})
@PropertySource("classpath:db-config.properties")
public class DataConfiguration {

	protected Logger logger = Logger.getLogger(DataConfiguration.class.getName());
	
	@Autowired
	public DataSource dataSource;
	
	
	
}
