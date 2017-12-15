package com.arrowgs.agsadmin.entities;

import java.util.List;

public class Report {

	public static class Parameter {
	
		private String name;
		private int type;
		private String model;
		private boolean required;
		
		public Parameter() {
			// TODO Auto-generated constructor stub
		}
		
		
		
		public Parameter(String name, int type, String model, boolean required) {
			super();
			this.name = name;
			this.type = type;
			this.model = model;
			this.required = required;
		}
		
		
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}
		public boolean isRequired() {
			return required;
		}
		public void setRequired(boolean required) {
			this.required = required;
		}
		
	
	}
	
	
	private String id;
	private String name;
	
	private List<Parameter> parameters;
	
	
	public Report() {
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Parameter> getParameters() {
		return parameters;
	}
	
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

}