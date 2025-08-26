package org.example.Dao;

import org.example.Persona;

import java.util.List;

public interface PersonaDAO {
    List<Persona> getAllPersonas();
    Persona getPersonaById(int id);
    void insertarPersona(Persona persona);
    void actualizarPersona(Persona persona);
    void eliminarPersona(int id);
}