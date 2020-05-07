package pglp_5.exo5_1;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import pglp_5.exo5_1.AbstractDAOFactory.DAOType;
import pglp_5.exo5_1.Personnel.Builder;
/**
 * 
 * @author Melissa
 *
 */

	public enum App {
	    
	    ENVIRONNEMENT;
	   
	    public void run() throws IOException,
	    SQLException, ClassNotFoundException {
	        

	        NumeroTel portable =
	                new NumeroTel("portable", "068554710", 1);
	        Builder b = new Builder("bafdel", "melissa", "secrétaire",
	                LocalDate.of(1995, 10, 11), 1);
	        b.numTelephones(portable);
	        Personnel secretaire = b.build();
	       NumeroTel portable2 =
	                new NumeroTel("fixe", "021241020", 4);
	        Builder b2 = new Builder("eliot", "alderson", "chef de service",
	                LocalDate.of(1990, 3, 11), 4);
	        b2.numTelephones(portable2);
	        Personnel chefDeService = b2.build();

	        DAOJDBC<GroupePersonnel> grPersoJDBC =new DAOFactoryJDBC().getGroupePersonnelsDAO();
	        GroupePersonnel departement =new GroupePersonnel("Departement", 1);
	        GroupePersonnel service =new GroupePersonnel("Service", 3);
	       service.add(chefDeService);
	        departement.add(secretaire);
	        departement.add(service);

	        grPersoJDBC.create(departement);
	        grPersoJDBC.delete(service);
	        grPersoJDBC.create(service);
	        grPersoJDBC.find(3);

	        ((GroupePersonnelDAOJDBC) grPersoJDBC).affichageTableGroupePersonnels();
	        ((GroupePersonnelDAOJDBC) grPersoJDBC).affichageTableAppartenanceGroupe();
	        ((GroupePersonnelDAOJDBC) grPersoJDBC).affichageTableAppartenancePersonnel();

	        DAO<Personnel> personnelDAO = AbstractDAOFactory
	                .getFactory(DAOType.JDBC).getPersonnelDAO();
	       
	        personnelDAO.find(1);
	        personnelDAO = AbstractDAOFactory
	                .getFactory(DAOType.FILE).getPersonnelDAO();
	        personnelDAO.find(1);

	        DAOJDBC<Personnel> personnel = new DAOFactoryJDBC().getPersonnelDAO();
	        ((PersonnelDAOJDBC) personnel).affichageTablePersonnel();
	        ((PersonnelDAOJDBC) personnel).affichageTableCorrespondance();

	        DAOJDBC<NumeroTel> numeroTel = new DAOFactoryJDBC()
	                .getNumeroTelephoneDAO();
	        ((NumeroTelDAOJDBC) numeroTel).affichageTableNumero();
	    }
	  
	    public static void main(final String[] args) throws SQLException,
	    ClassNotFoundException, IOException {
	        ENVIRONNEMENT.run();
	    }
}
