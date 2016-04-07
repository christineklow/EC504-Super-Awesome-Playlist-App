
  
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlaylistProject{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Color aColor = new Color(0x0DCCD6);
        
        //creating a panel to combine items
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        
        //label
        JLabel jlbempty = new JLabel("Enter PlayList?");
        jlbempty.setPreferredSize(new Dimension(100, 100));
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(jlbempty, c);
        
        JLabel label = new JLabel("");
        label.setPreferredSize(new Dimension(100, 100));
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(label, c);
        
        //empty text field
        JTextField t1 = new JTextField(10);
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        panel.add(t1, c);
        
        JTextField t2 = new JTextField(10);
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 2;
        panel.add(t2, c);
        
        
        //Radio Buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton y = new JRadioButton("Text File");
        y.setBackground(aColor);
        buttonGroup.add(y);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(y, c);
        JRadioButton n = new JRadioButton("Manual Input");
        n.setBackground(aColor);
        buttonGroup.add(n);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(n, c);
        
        //two buttons
        JButton startButton = new JButton("Enter");
        startButton.setPreferredSize(new Dimension(100, 50));
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        panel.add(startButton, c);
        
        JButton startButton2 = new JButton(";)");
        startButton2.setPreferredSize(new Dimension(100, 50));
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        panel.add(startButton2, c);
       
        panel.setBackground(aColor);
        //frame to encapsulate panel and pack components in
        JFrame frame = new JFrame("PlayList Project");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setVisible(true);
        
        
        
        startButton.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the button");
                String text = t1.getText();
                t1.setText("");
                label.setText(text);
                panel.revalidate();
            }
        });
        
        startButton2.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the other button");
            }
        });
    }
    
    
}
