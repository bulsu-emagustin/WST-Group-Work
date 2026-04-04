package EDSxADS_Final_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Bins {

    /*
    Updates the status of a specific bin based on total items collected.
    Logic: 
    0-50 units = Empty
    51-300 units = Half-Full
    > 300 units = Full
    */
    
    public static void updateBinStatus(int binID) {
        String countSql = "SELECT SUM(Quantity) FROM Transactions WHERE BinID = ?";
        String updateSql = "UPDATE Bins SET Status = ? WHERE BinID = ?";

        // Get connection using the Admin role by default
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return;

            int totalQuantity = 0;
            
            // 1. Calculate total quantity
            try (PreparedStatement pstmtCount = con.prepareStatement(countSql)) {
                pstmtCount.setInt(1, binID);
                try (ResultSet rs = pstmtCount.executeQuery()) {
                    if (rs.next()) {
                        totalQuantity = rs.getInt(1);
                    }
                }
            }

            // 2. Determine Status based on your logic
            String newStatus;
            if (totalQuantity <= 50) {
                newStatus = "Empty";
            } else if (totalQuantity <= 300) {
                newStatus = "Half-Full";
            } else {
                newStatus = "Full";
            }

            // 3. Update the Bins table
            try (PreparedStatement pstmtUpdate = con.prepareStatement(updateSql)) {
                pstmtUpdate.setString(1, newStatus);
                pstmtUpdate.setInt(2, binID);
                pstmtUpdate.executeUpdate();
            }

            // Alert logic
            if ("Full".equals(newStatus)) {
                System.out.println("ALERT: Bin ID " + binID + " is now FULL!");
                JOptionPane.showMessageDialog(null, "Bin ID " + binID + " has reached capacity!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating bin status: " + e.getMessage());
        }
    }
    
    /**
     * Logic to manually "Empty" a bin (For Admin use)
     * Note: If you use 'LastEmptied', ensure you add that column to your Bins table in DBConnection.
     */
    public static void clearBin(int binID) {
        // I've removed LastEmptied to match your current DBConnection schema, 
        // OR you should add "LastEmptied DATETIME" to the Bins table in DBConnection.
        String sql = "UPDATE Bins SET Status = 'Empty' WHERE BinID = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            if (con == null) return;
            
            pst.setInt(1, binID);
            int rowsAffected = pst.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Bin " + binID + " cleared successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
        }
    }
}