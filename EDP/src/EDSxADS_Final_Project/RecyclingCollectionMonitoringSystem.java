package EDSxADS_Final_Project;

//Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
public class RecyclingCollectionMonitoringSystem {

    //Main Method (DIto palagi magrun ng code)
    public static void main(String[] args) {
        new WelcomeFrame();
    }
}

//Main Frame
class UniversityRecycleZone extends JFrame {

    //Variables
    JFrame MainF;
    JPanel header, UnivImageP, leftPanel, UserImageP, MainP, ColumnChart;
    JDialog Login, Contribution, History;
    JLabel UnivIcon, UserIcon, UniversityL, UserL, AdminL, LoginL, UsernameL, PasswordL, ContributionL, SIDL, MTypeL, QuantityL, StudentCL;
    JButton AdminButton, AddContriButton, ViewContriButton, ClearButton, EnterButton, CancelButton, SearchButton;
    JTextField Usernamefield, Passworfield, IDfield, Quantityfield;
    JComboBox<String> MTypeBox;
    JTable Table;
    JScrollPane TableS;
    ImageIcon MainBack, UnivL, UserIconL, ScaledUnivIcon, ScaledUserIcon;
    Image Logo, Logo2;

    public UniversityRecycleZone() {

        //Containers
        MainF = new JFrame("University Recycle Zone");
        header = new JPanel();
        leftPanel = new JPanel();
        UnivImageP = new JPanel();
        UserImageP = new JPanel();
        Login = new JDialog(this, "Admin Login", true);
        Contribution = new JDialog(this, "Contribution Form", true);
        History = new JDialog(this, "User History", true);
        String[] Mtype = {"Plastic", "Glass", "Paper", "Metal", "E-Waste"};
        String[] Atributes = {"Student ID", "Department", "Material Type", "Quantity", "Transaction", "Date"};
        String[][] Values = {};
        MainP = new JPanel(); 
        ColumnChart = new WeeklyColumnChart();

        //Background Image
        MainBack = new ImageIcon("Main_Background.jpg");
        JLabel Mbackground = new JLabel(MainBack);
        Mbackground.setLayout(null);
        MainF.setContentPane(Mbackground);

        //Layout Config
        MainF.setLayout(new BorderLayout());

        //Header Config
        header.setBackground(new Color(34, 139, 34));
        header.setPreferredSize(new Dimension(1500, 90));
        header.setLayout(null);
        UnivImageP.setBounds(10, 10, 80, 70);
        UnivImageP.setLayout(null);
        UnivL = new ImageIcon("URZLogo.png");
        Logo = UnivL.getImage().getScaledInstance(80, 70, Image.SCALE_SMOOTH);
        ScaledUnivIcon = new ImageIcon(Logo);
        UnivIcon = new JLabel(ScaledUnivIcon);
        UnivIcon.setBounds(0, 0, 80, 70);
        UniversityL = new JLabel("University Recycle Zone");
        UniversityL.setBounds(61, 20, 500, 50);
        UniversityL.setFont(new Font("Arial Black", Font.BOLD, 30));

        //Login button
        AdminL = new JLabel("ADMIN");
        AdminL.setBounds(1240, 25, 120, 40);
        AdminL.setFont(new Font("Bodoni MT", Font.BOLD, 30));
        AdminButton = new JButton("Login");
        AdminButton.setForeground(Color.red);
        AdminButton.setFont(new Font("Arial Black", Font.BOLD, 20));
        AdminButton.setBounds(1350, 20, 120, 50); 
        new LoginFunction();

        //Left panel Config
        leftPanel.setBackground(new Color(72, 209, 204));
        leftPanel.setPreferredSize(new Dimension(400, 800));
        leftPanel.setLayout(null);
        UserImageP.setBounds(50, 50, 300, 250);
        UserImageP.setLayout(null);
        UserIconL = new ImageIcon("UserIcon.png");
        Logo2 = UserIconL.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
        ScaledUserIcon = new ImageIcon(Logo2);
        UserIcon = new JLabel(ScaledUserIcon);
        UserIcon.setBounds(0, 0, 300, 250);
        UserL = new JLabel("USER");
        UserL.setFont(new Font("Arial", Font.BOLD, 30));
        UserL.setBounds(160, 310, 100, 50);
        AddContriButton = new JButton("Add Contribution");
        AddContriButton.setBackground(Color.LIGHT_GRAY);
        AddContriButton.setFont(new Font("Arial", Font.BOLD, 30));
        AddContriButton.setBounds(50, 390, 300, 100);
        ViewContriButton = new JButton("View Contribution");
        ViewContriButton.setBackground(Color.LIGHT_GRAY);
        ViewContriButton.setFont(new Font("Arial", Font.BOLD, 30));
        ViewContriButton.setBounds(50, 530, 300, 100);

        //Add Contribution Dialog
        AddContriButton.addActionListener(e -> {
            Contribution.setSize(500, 350);
            Contribution.setLocationRelativeTo(this);
            Contribution.setLayout(null);
            ContributionL = new JLabel("CONTRIBUTION");
            ContributionL.setBounds(130, 10, 300, 50);
            ContributionL.setFont(new Font("Arial Black", Font.BOLD, 25));

            SIDL = new JLabel("School ID: ");
            SIDL.setBounds(63, 40, 70, 80);
            IDfield = new JTextField(100);
            IDfield.setBounds(130, 65, 300, 30);
            MTypeL = new JLabel("Material Type: ");
            MTypeL.setBounds(40, 93, 100, 80);
            MTypeBox = new JComboBox<>(Mtype);
            MTypeBox.setBounds(130, 120, 300, 30);
            QuantityL = new JLabel("Quantity: ");
            QuantityL.setBounds(70, 150, 70, 80);
            Quantityfield = new JTextField(100);
            Quantityfield.setBounds(130, 175, 300, 30);
            EnterButton = new JButton("Enter");
            EnterButton.setBounds(90, 230, 130, 50);
            EnterButton.setFont(new Font("Arial", Font.BOLD, 25));
            CancelButton = new JButton("Cancel");
            CancelButton.setBounds(270, 230, 130, 50);
            CancelButton.setFont(new Font("Arial", Font.BOLD, 25));

            //Adding Components
            Contribution.add(SIDL);
            Contribution.add(ContributionL);
            Contribution.add(IDfield);
            Contribution.add(MTypeL);
            Contribution.add(MTypeBox);
            Contribution.add(QuantityL);
            Contribution.add(Quantityfield);
            Contribution.add(EnterButton);
            Contribution.add(CancelButton);
            
            EnterButton.addActionListener(ev -> {
            try {
            // Validation
            if (IDfield.getText().isEmpty() || Quantityfield.getText().isEmpty()) {
            JOptionPane.showMessageDialog(Contribution, "Please fill all fields!", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
            }

            // Connect to DB
            Connection con = DBConnection.getConnection();
            if (con == null) {
            JOptionPane.showMessageDialog(Contribution, "Connection Failed!", "", JOptionPane.ERROR_MESSAGE);
            return;
            }
            
            // SQL Insert
            String sql = "INSERT INTO Contributions (StudentNo, MaterialType, Quantity) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, Integer.parseInt(IDfield.getText()));
            pst.setString(2, MTypeBox.getSelectedItem().toString());
            pst.setInt(3, Integer.parseInt(Quantityfield.getText()));

            // Execute
            int rows = pst.executeUpdate();

            if (rows > 0) {
            JOptionPane.showMessageDialog(Contribution, "Contribution successfully saved", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Clear fields after insert
            IDfield.setText("");
            Quantityfield.setText("");

            Contribution.dispose(); // close dialog
            }

            con.close();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Contribution, "Invalid Input: School ID and Quantity must be whole numbers.", "Format Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
        }
        });
            //Listeners for Contribution Dialog
            CancelButton.addActionListener(ev -> Contribution.dispose());

            //Visibility (Importanteng palaging nasa pinaka dulo ito)
            Contribution.setVisible(true);
        });

        //View Contribution
    ViewContriButton.addActionListener(e -> {
    History.setSize(850, 450); // Made slightly wider for more columns
    History.setLocationRelativeTo(this);
    History.setLayout(null);

    JLabel SIDL = new JLabel("School ID: ");
    SIDL.setBounds(400, 13, 100, 35);
    JTextField searchField = new JTextField(50);
    searchField.setBounds(470, 13, 200, 35);
    JButton searchBtn = new JButton("Search");
    searchBtn.setBounds(680, 13, 100, 35);

    // Columns
    String[] columns = {"Transaction ID", "Student No", "Material", "Quantity", "Department", "Date"};
    DefaultTableModel model = new DefaultTableModel(columns, 0);
    JTable Table = new JTable(model);
    JScrollPane TableS = new JScrollPane(Table);
    TableS.setBounds(10, 60, 810, 330);

    searchBtn.addActionListener(searchEv -> {
        String studentID = searchField.getText().trim();
        if (studentID.isEmpty()) {
            JOptionPane.showMessageDialog(History, "Please enter a Student Number.");
            return;
        }

        try (Connection con = DBConnection.getConnection()) {
            // JOIN query to pull data from both tables
            String query = "SELECT T.TransactionID, C.StudentNo, C.MaterialType, C.Quantity, T.Department, T.CollectionDate " +
                           "FROM Transactions T " +
                           "INNER JOIN Contributions C ON T.ContributionID = C.ContributionID " +
                           "WHERE C.StudentNo = ?";
            
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, studentID);
            ResultSet rs = pst.executeQuery();
            
            model.setRowCount(0); // Clear table for new search

            boolean found = false;
            while (rs.next()) {
                found = true;
                model.addRow(new Object[]{
                    rs.getInt("TransactionID"),
                    rs.getInt("StudentNo"),
                    rs.getString("MaterialType"),
                    rs.getInt("Quantity"),
                    rs.getString("Department"),
                    rs.getTimestamp("CollectionDate") // Using Timestamp for DATETIME2
                });
            }

            if (!found) {
                JOptionPane.showMessageDialog(History, "No transactions found for Student: " + studentID);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(History, "Connection Error");
        }
    });
    History.add(TableS);
    History.add(SIDL);
    History.add(searchField);
    History.add(searchBtn);
    History.setVisible(true);
});
        
        //Main Panel
        MainP.setBackground(Color.WHITE);
        MainP.setPreferredSize(new Dimension(1, 1));
        MainP.setLayout(null);    
        StudentCL = new JLabel("Students Weekly");
        StudentCL.setFont(new Font("Arial Black", Font.BOLD, 60));
        StudentCL.setBounds(270, 20, 700, 80);
        ContributionL = new JLabel("Contribution");
        ContributionL.setFont(new Font("Arial Black", Font.BOLD, 60));
        ContributionL.setBounds(350, 100, 700, 80);
        ColumnChart.setBounds(30, 200, 1030, 460);

        //Adding Components
        MainF.add(header, BorderLayout.NORTH);
        header.add(UnivImageP);
        UnivImageP.add(UnivIcon);
        header.add(UniversityL);
        header.add(AdminL);
        header.add(AdminButton);
        MainF.add(leftPanel, BorderLayout.WEST);
        leftPanel.add(UserImageP);
        UserImageP.add(UserIcon);
        leftPanel.add(AddContriButton);
        leftPanel.add(UserL);
        leftPanel.add(ViewContriButton);
        MainF.add(MainP, BorderLayout.CENTER);
        MainP.add(StudentCL);
        MainP.add(ContributionL);
        MainP.add(ColumnChart);

        //Frame Settings
        MainF.setSize(1500, 800);
        MainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainF.setLocationRelativeTo(null);
        MainF.setVisible(true);
    }

    //Class for Admin Login
    class LoginFunction extends JFrame {

        public LoginFunction() {
            AdminButton.addActionListener(e -> {

                //Dialog Settings
                Login.setSize(550, 300);
                Login.setLocationRelativeTo(this);
                Login.setLayout(null);
                LoginL = new JLabel("LOGIN");
                LoginL.setFont(new Font("Arial Black", Font.BOLD, 25));
                LoginL.setBounds(220, 10, 150, 40);
                Login.add(LoginL);

                //Username
                UsernameL = new JLabel("Username:");
                UsernameL.setBounds(50, 60, 100, 25);
                Login.add(UsernameL);
                Usernamefield = new JTextField();
                Usernamefield.setBounds(115, 60, 285, 30);
                Login.add(Usernamefield);

                //Clear Username Button
                JButton clearUser = new JButton("Clear");
                clearUser.setBounds(410, 60, 80, 30);
                Login.add(clearUser);

                //Password Label
                PasswordL = new JLabel("Password:");
                PasswordL.setBounds(50, 110, 100, 25);
                Login.add(PasswordL);

                //Password Field
                Passworfield = new JTextField();
                Passworfield.setBounds(115, 110, 285, 30);
                Login.add(Passworfield);

                // Enter Button Logic for Admin Login
                EnterButton = new JButton("Enter");
                EnterButton.setBounds(150, 180, 100, 40);

                EnterButton.addActionListener(ev -> {
                String user = Usernamefield.getText().trim();
                String pass = Passworfield.getText().trim();

                // 1. Basic Validation
                if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(Login, "Please enter both Username and Password.", "Login Error", JOptionPane.WARNING_MESSAGE);
                return;
                }

                // 2. Database Connection and Verification
                try (Connection con = DBConnection.getConnection()) {
                if (con == null) {
                JOptionPane.showMessageDialog(Login, "Could not connect to Database.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                return;
                }

                // Query the Admins table
                String sql = "SELECT * FROM Admins WHERE Username = ? AND Password = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, user);
                pst.setString(2, pass);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    // Login Successful
                    JOptionPane.showMessageDialog(Login, "Welcome, " + user + "!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
                    new AdminFrame();
                    Login.dispose();
                    MainF.dispose();
                } else {
                    // Login Failed
                    JOptionPane.showMessageDialog(Login, "Invalid Username or Password.", "Access Denied", JOptionPane.ERROR_MESSAGE);
                    Passworfield.setText(""); // Clear password field for retry
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(Login, "System Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
                Login.add(EnterButton);

                //Cancel Button
                CancelButton = new JButton("Cancel");
                CancelButton.setBounds(270, 180, 100, 40);
                Login.add(CancelButton);

                //Logic
                clearUser.addActionListener(ev -> Usernamefield.setText(""));
                ClearButton.addActionListener(ev -> Passworfield.setText(""));
                CancelButton.addActionListener(ev -> Login.dispose());
                CancelButton.addActionListener(ev -> Contribution.dispose());

                Login.setVisible(true);
            });
        }

    }
}
