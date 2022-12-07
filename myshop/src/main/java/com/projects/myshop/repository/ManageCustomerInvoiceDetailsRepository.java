package com.projects.myshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.myshop.enitity.CustomerInvoiceDetailsEntity;

@Repository
public interface ManageCustomerInvoiceDetailsRepository extends JpaRepository<CustomerInvoiceDetailsEntity, Long>{

}
