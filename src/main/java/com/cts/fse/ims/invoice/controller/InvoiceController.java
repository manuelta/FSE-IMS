package com.cts.fse.ims.invoice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fse.ims.invoice.dto.InvoiceDTO;
import com.cts.fse.ims.invoice.exception.RecordNotFoundException;
import com.cts.fse.ims.invoice.helper.InvoiceConstants;
import com.cts.fse.ims.invoice.service.InvoiceService;

@RestController
@RequestMapping(value = "/invoice")
public class InvoiceController {
	@Autowired
	private InvoiceService invoiceService;
	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);
	
	@PostMapping(value = "/v1", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InvoiceDTO> save(@RequestBody InvoiceDTO invoiceDto) {
		LOGGER.info(InvoiceConstants.MESSAGE_IMS_SAVE);
		return ResponseEntity.ok( invoiceService.save( invoiceDto ) );
	}
	
	@PutMapping(value = "/v1/{invoiceId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InvoiceDTO> update(@PathVariable String invoiceId, @RequestBody InvoiceDTO invoiceDto) {
		LOGGER.info(InvoiceConstants.MESSAGE_IMS_UPDATE);
		return ResponseEntity.ok( invoiceService.update( Long.parseLong( invoiceId ), invoiceDto ) );
	}
	
	@DeleteMapping(value = "/v1/{invoiceId}")
	public ResponseEntity delete(@PathVariable String invoiceId) {
		LOGGER.info(InvoiceConstants.MESSAGE_IMS_DELETE);
		invoiceService.delete( Long.parseLong( invoiceId ) );
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/v1")
	public ResponseEntity deleteAll() {
		LOGGER.info(InvoiceConstants.MESSAGE_IMS_DELETE_ALL);
		invoiceService.deleteAll();
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceDTO>> get() {
		LOGGER.info(InvoiceConstants.MESSAGE_IMS_GET_V1);
		List<InvoiceDTO> invoices = invoiceService.findAll();
		if(CollectionUtils.isEmpty(invoices)) {
			throw new RecordNotFoundException(InvoiceConstants.RECORD_NOT_FOUND);
		}
		return ResponseEntity.ok(invoices);
	}
	
	@GetMapping(value = "/v2", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceDTO>> getNew() {
		LOGGER.info(InvoiceConstants.MESSAGE_IMS_GET_V2);
		List<InvoiceDTO> invoices = invoiceService.findAllV2();
		if(CollectionUtils.isEmpty(invoices)) {
			throw new RecordNotFoundException(InvoiceConstants.RECORD_NOT_FOUND);
		}
		return ResponseEntity.ok(invoices);
	}
	
	@GetMapping(value = "/v1/{invoiceId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InvoiceDTO> getById(@PathVariable String invoiceId) {
		LOGGER.info(InvoiceConstants.MESSAGE_IMS_GET_BY_ID_V1);
		InvoiceDTO invoiceDTO = invoiceService.findOne( Long.parseLong( invoiceId ) );
		if(null == invoiceDTO) {
			throw new RecordNotFoundException(InvoiceConstants.RECORD_NOT_FOUND);
		}
		return ResponseEntity.ok(invoiceDTO);
	}
	@GetMapping(value = "/v2/{invoiceId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InvoiceDTO> getByIdNew(@PathVariable String invoiceId) {
		LOGGER.info(InvoiceConstants.MESSAGE_IMS_GET_BY_ID_V2);
		InvoiceDTO invoiceDTO = invoiceService.findOneV2( Long.parseLong( invoiceId ) );
		if(null == invoiceDTO) {
			throw new RecordNotFoundException(InvoiceConstants.RECORD_NOT_FOUND);
		}
		return ResponseEntity.ok(invoiceDTO);
	}
}
