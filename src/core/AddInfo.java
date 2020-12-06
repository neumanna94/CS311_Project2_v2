package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddInfo {
    Scanner keyInput = new Scanner(System.in);
    Updates menuObj = new Updates();
    //***********ALL ADD FUNCTIONS **********************
    public void runAdd(Connection conn)
    {
        try {
            boolean done = false;
            do {
                //Print MENU for INSERT / DELETE / UPDATE
                menuObj.printOptionMenu("ADD");
                System.out.print("Type in your option: ");
                System.out.flush();
                String ch = readLine();
                System.out.println();
                switch (ch.charAt(0)) {
                    case '1':
                        addEquiptment(conn);
                        break;
                    case '2':
                        addService(conn);
                        break;
                    case '3':
                        addCustomerInfo(conn);
                        break;
                    case '4':
                        addEmployeeInfo(conn);
                        break;
                    case '5':
                        done = true;
                        System.out.println("Returning to Previous Menu....");
                        break;
                    default:
                        System.out.println("NOT VALID OPTION!!!");
                } //switch
            } while (!done);

        }catch (SQLException ex) {
            System.out.println(ex); }
    }
    private void addEquiptment(Connection conn) throws SQLException
    {
        //Get BrandName, Description, PurchasePrice(double), Type,
        String brandName, description, type;
        double price;
        int maxID = 0;
        System.out.print("New Equiptment Brand Name: ");
        brandName = readLine();
        System.out.print("New Equiptment Description: ");
        description = readLine();
        System.out.print("New Equiptment Purchase Price: ");
        try {
            price = keyInput.nextDouble();
        }catch(Exception e)
        {
            System.out.println("Input Invalid Back to Option Menu!");
            keyInput.next();
            return;
        }
        System.out.print("New Equiptment Type: ");
        type = readLine();

        /*brandName = "Good Year"; description = "I am very lazy to think of a good Description";
        type = "dryer"; price = 998.99;
        System.out.print("New Equiptment Brand Name: " + brandName);
        System.out.print("New Equiptment Description: " + description);
        System.out.print("New Equiptment Purchase Price: " + price);
        System.out.print("New Equiptment Type: " + type);*/

        String query = "SELECT MAX(InventoryID) FROM Inventory";
        Statement selectStatement = conn.createStatement();
        ResultSet rset = selectStatement.executeQuery(query);
        if(rset.next())
        {
            maxID = rset.getInt(1); maxID++;
            System.out.println("MaxID: " + maxID);
            String query1 = "INSERT INTO Inventory(InventoryID, InventoryCategory) VALUES ("+ maxID + ", 'X')";
            int result = selectStatement.executeUpdate(query1);

            String query2 = "INSERT INTO Equipment (EquipmentID, InventoryID, BrandName, Description, PurchaseDate, PurchasePrice, Type, HoursUsed, EquipmentMaintenanceScheduleID) SELECT MAX(EquipmentID) + 1, "+ maxID +", '"+ brandName + "', '" + description + "', CURDATE(), " + price + ", '" + type + "', 0, 2 FROM Equipment";
            int resu = selectStatement.executeUpdate(query2);
            System.out.println("Result: " + resu);
        }
    }
    private void addService(Connection conn) throws SQLException
    {

        //Get ServiceName, Description, RateCharge, Duration
        String serviceName, description, duration;
        double rateCharge;
        System.out.print("New Service Name: ");
        serviceName = readLine();
        System.out.print("New Service Description: ");
        description = readLine();
        System.out.print("New Service Rate Charge: ");
        try {
            rateCharge = keyInput.nextDouble();
        }catch(Exception e)
        {
            System.out.println("Input Invalid Back to Option Menu!");
            keyInput.next();
            return;
        }
        System.out.print("New Service Duration: ");
        duration = readLine();
        /*System.out.print("New Service Name: " + serviceName);
        System.out.print("New Service Description: " + description);
        System.out.print("New Service Rate Charge: " + rateCharge);
        System.out.print("New Service Duration: " + duration);*/

        String query = "INSERT INTO Service SELECT MAX(ServiceID) + 1, '" + serviceName + "', '" + description + "', " + rateCharge +", '" + duration + "' FROM Service";
        Statement selectStatement = conn.createStatement();
        int rset = selectStatement.executeUpdate(query);
        if(rset > 0)
        {
            System.out.println("New Service Added.");
        }
        else
        {
            System.out.println("Fail to Add New Service!");
        }
    }
    private void addCustomerInfo(Connection conn) throws SQLException
    {
        //Get FirstName, LastName, Address, Email, Phone, Balance
        String firstName, lastName, address, email, phone;
        double balance;
        System.out.print("New Customer First Name: ");
        firstName = readLine();
        System.out.print("New Customer Second Name: ");
        lastName = readLine();
        System.out.print("New Customer Address: ");
        address = readLine();
        System.out.print("New Customer Email: ");
        email = readLine();
        System.out.print("New Customer Phone Number: ");
        phone = readLine();
        System.out.print("New Customer Balance: ");
        try {
            balance = keyInput.nextDouble();
        }catch(Exception e)
        {
            System.out.println("Input Invalid Back to Option Menu!");
            keyInput.next();
            return;
        }
        String query = "INSERT INTO Customer SELECT MAX(CustomerID) + 1, '" + firstName + "', '" + lastName + "', '" + address + "', '" + email + "', '" + phone + "', " + balance + " FROM Customer";
        Statement selectStatement = conn.createStatement();
        int rset = selectStatement.executeUpdate(query);
        if(rset > 0)
        {
            System.out.println("New Customer Added.");
        }
        else
        {
            System.out.println("Fail to Add New Customer!");
        }
    }
    private void addEmployeeInfo(Connection conn) throws SQLException
    {
        //Get FirstName, LastName, Address, Gender, Position
        String firstName, lastName, address, gender;
        int position;
        System.out.print("New Employee First Name: ");
        firstName = readLine();
        System.out.print("New Employee Second Name: ");
        lastName = readLine();
        System.out.print("New Employee Address: ");
        address = readLine();
        System.out.print("New Employee Gender: ");
        gender   = readLine();
        System.out.print("New Employee Position: ");
        try {
            position = keyInput.nextInt();
        }catch(Exception e)
        {
            System.out.println("Input Invalid Back to Option Menu!");
            keyInput.next();
            return;
        }
        String query = "INSERT INTO Employee SELECT MAX(EmployeeID) + 1, '" + firstName + "', '" + lastName + "', '" + address + "', '" + gender + "', CURDATE(), " + position + " FROM Employee;";
        Statement selectStatement = conn.createStatement();
        int rset = selectStatement.executeUpdate(query);
        if(rset > 0)
        {
            System.out.println("New Employee Added.");
        }
        else
        {
            System.out.println("Fail to Add New Employee!");
        }
    }

    private static String readLine() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr, 1);
        String line = "";

        try {
            line = br.readLine();
        } catch (IOException e) {
            System.out.println("Error in SimpleIO.readLine: " +
                    "IOException was thrown");
            System.exit(1);
        }
        return line;
    }
}
