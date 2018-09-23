package com.example.chaitali.showsapplication.model;

import com.google.gson.annotations.SerializedName;

public class Externals{

	@SerializedName("thetvdb")
	private int thetvdb;

	@SerializedName("imdb")
	private String imdb;

	@SerializedName("tvrage")
	private int tvrage;

	public void setThetvdb(int thetvdb){
		this.thetvdb = thetvdb;
	}

	public int getThetvdb(){
		return thetvdb;
	}

	public void setImdb(String imdb){
		this.imdb = imdb;
	}

	public String getImdb(){
		return imdb;
	}

	public void setTvrage(int tvrage){
		this.tvrage = tvrage;
	}

	public int getTvrage(){
		return tvrage;
	}
}