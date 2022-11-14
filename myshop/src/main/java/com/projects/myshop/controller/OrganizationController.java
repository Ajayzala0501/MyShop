package com.projects.myshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projects.myshop.Service.OrganizationService;
import com.projects.myshop.config.InfoClass;
import com.projects.myshop.config.ResponseMessageClass;
import com.projects.myshop.enitity.OrganizationEntity;
import com.projects.myshop.enitity.Registration;
import com.projects.myshop.model.OrganizationModel;

@RestController
public class OrganizationController {

	@Autowired 
	public OrganizationService organizationService;
	
	
	@PostMapping("/addOrganizationInfo")
	public ResponseEntity<ResponseMessageClass<Object>> addOrganizationInfo(@RequestBody OrganizationModel model, HttpServletRequest request){
		
		Registration re = InfoClass.getCurrentUser(request);	
		if(re != null) {	
			OrganizationEntity orgEn = organizationService.addOrganizationInfo(model, re);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>(orgEn,HttpStatus.OK,"success"));

		}else {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageClass<Object>("Please Do Login First",HttpStatus.BAD_REQUEST,"error"));
		}
	}
	
}
