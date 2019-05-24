package com.sparta.waj.climatedata;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.net.URI;

public class ClimateDataAccessor
{

    private static final String BASE_URL = "http://climatedataapi.worldbank.org/climateweb/rest/v1/country/";
    private static final String API_ENDPOINT = "mavg/tas/1980/1999/gbr";

    JsonPath climateDataPath;

    public ClimateDataAccessor()
    {
        String myString = BASE_URL+API_ENDPOINT;
        URI target =  URI.create(BASE_URL+API_ENDPOINT);

        Response response = RestAssured.get(target);
        climateDataPath = response.jsonPath();


    }

    public JsonPath getClimateDataPath()
    {
        return climateDataPath;
    }


}
