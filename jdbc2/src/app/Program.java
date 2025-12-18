package app;

import db.DB;
import db.dbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {

    public static void main(String[] args) {

        Connection conn = null;

        Statement st = null;

        ResultSet rs = null;

        try {
            conn = DB.getConn();

            st = conn.createStatement();

            rs = st.executeQuery("SELECT * FROM department");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + ", " +
                        rs.getString("name"));
            }

        }
        catch (SQLException sql) {
            throw new dbException(sql.getMessage());
        }
        finally {
            closeStatement(st);
            closeResultSet(rs);
            DB.closeConn();
        }

    }

    public static void closeStatement(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        }
        catch (SQLException sql) {
            throw new dbException(sql.getMessage());
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        }catch (SQLException sql) {
            throw new dbException(sql.getMessage());
        }
    }

}
