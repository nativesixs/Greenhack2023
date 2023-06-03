package maas.maas;


import java.sql.*;
import java.util.ArrayList;

public class Booking {
    private static ArrayList dblst;

    static ArrayList<String> ids = new ArrayList<>();
    static ArrayList<String> status = new ArrayList<>();
    static ArrayList<String> date = new ArrayList<>();



    public static void executeQuery(String query){
        String connectionUrl = "jdbc:mysql://localhost:3306/firstfloor";
        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "toor");
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ids.add(rs.getString("id"));
                status.add(rs.getString("status"));
                date.add(rs.getString("date"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void insertInto(String seat,String date) throws SQLException {
        String query = "INSERT INTO status_table(id,status,date) VALUES (\'"+seat+"\',\'BUSY\',\'"+date+"\')";
        String connectionUrl = "jdbc:mysql://localhost:3306/firstfloor";
        Connection conn = DriverManager.getConnection(connectionUrl, "root", "toor");


//             PreparedStatement ps = conn.prepareStatement(query);
//             ResultSet rs = ps.executeQuery()) {

        Statement statement = conn.createStatement();
        statement.executeUpdate(query);

    }

    public static boolean isBookable(String button) throws SQLException {

        executeQuery("SELECT * FROM status_table");
        if(!ids.contains(button)){
            //seat is free
            return true;
        }else {
            //do something, seat is booked
            return false;
        }
    }

    public static void bookSeat(String seat,String datew) throws SQLException {
        insertInto(seat,datew);
    }



}
