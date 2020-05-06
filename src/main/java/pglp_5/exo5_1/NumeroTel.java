package pglp_5.exo5_1;

import java.io.Serializable;


public class NumeroTel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
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
        NumeroTel other = (NumeroTel) obj;
        if (descriptif == null) {
            if (other.descriptif != null) {
                return false;
            }
        } else if (!descriptif.equals(other.descriptif)) {
            return false;
        }
        if (numero == null) {
            if (other.numero != null) {
                return false;
            }
        } else if (!numero.equals(other.numero)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((descriptif == null) ? 0 : descriptif.hashCode());
        result = prime * result + id;
        result = prime * result
                + ((numero == null) ? 0 : numero.hashCode());
        return result;
    }
    
    private String descriptif;
    
    private String numero;
    
    private int id;
    
    public NumeroTel(final String desc, final String num, final int id2) {
        this.descriptif = desc;
        this.numero = num;
        this.setId(id2);
    }
  
    public String getDescriptif() {
        return descriptif;
    }
    
    public void setDescriptif(final String desc) {
        this.descriptif = desc;
    }
    
    public String getNumero() {
        return numero;
    }
    
    public void setNumero(final String num) {
        this.numero = num;
    }
    
    public String toString() {
        return this.getDescriptif() + ": " + this.getNumero();
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(final int id2) {
        this.id = id2;
    }
}