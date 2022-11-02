package com.projects.myshop.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.myshop.Service.OrganizationService;
import com.projects.myshop.enitity.OrganizationEntity;
import com.projects.myshop.enitity.Registration;
import com.projects.myshop.model.OrganizationModel;
import com.projects.myshop.repository.OrganizationRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	OrganizationRepository organizationRepository;
	
	@Override
	public OrganizationEntity addOrganizationInfo(OrganizationModel model, Registration re) {
	
		OrganizationEntity orgInfo  = new OrganizationEntity();
		orgInfo.setFirstName(model.getFirstName());
		orgInfo.setLastName(model.getLastName());
		orgInfo.setCity(model.getCity());
		orgInfo.setAddress(model.getAddress());
		orgInfo.setCountry(model.getCountry());
		orgInfo.setMobileNumber(model.getMobileNumber());
		orgInfo.setState(model.getState());
		orgInfo.setEmailId(re.getEmail());
		orgInfo.setRegistration(re);
		return organizationRepository.save(orgInfo);
	}


}
