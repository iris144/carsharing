package carsharing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private static PreparedStatement preparedStatement;

    private static final String getAllCustomers = "SELECT * FROM CUSTOMER";
    private static final String insertCustomerName = "INSERT INTO CUSTOMER (NAME) VALUES (?);";
    //private final String getAvailableCarsByCompany = "SELECT * FROM car WHERE company_id = ? "
    //       + "AND NOT EXISTS (SELECT * FROM customer WHERE rented_car_id = car.id)";


    public static List<Customer> findAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        ResultSet resultSet;

        try {
            preparedStatement = getConnection.connection.prepareStatement(getAllCustomers);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("rented_car_id"));
                customers.add(customer);
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return customers;
    }


    public static void add(String name) {
        for (Customer customer : findAll()) {
            if (customer.getName().equals(name)) {
                System.out.println("Customer with that name already exists!");
                return;
            }
        }
        try {
            preparedStatement = getConnection.connection.prepareStatement(insertCustomerName);
            preparedStatement.setString(1, name);
           // preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The customer was added!\n");
    }
}
