package com.projects.myshop.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.myshop.Service.ManageProductService;
import com.projects.myshop.config.InfoClass;
import com.projects.myshop.config.ResponseMessageClass;
import com.projects.myshop.enitity.OrganizationEntity;
import com.projects.myshop.enitity.ProductCompanyEntity;
import com.projects.myshop.enitity.ProductDetailsEntity;
import com.projects.myshop.enitity.ProductTypesEntity;
import com.projects.myshop.enitity.Registration;
import com.projects.myshop.exception.MainExceptionClass;
import com.projects.myshop.exception.MainExceptionClass.ProductCompanyAlreadyExits;
import com.projects.myshop.exception.MainExceptionClass.ProductTypesAlreadyExits;
import com.projects.myshop.model.ProductDetailsModel;

@RestController
@RequestMapping("/Product")
public class ManageProductController {

	@Autowired 
	MainExceptionClass mainExceptionClass;
	
	@Autowired
	ManageProductService manageProductService;
	
	@PostMapping("/addNewProductTypes")
	public ResponseEntity<ResponseMessageClass<Object>>addNewProductTypes(@RequestBody ProductTypesEntity productTypesEntity, HttpServletRequest request) throws ProductTypesAlreadyExits{
		Registration re = InfoClass.getCurrentUser(request);	
		if(re != null) {
			Optional<ProductTypesEntity> checkIfExits = manageProductService.getProductTypesByName(productTypesEntity.getTypeName().toUpperCase(),re);
			if(checkIfExits.isPresent()) {
				throw mainExceptionClass.new ProductTypesAlreadyExits("Product type already exits!");
			}
			ProductTypesEntity pdtype = manageProductService.addNewProductTypes(productTypesEntity.getTypeName(),re);
			if(pdtype != null) {
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>(pdtype,HttpStatus.OK,"success"));

			}else {
				return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseMessageClass<Object>("New Type Not Added, Please Try Again!!",HttpStatus.NOT_MODIFIED,"warning"));
			}
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageClass<Object>("Please Do Login First",HttpStatus.BAD_REQUEST,"error"));
		}
	}
	
	@PostMapping("/addNewProductCompany")
	public ResponseEntity<ResponseMessageClass<Object>>addNewProductCompany(@RequestBody ProductCompanyEntity productCompanyEntity, HttpServletRequest request) throws ProductCompanyAlreadyExits {

		Registration re = InfoClass.getCurrentUser(request);	
		if(re != null) {
			
			Optional<ProductCompanyEntity> checkIfExits = manageProductService.getProductCompanyByName(productCompanyEntity, re);
			if(checkIfExits.isPresent()) {
				throw mainExceptionClass.new ProductCompanyAlreadyExits("Product Company Already Exits!");
			}
			ProductCompanyEntity pdCompany = manageProductService.addNewProductCompany(productCompanyEntity,re);
			if(pdCompany != null) {
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>(pdCompany,HttpStatus.OK,"success"));

			}else {
				return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseMessageClass<Object>("New Company Not Added, Please Try Again!!",HttpStatus.NOT_MODIFIED,"warning"));
			}
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageClass<Object>("Please Do Login First",HttpStatus.BAD_REQUEST,"error"));
		}
	}	
	
	@PostMapping("/addNewProduct")
	public ResponseEntity<ResponseMessageClass<Object>>addNewProduct(@RequestBody ProductDetailsModel  detailsEntity, HttpServletRequest request){
		Registration re = InfoClass.getCurrentUser(request);	
		if(re != null) {	
			ProductDetailsEntity pdDetails = manageProductService.addNewProduct(detailsEntity,re);
			if(pdDetails != null) {
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>("New Product Added Successfully",HttpStatus.OK,"success"));

			}else {
				return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseMessageClass<Object>("New Product Not Added, Please Try Again!!",HttpStatus.NOT_MODIFIED,"warning"));
			}
		}else {		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageClass<Object>("Please Do Login First",HttpStatus.BAD_REQUEST,"error"));
		}
	}
	
	@GetMapping("/getProductById/{prodID}")
	public ResponseEntity<ResponseMessageClass<Object>>getProductById(@PathVariable("prodID") String prodID, HttpServletRequest request){
		Registration re = InfoClass.getCurrentUser(request);	
		if(re != null) {	
			Optional<ProductDetailsEntity> pdDetails = manageProductService.getProductById(prodID);
			if(pdDetails.isPresent()){
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>(pdDetails,HttpStatus.OK,"success"));
				
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageClass<Object>("Product Not Exits",HttpStatus.BAD_REQUEST,"warning"));
			}
		}else {		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageClass<Object>("Please Do Login First",HttpStatus.BAD_REQUEST,"error"));
		}
	}
	@GetMapping("/getAllProductTypes")
	public ResponseEntity<ResponseMessageClass<Object>> getAllProductTypes(HttpServletRequest request){
		Registration re = InfoClass.getCurrentUser(request);	
		if(re != null) {	
			List<ProductTypesEntity> getTypeList = manageProductService.getAllProductTypes(re);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>(getTypeList, HttpStatus.OK, "success"));
		}
		else {		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageClass<Object>("Please Do Login First",HttpStatus.BAD_REQUEST,"error"));
		}
	}
	
	@GetMapping("/getAllProductCompany")
	public ResponseEntity<ResponseMessageClass<Object>> getAllProductCompany(HttpServletRequest request){
		Registration re = InfoClass.getCurrentUser(request);	
		if(re != null) {
			String typeId = request.getParameter("typeId");
			List<ProductCompanyEntity> getCompanyList = manageProductService.getAllProductCompany(typeId,re);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>(getCompanyList, HttpStatus.OK, "success"));
		}
		else {		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageClass<Object>("Please Do Login First",HttpStatus.BAD_REQUEST,"error"));
		}
	}
	
	@GetMapping("/getAllProductInfo")
	public ResponseEntity<ResponseMessageClass<Object>> getAllProductInfo(HttpServletRequest request){	
		Registration re = InfoClass.getCurrentUser(request);	
		if(re != null) {
			List<ProductDetailsEntity> pdDetails = manageProductService.getAllProducts(re);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>(pdDetails, HttpStatus.OK, "success"));
		}
		else {		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageClass<Object>("Please Do Login First",HttpStatus.BAD_REQUEST,"error"));
		}
	}

	@PostMapping("/updateProductDetails/{id}")
	public ResponseEntity<ResponseMessageClass<Object>> updateProductDetails(HttpServletRequest request, @PathVariable("id") String prodId, @RequestBody ProductDetailsEntity detailsEntity){
		Registration re = InfoClass.getCurrentUser(request);	
		if(re != null) {
			ProductDetailsEntity pdDetails = manageProductService.updateProductDetails(re,prodId,detailsEntity);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>("Product Update Successfull", HttpStatus.OK, "success"));
		}
		else {		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageClass<Object>("Please Do Login First",HttpStatus.BAD_REQUEST,"error"));
		}	
	}
	
	public ProductDetailsEntity getProductByIdAndUserName(String prodId, HttpServletRequest request) {
		Registration re = InfoClass.getCurrentUser(request);	
		if(re != null) {
			ProductDetailsEntity pdDetails = manageProductService.getProductByIdAndUserId(re,prodId);
			return pdDetails;
		}
		return null;
	}
	public ProductDetailsEntity deleteProductById(String proId, HttpServletRequest request) {
		
		Registration re = InfoClass.getCurrentUser(request);	
		if(re != null) {
			ProductDetailsEntity pdDetails = manageProductService.deleteByProduct(proId, re);
			return pdDetails;
		}
		return null;
	}
}   
