package service_and_storage;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.stream.Collectors;

import service_and_storage.Meal.MealType;
import service_and_storage.User.Gender;

public class Service
{
    private static final int NUM_OF_FAV_DRINKS = 5;
    
    private DataCollection dc;
    
    public Service()
    {
        this.dc = null;
    }
    public Service(DataCollection dc)
    {
        this.dc = dc;
    }

    public DataCollection getDataCollection()
    {
        return this.dc;
    }

    public void setDataCollection(DataCollection dc)
    {
        this.dc = dc;
    }
    
    public User getUser()
    {
        return this.dc.getUser();
    }
    
    public double getUserHeight()
    {
        return this.dc.getUser().getHeight();
    }
    
    public double getUserWeight()
    {
        return this.dc.getUser().getWeight();
    }
        
    public double getUserIntoxLevel()
    {
        return this.dc.getUser().getIntoxLevel();
    }
    
    public Gender getUserGender()
    {
        return this.dc.getUser().getGender();
    }
    
    public Meal getUserMeal()
    {
        return this.dc.getUser().getMeal();
    }
    
    public MealType getUserMealType()
    {
        return this.dc.getUser().getMeal().getMealType();
    }
    
    public long getUserMealTimeEaten()
    {
        return this.dc.getUser().getMeal().getTimeEaten();
    }
    
    public void createUser(double height, double weight, Gender gender)
    {
        this.dc.setUser(new User(height, weight, gender));
    }
        
    public void createUserWithMeal(double height, double weight,
            Gender gender, Meal meal)
    {
        this.dc.setUser(new User(height, weight, gender, meal));
    }
    
    public void setUserHeight(double height)
    {
        this.dc.getUser().setHeight(height);
    }
    
    public void setUserWeight(double weight)
    {
        this.dc.getUser().setWeight(weight);
    }
    
    public void setUserIntoxLevel(double intoxLevel)
    {
        this.dc.getUser().setIntoxLevel(intoxLevel);
    }
        
    public void setUserGender(Gender gender)
    {
        this.dc.getUser().setGender(gender);
    }
        
    public void setUserMeal(MealType mealType, long timeEaten)
    {
        this.dc.getUser().setMeal(new Meal(mealType, timeEaten));
    }
    
    public void setUserMealType(MealType mealType)
    {
        this.dc.getUser().getMeal().setMealType(mealType);
    }
    
    public void setUserMealTimeEaten(long timeEaten)
    {
        this.dc.getUser().getMeal().setTimeEaten(timeEaten);
    }
    
    public List<Drink> getDrinksConsumed()
    {
        return this.dc.getDrinksConsumed();
    }
        
    public void addConsumedDrink(String name, double sizeInOz,
            double proof, int timeDrank)
    {
        this.dc.getDrinksConsumed().add(new Drink(name, sizeInOz,
                proof, timeDrank));
    }
    
    public List<Drink> getDefaultDrinks()
    {
        return this.dc.getDefaultDrinks();
    }
        
    public void addDefaultDrink(String name, double sizeInOz, double proof)
    {
        this.dc.getDefaultDrinks().add(new Drink(name, sizeInOz, proof));
    }
    
    /*public List<Drink> getFavDrinks()
    {
        Map<Drink, Integer> map = new HashMap<>();

        this.dc.getDrinksConsumed().forEach((drink) ->
        {
            Integer count = map.get(drink);
            map.put(drink, (count == null) ? 1 : count + 1);
        });
        
        return map.entrySet().stream().sorted(Collections.reverseOrder(
                Map.Entry.comparingByValue())).limit(Service.NUM_OF_FAV_DRINKS).
                map(Map.Entry::getKey).collect(Collectors.toList());

    }*/
    
    public void saveData()
    {
        DataStorage.saveData(this.dc);
    }
    
    public void loadData()
    {
        this.dc = DataStorage.loadData();
    }
}