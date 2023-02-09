import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddTeamsPage extends JPanel {
    AddTeamsPage() {
        JTextField firstName = addTextField("Enter Team Name: ");
        // JLabel AddTeamsLabel = new JLabel("Enter Team Name:");
        // add(AddTeamsLabel);
        // JTextField AddTeamsTextField = new JTextField((10));
        // add(AddTeamsTextField);
        JButton AddTeamsButton = new JButton("Submit");
        add(AddTeamsButton);
        AddTeamsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //String teamName = AddTeamsTextField.getText();
              String teamName = firstName.getText();
              try {
                Class.forName("org.sqlite.JDBC");
                Connection connection = DriverManager.getConnection("jdbc:sqlite:mydb.db");
                if (teamName.equals("")) {
                  JOptionPane.showMessageDialog(null, "Please enter a team name");
                  return;
                }
                //write an elseif statement to check if the team name already exists in the teams table
                //if it does, display a message saying "Team already exists"
                //if it doesn't, add the team to the teams table
                PreparedStatement statement2 = connection.prepareStatement("SELECT name FROM teams WHERE name = ?");
                statement2.setString(1, teamName);
                ResultSet resultSet = statement2.executeQuery();
                if (resultSet.next()) {
                  JOptionPane.showMessageDialog(null, "Team already exists");
                  firstName.setText("");
                  return;
                }

                PreparedStatement statement = connection.prepareStatement("INSERT INTO teams (name) VALUES (?)");
                statement.setString(1, teamName);
                statement.executeUpdate();
                connection.close();
                JOptionPane.showMessageDialog(null, "Team Added Successfully");
                Pages.AddEmployeesPage.refreshTeamList();
                Pages.DelTeamsPage.refreshTeamList();
                Pages.AddTasksDeadlinesPage.refreshTeamList();
                Pages.Home.updateTable();
                Pages.Home.updateTeams();
                } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                } 
                //AddTeamsTextField.setText("");
                firstName.setText("");
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






