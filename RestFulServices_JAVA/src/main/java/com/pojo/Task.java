package com.pojo;

public class Task {

	private static final long serialVersionUID = 5088863992478607917L;

	String shortDescription;
	String primaryKey;

	public Task(){


	}
	public Task(String shortDescription) {

		this.shortDescription = shortDescription;

	}
	public String getShortDescription(){

		return shortDescription;
	}

	public void setShortDescription(String shortDescription){

		this.shortDescription = shortDescription;

	}
	public String getPrimaryKey() {
		return primaryKey;
	}



	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
}
