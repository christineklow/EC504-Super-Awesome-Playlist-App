import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PlaylistProject{

   /*public ArrayList executeCommand(String command){
       String s;
       try {
           Runtime rt = Runtime.getRuntime();
           Process pr = rt.exec("./a.out");
           BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
           while ((s= br.readLine()) != null)
                System.out.println(s);
                pr.waitFor();
                //System.out.println("exit: "+ pr.exitValue());
                pr.destroy();
       }
       catch (Exception e) {
           System.out.println(e.toString());
           e.printStackTrace();
	   }
   }*/
    
    
   public static void main(String[] args) {
        
        Color aColor = new Color(0x0DCCD6);
        
        //creating a panel to combine items
        JPanel TitlePanel = new JPanel(new GridBagLayout());
        GridBagConstraints cTitle = new GridBagConstraints();
        
        JPanel PlayListPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        JPanel SearchPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cSearch = new GridBagConstraints();
        
        
        //label
        JLabel jlbempty = new JLabel("\u266B Awesome Playlist App \u266B");
        jlbempty.setForeground(Color.WHITE);
        jlbempty.setFont(new Font("Monospaced", Font.BOLD, 38));
        TitlePanel.add(jlbempty, cTitle);
        
        
        //enter laylist tab
        JLabel label = new JLabel("Enter Playlist");
        label.setFont(new Font("Monospaced", Font.BOLD, 22));
        label.setForeground(Color.WHITE);
        GridBagConstraints enter_c = new GridBagConstraints();
        enter_c.gridx = 0;
        enter_c.gridy = 1;
        enter_c.insets = new Insets(0, 5, 30, 0);
        PlayListPanel.add(label, enter_c);
        
        
        //Radio Buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton y = new JRadioButton("Text File");
        GridBagConstraints y_c = new GridBagConstraints();
        y.setBackground(aColor);
        y.setForeground(Color.WHITE);
        y.setFont(new Font("Monospaced", Font.BOLD, 22));
        buttonGroup.add(y);
        y_c.gridx = 1;
        y_c.gridy = 1;
        y_c.insets = new Insets(0, 0, 30, 0);
        PlayListPanel.add(y, y_c);
        JRadioButton n = new JRadioButton("Manual Input");
        n.setFont(new Font("Monospaced", Font.BOLD, 22));
        n.setBackground(aColor);
        n.setForeground(Color.WHITE);
        buttonGroup.add(n);
        GridBagConstraints n_c = new GridBagConstraints();
        n_c.gridx = 2;
        n_c.gridy = 1;
        n_c.insets = new Insets(0, 0, 30, 5);
        PlayListPanel.add(n, n_c);
        
        
        //empty text field
        JTextField t1 = new JTextField(20);
        t1.setFont(new Font("Monospaced", Font.PLAIN, 16));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0,40,5,40);
        PlayListPanel.add(t1, c);
        
        //submit button
        JButton startButton = new JButton("Submit");
        startButton.setFont(new Font("Lucida", Font.PLAIN, 18));
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0,0,30,0);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 3;
        PlayListPanel.add(startButton, c);
        
        JLabel playlistLabel = new JLabel(" ");
        playlistLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        c.insets = new Insets(0,0,0,0);
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 4;
        PlayListPanel.add(playlistLabel, c);
        
        
        
        //search song tab:
        JLabel esonglabel = new JLabel(" Enter Song");
        esonglabel.setFont(new Font("Monospaced", Font.BOLD, 22));
        esonglabel.setForeground(Color.WHITE);
        cSearch.gridwidth = 1;
        cSearch.weightx = 0.5;
        cSearch.gridx = 1;
        cSearch.gridy = 0;
        cSearch.insets = new Insets(0, 0, 30, 0);
        SearchPanel.add(esonglabel, cSearch);
        
        //empty text field
        JTextField t2 = new JTextField(20);
        t2.setFont(new Font("Monospaced", Font.PLAIN, 16));
        cSearch.fill = GridBagConstraints.HORIZONTAL;
        cSearch.gridwidth = 3;
        cSearch.weightx = 0.5;
        cSearch.insets = new Insets(0,40,0,40);
        cSearch.gridx = 0;
        cSearch.gridy = 1;
        SearchPanel.add(t2, cSearch);
        
        
        DefaultListModel listModel = new DefaultListModel();
        listModel.addElement("Love Hurts");
        listModel.addElement("Love Letter");
        listModel.addElement("Love Song");
        listModel.addElement("Love Me Do");
        JList list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(4);
        JScrollPane listScrollPane = new JScrollPane(list);
        cSearch.gridwidth = 3;
        cSearch.gridheight = 4;
        cSearch.weightx = 0.5;
        cSearch.insets = new Insets(0,40,30,40);
        cSearch.gridx = 0;
        cSearch.gridy = 2;
        //SearchPanel.add(listScrollPane, cSearch);
        
        
        //submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Lucida", Font.PLAIN, 18));
        cSearch.fill = GridBagConstraints.NONE;
        cSearch.gridwidth = 1;
        cSearch.insets = new Insets(5,0,30,0);
        cSearch.weightx = 0.5;
        cSearch.gridx = 1;
        cSearch.gridy = 7;
        SearchPanel.add(submitButton, cSearch);
        
        //search Results
        JLabel searchLabel = new JLabel(" ");
        searchLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        cSearch.insets = new Insets(50,0,0,0);
        cSearch.gridwidth = 1;
        cSearch.weightx = 0.5;
        cSearch.gridx = 1;
        cSearch.gridy = 8;
        SearchPanel.add(searchLabel, cSearch);
      
        PlayListPanel.setBackground(aColor);
        TitlePanel.setBackground(aColor);
        SearchPanel.setBackground(aColor);
        PlayListPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        SearchPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        
        JTabbedPane categories = new JTabbedPane();
        categories.setFont(new Font("Monospaced", Font.PLAIN, 20) );
        categories.addTab("Enter Playlist", PlayListPanel);
        categories.addTab("Search Song", SearchPanel);
        
        categories.setForeground(aColor);
        categories.setBackground(Color.WHITE);
        
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setLeftComponent(TitlePanel);
        splitPane.setRightComponent(categories);
        splitPane.setDividerLocation(200);
        splitPane.setDividerSize(1);
        splitPane.setEnabled(false);
        
        //frame to encapsulate panel and pack components in
        JFrame frame = new JFrame("PlayList Project");
        frame.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.add(splitPane);
        frame.setSize(700, 700);
        frame.setVisible(true);
        
        
        startButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
            }
        });
        
        submitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //search Results
                String textfield2 = t2.getText();
                if (textfield2.length() != 0){
                    
                    searchLabel.setText("Search Results");
                }
                t2.setText("");
            }
        
        });
        
        t2.getDocument().addDocumentListener(new DocumentListener()
        {
            public void changedUpdate(DocumentEvent e)
            {

            }

            public void insertUpdate(DocumentEvent e) 
            {

            }

            public void removeUpdate(DocumentEvent e)
            {

            }
        });
    }
}
