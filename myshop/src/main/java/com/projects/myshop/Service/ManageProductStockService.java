package com.projects.myshop.Service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.projects.myshop.enitity.ProductStockEntity;
import com.projects.myshop.enitity.Registration;
import com.projects.myshop.model.InputModels;

public interface ManageProductStockService {
	List<ProductStockEntity> getAllProductStockByUserId(Registration registration);
	
	ProductStockEntity updateProductStock(ProductStockEntity entity, String userId);
	
	Object getTotalProductAndQuantity(String userId);
	
	Object getTodayTotalProductAndQuantity(String userId);
	
	
}
