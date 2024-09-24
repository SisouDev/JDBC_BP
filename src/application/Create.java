package application;

import database.DataBase;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Create {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DataBase.getConnection();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO jdbc.seller " +
                            "(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                            "VALUES " +
                            "(?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                    );
            preparedStatement.setString(1, "Carl Purple");
            preparedStatement.setString(2, "carl@gmail.com");
            preparedStatement.setDate(3, new Date(sdf.parse("22/07/1998").getTime()));
            preparedStatement.setDouble(4, 1550D);
            preparedStatement.setInt(5, 4);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    System.out.println("ID: " + id);
                }
            }else {
                System.out.println("No rows affected");
            }

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        finally {
            DataBase.closeStatement(preparedStatement);
            DataBase.closeConnection();
        }
    }
}
