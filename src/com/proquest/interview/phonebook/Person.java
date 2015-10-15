package com.proquest.interview.phonebook;

/*
 * Implemented the Comparable for usage in collections sort and Serializable which
 * is used for transferring the object bit blob across the network. 
 */
public class Person implements  java.io.Serializable{
	
	private static final long serialVersionUID = -5824538802961009462L;
	
	public String name;
	public String phoneNumber;
	public String address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return " Person:: Name =" + getName() + ", Phone Number="
				+ getPhoneNumber() + ", Address=" + getAddress() + "";
	}
	
}
