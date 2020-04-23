 

/**
 * Write a description of class Profile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Profile 
{
    private String name;
    private String studentID;
    private String email;
    
    public Profile()
    {
        this.name="John Smith";
        this.studentID="0000000";
        this.email="johnsmith@gmail.com";
    }
    
    public Profile(String name, String ID, String email)
    {
        this.name = name;
        this.studentID = ID;
        this.email = email;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setID(String ID)
    {
        this.studentID = ID;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getID()
    {
        return studentID;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public String toString()
    {
        return "Name: "+name +"\nStudent ID: "+studentID+"\nEmail: "+email;
    }
}
