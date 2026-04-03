package EDSxADS_Final_Project;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
        
public class WeeklyColumnChart extends JPanel {
    
    //initialize variable
    private int[] values = {0, 0, 0, 0, 0, 0, 0}; 
    private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public WeeklyColumnChart() {
        setBackground(Color.WHITE);
        fetchDbData(); // Load real data on startup
    }

    public void fetchDbData() {
        // Reset values
        for (int i = 0; i < values.length; i++) values[i] = 0;

        try (Connection con = DBConnection.getConnection()) {
            // This query calculates total quantity per day for the CURRENT week
            // WEEKDAY() returns 0 for Monday, 1 for Tuesday, etc.
            String query = "SELECT WEEKDAY(CollectionDate) as DayIndex, SUM(Quantity) as Total " +
                           "FROM Transactions " +
                           "WHERE YEARWEEK(CollectionDate, 1) = YEARWEEK(CURDATE(), 1) " +
                           "GROUP BY DayIndex";
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                int index = rs.getInt("DayIndex");
                if (index >= 0 && index < 7) {
                    values[index] = rs.getInt("Total");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        
        int barWidth = width / values.length;

        int max = 0;
        for (int v : values) {
            if (v > max) max = v;
        }
        
        for (int i = 0; i < values.length; i++) {
            int barHeight = (int) ((double) values[i] / max * (height - 50));

            int x = i * barWidth + 10;
            int y = height - barHeight - 30;

            // Bar Color
            g.setColor(new Color(34, 139, 34));
            g.fillRect(x, y, barWidth - 20, barHeight);

            // Labels
            g.setColor(Color.BLACK);
            g.drawString(days[i], x + 10, height - 10);
            g.drawString(String.valueOf(values[i]), x + 10, y - 5);
        }
    }
}