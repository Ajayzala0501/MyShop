package com.projects.myshop.Service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projects.myshop.Service.ManageProductService;
import com.projects.myshop.enitity.ProductCompanyEntity;
import com.projects.myshop.enitity.ProductDetailsEntity;
import com.projects.myshop.enitity.ProductStockEntity;
import com.projects.myshop.enitity.ProductTypesEntity;

import com.projects.myshop.enitity.Registration;
import com.projects.myshop.model.InformationProjection;
import com.projects.myshop.model.InformationProjection.getModelNameOnly;
import com.projects.myshop.model.ProductDetailsModel;
import com.projects.myshop.repository.ManageProductCompanyRepository;
import com.projects.myshop.repository.ManageProductDetailsRepository;
import com.projects.myshop.repository.ManageProductStockRepository;
import com.projects.myshop.repository.ManageProductTypesRepository;

@Service
public class ManageProductServiceImpl implements ManageProductService {

	@Autowired
	ManageProductTypesRepository manageProductRepository;

	@Autowired
	ManageProductDetailsRepository detailsRepository;

	@Autowired
	ManageProductCompanyRepository companyRepository;

	@Autowired
	ManageProductStockRepository manageProductStockRepository;
	
	@Override
	public ProductTypesEntity addNewProductTypes(String typeName, Registration re) {
		// TODO Auto-generated method stub
		ProductTypesEntity pdType = new ProductTypesEntity();
		pdType.setTypeName(typeName.toUpperCase());
		pdType.setOrgRefId(re.getOrgid());
		return manageProductRepository.save(pdType);
	}

	@Override
	public ProductCompanyEntity addNewProductCompany(ProductCompanyEntity companyEntity, Registration re) {
		// TODO Auto-generated method stub
		ProductCompanyEntity pdCompany = new ProductCompanyEntity();
		pdCompany.setCompanyName(companyEntity.getCompanyName().toUpperCase());
		pdCompany.setProductTypeId(companyEntity.getProductTypeId());
		pdCompany.setOrgRefId(re.getOrgid());
		return companyRepository.save(pdCompany);
	}

	@Override
	public ProductDetailsEntity addNewProduct(ProductDetailsModel detailsModel, Registration re) {
		ProductDetailsEntity pdDetails = new ProductDetailsEntity();
		ProductStockEntity stockEntity = new ProductStockEntity();
		

		//manageProductStockRepository.save(stockEntity);
		
		
		pdDetails.setTypeId(detailsModel.getTypeId());
		pdDetails.setCompanyId(detailsModel.getCompanyId());
		pdDetails.setUserId(re.getOrgid());
		pdDetails.setProductSpecification(detailsModel.getProductSpecification());
		pdDetails.setProductModel(detailsModel.getProductModel());
		pdDetails.setProductColour(detailsModel.getProductColour());
		pdDetails.setProductPrice(detailsModel.getProductPrice());
		
		stockEntity.setStockQuantity(detailsModel.getProductQuantity());
		stockEntity.setRemainingQuantity(detailsModel.getProductQuantity());
		stockEntity.setDetailsEntity(pdDetails);
		//pdDetails.setStockEntity(stockEntity);
		stockEntity.setUserId(re.getOrgid());
		ProductStockEntity entity = manageProductStockRepository.save(stockEntity);
		ProductDetailsEntity addDetails = entity.getDetailsEntity();
		return addDetails;
	}

	@Override
	@Transactional
	public Optional<ProductDetailsEntity> getProductById(String prodID) {
		Optional<ProductDetailsEntity> pdDetails = detailsRepository.findByProductsEntityProdId(prodID);
		return pdDetails;
	}

	@Override
	public List<ProductTypesEntity> getAllProductTypes(Registration registration) {
		// TODO Auto-generated method stub
		return manageProductRepository.findByOrgRefId(registration.getOrgid());
	}

	@Override
	public List<ProductCompanyEntity> getAllProductCompany(String typeId, Registration registration) {
		// TODO Auto-generated method stub
		return companyRepository.findByOrgRefIdAndProductTypeId(registration.getOrgid(), typeId);
	}

