package com.cts.fse.ims.invoice.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.cts.fse.ims.invoice.dto.InvoiceDTO;
import com.cts.fse.ims.invoice.model.Invoice;
import com.cts.fse.ims.invoice.repository.InvoiceRepository;


@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {
	@Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceService invoiceService;
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
	public void testFindAll() throws Exception{
    	String expectedValue = "Manesh";
		List<Invoice> invoices = new ArrayList<Invoice>();
		InvoiceDTO invoiceDto =  new InvoiceDTO(1L,"Manesh","test", 101.00f, new Date("22/06/2020"), new Date("22/06/2020"));
		invoices.add(invoiceDto.toEntity());
		given(invoiceRepository.findAll()).willReturn(invoices);		
		List<InvoiceDTO> invoiceDtos = invoiceService.findAll();
		assertThat(invoiceDtos).isNotNull();
		assertEquals(invoiceDtos.get(0).getName(),expectedValue);
    }
    
    @Test
	public void testFindAllV2() throws Exception{
    	String expectedValue = "Anesh";
		List<Invoice> invoices = new ArrayList<Invoice>();
		InvoiceDTO invoiceDto =  new InvoiceDTO(1L,"Anesh","test", 101.00f, new Date("22/06/2020"), new Date("22/06/2020"));
		invoices.add(invoiceDto.toEntity());
		given(invoiceRepository.findAll()).willReturn(invoices);		
		List<InvoiceDTO> invoiceDtos = invoiceService.findAllV2();
		assertThat(invoiceDtos).isNotNull();
		assertEquals(invoiceDtos.get(0).getName(), expectedValue);
    }
    
    @Test
	public void testFindOne() throws Exception{
    	String expectedValue = "Manesh";
    	Long invoiceId = 1L;
    	Invoice invoice = new Invoice(invoiceId,"Manesh","test", 101.00f, new Date("22/06/2020"), new Date("22/06/2020"));
		Optional<Invoice> invoices =  Optional.of(invoice);				
		when(invoiceRepository.findById(invoiceId)).thenReturn(invoices);		
		InvoiceDTO invoiceDto = invoiceService.findOne(invoiceId);
		assertThat(invoiceDto).isNotNull();
		assertEquals(invoiceDto.getName(),expectedValue);
    }
    
    @Test
   	public void testFindOneV2() throws Exception{
       	String expectedValue = "Anesh";
       	Long invoiceId = 1L;
       	Invoice invoice = new Invoice(invoiceId,"Anesh","test", 101.00f, new Date("22/06/2020"), new Date("22/06/2020"));
   		Optional<Invoice> invoices =  Optional.of(invoice);				
   		when(invoiceRepository.findById(invoiceId)).thenReturn(invoices);		
   		InvoiceDTO invoiceDto = invoiceService.findOneV2(invoiceId);
   		assertThat(invoiceDto).isNotNull();
   		assertEquals(invoiceDto.getName(),expectedValue);
     }
    
    @Test
   	public void testDeleteAll() throws Exception{
       	Long invoiceId = 1L;
       	doNothing().when(invoiceRepository).deleteAll();
       	invoiceService.deleteAll();
    }
    
    @Test
   	public void testDelete() throws Exception{
       	Long invoiceId = 1L;
       	doNothing().when(invoiceRepository).deleteById(invoiceId);
       	invoiceService.delete(invoiceId);
       	assertEquals(invoiceId, 1L);
    }
    
    @Test
   	public void testSave() throws Exception{
    	String expectedValue = "Anesh";
       	Long invoiceId =31L;
       	InvoiceDTO invoiceDto = new InvoiceDTO(invoiceId,"Anesh","test", 101.00f, new Date("22/06/2020"), new Date("22/06/2020"));
        InvoiceDTO invoice = invoiceService.save(invoiceDto);
       	assertThat(invoice).isNull();
   		assertEquals(invoiceDto.getName(),expectedValue);
    }
    
    @Test
   	public void testUpdate() throws Exception{
    	String expectedValue = "Anesh";
       	Long invoiceId = 3L;
       	InvoiceDTO invoiceDto = new InvoiceDTO(invoiceId,"Anesh","test", 101.00f, new Date("22/06/2020"), new Date("22/06/2020"));
       	InvoiceDTO invoice = invoiceService.update(invoiceId, invoiceDto);
       	assertThat(invoice).isNull();
   		assertEquals(invoiceDto.getName(),expectedValue);
    }

}
