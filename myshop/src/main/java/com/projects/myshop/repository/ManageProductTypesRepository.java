package com.projects.myshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.projects.myshop.enitity.ProductTypesEntity;

@Repository
public interface ManageProductTypesRepository extends JpaRepository<ProductTypesEntity, Long> {

	
	List<ProductTypesEntity> findByRegistrationOrgid(String userID);
}
