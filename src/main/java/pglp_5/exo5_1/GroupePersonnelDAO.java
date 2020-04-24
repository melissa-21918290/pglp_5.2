package pglp_5.exo5_1;

import java.io.FileNotFoundException;
import java.io.IOException;



public class GroupePersonnelDAO extends DAOApp<GroupePersonnel> {
	

	public GroupePersonnelDAO(int id) throws IOException {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public GroupePersonnel create(GroupePersonnel obj){	
	GroupePersonnel groupe = null;
		return groupe;
}
	
	//effacer
	  public void delete(GroupePersonnel obj) {
	        
	    }
	
	  //mise a jour
	  public GroupePersonnel update(GroupePersonnel obj) { 
	        GroupePersonnel groupe= null;
	        return groupe;
	    }
	  //recherche
	  public GroupePersonnel find(String string) {
	        GroupePersonnel groupe = null;
	        return groupe;
	    }

	
}
