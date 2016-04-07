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
        JLabel jlbempty = new JLabel("Awesome Playlist App");
        jlbempty.setForeground(Color.WHITE);
        jlbempty.setFont(new Font("Monaco", Font.BOLD, 40));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,25,40,0);
        c.gridwidth = 3;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(jlbempty, c);
        
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0,0,0,0);
        
        JLabel label = new JLabel("Enter Playlist: ");
        label.setFont(new Font("Monaco", Font.PLAIN, 24));
        label.setForeground(Color.WHITE);
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(label, c);
        
        //empty text field
        JTextField t1 = new JTextField(10);
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        panel.add(t1, c);
        
        
        
        //Radio Buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton y = new JRadioButton("Text File");
        y.setBackground(aColor);
        y.setForeground(Color.WHITE);
        y.setFont(new Font("Monaco", Font.PLAIN, 18));
        buttonGroup.add(y);
        y.putClientProperty("JComponent.sizeVariant", "large");
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        panel.add(y, c);
        JRadioButton n = new JRadioButton("Manual Input");
        n.setFont(new Font("Monaco", Font.PLAIN, 18));
        n.setBackground(aColor);
        n.setForeground(Color.WHITE);
        buttonGroup.add(n);
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        panel.add(n, c);
        
        //two buttons
        JButton startButton = new JButton("Submit");
        startButton.setPreferredSize(new Dimension(100, 25));
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 2;
        panel.add(startButton, c);
       
        panel.setBackground(aColor);
        //frame to encapsulate panel and pack components in
        JFrame frame = new JFrame("PlayList Project");
        frame.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setVisible(true);
        
        
        
        startButton.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                //String text = t1.getText();
                //t1.setText("");
                //label.setText(text);
                //panel.revalidate();
            }
        });
        
    }
    
    
}
