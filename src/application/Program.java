package application;

import database.DataBase;

import java.sql.Connection;

public class Program {
    public static void main(String[] args) {
        Connection conn = DataBase.getConnection();

        DataBase.closeConnection();

    }
}
