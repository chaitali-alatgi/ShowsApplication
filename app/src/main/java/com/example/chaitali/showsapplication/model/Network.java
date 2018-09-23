package com.example.chaitali.showsapplication.model;

import com.google.gson.annotations.SerializedName;

public class Network{

	@SerializedName("country")
	private Country country;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public void setCountry(Country country){
		this.country = country;
	}

	public Country getCountry(){
		return country;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
}