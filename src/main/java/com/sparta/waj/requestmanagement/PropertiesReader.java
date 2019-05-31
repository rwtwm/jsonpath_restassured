package com.sparta.waj.requestmanagement;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader
{
    private static final String FILE_LOC = "resources/climateurl.properties";
    private Properties properties;

    private int startYear;

    public PropertiesReader()
    {
        try
        {
            properties = new Properties();
            properties.load(new FileReader(FILE_LOC));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getEndpoint(boolean monthly)
    {
        String period = monthly ? "mavg" : "annualavg";
        String endYear = String.valueOf(startYear + 19);

        return properties.getProperty("base_url")
                + "/" + monthly
                + "/tas/"
                + getStartYear()
                + endYear
                + getCountryCode();
    }

    public String getStartYear()
    {
        String year = properties.getProperty("start_year");
        if (year.equals(""))
        {
            year = "1980";
        }
        startYear = Integer.parseInt(year);
        return year;
    }


    public String getCountryCode()
    {
        String country = properties.getProperty("country_code");

        if(country.equals(""))
        {
            return "gbr";
        }
        return country;
    }


}
