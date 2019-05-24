package com.sparta.waj.climatedata;

import io.restassured.path.json.JsonPath;

import java.util.List;

public class ModelTempData
{

    private int yearStart;
    private int yearEnd;
    private String modelName;
    private List<Double> tempMeans;

    public ModelTempData(String jsonString)
    {
        JsonPath path = JsonPath.from(jsonString);
        tempMeans = path.getList("monthVals");
        yearStart = path.getInt("fromYear");
        yearEnd = path.getInt("toYear");
        modelName = path.getString("gcm");
    }

    public double getJanMean()
    {
        return tempMeans.get(0);
    }

    public double getFebMean()
    {
        return tempMeans.get(1);
    }

    public double getMarMean()
    {
        return tempMeans.get(2);
    }

    public double getAprMean()
    {
        return tempMeans.get(3);
    }

    public double getMayMean()
    {
        return tempMeans.get(4);
    }

    public double getJunMean()
    {
        return tempMeans.get(5);
    }

    public double getJulMean()
    {
        return tempMeans.get(6);
    }

    public double getAugMean()
    {
        return tempMeans.get(7);
    }

    public double getSepMean()
    {
        return tempMeans.get(8);
    }

    public double getOctMean()
    {
        return tempMeans.get(9);
    }

    public double getNovMean()
    {
        return tempMeans.get(10);
    }

    public double getDecMean()
    {
        return tempMeans.get(11);
    }

}
