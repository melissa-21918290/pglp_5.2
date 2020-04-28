package pglp_5.exo5_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.sql.SQLException;



public class GroupePersonnelDAO extends DAO<GroupePersonnel> {

	@Override
	public GroupePersonnel create(final GroupePersonnel obj) throws IOException, SQLException, IOException {
		 String nomDir = "Groupes";
	        File dir = new File(nomDir);
	        FileOutputStream fileOut;
	        ObjectOutputStream objOut;

	        File file = new File(nomDir + "\\" + obj.getId() + ".txt");
	        if (!dir.exists()) {
	            if (dir.mkdir()) {
	                System.out.println("Le dossier est créé!");
	            } else {
	                System.out.println("le dossier n'a pas pu être créé!");
	            }
	        }
	        fileOut = new FileOutputStream(file);
	        objOut = new ObjectOutputStream(fileOut);
	        objOut.writeObject(obj);
	        objOut.close();
	        System.out.println("Le fichier est créé!");
		return obj;
	}

	@Override
	public void delete(final GroupePersonnel obj) throws SQLException {
		 String nomDir = "Groupes";
	        File dir = new File(nomDir);
	        if (dir.exists()) {
	            File file = new File(nomDir + "\\" + obj.getId() + ".txt");
	            if (file.exists()) {
	                boolean test = file.delete();
	                if (test) {
	                    System.out.println("Le fichier est supprimé!");
	                } else {
	                    System.out.println("Echec de la supression du fichier!");
	                }
	            } else {
	                System.out.println("Le fichier à supprimer n'existe pas!");
	            }
	        } else {
	            System.out.println("Le dossier contenant le fichier n'existe pas!");
	        }
	    }
		
	

	@Override
	public GroupePersonnel update(final GroupePersonnel obj) throws IOException, SQLException {
		String nomDir = "Groupes";
        File dir = new File(nomDir);
        if (dir.exists()) {
            File file = new File(nomDir + "\\" + obj.getId() + ".txt");
            if (file.exists()) {
                boolean test = file.delete();
                if (test) {
                    this.delete(obj);
                    this.create(obj);
                } else {
                    System.out.println("Echec de la mise a jour du fichier!");
                }
            } else {
                System.out.println("Le fichier à mettre à jour n'existe pas!");
            }
        } else {
            System.out.println("Le dossier contenant le fichier n'existe pas!");
        }
		return obj;
	}

	@Override
	public GroupePersonnel find(final int id) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {
		 String nomDir = "Groupes";
	        File dir = new File(nomDir);
	        File search = new File(nomDir + "\\" + id + ".txt");
	        Object deserialized = null;

	        if (dir.exists()) {
	            if (search.exists()) {
	                byte[] fileContent = Files.readAllBytes(search.toPath());
	                deserialized = deserialisable(fileContent);
	                GroupePersonnel gp = (GroupePersonnel) deserialized;
	                gp.hierarchie();
	                return gp;
	            } else {
	                System.out.println("Le fichier n'existe pas!");
	            }
	        } else {
	            System.out.println("Le dossier n'existe pas!");
	        }
		return null;
	}
	

	

	
}
