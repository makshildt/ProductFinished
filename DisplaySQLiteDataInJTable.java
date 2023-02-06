import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DisplaySQLiteDataInJTable {
  public static void main(String[] args) {
    try {
      // Step 1: Connect to the database
      Class.forName("org.sqlite.JDBC");
      Connection connection = DriverManager.getConnection("jdbc:sqlite:mydb.db");

      // Step 2: Retrieve data from the database
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM teams2");

      // Step 3: Convert ResultSet to Object array
      int columnCount = resultSet.getMetaData().getColumnCount();
      Object[][] data = new Object[100][columnCount];
      int row = 0;
      while (resultSet.next()) {
        for (int col = 0; col < columnCount; col++) {
          data[row][col] = resultSet.getObject(col + 1);
        }
        row++;
      }

      // Step 4: Set the data to JTable
      String[] columnNames = {"Team Name", "Task", "Deadline"};
      DefaultTableModel model = new DefaultTableModel(data, columnNames);
      JTable table = new JTable(model);

      // Step 5: Create JFrame and add the JTable to it
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(new JScrollPane(table));
      frame.setSize(500, 500);
      frame.setVisible(true);

      // Step 6: Close the connection
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

