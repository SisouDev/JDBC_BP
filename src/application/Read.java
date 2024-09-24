package application;

import database.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Read {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DataBase.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery(
                    "SELECT * FROM department"
            );

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("Id") + " " + resultSet.getString("Name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DataBase.closeResultSet(resultSet);
            DataBase.closeStatement(statement);
            DataBase.closeConnection();
        }
    }
}
