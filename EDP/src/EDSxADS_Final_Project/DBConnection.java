package EDSxADS_Final_Project;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Recycling;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "contributor_user";
    private static final String PASSWORD = "Password123!";
    
    public static Connection getConnection() {
        Connection con = null;
        
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
