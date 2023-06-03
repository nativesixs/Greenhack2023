package maas.maas;


import java.sql.*;
import java.util.ArrayList;

public class Booking {
    private static ArrayList dblst;

    static ArrayList<String> ids = new ArrayList<>();
    static ArrayList<String> status = new ArrayList<>();
    static ArrayList<String> date = new ArrayList<>();


    public static String getYear(String date){
        return date.substring(6, 10);
    }
    public static String getMonth(String date){
        return date.substring(3, 5);
    }
    public static String getDay(String date){
        return date.substring(0, 2);
    }

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
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);

    }

    public static void createTable(String query) throws SQLException {
        String connectionUrl = "jdbc:mysql://localhost:3306/firstfloor";
        Connection conn = DriverManager.getConnection(connectionUrl, "root", "toor");
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

    public static ArrayList<String> getBooked(){
        executeQuery("SELECT * FROM status_table");
        return ids;
    }


    public static void fetchMaster(String selected) throws SQLException {
        String query="SELECT monitors_n,side_direction,temperatureFROM firstfloor.master_table where id_chair = "+"'"+selected+"'"+";";
        String connectionUrl = "jdbc:mysql://localhost:3306/firstfloor";
        Connection conn = DriverManager.getConnection(connectionUrl, "root", "toor");
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        int monitor=0;
        String side=null;
        int temp=0;
        while (rs.next()) {
            monitor= Integer.parseInt(rs.getString(1));
            side=rs.getString(2);
            temp= Integer.parseInt(rs.getString(3));
        }




    }

    public static void recursiveInsert(ArrayList data) throws SQLException {
        String query="INSERT INTO javatable(sk) VALUES ";
        for(int i=0;i<data.size();i++){
            query=query+"("+"'"+data.get(i)+"'"+"),";
        }
        query=query.substring(0,query.length()-1);
        query=query+";";

        String connectionUrl = "jdbc:mysql://localhost:3306/firstfloor";
        Connection conn = DriverManager.getConnection(connectionUrl, "root", "toor");
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);

    }

    public static void drop(String query) throws SQLException {
        String connectionUrl = "jdbc:mysql://localhost:3306/firstfloor";
        Connection conn = DriverManager.getConnection(connectionUrl, "root", "toor");
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);

    }


    public static ArrayList<String> callByDate(Date start, Date end, String id) throws SQLException {
//        String skStart=id+start;
//        String skEnd=id+end;

        ArrayList<String> result = new ArrayList<>();

        String strS=String.valueOf(start);
        String strE=String.valueOf(end);

        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> datesKey = new ArrayList<>();

        String query= """
                select distinct * from\s
                (select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from
                 (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0,
                 (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1,
                 (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2,
                 (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3,
                 (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v
                where selected_date between """+"'"+strS+"'"+" and "+"'"+strE+"'";



        String connectionUrl = "jdbc:mysql://localhost:3306/firstfloor";
        Connection conn = DriverManager.getConnection(connectionUrl, "root", "toor");
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        int i=0;
        while (rs.next()) {
            dates.add(rs.getString(1));
            datesKey.add(id+rs.getString(1));
        }


        drop("DROP TABLE javatable;");

        String crTable="CREATE TABLE javatable(sk VARCHAR(20))";
        createTable(crTable);
        recursiveInsert(datesKey);


//        drop("DROP TABLE con;");

        String createCon="CREATE TABLE con (sk varchar(255));";
        createTable(createCon);

        String ins="insert into con (sk)select  concat(id,date) as sk from firstfloor.status_table;";
        runNoResult(ins);

        String selecthist="select 'live.status_chair', case when live.sk is null then 'free' else 'busy' end as 'status_chair' from firstfloor.con as hist left join firstfloor.javatable live on hist.sk = live.sk group by hist.sk";

        result = gethist(selecthist);

        drop("DROP TABLE con;");





        return result;
    }

    public static void runNoResult(String query) throws SQLException {
        String connectionUrl = "jdbc:mysql://localhost:3306/firstfloor";
        Connection conn = DriverManager.getConnection(connectionUrl, "root", "toor");
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);

    }

    public static ArrayList gethist(String query){
        ArrayList<String> data = new ArrayList<>();

        String connectionUrl = "jdbc:mysql://localhost:3306/firstfloor";
        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "toor");
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                data.add(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return data;
    }





}
