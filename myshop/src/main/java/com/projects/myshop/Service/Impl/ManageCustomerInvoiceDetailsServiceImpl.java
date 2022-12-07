package com.projects.myshop.Service.Impl;

import java.util.ArrayList;
import java.util.List;

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
		List<String> prodList = new ArrayList<>();
		for(int i=0; i< jsonArray.length();i++) {
			JSONObject rec = jsonArray.getJSONObject(i);
			String id = rec.getString("prodID");
			prodList.add(id);
		}
		//JSONObject jsonObject = new JSONObject();
		for(int i=0; i < prodList.size();i++) {
		InformationProjection.getOnlyId obj = detailsRepository.findByProdIdAndUserId(prodList.get(i), re.getOrgid());
		ProductStockEntity entity =  productStockRepository.findByProdRefIdAndUserId(obj.getId(), re.getOrgid());
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

}
