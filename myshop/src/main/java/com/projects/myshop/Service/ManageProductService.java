package com.projects.myshop.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projects.myshop.enitity.ProductCompanyEntity;
import com.projects.myshop.enitity.ProductDetailsEntity;
import com.projects.myshop.enitity.ProductTypesEntity;
import com.projects.myshop.enitity.Registration;
import com.projects.myshop.model.InformationProjection;
import com.projects.myshop.model.ProductDetailsModel;

@Service
public interface ManageProductService {

	ProductTypesEntity addNewProductTypes(String typeName,Registration re);

	ProductDetailsEntity addNewProduct(ProductDetailsModel detailsModel,Registration re);

	Optional<ProductDetailsEntity> getProductById(String prodID);

	ProductCompanyEntity addNewProductCompany(ProductCompanyEntity companyEntity,Registration re);
	
	List<ProductTypesEntity> getAllProductTypes(Registration registration);
	
	List<ProductCompanyEntity> getAllProductCompany(String typeId,Registration registration);
	
	Optional<ProductTypesEntity> getProductTypesByName(String typeName,Registration registration);
	
	Optional<ProductCompanyEntity> getProductCompanyByName(ProductCompanyEntity companyEntity,Registration registration);
	
	List<ProductDetailsEntity> getAllProducts(Registration registration);
	
	ProductDetailsEntity getProductByIdAndUserId(Registration registration, String prodId);
	
	ProductDetailsEntity updateProductDetails(Registration registration, String prodId, ProductDetailsEntity newDetailsEntity);

	void deleteByProduct(String prodId, Registration registration);
	
	String getCompanyNameById(String compName,String userId);
	
	List<String> getModelNameBasedTypeIdAndCompanyId(String typeId, String companyId, String userId);
	 
	List<ProductDetailsEntity> getProductInfoBasedOnModel(String typeId, String companyId,String model, String userId);
}


