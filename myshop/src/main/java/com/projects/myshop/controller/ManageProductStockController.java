package com.projects.myshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.myshop.Service.ManageProductStockService;
import com.projects.myshop.config.InfoClass;
import com.projects.myshop.config.ResponseMessageClass;
import com.projects.myshop.enitity.ProductStockEntity;
import com.projects.myshop.enitity.ProductTypesEntity;
import com.projects.myshop.enitity.Registration;

@RestController
@RequestMapping("/Stock")
public class ManageProductStockController {

	@Autowired
	ManageProductStockService manageProductStockService;
	

	
	@GetMapping("/getAllStockDetails")
	public ResponseEntity<ResponseMessageClass<Object>> getAllStockDetails(HttpServletRequest request){
		
		Registration re = InfoClass.getCurrentUser(request);	
		if(re != null) {	
			List<ProductStockEntity> psList = manageProductStockService.getAllProductStockByUserId(re); 
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>(psList, HttpStatus.OK, "success"));
		}
		else {		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageClass<Object>("Please Do Login First",HttpStatus.BAD_REQUEST,"error"));
		}
	}
	
}
