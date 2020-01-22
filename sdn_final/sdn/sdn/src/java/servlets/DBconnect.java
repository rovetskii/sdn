package servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * Created by Yuriy on 24.04.2017.
 */
class DBconnect {
    private Connection connection;
    DBconnect() throws SQLException{

        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "teacherdb";
        String driver = "com.mysql.jdbc.Driver";
        String username = "root";
        String passwordDB = "Fcbcntynkrf1234";

        try {
            Class.forName(driver);
            connection = DriverManager.
                    getConnection(url + dbName, username, passwordDB);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    Connection getConnection() {
        return connection;
    }
}
