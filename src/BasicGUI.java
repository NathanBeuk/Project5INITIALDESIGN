import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicGUI implements ActionListener {
    private int count = 0;
    private JLabel label;
    private JButton button;
    JPanel panel;
    JFrame frame;
    public BasicGUI(){



        frame = new JFrame();
        panel = new JPanel();


        button = new JButton("Click");


        button.addActionListener(this);
        label = new JLabel("Number of clicks 0 ");



        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout());
        panel.add(button);

        panel.add(label);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Board");
        frame.pack();
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        //
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
        new BasicGUI();
//
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText("Number of clicks: " + count);
    }
}