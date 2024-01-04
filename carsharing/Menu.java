package carsharing;

import java.util.List;
import java.util.Scanner;

public class Menu {
Scanner scanner = new Scanner(System.in);
private static Company currentCompany;
private static Customer currentCustomer;

    public void startMenu() {
        System.out.println("1. Log in as a manager");
        System.out.println("2. Log in as a customer");
        System.out.println("3. Create a customer");
        System.out.println("0. Exit");
        String action = scanner.nextLine();

        switch (action) {
            case "1":
                logInAsManager();
                break;
            case "2":
                logInAsCustomer();
                break;
            case "3":
                createCustomer();
                break;
            case "0":
                break;
            default:
                System.out.println("Sorry, that is incorrect input. Please look at what you have to do.");
                break;
        }
    }
    public void logInAsManager(){
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
        String action = scanner.nextLine();
        System.out.println();
        switch (action) {
            case "1":
                companyList();
                break;
            case "2":
                createCompany();
                break;
            case "0":
                startMenu();
                break;
            default:
                System.out.println("Sorry, that is incorrect input. Please look at what you have to do.");
                break;
        }
    }

    public void logInAsCustomer() {
        List<Customer> customerList = CustomerDAO.findAll();
        if (customerList.isEmpty()) {
            System.out.println("The customer list is empty!\n");
            startMenu();
        }

        System.out.println("Choose the customer:");
        int counterID = 1;
        for (Customer customer : customerList) {
            System.out.printf("%d. %s\n", counterID++, customer.getName());
        }
        System.out.println("0. Back");

        // Now you can choose one of the customers
        try {
            String menuInput = scanner.nextLine();
            if ("0".equals(menuInput)) {
                startMenu();
            } else {
                int menuInputIndex = 0;
                try {
                    menuInputIndex = Integer.parseInt(menuInput);
                } catch (NumberFormatException ex) {
                    System.out.println("Error");
                }
                if (menuInputIndex > 0 && menuInputIndex <= counterID) {
                    currentCustomer = customerList.get(menuInputIndex - 1);
                    customerMenu();
                } else {
                    System.out.println("Error");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void customerMenu() {
        System.out.println("1. Rent a car");
        System.out.println("2. Return a rented car");
        System.out.println("3. My rented car");
        System.out.println("0. Back");
        String action = scanner.nextLine();
        System.out.println();
        switch (action) {
            case "1":
                companyList();
                break;
            case "2":
                returnRentedCar();
                break;
            case "3":
                myRentedCar();
                break;
            case "0":
                startMenu();
                break;
            default:
                System.out.println("Sorry, that is incorrect input. Please look at what you have to do.");
                break;
        }
    }

    public void rentACar() {

    }

    public void returnRentedCar() {

    }
    public void myRentedCar() {

    }


    public void carMenu() {
        System.out.println("'" + currentCompany.getName() + "' company");
        System.out.println("1. Car list");
        System.out.println("2. Create a car");
        System.out.println("0. Back");
        String action = scanner.nextLine();
        System.out.println();
        switch (action) {
            case "1":
                carList();
                break;
            case "2":
                createCar();
                break;
            case "0":
                logInAsManager();
                break;
            default:
                System.out.println("Sorry, that is incorrect input. Please look at what you have to do.");
                break;
        }
    }

    public void companyList() {
        // Show your list of companies
        List<Company> companyList = CompanyDAO.findAll();
        if (companyList.isEmpty()) {
            System.out.println("The company list is empty!\n");
            logInAsManager();
        }

        System.out.println("Choose the company:");
        int counterID = 1;
        for (Company company : companyList) {
            //companyList.add(counterID, company);
            System.out.printf("%d. %s\n", counterID++, company.getName());
        }
        System.out.println("0. Back");

        // Now you can choose one of the companies
        try {
            String menuInput = scanner.nextLine();
            if ("0".equals(menuInput)) {
                logInAsManager();
            } else {
                int menuInputIndex = 0;
                try {
                    menuInputIndex = Integer.parseInt(menuInput);
                } catch (NumberFormatException ex) {
                    System.out.println("Error");
                }
                if (menuInputIndex > 0 && menuInputIndex <= counterID) {
                    currentCompany = companyList.get(menuInputIndex - 1);
                    carMenu();
                } else {
                    System.out.println("Error");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createCompany() {
        System.out.println("Enter the company name:");
        String input = scanner.nextLine();
        CompanyDAO.add(input);
        logInAsManager();
    }



    public void carList() {
        int counterID = 0;
        for (Car car : CarDAO.findAll()) {
            if (car.getCompany_id() == currentCompany.getId()) {
                counterID += 1;
            }
        }
        if (counterID == 0) {
            System.out.println("The car list is empty!\n");
            carMenu();
        }
        int menuId = 1;
        for (Car car : CarDAO.findAll()) {
            if (car.getCompany_id() == currentCompany.getId()) {
                System.out.println(menuId++ + ". " + car.getName());
            }
        }
        System.out.println();
        carMenu();
    }

    public void createCar() {
        System.out.println("Enter the car name:");
        String input = scanner.nextLine();
        CarDAO.add(input, currentCompany.getId());
        carMenu();
    }

    public void customerList() {

    }
    public void createCustomer() {
        System.out.println("Enter the customer name:");
        String input = scanner.nextLine();
        // if (currentCustomer != null) {
        CustomerDAO.add(input);
        startMenu();
        // }
    }
}
