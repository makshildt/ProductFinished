import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JPanel {
    JTable teamTable;
    String[] teamColumnNames = {"Teams: "};

    JTable taskTable;
    String[] taskColumnNames = {"Team Name", "Task", "Deadline"};

    JTable empTable;
    String[] empColumnNames = {"First Name", "Last Name", "Year of Birth", "Email", "Role", "Team Name"};

    Home() {
        JLabel HomeLabel = new JLabel("Welcome to the Employee Management System!");
        add(HomeLabel);
        
        //TASKS
        JButton taskButton = new JButton("Display Teams with Tasks");
        //====================
        taskTable = new JTable();
        JScrollPane taskScrollPane = new JScrollPane(taskTable);
        DefaultTableModel model = new DefaultTableModel(new Object[0][taskColumnNames.length], taskColumnNames);

        taskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });

        //EMPLOYEES
        JButton empButton = new JButton("Display Employees");
        //====================
        empTable = new JTable();
        JScrollPane empScrollPane = new JScrollPane(empTable);
        DefaultTableModel modelEmp = new DefaultTableModel(new Object[0][empColumnNames.length], empColumnNames);

        empButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmps();
            }
        });

        //TEAMS
        JButton teamButton = new JButton("Display Teams");
        //====================
        teamTable = new JTable();
        JScrollPane teamScrollPane = new JScrollPane(teamTable);
        DefaultTableModel teammodel = new DefaultTableModel(new Object[0][teamColumnNames.length], teamColumnNames);

        teamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTeams();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(teamButton, makeConstraints(0, 0));
        panel.add(taskButton, makeConstraints(1, 0));
        panel.add(empButton, makeConstraints(2, 0));
        panel.add(teamScrollPane, makeConstraints(0, 1));
        panel.add(taskScrollPane, makeConstraints(1, 1));
        panel.add(empScrollPane, makeConstraints(2, 1));
        add(panel);

    }
    //this
    private GridBagConstraints makeConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        return c;        
    }

    public void updateTable() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Mydb.db");

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM teams2");
            //int columnCount = resultSet.getMetaData().getColumnCount();
            ArrayList<Object[]> rows = new ArrayList<Object[]>();
            while (resultSet.next()) {
                Object[] row = new Object[taskColumnNames.length];
                for (int i = 1; i <= taskColumnNames.length; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                rows.add(row);
            }
            Object[][] data = rows.toArray(new Object[0][]);
            DefaultTableModel model = new DefaultTableModel(data, taskColumnNames);
            taskTable.setModel(model);                   
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void updateEmps() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Mydb.db");

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

            //int columnCountEmp = resultSet.getMetaData().getColumnCountEmp();
            ArrayList<Object[]> rows = new ArrayList<Object[]>();
            while (resultSet.next()) {
                Object[] row = new Object[empColumnNames.length];
                for (int i = 1; i <= empColumnNames.length; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                rows.add(row);
            }
            Object[][] data = rows.toArray(new Object[0][]);
            DefaultTableModel modelEmp = new DefaultTableModel(data, empColumnNames);
            empTable.setModel(modelEmp);                   
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void updateTeams() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Mydb.db");

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM teams");

            ArrayList<Object[]> rows = new ArrayList<Object[]>();
            while (resultSet.next()) {
                Object[] row = new Object[teamColumnNames.length];
                for (int i = 1; i <= teamColumnNames.length; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                rows.add(row);
            }
            Object[][] data = rows.toArray(new Object[0][]);
            DefaultTableModel teammodel = new DefaultTableModel(data, teamColumnNames);
            teamTable.setModel(teammodel);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
