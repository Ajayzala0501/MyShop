package com.projects.myshop.Service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projects.myshop.Service.ManageProductService;
import com.projects.myshop.enitity.ProductCompanyEntity;
import com.projects.myshop.enitity.ProductDetailsEntity;
import com.projects.myshop.enitity.ProductTypesEntity;

import com.projects.myshop.enitity.Registration;
import com.projects.myshop.model.ProductDetailsModel;
import com.projects.myshop.repository.ManageProductCompanyRepository;
import com.projects.myshop.repository.ManageProductDetailsRepository;
import com.projects.myshop.repository.ManageProductTypesRepository;

@Service
public class ManageProductServiceImpl implements ManageProductService {

	@Autowired
	ManageProductTypesRepository manageProductRepository;

	@Autowired
	ManageProductDetailsRepository detailsRepository;

	@Autowired
	ManageProductCompanyRepository companyRepository;

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
	public ProductDetailsEntity addNewProduct(ProductDetailsEntity detailsModel, Registration re) {

		ProductDetailsEntity pdDetails = new ProductDetailsEntity();

		pdDetails.setTypeId(detailsModel.getTypeId());
		pdDetails.setCompanyId(detailsModel.getCompanyId());
		pdDetails.setUserId(re.getOrgid());
		pdDetails.setProductSpecification(detailsModel.getProductSpecification());
		pdDetails.setProductModel(detailsModel.getProductModel());
		pdDetails.setProductColour(detailsModel.getProductColour());
		pdDetails.setProductQuantity(detailsModel.getProductQuantity());
		pdDetails.setProductPrice(detailsModel.getProductPrice());
		return detailsRepository.save(pdDetails);
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
}
