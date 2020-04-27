package pglp_5.exo5_1;

public abstract class AbstractDAOFactory {
	
	public enum DAOType{JBDC,FILE}
	
	public abstract DAO<NumeroTel> getNuleroTelDAO(); 

}
