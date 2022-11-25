package com.projects.myshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projects.myshop.enitity.ProductDetailsEntity;

@Repository
public interface ManageProductDetailsRepository extends JpaRepository<ProductDetailsEntity, Long> {

	@Query(value = "SELECT * FROM myshop_db.product_details_entity where prod_ref_id = ?1",nativeQuery = true)
	Optional<ProductDetailsEntity> findByProductsEntityProdId(String prodId);
	
	@Query(value = "SELECT de.product_quantity,de.user_id,de.id,de.prod_id,de.updated_date,de.product_colour,de.product_model,de.product_price,de.product_specification, de.created_date, pt.type_name as type_id,pc.company_name as company_id FROM (( product_details_entity de INNER JOIN product_types_entity pt ON de.type_id = pt.type_id ) INNER JOIN product_company_entity pc ON de.company_id = pc.company_id) where de.user_id = ?1",nativeQuery = true)
	List<ProductDetailsEntity> findAllProductByUserId(String userId);
	
	
	@Query(value = "SELECT de.product_quantity,de.user_id,de.id,de.prod_id,de.updated_date,de.product_colour,de.product_model,de.product_price,de.product_specification, de.created_date, pt.type_name as type_id,pc.company_name as company_id FROM (( product_details_entity de INNER JOIN product_types_entity pt ON de.type_id = pt.type_id ) INNER JOIN product_company_entity pc ON de.company_id = pc.company_id) where de.user_id = ?1 AND de.prod_id = ?2 ",nativeQuery = true)
	ProductDetailsEntity findProductByUserIdAndProdId(String userId, String prodId);
	
	
	ProductDetailsEntity findByUserIdAndProdId(String userId, String pString);
}
