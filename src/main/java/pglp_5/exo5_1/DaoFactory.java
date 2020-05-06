package pglp_5.exo5_1;

import java.io.IOException;

public final class DAOFactory extends AbstractDAOFactory {
    
    public DAOFactory() {
      }
    
    public DAO<NumeroTel> getNumeroTelephoneDAO()
            throws IOException {
        return new NumeroTelDAO();
    }
   
    public DAO<Personnel> getPersonnelDAO() throws IOException {
        return new PersonnelDAO();
    }
    
    public DAO<GroupePersonnel> getGroupePersonnelsDAO()
            throws IOException {
        return new GroupePersonnelDAO();
    }
}
