package utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class PrintManager {

    public static void printNColumns(ResultSet rset) throws SQLException {
        ResultSetMetaData rsmd = rset.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rset.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rset.getString(i);
                System.out.print(columnValue /*+ " " + rsmd.getColumnName(i)*/);
            }
            System.out.println("");
        }
    }
    public static void printColumns(ResultSet rset) throws SQLException {
        ResultSet result = rset;

        while(result.next()) {
            System.out.print(rset.getString(1));
        }

    }
}