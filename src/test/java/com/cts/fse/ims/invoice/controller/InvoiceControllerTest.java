package com.cts.fse.ims.invoice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import com.cts.fse.ims.invoice.dto.InvoiceDTO;
import com.cts.fse.ims.invoice.service.InvoiceService;

@SpringBootTest
public class InvoiceControllerTest {
	
	@InjectMocks
	private InvoiceController invoiceController;
	
	@Mock
	private InvoiceService invoiceService;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testGet() throws Exception {
		List<InvoiceDTO> invoices = new ArrayList<InvoiceDTO>();
		InvoiceDTO invoiceDto =  new InvoiceDTO(1L,"Manesh","test", 101.00f, new Date("22/06/2020"), new Date("22/06/2020"));
		invoices.add(invoiceDto);
		when(invoiceService.findAll()).thenReturn(invoices);		
		ResponseEntity<List<InvoiceDTO>> invoiceDtos = invoiceController.get();
		assertThat(invoiceDtos).isNotNull();
		assertEquals(200, invoiceDtos.getStatusCodeValue());
    }
	
	@Test
	public void testGetNew() throws Exception {
		List<InvoiceDTO> invoices = new ArrayList<InvoiceDTO>();
		InvoiceDTO invoiceDto =  new InvoiceDTO(1L,"Manesh","test", 101.00f, new Date("22/06/2020"), new Date("22/06/2020"));
		invoices.add(invoiceDto);
		when(invoiceService.findAllV2()).thenReturn(invoices);		
		ResponseEntity<List<InvoiceDTO>> invoiceDtos = invoiceController.getNew();
		assertThat(invoiceDtos).isNotNull();
		assertEquals(200, invoiceDtos.getStatusCodeValue());
    }
	
	@Test
	public void testGetById() throws Exception{
    	Long invoiceId = 1L;
    	InvoiceDTO invoice = new InvoiceDTO(invoiceId,"Manesh","test", 101.00f, new Date("22/06/2020"), new Date("22/06/2020"));
		when(invoiceService.findOne(invoiceId)).thenReturn(invoice);		
		ResponseEntity<InvoiceDTO> invoiceDto = invoiceController.getById(invoiceId.toString());
		assertThat(invoiceDto).isNotNull();
		assertEquals(200, invoiceDto.getStatusCodeValue());
    }
	
	@Test
	public void testGetByIdNew() throws Exception{
    	Long invoiceId = 1L;
    	InvoiceDTO invoice = new InvoiceDTO(invoiceId,"Manesh","test", 101.00f, new Date("22/06/2020"), new Date("22/06/2020"));
		when(invoiceService.findOneV2(invoiceId)).thenReturn(invoice);		
		ResponseEntity<InvoiceDTO> invoiceDto = invoiceController.getByIdNew(invoiceId.toString());
		assertThat(invoiceDto).isNotNull();
		assertEquals(200, invoiceDto.getStatusCodeValue());
    }
	
	@Test
   	public void testDeleteAll() throws Exception{
       	doNothing().when(invoiceService).deleteAll();
       	ResponseEntity result = invoiceController.deleteAll();
       	assertEquals(204, result.getStatusCodeValue());
    }
	
	@Test
   	public void testDelete() throws Exception{
		Long invoiceId = 1L;
       	doNothing().when(invoiceService).delete(invoiceId);
       	ResponseEntity result = invoiceController.delete(invoiceId.toString());
       	assertEquals(204, result.getStatusCodeValue());
    }
	
	@Test
   	public void testSave() throws Exception{
    	String expectedValue = "Anesh";
       	Long invoiceId =31L;
       	InvoiceDTO invoiceDto = new InvoiceDTO(invoiceId,"Anesh","test", 101.00f, new Date("22/06/2020"), new Date("22/06/2020"));
       	when(invoiceService.save(invoiceDto)).thenReturn(invoiceDto);
       	ResponseEntity<InvoiceDTO> invoice = invoiceController.save(invoiceDto);
       	assertThat(invoice).isNotNull();
       	assertEquals(200, invoice.getStatusCodeValue());
   		assertEquals(invoiceDto.getName(),expectedValue);
    }
	
	
	@Test
   	public void testUpdate() throws Exception{
    	String expectedValue = "Anesh";
       	Long invoiceId =31L;
       	InvoiceDTO invoiceDto = new InvoiceDTO(invoiceId,"Anesh","test", 101.00f, new Date("22/06/2020"), new Date("22/06/2020"));
       	when(invoiceService.update(invoiceId, invoiceDto)).thenReturn(invoiceDto);
       	ResponseEntity<InvoiceDTO> invoice = invoiceController.update(invoiceId.toString(), invoiceDto);
       	assertThat(invoice).isNotNull();
       	assertEquals(200, invoice.getStatusCodeValue());
   		assertEquals(invoiceDto.getName(),expectedValue);
    }
}
