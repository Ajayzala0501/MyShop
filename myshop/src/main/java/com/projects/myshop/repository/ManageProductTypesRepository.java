package com.projects.myshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.projects.myshop.enitity.ProductTypesEntity;

@Repository
public interface ManageProductTypesRepository extends JpaRepository<ProductTypesEntity, Long> {

	/*
	 * @Query( value =
	 * "SELECT * FROM myshop_db.product_types_entity t where t.obj_ref_id = :id",
	 * nativeQuery = true) List<ProductTypesEntity>
	 * getProductTypeByUsername(@Param("id") Long id);
	 */

	List<ProductTypesEntity> findByOrgRefId(String userId);
	
	Optional<ProductTypesEntity> findByTypeNameAndOrgRefId(String typeName,String userId);
}
 