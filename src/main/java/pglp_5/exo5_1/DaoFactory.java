package pglp_5.exo5_1;

import java.io.IOException;

public class DaoFactory {
	
	//recuperer le numero de telephone
	public static DAOApp<NumeroTel> getNumeroTelDAO() throws IOException {
		return getNumeroTelDAO();
		}
		
		//recuperer de DAO personnel
		public static DAOApp<Personnel> getPersonnelDAO(){ 
			return  getPersonnelDAO();
		}
	
	//recuperer le DAO de GroupePersonnel
		 public static DAOApp<GroupePersonnel> getGroupePersonnels() {
		        return getGroupePersonnels();
		        
		    }
}