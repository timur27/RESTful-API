package com.model;

/**
 * Created by Timur on 23.07.2017.
 */

public class Search {
    private int id;
    private String query;
    private String status;
    private String user;
    private String created;

    public Search(){};

    public Search(int id, String query, String status, String user, String created){
        this.id = id;
        this.query = query;
        this.status = status;
        this.user = user;
        this.created = created;
    }

    public Search(String query, String status, String user, String created){
        this.query = query;
        this.status = status;
        this.user = user;
        this.created = created;
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

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getCreated(){
        return this.created;
    }

    public void setCreated(String created){
        this.created = created;
    }
}
