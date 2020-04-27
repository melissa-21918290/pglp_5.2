package pglp_5.exo5_1;

import java.sql.Statement;
import java.time.LocalDate;

import pglp_5.exo5111111.Personnel.Builder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonnelDAOJDBC extends DAOJDBC<Personnel> {

	
	static final int UN =1;
	static final int DEUX=2;
	static final int TROIS= 3;
	private DAOJDBC<NumeroTel>numTelJDBC;
	
	public PersonnelDAOJDBC() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	numTelJDBC = new DAOFactoryJDBC().getNumeroTelDAO();
	}

	@Override
	public Personnel create(Personnel obj) throws IOException, SQLException {
		DatabaseMetaData DBMD= getconn().getMetaData();
		ResultSet resultat =DBMD.getTables(null, null, "personnel".toUpperCase(), null);
		try(Statement create =  getconn().createStatement()){
			if (!resultat.next()){
				 create.executeUpdate("Create table personnel"
	                        + " (id int primary key, nom varchar(30),"
	                        + " prenom varchar(30), fonction varchar(30),"
	                        + "date_de_naissance varchar(30))");
	            }
			try {String updateString = ("insert into personnel values"
                    + " (?, ?, ?, ?, ?)");
            PreparedStatement update = getconn().prepareStatement(updateString);
            update.setInt(1, obj.getId());
            update.setString(2, obj.getNom());
            update.setString(UN, obj.getPrenom());
            update.setString(DEUX, obj.getFonction());
            update.setString(TROIS, obj.getDateNaissance().toString());
            update.executeUpdate();
            update.close();
            resultat = create.executeQuery("SELECT * FROM personnel");
            System.out.println("---Table Personnel:---\n");
            System.out.println("id\t nom\t prenom\t fonction\t"
                    + " date_de_naissance");
            while (resultat.next()) {
                System.out.printf("%d\t%s\t%s\t%s\t%s%n", resultat.getInt("id"),
                        resultat.getString("nom"), resultat.getString("prenom"),
                        resultat.getString("fonction"),
                        resultat.getString("date_de_naissance"));
            }
            System.out.println("---------------------"
                    + "---------------\n");

            resultat.close();
            for (NumeroTel num : obj.getNumTelephones()) {
                numTelJDBC.create(num);
                this.associe(obj.getId(), num.getId());
            }

            System.out.println("L'objet " + obj.toString()
            + "a bien été enregistré!\n\n");
        } catch (org.apache.derby.shared.common.error
                .DerbySQLIntegrityConstraintViolationException e) {
            System.out.println("Cet id a deja était utilisé"
                    + " pour la table personnel!\n");
        }
        create.close();
        return obj;
    }
}

	@Override
	public void delete(Personnel obj) throws SQLException {
		try (Statement stmt = getconn().createStatement()) {
            try (Statement stmt2 = getconn().createStatement()) {
                int idNum, idPersonne;
                idPersonne = obj.getId();
                ResultSet resultat = stmt.executeQuery("SELECT * FROM"
                        + " correspondance WHERE"
                        + " id_personnel=" + idPersonne);
                String sql;

                while (resultat.next()) {
                    idNum = resultat.getInt("id_num");
                    sql = "delete from correspondance"
                            + " where id_personnel=" + idPersonne
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
		
	

	@Override
	public Personnel update(Personnel obj) throws IOException, SQLException {
	
	        try (Statement stmt =
	                getconn().createStatement()) {
	            try (ResultSet resultat = stmt.executeQuery("select *"
	                    + "from personnel where id="
	                    + obj.getId())) {
	                if (!resultat.next()) {
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
	                resultat.close();
	                stmt.close();
	                return obj;
	            }
	        }
	    }
	
	@Override
	public Personnel find(int id) throws FileNotFoundException, IOException, SQLException {
	     Personnel search = null;
	        DatabaseMetaData DBMD = getconn().getMetaData();
	        try (Statement existe = getconn().createStatement()) {
	            ResultSet resulatS = DBMD.getTables(null, null,
	                    "personnel".toUpperCase(),
	                    null);
	            if (resulatS.next()) {
	                try (Statement stmt =
	                        getconn().createStatement()) {
	                    try (ResultSet resultat = stmt.executeQuery("select *"
	                            + "from personnel"
	                            + " where id=" + id)) {
	                        if (!resultat.next()) {
	                            System.out.println("Il n'y a pas de personnel"
	                                    + " correspondant a l'id "
	                                    + id + " dans la table personnel!\n");
	                            return null;
	                        }
	                        String nom = resultat.getString("nom");
	                        String prenom = resultat.getString("prenom");
	                        String fonction = resultat.getString("fonction");
	                        String date = resultat.getString("date de naissance");
	                        String[] tab = date.split("-");
	                        LocalDate lDate =
	                                LocalDate.of(Integer.parseInt(tab[0]),
	                                        Integer.parseInt(tab[1]),
	                                        Integer.parseInt(tab[2]));
	                        Builder b = new Builder(nom,
	                                prenom, fonction,
	                                lDate, id);
	                        search = b.build();

	                        int idNum;
	                        NumeroTel numeroTel;
	                        try (ResultSet rs2 = stmt.executeQuery("select *"
	                                + " from correspondance"
	                                + " where id_personnel=" + id)) {
	                            while (rs2.next()) {
	                                idNum = rs2.getInt("id_numero");
	                                numeroTel = numTelJDBC.find(idNum);
	                                if (numeroTel != null) {
	                                    b.numTelephones(numeroTel);
	                                }
	                            }

	                            System.out.println("Le personnel suivant a ete"
	                                    + " trouve avec l'identifiant " + id + ":");
	                            System.out.println(search.toString() + "\n");

	                            rs2.close();
	                            resultat.close();
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
	private void associe(final int idPerso, final int idNum)
            throws SQLException {
        DatabaseMetaData DBMD = getconn().getMetaData();
        ResultSet resultat = DBMD.getTables(null, null,
                "correspondance".toUpperCase(), null);

        try (Statement stmt =
                getconn().createStatement()) {
            if (!resultat.next()) {
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
                try (ResultSet resultat2 = stmt.executeQuery("SELECT *"
                        + " FROM correspondance")) {
                    System.out.println("---Table correspondance:---\n");
                    System.out.println("id_personnel\t id_numero");
                    while (resultat2.next()) {
                        System.out.printf("%d\t\t%d%n",
                                resultat2.getInt("id_personnel"),
                                resultat2.getInt("id_numero"));
                    }
                    System.out.println("---------------"
                            + "---------------------\n");
                    resultat2.close();
                    resultat.close();
                    stmt.close();
                }
            }  catch (org.apache.derby.shared.common
                    .error.DerbySQLIntegrityConstraintViolationException e) {
                System.out.println("Cet id a deja était utilisé pour "
                        + "la table correspondance!\n");
            }
        }
    }
	
	public void afficheTabPersonnel() throws SQLException {
        DatabaseMetaData DBMD= getconn().getMetaData();
        try (Statement exist = getconn().createStatement()) {
            ResultSet rsEx = DBMD.getTables(null, null,
                    "correspondance".toUpperCase(),
                    null);
            if (rsEx.next()) {
                try (Statement stmt = getconn().createStatement()) {
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
	
	public void affichageTabassocie() throws SQLException {
        DatabaseMetaData DBMD = getconn().getMetaData();
        try (Statement exist = getconn().createStatement()) {
            ResultSet rsEx = DBMD.getTables(null, null,
                    "correspondance".toUpperCase(),
                    null);
            if (rsEx.next()) {
                try (Statement stmt = getconn().createStatement()) {
                    try (ResultSet resultat = stmt.executeQuery("SELECT *"
                            + " FROM correspondance")) {
                        System.out.println("---Table correspondance:---\n");
                        System.out.println("id_personnel\t id_numero\t");
                        while (resultat.next()) {
                            System.out.printf("%d\t\t%d\t%n",
                                    resultat.getInt("id_personnel"),
                                    resultat.getInt("id_numero"));
                        }
                        System.out.println("-----------------------"
                                + "-------------\n");
                        resultat.close();
                    }
                }
            } else {
                System.out.println("Il n'y a pas de correspondance!\n");
            }
        }
    }
	
	}


