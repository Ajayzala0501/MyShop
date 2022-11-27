package com.projects.myshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.myshop.enitity.ProductStockEntity;

@Repository
public interface ManageProductStockRepository extends JpaRepository<ProductStockEntity , String>{

}
