package com.projects.myshop.controller;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
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

import com.projects.myshop.Service.ManageProductStockService;
import com.projects.myshop.config.InfoClass;
import com.projects.myshop.config.ResponseMessageClass;
import com.projects.myshop.enitity.ProductStockEntity;
import com.projects.myshop.enitity.Registration;
import com.projects.myshop.model.InputModels;
import com.projects.myshop.model.StockDisplayModel;

@RestController
@RequestMapping("/Stock")
public class ManageProductStockController {

	@Autowired
	ManageProductStockService manageProductStockService;
	
	@Autowired
	ManageProductController productController;
	
	@GetMapping("/getAllStockDetails")
	public ResponseEntity<ResponseMessageClass<Object>> getAllStockDetails(HttpServletRequest request){	
		Registration re = InfoClass.getCurrentUser(request);	
		if(re != null) {	
			List<ProductStockEntity> psList = manageProductStockService.getAllProductStockByUserId(re); 
			List<StockDisplayModel> displayModels = new ArrayList<>(); 
			 
			SimpleDateFormat DateFor = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			   
			for(ProductStockEntity entity :psList) {
				StockDisplayModel displayModel = new StockDisplayModel();
				displayModel.setCompName(productController.findProductCompanyNameById(entity.getDetailsEntity().getCompanyId(),re.getOrgid()));
				displayModel.setProdModel(entity.getDetailsEntity().getProductModel());
				displayModel.setStockQuantity(entity.getStockQuantity());
				displayModel.setRemainingQuantity(entity.getRemainingQuantity());
				displayModel.setUpdatedDate(DateFor.format(entity.getUpdatedDate()));
				displayModel.setStockId(entity.getStockId());
				displayModel.setProdId(entity.getDetailsEntity().getProdId());
				displayModels.add(displayModel);
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>(displayModels, HttpStatus.OK, "success"));
		}
		else {		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageClass<Object>("Please Do Login First",HttpStatus.BAD_REQUEST,"error"));
		}
	}	
	
	@PostMapping("/updateStock")
	public ResponseEntity<ResponseMessageClass<Object>> updateStock(@RequestBody ProductStockEntity entity,HttpServletRequest request){		
		Registration re = InfoClass.getCurrentUser(request);	
		if(re != null) {	
			ProductStockEntity resEntity = manageProductStockService.updateProductStock(entity, re.getOrgid());
			if(resEntity != null) {

				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>("Product Stock Update Successfull", HttpStatus.OK, "success"));	
			}else {
				return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseMessageClass<Object>("Product Stock Not Updated", HttpStatus.FAILED_DEPENDENCY, "warning"));	
					
			}
		}
		else {		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageClass<Object>("Please Do Login First",HttpStatus.BAD_REQUEST,"error"));
		}		
	}
	
	@GetMapping("/getTotalProductAndQuantity")
	public ResponseEntity<ResponseMessageClass<Object>> getTotalProductAndQuantity(HttpServletRequest request){
		Registration re = InfoClass.getCurrentUser(request);	
		if(re != null) {	
			
			Object totalCount  = manageProductStockService.getTotalProductAndQuantity(re.getOrgid());
			if(totalCount != null) {

				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>(totalCount, HttpStatus.OK, "success"));	
			}else {
				return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseMessageClass<Object>("Product Stock Not Updated", HttpStatus.FAILED_DEPENDENCY, "warning"));	
					
			}
		}
		else {		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageClass<Object>("Please Do Login First",HttpStatus.BAD_REQUEST,"error"));
		}
	}
	@GetMapping("/getTodayTotalProductAndQuantity")
	public ResponseEntity<ResponseMessageClass<Object>> getTodayTotalProductAndQuantity(HttpServletRequest request){
		Registration re = InfoClass.getCurrentUser(request);	
		if(re != null) {	
			
			Object totalCount  = manageProductStockService.getTodayTotalProductAndQuantity(re.getOrgid());
			if(totalCount != null) {

				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>(totalCount, HttpStatus.OK, "success"));	
			}else {
				return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseMessageClass<Object>("Product Stock Not Updated", HttpStatus.FAILED_DEPENDENCY, "warning"));	
					
			}
		}
		else {		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageClass<Object>("Please Do Login First",HttpStatus.BAD_REQUEST,"error"));
		}
	}
}
