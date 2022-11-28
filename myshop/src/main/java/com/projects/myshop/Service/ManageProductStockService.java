package com.projects.myshop.Service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.projects.myshop.enitity.ProductStockEntity;
import com.projects.myshop.enitity.Registration;

public interface ManageProductStockService {
	List<ProductStockEntity> getAllProductStockByUserId(Registration registration);
}
