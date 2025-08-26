package org.example;

import org.example.Dao.PersonaDAO;
import org.example.Dao.PersonaDAOMySQL;
import org.example.Service.PersonaService;


public class Main {
    public static void main(String[] args) {
        PersonaDAO personaDAO = new PersonaDAOMySQL();
        PersonaService service = new PersonaService(personaDAO);
        Persona persona1 = new Persona(1,"Lulox","dj",28);
        Persona persona2 = new Persona(2,"TGomaz","dj",20);
        service.insertarPersona(persona1);
        service.insertarPersona(persona2);
        System.out.println("Personas en la BD:");
        for (Persona p : service.listarTodas()) {
            System.out.println(p);
        }
    }
}