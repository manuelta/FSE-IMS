package com.cts.fse.ims.invoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fse.ims.invoice.DTO.InvoiceDTO;
import com.cts.fse.ims.invoice.service.InvoiceService;

@RestController
@RequestMapping(value = "/invoice")
public class InvoiceController {
	@Autowired
	private InvoiceService invoiceService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InvoiceDTO> save(@RequestBody InvoiceDTO invoiceDto) {
		return ResponseEntity.ok( invoiceService.save( invoiceDto ) );
	}
	
	@RequestMapping(value = "{invoiceId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity update(@PathVariable String invoiceId, @RequestBody InvoiceDTO invoiceDto) {
		return ResponseEntity.ok( invoiceService.update( Long.parseLong( invoiceId ), invoiceDto ) );
	}
	
	@RequestMapping(value = "{invoiceId}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable String invoiceId) {
		invoiceService.delete( Long.parseLong( invoiceId ) );
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity get() {
		return ResponseEntity.ok( invoiceService.findAll());
	}
	
	@RequestMapping(value = "{invoiceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getById(@PathVariable String invoiceId) {
		return ResponseEntity.ok( invoiceService.findOne( Long.parseLong( invoiceId ) ) );
	}
	
	

}
