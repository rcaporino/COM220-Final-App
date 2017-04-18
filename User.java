package service_and_storage;

public class User
{
    public static enum Gender {Male, Female}
    
    private double height;
    private double weight;
    private Gender gender;
    private Meal meal;
    
    public User()
    {
        this.height = Double.NaN;
        this.weight = Double.NaN;
        this.gender = null;
        this.meal = null;
    }
    
    public User(double height, double weight, Gender gender, Meal meal)
    {
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.meal = meal;
    }

    public double getHeight()
    {
        return this.height;
    }

    public double getWeight()
    {
        return this.weight;
    }

    public Gender getGender()
    {
        return this.gender;
    }
    
    public Meal getMeal()
    {
        return this.meal;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }
    
    public void setMeal(Meal meal)
    {
        this.meal = meal;
    }
}
