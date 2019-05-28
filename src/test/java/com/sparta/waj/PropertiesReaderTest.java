package com.sparta.waj;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PropertiesReaderTest
{
    private static final String FILE_LOC = "resources/climateurl.properties";

    @Test
    public void testYearDefault() throws IOException
    {
        editProperty("start_year","1980");
    }

    /**
     * @param property - The property to edit
     * @param value - The value to assign to the property
     * @throws IOException
     *
     * Enables test editing of climateurl.properties to help ensure that defensive coding works
     * and default values are used correctly.
     */
    private void editProperty(String property, String value) throws IOException
    {
        List<String> propertiesText = captureProperties();

        for (int i = 0; i < propertiesText.size(); i++)
        {
            if(propertiesText.get(i).matches(property + ".*"))
            {
                propertiesText.set(i, property + "=" + value);
            }
        }

        writeProperties(propertiesText);
    }



    private void writeProperties(List<String> properties) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_LOC));

        for(String property: properties)
        {
            writer.write(property);
            writer.newLine();
        }

        writer.close();

    }

    private List<String> captureProperties() throws IOException
    {
        List<String> propertiesText = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_LOC));

        String property;

        while ((property = reader.readLine()) != null)
        {
            propertiesText.add(property);
        }

        reader.close();

        return propertiesText;
    }
}
