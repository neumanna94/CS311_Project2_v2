package utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintManager {
    public static void printResultSet(ResultSet rset, int numColums) throws SQLException {
        switch(numColums){
            case 1: printOneColumn(rset);
                break;
            case 2: printTwoColumn(rset);
                break;
            case 3: printThreeColumn(rset);
                break;
            default:
                break;
        }
    }

    public static void printOneColumn(ResultSet rset) throws SQLException {
        while(rset.next())
            System.out.print(rset.getString(1));
    }

    public static void printTwoColumn(ResultSet rset) throws SQLException {
        while(rset.next()){
            System.out.print(rset.getString(1) + " ");
            System.out.print(rset.getString(2));
        }
    }
    public static void printNewCustomers(ResultSet rset) throws SQLException {
        while(rset.next()){
            System.out.println(rset.getInt(1));
        }
    }
    public static void printThreeColumn(ResultSet rset) throws SQLException {
        while(rset.next()){
            System.out.print(rset.getString(1) + " ");
            System.out.print(rset.getString(2) + " " );
            System.out.print(rset.getString(3));
        }
    }
}