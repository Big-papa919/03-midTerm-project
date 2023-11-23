import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

public class ViewPage implements ActionListener {


    JButton backButton = new JButton("Back");
    JFrame frame = new JFrame();

    JTable dataTable;

    HashSet<String> uniqueNames = new HashSet<>();

    ViewPage() {
        // i will make a combination between Arraylist and hashset so the user cant add the same name twice
        ArrayList<Object[]> data = new ArrayList<>();
        data.add(new Object[]{"John Smith", 25,"Timisoara",24443224, 2000+"$"});


        // here we check for duplicate names, a small note: this for loop wont work because im not receiving the data
        // from the add page, i might add it in the future when i add database
        for (Object[] rowData: data){
            try {
                String name = rowData[0].toString();
                if (!uniqueNames.contains(name)){
                    uniqueNames.add(name);
                }else{
                    System.out.println("Duplicate name: "+ name);
                }
            }catch (NullPointerException e){
                System.out.println("Error, the name is null");
            }


        }

        // Column names
        String[] columns = {"Full Name", "Age", "Address", "Phone Number", "Salary"};

        // a table to hold data
        DefaultTableModel model = new DefaultTableModel(convertListToArray(data), columns);
        dataTable = new JTable(model);

        // it wont let the user play with the position of the columns
        dataTable.getTableHeader().setReorderingAllowed(false);

        // to resize the width of a column
        dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Set preferred column width for each column header
        for (int i = 0; i < columns.length; i++) {
            TableColumn column = dataTable.getColumnModel().getColumn(i);
            column.setHeaderValue(columns[i]);
            int headerWidth = column.getHeaderValue().toString().length() * 8;
            column.setPreferredWidth(headerWidth);
        }

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setBounds(50, 50, 400, 300);
        frame.add(scrollPane);

        backButton.setBounds(280, 400, 100, 20);
        frame.add(backButton);
        backButton.addActionListener(this);
        backButton.setFocusable(false);



        frame.setTitle("View page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    // im using this because the table  expects a 2D array as its data parameter
    private Object[][] convertListToArray(ArrayList<Object[]> list) {
        Object[][] array = new Object[list.size()][5];
        for (int i = 0; i < list.size(); i++) {
            Object[] rowData = list.get(i);
            array[i] = rowData;
        }
        return array;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            LaunchPage launchPage = new LaunchPage();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewPage());
    }
}
