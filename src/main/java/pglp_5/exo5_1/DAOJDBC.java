package pglp_5.exo5_1;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAOJDBC<T> extends DAO<T> {
   
    private String dbUrl;
   
    private Connection connect;
    
    
    public DAOJDBC() throws SQLException {
        dbUrl = "jdbc:derby:BDD\\jdbcDB;create=true";

        try {
            setConnect(DriverManager.getConnection(dbUrl));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    @Override
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
   
    public Connection getConnect() {
        return connect;
    }
   
    public void setConnect(final Connection newCon) {
        this.connect = newCon;
    }
}