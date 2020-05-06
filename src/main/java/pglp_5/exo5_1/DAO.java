package pglp_5.exo5_1;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;

public abstract class DAO<T> {
   
    public abstract T create(T obj) throws IOException, SQLException;
   
    public abstract void delete(T obj) throws SQLException;
    
    public abstract T update(T obj) throws IOException, SQLException;
   
    public abstract T find(int id) throws FileNotFoundException,
    ClassNotFoundException, IOException, SQLException;
   
    public Object deserialize(final byte[] bytes) throws ClassNotFoundException,
    IOException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readObject();
    }
}