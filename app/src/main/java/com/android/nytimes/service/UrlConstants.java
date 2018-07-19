package com.android.nytimes.service;

/**
 * Created by Nibedita on 11/02/2018.
 */

public class UrlConstants {

   private UrlConstants(){}

   public static final String BASE_URL = "http://api.nytimes.com";
   public static final String API_KEY = "328c8770deff4e44b77b935618667461";
   public static final String MOST_VIEWED_URL = "/svc/mostpopular/v2/mostviewed/all-sections/{period}.json?apikey=" + API_KEY;

}
