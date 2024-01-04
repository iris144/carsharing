package carsharing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    private static PreparedStatement preparedStatement;

    private static final String getAllCars = "SELECT * FROM CAR";
    private static final String insertCarName = "INSERT INTO CAR (NAME, COMPANY_ID) VALUES (?, ?);";

    public static List<Car> findAll() {
        ArrayList<Car> cars = new ArrayList<>();
        ResultSet resultSet;

        try {
            preparedStatement = getConnection.connection.prepareStatement(getAllCars);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("company_id"));
                cars.add(car);
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return cars;
    }

    public static List<Car> findAllCarsForCustomers() {

        return null;
    }

    public static void add(String name, int id) {
        for (Car car : findAll()) {
            if (car.getName().equals(name)) {
                System.out.println("Car with that name already exists!");
                return;
            }
        }
        try {
            preparedStatement = getConnection.connection.prepareStatement(insertCarName);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The car was added!\n");
    }
}
