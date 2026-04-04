package EDSxADS_Final_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DBConnection {

    private static final String BASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "Recycling";
    private static final String USER = "root"; // Root for init
    private static final String PASS = "";

    // Role-specific credentials
    private static final String ADMIN_DB_USER = "Admin";
    private static final String ADMIN_DB_PASS = "admin123";
    private static final String STUDENT_DB_USER = "Student";

    public static void initDatabase() {
        // Connect to the server without a database name first to create the DB
        try (Connection conn = DriverManager.getConnection(BASE_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            stmt.executeUpdate("USE " + DB_NAME);

            // Create Admins table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Admins (" +
                               "Username VARCHAR(100) PRIMARY KEY, " +
                               "Password INT)");
            
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Bins (" +
                               "BinID INT AUTO_INCREMENT PRIMARY KEY, " +
                               "LocationName VARCHAR(100) NOT NULL, " +
                               "Status ENUM('Empty', 'Half-Full', 'Full') DEFAULT 'Empty')");
            
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Students (" +
                               "StudentNo INT PRIMARY KEY, " +
                               "Department VARCHAR(100))");

            // Transactions table creation
            String sqlTransactions = "CREATE TABLE IF NOT EXISTS Transactions (" +
                         "TransactionID INT AUTO_INCREMENT PRIMARY KEY, " +
                         "BinID INT, " + // <--- ADD THIS
                         "StudentNo INT NOT NULL, " +
                         "MaterialType VARCHAR(50) NOT NULL, " +
                         "Quantity INT NOT NULL, " +
                         "CollectionDate DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                         "CONSTRAINT fk_student FOREIGN KEY (StudentNo) " +
                         "REFERENCES Students(StudentNo) ON DELETE CASCADE ON UPDATE CASCADE)";

            stmt.executeUpdate(sqlTransactions);

            // Permission Management - Using try-catch inside for User Creation to avoid "User already exists" errors
            try {
                stmt.executeUpdate("CREATE USER IF NOT EXISTS '" + ADMIN_DB_USER + "'@'localhost' IDENTIFIED BY '" + ADMIN_DB_PASS + "'");
                stmt.executeUpdate("GRANT ALL PRIVILEGES ON " + DB_NAME + ".* TO '" + ADMIN_DB_USER + "'@'localhost'");

                stmt.executeUpdate("CREATE USER IF NOT EXISTS '" + STUDENT_DB_USER + "'@'localhost' IDENTIFIED BY ''");
                stmt.executeUpdate("GRANT SELECT, INSERT ON " + DB_NAME + ".* TO '" + STUDENT_DB_USER + "'@'localhost'");
                
                stmt.executeUpdate("FLUSH PRIVILEGES");
            } catch (SQLException e) {
                System.out.println("Note: Users might already exist or permission grant failed.");
            }

            // Insert Initial Data
            if (!hasData(stmt, "Students")) {
                stmt.executeUpdate("INSERT INTO Students (StudentNo, Department) VALUES (2024104677, 'Information Technology'), (2024104678, 'Computer Science')");
            }

            if (!hasData(stmt, "Bins")) {
                stmt.executeUpdate("INSERT INTO Bins (LocationName, Status) VALUES " +
                                   "('Engineering Building', 'Empty'), ('Canteen', 'Half-Full')");
            }
            
            // Added check for Admins so you have a default login
            if (!hasData(stmt, "Admins")) {
                stmt.executeUpdate("INSERT INTO Admins (Username, Password) VALUES ('admin', 12345)");
            }

        } catch (SQLException se) {
            se.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection Error: " + se.getMessage());
        }
    }

    private static boolean hasData(Statement stmt, String tableName) throws SQLException {
        
        try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + DB_NAME + "." + tableName)) {
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    // Role-Based Connection 
    public static Connection getConnection(String role) {
        String dbUser = STUDENT_DB_USER;
        String dbPass = "";

        if ("Admin".equalsIgnoreCase(role)) {
            dbUser = ADMIN_DB_USER;
            dbPass = ADMIN_DB_PASS;
        }

        try {
            
            return DriverManager.getConnection(BASE_URL + DB_NAME, dbUser, dbPass);
        } catch (SQLException e) {
            System.err.println("Database Access Denied for: " + role + " - " + e.getMessage());
            return null;
        }
    }

    // Overloaded original method
    public static Connection getConnection() {
        return getConnection("Admin"); 
    }
}