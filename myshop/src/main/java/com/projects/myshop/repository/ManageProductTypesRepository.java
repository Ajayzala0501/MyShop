package com.projects.myshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.myshop.enitity.ProductTypesEntity;

@Repository
public interface ManageProductTypesRepository extends JpaRepository<ProductTypesEntity, Long> {

}
