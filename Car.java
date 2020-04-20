package CsProject;

import java.io.Serializable;

/**
 * @authors Jasur Shukurov && Matt Vang
 * Car object class
 * 
 * @version 1.0 04/09/2020
 */

public class Car implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String license, model, color;
    public Car()
    {
        this.license = "AA28019";
        this.model = "BMW M5";
        this.color = "Dark Blue";
    }
    
    public Car(String license, String model, String color)
    {
        this.license=license;
        this.model=model;
        this.color = color;
    }
    
    public void setLicense(String license)
    {
        this.license = license;
    }
    
    public void setModel(String model)
    {
        this.model = model;
    }
    
    public void setColor(String color)
    {
        this.color = color;
    }
    
    public String getlicense()
    {
        return license;
    }
    
    public String getModel()
    {
        return model;
    }
    
    public String getColor()
    {
        return color;
    }
    
    public String toString()
    {
        return "License: "+license +"\nCar model: "+model+"\nColor: "+color;
    }
}
