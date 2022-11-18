package com.projects.myshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.myshop.enitity.ProductCompanyEntity;
@Repository
public interface ManageProductCompanyRepository extends JpaRepository<ProductCompanyEntity, Long>{

}
