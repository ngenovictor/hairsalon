import org.sql2o.*;
import java.net.URI;
import java.net.URISyntaxException;
/**
 * Created by kingkong on 8/23/17.
 */
public class DB {
    private static URI dbUri;
    public static Sql2o sql2o;
    static {
        try{
            if(System.getenv("DATABASE_URL") == null){
                dbUri = new URI("postgres://localhost:5432/hairsalon");
            }else{
                dbUri = new URI(System.getenv("DATABASE_URL"));
            }
            int port = dbUri.getPort();
            String host = dbUri.getHost();
            String path = dbUri.getPath();
            String username = (dbUri.getUserInfo() == null) ? DbDetails.getUsername() : dbUri.getUserInfo().split(":")[0];
            String password = (dbUri.getUserInfo() == null) ? DbDetails.getPassword() : dbUri.getUserInfo().split(":")[1];
            sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path, username, password);
        }catch (URISyntaxException e){

        }
    }
}
