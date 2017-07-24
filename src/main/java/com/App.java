package com;

import com.dao.SearchDAO;
import com.dao.SearchDAOImpl;
import com.model.Search;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by Timur on 18.07.2017.
 */
public class App {
    public static void main(String [] args){
        Spark.get("/searches", new Route(){
            @Override
            public Object handle(Request request, Response response){
                SearchDAOImpl searchDAO = new SearchDAOImpl();
                JSONObject jsonObj = searchDAO.getAllSearches();
                return jsonObj.toString();
            }
        });
    }
}