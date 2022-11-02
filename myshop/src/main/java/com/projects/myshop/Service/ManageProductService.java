package com.projects.myshop.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projects.myshop.enitity.ProductDetailsEntity;
import com.projects.myshop.enitity.ProductTypesEntity;
import com.projects.myshop.enitity.Registration;
import com.projects.myshop.model.ProductDetailsModel;

@Service
public interface ManageProductService {

	ProductTypesEntity addNewProductTypes(String typeName);

	ProductDetailsEntity addNewProduct(ProductDetailsModel detailsModel,Registration re);

	Optional<ProductDetailsEntity> getProductById(String prodID);

}
