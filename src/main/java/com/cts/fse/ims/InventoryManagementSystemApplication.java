package com.cts.fse.ims;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import com.cts.fse.ims.invoice.DTO.InvoiceDTO;
import com.cts.fse.ims.invoice.service.InvoiceService;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.cts.fse.ims.invoice.repository"})
@EnableSwagger2
public class InventoryManagementSystemApplication implements CommandLineRunner {
	@Autowired
	private InvoiceService invoiceService;
	
	//@Autowired
	//private BillService billService;
	
	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementSystemApplication.class, args);
		
	}
	@Override
    public void run(String... args) throws Exception {
		
		List<InvoiceDTO> in = invoiceService.findAll();
		System.out.println("res :"+in.size());
		
		/*InvoiceDTO res = invoiceService.findOne(8L);
		System.out.println("aa:"+res.getName());*/
//		
//		InvoiceDTO inv = new InvoiceDTO();
//		inv.setName("Manesh");
//		inv.setAmount(100);
//		inv.setDescription("Testing");
//		inv.setCreated(new Date());
//		inv.setUpdated(new Date());
//		InvoiceDTO invs = invoiceService.save(inv);
//		System.out.println("updated.."+invs.getName());
		
//		Set<ItemDTO> itemDtos = new HashSet<>();
//		ItemDTO itemDto = new ItemDTO();
//		itemDto.setItemName("Chair");
//		itemDto.setItemDescription("Wheel Chair");
//		itemDto.setUnitPrice(1000);
//		itemDto.setCount(5);
//		itemDto.setTaxAmount(100);
//		itemDto.setGrossAmount(5100);
//		itemDto.setCreated(new Date());
//		itemDto.setUpdated(new Date());
//		itemDtos.add(itemDto);
//		
//		BillDTO billDto = new BillDTO();
//		billDto.setBillName("Manesh");
//		billDto.setBillDescription("Chair Purchase");
//		billDto.setItems(itemDtos);
//		billDto.setTotalAmount(5100);
//		billDto.setCreated(new Date());
//		billDto.setUpdated(new Date());
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
