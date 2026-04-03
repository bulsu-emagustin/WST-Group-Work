package EDSxADS_Final_Project;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnection {

    private static final String BASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "Recycling";
    private static final String USER = "root";
    private static final String PASS = "";

    public static void initDatabase() {
        try (Connection conn = DriverManager.getConnection(BASE_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            // Create Database
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            stmt.executeUpdate("USE " + DB_NAME);

            // Create Tables
            String sqlAdmins = "CREATE TABLE IF NOT EXISTS Admins (Username VARCHAR(100) PRIMARY KEY, Password INT)";
            
            String sqlTransactions = "CREATE TABLE IF NOT EXISTS Transactions (" +
                         "TransactionID INT AUTO_INCREMENT PRIMARY KEY, " +
                         "StudentNo INT NOT NULL, " +
                         "MaterialType VARCHAR(50) NOT NULL, " +
                         "Quantity INT NOT NULL, " +
                         "Department VARCHAR(100) DEFAULT 'Information Technology', " +
                         "CollectionDate DATETIME DEFAULT CURRENT_TIMESTAMP" +
                         ")";
            
            String TransacValue = " INSERT IGNORE INTO Transactions (StudentNo, MaterialType, Quantity) VALUES"
                                    + "(2024104677, 'Plastic', 12),"
                                    + "(2024104677, 'Plastic', 20)," 
                                    + "(2024104677, 'Paper', 32)," 
                                    + "(2024104677, 'Paper', 90)," 
                                    + "(2024104678, 'Metal', 2)," 
                                    + "(2024104678, 'Metal', 3)," 
                                    + "(2024104678, 'Glass', 12)," 
                                    + "(2024104679, 'Organic', 30)," 
                                    + "(2024104679, 'E-Waste', 3)," 
                                    + "(2024104679, 'Textile', 19);";
            
            stmt.executeUpdate(sqlAdmins);
            stmt.executeUpdate(sqlTransactions);
            stmt.executeUpdate(TransacValue);
            
            // Permissions
            stmt.executeUpdate("GRANT SELECT, INSERT ON Recycling.* TO 'contributor_user'@'localhost'");

            // Initial Data
            stmt.executeUpdate("INSERT IGNORE INTO Admins (Username, Password) VALUES ('Akira', 1234), ('Ernest', 1234)");

            System.out.println("Database Initialized Successfully.");

        } catch (SQLException se) {
            se.printStackTrace();
            JOptionPane.showMessageDialog(null, "Critical: Could not initialize database.\n" + se.getMessage());
        }
    }
    
    public static Connection getConnection() {
        try {
            // We include the DB_NAME in the URL here so we don't have to call 'USE' every time
            return DriverManager.getConnection(BASE_URL + DB_NAME, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
    

