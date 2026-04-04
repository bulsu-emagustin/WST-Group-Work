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
        try (Connection conn = DriverManager.getConnection(BASE_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            stmt.executeUpdate("USE " + DB_NAME);

            // tables
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Admins (" +
                               "Username VARCHAR(100) PRIMARY KEY, " +
                               "Password INT)");
            
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Bins (" +
                               "BinID INT AUTO_INCREMENT PRIMARY KEY, " +
                               "LocationName VARCHAR(100) NOT NULL, " +
                               "Status ENUM('Empty', 'Half-Full', 'Full') DEFAULT 'Empty'");
            
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Students (" +
                               "StudentNo INT PRIMARY KEY, " +
                               "Department VARCHAR(100))");

            String sqlTransactions = "CREATE TABLE IF NOT EXISTS Transactions (" +
                                     "TransactionID INT AUTO_INCREMENT PRIMARY KEY, " +
                                     "StudentNo INT NOT NULL, " +
                                     "MaterialType VARCHAR(50) NOT NULL, " +
                                     "Quantity INT NOT NULL, " +
                                     "CollectionDate DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                                     "CONSTRAINT fk_student FOREIGN KEY (StudentNo) " +
                                     "REFERENCES Students(StudentNo) ON DELETE CASCADE ON UPDATE CASCADE)";
            stmt.executeUpdate(sqlTransactions);

            // Permission Management 
            stmt.executeUpdate("CREATE USER IF NOT EXISTS '" + ADMIN_DB_USER + "'@'localhost' IDENTIFIED BY '" + ADMIN_DB_PASS + "'");
            stmt.executeUpdate("GRANT ALL PRIVILEGES ON " + DB_NAME + ".* TO '" + ADMIN_DB_USER + "'@'localhost'");

            stmt.executeUpdate("CREATE USER IF NOT EXISTS '" + STUDENT_DB_USER + "'@'localhost' IDENTIFIED BY ''");
            stmt.executeUpdate("GRANT SELECT, INSERT ON " + DB_NAME + ".* TO '" + STUDENT_DB_USER + "'@'localhost'");
            
            stmt.executeUpdate("FLUSH PRIVILEGES");

            // insert datas
            if (!hasData(stmt, "Students")) {
                stmt.executeUpdate("INSERT INTO Students VALUES (2024104677, 'Information Technology'), (2024104678, 'Computer Science')");
            }
            

            if (!hasData(stmt, "Bins")) {
                stmt.executeUpdate("INSERT INTO Bins (LocationName, Status) VALUES " +
                                   "('Engineering Building', 'Empty'), ('Canteen', 'Half-Full')");
            }

        } catch (SQLException se) {
            se.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection Error");
        }
    }

    private static boolean hasData(Statement stmt, String tableName) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
        return rs.next() && rs.getInt(1) > 0;
    }

      //Role-Based Connection 
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
            System.err.println("Database Access Denied for: " + role);
            return null;
        }
    }

    // Overloaded original method to prevent breaking existing code
    public static Connection getConnection() {
        return getConnection("Admin"); 
    }
}