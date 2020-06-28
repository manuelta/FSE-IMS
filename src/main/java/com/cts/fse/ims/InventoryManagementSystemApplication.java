package com.cts.fse.ims;
import com.cts.fse.ims.invoice.helper.InvoiceConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.cts.fse.ims.invoice.repository"})
@EnableSwagger2
@EnableCaching
//@EnableEurekaClient
//@EnableFeignClients
public class InventoryManagementSystemApplication extends SpringBootServletInitializer {
	private static final Logger LOGGER=LoggerFactory.getLogger(InventoryManagementSystemApplication.class);
	@Bean
    public HandlerMapping handlerMapping() {
        return new RequestMappingHandlerMapping();
    }
    @Bean
    public HandlerAdapter handlerAdapter() {
        return new RequestMappingHandlerAdapter();
    }
    
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
	
	@Bean
    public HandlerExceptionResolver handlerExceptionResolver1() {
        return new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
                return null;
            }
        };
    }
}
