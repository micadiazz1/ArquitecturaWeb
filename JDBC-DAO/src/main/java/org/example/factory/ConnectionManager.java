package org.example.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class ConnectionManager {
    private Connection conn;
    private static volatile ConnectionManager instance;

    private ConnectionManager() {
        try {
            String url = "jdbc:mysql://localhost:3307/mydbDAO";
            String user = "root";
            String password = "123";
            // Establecer la conexión
            this.conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida correctamente con MySQL.");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos.");
            e.printStackTrace();
        }
    }

    public static ConnectionManager getInstance() {
        if (instance == null) { // 1er chequeo: Evita bloquear si ya existe la instancia.
            synchronized (ConnectionManager.class) { // Bloque sincronizado: Asegura que solo un hilo cree la instancia en caso de concurrencia.
                if (instance == null) { // 2do chequeo Confirma que instance sigue siendo null antes de crearla.
                    instance = new ConnectionManager();
                }
            }
        }
        return instance;
    }
    public Connection getConnection() {
        return conn;
    }


}
