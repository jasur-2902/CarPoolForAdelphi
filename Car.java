
/**
 * Write a description of class Car here.
 *
 * @author (your license)
 * @version (a version number or a date)
 */
public class Car
{
    private String license, model, color;
    public Car()
    {
        this.license=null;
        this.model=null;
        this.color=null;
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
