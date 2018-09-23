package com.example.chaitali.showsapplication.model;

import com.google.gson.annotations.SerializedName;

public class Country{

	@SerializedName("code")
	private String code;

	@SerializedName("timezone")
	private String timezone;

	@SerializedName("name")
	private String name;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setTimezone(String timezone){
		this.timezone = timezone;
	}

	public String getTimezone(){
		return timezone;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}