import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class ButtonExample {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Button Example");
    JPanel panel = new JPanel();
    JLabel label1 = new JLabel("Name:");
    JLabel label2 = new JLabel("Age:");
    JTextField textField1 = new JTextField(10);
    JTextField textField2 = new JTextField(10);
    JButton button = new JButton("Insert");
    panel.add(label1);
    panel.add(textField1);
    panel.add(label2);
    panel.add(textField2);
    panel.add(button);
    frame.add(panel);
    frame.setSize(300, 300);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String name = textField1.getText();
        int age = Integer.parseInt(textField2.getText());
        try {
          Class.forName("org.sqlite.JDBC");
          Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");
          PreparedStatement statement = connection.prepareStatement("INSERT INTO person (name, age) VALUES (?,?)");
          statement.setString(1, name);
          statement.setInt(2, age);
          statement.executeUpdate();
          connection.close();
          JOptionPane.showMessageDialog(null, "Data Inserted Successfully");
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, ex.getMessage());
        }
      }
    });
  }
}
