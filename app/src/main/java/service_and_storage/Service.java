package service_and_storage;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import service_and_storage.Meal.MealType;
import service_and_storage.User.Gender;

public class Service
{
    private static final int FAV_DRINK_START_INDEX = 0;
    private static final int FAV_DRINK_END_INDEX = 4;
    
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
            double proof, long timeDrank)
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
    
    public List<Friend> getCheersFriends()
    {
        return this.dc.getCheersFriends();
    }
    
    public void addCheersFriend(Friend friend)
    {
        this.dc.getCheersFriends().add(friend);
    }
    
    public List<Friend> getEmergencyContacts()
    {
        return this.dc.getEmergencyContacts();
    }
    
    public void addEmergencyContact(Friend friend)
    {
        this.dc.getEmergencyContacts().add(friend);
    }
    
    public List<Drink> getFavDrinks()
    {
        Map<Drink, Integer> map = new HashMap<>();
        
        for(Drink drink : this.dc.getDrinksConsumed())
        {
            Integer count = map.get(drink);
            map.put(drink, (count == null) ? 1 : count + 1);
        }
        
        List<Map.Entry<Drink, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Drink, Integer>>()
        {

            @Override
            public int compare(Map.Entry<Drink, Integer> o1,
                    Map.Entry<Drink, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        
        List<Drink> result = new LinkedList<>();
        
        for(Map.Entry<Drink, Integer> entry : list)
        {
            result.add(entry.getKey());
        }
        
        return result.subList(Service.FAV_DRINK_START_INDEX,
                Service.FAV_DRINK_END_INDEX);
    }
    
    public void saveData()
    {
        DataStorage.saveData(this.dc);
    }
    
    public void loadData()
    {
        this.dc = DataStorage.loadData();
    }
    
    public void saveDefaultDrinks()
    {
        DataStorage.saveDefaultDrinks(this.dc.getDefaultDrinks());
    }
    
    public void loadDefaultDrinks()
    {
        this.dc.setDefaultDrinks(DataStorage.loadDefaultDrinks());
    }
}