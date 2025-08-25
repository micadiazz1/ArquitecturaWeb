package org.example;

import java.sql.*;

public class Select {
    public static void main(String[] args) throws SQLException {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";

        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String uri = "jdbc:derby:MyDerbyDb;create=true";
        try {
            Connection conn = DriverManager.getConnection(uri);
            String sql = "select * from persona";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                System.out.println("id: " + id + " nombre: " + nombre + " apellido: " + apellido);
            }
            conn.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
