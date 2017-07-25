package com.dao;

import org.json.JSONObject;


/**
 * Created by Timur on 23.07.2017.
 */

public interface SearchDAO {
    JSONObject getSearch(String username);
    JSONObject getAllSearches();
}
