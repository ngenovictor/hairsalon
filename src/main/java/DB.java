import org.sql2o.*;
/**
 * Created by kingkong on 8/23/17.
 */
public class DB {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hairsalon", "kingkong", "m1@467net");
//    public static Sql2o sql2o = new Sql2o(
//            "jdbc:postgresql://qtedkvydgcuayw:5ecc08f1bfe68921f8eb44e531f781732917ee3faf2e80af334da795ebb47c4f@ec2-174-129-218-106.compute-1.amazonaws.com:5432/dd9jscdrebbm9f",
//        "qtedkvydgcuayw",
//        "5ecc08f1bfe68921f8eb44e531f781732917ee3faf2e80af334da795ebb47c4f");
}
