package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/nome_do_banco";
    private static final String USER = "seu_usuario";
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    
    private DatabaseConnection() {
        
    }

    public static Connection getConnection() throws SQLException {
        if (PASSWORD == null) {
            throw new SQLException("Senha do banco de dados não definida na variável de ambiente db_password");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}