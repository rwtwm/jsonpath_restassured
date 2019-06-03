package com.sparta.waj.climatedata.climatedataobjects;

import io.restassured.path.json.JsonPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClimateDataArray
{
    private JsonPath arrayData;
    private Map<String,ModelTempData> climateData;
    private List<String> gcms;

    public ClimateDataArray(JsonPath arrayData)
    {
        this.arrayData = arrayData;
        climateData = new HashMap<>();
    }

    //This is not the most effective way of parsing the data. Instead this
    //approach has been selected to demonstrate the jsonPath syntax. The values
    // in the constructor would parse as e.g. "[0].fromYear".
    private void constructMap()
    {
        gcms = arrayData.getList("gcm");

        for(int i = 0; i < gcms.size(); i++)
        {
            String objectString = "[" + i + "]";
            ModelTempData nextRecord = new ModelTempData(
                    gcms.get(i),
                    arrayData.getInt(objectString + ".fromYear"),
                    arrayData.getInt(objectString + ".toYear"),
                    arrayData.getList(objectString + ".monthVals")
            );
            climateData.put(gcms.get(i),nextRecord);
        }
    }

    public Map<String, ModelTempData> getClimateData()
    {
        return climateData;
    }

    public ModelTempData getSingleModelData(int index)
    {
        String modelString = gcms.get(index);
        return getSingleModelData(modelString);
    }

    public ModelTempData getSingleModelData(String model)
    {
        return climateData.get(model);
    }
}
