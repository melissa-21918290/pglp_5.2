import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pglp_5.exo5_1.DAO;
import pglp_5.exo5_1.DAOFactoryJDBC;
import pglp_5.exo5_1.NumeroTel;
import pglp_5.exo5_1.Personnel;
import pglp_5.exo5_1.Personnel.Builder;
public class PersonnelTest {
	
	Personnel secretaire;
	
	String nomDir;
	File dir;
	
	DAO<Personnel> personnel;
	
	@Before
	public void setUp () throws IOException, SQLException{
		nomDir = "Personnel";
		dir = new File(nomDir);
		personnel = new DAOFactoryJDBC().getPersonnelDAO();
		NumeroTel portable = new NumeroTel("portable","0698102210", 1);
		Builder b = new Builder("bafdel","melissa","secretaire", LocalDate.of(1995, 11, 10),1);
		b.numTelephones(portable);
		Personnel p =b.build();
		secretaire = p;
				
	}
	@Test
	public void constructeurTest(){
		  String expDesc = "portable";
	        String expNum = "0698102210";
	        String expNom = "bafdel";
	        String expPrenom = "melissa";
	        String expFonction = "secrétaire";
	        LocalDate expDate = LocalDate.of(1995, 11, 10);
	        assertEquals(expDesc,
	                secretaire.getNumTelephones().get(0).getDescriptif());
	        assertEquals(expNum, secretaire.getNumTelephones().get(0).getNumero());
	        assertEquals(expNom, secretaire.getNom());
	        assertEquals(expPrenom, secretaire.getPrenom());
	        assertEquals(expFonction, secretaire.getFonction());
	        assertEquals(expDate, secretaire.getDateNaissance());
	}
	
	

}
