package com.cts.fse.ims.invoice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.ims.invoice.DTO.InvoiceDTO;
import com.cts.fse.ims.invoice.model.Invoice;
import com.cts.fse.ims.invoice.repository.InvoiceRepository;

@Service
public class InvoiceService {
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public InvoiceDTO save(InvoiceDTO invoiceDTO) {
		Invoice invoice = invoiceRepository.save( invoiceDTO.toEntity() );
		return InvoiceDTO.of(invoice);
	}
	
	public InvoiceDTO update(Long invoiceId, InvoiceDTO invoiceDTO) {
		Invoice invoice = invoiceDTO.toEntity();
		invoice.setId( invoiceId );
		Invoice updatedInvoice = invoiceRepository.save( invoice );
		return InvoiceDTO.of( updatedInvoice );
	}
	
	public void delete(Long invoiceId) {
		invoiceRepository.deleteById( invoiceId );
	}

	public InvoiceDTO findOne(Long invoiceId) {
		return invoiceRepository.findById( invoiceId )
				.map( InvoiceDTO::of )
				.orElse( null );
	}
	public InvoiceDTO findOneV2(Long invoiceId) {
		return invoiceRepository.findById( invoiceId )
				.map( InvoiceDTO::of )
				.orElse( null );
	}
	
	public List<InvoiceDTO> findAll() {
		List<Invoice> invoices = (List<Invoice>) invoiceRepository.findAll();
		return invoices.stream()
				.map( InvoiceDTO::of )
				.collect( Collectors.toList() );
	}
	public List<InvoiceDTO> findAllV2() {
		List<Invoice> invoices = (List<Invoice>) invoiceRepository.findAll();
		return invoices.stream()
				.map( InvoiceDTO::of )
				.collect( Collectors.toList() );
	}
	
}
