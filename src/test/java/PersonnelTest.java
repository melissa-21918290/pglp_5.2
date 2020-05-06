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
	        String expFonction = "secretaire";
	        LocalDate expDate = LocalDate.of(1995, 11, 10);
	        assertEquals(expDesc,secretaire.getNumTelephones().get(0).getDescriptif());
	        assertEquals(expNum, secretaire.getNumTelephones().get(0).getNumero());
	        assertEquals(expNom, secretaire.getNom());
	        assertEquals(expPrenom, secretaire.getPrenom());
	        assertEquals(expFonction, secretaire.getFonction());
	        assertEquals(expDate, secretaire.getDateNaissance());
	}
	 private Object deserialize(final byte[] bytes) throws ClassNotFoundException, IOException {
	        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
	        ObjectInputStream o = new ObjectInputStream(b);
	        return o.readObject();
	    }
	 private byte[] serialize(final Object obj) throws IOException {
	        ByteArrayOutputStream b = new ByteArrayOutputStream();
	        ObjectOutputStream o = new ObjectOutputStream(b);
	        o.writeObject(obj);
	        return b.toByteArray();
	    }
	   @Test
	    public void ConsistencyTest() throws IOException, ClassNotFoundException {
	        Personnel p = secretaire;
	        byte[] serialized1 = serialize(p);
	        byte[] serialized2 = serialize(p);

	        Object deserialized1 = deserialize(serialized1);
	        Object deserialized2 = deserialize(serialized2);
	        Assert.assertEquals(deserialized1, deserialized2);
	        Assert.assertEquals(p, deserialized1);
	        Assert.assertEquals(p, deserialized2);
	    }
	   @Test
	    public void createTest() throws IOException, ClassNotFoundException, SQLException {       
	        personnel.create(secretaire);
	        
	        File search = new File(nomDir + "\\" + secretaire.getId() + ".txt");
	        Object deserialized = null;
	        
	        byte[] fileContent = Files.readAllBytes(search.toPath());
	       
	        deserialized = deserialize(fileContent);
	        Personnel expected = (Personnel) deserialized;
	        
	        assertTrue(dir.exists());
	        assertTrue(search.exists());
	        assertEquals(expected, secretaire);
	        
	        personnel.delete(secretaire);
	        dir.delete();
	    }
	   @Test
	    public void deleteTest() throws IOException, ClassNotFoundException, SQLException {      
	        NumeroTel portable =
	                new NumeroTel("portable", "0651724519", 2);
	        Builder b = new Builder("bafdel", "melissa", "secrétaire",
	                LocalDate.of(1995, 11, 10), 2);
	        b.numTelephones(portable);
	        Personnel secretaire2 = b.build();
	        
	        File search = new File(nomDir + "\\" + secretaire.getId() + ".txt");
	        File expected = new File(nomDir + "\\" + secretaire2.getId() + ".txt");  

	        personnel.create(secretaire);
	        personnel.create(secretaire2);
	        personnel.delete(secretaire);
	        
	        assertTrue(!search.exists());
	        assertTrue(expected.exists());
	        personnel.delete(secretaire2);
	    }
	   @Test
	    public void updateTest() throws IOException, ClassNotFoundException, SQLException {      
	        File search = new File(nomDir + "\\" + secretaire.getId() + ".txt");

	        personnel.create(secretaire);
	        personnel.update(secretaire);
	        Object deserialized = null;
	        
	        byte[] fileContent = Files.readAllBytes(search.toPath());
	       
	        deserialized = deserialize(fileContent);
	        Personnel expected = (Personnel) deserialized;
	        
	        assertTrue(search.exists());
	        assertEquals(expected, secretaire);
	        personnel.delete(secretaire);
	    }
	   @Test
	    public void findTest() throws IOException, ClassNotFoundException, SQLException {      
	        File search = new File(nomDir + "\\" + secretaire.getId() + ".txt");
	        Personnel expected;
	        personnel.create(secretaire);
	        
	        expected = personnel.find(1);
	        
	        assertTrue(search.exists());
	        assertEquals(expected, secretaire);
	        personnel.delete(secretaire);
	    }
	}


