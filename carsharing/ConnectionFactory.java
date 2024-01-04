package carsharing;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

    public void getConnection() {
        // Show path to database
        final String JDBC_Driver = "org.h2.Driver";
        final String dataBaseName = "carsharing";
        final String dataBaseURL = "jdbc:h2:./src/carsharing/db/" + dataBaseName;

        try {
            // Try to open the connection to your database
            getConnection.connection = DriverManager.getConnection(dataBaseURL);
            getConnection.connection.setAutoCommit(true);
            // Register your driver
            Class.forName(JDBC_Driver);

            // A Statement is an interface that represents a SQL statement.
            Statement createSQLStatement = getConnection.connection.createStatement();

            // Make your tables, but only when it doesn't exist yet
            String createCompanyTable = "CREATE TABLE IF NOT EXISTS COMPANY " +
                    "(ID INT PRIMARY KEY AUTO_INCREMENT" + ",NAME VARCHAR UNIQUE NOT NULL" + ")";
            String createCarTable = "CREATE TABLE IF NOT EXISTS CAR " +
                    "(ID INT PRIMARY KEY AUTO_INCREMENT" + ",NAME VARCHAR UNIQUE NOT NULL" +
                    ",COMPANY_ID INT NOT NULL" +
                    ",CONSTRAINT FK_COMPANY FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID)" + ")";
            String createCustomerTable = "CREATE TABLE IF NOT EXISTS CUSTOMER " +
                    "(ID INT PRIMARY KEY AUTO_INCREMENT" + ",NAME VARCHAR UNIQUE NOT NULL" +
                    ",RENTED_CAR_ID INT DEFAULT NULL" +
                    ",CONSTRAINT FK_CAR FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR(ID)" + ")";

            // Executes the statement
            createSQLStatement.executeUpdate(createCompanyTable);
            createSQLStatement.executeUpdate(createCarTable);
            createSQLStatement.executeUpdate(createCustomerTable);


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
