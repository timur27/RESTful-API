package com.dao;

import com.connection.ConnectionFactory;
import com.model.Search;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.Set;


/**
 * Created by Timur on 23.07.2017.
 */
public class SearchDAOImpl implements SearchDAO{
    private Search extractSearchFromResultset(ResultSet rs) throws SQLException{
        Search search = new Search();
        search.setId(rs.getInt("id"));
        search.setQuery(rs.getString("query"));
        search.setUser(rs.getString("user"));

        return search;
    }

    public JSONObject getAllSearches(){
        Connection connection = ConnectionFactory.getConnection();
        try{
            java.sql.Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM searches");
            //Set searches = new HashSet();
            ArrayList<Search> list = new ArrayList<Search>();
            Search search = null;
            JSONObject jsonObject = new JSONObject();
            while (rs.next()){
                search = new Search();
                search.setId(rs.getInt("ID"));
                search.setQuery(rs.getString("Query"));
                search.setUser(rs.getString("User"));
                list.add(search);
                /*Search search = extractSearchFromResultset(rs);
                searches.add(search);
                */
            }
            //return searches;
            jsonObject.put("SearchDetails", list);
            return jsonObject;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
