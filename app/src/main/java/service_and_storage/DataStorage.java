package service_and_storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataStorage
{
    private static final String FILE_NAME = "data.bin";
    
    public static void saveData(DataCollection dc)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(DataStorage.FILE_NAME);
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
                    new FileInputStream(DataStorage.FILE_NAME));
            dc = (DataCollection) ois.readObject();
        }
        catch (FileNotFoundException ex) {}
        catch (IOException | ClassNotFoundException ex) {}
        return dc;
    }
}