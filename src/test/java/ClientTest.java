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
        Client newClient = new Client("Victor","0721717141",newStylist.getId());
        newClient.save();
        Client secondClient = new Client("Shiro", "0721787878", newStylist.getId());
        secondClient.save();
        assertTrue(Client.all().contains(newClient));
        assertTrue(Client.all().contains(secondClient));
    }
}
