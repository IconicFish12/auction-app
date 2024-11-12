package lelang;

import java.sql.Connection;

import lelang.app.controller.*;
import lelang.database.DBConnection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Connection connect = DBConnection.getConnection();

        if (connect != null) {
            try {
                connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}