package application;

import database.DataBase;
import database.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataBase.getConnection();
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM jdbc.department " +
                            "WHERE " +
                            "Id = ?");
            preparedStatement.setInt(1 , 9);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DataBase.closeStatement(preparedStatement);
            DataBase.closeConnection();
        }
    }
}
