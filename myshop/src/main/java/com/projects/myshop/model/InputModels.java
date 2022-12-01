package com.projects.myshop.model;

public class InputModels {

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
