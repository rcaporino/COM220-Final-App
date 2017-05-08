package service_and_storage;

import java.io.Serializable;

public class User implements Serializable
{
    public static enum Gender {Male, Female}
    
    private static final double BASE_INTOX_LEVEL = 0;
    
    private double height;
    private double weight;
    private double intoxLevel;
    private Gender gender;
    private Meal meal;
    
    public User()
    {
        this.height = Double.NaN;
        this.weight = Double.NaN;
        this.intoxLevel = User.BASE_INTOX_LEVEL;
        this.gender = null;
        this.meal = null;
    }
    
    public User(double height, double weight, Gender gender)
    {
        this.height = height;
        this.weight = weight;
        this.intoxLevel = User.BASE_INTOX_LEVEL;
        this.gender = gender;
        this.meal = null;
    }
    
    public User(double height, double weight, Gender gender, Meal meal)
    {
        this.height = height;
        this.weight = weight;
        this.intoxLevel = User.BASE_INTOX_LEVEL;
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
    
    public double getIntoxLevel()
    {
        return this.intoxLevel;
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
    
    public void setIntoxLevel(double intoxLevel)
    {
        this.intoxLevel = intoxLevel;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }
    
    public void setMeal(Meal meal)
    {
        this.meal = meal;
    }

    @Override
    public String toString()
    {
        return "User{" + "height=" + height + ", weight=" + weight + 
                ", intoxLevel=" + intoxLevel + ", gender=" + gender + 
                ", meal=" + meal + '}';
    }
}