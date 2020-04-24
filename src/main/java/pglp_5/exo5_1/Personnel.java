package pglp_5.exo5_1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;



public class Personnel implements Hierarchie, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String nom;
	private final String prenom;
	private final String fonction;
	private final LocalDate dateNaissance;
	private final ArrayList<NumeroTel> numTelephones;
	
	public static class Builder {
		private final String nom;
		private final String prenom;
		private final String fonction;
		private final LocalDate dateNaissance;
		private final ArrayList<NumeroTel> numTelephones;
		
		public Builder(String nom, String prenom, String fonction, LocalDate dateNaissance) {
			this.nom = nom;
			this.prenom = prenom;
			this.fonction = fonction;
			this.dateNaissance= dateNaissance;
			this.numTelephones= new ArrayList<NumeroTel>();
		}
		
		public Builder numTelephones(NumeroTel num) {
			this.numTelephones.add(num);
			return this;
		}
		
		public Personnel build() {
			return new Personnel(this);
		}
	}
	
	private Personnel(Builder builder) {
		nom = builder.nom;
		prenom = builder.prenom;
		fonction = builder.fonction;
		dateNaissance = builder.dateNaissance;
		numTelephones = builder.numTelephones;
	}
	
	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getFonction() {
		return fonction;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public ArrayList<NumeroTel> getNumTelephones() {
		return numTelephones;
	}
	
	public void print() {
		System.out.println(this.nom + " " + this.prenom + ": \nfonction: " 
		+ this.fonction + "\ndate de naissance: "+this.dateNaissance + "\n");	
	}
	
	
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }      
        if (getClass() != obj.getClass()) {
            return false;
        }
        Personnel other = (Personnel) obj;
        if (dateNaissance == null) {
            if (other.dateNaissance != null) {
                return false;
            }        
        } else if (!dateNaissance.equals(other.dateNaissance)) {       
            return false;
        }
        if (fonction == null) {
            if (other.fonction != null) {
                return false;
            }
        } else if (!fonction.equals(other.fonction)) {
            return false;
        }
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        if (numTelephones == null) {
            if (other.numTelephones != null) {
                return false;
            }
        } else if (!numTelephones.equals(other.numTelephones))
            return false;
        if (prenom == null) {
            if (other.prenom != null)
                return false;
        } else if (!prenom.equals(other.prenom))
            return false;
        return true;
    }
	
}