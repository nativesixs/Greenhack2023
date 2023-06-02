package maas.maas;

import maas.maas.controllers.Querries;
import maas.maas.controllers.SQLHandle;

import java.sql.*;
import java.util.ArrayList;

public class Booking {
    private static ArrayList dblst;

    public static boolean isBooked(String action){
        //mysql q to find if taken=true or no


        return false;
    }


    public static void tableSetter(ResultSet rs) throws SQLException {
        ArrayList<String> data = new ArrayList<String>();
        while (rs.next()) {
            //iterate row
            ArrayList<String> row = new ArrayList<String>();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                row.add(rs.getString(i));
                System.out.println(row);
            }
            data.add(String.valueOf(row));
        }

        System.out.println(data);
    }



    public static String main(String button) throws SQLException {
        if(isBooked(button)){
            return "error";
        }
//        dblst = SQLHandle.verifyLogin(hostField.getText(), portField.getText(), loginField.getText(), pwField.getText());
//        dblst = SQLHandle.verifyLogin("localhost","3306","root","star230902");
//
//        Querries.pickdatabase("cats");
//
//        tableSetter(Querries.queryExecute("","","",2));

        String sqlSelectAllPersons = "SELECT * FROM seating";
        String connectionUrl = "jdbc:mysql://localhost:3306/firstfloor";

        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "toor");
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("id");
                System.out.println(id);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }



        return null;
    }

}
