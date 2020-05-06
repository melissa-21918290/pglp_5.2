package pglp_5.exo5_1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public final class Personnel implements Composite, Serializable {
   
    private static final long serialVersionUID = 1L;
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((dateNaissance == null) ? 0 : dateNaissance.hashCode());
        result = prime * result
                + ((fonction == null) ? 0 : fonction.hashCode());
        result = prime * result
                + ((nom == null) ? 0 : nom.hashCode());
        result = prime * result
                + ((numTelephones == null) ? 0 : numTelephones.hashCode());
        result = prime * result
                + ((prenom == null) ? 0 : prenom.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(final Object obj) {
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
            if (other.nom != null) {
                return false;
            }
        } else if (!nom.equals(other.nom)) {
            return false;
        }
        if (numTelephones == null) {
            if (other.numTelephones != null) {
                return false;
            }
        } else if (!numTelephones.equals(other.numTelephones)) {
            return false;
        }
        if (prenom == null) {
            if (other.prenom != null) {
                return false;
            }
        } else if (!prenom.equals(other.prenom)) {
            return false;
        }
        return true;
    }
    
    private String nom;
    
    private String prenom;
    
    private String fonction;
    
    private LocalDate dateNaissance;
    
    private final ArrayList<NumeroTel> numTelephones;
    
    private final int id;
    
    public static class Builder {
        
        private final String nom;
        
        private final String prenom;
        
        private final String fonction;
        
        private final LocalDate dateNaissance;
        
        private final int id;
        
        private final ArrayList<NumeroTel> numTelephones;
        
        public Builder(final String nom2, final String prm,
                final String fonc,
                final LocalDate date, final int id2) {
            this.nom = nom2;
            this.prenom = prm;
            this.fonction = fonc;
            this.dateNaissance = date;
            this.numTelephones = new ArrayList<NumeroTel>();
            this.id = id2;
        }
        
        public Builder numTelephones(final NumeroTel num) {
            this.numTelephones.add(num);
            return this;
        }
       
        public Personnel build() {
            return new Personnel(this);
        }
    }
   
    private Personnel(final Builder builder) {
        nom = builder.nom;
        prenom = builder.prenom;
        fonction = builder.fonction;
        dateNaissance = builder.dateNaissance;
        numTelephones = builder.numTelephones;
        id = builder.id;
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
    
    public int getId() {
        return id;
    }
   
    public void print() {
        System.out.println(this.nom + " " + this.prenom + ": \nfonction: "
                + this.fonction + "\ndate de naissance: "
                + this.dateNaissance + "\n");
    }
   
    public void setNom(final String nom2) {
        this.nom = nom2;
    }
    
    public void setPrenom(final String prenom2) {
        this.prenom = prenom2;
    }
    
    public void setFonction(final String fonction2) {
        this.fonction = fonction2;
    }
    
    public void setNaissance(final LocalDate date2) {
        this.dateNaissance = date2;
    }
   
    public String toString() {
        return (this.nom + " " + this.prenom + ": \nfonction: "
                + this.fonction + "\ndate de naissance: "
                + this.dateNaissance + "\n");
    }
}