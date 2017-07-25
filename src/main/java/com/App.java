package com;

import com.dao.SearchDAOImpl;
import com.model.Search;
import org.json.JSONObject;
import spark.*;

import java.text.SimpleDateFormat;

/**
 * Created by Timur on 18.07.2017.
 */

public class App {
    public static void main(String [] args){
        SearchDAOImpl searchDAO = new SearchDAOImpl();

        Spark.get("/searches", new Route(){
            @Override
            public Object handle(Request request, Response response){
                String username = request.queryParams("username");
                JSONObject jsonObject;
                if (username == null)
                    jsonObject = searchDAO.getAllSearches();
                else
                    jsonObject = searchDAO.getSearch(username);
                return jsonObject.toString();
            }
        });

        Spark.get("/search", new Route(){
            @Override
            public Object handle(Request request, Response response){
                Search search = new Search(request.queryParams("q"), "CREATED", request.queryParams("user"),new
                        SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
                return searchDAO.insertSearch(search);
            }
        });

        /*
        Spark.get("/searches/:username", new Route(){
            @Override
            public Object handle(Request request, Response response){
                String username = request.queryParams("username");

                JSONObject jsonObject = searchDAO.getSearch(username);
                return jsonObject.toString();
            }
        });*/

    }



}