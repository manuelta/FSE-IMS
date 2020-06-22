package com.cts.fse.ims.invoice.service;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.cts.fse.ims.invoice.DTO.InvoiceDTO;
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
	public void testfindAll() throws Exception{
		List<Invoice> invoices = new ArrayList<Invoice>();
		InvoiceDTO invoiceDto =  new InvoiceDTO(1L,"Manesh","test", 101.00f, new Date("22/06/2020"), new Date("22/06/2020"));
		invoices.add(invoiceDto.toEntity());
		given(invoiceRepository.findAll()).willReturn(invoices);		
		List<InvoiceDTO> invoiceDtos = invoiceService.findAll();
		assertThat(invoiceDtos).isNotNull();
    }
    
    @Test
	public void testFindAllV2() throws Exception{
		List<Invoice> invoices = new ArrayList<Invoice>();
		InvoiceDTO invoiceDto =  new InvoiceDTO(1L,"Manesh","test", 101.00f, new Date("22/06/2020"), new Date("22/06/2020"));
		invoices.add(invoiceDto.toEntity());
		given(invoiceRepository.findAll()).willReturn(invoices);		
		List<InvoiceDTO> invoiceDtos = invoiceService.findAllV2();
		assertThat(invoiceDtos).isNotNull();
    }

}
