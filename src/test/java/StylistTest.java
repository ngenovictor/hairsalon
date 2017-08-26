import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;

/**
 * Created by kingkong on 8/23/17.
 */
public class StylistTest {
    @Before
    public void setUp(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hairsalontest", DbDetails.getUsername(), DbDetails.getPassword());
    }
    @After
    public void tearDown(){
        try(Connection conn = DB.sql2o.open()){
            String stylistsql = "DELETE FROM stylists *";
            String clientsql = "DELETE FROM clients *";
            conn.createQuery(stylistsql).executeUpdate();
            conn.createQuery(clientsql).executeUpdate();
        }
    }
    @Test
    public void Stylist_initializesCorrectly_true(){
        Stylist newStylist = new Stylist("Rose","123");
        assertTrue(newStylist instanceof Stylist);
    }
    @Test
    public void getId_returnsIdOfStylist_true(){
        Stylist newStylist = new Stylist("Rose","123");
        newStylist.save();
        assertTrue(newStylist.getId()>0);
    }
    @Test
    public void getName_returnsNameOfStylist_String(){
        Stylist newStylist = new Stylist("Rose","123");
        assertEquals(newStylist.getName(), "Rose");
    }
    @Test
    public void getTelNumber_returnsPhoneNumberOfStylist_int(){
        Stylist newStylist = new Stylist("Rose","123");
        assertEquals(newStylist.getTelNumber(), "123");
    }
    @Test
    public void find_returnsObjectAsStoredInDB_String(){
        Stylist newStylist = new Stylist("Rose","123");
        newStylist.save();
        assertEquals(Stylist.find(newStylist.getId()).getName(),"Rose");
    }
    @Test
    public void save_savesTheStylistIntoDB_int(){
        Stylist newStylist = new Stylist("Rose","123");
        newStylist.save();
        assertEquals((Stylist.find(newStylist.getId())).getId(),newStylist.getId());
    }
    @Test
    public void update_changesDetailsOfAStylist_String(){
        Stylist newStylist = new Stylist("Rose","123");
        newStylist.save();
        newStylist.update("Wambui","1234");
        assertEquals(newStylist.getTelNumber(), "1234");
        assertEquals(newStylist.getName(),"Wambui");
    }
    @Test
    public void all_returnsAllInstancesOfStylist_String(){
        Stylist newStylist = new Stylist("Njeri","123");
        newStylist.save();
        assertEquals(Stylist.all().get(0).getName(),"Njeri");
    }
    @Test
    public void delete_removesTheStylistFromTheDB_false(){
        Stylist newStylist = new Stylist("Rose","123");
        newStylist.save();
        newStylist.delete();
        assertFalse(Stylist.all().size()>0);
    }
    @Test
    public void getClients_returnsAllTheStylistClients_String(){
        Stylist newStylist = new Stylist("Rose","123");
        newStylist.save();
        Client newClient = new Client("Victor","0721717141",newStylist.getId());
        newClient.save();
        assertEquals(newStylist.getClients().get(0).getName(),newClient.getName());
    }
}
