package com.sparta.waj.codetesting;

import com.sparta.waj.climatedata.climatedataobjects.ClimateDataArray;
import com.sparta.waj.climatedata.climatedataobjects.ModelTempData;
import com.sparta.waj.requestmanagement.ClimateDataAccessor;
import com.sparta.waj.requestmanagement.PropertiesReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class IntegrationTests
{
    private static PropertiesReader reader;
    private static ClimateDataAccessor accessor;
    private static ClimateDataArray dataArray;

    @BeforeClass
    public static void setup()
    {
        reader = new PropertiesReader();
        accessor = new ClimateDataAccessor(reader.getEndpoint(true));
        dataArray = new ClimateDataArray(accessor.getClimateDataPath());
    }

    /**
     * retrieves the set of models from the api and compares it to the set of keys stored in the model.
     */
    @Test
    public void testGcmList()
    {
        Set<String> directGcmSet = new HashSet<>(accessor.getClimateDataPath().getList("gcm"));
        Set<String> modelGcmSet = dataArray.getClimateData().keySet();

        assertEquals(directGcmSet,modelGcmSet);
    }

    /**
     * Checks that the July value for any given year is higher than the january value.
     * Only works if the country code specified in the properties file is in the northern hemisphere.
     */
    @Test
    public void tempSenseCheck()
    {
        Map<String, ModelTempData> climateData = dataArray.getClimateData();

        

    }




}
