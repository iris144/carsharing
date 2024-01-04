package carsharing;


import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {


        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.getConnection();

        Menu menu = new Menu();
        menu.startMenu();

        getConnection.connection.close();
        System.exit(0);
    }
}