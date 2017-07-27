package com.dao;

import com.connection.ConnectionFactory;
import com.model.Search;
import java.sql.*;
import java.util.ArrayList;
import org.json.JSONObject;

import javax.xml.transform.Result;

/**
 * Created by Timur on 23.07.2017.
 */

public class SearchDAOImpl implements SearchDAO{

    public JSONObject getSearch(String username){
        Connection connection = ConnectionFactory.getConnection();
        try{
            java.sql.Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM searches WHERE User='"+username+"'");
            ArrayList<Search> list = new ArrayList<>();
            Search search = null;
            JSONObject jsonObject = new JSONObject();
            while(rs.next()){
                search = new Search();
                search.setId(rs.getInt("ID"));
                search.setStatus(rs.getString("Status"));
                search.setUser(rs.getString("User"));
                search.setCreated(rs.getString("Created"));
                search.setQuery(rs.getString("Query"));
                search.setResult(rs.getString("Result"));
                list.add(search);
            }
            jsonObject.put("Search Details for " + username, list);
            return jsonObject;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public JSONObject getAllSearches(){
        Connection connection = ConnectionFactory.getConnection();
        try{
            java.sql.Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM searches");
            ArrayList<Search> list = new ArrayList<Search>();
            Search search = null;
            JSONObject jsonObject = new JSONObject();
            while (rs.next()){
                search = new Search();
                search.setId(rs.getInt("ID"));
                search.setQuery(rs.getString("Query"));
                search.setStatus(rs.getString("Status"));
                search.setUser(rs.getString("User"));
                search.setCreated(rs.getString("Created"));
                search.setResult(rs.getString("Result"));
                list.add(search);
            }
            jsonObject.put("SearchDetails", list);
            return jsonObject;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public boolean insertSearch(Search search){
        Connection connection = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO searches VALUES(NULL, ?, ?, ?, ?,?)");
            ps.setString(1, search.getQuery());
            ps.setString(2, search.getStatus());
            ps.setString(3,search.getUser());
            ps.setString(4,search.getCreated());
            ps.setString(5,search.getResult());
            int i = ps.executeUpdate();
            if (i == 1){
                System.out.println("DA");
                return true;
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public String deleteSearch(String id){
        Connection connection = ConnectionFactory.getConnection();
        try{
            java.sql.Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM searches WHERE id="+id);
            return "Database was updated";
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public String changeSearchStatus(String oldQuery, String newQuery){
        Connection connection = ConnectionFactory.getConnection();
        try{
            java.sql.Statement stmt = connection.createStatement();
            stmt.executeUpdate("update searches set Status='"+newQuery+"'"+"where Query='"+oldQuery+"'");
            return "Search status was successfully updated and now is " + newQuery;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public boolean addResult(String q, StringBuffer result){
        Connection connection = ConnectionFactory.getConnection();
        String tmp = result.toString();
        try{
            java.sql.Statement stmt = connection.createStatement();
            stmt.executeUpdate("update searches set Result='"+tmp+"'where Query='"+q+"'");
            return true;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
}