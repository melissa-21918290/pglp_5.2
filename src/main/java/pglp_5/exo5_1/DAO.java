package pglp_5.exo5_1;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;

public abstract class DAO<T> {

	public abstract T create (T obj) throws IOException, SQLException, IOException;

	public abstract void delete(T obj) throws SQLException;
	
	public abstract T update(T obj) throws IOException, SQLException; 
	
	public abstract T find(int id) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException; 

	public Object deserialisable(final byte[] bytes) throws ClassNotFoundException, IOException {
		ByteArrayInputStream a = new ByteArrayInputStream(bytes);
		ObjectInputStream b = new ObjectInputStream(a);
		return b.readObject();
	}
}

