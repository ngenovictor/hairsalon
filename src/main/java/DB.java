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
            String username = (dbUri.getUserInfo() == null) ? "kingkong" : dbUri.getUserInfo().split(":")[0];
            String password = (dbUri.getUserInfo() == null) ? "m1@467net" : dbUri.getUserInfo().split(":")[1];
            sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path, username, password);
        }catch (URISyntaxException e){

        }
    }
//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hairsalon", "kingkong", "m1@467net");
//    public static Sql2o sql2o = new Sql2o(
//            "jdbc:postgresql://qtedkvydgcuayw:5ecc08f1bfe68921f8eb44e531f781732917ee3faf2e80af334da795ebb47c4f@ec2-174-129-218-106.compute-1.amazonaws.com:5432/dd9jscdrebbm9f",
//        "qtedkvydgcuayw",
//        "5ecc08f1bfe68921f8eb44e531f781732917ee3faf2e80af334da795ebb47c4f");
}
