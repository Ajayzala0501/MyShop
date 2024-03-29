package com.projects.myshop.Service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.projects.myshop.enitity.CustomerInvoiceDetailsEntity;
import com.projects.myshop.enitity.ProductDetailsEntity;
import com.projects.myshop.enitity.Registration;


public interface ManageCustomerInvoiceDetailsService {

	CustomerInvoiceDetailsEntity addNewCustomerInvoiceDetails(CustomerInvoiceDetailsEntity detailsEntity, Registration re);

	List<CustomerInvoiceDetailsEntity> getAllInvoiceDetails(String orgid);

}
