package com.projects.myshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projects.myshop.enitity.ProductDetailsEntity;

@Repository
public interface ManageProductDetailsRepository extends JpaRepository<ProductDetailsEntity, Long> {

	@Query(value = "SELECT * FROM myshop_db.product_details_entity where prod_ref_id = ?1",nativeQuery = true)
	Optional<ProductDetailsEntity> findByProductsEntityProdId(String prodId);
}
