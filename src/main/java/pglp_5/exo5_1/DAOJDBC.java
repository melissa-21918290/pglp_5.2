package pglp_5.exo5_1;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  abstract class  DAOJDBC<T> extends DAO<T> {
	private String BDurl;
	private Connection conn;

	
	public DAOJDBC() throws SQLException{
		BDurl ="jdbc:derby:DATA\\ jdbcDB;create=true";
		try{
			SetConn(DriverManager.getConnection(BDurl));
		}catch (SQLException e){
		e.printStackTrace();
	}
}
	
@Override
public abstract T create (T obj) throws IOException, SQLException;
public abstract void delete (T obj) throws SQLException;
public abstract T update (T obj) throws IOException, SQLException;
public abstract T find (int id) throws FileNotFoundException, IOException, SQLException;
	

public Object deserialisation(final byte[] bytes) throws ClassNotFoundException, IOException{
	 ByteArrayInputStream a = new ByteArrayInputStream(bytes);
	 ObjectInputStream b = new ObjectInputStream(a);
	 return b.readObject();
}
public Connection getconn(){
	return conn;
}
public void SetConn(final Connection nouveauConn){
	this.conn= nouveauConn; 
}
}