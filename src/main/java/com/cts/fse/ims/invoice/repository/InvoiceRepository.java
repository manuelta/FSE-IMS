package com.cts.fse.ims.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.fse.ims.invoice.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

}
