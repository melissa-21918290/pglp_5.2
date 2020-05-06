package pglp_5.exo5_1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;

public class GroupePersonnel implements Composite, Serializable {
   
    private static final long serialVersionUID = 1L;
    
    private List<Composite> children = new ArrayList<Composite>();
    
    private String nomGroupe;
    
    private int id;
   
    public GroupePersonnel(final String nom, final int id2) {
        this.nomGroupe = nom;
        this.setId(id2);
    }
    
    public void print() {
        System.out.println("-------" + this.getNomGroupe() + "-------");
        for (Composite composant: children) {
            composant.print();
        }
    }
    
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("-------" + this.getNomGroupe() + "-------\n");
        for (Composite composant: children) {
            buf.append(composant.toString() + "\n");
        }
        String s = buf.toString();
        return s;
    }
    
    public void add(final Composite composant) {
        children.add(composant);
    }
   
    public void remove(final Composite composant) {
        children.remove(composant);
    }
    
    public void hierarchie() {
        Iterator<Composite> ite = children.iterator();
        System.out.println("-------" + this.getNomGroupe() + "-------");
        while (ite.hasNext()) {
            Composite c = ite.next();
            c.print();
        }

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
        GroupePersonnel other = (GroupePersonnel) obj;
        if (children == null) {
            if (other.children != null) {
                return false;
            }
        } else if (!children.equals(other.children)) {
            return false;
        }
        if (nomGroupe == null) {
            if (other.nomGroupe != null) {
                return false;
            }
        } else if (!nomGroupe.equals(other.nomGroupe)) {
            return false;
        }
        return true;
    }
   
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((children == null) ? 0 : children.hashCode());
        result = prime * result + id;
        result = prime * result
                + ((nomGroupe == null) ? 0 : nomGroupe.hashCode());
        return result;
    }
    
    public int getId() {
        return id;
    }
   
    public void setId(final int id2) {
        this.id = id2;
    }
    
    public String getNomGroupe() {
        return this.nomGroupe;
    }
    
    public List<Composite> getChildren() {
        return Collections.unmodifiableList(this.children);
    }
}