package service_and_storage;

import java.io.File;
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
    private static final int FAV_DRINK_END_INDEX = 5;
    
    private static Service service;
    private static String filePath;
    
    private final DataCollection dc;
    
    private Service(DataCollection dc)
    {
        this.dc = dc;
    }
    
    public static Service getInstance()
    {
        if(Service.service == null)
        {
            Service.service = new Service(DataStorage.loadData());
        }
        return Service.service;
    }
    
    public boolean userExists()
    {
        return this.dc.getUser().getHeight() != Double.NaN &&
               this.dc.getUser().getWeight() != Double.NaN &&
               this.dc.getUser().getGender() == null;
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
        saveData();
    }
        
    public void createUserWithMeal(double height, double weight,
            Gender gender, Meal meal)
    {
        this.dc.setUser(new User(height, weight, gender, meal));
        saveData();
    }
    
    public void setUserHeight(double height)
    {
        this.dc.getUser().setHeight(height);
        saveData();
    }
    
    public void setUserWeight(double weight)
    {
        this.dc.getUser().setWeight(weight);
        saveData();
    }
    
    public void setUserIntoxLevel(double intoxLevel)
    {
        this.dc.getUser().setIntoxLevel(intoxLevel);
        saveData();
    }
        
    public void setUserGender(Gender gender)
    {
        this.dc.getUser().setGender(gender);
        saveData();
    }
        
    public void setUserMeal(MealType mealType, long timeEaten)
    {
        this.dc.getUser().setMeal(new Meal(mealType, timeEaten));
        saveData();
    }
    
    public void setUserMealType(MealType mealType)
    {
        this.dc.getUser().getMeal().setMealType(mealType);
        saveData();
    }
    
    public void setUserMealTimeEaten(long timeEaten)
    {
        this.dc.getUser().getMeal().setTimeEaten(timeEaten);
        saveData();
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
        saveData();
    }
    
    public List<Drink> getDefaultDrinks()
    {
        return this.dc.getDefaultDrinks();
    }
        
    public void addDefaultDrink(String name, double sizeInOz, double proof)
    {
        this.dc.getDefaultDrinks().add(new Drink(name, sizeInOz, proof));
        saveData();
    }
    
    public List<Friend> getCheersFriends()
    {
        return this.dc.getCheersFriends();
    }
    
    public void addCheersFriend(Friend friend)
    {
        this.dc.getCheersFriends().add(friend);
        saveData();
    }
    
    public List<Friend> getEmergencyContacts()
    {
        return this.dc.getEmergencyContacts();
    }
    
    public void addEmergencyContact(Friend friend)
    {
        this.dc.getEmergencyContacts().add(friend);
        saveData();
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
                Math.min(result.size(), FAV_DRINK_END_INDEX));
    }
    
    public File getImageFile()
    {
        return this.dc.getImageFile();
    }
    
    public void setImageFile(File file)
    {
        this.dc.setImageFile(file);
        saveData();
    }
    
    public String getEmergencyMsg()
    {
        return this.dc.getEmergencyMsg();
    }
    
    public void setEmergencyMsg(File file)
    {
        this.dc.setImageFile(file);
        saveData();
    }
    
    public String getFilePath()
    {
        return Service.filePath;
    }
    
    public void setFilePath(String filePath)
    {
        Service.filePath = filePath;
        saveData();
    }
    
    private void saveData()
    {
        DataStorage.saveData(this.dc);
    }

    public static void setFilePath(File path)
    {
        DataStorage.setParentDir(path);
    }
}