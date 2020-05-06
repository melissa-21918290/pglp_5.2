package pglp_5.exo5_1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;



public class PersonnelDAOJDBC extends DAOJDBC<Personnel> {
    
    static final int TROIS = 3;
    
    static final int QUATRE = 4;
    
    static final int CINQ = 5;
    
    private DAOJDBC<NumeroTel> numTelJDBC;
    
    public PersonnelDAOJDBC() throws SQLException, IOException {
        super();
        numTelJDBC = new DAOFactoryJDBC().getNumeroTelephoneDAO();
    }
    
    public Personnel create(final Personnel obj) throws SQLException,
    IOException {
        DatabaseMetaData dbmd = getConnect().getMetaData();
        ResultSet rs = dbmd.getTables(null, null,
                "personnel".toUpperCase(), null);

        try (Statement creation = getConnect().createStatement()) {
            if (!rs.next()) {
                creation.executeUpdate("Create table personnel"
                        + " (id int primary key, nom varchar(30),"
                        + " prenom varchar(30), fonction varchar(30),"
                        + "date_de_naissance varchar(30))");
            }
            try {
                String updateString = ("insert into personnel values"
                        + " (?, ?, ?, ?, ?)");
                PreparedStatement update =
                        getConnect().prepareStatement(updateString);
                update.setInt(1, obj.getId());
                update.setString(2, obj.getNom());
                update.setString(TROIS, obj.getPrenom());
                update.setString(QUATRE, obj.getFonction());
                update.setString(CINQ, obj.getDateNaissance().toString());

                update.executeUpdate();
                update.close();

                rs = creation.executeQuery("SELECT * FROM personnel");

                System.out.println("---Table Personnel:---\n");
                System.out.println("id\t nom\t prenom\t fonction\t"
                        + " date_de_naissance");
                while (rs.next()) {
                    System.out.printf("%d\t%s\t%s\t%s\t%s%n", rs.getInt("id"),
                            rs.getString("nom"), rs.getString("prenom"),
                            rs.getString("fonction"),
                            rs.getString("date_de_naissance"));
                }
                System.out.println("---------------------"
                        + "---------------\n");

                rs.close();
                for (NumeroTel num : obj.getNumTelephones()) {
                    numTelJDBC.create(num);
                    this.correspondance(obj.getId(), num.getId());
                }

                System.out.println("L'objet " + obj.toString()
                + "a bien été enregistré!\n\n");
            } catch (org.apache.derby.shared.common.error
                    .DerbySQLIntegrityConstraintViolationException e) {
                System.out.println("Cet id a deja était utilisé"
                        + " pour la table personnel!\n");
            }
            creation.close();
            return obj;
        }
    }
    
    public void delete(final Personnel obj) throws SQLException {
        try (Statement stmt = getConnect().createStatement()) {
            try (Statement stmt2 = getConnect().createStatement()) {
                int idNum, idPerso;
                idPerso = obj.getId();
                ResultSet rs = stmt.executeQuery("SELECT * FROM"
                        + " correspondance WHERE"
                        + " id_personnel=" + idPerso);
                String sql;

                while (rs.next()) {
                    idNum = rs.getInt("id_numero");
                    sql = "delete from correspondance"
                            + " where id_personnel=" + idPerso
                            + "and id_numero=" + idNum;
                    stmt2.executeUpdate(sql);
                    sql = "delete from numero_telephone where id=" + idNum;
                    stmt2.executeUpdate(sql);
                    System.out.printf("Le numero avec l'id " + idNum
                            + " a bien été supprimé!\n");
                }
                sql = "delete from appartenance_personnel"
                        + " where id=" + obj.getId();
                stmt2.executeUpdate(sql);
                sql = "delete from appartenance_sous_groupe"
                        + " where id=" + obj.getId();
                stmt2.executeUpdate(sql);
                sql = "delete from personnel where id=" + obj.getId();
                stmt2.executeUpdate(sql);
                stmt.close();
                stmt2.close();
                System.out.printf("Le personnel avec l'id " + obj.getId()
                + " et les correspondances associées"
                + " ont bien été supprimé!\n");
            }
        }
    }
   
    public Personnel update(final Personnel obj)
            throws SQLException, IOException {
        try (Statement stmt =
                getConnect().createStatement()) {
            try (ResultSet result = stmt.executeQuery("select *"
                    + "from personnel where id="
                    + obj.getId())) {
                if (!result.next()) {
                    System.out.println("Cet identifiant pour personnel"
                            + " n'a pas encore été utilisé,"
                            + "il n'y a donc pas de mise a jour possible.");
                    this.create(obj);
                } else {
                    this.delete(obj);
                    this.create(obj);
                    System.out.println("La mise à jour du personnel d'id "
                            + obj.getId()
                            + " dans la table personnel a été effectué!\n");
                }
                result.close();
                stmt.close();
                return obj;
            }
        }
    }
    
