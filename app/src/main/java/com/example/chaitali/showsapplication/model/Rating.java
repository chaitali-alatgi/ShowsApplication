package com.example.chaitali.showsapplication.model;

import com.google.gson.annotations.SerializedName;

public class Rating{

	@SerializedName("average")
	private double average;

	public void setAverage(double average){
		this.average = average;
	}

	public double getAverage(){
		return average;
	}
}