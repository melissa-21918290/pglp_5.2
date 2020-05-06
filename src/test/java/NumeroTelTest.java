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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pglp_5.exo5_1.DAO;
import pglp_5.exo5_1.DAOFactory;
import pglp_5.exo5_1.NumeroTel;
public class NumeroTelTest {
	NumeroTel tel;
	File dir;
	DAO<NumeroTel> numTel;
	String nomDir;
	 @Before
	    public void setUp() throws IOException {
	        nomDir = "NumeroTels";
	        dir = new File(nomDir);
	        
	        tel = new NumeroTel("portable", "0698294175", 2);
	        numTel = new DAOFactory().getNumeroTelephoneDAO();
	    }
	 @Test
	    public void getDescriptifTest() {
	        String expected = "portable";
	        assertEquals(expected, tel.getDescriptif());
	    }
	 
	
	

}