    public Personnel find(final int id) throws SQLException,
    FileNotFoundException, ClassNotFoundException, IOException {
        Personnel search = null;
        DatabaseMetaData dbmd = getConnect().getMetaData();
        try (Statement exist = getConnect().createStatement()) {
            ResultSet rsEx = dbmd.getTables(null, null,
                    "personnel".toUpperCase(),
                    null);
            if (rsEx.next()) {
                try (Statement stmt =
                        getConnect().createStatement()) {
                    try (ResultSet rs = stmt.executeQuery("select *"
                            + "from personnel"
                            + " where id=" + id)) {
                        if (!rs.next()) {
                            System.out.println("Il n'y a pas de personnel"
                                    + " correspondant a l'id "
                                    + id + " dans la table personnel!\n");
                            return null;
                        }
                        String nom = rs.getString("nom");
                        String prenom = rs.getString("prenom");
                        String fonction = rs.getString("fonction");
                        String date = rs.getString("date_de_naissance");
                        String[] tab = date.split("-");
                        LocalDate lDate =
                                LocalDate.of(Integer.parseInt(tab[0]),
                                        Integer.parseInt(tab[1]),
                                        Integer.parseInt(tab[2]));
                        pglp_5.exo5_1.Personnel.Builder b = new pglp_5.exo5_1.Personnel.Builder(nom,
                                prenom, fonction,
                                lDate, id);
                        search = b.build();

                        int idNum;
                        NumeroTel numTel;
                        try (ResultSet rs2 = stmt.executeQuery("select *"
                                + " from correspondance"
                                + " where id_personnel=" + id)) {
                            while (rs2.next()) {
                                idNum = rs2.getInt("id_numero");
                                numTel = numTelJDBC.find(idNum);
                                if (numTel != null) {
                                    b.numTelephones(numTel);
                                }
                            }

                            System.out.println("Le personnel suivant a ete"
                                    + " trouve avec l'identifiant " + id + ":");
                            System.out.println(search.toString() + "\n");

                            rs2.close();
                            rs.close();
                            stmt.close();
                        }
                    }
                }
            } else {
                System.out.println("Il n'y a pas encore de personnels!\n");
            }
        }
        return search;
    }

    
    private void correspondance(final int idPerso, final int idNum)
            throws SQLException {
        DatabaseMetaData dbmd = getConnect().getMetaData();
        ResultSet rs = dbmd.getTables(null, null,
                "correspondance".toUpperCase(), null);

        try (Statement stmt =
                getConnect().createStatement()) {
            if (!rs.next()) {
                stmt.executeUpdate("Create table correspondance"
                        + " (id_personnel int NOT NULL,"
                        + " id_numero int NOT NULL, "
                        + "primary key (id_personnel, id_numero),"
                        + "foreign key (id_personnel) references"
                        + " personnel(id),"
                        + "foreign key (id_numero) references"
                        + " numero_telephone(id))");
            }

            try {
                stmt.executeUpdate("insert into correspondance values ("
                        + idPerso + "," + idNum + ")");
                try (ResultSet rs2 = stmt.executeQuery("SELECT *"
                        + " FROM correspondance")) {
                    System.out.println("---Table correspondance:---\n");
                    System.out.println("id_personnel\t id_numero");
                    while (rs2.next()) {
                        System.out.printf("%d\t\t%d%n",
                                rs2.getInt("id_personnel"),
                                rs2.getInt("id_numero"));
                    }
                    System.out.println("---------------"
                            + "---------------------\n");
                    rs2.close();
                    rs.close();
                    stmt.close();
                }
            }  catch (org.apache.derby.shared.common
                    .error.DerbySQLIntegrityConstraintViolationException e) {
                System.out.println("Cet id a deja était utilisé pour "
                        + "la table correspondance!\n");
            }
        }
    }

   
    public void affichageTablePersonnel() throws SQLException {
        DatabaseMetaData dbmd = getConnect().getMetaData();
        try (Statement exist = getConnect().createStatement()) {
            ResultSet rsEx = dbmd.getTables(null, null,
                    "correspondance".toUpperCase(),
                    null);
            if (rsEx.next()) {
                try (Statement stmt = getConnect().createStatement()) {
                    try (ResultSet rs = stmt.executeQuery("SELECT *"
                            + " FROM personnel")) {
                        System.out.println("---Table personnel:---\n");
                        System.out.println("id\t nom\t prenom\t fonction\t"
                                + " naissance\t");
                        while (rs.next()) {
                            System.out.printf("%d\t%s \t%s\t %s\t %s%n",
                                    rs.getInt("id"),
                                    rs.getString("nom"),
                                    rs.getString("prenom"),
                                    rs.getString("fonction"),
                                    rs.getString("date_de_naissance"));
                        }
                        System.out.println("-----------------------"
                                + "-------------\n");

                        rs.close();
                    }
                }
            } else {
                System.out.println("Il n'y a pas encore de personnels!\n");
            }
        }
    }

    
    public void affichageTableCorrespondance() throws SQLException {
        DatabaseMetaData dbmd = getConnect().getMetaData();
        try (Statement exist = getConnect().createStatement()) {
            ResultSet rsEx = dbmd.getTables(null, null,
                    "correspondance".toUpperCase(),
                    null);
            if (rsEx.next()) {
                try (Statement stmt = getConnect().createStatement()) {
                    try (ResultSet rs = stmt.executeQuery("SELECT *"
                            + " FROM correspondance")) {
                        System.out.println("---Table correspondance:---\n");
                        System.out.println("id_personnel\t id_numero\t");
                        while (rs.next()) {
                            System.out.printf("%d\t\t%d\t%n",
                                    rs.getInt("id_personnel"),
                                    rs.getInt("id_numero"));
                        }
                        System.out.println("-----------------------"
                                + "-------------\n");
                        rs.close();
                    }
                }
            } else {
                System.out.println("Il n'y a pas de correspondance!\n");
            }
        }
    }
}