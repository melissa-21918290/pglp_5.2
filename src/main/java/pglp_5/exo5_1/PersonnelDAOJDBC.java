package pglp_5.exo5_1;

import java.sql.Statement;
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
                this.correspondance(obj.getId(), num.getId());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personnel find(int id) throws FileNotFoundException, IOException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
