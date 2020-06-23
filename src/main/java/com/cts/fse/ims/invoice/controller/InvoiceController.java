package com.cts.fse.ims.invoice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fse.ims.invoice.DTO.InvoiceDTO;
import com.cts.fse.ims.invoice.exception.RecordNotFoundException;
import com.cts.fse.ims.invoice.helper.InvoiceConstants;
import com.cts.fse.ims.invoice.service.InvoiceService;

@RestController
@RequestMapping(value = "/invoice")
public class InvoiceController {
	@Autowired
	private InvoiceService invoiceService;
	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);
	
	@RequestMapping(value = "/v1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InvoiceDTO> save(@RequestBody InvoiceDTO invoiceDto) {
		LOGGER.info(InvoiceConstants.MESSAGE_IMS_SAVE);
		return ResponseEntity.ok( invoiceService.save( invoiceDto ) );
	}
	
	@RequestMapping(value = "{invoiceId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity update(@PathVariable String invoiceId, @RequestBody InvoiceDTO invoiceDto) {
		LOGGER.info(InvoiceConstants.MESSAGE_IMS_UPDATE);
		return ResponseEntity.ok( invoiceService.update( Long.parseLong( invoiceId ), invoiceDto ) );
	}
	
	@RequestMapping(value = "{invoiceId}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable String invoiceId) {
		LOGGER.info(InvoiceConstants.MESSAGE_IMS_DELETE);
		invoiceService.delete( Long.parseLong( invoiceId ) );
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity deleteAll() {
		LOGGER.info(InvoiceConstants.MESSAGE_IMS_DELETE_ALL);
		invoiceService.deleteAll();
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/v1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity get() {
		LOGGER.info(InvoiceConstants.MESSAGE_IMS_GET_V1);
		List<InvoiceDTO> invoices = invoiceService.findAll();
		if(CollectionUtils.isEmpty(invoices)) {
			throw new RecordNotFoundException(InvoiceConstants.RECORD_NOT_FOUND);
		}
		return ResponseEntity.ok(invoices);
	}
	
	@RequestMapping(value = "/v2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getNew() {
		LOGGER.info(InvoiceConstants.MESSAGE_IMS_GET_V2);
		List<InvoiceDTO> invoices = invoiceService.findAllV2();
		if(CollectionUtils.isEmpty(invoices)) {
			throw new RecordNotFoundException(InvoiceConstants.RECORD_NOT_FOUND);
		}
		return ResponseEntity.ok(invoices);
	}
	
	@RequestMapping(value = "/v1/{invoiceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getById(@PathVariable String invoiceId) {
		LOGGER.info(InvoiceConstants.MESSAGE_IMS_GET_BY_ID_V1);
		InvoiceDTO invoiceDTO = invoiceService.findOne( Long.parseLong( invoiceId ) );
		if(null == invoiceDTO) {
			throw new RecordNotFoundException(InvoiceConstants.RECORD_NOT_FOUND);
		}
		return ResponseEntity.ok(invoiceDTO);
	}
	@RequestMapping(value = "/v2/{invoiceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getByIdNew(@PathVariable String invoiceId) {
		LOGGER.info(InvoiceConstants.MESSAGE_IMS_GET_BY_ID_V2);
		InvoiceDTO invoiceDTO = invoiceService.findOneV2( Long.parseLong( invoiceId ) );
		if(null == invoiceDTO) {
			throw new RecordNotFoundException(InvoiceConstants.RECORD_NOT_FOUND);
		}
		return ResponseEntity.ok(invoiceDTO);
	}
}
