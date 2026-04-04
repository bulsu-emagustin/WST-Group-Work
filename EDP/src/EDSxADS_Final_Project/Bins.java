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

        try (Connection con = DBConnection.getConnection()) {
            // Calculate total quantity in this bin
            PreparedStatement pstmtCount = con.prepareStatement(countSql);
            pstmtCount.setInt(1, binID);
            ResultSet rs = pstmtCount.executeQuery();

            int totalQuantity = 0;
            if (rs.next()) {
                totalQuantity = rs.getInt(1);
            }

            // Determine Status
            String newStatus;
            if (totalQuantity <= 50) {
                newStatus = "Empty";
            } else if (totalQuantity <= 300) {
                newStatus = "Half-Full";
            } else {
                newStatus = "Full";
            }

            // 3. Update the Bins table
            PreparedStatement pstmtUpdate = con.prepareStatement(updateSql);
            pstmtUpdate.setString(1, newStatus);
            pstmtUpdate.setInt(2, binID);
            pstmtUpdate.executeUpdate();

            // Optional: Alert if bin becomes full
            if (newStatus.equals("Full")) {
                System.out.println("ALERT: Bin ID " + binID + " is now FULL!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating bin status: " + e.getMessage());
        }
    }
    
    /**
     * Logic to manually "Empty" a bin (For Admin use)
     */
    public static void clearBin(int binID) {
        String sql = "UPDATE Bins SET Status = 'Empty', LastEmptied = CURRENT_TIMESTAMP WHERE BinID = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, binID);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}