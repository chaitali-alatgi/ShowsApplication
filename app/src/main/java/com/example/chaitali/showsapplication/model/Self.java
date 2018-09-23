package com.example.chaitali.showsapplication.model;

import com.google.gson.annotations.SerializedName;

public class Self{

	@SerializedName("href")
	private String href;

	public void setHref(String href){
		this.href = href;
	}

	public String getHref(){
		return href;
	}
}