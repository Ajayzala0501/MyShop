package com.projects.myshop.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.myshop.Service.ManageCustomerInvoiceDetailsService;
import com.projects.myshop.config.InfoClass;
import com.projects.myshop.config.ResponseMessageClass;
import com.projects.myshop.enitity.CustomerInvoiceDetailsEntity;
import com.projects.myshop.enitity.ProductDetailsEntity;
import com.projects.myshop.enitity.Registration;
import com.projects.myshop.model.ProductDetailsModel;

@RestController
@RequestMapping("/ManageInvoice")
public class ManageCutomerInvoiceDetailsController {

	@Autowired
	ManageCustomerInvoiceDetailsService customerInvoiceDetailsService;

	@PostMapping("/addNewCustomerInvoiceDetails")
	public ResponseEntity<ResponseMessageClass<Object>> addNewCustomerInvoiceDetails(
			@RequestBody CustomerInvoiceDetailsEntity detailsEntity, HttpServletRequest request) {
		Registration re = InfoClass.getCurrentUser(request);
		if (re != null) {
			CustomerInvoiceDetailsEntity invDetails = customerInvoiceDetailsService
					.addNewCustomerInvoiceDetails(detailsEntity, re);
			if (invDetails != null) {
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>(
						"New Invoice Details Added Successfully", HttpStatus.OK, "success"));

			} else {
				return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseMessageClass<Object>(
						"New Product Not Added, Please Try Again!!", HttpStatus.NOT_MODIFIED, "warning"));
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseMessageClass<Object>("Please Do Login First", HttpStatus.BAD_REQUEST, "error"));
		}
	}

	@GetMapping("/getAllInvoiceDetails")
	public ResponseEntity<ResponseMessageClass<Object>> getAllInvoiceDetails(HttpServletRequest request) {
		Registration re = InfoClass.getCurrentUser(request);
		if (re != null) {
			List<CustomerInvoiceDetailsEntity> invDetails = customerInvoiceDetailsService
					.getAllInvoiceDetails(re.getOrgid());
			if (invDetails != null) {
				
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>(
						invDetails, HttpStatus.OK, "success"));

			} else {
				return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseMessageClass<Object>(
						"Please Try Again!!", HttpStatus.NOT_MODIFIED, "warning"));
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseMessageClass<Object>("Please Do Login First", HttpStatus.BAD_REQUEST, "error"));
		}
	}

}
