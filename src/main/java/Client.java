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
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getTelNo(){
        return telno;
    }
    public int getStylistId(){
        return stylistId;
    }
    public static Client find(int id){
        try(Connection conn = DB.sql2o.open()){
            String sql = "SELECT * FROM clients WHERE id=:id";
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Client.class);
        }
    }

    public static List<Client> all(){
        try(Connection conn = DB.sql2o.open()){
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
    @Override
    public boolean equals(Object otherObject){
        if (!(otherObject instanceof Client)){
            return false;
        }else {
            Client thisClient = (Client) otherObject;
            return thisClient.getId() == this.getId() &&
                    thisClient.getName().equals(this.getName())&&
                    thisClient.getTelNo().equals(this.getTelNo())&&
                    thisClient.getStylistId() == this.getStylistId();
        }
    }
    public void update(String name, String telno, int stylistId){
        try(Connection conn = DB.sql2o.open()){
            String sql = "UPDATE clients SET name=:name, telno=:telno, stylistId=:stylistId where id=:id";
            conn.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("telno",telno)
                    .addParameter("stylistId",stylistId)
                    .addParameter("id",id)
                    .executeUpdate();
            this.name = name;
            this.telno = telno;
            this.stylistId = stylistId;
        }
    }
    public void delete(){
        try(Connection conn = DB.sql2o.open()){
            String sql = "DELETE FROM clients WHERE id=:id";
            conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }
    }
}
