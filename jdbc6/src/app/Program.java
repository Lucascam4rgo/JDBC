package app;

import db.DB;
import db.dbException;
import db.dbIntegrityException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {

    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;

        try {
            conn = DB.getConn();

            conn.setAutoCommit(false);

            st = conn.createStatement();

            int rows1 = st.executeUpdate(
                    "UPDATE seller SET " +
                            "baseSalary = 2090 " +
                            "WHERE departmentID = 1");

//            int x = 1;

//            if (x < 2) {
//                throw new SQLException("Fake error");
//            }

            int rows2 = st.executeUpdate("UPDATE seller SET " +
                    "baseSalary = 3090 " +
                    "WHERE departmentID = 2");

            conn.commit();

            System.out.println("rows1 = " + rows1);
            System.out.println("rows2 = " + rows2);

        }
        catch (SQLException sql) {
            try {
                conn.rollback();
                throw new dbException("Transaction rolled back! Caused by: "
                        + sql.getMessage());
            }
            catch (SQLException sql1) {
                throw new dbException("Error trying to rollback! Caused by: "
                        + sql1.getMessage());
            }
        }
        finally {
            closeStatement(st);
            DB.closeConn();
        }

    }

    private static void closeStatement(Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
