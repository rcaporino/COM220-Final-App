package service_and_storage;

import java.io.File;
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
    private File imageFile;
    private String emergencyMsg;
    
    public DataCollection()
    {
        this.user = new User();
        this.drinksConsumed = new LinkedList<>();
        this.defaultDrinks = new LinkedList<>();
        this.cheersFriends = new LinkedList<>();
        this.emergencyContacts = new LinkedList<>();
        this.imageFile = null;
        this.emergencyMsg = null;
    }
    
    public DataCollection(User user, List<Drink> drinkList,
            List<Drink> defaultDrinks)
    {
        this.user = user;
        this.drinksConsumed = drinkList;
        this.defaultDrinks = defaultDrinks;
        this.cheersFriends = new LinkedList<>();
        this.emergencyContacts = new LinkedList<>();
        this.imageFile = null;
        this.emergencyMsg = null;
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
        this.imageFile = null;
        this.emergencyMsg = null;
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
    
    public File getImageFile()
    {
        return this.imageFile;
    }
    
    public String getEmergencyMsg()
    {
        return this.emergencyMsg;
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
    
    public void setImageFile(File file)
    {
        this.imageFile = file;
    }
    
    public void setEmergencyMsg(String emergencyMsg)
    {
        this.emergencyMsg = emergencyMsg;
    }
}