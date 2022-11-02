package com.projects.myshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.myshop.enitity.OrganizationEntity;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long>{

}
