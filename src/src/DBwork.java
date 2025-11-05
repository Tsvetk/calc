package src;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DBwork implements IWorkResult {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    DBwork(String file) {

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + file);
            statmt = conn.createStatement();
        } catch (SQLException err) {
            System.out.println("Coon err " + err.toString());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void close() {
        try {
            statmt.close();
            conn.close();
        } catch (SQLException err) {
            System.out.println("Coon err " + err.toString());
        }
    }

    @Override
    public void write(String file, String data) {
        String[] data_s = data.split(" ");
//        String query = String.format( "INSERT INTO 'Numbers' ('Number_A', 'Number_B' ,'Action') VALUES (%s, %s ,'%s');", data_s) ;

        try //(ResultSet resSet = statmt.executeQuery(query))
        {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO 'Numbers' ('Number_A', 'Number_B' ,'Action') VALUES (?, ? , ?);");
            pstmt.setDouble(1, Double.parseDouble(data_s[0]));
            pstmt.setDouble(2, Double.parseDouble(data_s[1]));
            pstmt.setString(3, data_s[2]);
            pstmt.executeUpdate();
//            System.out.println(" Write to db : " + pstmt.toString());
        } catch (SQLException err) {
            System.out.println("Coon err " + err.toString());
        }
    }

    public void write_Number(String fileName, List<Number> num) {
        for (Number item : num) {
            String str = item.a + " " + item.b + " " + item.act;
            this.write(fileName, str);
        }
    }

    public void truncate(String table) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM " + table + ";" +
                    "UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = '" + table + "';" +
                    "VACUUM;");
            pstmt.executeUpdate();
            System.out.println("Truncate table " + table);
        } catch (SQLException err) {
            System.out.println("Coon err " + err.toString());
        }

    }

    @Override
    public List<String> read(String file) {
        ArrayList line_str = new ArrayList();
        String query = "SELECT * FROM Numbers;";

        try (ResultSet resSet = statmt.executeQuery(query)) {
            while (resSet.next()) {
                Double Number_A = resSet.getDouble("Number_A");
                Double Number_B = resSet.getDouble("Number_B");
                String Action = resSet.getString("Action");
                System.out.println("Number_A = " + Number_A);
                System.out.println("Number_B = " + Number_B);
                System.out.println("Action = " + Action);
                System.out.println();
                line_str.add(Number_A + " " + Number_B + " " + Action);
            }
        } catch (SQLException err) {
            System.out.println("Coon err " + err.toString());
        }
        return line_str;
    }
}
