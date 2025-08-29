package org.example.repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.example.Dao.PersonaDAO;
import org.example.Persona;
import org.example.factory.DAOfactory;

public class PersonaDAOMySQL  implements PersonaDAO {


    private final Connection conn;

    public PersonaDAOMySQL(Connection con) {
        this.conn = con;
    }
    @Override
    public List<Persona> getAllPersonas() {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Persona persona = new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("edad")
                );
                personas.add(persona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }

    @Override
    public Persona getPersonaById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("edad")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertarPersona(Persona persona) {
        String sql = "INSERT INTO users(nombre, apellido, edad) VALUES (?, ?, ?)";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setInt(3, persona.getEdad());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarPersona(Persona persona) {
        String sql = "UPDATE users SET nombre=?, apellido=?, edad=? WHERE id=?";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setInt(3, persona.getEdad());
            stmt.setInt(4, persona.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarPersona(int id) {
        String sql = "DELETE FROM users WHERE id=?";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
