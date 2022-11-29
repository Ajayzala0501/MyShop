package com.projects.myshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projects.myshop.enitity.ProductCompanyEntity;
import com.projects.myshop.model.InformationProjection;

@Repository
public interface ManageProductCompanyRepository extends JpaRepository<ProductCompanyEntity, Long>{

	List<ProductCompanyEntity> findByOrgRefIdAndProductTypeId(String userId,String typeId);
	
	Optional<ProductCompanyEntity> findByCompanyNameAndOrgRefIdAndProductTypeId(String comString,String userId,String typeId);
	
	//@Query(value = "SELECT company_name FROM product_company_entity where company_id =?1 and org_ref_id = ?2")
	//String getCompanyNameById(String compName,String userID);
	
	InformationProjection.getCompanyNameOnly findByCompanyIdAndOrgRefId(String compName,String userID);
}
