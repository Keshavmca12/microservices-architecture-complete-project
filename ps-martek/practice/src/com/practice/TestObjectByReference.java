package com.practice;

public class TestObjectByReference {

	int id;
	String name;
	String type;
	String address;
	
	

	public TestObjectByReference(int id, String name, String type, String address) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
