package com.cts.fse.ims;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.cts.fse.ims.invoice.repository"})
@EnableSwagger2
public class InventoryManagementSystemApplication {
	private static final Logger LOGGER=LoggerFactory.getLogger(InventoryManagementSystemApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementSystemApplication.class, args);
		LOGGER.info("Inventory application Started");
	}
	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.cts.fse.ims.invoice.controller"))
                .paths(PathSelectors.ant("/invoice"))
                .build();		
	}
}
