package app;

import db.DB;
import db.dbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {

    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection conn = null;

        PreparedStatement ps = null;

        try {
            conn = DB.getConn();

            /*
            ps = conn.prepareStatement(
                    "INSERT INTO seller " +
                            "(name, email, birthdate, baseSalary, departmentID)" +
                            "VALUES " +
                            "(?, ?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, "Beatriz Santos");
            ps.setString(2, "beatriz@gmail.com");
            ps.setDate(3, new java.sql.Date(
                    sdf.parse("22/04/1995").getTime()));
            ps.setDouble(4, 3000.0);
            ps.setInt(5, 4);
             */

            ps = conn.prepareStatement("insert into department (name) values" +
                    "('D1'), ('D2')",
                    Statement.RETURN_GENERATED_KEYS);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done! ID = " + id);
                }
            }
            else {
                System.out.println("No rows affected.");
            }

        }
        catch (SQLException /*| ParseException*/ e) {
            throw new dbException(e.getMessage());
        }
        finally {
            closeStatement(ps);
            DB.closeConn();
        }

    }

    private static void closeStatement(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
