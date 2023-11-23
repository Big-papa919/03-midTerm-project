import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class AddPage {
    private JTextField nameHolder;
    private JTextField addressHolder;
    private JTextField phoneNbHolder;
    private JTextField salaryHolder;
    private JButton submitButton;
    private JButton backButton;
    private JLabel nameField;
    private JLabel addressField;
    private JLabel phoneNbField;
    private JLabel salaryField;
    private JLabel ageField;
    private JTextField ageHolder;
    private JButton readButton;

    private static final String FILE_NAME = "StoreData.txt";

    public AddPage() {

        nameHolder = new JTextField();
        addressHolder = new JTextField();
        phoneNbHolder = new JTextField();
        salaryHolder = new JTextField();
        ageHolder = new JTextField();

        //key listeners
        nameHolder.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != KeyEvent.VK_SPACE) {
                    e.consume();
                }
            }
        });

        phoneNbHolder.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        ageHolder.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        salaryHolder.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        // buttons
        submitButton = new JButton("Submit");
        backButton = new JButton("Back");
        readButton = new JButton("Read");

        //labels
        nameField = new JLabel("Full Name:");
        addressField = new JLabel("Address:");
        phoneNbField = new JLabel("Phone Number:");
        salaryField = new JLabel("Salary:");
        ageField = new JLabel("Age: ");

        //JFrame
        JFrame frame = new JFrame("AddPage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);


        nameField.setBounds(20, 20, 80, 25);
        nameHolder.setBounds(120, 20, 200, 25);

        ageField.setBounds(20, 60, 80, 25);
        ageHolder.setBounds(120, 60, 200, 25);

        addressField.setBounds(20, 100, 80, 25);
        addressHolder.setBounds(120, 100, 200, 25);

        phoneNbField.setBounds(20, 140, 100, 25);
        phoneNbHolder.setBounds(120, 140, 200, 25);

        salaryField.setBounds(20, 180, 80, 25);
        salaryHolder.setBounds(120, 180, 200, 25);

        backButton.setBounds(120, 220, 80, 25);
        submitButton.setBounds(220, 220, 80, 25);
        readButton.setBounds(0, 220, 80, 25);

        frame.add(nameField);
        frame.add(nameHolder);
        frame.add(ageField);
        frame.add(ageHolder);
        frame.add(addressField);
        frame.add(addressHolder);
        frame.add(phoneNbField);
        frame.add(phoneNbHolder);
        frame.add(salaryField);
        frame.add(salaryHolder);
        frame.add(backButton);
        frame.add(submitButton);
        frame.add(readButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == backButton) {
                    frame.dispose();
                    LaunchPage launchPage = new LaunchPage();
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == submitButton) {
                    try {
                        String name = nameHolder.getText();
                        String age = ageHolder.getText();
                        String address = addressHolder.getText();
                        String phoneNb = phoneNbHolder.getText();
                        String salary = salaryHolder.getText();

                        validateAge(age);
                        validateSalary(salary);


                        writeDataToFile(name, age, address, phoneNb, salary);

                        clearInputFields();
                    } catch (AgeException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid age: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (SalaryException ex) {
                        JOptionPane.showMessageDialog(null,"Invalid Salary: "+ ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
                    }
                }
            }
        });

        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == readButton) {
                    readDataFromFile();
                }
            }
        });

        frame.setVisible(true);
    }

    private void validateAge(String age) throws AgeException {
        try {
            int ageValue = Integer.parseInt(age);
            if (ageValue < 17 || ageValue > 120) {
                throw new AgeException("Age must be between 17 and 120");
            }
        } catch (NumberFormatException e) {
            throw new AgeException("Invalid age format");
        }
    }

    private  void validateSalary(String salary) throws SalaryException{
        try{
            double salaryValue = Double.parseDouble(salary);
            if (salaryValue <= 0 ){
                throw new SalaryException("Salary must be greater than 0");
            }
        }catch (NumberFormatException e){
            throw new SalaryException("Invalid salary format");
        }

    }

    private void writeDataToFile(String name, String age, String address, String phoneNb, String salary) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {

            writer.write("Name: " + name + "\n");
            writer.write("Age: " + age + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Phone Number: " + phoneNb + "\n");
            writer.write("Salary: " + salary + "\n");
            writer.write("-----------------------------\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void readDataFromFile() {
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }

            JOptionPane.showMessageDialog(null, content.toString(), "Stored Data", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private void clearInputFields() {

        nameHolder.setText("");
        ageHolder.setText("");
        addressHolder.setText("");
        phoneNbHolder.setText("");
        salaryHolder.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddPage());
    }
}
