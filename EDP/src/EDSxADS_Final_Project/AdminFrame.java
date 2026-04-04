package EDSxADS_Final_Project;

// UI and Layout Imports
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

// Swing Component Imports
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

// Event Handling Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Database and SQL Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminFrame extends JFrame {

    // Variables (Preserved exactly)
    JFrame AdminF;
    JPanel mainheader, header, UnivImageP, customMenuBar, mainPanel, panel1, panel2, panel3, RecordP, subheader1, subheader2,dashboardP, studentP, plasticP, glassP, paperP, metalP, EWasteP, DepartmentDP;
    JTextField IDfield;
    JLabel UnivIcon, UniversityL, AdminL, TitleL, UnivIDL, DepartmentL1, DepartmentL2, MTypeL, DashT, RegisterL, StudentID, RemoveL, StudentT, RecycleBinL, LocationL, Status;
    JDialog RegisterD, DeleteD, BinsD;
    ImageIcon UnivL, ScaledUnivIcon;
    Image Logo;
    JButton AdminButton, RecordButton, RegisterButton, RemoveButton, DashboardButton, StudentButton, BinButton, SearchButton, AddButton, CancelButton, ConfirmRemoveBtn, EmptyBinButton;
    JButton StudentSearchButton;
    String[][] Values = {};
    String[] Atributes = {"Student ID", "Department", "Material Type", "Quantity", "Transaction", "Date"};
    String[] StudentAtributes = {"Student ID", "First Name", "Middle Name", "Last Name", "Course", "Section", "Specialization", "School Year"};
    JTable Table, StudentTable;
    JScrollPane TableS, StudentTableS;
    JTextField StudentIDfield;
    JLabel StudentIDL;
    JComboBox<String> StudentDTypeBox;
    ImageIcon AMainBack1, AMainBack2, AMainBack3;
    JComboBox<String> DTypeBox, MTypeBox, LocationBox;

    public AdminFrame() {
        // Containers & Arrays
        AdminF = new JFrame("University Recycle Zone");
        mainheader = new JPanel();
        header = new JPanel();
        UnivImageP = new JPanel();
        mainPanel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        customMenuBar = new JPanel();
        RecordP = new JPanel();
        subheader1 = new JPanel();
        studentP = new JPanel();
        subheader2 = new JPanel();

        // Added "All" to the arrays
        String[] DType = {"All Departments", "Information Technology", "Civil Engineering", "Business Administration", "Entrepreneurship", "Education", "Medical Technology", "Criminology"};
        String[] MType = {"All Materials", "Plastic", "Glass", "Paper", "Metal", "E-Waste"};
        String[] locations = {"Engineering Building", "Canteen", "E-Library", "Pimentel"};

        dashboardP = new JPanel();
        plasticP = new JPanel();
        glassP = new JPanel();
        paperP = new JPanel();
        metalP = new JPanel();
        EWasteP = new JPanel();
        DepartmentDP = new JPanel();

        //Set the values
        AdminStatsLoader loader = new AdminStatsLoader();

        //fetch the data using the named  class
        loader.updateDepartmentCounts();
        loader.updateMaterialCounts();

        int total = loader.getPlastic() + loader.getGlass() + loader.getPaper()
                + loader.getMetal() + loader.getEWaste();

        DonutChartPanel plasticChart = new DonutChartPanel(loader.getPlastic(), Color.GREEN, "Plastic");
        DonutChartPanel glassChart = new DonutChartPanel(loader.getGlass(), Color.CYAN, "Glass");
        DonutChartPanel paperChart = new DonutChartPanel(loader.getPaper(), Color.YELLOW, "Paper");
        DonutChartPanel metalChart = new DonutChartPanel(loader.getMetal(), Color.GRAY, "Metal");
        DonutChartPanel ewasteChart = new DonutChartPanel(loader.getEWaste(), Color.RED, "E-Waste");

        RegisterD = new JDialog();
        DeleteD = new JDialog();
        BinsD = new JDialog();

        // Backgrounds
        AMainBack1 = new ImageIcon("Admin_Background.jpg");
        JLabel AMbackground1 = new JLabel(AMainBack1);
        AMbackground1.setLayout(null);
        AMbackground1.setBounds(0, 0, 1500, 625);

        AMainBack2 = new ImageIcon("Admin_Background.jpg");
        JLabel AMbackground2 = new JLabel(AMainBack2);
        AMbackground2.setLayout(null);
        AMbackground2.setBounds(0, 0, 1500, 625);

        AMainBack3 = new ImageIcon("Admin_Background.jpg");
        JLabel AMbackground3 = new JLabel(AMainBack3);
        AMbackground3.setLayout(null);
        AMbackground3.setBounds(0, 0, 1500, 625);

        // Layouts
        AdminF.setLayout(new BorderLayout());
        mainheader.setLayout(new BorderLayout());
        customMenuBar.setLayout(new FlowLayout(FlowLayout.LEADING));
        panel1.setLayout(null);
        panel2.setLayout(null);
        RecordP.setLayout(new BorderLayout());
        subheader1.setLayout(null);
        subheader2.setLayout(null);
        dashboardP.setLayout(null);

        // Header Config
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

        AdminL = new JLabel("ADMIN");
        AdminL.setBounds(1240, 25, 120, 40);
        AdminL.setFont(new Font("Bodoni MT", Font.BOLD, 30));
        AdminButton = new JButton("Logout");
        AdminButton.setForeground(Color.red);
        AdminButton.setFont(new Font("Arial Black", Font.BOLD, 20));
        AdminButton.setBounds(1350, 20, 120, 50);

        // CardLayout Setup
        CardLayout cl = new CardLayout();
        mainPanel = new JPanel(cl);

        // Menu Bar
        customMenuBar.setBackground(Color.LIGHT_GRAY);
        customMenuBar.setPreferredSize(new Dimension(1500, 35));
        RecordButton = new JButton("Records");
        RegisterButton = new JButton("Register");
        RemoveButton = new JButton("Remove");
        DashboardButton = new JButton("Dashboard");
        StudentButton = new JButton("Students");
        BinButton = new JButton("Recycle Bin");

        // Panel 1 (Records)
        TitleL = new JLabel("Recycling Collection Monitoring System");
        TitleL.setFont(new Font("Bell MT", Font.BOLD, 50));
        TitleL.setBounds(310, 0, 1000, 100);
        RecordP.setBounds(150, 100, 1180, 700);
        subheader1.setBackground(Color.white);
        subheader1.setPreferredSize(new Dimension(50, 60));

        DepartmentL1 = new JLabel("Department: ");
        DepartmentL1.setBounds(30, 10, 100, 30);
        DTypeBox = new JComboBox<>(DType);
        DTypeBox.setBounds(105, 10, 170, 30);

        MTypeL = new JLabel("Material Type: ");
        MTypeL.setBounds(310, 10, 200, 30);
        MTypeBox = new JComboBox<>(MType);
        MTypeBox.setBounds(395, 10, 100, 30);

        UnivIDL = new JLabel("ID: ");
        UnivIDL.setBounds(860, 10, 30, 30);
        IDfield = new JTextField(50);
        IDfield.setBounds(880, 10, 200, 30);

        SearchButton = new JButton("Search");
        SearchButton.setBounds(1080, 10, 90, 30);

        DefaultTableModel model = new DefaultTableModel(Atributes, 0);
        Table = new JTable(model);
        Table.getTableHeader().setReorderingAllowed(false);
        TableS = new JScrollPane(Table);

        // Panel 2 (Dashboard)
        DashT = new JLabel("Dashboard");
        DashT.setFont(new Font("Bell MT", Font.BOLD, 50));
        DashT.setBounds(50, 0, 1000, 100);
        dashboardP.setBackground(Color.white);
        dashboardP.setBounds(50, 100, 1385, 500);

        plasticChart.setBounds(10, 35, 250, 200);
        glassChart.setBounds(270, 35, 250, 200);
        paperChart.setBounds(530, 35, 250, 200);
        metalChart.setBounds(140, 270, 250, 200);
        ewasteChart.setBounds(400, 270, 250, 200);

        DepartmentDP.setBounds(800, 10, 570, 480);

        //Dashboard Panel Components
        dashboardP.add(plasticChart);
        dashboardP.add(glassChart);
        dashboardP.add(paperChart);
        dashboardP.add(metalChart);
        dashboardP.add(ewasteChart);
        dashboardP.add(DepartmentDP);

        //Panel 3 (Student)
        StudentT = new JLabel("Student Records");
        StudentT.setFont(new Font("Bell MT", Font.BOLD, 50));
        StudentT.setBounds(565, 0, 1000, 100);
        studentP.setLayout(new BorderLayout());
        studentP.setBounds(150, 100, 1180, 700);
        subheader2.setBackground(Color.white);
        subheader2.setPreferredSize(new Dimension(1180, 60));

        DepartmentL2 = new JLabel("Department: ");
        DepartmentL2.setBounds(30, 10, 100, 30);
        StudentDTypeBox = new JComboBox<>(DType);
        StudentDTypeBox.setBounds(105, 10, 170, 30);

        StudentIDL = new JLabel("ID: ");
        StudentIDL.setBounds(860, 10, 30, 30);
        StudentIDfield = new JTextField(50);
        StudentIDfield.setBounds(880, 10, 200, 30);

        StudentSearchButton = new JButton("Search");
        StudentSearchButton.setBounds(1080, 10, 90, 30);

        DefaultTableModel model2 = new DefaultTableModel(StudentAtributes, 0);
        StudentTable = new JTable(model2);
        StudentTable.getTableHeader().setReorderingAllowed(false);
        StudentTableS = new JScrollPane(StudentTable);

        // Navigation
        RecordButton.addActionListener(new PanelSwitcher(mainPanel, cl, "panel1"));
        DashboardButton.addActionListener(new PanelSwitcher(mainPanel, cl, "panel2"));
        StudentButton.addActionListener(new PanelSwitcher(mainPanel, cl, "panel3"));

        // Load initial table data automatically
        FilterRecords initialRecordFilter = new FilterRecords(IDfield, DTypeBox, MTypeBox, Table);
        FilterStudents initialStudentFilter = new FilterStudents(StudentIDfield, StudentDTypeBox, StudentTable);
        initialRecordFilter.actionPerformed(null);
        initialStudentFilter.actionPerformed(null);

        // Logout
        AdminButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Logout?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                AdminF.dispose();
                new UniversityRecycleZone();
            }
        });

        // Search Action
        SearchButton.addActionListener(initialRecordFilter);

        // Student Search Action
        StudentSearchButton.addActionListener(initialStudentFilter);

        // Register Dialog Trigger
        RegisterButton.addActionListener(e -> {
            RegisterD = new JDialog(AdminF, "Register Student", true);
            RegisterD.setSize(500, 315);
            RegisterD.setLocationRelativeTo(null);
            RegisterD.setLayout(null);

            RegisterL = new JLabel("Register Student");
            RegisterL.setFont(new Font("Arial", Font.BOLD, 20));
            RegisterL.setBounds(165, 10, 300, 50);

            StudentID = new JLabel("Student ID: ");
            StudentID.setBounds(50, 60, 100, 50);
            JTextField regID = new JTextField();
            regID.setBounds(120, 70, 300, 30);

            DepartmentL1 = new JLabel("Department: ");
            DepartmentL1.setBounds(45, 130, 100, 50);

            
            String[] regDeptsOnly = java.util.Arrays.copyOfRange(DType, 1, DType.length);
            JComboBox<String> regDept = new JComboBox<>(regDeptsOnly);
            regDept.setBounds(120, 140, 300, 30);

            AddButton = new JButton("Add Student");
            AddButton.setBounds(80, 200, 150, 50);
            AddButton.addActionListener(new AddStudent(RegisterD, regID, regDept));

            CancelButton = new JButton("Cancel");
            CancelButton.setBounds(250, 200, 150, 50);
            CancelButton.addActionListener(ev -> RegisterD.dispose());

            RegisterD.add(RegisterL);
            RegisterD.add(StudentID);
            RegisterD.add(regID);
            RegisterD.add(DepartmentL1);
            RegisterD.add(regDept);
            RegisterD.add(AddButton);
            RegisterD.add(CancelButton);
            RegisterD.setVisible(true);
        });

        // Remove Dialog Trigger
        RemoveButton.addActionListener(e -> {
            DeleteD = new JDialog(AdminF, "Remove Student", true);
            DeleteD.setSize(500, 250);
            DeleteD.setLocationRelativeTo(null);
            DeleteD.setLayout(null);

            RemoveL = new JLabel("Remove Student");
            RemoveL.setFont(new Font("Arial", Font.BOLD, 20));
            RemoveL.setBounds(165, 10, 300, 50);

            StudentID = new JLabel("Student ID: ");
            StudentID.setBounds(50, 60, 100, 50);
            JTextField delID = new JTextField();
            delID.setBounds(120, 70, 300, 30);

            ConfirmRemoveBtn = new JButton("Remove Student");
            ConfirmRemoveBtn.setBounds(80, 130, 150, 50);
            ConfirmRemoveBtn.addActionListener(new RemoveStudent(DeleteD, delID));

            CancelButton = new JButton("Cancel");
            CancelButton.setBounds(250, 130, 150, 50);
            CancelButton.addActionListener(ev -> DeleteD.dispose());

            DeleteD.add(RemoveL);
            DeleteD.add(StudentID);
            DeleteD.add(delID);
            DeleteD.add(ConfirmRemoveBtn);
            DeleteD.add(CancelButton);
            DeleteD.setVisible(true);
        });

        //Bins Dialog Trigger
        BinButton.addActionListener(e -> {
            BinsD = new JDialog(AdminF, "Recycle Bin Status", true);
            BinsD.setSize(500, 300);
            BinsD.setLocationRelativeTo(null);
            BinsD.setLayout(null);

            RecycleBinL = new JLabel("Recycle Bin Status");
            RecycleBinL.setFont(new Font("Arial", Font.BOLD, 20));
            RecycleBinL.setBounds(155, 10, 300, 50);

            LocationL = new JLabel("Location: ");
            LocationL.setBounds(50, 70, 70, 30);
            LocationBox = new JComboBox<>(locations);
            LocationBox.setBounds(120, 70, 300, 30);

            Status = new JLabel("Status: ");
            Status.setBounds(50, 120, 70, 30);
            JTextArea statusArea = new JTextArea();
            statusArea.setBounds(120, 120, 300, 30);
            statusArea.setEditable(false);

            // Load status for the default selected location
            statusArea.setText(getBinStatus(LocationBox.getSelectedItem().toString()));

            // Update status 
            LocationBox.addActionListener(ev -> {
                String selected = LocationBox.getSelectedItem().toString();
                statusArea.setText(getBinStatus(selected));
            });

            EmptyBinButton = new JButton("Empty Bin");
            EmptyBinButton.setBounds(80, 200, 150, 50);
            EmptyBinButton.addActionListener(ev -> {
                String loc = LocationBox.getSelectedItem().toString();
                try (Connection con = DBConnection.getConnection()) {
                    PreparedStatement pst = con.prepareStatement("UPDATE RecycleBins SET Status = 'Empty' WHERE Location = ?");
                    pst.setString(1, loc);
                    if (pst.executeUpdate() > 0) {
                        statusArea.setText("Empty");
                        JOptionPane.showMessageDialog(BinsD, "Bin at " + loc + " has been emptied.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(BinsD, "Error emptying bin.");
                }
            });


            CancelButton = new JButton("Cancel");
            CancelButton.setBounds(250, 200, 150, 50);
            CancelButton.addActionListener(ev -> BinsD.dispose());

            BinsD.add(RecycleBinL);
            BinsD.add(LocationL);
            BinsD.add(LocationBox);
            BinsD.add(Status);
            BinsD.add(statusArea);
            BinsD.add(EmptyBinButton);
            BinsD.add(CancelButton);
            BinsD.setVisible(true);
        });
        
        // Dashboard (Pie Chart)
        //instantiate variables
        String[] depts = {"IT", "Engineering", "Business", "Education", "Medical", "Criminology"};
        double[] vals = loader.getCountsArray();
        Color[] cols = {Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.PINK};

        //creation of the chart
        PieChartPanel pieChart = new PieChartPanel(depts, vals, cols);
        DepartmentDP.setLayout(new BorderLayout());
        DepartmentDP.add(pieChart, BorderLayout.CENTER);

        // UI Assembly
        AdminF.add(mainheader, BorderLayout.NORTH);
        mainheader.add(header, BorderLayout.NORTH);
        mainheader.add(customMenuBar, BorderLayout.CENTER);
        customMenuBar.add(RecordButton);
        customMenuBar.add(RegisterButton);
        customMenuBar.add(RemoveButton);
        customMenuBar.add(DashboardButton);
        customMenuBar.add(StudentButton);
        customMenuBar.add(BinButton);

        //Main Panel
        AdminF.add(mainPanel, BorderLayout.CENTER);

        //Panel 1 Components
        mainPanel.add(panel1, "panel1");
        panel1.add(AMbackground1);
        AMbackground1.add(TitleL);
        AMbackground1.add(RecordP);
        RecordP.add(subheader1, BorderLayout.NORTH);
        subheader1.add(DepartmentL1);
        subheader1.add(DTypeBox);
        subheader1.add(MTypeL);
        subheader1.add(MTypeBox);
        subheader1.add(UnivIDL);
        subheader1.add(IDfield);
        subheader1.add(SearchButton);
        RecordP.add(TableS, BorderLayout.CENTER);
        

        //Panel 2 Components
        mainPanel.add(panel2, "panel2");
        panel2.add(AMbackground2);
        AMbackground2.add(DashT);
        AMbackground2.add(dashboardP);

        //Panel 3 Components
        mainPanel.add(panel3, "panel3");
        panel3.setLayout(new BorderLayout());
        panel3.add(AMbackground3);
        AMbackground3.add(StudentT);
        AMbackground3.add(studentP);
        studentP.add(subheader2, BorderLayout.NORTH);
        subheader2.add(DepartmentL2);
        subheader2.add(StudentDTypeBox);
        subheader2.add(StudentIDL);
        subheader2.add(StudentIDfield);
        subheader2.add(StudentSearchButton);
        studentP.add(StudentTableS, BorderLayout.CENTER);

        header.add(UnivImageP);
        UnivImageP.add(UnivIcon);
        header.add(UniversityL);
        header.add(AdminL);
        header.add(AdminButton);

        AdminF.setSize(1500, 800);
        AdminF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AdminF.setLocationRelativeTo(null);
        AdminF.setVisible(true);
    }

    // Named class
    class PanelSwitcher implements ActionListener {

        private JPanel mainPanel;
        private CardLayout cl;
        private String target;

        public PanelSwitcher(JPanel mp, CardLayout c, String t) {
            this.mainPanel = mp;
            this.cl = c;
            this.target = t;
        }

        public void actionPerformed(ActionEvent e) {
            cl.show(mainPanel, target);
        }
    }

    class AddStudent implements ActionListener {

        private JDialog parent;
        private JTextField idF;
        private JComboBox<String> dBox;

        public AddStudent(JDialog p, JTextField i, JComboBox<String> d) {
            this.parent = p;
            this.idF = i;
            this.dBox = d;
        }

        public void actionPerformed(ActionEvent e) {
            try (Connection con = DBConnection.getConnection()) {
                PreparedStatement pst = con.prepareStatement("INSERT INTO Students (StudentNo, Department) VALUES (?, ?)");
                pst.setInt(1, Integer.parseInt(idF.getText().trim()));
                pst.setString(2, dBox.getSelectedItem().toString());
                if (pst.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(parent, "Student Registered!");
                    parent.dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(parent, "Error: " + ex.getMessage());
            }
        }
    }

    class RemoveStudent implements ActionListener {

        private JDialog parent;
        private JTextField idF;

        public RemoveStudent(JDialog p, JTextField i) {
            this.parent = p;
            this.idF = i;
        }

        public void actionPerformed(ActionEvent e) {

            //input checker
            String idText = idF.getText().trim();

            // Guard clause for non-numeric input
            if (idText.isEmpty() || !idText.matches("\\d+")) {
                JOptionPane.showMessageDialog(parent, "Please enter a valid numeric Student ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(parent, "Delete student and all related records?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try (Connection con = DBConnection.getConnection()) {
                    PreparedStatement pst = con.prepareStatement("DELETE FROM Students WHERE StudentNo = ?");
                    pst.setInt(1, Integer.parseInt(idF.getText().trim()));
                    if (pst.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(parent, "Record Deleted.");
                        parent.dispose();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(parent, "Error: " + ex.getMessage());
                }
            }
        }
    }

    class FilterRecords implements ActionListener {

        private JTextField idF;
        private JComboBox<String> dBox;
        private JComboBox<String> mBox;
        private JTable table;

        public FilterRecords(JTextField i, JComboBox<String> d, JComboBox<String> m, JTable t) {
            this.idF = i;
            this.dBox = d;
            this.mBox = m;
            this.table = t;
        }

        public void actionPerformed(ActionEvent e) {
            String id = idF.getText().trim();
            String dept = dBox.getSelectedItem().toString();
            String mat = mBox.getSelectedItem().toString();
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            // Convert "All" selections to SQL wildcard (%)
            String deptSearch = dept.equals("All Departments") ? "%" : dept;
            String matSearch = mat.equals("All Materials") ? "%" : mat;

            try (Connection con = DBConnection.getConnection()) {
                // Using LIKE allows '%' to act as a wildcard for 'All'
                String sql = "SELECT t.StudentNo, s.Department, t.MaterialType, t.Quantity, t.TransactionID, t.CollectionDate "
                        + "FROM Transactions t "
                        + "INNER JOIN Students s ON t.StudentNo = s.StudentNo "
                        + "WHERE (t.StudentNo = ? OR ? = '') "
                        + "AND s.Department LIKE ? "
                        + "AND t.MaterialType LIKE ?";

                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, id);
                pst.setString(3, deptSearch);
                pst.setString(4, matSearch);

                ResultSet rs = pst.executeQuery();
                model.setRowCount(0);
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("StudentNo"),
                        rs.getString("Department"),
                        rs.getString("MaterialType"),
                        rs.getInt("Quantity"),
                        rs.getInt("TransactionID"),
                        rs.getTimestamp("CollectionDate")
                    });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Search Error: " + ex.getMessage());
            }
        }
    }

    class FilterStudents implements ActionListener {

        private JTextField idF;
        private JComboBox<String> dBox;
        private JTable table;

        public FilterStudents(JTextField i, JComboBox<String> d, JTable t) {
            this.idF = i;
            this.dBox = d;
            this.table = t;
        }

        public void actionPerformed(ActionEvent e) {
            String id = idF.getText().trim();
            String dept = dBox.getSelectedItem().toString();
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            String deptSearch = dept.equals("All Departments") ? "%" : dept;

            try (Connection con = DBConnection.getConnection()) {
                String sql = "SELECT StudentNo, FirstName, MiddleName, LastName, Course, Section, Specialization, SchoolYear "
                        + "FROM Students "
                        + "WHERE (StudentNo = ? OR ? = '') "
                        + "AND Department LIKE ?";

                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, id);
                pst.setString(3, deptSearch);

                ResultSet rs = pst.executeQuery();
                model.setRowCount(0);
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("StudentNo"),
                        rs.getString("FirstName"),
                        rs.getString("MiddleName"),
                        rs.getString("LastName"),
                        rs.getString("Course"),
                        rs.getString("Section"),
                        rs.getString("Specialization"),
                        rs.getString("SchoolYear")
                    });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Search Error: " + ex.getMessage());
            }
        }
    }

    private String getBinStatus(String location) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement(
                "SELECT Status FROM RecycleBins WHERE Location = ?");
            pst.setString(1, location);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString("Status"); // Expected values: "Empty", "Half-Empty", "Full"
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Unknown";
    }

    class AdminStatsLoader {

        private int itCount = 0;
        private int engCount = 0;
        private int busCount = 0;
        private int eduCount = 0;
        private int medCount = 0;
        private int crimCount = 0;

        private int plasticTot, glassTot, paperTot, metalTot, eWasteTot;

        //method for fetching Material count
        public void updateMaterialCounts() {

            plasticTot = glassTot = paperTot = metalTot = eWasteTot = 0;
            String sql = "SELECT MaterialType, "
                    + "(SUM(Quantity) * 100.0 / (SELECT SUM(Quantity) FROM Transactions)) AS Total "
                    + "FROM Transactions GROUP BY MaterialType";

            try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    String mat = rs.getString("MaterialType");
                    double total = rs.getDouble("Total");

                    switch (mat) {
                        case "Plastic":
                            plasticTot = (int) Math.round(total);
                            break;
                        case "Glass":
                            glassTot = (int) Math.round(total);
                            break;
                        case "Paper":
                            paperTot = (int) Math.round(total);
                            break;
                        case "Metal":
                            metalTot = (int) Math.round(total);
                            break;
                        case "E-Waste":
                            eWasteTot = (int) Math.round(total);
                            break;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //method for fetching Deparment counts
        public void updateDepartmentCounts() {

            itCount = engCount = busCount = eduCount = medCount = crimCount = 0;

            String sql = "SELECT Department, COUNT(*) AS Total FROM Students GROUP BY Department";

            try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    String dept = rs.getString("Department");
                    int total = rs.getInt("Total");

                    //
                    switch (dept) {
                        case "Information Technology":
                            itCount = total;
                            break;
                        case "Civil Engineering":
                            engCount = total;
                            break;
                        case "Business Administration":
                            busCount = total;
                            break;
                        case "Education":
                            eduCount = total;
                            break;
                        case "Medical Technology":
                            medCount = total;
                            break;
                        case "Criminology":
                            crimCount = total;
                            break;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // return the val to an array
        public double[] getCountsArray() {
            return new double[]{itCount, engCount, busCount, eduCount, medCount, crimCount};
        }

        //return material val to the charts
        public int getPlastic() {
            return plasticTot;
        }

        public int getGlass() {
            return glassTot;
        }

        public int getPaper() {
            return paperTot;
        }

        public int getMetal() {
            return metalTot;
        }

        public int getEWaste() {
            return eWasteTot;
        }
    }
}