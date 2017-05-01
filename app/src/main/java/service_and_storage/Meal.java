package service_and_storage;

import java.io.Serializable;

public class Meal implements Serializable
{
    public static enum MealType{Small, Medium, Large}
    
    private static final long BASE_TIME_EATEN = 0;
    
    private MealType mealType;
    private long timeEaten;
    
    public Meal()
    {
        this.mealType = null;
        this.timeEaten = Meal.BASE_TIME_EATEN;
    }
    
    public Meal(MealType mealType, long timeEaten)
    {
        this.mealType = mealType;
        this.timeEaten = timeEaten;
    }

    public MealType getMealType()
    {
        return this.mealType;
    }

    public long getTimeEaten()
    {
        return this.timeEaten;
    }

    public void setMealType(MealType mealType)
    {
        this.mealType = mealType;
    }

    public void setTimeEaten(long timeEaten)
    {
        this.timeEaten = timeEaten;
    }
}