	@Override
	public Optional<ProductTypesEntity> getProductTypesByName(String typeName, Registration registration) {
		// TODO Auto-generated method stub
		return manageProductRepository.findByTypeNameAndOrgRefId(typeName, registration.getOrgid());
	}

	@Override
	public Optional<ProductCompanyEntity> getProductCompanyByName(ProductCompanyEntity companyEntity,
			Registration registration) {
		// TODO Auto-generated method stub
		return companyRepository.findByCompanyNameAndOrgRefIdAndProductTypeId(
				companyEntity.getCompanyName().toUpperCase(), registration.getOrgid(),
				companyEntity.getProductTypeId());
	}

	@Override
	public List<ProductDetailsEntity> getAllProducts(Registration registration) {
		return detailsRepository.findAllProductByUserId(registration.getOrgid());
	}

	@Override
	public ProductDetailsEntity getProductByIdAndUserId(Registration registration, String prodId) {
		// TODO Auto-generated method stub
		return detailsRepository.findProductByUserIdAndProdId(registration.getOrgid(), prodId);
	}

	@Override
	public ProductDetailsEntity updateProductDetails(Registration registration, String prodId, ProductDetailsEntity newDetailsEntity) {
		// TODO Auto-generated method stub
		ProductDetailsEntity detailsEntity = detailsRepository.findByUserIdAndProdId(registration.getOrgid(),prodId);
		detailsEntity.setTypeId(newDetailsEntity.getTypeId());
		detailsEntity.setCompanyId(newDetailsEntity.getCompanyId());
		detailsEntity.setProductModel(newDetailsEntity.getProductModel());
		detailsEntity.setProductColour(newDetailsEntity.getProductColour());
		detailsEntity.setProductSpecification(newDetailsEntity.getProductSpecification());
		detailsEntity.setProductPrice(newDetailsEntity.getProductPrice());
		detailsEntity.setUpdatedDate(new Date());
		return detailsRepository.save(detailsEntity);
	}

	@Transactional
	public void deleteByProduct(String prodId, Registration registration) {	
		ProductDetailsEntity detailsEntity = detailsRepository.findByUserIdAndProdId(registration.getOrgid(), prodId);
		detailsRepository.delete(detailsEntity);
	}

	@Override
	public String getCompanyNameById(String compName, String userId) {
		// TODO Auto-generated method stub
	 InformationProjection.getCompanyNameOnly obj =	companyRepository.findByCompanyIdAndOrgRefId(compName, userId);
		return obj.getCompanyName();
	}

	@Override
	public List<String> getModelNameBasedTypeIdAndCompanyId(String typeId, String companyId, String userId) {
		// TODO Auto-generated method stub
		List<String> models = new ArrayList<>();
		List<InformationProjection.getModelNameOnly> pdModel =detailsRepository.findByTypeIdAndCompanyIdAndUserId(typeId, companyId, userId);
		for(InformationProjection.getModelNameOnly data : pdModel) {
			models.add(data.getProductModel()); 
		}
		return models;
	}

	@Override
	public List<ProductDetailsEntity> getProductInfoBasedOnModel(String typeId, String companyId, String model,
			String userId) {
		// TODO Auto-generated method stub
		List<ProductDetailsEntity> entityList = new ArrayList<>();
		List<InformationProjection.getProductInfoBasedOnModel> obj = detailsRepository.findByTypeIdAndCompanyIdAndProductModelAndUserId(typeId, companyId, model, userId);
		for(InformationProjection.getProductInfoBasedOnModel objInput : obj) {
			ProductDetailsEntity entity = new ProductDetailsEntity();
			entity.setProdId(objInput.getProdId());
			entity.setProductColour(objInput.getProductColour());
			entity.setProductPrice(objInput.getProductPrice());
			entity.setProductSpecification(objInput.getProductSpecification());
			entityList.add(entity);
		}
		
		return entityList;
	}
}
