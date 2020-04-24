package pglp_5.exo5_1;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;



public class NumeroTelTest {
	NumeroTel numero;
	
	@Before
	public void SetUp(){
		numero = new NumeroTel("portable","0698552310");
	}
	@Test
	public void getNumeroTest(){
		NumeroTel numero = new NumeroTel("portable","0698552310");
		String num ="0698552310";
		assertEquals(num, numero.getNumero());
	}
	@Test
	public void getDescriptif(){
		String Type ="portable";
		assertEquals(Type,numero.getDescriptif());
	}
	
	public void createTest()throws IOException, ClassNotFoundException{
		String nomF = "NumeroTel";
		File fichier = new File(nomF);
		
		DAOApp<NumeroTel> numTel = DaoFactory.getNumeroTelDAO();
		numTel.create(numero);
		File search = new File(nomF +".txt");
		Object deserialisable =null;
		 byte[] fileContent = Files.readAllBytes(search.toPath());
		  deserialisable = deserialize(fileContent);
		  NumeroTel type = (NumeroTel) deserialisable;
		  assertTrue(fichier.exists());
	        assertTrue(search.exists());
	        assertEquals(type, numero);
	        numTel.delete(numero);
				
	}
	
	private Object deserialize(final byte[] bytes) throws ClassNotFoundException, IOException {
        ByteArrayInputStream a = new ByteArrayInputStream(bytes);
        ObjectInputStream b = new ObjectInputStream(a);
        return b.readObject();
        }
	
	private byte[] serialize(final Object obj)throws IOException{
		
		 ByteArrayOutputStream a= new ByteArrayOutputStream(); 
		ObjectOutputStream b = new ObjectOutputStream(a);
		b.writeObject(obj);
		return a.toByteArray();
		
	}
	
	@Test
    public void serialisableTest() throws IOException, ClassNotFoundException {
        byte[] serialisable = serialize(numero);
        byte[] serialisable2= serialize(numero);

        Object deserialisable = deserialize(serialisable);
        Object deserialisable2 = deserialize(serialisable2);
        Assert.assertEquals(deserialisable, deserialisable2);
        Assert.assertEquals(numero, deserialisable);
        Assert.assertEquals(numero, deserialisable2);
    }
	
	
	
}
