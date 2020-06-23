package com.cts.fse.ims.invoice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.ims.invoice.dto.InvoiceDTO;
import com.cts.fse.ims.invoice.model.Invoice;
import com.cts.fse.ims.invoice.repository.InvoiceRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@Service
public class InvoiceService {
	@Autowired
	private InvoiceRepository invoiceRepository;
	public InvoiceDTO save(InvoiceDTO invoiceDTO) {
		Invoice invoice = invoiceRepository.save( invoiceDTO.toEntity() );
		return InvoiceDTO.of(invoice);
	}
	
	@CachePut(value = "invoices", key = "#invoiceId")
	public InvoiceDTO update(Long invoiceId, InvoiceDTO invoiceDTO) {
		Invoice invoice = invoiceDTO.toEntity();
		invoice.setId( invoiceId );
		Invoice updatedInvoice = invoiceRepository.save( invoice );
		return InvoiceDTO.of( updatedInvoice );
	}
	
	@CacheEvict(value = "invoices", key = "#invoiceId")
	public void delete(Long invoiceId) {
		invoiceRepository.deleteById( invoiceId );
	}
	
	@CacheEvict(value = "invoices", allEntries=true)
	public void deleteAll() {
		invoiceRepository.deleteAll();
	}

	public InvoiceDTO findOne(Long invoiceId) {
		return invoiceRepository.findById( invoiceId )
				.map( InvoiceDTO::of )
				.orElse( null );
	}
	@Cacheable(value = "invoices", key = "#invoiceId")
	public InvoiceDTO findOneV2(Long invoiceId) {
		return invoiceRepository.findById( invoiceId )
				.map( InvoiceDTO::of )
				.orElse( null );
	}
	@Cacheable(value = "invoices")
	public List<InvoiceDTO> findAll() {
		List<Invoice> invoices = invoiceRepository.findAll();
		return invoices.stream()
				.map( InvoiceDTO::of )
				.collect( Collectors.toList() );
	}
	@Cacheable(value = "invoices")
	public List<InvoiceDTO> findAllV2() {
		List<Invoice> invoices = invoiceRepository.findAll();
		return invoices.stream()
				.map( InvoiceDTO::of )
				.collect( Collectors.toList() );
	}
	
}
