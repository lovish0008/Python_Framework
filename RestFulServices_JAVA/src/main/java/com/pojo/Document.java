/**
 * 
 */
package com.pojo;

/**
 * @author asingla
 *
 */
public class Document {

	String name;
	String type;
	String primaryKey;

	public Document(){


	}
	public Document(String name, String type) {

		this.name = name;
		this.type = type;

	}

	public String getName(){

		return name;
	}


	public void setName(String name){

		this.name = name;
	}
	public String getType(){

		return type;
	}


	public void setType(String type){

		this.type = type;
	}
	
	public String getPrimaryKey(){

		return primaryKey;

	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
}
