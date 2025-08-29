package org.example.factory;

import org.example.Dao.PersonaDAO;
import org.example.Persona;
import org.example.repository.MysqlDAOfactory;

import java.sql.Connection;
import java.util.List;

public abstract class DAOfactory {
    public static final int MYSQL = 1;
    private static volatile DAOfactory instance;

    public static DAOfactory getIntence(int db) {
        if (instance == null) {
            synchronized (DAOfactory.class) {
                if (instance == null) {
                    switch (db) {
                        case MYSQL:
                            instance = new MysqlDAOfactory();
                            break;
                        default:
                            throw new RuntimeException("Unknown database type");
                    }

                }
            }


        }
        return instance;
    }
    public abstract List<Persona> getAllPersonas();
    public abstract Persona getPersonaById(int id);
    public abstract void insertarPersona(Persona persona);
    public abstract void actualizarPersona(Persona persona);
    public abstract void eliminarPersona(int id);

}
