package com.projects.myshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projects.myshop.enitity.ProductDetailsEntity;
import com.projects.myshop.model.InformationProjection;
import com.projects.myshop.model.InputModels;

@Repository
public interface ManageProductDetailsRepository extends JpaRepository<ProductDetailsEntity, Long> {

	@Query(value = "SELECT * FROM myshop_db.product_details_entity where prod_ref_id = ?1",nativeQuery = true)
	Optional<ProductDetailsEntity> findByProductsEntityProdId(String prodId);
	
	@Query(value = "SELECT de.user_id,de.id,de.prod_id,de.updated_date,de.product_colour,de.product_model,de.product_price,de.product_specification, de.created_date, pt.type_name as type_id,pc.company_name as company_id FROM (( product_details_entity de INNER JOIN product_types_entity pt ON de.type_id = pt.type_id ) INNER JOIN product_company_entity pc ON de.company_id = pc.company_id) where de.user_id = ?1",nativeQuery = true)
	List<ProductDetailsEntity> findAllProductByUserId(String userId);
	
	@Query(value = "SELECT de.user_id,de.id,de.prod_id,de.updated_date,de.product_colour,de.product_model,de.product_price,de.product_specification, de.created_date, pt.type_name as type_id,pc.company_name as company_id FROM (( product_details_entity de INNER JOIN product_types_entity pt ON de.type_id = pt.type_id ) INNER JOIN product_company_entity pc ON de.company_id = pc.company_id) where de.user_id = ?1 AND de.prod_id = ?2 ",nativeQuery = true)
	ProductDetailsEntity findProductByUserIdAndProdId(String userId, String prodId);
	
	ProductDetailsEntity findByUserIdAndProdId(String userId, String pString);
	
	//@Query(value = "SELECT pd.product_model FROM product_details_entity pd where pd.type_id = ?1 AND pd.company_id = ?2 AND pd.user_id = ?3", nativeQuery = true)
//	List<InformationProjection.getModelNameOnly> getModelNameBasedTypeIdAndCompanyId(String typeId, String companyId, String userId);

	List<InformationProjection.getModelNameOnly> findByTypeIdAndCompanyIdAndUserId(String typeId, String companyId, String userId);
	
	@Query(value = "SELECT *FROM product_details_entity pd inner join product_stock_entity ps on pd.id = ps.prod_ref_id where pd.type_id = ?1 AND pd.company_id =?2 AND pd.product_model =?3 AND pd.user_id = ?4 ", nativeQuery = true)
	List<ProductDetailsEntity>getByTypeIdAndCompanyIdAndProductModelAndUserId(String typeId, String companyId,String model, String userId);

	//pd.prod_id,pd.product_colour,pd.product_price, pd.product_specification,ps.remaining_quantity
	
	InformationProjection.getOnlyId findByProdIdAndUserId( String pString,String userId);

}
