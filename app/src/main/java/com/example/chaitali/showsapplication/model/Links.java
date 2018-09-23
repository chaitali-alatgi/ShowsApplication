package com.example.chaitali.showsapplication.model;

import com.google.gson.annotations.SerializedName;

public class Links{

	@SerializedName("self")
	private Self self;

	@SerializedName("previousepisode")
	private Previousepisode previousepisode;

	public void setSelf(Self self){
		this.self = self;
	}

	public Self getSelf(){
		return self;
	}

	public void setPreviousepisode(Previousepisode previousepisode){
		this.previousepisode = previousepisode;
	}

	public Previousepisode getPreviousepisode(){
		return previousepisode;
	}
}