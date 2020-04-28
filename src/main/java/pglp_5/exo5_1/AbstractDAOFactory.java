package pglp_5.exo5_1;

import java.io.IOException;
import java.sql.SQLException;

public abstract class AbstractDAOFactory {
	
	public enum DAOType{JBDC,FILE}
	
	public abstract DAO<NumeroTel> getNuleroTelDAO() throws IOException, SQLException;
	public abstract DAO <Personnel> getPersonnelDAO()throws IOException, SQLException;
	public abstract DAO<GroupePersonnel> getGroupePersonnelDAO() throws IOException, SQLException;
}