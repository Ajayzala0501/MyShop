package com.projects.myshop.model;

import com.projects.myshop.enitity.ProductStockEntity;

public interface InformationProjection {

	interface getTotalProductAndQuantityCount{
		int getRemainingQuantity();
		int getStockQuantity();
	}
	
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
		int getRemainingQuantity();
	}
	
	interface getOnlyId{
		long getId();
	}
}
