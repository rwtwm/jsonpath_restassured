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

    /**
     * Reviews the properties file and constructs a URL based on the arguments present.
     * The switch between monthly and annual values is made in the method call.
     * @param monthly - boolean. Is the data monthly or annual average
     * @return - String to be converted into URI for data access.
     */
    public String getEndpoint(boolean monthly)
    {
        String period = monthly ? "mavg" : "annualavg";
        String startYear = getStartYear();
        String endYear = String.valueOf(this.startYear + 19);

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
