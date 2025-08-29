package org.example;

import org.example.Dao.PersonaDAO;

import org.example.factory.DAOfactory;
import org.example.repository.PersonaDAOMySQL;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        DAOfactory factory = DAOfactory.getIntence(1);
        Persona nuevaPersona = new Persona(2, "DJ", "Lulox", 43);
        factory.insertarPersona(nuevaPersona);
        System.out.println("Persona insertada correctamente.");
        List<Persona> personas = factory.getAllPersonas();
        for (Persona p : personas) {
            System.out.println(p.getId() + " - " + p.getNombre() + " " + p.getApellido() + " (" + p.getEdad() + ")");
        }

    }
}