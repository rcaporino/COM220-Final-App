package service_and_storage;

import java.io.FileInputStream;
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
                    Service.getInstance().getFilePath() +
                            DataStorage.DATA_FILE_NAME);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos))
            {
                oos.writeObject(dc);
            }
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
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
        catch (IOException | ClassNotFoundException ex) {}
        if(dc.getDefaultDrinks().isEmpty())
        {
            DataStorage.loadDefaultDrinks();
        }
        return dc;
    }
    
    private static List<Drink> loadDefaultDrinks()
    {
        List<Drink> list = new LinkedList<>();
        try
        {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(Service.getInstance().getFilePath() + 
                            DataStorage.DRINK_FILE_NAME));
            list = (List<Drink>) ois.readObject();
        }
        catch (IOException | ClassNotFoundException ex) {}
        return list;
    }
}