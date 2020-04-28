package pglp_5.exo5_1;

import java.io.IOException;
import java.sql.SQLException;

public final class DAOFactory extends AbstractDAOFactory {
	public DAOFactory(){
	
	}


	public DAO<NumeroTel> getNumeroTelDAO() throws IOException {
		// TODO Auto-generated method stub
		return new NumeroTelDAO();
	}

	@Override
	public DAO<Personnel> getPersonnelDAO() throws IOException, SQLException {
		// TODO Auto-generated method stub
		return new PersonnelDAO();
	}

	@Override
	public DAO<GroupePersonnel> getGroupePersonnelDAO() throws IOException, SQLException {
		// TODO Auto-generated method stub
		return new GroupePersonnelDAO();
	}
	
	
	
}