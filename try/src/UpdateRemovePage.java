import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateRemovePage implements ActionListener {
    JButton backButton = new JButton("Back");

    JButton submitButton = new JButton("Submit");
    JFrame frame = new JFrame();
    JLabel label = new JLabel();

    JTable updateRemoveTable;


    UpdateRemovePage(){


        //data for the table
        Object[][] data = {
                {"John smith", 25, "timisoara", 242423224, 2000 + "$"},

        };

        // Column
        String[] columns = {"Full Name", "Age", "Address", "Phone Number", "Salary"};


        DefaultTableModel model = new DefaultTableModel(data, columns);

        updateRemoveTable = new JTable(model);


        updateRemoveTable.getTableHeader().setReorderingAllowed(false);

        // Allow columns to resize based on content
        updateRemoveTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Set preferred column width for each column header
        for (int i = 0; i < columns.length; i++) {
            TableColumn column = updateRemoveTable.getColumnModel().getColumn(i);
            column.setHeaderValue(columns[i]);
            int headerWidth = column.getHeaderValue().toString().length() * 8;
            column.setPreferredWidth(headerWidth);
        }

        JScrollPane scrollPane = new JScrollPane(updateRemoveTable);
        scrollPane.setBounds(50, 50, 400, 300);


        frame.add(scrollPane);




        backButton.setBounds(280, 400, 100, 20);
        frame.add(backButton);
        backButton.addActionListener(this);
        backButton.setFocusable(false);

        submitButton.setBounds(380, 400, 100, 20);
        frame.add(submitButton);
        submitButton.setFocusable(false);
        submitButton.addActionListener(this);







        frame.setTitle("Update Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==backButton){
            frame.dispose();
            LaunchPage lunchPage = new LaunchPage();
        }

    }
}
