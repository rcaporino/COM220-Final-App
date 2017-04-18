package service_and_storage;

public class Meal
{
    public static enum MealType{Small, Medium, Large}
    
    private MealType mealType;
    private long timeEaten;
    
    public Meal()
    {
        this.mealType = null;
        this.timeEaten = 0;
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
