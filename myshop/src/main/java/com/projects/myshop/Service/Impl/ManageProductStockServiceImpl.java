package com.projects.myshop.Service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.myshop.Service.ManageProductStockService;
import com.projects.myshop.enitity.ProductStockEntity;
import com.projects.myshop.enitity.Registration;
import com.projects.myshop.model.InformationProjection;
import com.projects.myshop.model.InputModels.GetTotalProductAndQuantity;
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

	@Override
	public ProductStockEntity updateProductStock(ProductStockEntity entity, String userId) {
		// TODO Auto-generated method stub
		ProductStockEntity findEntity = productStockRepository.findByStockIdAndUserId(entity.getStockId(), userId);
		findEntity.setRemainingQuantity(findEntity.getRemainingQuantity() + entity.getStockQuantity());
		findEntity.setUpdatedDate(new Date());
		return productStockRepository.save(findEntity);
	}

	@Override
	public Object getTotalProductAndQuantity(String userId) {
		// TODO Auto-generated method stub
		Object obj = productStockRepository.getTotalProductAndQuantity(userId);
		return obj;
	}

	@Override
	public Object getTodayTotalProductAndQuantity(String userId) {
		// TODO Auto-generated method stub
		Object obj = productStockRepository.getTodayTotalProductAndQuantity(userId);
		return obj;	
	}
}
