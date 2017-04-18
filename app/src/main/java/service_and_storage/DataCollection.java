package service_and_storage;

import java.io.Serializable;

import java.util.LinkedList;
import java.util.List;

public class DataCollection implements Serializable
{
    private User user;
    private List drinksConsumed;
    private List defaultDrinks;
    
    public DataCollection()
    {
        this.user = null;
        this.drinksConsumed = new LinkedList<>();
    }
    
    public DataCollection(User user, List drinkList)
    {
        this.user = user;
        this.drinksConsumed = drinkList;
    }

    public User getUser()
    {
        return this.user;
    }

    public List getDrinksConsumed()
    {
        return this.drinksConsumed;
    }
    
    public List getDefaultDrinks()
    {
        return this.defaultDrinks;
    }
    
    public void setUser(User user)
    {
        this.user = user;
    }
    
    public void setDrinksConsumed(List drinksConsumed)
    {
        this.drinksConsumed = drinksConsumed;
    }
    
    public void setDefaultDrinks(List defaultDrinks)
    {
        this.defaultDrinks = defaultDrinks;
    }
}
