/*
 *@author Mac DeCourcy
 * TODO: Dynamic response method
 */
package core;

import menu.*;
import updates.Updates;
import utils.*;
import data.User;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static Menu menu = new Menu(0);

    public static void main(String[] args){
        populateMenu();
        menu.menuLoop();
        System.exit(0);
    }

    private static User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        try {
            user.setId(rs.getInt("userID"));
            user.setName(rs.getString("Username"));
            user.setPass(rs.getString("Password"));
        }catch (SQLException ex){
            System.out.println("User or pass incorrect");
            ex.printStackTrace();
            return null;
        }
        return user;

    }

    public static ResultSet queryToResultSet(Queries query) throws SQLException {
        ConnectionManager connector = new ConnectionManager();
        Connection connection = connector.createConnection();
        String queryString = query.getString();
        ResultSet rset;
        try {
            PreparedStatement ps = connection.prepareStatement(queryString);
            rset = ps.executeQuery();
            if(rset.equals(null))
            {
                System.out.println("No results found...");
            }

        } catch (SQLException e) {
            System.out.println("[ERROR] QUERY COULD NOT BE EXECUTED ");
            while (e != null) {
                System.out.println("Message :" + e.getMessage());
                e = e.getNextException();
            }
            connection.close();
            return null;
        }
      
        return rset;
    }

    public static void populateMenu(){

        System.out.println("CLEAN N GO MENU");

        Menu equipAndSupplies = new Menu(1);
        Menu custAndServices = new Menu(1);
        Menu employees = new Menu(1);
        Menu updates = new Menu(1);
        Menu debug = new Menu(1);
        Menu cust_analyze = new Menu(2);
        Menu cust_services = new Menu(2);
        Menu cust_cust = new Menu(2);
        Menu updates_insert = new Menu(2);
        Menu updates_delete = new Menu(2);
        Menu updates_update = new Menu(2);


        equipAndSupplies.add(new MenuOption("1", "Add new equipment") {
            @Override
            public void doAction() throws SQLException {

            }
        });
        equipAndSupplies.add(new MenuOption("2", "Add new supply") {
            @Override
            public void doAction() {

            }
        });
        equipAndSupplies.add(new MenuOption("3", "View Inventory") {
            @Override

            public void doAction() {}
        });
        equipAndSupplies.add(new MenuOption("0", "Quit") {
            @Override

            public void doAction() {}
        });
        custAndServices.add(new MenuOption("1", "Analyze the progress of the business") {
            @Override
            public void doAction() {

                cust_analyze.menuLoop();
            }
        });
        cust_analyze.add(new MenuOption("1", "Total number of new customers")
        {
            public void doAction() throws SQLException {
                System.out.println("Total number of new customers this year: ");
                ResultSet resultSet = queryToResultSet(Queries.SELECT_NEW_CUSTOMERS_YEARLY);
                PrintManager printManager = new PrintManager();
                printManager.printColumns(resultSet);
            }
        });
        cust_analyze.add(new MenuOption("2", "Total number of service transactions")
        {
            public void doAction() throws SQLException {
                System.out.println("Total number of service transactions this month: ");
                ResultSet resultSet = queryToResultSet(Queries.SELECT_SERVICE_TRANSACTION_COUNT_MONTH);
                PrintManager printManager = new PrintManager();
                printManager.printNColumns(resultSet);

            };
        });
        custAndServices.add(new MenuOption("2", "Services") {
            @Override
            public void doAction() {
                cust_services.menuLoop();
            }
        });
        cust_services.add(new MenuOption("1", "Most requested service")
        {
            public void doAction() throws SQLException {
                System.out.println("The most used service this month was: ");
                ResultSet resultSet = queryToResultSet(Queries.SELECT_MOST_USED_SERVICE);
                PrintManager printManager = new PrintManager();
                printManager.printNColumns(resultSet);
            }
        });
        cust_services.add(new MenuOption("2", "Service Transactions")
        {
            @Override
            public void doAction() throws SQLException {
                System.out.println("Total number of service transactions this month: ");
                ResultSet resultSet = queryToResultSet(Queries.SELECT_SERVICE_TRANSACTION_COUNT_MONTH);
                PrintManager printManager = new PrintManager();
                printManager.printNColumns(resultSet);

            };
        });
        cust_services.add(new MenuOption("3", "Annual revenues from services")
        {
            @Override
            public void doAction() throws SQLException {
                System.out.println("Annual revenue: ");
                ResultSet resultSet = queryToResultSet(Queries.SELECT_ANNUAL_REVENUE_SERVICE_TO_CUSTOMERS);
                PrintManager printManager = new PrintManager();
                printManager.printNColumns(resultSet);
            };
        });
        custAndServices.add(new MenuOption("3", "Customers") {
            @Override
            public void doAction() {cust_cust.menuLoop();}
        });

        cust_cust.add(new MenuOption("1", "Customer list for a service")
        {
            @Override
            public void doAction() throws SQLException {

                ResultSet resultSet = queryToResultSet(Queries.SELECT_NEW_CUSTOMERS_MONTH_DISTRIBUTION);
                PrintManager printManager = new PrintManager();
                printManager.printNColumns(resultSet);
            };
        });
        cust_cust.add(new MenuOption("2", "Customer number")
        {
            @Override
            public void doAction() throws SQLException {
                System.out.println("Enter a name of a service: ");

                Scanner sc = new Scanner(System.in);
                String in = sc.next();

                ResultSet resultSet = queryToResultSet(Queries.SELECT_NEW_CUSTOMERS_MONTH_DISTRIBUTION);
                PrintManager printManager = new PrintManager();
                printManager.printNColumns(resultSet);
            };
        });

        custAndServices.add(new MenuOption("0", "Quit") {
            @Override
            public void doAction() {
                System.out.println("Exiting...");
            }
        });
        //Display Schedules calls code to display schedule based on employeeID input.
        employees.add(new MenuOption("1", "Display Schedules"){
            @Override
            public void doAction() throws SQLException {
                System.out.println("Name,\t\tDate,\t\tStart Time,\t  Duration");
                ResultSet resultSet = queryToResultSet(Queries.SELECT_EMPLOYEE_SCHEDULES);
                PrintManager printManager = new PrintManager();
                printManager.printNColumns(resultSet);
            };
        });
        //Quit employee menu.
        employees.add(new MenuOption("0", "Quit"){
            @Override
            public void doAction(){};
        });

        //Calls the add information menu
        updates.add(new MenuOption("3", "Update current information")
        {
            @Override
            public void doAction(){updates_update.menuLoop();};
        });
        updates.add(new MenuOption("0", "Quit")
        {
            @Override
            public void doAction(){};
        });

        debug.add(new MenuOption("1", "Test conn") {
            @Override
            public void doAction() throws SQLException {
            }
        });

        debug.add(new MenuOption("2", "Test login") {
            @Override
            public void doAction() throws SQLException, IOException {
                LoginManager loginManager = new LoginManager();

                User x = loginManager.getUserPass();
                User user = loginManager.getUserByUserNameAndPassword(x.getName(), x.getPass());
                if(user == null)
                    System.out.println("user pass incorrect try again");
                else
                    System.out.println("Great success");
            }
        });


        menu.add(new MenuOption("1","Equipment & Supplies") {
            @Override
            public void doAction() {
                equipAndSupplies.menuLoop();
            }
        });

        menu.add(new MenuOption("2","Customer & Services") {
            @Override
            public void doAction() {
                custAndServices.menuLoop();
            }
        });
        menu.add(new MenuOption("3","Employees") {
            @Override
            public void doAction() {
                employees.menuLoop();
            }
        });
        menu.add(new MenuOption("4","Updates") {
            @Override
            public void doAction() {

                ConnectionManager connObj = new ConnectionManager();
                Connection conn = connObj.createConnection();
                Updates update = new Updates();
                update.run(conn);
                //updates.menuLoop();
            }
        });

    }
}
