import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DelTDPage extends JPanel {
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

    void refreshTeam2List() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Mydb.db");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM teams2");
            teamListLable.removeAllItems();
            while (resultSet.next()) {
                teamListLable.addItem(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    DelTDPage() {
        JLabel AddEmpLabel = new JLabel("Select Team's Task to Delete:");
        add(AddEmpLabel);

        JComboBox AddEmpLabel6 = new JComboBox();
        teamListLable = AddEmpLabel6;
        add(AddEmpLabel6);
        refreshTeamList();
        refreshTeam2List();

        JButton AddEmpButton = new JButton("Delete");
        add(AddEmpButton);
        AddEmpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("org.sqlite.JDBC");
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:Mydb.db");
                    Statement statement = conn.createStatement();
                    //statement.executeUpdate("DELETE FROM teams WHERE name = '" + teamListLable.getSelectedItem() + "'");
                    statement.executeUpdate("DELETE FROM teams2 WHERE name = '" + teamListLable.getSelectedItem() + "'");
                    conn.close();
                    JOptionPane.showMessageDialog(null, "Task Deleted Successfully");
                    Pages.AddEmployeesPage.refreshTeamList();
                    Pages.DelTeamsPage.refreshTeamList();
                    Pages.AddTasksDeadlinesPage.refreshTeamList();
                    //Pages.AddTasksDeadlinesPage.refreshTeam2List();
                    Pages.DelTDPage.refreshTeamList();
                    Pages.DelTDPage.refreshTeam2List();
                    Pages.Home.updateTable();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });   
    }
}
