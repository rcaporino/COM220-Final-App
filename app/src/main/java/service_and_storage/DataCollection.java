package service_and_storage;

import java.io.Serializable;

import java.util.LinkedList;
import java.util.List;

public class DataCollection implements Serializable
{
    private User user;
    private List<Drink> drinksConsumed;
    private List<Drink> defaultDrinks;
    private List<Friend> cheersFriends;
    private List<Friend> emergencyContacts;
    
    public DataCollection()
    {
        this.user = null;
        this.drinksConsumed = new LinkedList<>();
        this.defaultDrinks = new LinkedList<>();
        this.cheersFriends = new LinkedList<>();
        this.emergencyContacts = new LinkedList<>();
    }
    
    public DataCollection(User user, List<Drink> drinkList,
            List<Drink> defaultDrinks)
    {
        this.user = user;
        this.drinksConsumed = drinkList;
        this.defaultDrinks = defaultDrinks;
        this.cheersFriends = new LinkedList<>();
        this.emergencyContacts = new LinkedList<>();
    }
    
    public DataCollection(User user, List<Drink> drinkList,
            List<Drink> defaultDrinks, List<Friend> cheersFriends,
            List<Friend> emergencyContacts)
    {
        this.user = user;
        this.drinksConsumed = drinkList;
        this.defaultDrinks = defaultDrinks;
        this.cheersFriends = cheersFriends;
        this.emergencyContacts = emergencyContacts;
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
    
    public List<Friend> getCheersFriends()
    {
        return this.cheersFriends;
    }
    
    public List<Friend> getEmergencyContacts()
    {
        return this.emergencyContacts;
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
    
    public void setCheersFriends(List<Friend> cheersFriends)
    {
        this.cheersFriends = cheersFriends;
    }
    
    public void setEmergencyContacts(List<Friend> emergencyContacts)
    {
        this.emergencyContacts = emergencyContacts;
    }
}