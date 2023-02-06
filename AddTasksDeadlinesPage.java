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

public class AddTasksDeadlinesPage extends JPanel {
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

    // void refreshTeam2List() {
    //     try {
    //         Class.forName("org.sqlite.JDBC");
    //         Connection conn = DriverManager.getConnection("jdbc:sqlite:Mydb.db");
    //         Statement statement = conn.createStatement();
    //         ResultSet resultSet = statement.executeQuery("SELECT name FROM teams2");
    //         teamListLable.removeAllItems();
    //         while (resultSet.next()) {
    //             teamListLable.addItem(resultSet.getString("name"));
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    AddTasksDeadlinesPage() {

        JLabel ComboBoxTD = new JLabel("Team:");
        add(ComboBoxTD);

        JComboBox AddEmpLabel6 = new JComboBox();
        teamListLable = AddEmpLabel6;
        add(AddEmpLabel6);
        refreshTeamList();

        JLabel AddTDLabel = new JLabel("Enter Task Name:");
        add(AddTDLabel);
        JTextField AddTDTextField = new JTextField((10));
        add(AddTDTextField);

        JLabel AddTDLabel2 = new JLabel("Enter Deadline Date:");
        add(AddTDLabel2);
        JTextField AddTDTextField2 = new JTextField((10));
        add(AddTDTextField2);

        JButton AddTDButton = new JButton("Submit");
        add(AddTDButton);
        AddTDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = AddEmpLabel6.getSelectedItem().toString();
                String task = AddTDTextField.getText();
                String deadline = AddTDTextField2.getText();
                try {
                    Class.forName("org.sqlite.JDBC");
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:Mydb.db");
                    PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO teams2 (name, task, deadline) VALUES (?, ?, ?)");
                    statement.setString(1, name);
                    statement.setString(2, task);
                    statement.setString(3, deadline);
                    statement.executeUpdate();
                    connection.close();
                    JOptionPane.showMessageDialog(null, "Task Added Successfully");
                    Pages.DelTDPage.refreshTeamList();
                    Pages.DelTDPage.refreshTeam2List();
                    Pages.Home.updateTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                AddTDTextField.setText("");
                AddTDTextField2.setText("");
            }
        });
    }
}
