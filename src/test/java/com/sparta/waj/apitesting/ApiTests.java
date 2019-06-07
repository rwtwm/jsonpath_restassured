package com.sparta.waj.apitesting;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

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

    /**
     * Note that restAssured parses decimal values as floats.
     */
    @Test
    public void testValueType()
    {
        when().
            get("mavg/tas/1980/1999/gbr")
        .then()
            .statusCode(200)
            .and()
            .body("[3].monthVals[6]",isA(float.class));
    }


    /**
     * Rudimentary performance testing. Note that the time taken requires a 'long'.
     * The argument specifying the TimeUnit is optional.
     */
    @Test
    public void testTimeTaken()
    {
        when()
            .get("$")
        .then()
            .time(lessThan(3L), TimeUnit.SECONDS);
    }


    /**
     * Demonstrates the 'peek()' method which can be helpful in understanding
     * the structure of the json record. Also shows the syntax for storing responses.
     * Use response.then() to obtain a ValidatableResponse
     */
    @Test
    public void testValueResponse()
    {
        Response response = get("annualavg/tas/1980/1999/gbr").then().extract().response();

        response.body().peek();
    }


    //Demonstration of status code check.  
    @Test
    public void malformedUrlCheck()
    {
        //given
        baseURI = "http://climatedataapi.worldbank.org/climateweb/rest/v1//";
        when()
            .get("manom/tas/1960/1979/DEU")
        .then()
            .statusCode(404);
    }












}
