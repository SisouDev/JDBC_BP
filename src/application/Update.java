package application;

import database.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataBase.getConnection();
            preparedStatement = connection.prepareStatement(
                    "UPDATE jdbc.seller " +
                            "SET BaseSalary = BaseSalary + ? " +
                            "WHERE " +
                            "(DepartmentId = ?)");
            preparedStatement.setDouble(1 , 200D);
            preparedStatement.setInt(2, 2);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DataBase.closeStatement(preparedStatement);
            DataBase.closeConnection();
        }

    }
}
