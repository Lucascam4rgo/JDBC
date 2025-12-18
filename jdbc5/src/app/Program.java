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

        PreparedStatement ps = null;

        try {

            conn = DB.getConn();

            ps = conn.prepareStatement(
                    "DELETE FROM department "
                        + "WHERE "
                        + "id = ?"
                    );

            ps.setInt(1, 2);

            int rowsAffected = ps.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);

        }
        catch (SQLException sql) {
            throw new dbIntegrityException("Erro de integridade referencial: " + sql.getMessage());
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
