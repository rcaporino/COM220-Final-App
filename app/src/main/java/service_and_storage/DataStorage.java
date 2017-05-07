package service_and_storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.LinkedList;
import java.util.List;

public class DataStorage
{
    private static final String DATA_FILE_NAME = "data.bin";
    private static final String DRINK_FILE_NAME = "drinks.bin";
    
    public static void saveData(DataCollection dc)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(
                    DataStorage.DATA_FILE_NAME);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos))
            {
                oos.writeObject(dc);
            }
        }
        catch (FileNotFoundException ex) {}
        catch (IOException ex) {}
    }
    
    public static DataCollection loadData()
    {
        DataCollection dc = new DataCollection();
        try
        {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(DataStorage.DATA_FILE_NAME));
            dc = (DataCollection) ois.readObject();
        }
        catch (FileNotFoundException ex) {}
        catch (IOException | ClassNotFoundException ex) {}
        return dc;
    }
    
    public static void saveDefaultDrinks(List<Drink> defaultDrinks)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(
                    DataStorage.DRINK_FILE_NAME);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos))
            {
                oos.writeObject(defaultDrinks);
            }
        }
        catch (FileNotFoundException ex) {}
        catch (IOException ex) {}
    }
    
    public static List<Drink> loadDefaultDrinks()
    {
        List<Drink> list = new LinkedList<>();
        try
        {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(DataStorage.DRINK_FILE_NAME));
            list = (List<Drink>) ois.readObject();
        }
        catch (FileNotFoundException ex) {}
        catch (IOException | ClassNotFoundException ex) {}
        return list;
    }
}