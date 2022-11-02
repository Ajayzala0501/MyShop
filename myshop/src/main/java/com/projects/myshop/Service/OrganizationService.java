package com.projects.myshop.Service;

import com.projects.myshop.enitity.OrganizationEntity;
import com.projects.myshop.enitity.Registration;
import com.projects.myshop.model.OrganizationModel;

public interface OrganizationService {

	OrganizationEntity addOrganizationInfo(OrganizationModel model, Registration re);

	
}
