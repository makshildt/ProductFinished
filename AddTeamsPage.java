import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddTeamsPage extends JPanel {
    AddTeamsPage() {
        JLabel AddTeamsLabel = new JLabel("Enter Team Name:");
        add(AddTeamsLabel);
        JTextField AddTeamsTextField = new JTextField((10));
        add(AddTeamsTextField);
        JButton AddTeamsButton = new JButton("Submit");
        add(AddTeamsButton);
        AddTeamsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              String teamName = AddTeamsTextField.getText();
              try {
                Class.forName("org.sqlite.JDBC");
                Connection connection = DriverManager.getConnection("jdbc:sqlite:mydb.db");
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
                AddTeamsTextField.setText("");
            }
          });
    }
}






