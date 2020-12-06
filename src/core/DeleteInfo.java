package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteInfo {
    //*********** ALL DELETE FUNCTIONS ************************
    Scanner keyInput = new Scanner(System.in);
    Updates menuObj = new Updates();
    public void runDelete(Connection conn)
    {
        try {
            boolean done = false;
            do {
                //Print MENU for INSERT / DELETE / UPDATE
                menuObj.printOptionMenu("DELETE");
                System.out.print("Type in your option: ");
                System.out.flush();
                String ch = readLine();
                System.out.println();
                switch (ch.charAt(0)) {
                    case '1':
                        deleteEquiptment(conn);
                        break;
                    case '2':
                        deleteService(conn);
                        break;
                    case '3':
                        deleteCustomerInfo(conn);
                        break;
                    case '4':
                        deleteEmployeeInfo(conn);
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
    private void deleteEquiptment(Connection conn) throws SQLException
    {
        //Get Equipment ID
        int id;
        System.out.print("DELETE Equipment ID: ");
        try {
            id = keyInput.nextInt();
        }catch(Exception e)
        {
            System.out.println("Input Invalid Back to Option Menu!");
            keyInput.next();
            return;
        }
        String query = "SELECT EquipmentID FROM Equipment WHERE EquipmentID = " + id;
        Statement selectStatement = conn.createStatement();
        ResultSet rset = selectStatement.executeQuery(query);
        if(rset.next())
        {
            System.out.println("Equipment ID " + id + " Found.");
            String dquery = "DELETE FROM Equipment WHERE EquipmentID = " + id;
            int rseti = selectStatement.executeUpdate(dquery);
            if(rseti > 0)
                System.out.println("Equipment ID " + id + " Deleted.");
        }
        else
        {
            System.out.println("Equipment ID " + id + " Not Found!");
        }
    }
    private void deleteService(Connection conn) throws SQLException
    {
        //Get Equipment ID
        int id;
        System.out.print("DELETE Service ID: ");
        try {
            id = keyInput.nextInt();
        }catch(Exception e)
        {
            System.out.println("Input Invalid Back to Option Menu!");
            keyInput.next();
            return;
        }
        String query = "SELECT ServiceID FROM Service WHERE ServiceID = " + id;
        Statement selectStatement = conn.createStatement();
        ResultSet rset = selectStatement.executeQuery(query);
        if(rset.next())
        {
            System.out.println("Service ID " + id + " Found.");
            String dquery = "DELETE FROM Service WHERE ServiceID = " + id;
            int rseti = selectStatement.executeUpdate(dquery);
            if(rseti > 0)
                System.out.println("Service ID " + id + " Deleted.");
        }
        else
        {
            System.out.println("Service ID " + id + " Not Found!");
        }
    }
    private void deleteCustomerInfo(Connection conn) throws SQLException
    {
        //Get Equipment ID
        int id;
        System.out.print("DELETE Customer ID: ");
        try {
            id = keyInput.nextInt();
        }catch(Exception e)
        {
            System.out.println("Input Invalid Back to Option Menu!");
            keyInput.next();
            return;
        }
        String query = "SELECT CustomerID FROM Customer WHERE CustomerID = " + id;
        Statement selectStatement = conn.createStatement();
        ResultSet rset = selectStatement.executeQuery(query);
        if(rset.next())
        {
            System.out.println("Customer ID " + id + " Found.");
            String dquery = "DELETE FROM Customer WHERE CustomerID = " + id;
            int rseti = selectStatement.executeUpdate(dquery);
            if(rseti > 0)
                System.out.println("Customer ID " + id + " Deleted.");
        }
        else
        {
            System.out.println("Customer ID " + id + " Not Found!");
        }
    }

    private void deleteEmployeeInfo(Connection conn) throws SQLException
    {
        //Get Employee ID
        int id;
        System.out.print("DELETE Employee ID: ");
        try {
            id = keyInput.nextInt();
        }catch(Exception e)
        {
            System.out.println("Input Invalid Back to Option Menu!");
            keyInput.next();
            return;
        }
        String query = "SELECT EmployeeID FROM Employee WHERE EmployeeID = " + id;
        Statement selectStatement = conn.createStatement();
        ResultSet rset = selectStatement.executeQuery(query);
        if(rset.next())
        {
            System.out.println("Employee ID " + id + " Found.");
            String dquery = "DELETE FROM Employee WHERE EmployeeID = " + id;
            int rseti = selectStatement.executeUpdate(dquery);
            if(rseti > 0)
                System.out.println("Employee ID " + id + " Deleted.");
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
