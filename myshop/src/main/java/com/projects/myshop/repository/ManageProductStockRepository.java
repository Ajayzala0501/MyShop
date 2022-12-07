package com.projects.myshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projects.myshop.enitity.ProductStockEntity;

@Repository
public interface ManageProductStockRepository extends JpaRepository<ProductStockEntity , String>{

	@Query(value = "SELECT ps.user_id,ps.stock_id,ps.created_date,ps.remaining_quantity,ps.stock_quantity,ps.prod_ref_id,ps.updated_date FROM product_stock_entity ps inner join product_details_entity pd on ps.prod_ref_id = pd.id where pd.user_id = ?1",nativeQuery =  true)
	List<ProductStockEntity> getStockDetailsByUserId(String userId);
	
	ProductStockEntity findByStockIdAndUserId(String stockId,String userId);
	
	ProductStockEntity findByProdRefIdAndUserId(long Id,String userId);
}
