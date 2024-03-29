package maas.maas.controllers;
import java.sql.*;
import java.util.ArrayList;

public class Querries {
    //methods with req -> established connection
    private static Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static ResultSet queryExecute(String selected, String id,String command,int choice) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = null;
        switch(choice) {
            case 1:
                rs = statement.executeQuery("select * from "+selected);
                break;
            case 2:
                rs = statement.executeQuery("show tables");
                break;
            case 3:
                rs = statement.executeQuery("select * from " + selected + " where " + id + "= " + '\u0022' + command + '\u0022');
                break;
            case 4:
                rs = statement.executeQuery(command);
                break;
            case 5:
                rs = statement.executeQuery("show databases");
                break;
            case 6:
                rs = statement.executeQuery("drop table "+selected);
                break;
            case 7:
                //same as case 4 -> for statements that dont produce result set
                statement.executeUpdate(command);
                break;
        }
        return rs;
    }

    public static void pickdatabase(String db) throws SQLException {
        //eq to use <databasename>
        Statement statement = connection.createStatement();
        connection.setCatalog(db);
    }

    public static ArrayList<String> getColumnNames(String selected) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from "+selected);
        ResultSetMetaData rsm= rs.getMetaData();
        ArrayList<String> lst = new ArrayList<String>();
        for(int i=1;i<=getColumnCount(rsm);i++){
            lst.add(getColumnName(rsm,i));
        }
        return lst;
    }
    public static String getColumnName(ResultSetMetaData rsm, int colnum) throws SQLException {
        return rsm.getColumnName(colnum);
    }
    public static int getColumnCount(ResultSetMetaData rsm) throws SQLException {
        return rsm.getColumnCount();
    }

    public static ArrayList<String> getColumnType(String selected) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from "+selected);
        ResultSetMetaData rsm= rs.getMetaData();
        ArrayList<String> lst = new ArrayList<String>();
        for(int i=1;i<=getColumnCount(rsm);i++){
            lst.add(rsm.getColumnClassName(i));
        }
        return lst;
    }


    public static String getCurrentDb() throws SQLException {
        Statement statement = connection.createStatement();
        String db = connection.getCatalog();
        return db;
    }

}
