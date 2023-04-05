package com.projects.myshop.Service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.myshop.Service.ManageCustomerInvoiceDetailsService;
import com.projects.myshop.enitity.CustomerInvoiceDetailsEntity;
import com.projects.myshop.enitity.ProductStockEntity;
import com.projects.myshop.enitity.Registration;
import com.projects.myshop.model.InformationProjection;
import com.projects.myshop.repository.ManageCustomerInvoiceDetailsRepository;
import com.projects.myshop.repository.ManageProductDetailsRepository;
import com.projects.myshop.repository.ManageProductStockRepository;

@Service
public class ManageCustomerInvoiceDetailsServiceImpl implements ManageCustomerInvoiceDetailsService{

	
	@Autowired
	ManageProductDetailsRepository detailsRepository;
	
	@Autowired
	ManageCustomerInvoiceDetailsRepository customerInvoiceDetailsRepository;
	
	@Autowired
	ManageProductStockRepository productStockRepository;
	
	@Override
	public CustomerInvoiceDetailsEntity addNewCustomerInvoiceDetails(CustomerInvoiceDetailsEntity detailsEntity,
			Registration re) {
		// TODO Auto-generated method stub
		//detailsEntity.getCustomerInvoiceProductDetails().replaceAll("\"", "\'");
		
		JSONArray jsonArray = new JSONArray(detailsEntity.getCustomerInvoiceProductDetails());
		Map<String,Integer> prodList = new HashMap();
		for(int i=0; i< jsonArray.length();i++) {
			JSONObject rec = jsonArray.getJSONObject(i);
			prodList.put(rec.getString("prodID"), Integer.valueOf(rec.getString("prodQuantity")));
		}
		//JSONObject jsonObject = new JSONObject();
		
		for(String keys : prodList.keySet()) {
			InformationProjection.getOnlyId obj = detailsRepository.findByProdIdAndUserId(keys , re.getOrgid());
			ProductStockEntity StockEntity =  productStockRepository.findByDetailsEntityIdAndUserId(obj.getId(), re.getOrgid());
			StockEntity.setRemainingQuantity(StockEntity.getRemainingQuantity()-prodList.get(keys));
			productStockRepository.save(StockEntity);
			
			
		}
		CustomerInvoiceDetailsEntity entity =  new CustomerInvoiceDetailsEntity();
		entity.setCustomerName(detailsEntity.getCustomerName());
		entity.setCustomerAddess(detailsEntity.getCustomerAddess());
		entity.setCustomerMobileNumber(detailsEntity.getCustomerMobileNumber());
		entity.setInvoiceID(detailsEntity.getInvoiceID());
		entity.setCustomerInvoiceProductDetails(detailsEntity.getCustomerInvoiceProductDetails());
		entity.setTotalQuantity(detailsEntity.getTotalQuantity());
		entity.setTotalAmount(detailsEntity.getTotalAmount());
		entity.setUserID(re.getOrgid());
		entity.setInvoiceDate(detailsEntity.getInvoiceDate());
		
		return customerInvoiceDetailsRepository.save(entity);
	}

	@Override
	public List<CustomerInvoiceDetailsEntity> getAllInvoiceDetails(String orgid) {
		// TODO Auto-generated method stub
		return customerInvoiceDetailsRepository.findByUserID(orgid) ;
	}

}
