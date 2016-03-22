/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package playlistproject;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Alex
 */
public class PlaylistProject{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("Playlist JFrame");
	// Add a window listner for close button
	frame.addWindowListener(new WindowAdapter() {
	public void windowClosing(WindowEvent e) {
            System.exit(0);
	}
	});
		// This is an empty content area in the frame
	JLabel jlbempty = new JLabel("");
        jlbempty.setText("Hello World");
	jlbempty.setPreferredSize(new Dimension(175, 100));
	frame.getContentPane().add(jlbempty, BorderLayout.PAGE_START);
	frame.pack();
	frame.setVisible(true);
        
        
        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(200, 100));
        frame.getContentPane().add(startButton, BorderLayout.LINE_START);
        
        startButton.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the button");
            }
        });
        
        JButton startButton2 = new JButton("Another Start");
        frame.getContentPane().add(startButton2, BorderLayout.CENTER);
        startButton2.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the other button");
            }
        });
    }
    
    
}
