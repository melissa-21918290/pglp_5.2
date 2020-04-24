package pglp_5.exo5_1;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;




public class GroupePersonnelTest {

	GroupePersonnel G,G2,G3;
	
	
	@Before
	public void SetUp(){
		G = new GroupePersonnel("departement");
		G2 = new GroupePersonnel("groupe personnel 2");
		G3 = new GroupePersonnel("groupe personnel 3");
	
	}
	
	
	  
	@Test
	public void containsTest(){
       G.add(G2);
       G.add(G3);
       assertEquals("departement", G.getName());
       assertTrue(G.contains(G2));
       assertTrue(G.contains(G3));
     
       
	}
	   
	   private Object deserialize(final byte[] bytes) throws ClassNotFoundException, IOException {
	        ByteArrayInputStream a = new ByteArrayInputStream(bytes);
	        ObjectInputStream b = new ObjectInputStream(a);
	        return b.readObject();
	    }
	   
	   private byte[] serialize(final Object obj) throws IOException {
	        ByteArrayOutputStream a = new ByteArrayOutputStream();
	        ObjectOutputStream b = new ObjectOutputStream(a);
	        b.writeObject(obj);
	        return a.toByteArray();
	    }
	   
	   @Test
	    public void serialisableTest() throws IOException, ClassNotFoundException {
	        byte[] serialisable = serialize(G);
	        byte[] serialisable2= serialize(G);

	        Object deserialisable = deserialize(serialisable);
	        Object deserialisable2 = deserialize(serialisable2);
	        Assert.assertEquals(deserialisable, deserialisable2);
	        Assert.assertEquals(G, deserialisable);
	        Assert.assertEquals(G, deserialisable2);
	    }
	   
	
}
