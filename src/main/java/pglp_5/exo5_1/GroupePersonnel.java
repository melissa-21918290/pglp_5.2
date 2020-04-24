package pglp_5.exo5_1;

import java.util.ArrayList;
import java.util.List;


import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
public class GroupePersonnel implements Hierarchie, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Hierarchie> personne = new ArrayList<Hierarchie>();
	
	//creer un groupe personnels
	private String nomDuGroupe;
	public GroupePersonnel( String nom){
		this.nomDuGroupe = nom;
	}
	
	
	//recuperer le nom du groupe
	
	 public String getName(){
		 return this.nomDuGroupe;
	 }
	 //afficher les composants
	  
	public void print() {
		System.out.println(this.getName());
	
		for(Hierarchie composant: personne) {
			composant.print();
		}
	}
	
	public boolean contains(Hierarchie composant) {
        return personne.contains(composant);
    }

	
	//retourne la liste des composants
	public List<Hierarchie> getList(){
		return (List<Hierarchie>) Collections.unmodifiableCollection(personne) ;
		
		
	}
	//ajout de composant au groupe
	public void add(Hierarchie composant) {
		personne.add(composant);
	}
	
	//retirer un composanr du groupe
	public void remove(Hierarchie composant) {
		personne.remove(composant);
	}
	
	
	
	
	
	
	public void hierarchie() {
        Iterator<Hierarchie> ite = personne.iterator();
        System.out.println("-------" + this.getName() + "-------");
        while (ite.hasNext()) {
            Hierarchie c = ite.next();
            c.print();
        }

	}    
   
    
   @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GroupePersonnel other = (GroupePersonnel) obj;
        if (personne == null) {
            if (other.personne != null)
                return false;
        } else if (!personne.equals(other.personne))
            return false;
        if (nomDuGroupe == null) {
            if (other.nomDuGroupe != null)
                return false;
        } else if (!nomDuGroupe.equals(other.nomDuGroupe))
            return false;
        return true;
    }

    

}
	
	
	
	
	

