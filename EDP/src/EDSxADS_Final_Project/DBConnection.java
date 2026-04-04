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
    private static final String USER = "root";
    private static final String PASS = "";

    public static void initDatabase() {
        try (Connection conn = DriverManager.getConnection(BASE_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            stmt.executeUpdate("USE " + DB_NAME);

            // Create Admins Table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Admins (" +
                               "Username VARCHAR(100) PRIMARY KEY, " +
                               "Password INT)");
            
            stmt.executeUpdate ("INSERT INTO Admins (Username, Password) VALUES ('Akira', 1234), ('Ernesto', 1234)");
            
            // Create Registered Table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Students (" +
                               "StudentNo INT PRIMARY KEY, " +
                               "Department VARCHAR(100))");

            // Create Transactions Table 
            String sqlTransactions = "CREATE TABLE IF NOT EXISTS Transactions (" +
                                     "TransactionID INT AUTO_INCREMENT PRIMARY KEY, " +
                                     "StudentNo INT NOT NULL, " +
                                     "MaterialType VARCHAR(50) NOT NULL, " +
                                     "Quantity INT NOT NULL, " +
                                     "CollectionDate DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                                     "CONSTRAINT fk_student FOREIGN KEY (StudentNo) " +
                                     "REFERENCES Students(StudentNo) ON DELETE CASCADE ON UPDATE CASCADE)";
            
            stmt.executeUpdate(sqlTransactions);

            // We MUST add students here first, or the Transaction inserts will FAIL.
            if (!hasData(stmt, "Students")) {
                stmt.executeUpdate("INSERT INTO Students (StudentNo, Department) VALUES " +
                                   "(2024104677, 'Information Technology'), " +
                                   "(2024104678, 'Business Administration'), " +
                                   "(2024104679, 'Civil Engineering')");
            }

            // Initial Data Handler for Transactions
            if (!hasData(stmt, "Transactions")) {
                String TransacValue = "INSERT INTO Transactions (StudentNo, MaterialType, Quantity) VALUES "
                        + "(2024104677, 'Plastic', 12), "
                        + "(2024104677, 'Plastic', 20), "
                        + "(2024104677, 'Paper', 32), "
                        + "(2024104678, 'Metal', 2), "
                        + "(2024104679, 'Textile', 19)";
                stmt.executeUpdate(TransacValue);
            }

            System.out.println("Database Initialized with Relationships.");

        } catch (SQLException se) {
            se.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection Error");
        }
    }
    
    //Safety check for duplication of value
    private static boolean hasData(Statement stmt, String tableName) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
        return rs.next() && rs.getInt(1) > 0;
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(BASE_URL + DB_NAME, USER, PASS);
        } catch (SQLException e) {
            return null;
        }
    }
}