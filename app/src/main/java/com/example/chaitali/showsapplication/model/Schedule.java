package com.example.chaitali.showsapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Schedule{

	@SerializedName("days")
	private List<String> days;

	@SerializedName("time")
	private String time;

	public void setDays(List<String> days){
		this.days = days;
	}

	public List<String> getDays(){
		return days;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}
}