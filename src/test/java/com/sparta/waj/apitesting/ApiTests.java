package com.sparta.waj.apitesting;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTests
{
    private static final String BASE_URL = "http://climatedataapi.worldbank.org/climateweb/rest/v1/country";

    @BeforeClass
    public static void setup()
    {
        baseURI = BASE_URL;
    }

    /**
     * Checks that 12 temperature values are returned. Note the use of the 'size()' method
     * within the body string.
     */
    @Test
    public void testTwelveMonths()
    {
        when().
            get("mavg/tas/1960/1979/DEU")
        .then()
            .statusCode(200)
        .and()
            .body("[0].monthVals.size()",is(12));
    }

    /**
     * If an invalid parameter combination is given, an empty json object is returned
     * rather than an error code. The 'empty()' Hamcrest matcher is used to confirm this.  
     */
    @Test
    public void testAnomalyPastFail()
    {
        when().
            get("manom/tas/1960/1979/DEU")
        .then()
            .body("$",empty());
    }








}
