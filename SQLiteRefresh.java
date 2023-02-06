import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteRefresh {
  private static Connection conn;

  public static void main(String[] args) {
    connectToDatabase();
    // perform some database operations
    refreshDatabase();
    // perform some more database operations
  }

  private static void connectToDatabase() {
    try {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection("jdbc:sqlite:Mydb.db");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static void refreshDatabase() {
    try {
      conn.close();
      connectToDatabase();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
