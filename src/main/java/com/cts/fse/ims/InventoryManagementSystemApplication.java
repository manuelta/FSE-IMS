package com.cts.fse.ims;
import com.cts.fse.ims.invoice.helper.InvoiceConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
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
@EnableCaching
public class InventoryManagementSystemApplication {
	private static final Logger LOGGER=LoggerFactory.getLogger(InventoryManagementSystemApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementSystemApplication.class, args);
		LOGGER.info(InvoiceConstants.MESSAGE_IMS_STARTED);
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
