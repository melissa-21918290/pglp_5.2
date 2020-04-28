package pglp_5.exo5_1;

import java.io.IOException;
import java.sql.SQLException;

public abstract class AbstractDAOFactory {
	
	public enum DAOType{JDBC,FILE}
	
	public abstract DAO<NumeroTel> getNumeroTelDAO() throws IOException, SQLException;
	public abstract DAO <Personnel> getPersonnelDAO()throws IOException, SQLException;
	public abstract DAO<GroupePersonnel> getGroupePersonnelDAO() throws IOException, SQLException;
	public static AbstractDAOFactory getFactory (final DAOType type){
		if (type ==DAOType.FILE){
			return new DAOFactory();
		}
		if (type == DAOType.JDBC){
			return new DAOFactoryJDBC();
		}
		return null;
	}

	
	
}