package org.example.repository;

import org.example.Persona;
import org.example.factory.ConnectionManager;
import org.example.factory.DAOfactory;

import java.util.List;

public class MysqlDAOfactory extends DAOfactory {

    private PersonaDAOMySQL getDAO(){
        return new PersonaDAOMySQL(ConnectionManager.getInstance().getConnection());
    }

    @Override
    public List<Persona> getAllPersonas() {
        return getDAO().getAllPersonas();
    }

    @Override
    public Persona getPersonaById(int id) {
        return getDAO().getPersonaById(id);
    }
    @Override
    public void insertarPersona(Persona persona) {
        getDAO().insertarPersona(persona);
    }

    @Override
    public void actualizarPersona(Persona persona) {
        getDAO().actualizarPersona(persona);
    }

    @Override
    public void eliminarPersona(int id) {
        getDAO().eliminarPersona(id);
    }
}
