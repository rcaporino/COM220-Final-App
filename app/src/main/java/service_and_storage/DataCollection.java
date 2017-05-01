package service_and_storage;

import java.io.Serializable;

import java.util.LinkedList;
import java.util.List;

public class DataCollection implements Serializable
{
    private User user;
    private List<Drink> drinksConsumed;
    private List<Drink> defaultDrinks;
    
    public DataCollection()
    {
        this.user = null;
        this.drinksConsumed = new LinkedList<>();
        this.defaultDrinks = new LinkedList<>();
    }
    
    public DataCollection(User user, List<Drink> drinkList,
            List<Drink> defaultDrinks)
    {
        this.user = user;
        this.drinksConsumed = drinkList;
        this.defaultDrinks = defaultDrinks;
    }

    public User getUser()
    {
        return this.user;
    }

    public List<Drink> getDrinksConsumed()
    {
        return this.drinksConsumed;
    }
    
    public List<Drink> getDefaultDrinks()
    {
        return this.defaultDrinks;
    }
    
    public void setUser(User user)
    {
        this.user = user;
    }
    
    public void setDrinksConsumed(List<Drink> drinksConsumed)
    {
        this.drinksConsumed = drinksConsumed;
    }
    
    public void setDefaultDrinks(List<Drink> defaultDrinks)
    {
        this.defaultDrinks = defaultDrinks;
    }
}