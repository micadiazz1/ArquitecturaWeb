package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/mydb";
        String user = "root";
        String password = "123";

        try(Connection conn = DriverManager.getConnection(url, user, password)){
            System.out.println("Connected");

            addUser(conn,"Micaela","Diaz",24);

        }catch (SQLException e){
            System.out.println("Error al conectar a la base de datos");
            e.printStackTrace();
        }

    }

    private static void addUser(Connection conn, String nombre, String apellido, int edad)throws SQLException {
        String insert = "Insert into users (nombre, apellido, edad) values (?, ?, ?)";
        PreparedStatement stm = conn.prepareStatement(insert);
        stm.setString(1,nombre);
        stm.setString(2,apellido);
        stm.setInt(3,edad);
        stm.executeUpdate();
        System.out.println("Usuario agregado");

    }
}
