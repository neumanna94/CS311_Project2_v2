package utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

    private static void printNColumns(ResultSet rset) throws SQLException {
        ResultSetMetaData rsmd = rset.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rset.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rset.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }
    }

    public static void printOneColumn(ResultSet rset) throws SQLException {
        ResultSet result = rset;
       while(result.next())
            System.out.print(rset.getString(1) + "\n");
    }

    public static void printTwoColumn(ResultSet rset) throws SQLException {
        ResultSet result = rset;
        while(result.next()){
            System.out.print(rset.getString(1) + " ");
            System.out.print(rset.getString(2));
        }
    }

    public static void printThreeColumn(ResultSet rset) throws SQLException {
        ResultSet result = rset;
        while(result.next()){
            System.out.print(rset.getInt(1) + " ");
            System.out.print(rset.getString(2) + " ");
            System.out.print(rset.getString(3));
        }
    }
}
