package com.projects.myshop.model;

public class InputModels {
	
	
	
	public static class GetProductInfo{
		
		
		public GetProductInfo() {
			super();
		}

		private String productModel;
		
		private String productColour;
		
		private String prodId;
		
		private String productSpecification;
		
		private int remainingQuantity;
		
		private int productPrice;
		
		public String getProductModel() {
			return productModel;
		}

		public void setProductModel(String productModel) {
			this.productModel = productModel;
		}

		public String getProductColour() {
			return productColour;
		}

		public void setProductColour(String productColour) {
			this.productColour = productColour;
		}

		public String getProdId() {
			return prodId;
		}

		public void setProdId(String prodId) {
			this.prodId = prodId;
		}

		public String getProductSpecification() {
			return productSpecification;
		}

		public void setProductSpecification(String productSpecification) {
			this.productSpecification = productSpecification;
		}

		public int getRemainingQuantity() {
			return remainingQuantity;
		}

		public void setRemainingQuantity(int remainingQuantity) {
			this.remainingQuantity = remainingQuantity;
		}

		public int getProductPrice() {
			return productPrice;
		}

		public void setProductPrice(int productPrice) {
			this.productPrice = productPrice;
		}

		
	}

	public static class TypeIdAndCompanyId{
		
		public TypeIdAndCompanyId() {
			super();
		}

		public TypeIdAndCompanyId(String typeId, String companyId) {
			super();
			this.typeId = typeId;
			this.companyId = companyId;
		}

		private String typeId;
		
		private String companyId;
		
		private String model;

		public String getTypeId() {
			return typeId;
		}

		public void setTypeId(String typeId) {
			this.typeId = typeId;
		}

		public String getCompanyId() {
			return companyId;
		}

		public void setCompanyId(String companyId) {
			this.companyId = companyId;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}
	}
}
