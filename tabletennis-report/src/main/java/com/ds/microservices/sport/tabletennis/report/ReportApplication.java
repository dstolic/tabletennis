package com.ds.microservices.sport.tabletennis.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.ds.microservices.sport.tabletennis.report.config.DataConfiguration;
import com.ds.microservices.sport.tabletennis.report.repository.CompetitionRepository;
import com.ds.microservices.sport.tabletennis.report.service.impl.CompetitionService;

@SpringBootApplication
@Import(DataConfiguration.class)
public class ReportApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name" , "report-server");
		SpringApplication.run(ReportApplication.class, args);
	}
	
}
