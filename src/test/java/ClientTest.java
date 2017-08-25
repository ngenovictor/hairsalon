import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;

/**
 * Created by kingkong on 8/23/17.
 */
public class ClientTest {

    @Before
    public void setUp(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hairsalontest", "kingkong", "m1@467net");
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
    public void Client_instantiatesCorrectly_true(){
        Stylist newStylist = new Stylist("Rose","0726772775");
        newStylist.save();
        Client newClient = new Client("Victor","0721717141",newStylist.getId());
        newClient.save();
        assertTrue(newClient instanceof Client);
    }
    @Test
    public void all_returnsALlInstancesOfClients_true(){
        Stylist newStylist = new Stylist("Rose","0726772775");
        newStylist.save();

        Client firstClient = new Client("Victor","0721717141",newStylist.getId());
        firstClient.save();

        Client secondClient = new Client("Shiro", "0721787878", newStylist.getId());
        secondClient.save();

        Client thirdClient = new Client("Shiro", "0721787878", newStylist.getId());
        thirdClient.save();

        assertTrue(Client.all().size()==3);

        assertTrue(Client.all().get(0).equals(firstClient));
        assertTrue(Client.all().get(1).equals(secondClient));
        assertTrue(Client.all().get(2).equals(thirdClient));
    }
    @Test
    public void find_returnsAParticularInstanceOfClient_Client(){
        Stylist newStylist = new Stylist("Rose","0726772775");
        newStylist.save();

        Client firstClient = new Client("Victor","0721717141",newStylist.getId());
        firstClient.save();

        assertTrue(Client.find(firstClient.getId()).equals(firstClient));
    }
    @Test
    public void save_createsARecordOfClientInDb_true(){
        Stylist newStylist = new Stylist("Rose","0726772775");
        newStylist.save();

        Client firstClient = new Client("Victor","0721717141",newStylist.getId());
        firstClient.save();

        Client secondClient = new Client("Shiro", "0721787878", newStylist.getId());
        secondClient.save();

        Client thirdClient = new Client("Shiro", "0721787878", newStylist.getId());
        thirdClient.save();

        assertTrue(Client.all().size()==3);
    }
    @Test
    public void update_changesNameOfClient_String(){
        Stylist newStylist = new Stylist("Rose","0726772775");
        newStylist.save();

        Client firstClient = new Client("Victor","0721717141",newStylist.getId());
        firstClient.save();

        firstClient.update("Ngeno","0721717141",newStylist.getId());
        assertEquals(firstClient.getName(), "Ngeno");
    }
    @Test
    public void delete_removesTheInstanceOfClientFromDb_true(){
        Stylist newStylist = new Stylist("Rose","0726772775");
        newStylist.save();

        Client firstClient = new Client("Victor","0721717141",newStylist.getId());
        firstClient.save();

        firstClient.delete();

        assertTrue(Client.all().size()==0);
    }
}
