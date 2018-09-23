package com.example.chaitali.showsapplication.model;

import com.google.gson.annotations.SerializedName;

public class Image{

	@SerializedName("original")
	private String original;

	@SerializedName("medium")
	private String medium;

	public void setOriginal(String original){
		this.original = original;
	}

	public String getOriginal(){
		return original;
	}

	public void setMedium(String medium){
		this.medium = medium;
	}

	public String getMedium(){
		return medium;
	}
}