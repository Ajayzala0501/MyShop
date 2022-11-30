package com.projects.myshop.model;

public interface InformationProjection {

	interface getCompanyNameOnly{
		String getCompanyName();
	}
	
	interface getTypeIdAndCompanyId{
		 String getTypeId();
		 String getCompanyId();
	}
	
	interface getModelNameOnly{
		String getProductModel();
	}
}
