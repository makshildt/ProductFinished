import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;

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
        JTextField empField1 = addTextField("First Name:");
        JTextField empField2 = addTextField("Last Name:");
        JTextField empField3 = addTextField("Year of Birth:");
        JTextField empField4 = addTextField("Email:");
        JTextField empField5 = addTextField("Role:");

        // JLabel AddEmpLabel = new JLabel("First Name:");
        // add(AddEmpLabel);
        // JTextField AddEmpTextField = new JTextField((10));
        // add(AddEmpTextField);

        // JLabel AddEmpLabel2 = new JLabel("Last Name:");
        // add(AddEmpLabel2);
        // JTextField AddEmpTextField2 = new JTextField((10));
        // add(AddEmpTextField2);

        // JLabel AddEmpLabel3 = new JLabel("Year of Birth:");
        // add(AddEmpLabel3);
        // JTextField AddEmpTextField3 = new JTextField((10));
        // add(AddEmpTextField3);

        // JLabel AddEmpLabel4 = new JLabel("Email:");
        // add(AddEmpLabel4);
        // JTextField AddEmpTextField4 = new JTextField((10));
        // add(AddEmpTextField4);

        // JLabel AddEmpLabel5 = new JLabel("Role:");
        // add(AddEmpLabel5);
        // JTextField AddEmpTextField5 = new JTextField((10));
        // add(AddEmpTextField5);

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
                String FirstName = empField1.getText();
                String LastName = empField2.getText();
                String YearOfBirth = empField3.getText();
                String Email = empField4.getText();
                String Role = empField5.getText();
                String Team = AddEmpLabel6.getSelectedItem().toString();
                try {
                    Class.forName("org.sqlite.JDBC");
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:Mydb.db");
                    //write an if statement make sure no fields are empty
                    if (FirstName.isEmpty() || LastName.isEmpty() || YearOfBirth.isEmpty() || Email.isEmpty() || Role.isEmpty() || Team.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields");
                        return;
                    }
                    //write an if statement to make sure the year of birth is a number
                    try {
                        Integer.parseInt(YearOfBirth);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Year of Birth must be a number");
                        return;
                    }
                    //write an if statement to make sure email is unique
                    Statement statement1 = connection.createStatement();
                    ResultSet resultSet = statement1.executeQuery("SELECT email FROM employees");
                    while (resultSet.next()) {
                        if (resultSet.getString("email").equals(Email)) {
                            JOptionPane.showMessageDialog(null, "Email already exists");
                            empField4.setText("");
                            return;
                        }
                    }
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
                empField1.setText("");
                empField2.setText("");
                empField3.setText("");
                empField4.setText("");
                empField5.setText("");
            }
        });
    }

    public void addField(String name, JComponent component) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(name));
        panel.add(component);
        add(panel);
    }
  
    public JTextField addTextField(String name) {
        JTextField textField = new JTextField(10);
        addField(name, textField);
        return textField;
    }
}

