package org.example;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {

        String uri = "jdbc:derby:MyDerbyDb;create=true";
        String sql = "CREATE TABLE persona (" +
                "id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                "nombre VARCHAR(50) NOT NULL, " +
                "apellido VARCHAR(50) NOT NULL, " +
                "edad INT NOT NULL" +
                ")";
        try (Connection conn = DriverManager.getConnection(uri);
             Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            addPerson(conn,"Soledad","Gomez",30);
            addPerson(conn,"Luciano","Tourn",26);
            addPerson(conn,"Juan","Miguel",28);
            deletePerson(conn,1);


        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private static void addPerson(Connection conn, String nombre, String apellido, int edad) throws SQLException {
        String insert = "INSERT INTO persona (nombre, apellido, edad) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(insert)) {
            // Asignar valores a los parámetros
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setInt(3, edad);

            // Ejecutar la consulta de inserción
            ps.executeUpdate();
            System.out.println("Persona agregada exitosamente: " + nombre + " " + apellido);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void deletePerson(Connection conn, int id) throws SQLException {
        String delete = "DELETE FROM persona WHERE id = ?";
        PreparedStatement ps= conn.prepareStatement(delete);
        ps.setInt(1,id);

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Persona con ID " + id + " eliminada exitosamente.");
        } else {
            System.out.println("No se encontró una persona con el ID: " + id);
        }
    }

    private static void updatePerson(Connection conn, int id, String nombre, String apellido, int edad) throws SQLException {
        String update= "UPDATE persona set nombre = ?, apellido = ?, edad = ? WHERE id = ?";
        PreparedStatement ps= conn.prepareStatement(update);
        ps.setString(1,nombre);
        ps.setString(2, apellido);
        ps.setInt(3, edad);
        ps.setInt(4, id);
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Persona actualizada exitosamente.");
        }else {
            System.out.println("No se encontro una persona con el ID: " + id);
        }
    }

}