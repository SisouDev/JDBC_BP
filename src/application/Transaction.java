package application;

import database.DataBase;
import database.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Transaction {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DataBase.getConnection();
            connection.setAutoCommit(false);

            statement = connection.createStatement();

            int rows1 = statement.executeUpdate(
                    "UPDATE jdbc.seller SET BaseSalary = 2090 WHERE DepartmentId = 1"
            );
            int rows2 = statement.executeUpdate(
                    "UPDATE jdbc.seller SET BaseSalary = 3090 WHERE DepartmentId = 2"
            );

            connection.commit();

            System.out.println("Rows 1: " + rows1);
            System.out.println("Rows 2: " + rows2);

        } catch (SQLException e) {
            try {
                connection.rollback();
                throw new DbException("Transaction rolled back, caused by: " + e.getMessage());
            } catch (SQLException ex) {
                throw new DbException("Error trying to rollback transaction, caused by: " + ex.getMessage());
            }
        }
        finally {
            DataBase.closeStatement(statement);
            DataBase.closeConnection();
        }
    }
}
