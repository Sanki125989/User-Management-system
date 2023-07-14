package com.user.management.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="address_table")
public class Address {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long add_id;
	
	private String line;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String pincode; 
	
	private String addresstype;

	

	public String getAddresstype() {
		return addresstype;
	}

	public void setAddresstype(String addresstype) {
		this.addresstype = addresstype;
	}

	public Long getAdd_id() {
		return add_id;
	}

	public void setAdd_id(Long add_id) {
		this.add_id = add_id;
	}
	

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "Address [add_id=" + add_id + ", line=" + line + ", street=" + street + ", city=" + city + ", state="
				+ state + ", pincode=" + pincode + ", addresstype=" + addresstype + "]";
	}

	
}
