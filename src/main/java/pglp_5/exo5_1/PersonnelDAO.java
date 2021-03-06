package pglp_5.exo5_1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;


public class PersonnelDAO extends DAO<Personnel> {
    
    @Override
    public Personnel create(final Personnel obj) throws IOException {
        String nomDir = "Personnels";
        File dir = new File(nomDir);
        FileOutputStream fileOut;
        ObjectOutputStream objOut;

        File file = new File(nomDir + "\\" + obj.getId() + ".txt");
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Le dossier est créé!\n");
            } else {
                System.out.println("le dossier n'a pas pu être créé!\n");
            }
        }
        fileOut = new FileOutputStream(file);
        objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(obj);
        objOut.close();
        System.out.println("Le fichier est créé!");
        return obj;
    }
    
    public Personnel update(final Personnel obj)
            throws IOException {
        String nomDir = "Personnels";
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
                System.out.println("Le fichier à mettre à jour"
                        + " n'existe pas!\n");
            }
        } else {
            System.out.println("Le dossier contenant le fichier"
                    + " n'existe pas!\n");
        }
        return obj;
    }
    
    public void delete(final Personnel obj) {
        String nomDir = "Personnels";
        File dir = new File(nomDir);
        if (dir.exists()) {
            File file = new File(nomDir + "\\" + obj.getId() + ".txt");
            if (file.exists()) {
                boolean test = file.delete();
                if (test) {
                    System.out.println("Le fichier est supprimé!\n");
                } else {
                    System.out.println("Echec de la suppression du fichier!\n");
                }
            } else {
                System.out.println("Le fichier à supprimer n'existe pas!\n");
            }
        } else {
            System.out.println("Le dossier contenant"
                    + " le fichier n'existe pas!\n");
        }
    }
    
    public Personnel find(final int id)
            throws IOException, ClassNotFoundException {
        String nomDir = "Personnels";
        File dir = new File(nomDir);
        File search = new File(nomDir + "\\" + id + ".txt");
        Object deserialized = null;
        if (dir.exists()) {
            if (search.exists()) {
                byte[] fileContent = Files.readAllBytes(search.toPath());
                deserialized = deserialize(fileContent);
                Personnel pers = (Personnel) deserialized;
                pers.print();
                return pers;
            } else {
                System.out.println("Le fichier n'existe pas!\n");
            }
        } else {
            System.out.println("Le dossier n'existe pas!\n");
        }
        return null;
    }
}