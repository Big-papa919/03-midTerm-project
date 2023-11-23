import javax.swing.*;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage implements ActionListener,ThankYouMessage, SuccessMessage {

    JFrame frame = new JFrame();
    JLabel label;





    JButton addButton = new JButton("Add");
    JButton viewButton = new JButton("View ");

    JButton updateRemoveButton = new JButton("Update & Remove");







    LaunchPage(){




        addButton.setBounds(200, 200, 200, 40);
        viewButton.setBounds(200, 260, 200, 40);

        updateRemoveButton.setBounds(200, 320, 200, 40);


        addButton.setFocusable(false);
        viewButton.setFocusable(false);

        updateRemoveButton.setFocusable(false);


        addButton.addActionListener(this);
        viewButton.addActionListener(this);

        updateRemoveButton.addActionListener(this);

        frame.add(addButton);
        frame.add(viewButton);

        frame.add(updateRemoveButton);

        frame.setTitle("Main Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setResizable(false);

        JLabel titleLabel = new JLabel("Employee Management System");
        titleLabel.setBounds(150, 20, 300, 40);
        titleLabel.setFont(new Font("Segue UI",Font.BOLD,20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.add(titleLabel);



        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            frame.dispose();
            AddPage addPage = new AddPage();
        }
        if (e.getSource()==viewButton){
            frame.dispose();
            ViewPage viewPage = new ViewPage();
        }

        if (e.getSource()==updateRemoveButton){
            frame.dispose();
            UpdateRemovePage updatePage = new UpdateRemovePage();
        }

    }

    @Override
    public void thanks() {
        System.out.println("thank you for using my program");
    }

    @Override
    public void Success() {
        System.out.println("The program runs successfully");
    }
}