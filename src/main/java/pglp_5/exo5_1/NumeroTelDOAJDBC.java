package pglp_5.exo5_1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.database.Database;

public class NumeroTelDOAJDBC extends DAOJDBC<NumeroTel> {

	static final int UN = 1;
	public NumeroTelDOAJDBC() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
public NumeroTel create(final NumeroTel obj)
throws SQLException {
	DatabaseMetaData DBMD = getconn().getMetaData();
	ResultSet resultat = DBMD.getTables(null, null, "numero_telephone".toUpperCase(), null );
	try(Statement create =  getconn().createStatement()){
	if (!resultat.next()){
		create.executeUpdate("create table NumeroTel"
			  + " (id int primary key, descriptif varchar(30),"
                + " numero varchar(30))");
	}
	try { 
		String updateString =("insert into NumeroTel"
			+"value(?,?,?)");
			PreparedStatement update = getconn().prepareStatement(updateString);
			
		

            update.setInt(1, obj.getId());
            update.setString(2, obj.getDescriptif());
            update.setString(UN, obj.getNumero());

            update.executeUpdate();
            update.close();

            resultat = create.executeQuery("SELECT * FROM numero_telephone");

            System.out.println("---Table numero_telephone:---\n");
            System.out.println("id\t descriptif\t numero");
            while (resultat.next()) {
                System.out.printf("%d\t%s\t%s%n", resultat.getInt("id"),
                        resultat.getString("descriptif"), resultat.getString("numero"));
            }
            System.out.println("------------------------------------\n");
            System.out.println("L'objet " + obj.toString()
            + " a bien été enregistré!\n");
            resultat.close();
        }  catch (org.apache.derby.shared.common.error
                .DerbySQLIntegrityConstraintViolationException e) {
            System.out.println("Cet id a deja était utilisé pour"
                    + " la table numero_de_telephone!\n");
        }
	create.close();
	return obj;
	
}
}

@Override
public void delete(NumeroTel obj) throws SQLException {
	 try (Statement stmt = getconn().createStatement()) {
         try (ResultSet  resultat = stmt.executeQuery("select id"
                 + " from numero_telephone where id="
                 + obj.getId())) {

             if (!resultat.next()) {
                 System.out.println("Cet identifiant pour numero n'a pas"
                         + " encore été utilisé,"
                         + "il n'y a donc pas de suppression possible.");
             } else {
                 String sql = "delete from correspondance where id_numero="
                         + obj.getId();
                 stmt.executeUpdate(sql);
                 sql = "delete from numero_telephone"
                         + " where id=" + obj.getId();
                 stmt.executeUpdate(sql);
                 System.out.printf("Le numero de telephone avec l'id "
                         + obj.getId()
                         + " a bien été supprimé!\n");
             }
             stmt.close();
         }
     }
 }
	


@Override
public NumeroTel update(NumeroTel obj) throws IOException, SQLException {
	 try (Statement stmt = getconn().createStatement()) {
         try (ResultSet resultat = stmt.executeQuery("select descriptif,"
                 + " numero from numero_telephone where id="
                 + obj.getId())) {
             if (!resultat.next()) {
                 System.out.println("Cet identifiant pour numero"
                         + " n'a pas encore été utilisé,"
                         + "il n'y a donc pas de mise a jour possible.");
                 this.create(obj);
             } else {
                 this.delete(obj);
                 this.create(obj);
                 System.out.println("La mise à jour du numero d'id "
                         + obj.getId()
                         + " dans la table numero_de_telephone"
                         + " a été effectué!\n");
             }
             stmt.close();
             return obj;
         }}
}

@Override
public NumeroTel find(int id) throws FileNotFoundException, IOException, SQLException {
	 NumeroTel search = null;
     DatabaseMetaData dbmd = getconn().getMetaData();
     try (Statement exist = getconn().createStatement()) {
         ResultSet rsEx = dbmd.getTables(null, null,
                 "numero_telephone".toUpperCase(),
                 null);
         if (rsEx.next()) {
             try (Statement stmt = getconn().createStatement()) {
                 try (ResultSet rs = stmt.executeQuery("select descriptif,"
                         + " numero from numero_telephone"
                         + " where id=" + id)) {
                     if (!rs.next()) {
                         System.out.println("Il n'y a pas de"
                                 + " numero correspondant"
                                 + " a l'id" + id + " dans la"
                                 + " table numero_de_telephone!\n");
                         return null;
                     }
                     String desc = rs.getString("descriptif");
                     String num = rs.getString("numero");
                     search = new NumeroTel(desc, num, id);
                     System.out.println("Le numero suivant a ete trouve"
                             + " avec l'identifiant " + id + ":");
                     System.out.println(search.toString() + "\n");
                     rs.close();
                     stmt.close();
                 }
             }
         } else {
             System.out.println("Il n'y a pas encore de numeros"
                     + " dans la base de données!\n");
         }
     }
     return search;
 }
public void affichageTableNumero() throws SQLException {
    DatabaseMetaData dbmd = getconn().getMetaData();
    try (Statement exist = getconn().createStatement()) {
        ResultSet rsEx = dbmd.getTables(null, null,
                "numero_telephone".toUpperCase(),
                null);
        if (rsEx.next()) {
            try (Statement stmt = getconn().createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT *"
                        + " FROM numero_telephone")) {
                    System.out.println("---Table numero_telephone:---\n");
                    System.out.println("id\t descriptif\t numero\t");
                    while (rs.next()) {
                        System.out.printf("%d\t%s \t%s %n",
                                rs.getInt("id"),
                                rs.getString("descriptif"),
                                rs.getString("numero"));
                    }
                    System.out.println("-----------------------"
                            + "-------------\n");
                    rs.close();
                }
            }
        } else {
            System.out.println("Il n'y a pas encore de numeros"
                    + " dans la base de données!\n");
        }
    }
}
}
