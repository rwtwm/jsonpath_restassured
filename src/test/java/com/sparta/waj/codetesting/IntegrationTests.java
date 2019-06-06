package com.sparta.waj.codetesting;

import com.sparta.waj.climatedata.climatedataobjects.ClimateDataArray;
import com.sparta.waj.climatedata.climatedataobjects.ModelTempData;
import com.sparta.waj.requestmanagement.ClimateDataAccessor;
import com.sparta.waj.requestmanagement.PropertiesReader;
import com.sun.tools.xjc.model.Model;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

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
     *
     * Uses iterator to avoid reference to keys in array.
     */
    @Test
    public void tempSenseCheck()
    {
        Map<String, ModelTempData> climateData = dataArray.getClimateData();
        Iterator<ModelTempData> dataIterator = climateData.values().iterator();

        ModelTempData testData = dataIterator.next();
        assertTrue(testData.getJunMean()>testData.getJanMean());
    }




}
