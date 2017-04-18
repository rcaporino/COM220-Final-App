package service_and_storage;

import java.util.List;

import service_and_storage.User.Gender;

public class Service
{
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
    
    public void createUser(double height, double weight,
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
    
    public List getFavDrinks()
    {
        List favDrinks = this.dc.getDrinksConsumed();
        favDrinks.sort(null);
        return favDrinks.subList(0, 2);
    }
    
    public void addDrink(String name, double oz, double proof, int timeDrank)
    {
        this.dc.getDrinksConsumed().add(new Drink(name, oz, proof, timeDrank));
    }
}