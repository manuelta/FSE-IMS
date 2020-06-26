package com.cts.fse.ims;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.cts.fse.ims.invoice.controller.InvoiceControllerTest;
import com.cts.fse.ims.invoice.service.InvoiceServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	InvoiceControllerTest.class,
	InvoiceServiceTest.class
})
public class InvoiceTestSuit {

}
