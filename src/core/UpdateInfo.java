package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

public class UpdateInfo {
    Scanner keyInput = new Scanner(System.in);
    Updates menuObj = new Updates();



    //*********** ALL UPDATE FUNCTIONS ******************************
    public void runUpdate(Connection conn)
    {
        try {
            boolean done = false;
            do {
                //Print MENU for INSERT / DELETE / UPDATE
                menuObj.printOptionMenu("UPDATE");
                System.out.print("Type in your option: ");
                System.out.flush();
                String ch = readLine();
                System.out.println();
                switch (ch.charAt(0)) {
                    case '1':
                        updateEquiptment(conn);
                        break;
                    case '2':
                        updateService(conn);
                        break;
                    case '3':
                        updateCustomerInfo(conn);
                        break;
                    case '4':
                        updateEmployeeInfo(conn);
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
    private void updateEquiptment(Connection conn) throws SQLException
    {
        //Get Equipment ID
        int id;
        System.out.print("UPDATE Equipment ID: ");
        try {
            id = keyInput.nextInt();
        }catch(Exception e)
        {
            System.out.println("Input Invalid Back to Option Menu!");
            keyInput.next();
            return;
        }
        String query = "SELECT * FROM Equipment WHERE EquipmentID = " + id;
        Statement selectStatement = conn.createStatement();
        ResultSet rset = selectStatement.executeQuery(query);
        if(rset.next())
        {
            System.out.println("Equipment ID " + id + " Found.");
            System.out.println("Brand Name: " + rset.getString(3));
            System.out.println("Description: " + rset.getString(4));
            System.out.println("Price: " + rset.getString(6));
            System.out.println("HourUsed: " + rset.getString(8));
            System.out.println("Maintenance: " + rset.getString(9));

            String brandName, description, type;
            double hourUsed;
            int schecule;
            System.out.print("UPDATE Equipment Brand Name: ");
            brandName = readLine();
            System.out.print("UPDATE Equipment Description: ");
            description = readLine();
            System.out.print("UPDATE Equipment Hours Used: ");
            try {
                hourUsed = keyInput.nextDouble();
            }catch(Exception e)
            {
                System.out.println("Input Invalid Back to Option Menu!");
                keyInput.next();
                return;
            }
            System.out.print("UPDATE Equipment Maintenance Schedule: ");
            try {
                schecule = keyInput.nextInt();
            }catch(Exception e)
            {
                System.out.println("Input Invalid Back to Option Menu!");
                keyInput.next();
                return;
            }
            /*String pquery = "UPDATE Equipment SET BrandName = ?, Description = ?, HoursUsed ?, EquipmentMaintenanceScheduleID = ? WHERE EquipmentID = "  + id;
            PreparedStatement p = conn.prepareStatement(pquery);
            p.clearParameters();
            p.setString(1, brandName);
            p.setString(2, description);
            p.setDouble(3, hourUsed);
            p.setInt(4, schecule);
            int rsetp = p.executeUpdate();*/
            String updateQuery = "UPDATE Equipment SET BrandName = '" + brandName + "', Description = '" + description +"', HoursUsed = " + hourUsed + ", EquipmentMaintenanceScheduleID = " + schecule + " WHERE EquipmentID = "  + id;
            int rsetu = selectStatement.executeUpdate(updateQuery);

            if(rsetu > 0)
                System.out.println("Equipment ID " + id + " Updated.");
            else
                System.out.println("Fail to UPDATE New Service!");

        }
        else
        {
            System.out.println("Equipment ID " + id + " Not Found!");
        }
    }
    private void updateService(Connection conn) throws SQLException
    {
        int id;
        System.out.print("UPDATE Service ID: ");
        try {
            id = keyInput.nextInt();
        }catch(Exception e)
        {
            System.out.println("Input Invalid Back to Option Menu!");
            keyInput.next();
            return;
        }
        String query = "SELECT * FROM Service WHERE ServiceID = " + id;
        Statement selectStatement = conn.createStatement();
        ResultSet rset = selectStatement.executeQuery(query);
        if(rset.next())
        {

            System.out.println("Service ID " + id + " Found.");
            System.out.println("Service Name: " + rset.getString(2));
            System.out.println("Description: " + rset.getString(3));
            System.out.println("Rate Charge: " + rset.getDouble(4));
            System.out.println("Duration: " + rset.getString(4));

            String serviceName, description, duration;
            double rateCharge;
            System.out.print("UPDATE Service Name: ");
            serviceName = readLine();
            System.out.print("UPDATE Service Description: ");
            description = readLine();
            System.out.print("UPDATE Service Rate Charge: ");
            try {
                rateCharge = keyInput.nextDouble();
            }catch(Exception e)
            {
                System.out.println("Input Invalid Back to Option Menu!");
                keyInput.next();
                return;
            }
            System.out.print("UPDATE Service Duration: ");
            duration = readLine();

            String uquery = "UPDATE Service SET ServiceName = '" + serviceName + "', Description = '" + description + "', RateCharge = " + rateCharge + " , Duration = '" + duration + "' WHERE ServiceID = " + id;

            int rsetu = selectStatement.executeUpdate(uquery);
            if(rsetu > 0)
            {
                System.out.println("Service ID: " + id  + " UPDATED! :D.");
            }
            else
            {
                System.out.println("Fail to UPDATE New Service!");
            }
        }
        else
        {
            System.out.println("Service ID " + id + " Not Found!");
        }
    }
    private void updateCustomerInfo(Connection conn) throws SQLException
    {
        //Get Equipment ID
        int id;
        System.out.print("UPDATE Customer ID: ");
        try {
            id = keyInput.nextInt();
        }catch(Exception e)
        {
            System.out.println("Input Invalid Back to Option Menu!");
            keyInput.next();
            return;
        }
        String query = "SELECT * FROM Customer WHERE CustomerID = " + id;
        Statement selectStatement = conn.createStatement();
        ResultSet rset = selectStatement.executeQuery(query);
        if(rset.next())
        {
            System.out.println("Customer ID " + id + " Found.");
            System.out.println("First Name: " + rset.getString(2));
            System.out.println("Last Name: " + rset.getString(3));
            System.out.println("Address: " + rset.getString(4));
            System.out.println("Email: " + rset.getString(5));
            System.out.println("Phone Number: " + rset.getString(6));
            System.out.println("Balance: " + rset.getDouble(7));

            String firstName, lastName, address, email, phone;
            double balance;
            System.out.print("UPDATE Customer First Name: ");
            firstName = readLine();
            System.out.print("UPDATE Customer Second Name: ");
            lastName = readLine();
            System.out.print("UPDATE Customer Address: ");
            address = readLine();
            System.out.print("UPDATE Customer Email: ");
            email = readLine();
            System.out.print("UPDATE Customer Phone Number: ");
            phone = readLine();
            System.out.print("UPDATE Customer Balance: ");
            try {
                balance = keyInput.nextDouble();
            }catch(Exception e)
            {
                System.out.println("Input Invalid Back to Option Menu!");
                keyInput.next();
                return;
            }

            String uquery = "UPDATE Customer SET FirstName = '" + firstName + " ', LastName = '" + lastName +"', Address = '" + address + "', Email = '" + email + "', PhoneNumber = '" + phone + "', Balance = " + balance + " WHERE CustomerID = " + id;
            int rsetu = selectStatement.executeUpdate(uquery);
            if(rsetu > 0)
                System.out.println("Customer ID " + id + " UPDATED :D");
            else
                System.out.println("Fail to UPDATE New Customer!");

        }
        else
        {
            System.out.println("Customer ID " + id + " Not Found!");
        }
    }
    private void updateEmployeeInfo(Connection conn) throws SQLException
    {
        //Get Employee ID
        int id;
        System.out.print("UPDATE Employee ID: ");
        try {
            id = keyInput.nextInt();
        }catch(Exception e)
        {
            System.out.println("Input Invalid Back to Option Menu!");
            keyInput.next();
            return;
        }
        String query = "SELECT * FROM Employee WHERE EmployeeID = " + id;
        Statement selectStatement = conn.createStatement();
        ResultSet rset = selectStatement.executeQuery(query);
        if(rset.next())
        {
            System.out.println("Employee ID " + id + " Found.");
            System.out.println("First Name: " + rset.getString(2));
            System.out.println("Last Name: " + rset.getString(3));
            System.out.println("Address: " + rset.getString(4));
            System.out.println("Gender: " + rset.getString(5));
            System.out.println("DateHired: " + rset.getString(6));
            System.out.println("PositionID: " + rset.getInt(7));

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
            String uquery = "UPDATE Employee SET FirstName = '" + firstName + "', LastName = '" + lastName + "', AddressID = '" + address + "', Gender = '" + gender + "', PositionID = " + position + " WHERE EmployeeID = " + id;
            int rsetu = selectStatement.executeUpdate(uquery);
            if(rsetu > 0)
                System.out.println("Employee ID " + id + " UPDATED.");
            else
                System.out.println("Fail to UPDATE Employee!");
        }
        else
        {
            System.out.println("Employee ID " + id + " Not Found!");
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
