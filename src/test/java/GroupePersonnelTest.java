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
import pglp_5.exo5_1.GroupePersonnel;
import pglp_5.exo5_1.NumeroTel;
public class GroupePersonnelTest {
GroupePersonnel g,g2,g3;
File dir;
DAO<NumeroTel> numtel;
String nomDir;
DAO<GroupePersonnel> groupePersoDAO; 

@Before
public void setUp() throws IOException {
    nomDir = "Groupes";
    dir = new File(nomDir);
    
    g = new GroupePersonnel("groupe perso", 1);
    g2 = new GroupePersonnel("groupe perso2", 2);
    g3 = new GroupePersonnel("groupe perso3", 3);
    
    groupePersoDAO = new DAOFactory().getGroupePersonnelsDAO();
}
    @Test
    public void addTest() {

        g.add(g2);
        assertEquals(g.getChildren().get(0), g2);
    }

    @Test
    public void removeTest() {
        g.add(g2);
        g.add(g3);
        g.remove(g2);
        assertEquals(g.getChildren().get(0), g3);
    }


	
}
