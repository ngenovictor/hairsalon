import org.sql2o.Connection;

import java.util.List;

/**
 * Created by kingkong on 8/23/17.
 */
public class Client {
    private int id;
    private String name;
    private String telno;
    private int stylistId;

    public Client(String name, String telno, int stylistId){
        this.name = name;
        this.telno = telno;
        this.stylistId = stylistId;
    }

    public static List<Client> all(){
        try(Connection conn = new DB.sql2o.open()){
            String sql = "SELECT * FROM clients";
            return conn.createQuery(sql)
                    .executeAndFetch(Client.class);
        }
    }
    public void save(){
        try(Connection conn = DB.sql2o.open()){
            String sql = "INSERT INTO clients (name, telno, stylistId) VALUES (:name, :telno,:stylistId)";
            this.id = (int) conn.createQuery(sql, true)
                    .addParameter("name",name)
                    .addParameter("telno", telno)
                    .addParameter("stylistId",stylistId)
                    .executeUpdate()
                    .getKey();
        }

    }
}
