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
	
	interface getProductInfoBasedOnModel{
		String getProdId();
		String getProductColour();
		String getProductSpecification();
		int getProductPrice();
	}
}
