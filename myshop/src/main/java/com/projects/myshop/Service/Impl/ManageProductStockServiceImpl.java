package com.projects.myshop.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.myshop.Service.ManageProductStockService;
import com.projects.myshop.enitity.ProductStockEntity;
import com.projects.myshop.enitity.Registration;
import com.projects.myshop.repository.ManageProductStockRepository;
@Service
public class ManageProductStockServiceImpl implements ManageProductStockService {

	@Autowired 
	ManageProductStockRepository productStockRepository;
	
	@Override
	public List<ProductStockEntity> getAllProductStockByUserId(Registration registration) {
		// TODO Auto-generated method stub
		return productStockRepository.getStockDetailsByUserId(registration.getOrgid());
	}

}
