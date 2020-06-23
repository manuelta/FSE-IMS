package com.cts.fse.ims.invoice.dto;

import java.util.Date;

import com.cts.fse.ims.invoice.model.Invoice;

public class InvoiceDTO {
	private Long id;
	private String name;
	private String description;
	private float amount;
	private Date created;
	private Date updated;
	public InvoiceDTO() {
		super();
	}
	
	public InvoiceDTO(Long id, String name, String description, float amount, Date created, Date updated) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.created = created;
		this.updated = updated;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
		
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Invoice toEntity() {
		return new Invoice( id, name, description, amount, created, updated); 
	}

	public static InvoiceDTO of(Invoice invoice) {
		return new InvoiceDTO( invoice.getId(), invoice.getName(), invoice.getDescription(), invoice.getAmount(), invoice.getCreated(), invoice.getUpdated() );
	}
	
}
