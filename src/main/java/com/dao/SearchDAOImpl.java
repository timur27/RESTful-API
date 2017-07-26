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
            PreparedStatement ps = connection.prepareStatement("INSERT INTO searches VALUES(NULL, ?, ?, ?, ?)");
            ps.setString(1, search.getQuery());
            ps.setString(2, search.getStatus());
            ps.setString(3,search.getUser());
            ps.setString(4,search.getCreated());
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
}