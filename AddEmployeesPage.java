import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddEmployeesPage extends JPanel {
    JComboBox teamListLable;

    void refreshTeamList() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Mydb.db");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM teams");
            teamListLable.removeAllItems();
            while (resultSet.next()) {
                teamListLable.addItem(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    AddEmployeesPage() {
        JLabel AddEmpLabel = new JLabel("First Name:");
        add(AddEmpLabel);
        JTextField AddEmpTextField = new JTextField((10));
        add(AddEmpTextField);

        JLabel AddEmpLabel2 = new JLabel("Last Name:");
        add(AddEmpLabel2);
        JTextField AddEmpTextField2 = new JTextField((10));
        add(AddEmpTextField2);

        JLabel AddEmpLabel3 = new JLabel("Year of Birth:");
        add(AddEmpLabel3);
        JTextField AddEmpTextField3 = new JTextField((10));
        add(AddEmpTextField3);

        JLabel AddEmpLabel4 = new JLabel("Email:");
        add(AddEmpLabel4);
        JTextField AddEmpTextField4 = new JTextField((10));
        add(AddEmpTextField4);

        JLabel AddEmpLabel5 = new JLabel("Role:");
        add(AddEmpLabel5);
        JTextField AddEmpTextField5 = new JTextField((10));
        add(AddEmpTextField5);

        JLabel ComboBoxTeams = new JLabel("Team:");
        add(ComboBoxTeams);

        JComboBox AddEmpLabel6 = new JComboBox();
        teamListLable = AddEmpLabel6;
        add(AddEmpLabel6);
        refreshTeamList();

        JButton AddEmpButton = new JButton("Submit");
        add(AddEmpButton);
        AddEmpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String FirstName = AddEmpTextField.getText();
                String LastName = AddEmpTextField2.getText();
                String YearOfBirth = AddEmpTextField3.getText();
                String Email = AddEmpTextField4.getText();
                String Role = AddEmpTextField5.getText();
                String Team = AddEmpLabel6.getSelectedItem().toString();
                try {
                    Class.forName("org.sqlite.JDBC");
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:Mydb.db");
                    PreparedStatement statement = connection.prepareStatement(
                            "INSERT INTO employees (first_name, last_name, year_of_birth, email, role, team) VALUES (?, ?, ?, ?, ?, ?)");
                    statement.setString(1, FirstName);
                    statement.setString(2, LastName);
                    statement.setString(3, YearOfBirth);
                    statement.setString(4, Email);
                    statement.setString(5, Role);
                    statement.setString(6, Team);
                    statement.executeUpdate();
                    connection.close();
                    JOptionPane.showMessageDialog(null, "Employee Added Successfully");
                    Pages.DelEmployeesPage.refreshEmpList();
                    Pages.Home.updateTable();
                    Pages.Home.updateEmps();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                AddEmpTextField.setText("");
                AddEmpTextField2.setText("");
                AddEmpTextField3.setText("");
                AddEmpTextField4.setText("");
                AddEmpTextField5.setText("");
            }
        });
    }
}

