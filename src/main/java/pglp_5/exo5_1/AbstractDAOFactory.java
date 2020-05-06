package pglp_5.exo5_1;

import java.io.IOException;
import java.sql.SQLException;
/**
 * 
 * @author Melissa
 *
 */

public abstract class AbstractDAOFactory {
   
    public enum DAOType { FILE, JDBC; }
   
    public abstract DAO<NumeroTel> getNumeroTelephoneDAO()
            throws IOException, SQLException;
    
    public abstract DAO<Personnel> getPersonnelDAO()
            throws IOException, SQLException;
    
    public abstract DAO<GroupePersonnel> getGroupePersonnelsDAO()
            throws IOException, SQLException;
   
    public static AbstractDAOFactory getFactory(final DAOType type) {
        if (type == DAOType.FILE) {
            return new DAOFactory();
        }
        if (type == DAOType.JDBC) {
            return new DAOFactoryJDBC();
        }
        return null;
    }

}
