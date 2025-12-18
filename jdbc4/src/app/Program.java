package app;

import db.DB;
import db.dbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {

    public static void main(String[] args) {

        Connection conn = null;

        PreparedStatement ps = null;

        try {

            conn = DB.getConn();

            ps = conn.prepareStatement(
                    "UPDATE seller "
                        + "SET baseSalary = baseSalary + ? "
                        + "WHERE "
                        + "(departmentID = ?)"
            );

            ps.setDouble(1, 500.0);
            ps.setInt(2, 2);

            int rowsAffected = ps.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);

        }
        catch (SQLException sql) {
            sql.printStackTrace();
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
