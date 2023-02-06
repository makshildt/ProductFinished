// import javax.swing.JButton;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.JTable;
// import javax.swing.table.DefaultTableModel;

// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;


// public class ViewEmployeesPage extends JPanel {
//     JTable tableEmp;
//     String[] columnNamesEmp = {"First Name", "Last Name", "Team Name"};

//     ViewEmployeesPage() {
        
//         //++++++++++++++++++++
//         JButton buttonEmp = new JButton("Display Employees");
//         add(buttonEmp);
//         //====================
//         tableEmp = new JTable();
//         JScrollPane scrollPaneEmp = new JScrollPane(tableEmp);
//         DefaultTableModel modelEmp = new DefaultTableModel(new Object[0][columnNamesEmp.length], columnNamesEmp);
//         add(scrollPaneEmp);

//         buttonEmp.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 updateEmps();
//             }
//         });

//     }
// }
