package maas.maas.controllers;

import java.sql.*;
import java.util.ArrayList;


public class SQLHandle {
    public static ArrayList verifyLogin(String host, String port, String login, String pw) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+port
                ,login
                ,pw);
        //class object declaration for Connected.java
        Querries sh=new Querries();
        sh.setConnection(connection);

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("show databases");

        ArrayList dblist = new ArrayList();
        while(rs.next()){
            dblist.add(rs.getString(1));
        }
        return dblist;
    }



}
