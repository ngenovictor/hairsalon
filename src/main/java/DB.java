import org.sql2o.*;
/**
 * Created by kingkong on 8/23/17.
 */
public class DB {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hairsalon", "kingkong", "m1@467net");
}
