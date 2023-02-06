import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class DelEmployeesPage extends JPanel{
    JComboBox empListLable;

    void refreshEmpList() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Mydb.db");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT first_name FROM employees");
            empListLable.removeAllItems();
            while (resultSet.next()) {
                empListLable.addItem(resultSet.getString("first_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    DelEmployeesPage() {
        JLabel DelEmpLabel = new JLabel("Select Employee to Delete:");
        add(DelEmpLabel);

        JComboBox AddEmpLabel6 = new JComboBox();
        empListLable = AddEmpLabel6;
        add(AddEmpLabel6);
        refreshEmpList();

        JButton AddEmpButton = new JButton("Delete");
        add(AddEmpButton);
        AddEmpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("org.sqlite.JDBC");
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:Mydb.db");
                    Statement statement = conn.createStatement();
                    statement.executeUpdate("DELETE FROM employees WHERE first_name = '" + empListLable.getSelectedItem() + "'");
                    conn.close();
                    JOptionPane.showMessageDialog(null, "Employee Deleted Successfully");
                    Pages.DelEmployeesPage.refreshEmpList();
                    Pages.Home.updateTable();
                    Pages.Home.updateEmps();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}

