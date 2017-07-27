package com.dao;

import com.model.Search;
import org.json.JSONObject;


/**
 * Created by Timur on 23.07.2017.
 */

public interface SearchDAO {

    JSONObject getSearch(String username);
    JSONObject getAllSearches();
    String deleteSearch(String id);
    boolean insertSearch(Search search);
    String changeSearchStatus(String oldQuery, String newQuery);
    boolean addResult(String q, StringBuffer result);

}
