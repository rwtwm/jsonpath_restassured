package com.sparta.waj.requestmanagement;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.net.URI;

public class ClimateDataAccessor
{

    JsonPath climateDataPath;

    public ClimateDataAccessor(String endpoint)
    {
        URI target =  URI.create(endpoint);

        Response response = RestAssured.get(target);
        climateDataPath = response.jsonPath();
    }

    public JsonPath getClimateDataPath()
    {
        return climateDataPath;
    }


}
