package core;

import com.mysql.cj.protocol.Resultset;

import javax.print.attribute.standard.JobOriginatingUserName;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

public class Updates {
    private String username = "wayne";
    private String password = "password";
    Scanner keyInput = new Scanner(System.in);

    public void run(Connection conn)
    {
        System.out.print("Enter User ID:");
        username = readLine();
        System.out.print("Enter Password:");
        password = readLine();

        try {
            if(checkUserPassword(username, password, conn))
            {
                boolean done = false;
                do {
                    //Print MENU for INSERT / DELETE / UPDATE
                    printUpdatesOptions();
                    System.out.print("Type in your option: ");
                    System.out.flush();
                    String ch = readLine();
                    System.out.println();
                    switch (ch.charAt(0)) {
                        case 'A': case'a':
                            AddInfo addObj = new AddInfo();
                            addObj.runAdd(conn);
                            break;
                        case 'B': case'b':
                            DeleteInfo deleteObj = new DeleteInfo();
                            deleteObj.runDelete(conn);
                            break;
                        case 'C': case'c':
                            UpdateInfo updateObj = new UpdateInfo();
                            updateObj.runUpdate(conn);
                            break;
                        case 'Q': case'q':
                            done = true;
                            System.out.println("Returning to Main Menu....");
                            break;
                        default:
                            System.out.println("NOT VALID OPTION!!!");
                    } //switch
                } while (!done);
            }
            else
            {
                System.out.println("Username or Password is Incorrect");
            }
        }catch (SQLException ex) {
            System.out.println(ex);
        }

    }// END OF RUN

    public void printUpdatesOptions(){
        System.out.println("\n*****************************************************************************");
        System.out.println("                       **********************");
//        System.out.println("                     Welcome to Clean-and-Go Shop");
        System.out.println("               Menu 4. UPDATES. Current User: " + username);
        System.out.println("                       **********************");
        System.out.println("*****************************************************************************");
        System.out.println("                     A. Insert New Information.");
        System.out.println("                     B. Delete Some Information.");
        System.out.println("                     C. Update Current Information.");
        System.out.println("                           Q. Quit");
    }
    public void printOptionMenu(String option)
    {
        System.out.println("\n*****************************************************************************");
        System.out.println("                       **********************");
//        System.out.println("                     Welcome to Clean-and-Go Shop");
        System.out.println("               Menu 4. UPDATES. Current User: " + username);
        System.out.println("                        Sub Option: " + option);
        System.out.println("                       **********************");
        System.out.println("*****************************************************************************");
        System.out.println("                     1. " + option + " Equipment");
        System.out.println("                     2. " + option + " Service");
        System.out.println("                     3. " + option + " Customer Information");
        System.out.println("                     4. " + option + " Employee Information");
        System.out.println("                           5. Quit");
    }

    private boolean checkUserPassword (String username, String password, Connection conn) throws SQLException
    {
        String query = "SELECT * FROM User WHERE Username = '" + username + "' AND Password = '" + password + "';";
        Statement selectStatement = conn.createStatement();
        ResultSet rset = selectStatement.executeQuery(query);
        if(rset.next())
        {
            System.out.println("LOGIN SUCCESS :D");
            return true;
        }
        else
        {
            return false;
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
}// End of Updates CLASS
