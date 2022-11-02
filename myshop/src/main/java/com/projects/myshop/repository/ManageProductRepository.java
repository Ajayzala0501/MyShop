package com.projects.myshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.myshop.enitity.ProductsEntity;

@Repository
public interface ManageProductRepository extends JpaRepository<ProductsEntity, Long>{

}
