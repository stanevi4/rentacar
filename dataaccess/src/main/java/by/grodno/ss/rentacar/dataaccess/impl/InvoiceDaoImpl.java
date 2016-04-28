package by.grodno.ss.rentacar.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.grodno.ss.rentacar.dataaccess.InvoiceDao;
import by.grodno.ss.rentacar.datamodel.Invoice;

@Repository
public class InvoiceDaoImpl extends AbstractDaoImpl<Invoice, Long> implements InvoiceDao {
	
	protected InvoiceDaoImpl() {
		super(Invoice.class);
	}
}
