package org.example.Service;

import org.example.Dao.PersonaDAO;
import org.example.Persona;

import java.util.List;

public class PersonaService {
    private final PersonaDAO personaDAO;

    public PersonaService(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }

    public List<Persona> listarTodas() {
        return personaDAO.getAllPersonas();
    }
    public void insertarPersona(Persona persona) {
        personaDAO.insertarPersona(persona);
    }
}
