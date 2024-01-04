package carsharing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CompanyDAO {
    private static PreparedStatement preparedStatement;

    private static final String getAllCompanies = "SELECT * FROM COMPANY";
    private static final String insertCompanyName = "INSERT INTO COMPANY (NAME) VALUES (?);";


    public static List<Company> findAll() {
        ArrayList<Company> companies = new ArrayList<>();
        ResultSet resultSet;

        try {
            preparedStatement = getConnection.connection.prepareStatement(getAllCompanies);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Company company = new Company(resultSet.getInt("id"), resultSet.getString("name"));
                companies.add(company);
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return companies;
    }


    public static void add(String name) {
        for (Company company : findAll()) {
            if (company.getName().equals(name)) {
                System.out.println("Company with that name already exists!");
                return;
            }
        }
        try {
            preparedStatement = getConnection.connection.prepareStatement(insertCompanyName);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The company was created!\n");
    }
}