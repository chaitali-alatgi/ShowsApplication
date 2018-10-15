package com.example.chaitali.showsapplication.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.chaitali.showsapplication.model.Image;
import com.google.gson.annotations.SerializedName;

@Entity(tableName= "shows")
public class ShowsEntity {


    @ColumnInfo(name = "number")
    @PrimaryKey(autoGenerate = true)
    private int number;

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "status")
    private String status;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
