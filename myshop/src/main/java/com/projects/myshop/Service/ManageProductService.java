package com.projects.myshop.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projects.myshop.enitity.ProductCompanyEntity;
import com.projects.myshop.enitity.ProductDetailsEntity;
import com.projects.myshop.enitity.ProductTypesEntity;
import com.projects.myshop.enitity.Registration;
import com.projects.myshop.model.ProductDetailsModel;

@Service
public interface ManageProductService {

	ProductTypesEntity addNewProductTypes(String typeName,Registration re);

	ProductDetailsEntity addNewProduct(ProductDetailsModel detailsModel,Registration re);

	Optional<ProductDetailsEntity> getProductById(String prodID);

	ProductCompanyEntity addNewProductCompany(String companyName,Registration re);
	
	List<ProductTypesEntity> getAllProductTypes(Registration registration);
	
}
