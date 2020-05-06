package pglp_5.exo5_1;

import java.io.IOException;
import java.sql.SQLException;

public class DAOFactoryJDBC extends AbstractDAOFactory {
   
    public DAOFactoryJDBC() {
    }
   
    public DAOJDBC<NumeroTel> getNumeroTelephoneDAO()
            throws IOException, SQLException {
        return new NumeroTelDAOJDBC();
    }
    
    public DAOJDBC<Personnel> getPersonnelDAO()
            throws IOException, SQLException {
        return new PersonnelDAOJDBC();
    }
    
    public DAOJDBC<GroupePersonnel> getGroupePersonnelsDAO()
            throws IOException, SQLException {
        return new GroupePersonnelDAOJDBC();
    }
}