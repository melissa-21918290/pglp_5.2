package pglp_5.exo5_1;

import java.io.IOException;
import java.sql.SQLException;

public final class DAOFactoryJDBC extends AbstractDAOFactory {

	public DAOFactoryJDBC(){
		
	}

	public DAOJDBC<NumeroTel> getNumeroTelDAO()throws IOException, SQLException  {
		return new NumeroTelDOAJDBC();
	}
	
	public DAOJDBC<Personnel>getPersonnelDAO() throws IOException, SQLException{
		return new PersonnelDAOJDBC();
	}

	public DAOJDBC<GroupePersonnel> getGroupePersonnelDAO() throws IOException, SQLException {
		return new GroupePersonnelJDBC();
	}

	
	//public DAOJBCD<NumeroTel>getNumeroTelDAO() throws IOException, SQLException{
		//return new NumeroTelDAOJDBC();
		
	}
	
	
	

