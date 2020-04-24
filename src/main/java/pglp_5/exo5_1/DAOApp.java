package pglp_5.exo5_1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class DAOApp<T> {
	protected File f;
    protected FileOutputStream fileOut;
    protected ObjectOutputStream objOut;
    
  //creer le constructeur de DAO
    public DAOApp(int id) throws IOException{
    	f = new File(id +".txt");
    	fileOut = new FileOutputStream(f);
    	objOut = new ObjectOutputStream(fileOut);
    }
    
    //creation
    public abstract T create (T obj) throws IOException;
    
    //effacer
    public abstract void delete(T obj);
    
    //mise a jour
    public abstract T update(T obj) throws IOException;
    
    //recherche de l'information
    public abstract T find(String string) throws FileNotFoundException, ClassNotFoundException, IOException;
    
    //déserialisation
    public Object deserialize(final byte[] bytes) throws ClassNotFoundException, IOException {
        ByteArrayInputStream a = new ByteArrayInputStream(bytes);
        ObjectInputStream b = new ObjectInputStream(a);
        return b.readObject();
    }
    
    //sérialisation
    public byte[] serialize(final Object obj) throws IOException {
        ByteArrayOutputStream a = new ByteArrayOutputStream();
        ObjectOutputStream b = new ObjectOutputStream(a);
        b.writeObject(obj);
        return a.toByteArray();
    }
    
}

