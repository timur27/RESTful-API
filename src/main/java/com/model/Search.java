package com.model;

/**
 * Created by Timur on 23.07.2017.
 */
public class Search {
    private int id;
    private String query;
    private String user;

    public Search(){};

    public Search(int id, String query, String user){
        this.id = id;
        this.query = query;
        this.user = user;
    }

    public Search(String query, String user){
        this.query = query;
        this.user = user;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getQuery(){
        return this.query;
    }

    public void setQuery(String query){
        this.query = query;
    }

    public String getUser(){
        return this.user;
    }

    public void setUser(String user){
        this.user = user;
    }
}
