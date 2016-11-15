package com.pojo;

import java.io.Serializable;

public class Project implements Serializable {

	private static final long serialVersionUID = 5088863992478607917L;

	String  entityType ;
	String name;
	String idNumber;
	String primaryKey;

	public Project(){


	}
	public Project(String entityType, String name, String idNumber) {
		super();
		this.entityType = entityType;
		this.name = name;
		this.idNumber = idNumber;

	}

	public String getEntityType() {
		return entityType;
	}



	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
	
	public String getPrimaryKey() {
		return primaryKey;
	}



	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}



	public String getIdNumber() {
		return idNumber;
	}



	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	@Override
	public String toString() {
		return "Project [Entity Type=" + entityType + ", Name=" + name + ", Id_Number=" + idNumber
				+ "]";
	}
}
