package Conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sistema-de-gerenciamento-de-funcionarios";
        String user = "root";
        String password = "16022003";

        return DriverManager.getConnection(url, user, password);
    }
}
