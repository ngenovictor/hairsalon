import org.sql2o.Connection;

import java.util.List;

/**
 * Created by kingkong on 8/23/17.
 */
public class Stylist {
    private String name;
    private String telno;
    private int id;

    public Stylist(String name, String telno){
        this.name = name;
        this.telno = telno;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getTelNumber(){
        return telno;
    }
    public void save(){
        try(Connection conn=DB.sql2o.open()){
            String sql = "INSERT INTO stylists (name, telno) VALUES (:name, :telno)";
            this.id = (int) conn.createQuery(sql, true)
                    .addParameter("name", name)
                    .addParameter("telno", telno)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static Stylist find(int id){
        try(Connection conn = DB.sql2o.open()){
            String sql = "SELECT* FROM stylists WHERE id=:id";
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Stylist.class);
        }
    }
    public void update(String name, String telno){
        try(Connection conn = DB.sql2o.open()){
            String sql = "UPDATE stylists SET name=:name, telno=:telno WHERE id=:id";
            conn.createQuery(sql)
                    .addParameter("name",name)
                    .addParameter("telno", telno)
                    .addParameter("id",id)
                    .executeUpdate();
            this.name = find(this.id).name;
            this.telno = find(this.id).telno;
        }
    }
    public static List<Stylist> all(){
        try(Connection conn = DB.sql2o.open()){
            String sql = "SELECT * FROM stylists";
            return conn.createQuery(sql)
                    .executeAndFetch(Stylist.class);
        }
    }
    public void delete(){
        try(Connection conn = DB.sql2o.open()){
            String sql = "DELETE FROM stylists WHERE id=:id";
            conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }
    }

}
