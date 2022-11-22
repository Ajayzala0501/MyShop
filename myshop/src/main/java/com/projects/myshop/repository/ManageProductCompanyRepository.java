package com.projects.myshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.myshop.enitity.ProductCompanyEntity;

@Repository
public interface ManageProductCompanyRepository extends JpaRepository<ProductCompanyEntity, Long>{

	List<ProductCompanyEntity> findByOrgRefIdAndProductTypeId(String userId,String typeId);
	
	Optional<ProductCompanyEntity> findByCompanyNameAndOrgRefIdAndProductTypeId(String comString,String userId,String typeId);
}
