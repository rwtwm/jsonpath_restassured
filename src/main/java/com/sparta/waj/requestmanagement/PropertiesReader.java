package com.sparta.waj.requestmanagement;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader
{
    private static final String FILE_LOC = "resources/climateurl.properties";
    private Properties properties;

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


    public String getEndpoint()
    {
        return properties.getProperty("base_url");
    }

    public String getStartYear()
    {
        String year = properties.getProperty("start_year");
        return year;
    }
}
