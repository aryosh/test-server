/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aryosh.var;


/**
 *
 * @author AryoSH XLINK
 */
public class DriverLocation
{
    private int DriverId;
    private double DriverLat;
    private double DriverLon;
    private int DriverRadius;

    public int getDriverId()
    {
        return DriverId;
    }

    public void setDriverId(int DriverId)
    {
        this.DriverId = DriverId;
    }

    public double getDriverLat()
    {
        return DriverLat;
    }

    public void setDriverLat(double DriverLat)
    {
        this.DriverLat = DriverLat;
    }

    public double getDriverLon()
    {
        return DriverLon;
    }

    public void setDriverLon(double DriverLon)
    {
        this.DriverLon = DriverLon;
    }

    public int getDriverRadius()
    {
        return DriverRadius;
    }

    public void setDriverRadius(int DriverRadius)
    {
        this.DriverRadius = DriverRadius;
    }

    public DriverLocation(int DriverId, double DriverLat, double DriverLon, int DriverRadius)
    {
        this.DriverId = DriverId;
        this.DriverLat = DriverLat;
        this.DriverLon = DriverLon;
        this.DriverRadius = DriverRadius;
    }
    
    
}
