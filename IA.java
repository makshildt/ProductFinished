import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class IA {
    public static void main(String[] args) {
        new IA();
        new DisplaySQLiteDataInJTable();
    }

    JFrame mainFrame;
    JTabbedPane tabbedPane;
    JPanel page1;

    IA() {
        mainFrame = new JFrame("IA");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1400, 550);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        tabbedPane = new JTabbedPane();
        mainFrame.add(tabbedPane);

        page1 = new JPanel();

        Pages.Home = new Home();
        tabbedPane.addTab("Home", Pages.Home);
        
        //teams
        Pages.AddTeamsPage = new AddTeamsPage();
        tabbedPane.addTab("Add Teams", Pages.AddTeamsPage);

        Pages.DelTeamsPage = new DelTeamsPage();
        tabbedPane.addTab("Delete Teams", Pages.DelTeamsPage);

        //employees
        Pages.AddEmployeesPage = new AddEmployeesPage();
        tabbedPane.addTab("Add Employees", Pages.AddEmployeesPage);

        Pages.DelEmployeesPage = new DelEmployeesPage();
        tabbedPane.addTab("Delete Employees", Pages.DelEmployeesPage);

        //tasks
        Pages.AddTasksDeadlinesPage = new AddTasksDeadlinesPage();
        tabbedPane.addTab("Add Tasks/Deadlines", Pages.AddTasksDeadlinesPage);

        Pages.DelTDPage = new DelTDPage();
        tabbedPane.addTab("Delete Tasks/Deadlines", Pages.DelTDPage);
    }
}

