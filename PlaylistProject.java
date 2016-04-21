import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FilenameFilter;


public class PlaylistProject{
    //going to the backend
   public ArrayList executeCommand(String command, ArrayList parameters){
       String s;
       ArrayList dataList = new ArrayList();
       command = command + "./a.out";
       for(int i = 0; i < parameters.size(); i = i+1){
         command = command + parameters.get(i);
       }
       try {
           Runtime rt = Runtime.getRuntime();
           Process pr = rt.exec(command);
           BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
           while ((s= br.readLine()) != null)
                dataList.add(s);
           pr.waitFor();
           pr.destroy();
       }
       catch (Exception e) {
           System.out.println(e.toString());
           e.printStackTrace();
        }
       return dataList;
   }
   @SuppressWarnings("unchecked")
   public static void main(String[] args) {

        Color aColor = new Color(0x0DCCD6);
        Color bColor = new Color(0xCAE9EB);

        //creating a panel to combine items
        JPanel TitlePanel = new JPanel(new GridBagLayout());
        GridBagConstraints cTitle = new GridBagConstraints();

        final JPanel PlayListPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        final JPanel SearchPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cSearch = new GridBagConstraints();

        final JPanel PopularPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cPop = new GridBagConstraints();


        //label
        JLabel jlbempty = new JLabel("\u266B Awesome Playlist App \u266B");
        jlbempty.setForeground(Color.WHITE);
        jlbempty.setFont(new Font("Monospaced", Font.BOLD, 38));
        TitlePanel.add(jlbempty, cTitle);


        //enter playlist tab
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
        final JRadioButton y = new JRadioButton("Text File");
        GridBagConstraints y_c = new GridBagConstraints();
        y.setBackground(aColor);
        y.setForeground(Color.WHITE);
        y.setFont(new Font("Monospaced", Font.BOLD, 22));
        buttonGroup.add(y);
        y_c.gridx = 1;
        y_c.gridy = 1;
        y_c.insets = new Insets(0, 0, 30, 0);
        PlayListPanel.add(y, y_c);
        final JRadioButton n = new JRadioButton("Manual Input");
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
        final JTextField t1 = new JTextField(20);
        t1.setFont(new Font("Monospaced", Font.PLAIN, 16));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0,40,5,40);
        PlayListPanel.add(t1, c);

        //browse button
        final JButton browseButton = new JButton("Browse");
        browseButton.setFont(new Font("Lucida", Font.PLAIN, 14));


        //submit button
        JButton startButton = new JButton("Submit");
        startButton.setFont(new Font("Lucida", Font.PLAIN, 18));
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0,0,30,0);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 3;
        PlayListPanel.add(startButton, c);

        final JLabel playlistLabel = new JLabel(" ");
        playlistLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        c.insets = new Insets(0,0,0,0);
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 4;
        PlayListPanel.add(playlistLabel, c);


        //popular playlist tab
        JLabel titlePop = new JLabel("Top Eight Playlists");
        titlePop.setFont(new Font("Monospaced", Font.BOLD, 30));
        titlePop.setForeground(Color.WHITE);
        cPop.gridx = 0;
        cPop.gridy = 0;
        cPop.insets = new Insets(0,0,30,0);
        PopularPanel.add(titlePop, cPop);

        //enter command here to get the top eight playlists
        //replace word "playlist"
<<<<<<< HEAD
        ArrayList<String> topSongs = executeCommand("l", "");
        JLabel playlist1 = new JLabel("hi");
        JLabel playlist2 = new JLabel();
        JLabel playlist3 = new JLabel();
        JLabel playlist4 = new JLabel();
        JLabel playlist5 = new JLabel();
        JLabel playlist6 = new JLabel();
        JLabel playlist7 = new JLabel();
        JLabel playlist8 = new JLabel();
        if (topSongs.size() >= 1){
          playlist1.setText(topSongs.get(0));
          playlist1.setFont(new Font("Monospaced", Font.BOLD, 22));
          playlist1.setForeground(Color.WHITE);
          cPop.insets = new Insets(0,0,0,0);
          cPop.gridx = 0;
          cPop.gridy = 1;
          PopularPanel.add(playlist1, cPop);
        }
        if (topSongs.size() == 0){
          playlist1.setText("None Currently");
        }
        if (topSongs.size() >= 2){
          playlist2.setText(topSongs.get(1));
          playlist2.setFont(new Font("Monospaced", Font.BOLD, 22));
          playlist2.setForeground(Color.WHITE);
          cPop.gridx = 0;
          cPop.gridy = 2;
          PopularPanel.add(playlist2, cPop);
        }
=======

>>>>>>> origin/master

        JLabel playlist1 = new JLabel("playlist");
        playlist1.setFont(new Font("Monospaced", Font.BOLD, 22));
        playlist1.setForeground(Color.WHITE);
        cPop.insets = new Insets(0,0,0,0);
        cPop.gridx = 0;
        cPop.gridy = 1;
        PopularPanel.add(playlist1, cPop);

        JLabel playlist2 = new JLabel("playlist");
        playlist2.setFont(new Font("Monospaced", Font.BOLD, 22));
        playlist2.setForeground(Color.WHITE);
        cPop.gridx = 0;
        cPop.gridy = 2;
        PopularPanel.add(playlist2, cPop);

        JLabel playlist3 = new JLabel("playlist");
        playlist3.setFont(new Font("Monospaced", Font.BOLD, 22));
        playlist3.setForeground(Color.WHITE);
        cPop.gridx = 0;
        cPop.gridy = 3;
        PopularPanel.add(playlist3, cPop);

        JLabel playlist4 = new JLabel("playlist");
        playlist4.setFont(new Font("Monospaced", Font.BOLD, 22));
        playlist4.setForeground(Color.WHITE);
        cPop.gridx = 0;
        cPop.gridy = 4;
        PopularPanel.add(playlist4, cPop);

        JLabel playlist5 = new JLabel("playlist");
        playlist5.setFont(new Font("Monospaced", Font.BOLD, 22));
        playlist5.setForeground(Color.WHITE);
        cPop.gridx = 0;
        cPop.gridy = 5;
        PopularPanel.add(playlist5, cPop);

        JLabel playlist6 = new JLabel("playlist");
        playlist6.setFont(new Font("Monospaced", Font.BOLD, 22));
        playlist6.setForeground(Color.WHITE);
        cPop.gridx = 0;
        cPop.gridy = 6;
        PopularPanel.add(playlist6, cPop);

        JLabel playlist7 = new JLabel("playlist");
        playlist7.setFont(new Font("Monospaced", Font.BOLD, 22));
        playlist7.setForeground(Color.WHITE);
        cPop.gridx = 0;
        cPop.gridy = 7;
        PopularPanel.add(playlist7, cPop);

        JLabel playlist8 = new JLabel("playlist");
        playlist8.setFont(new Font("Monospaced", Font.BOLD, 22));
        playlist8.setForeground(Color.WHITE);
        cPop.gridx = 0;
        cPop.gridy = 8;
        PopularPanel.add(playlist8, cPop);

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
        final JTextField t2 = new JTextField(20);
        t2.setFont(new Font("Monospaced", Font.PLAIN, 16));
        cSearch.fill = GridBagConstraints.HORIZONTAL;
        cSearch.gridwidth = 3;
        cSearch.weightx = 0.5;
        cSearch.insets = new Insets(0,40,0,40);
        cSearch.gridx = 0;
        cSearch.gridy = 1;
        SearchPanel.add(t2, cSearch);


        final DefaultListModel<String> listModel = new DefaultListModel<String>();
        //initialize should be blank
        listModel.addElement("Love Hurts");
        listModel.addElement("Love Letter");
        listModel.addElement("Love Song");
        listModel.addElement("Love Me Do");
        final JList<String> list = new JList<String>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font("Monospaced", Font.PLAIN, 16));
        list.setSelectedIndex(0);
        list.setVisibleRowCount(4);
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setViewportView(list);
        cSearch.gridwidth = 3;
        cSearch.gridheight = 4;
        cSearch.weightx = 0.5;
        cSearch.insets = new Insets(0,40,30,40);
        cSearch.gridx = 0;
        cSearch.gridy = 2;
        SearchPanel.add(listScrollPane, cSearch);


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
        final JLabel searchLabel = new JLabel(" ");
        searchLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        cSearch.insets = new Insets(50,0,0,0);
        cSearch.gridwidth = 1;
        cSearch.weightx = 0.5;
        cSearch.gridx = 1;
        cSearch.gridy = 8;
        SearchPanel.add(searchLabel, cSearch);


        PlayListPanel.setBackground(aColor);
        PopularPanel.setBackground(aColor);
        TitlePanel.setBackground(aColor);
        SearchPanel.setBackground(aColor);
        PlayListPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        SearchPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        PopularPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

        JTabbedPane categories = new JTabbedPane();
        categories.setFont(new Font("Monospaced", Font.PLAIN, 20) );
        categories.addTab("Enter Playlist", PlayListPanel);
        categories.addTab("Top Playlists", PopularPanel);
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
        final JFrame frame = new JFrame("PlayList Project");
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.add(splitPane);
        frame.setSize(800, 800);
        frame.setVisible(true);


        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String textfield1 = t1.getText();
                if (y.isSelected()){
                    if (textfield1.length() == 0)
                        playlistLabel.setText("Input A Valid File!");
                    else{
                        t1.setText(""); //check if this is okay
                        //call on input playlist functions here!
                        playlistLabel.setText("Playlist File Added!");
                        //update top playlist
                    }
                }
                else if (n.isSelected()){
                    if (textfield1.length() == 0)
                        playlistLabel.setText("Input A Valid File!");
                    else{
                        t1.setText("");//check if this is okay
                        //call on input playlist functions here
                        playlistLabel.setText("Playlist Added!");
                        //update top playlists
                    }
                }
                else{
                    playlistLabel.setText("Choose A Method!");
                }
<<<<<<< HEAD
                //updating the top 8 playlists
                ArrayList<String> topSongs = executeCommand("l", "");
                if (topSongs.size() == 0){
                  playlist1.setText("None Currently");
                }

                if (topSongs.size() >= 1){
                  playlist1.setText(topSongs.get(0));
                }
                if (topSongs.size() >= 2){
                  playlist2.setText(topSongs.get(1));
                }
                if (topSongs.size() >= 3){
                  playlist3.setText(topSongs.get(2));
                }
                if (topSongs.size() >= 4){
                  playlist4.setText(topSongs.get(3));
                }
                if (topSongs.size() >= 5){
                  playlist5.setText(topSongs.get(4));
                }
                if (topSongs.size() >= 6){
                  playlist6.setText(topSongs.get(5));
                }
                if (topSongs.size() >= 7){
                  playlist7.setText(topSongs.get(6));
                }
                if (topSongs.size() >= 8){
                  playlist8.setText(topSongs.get(7));
                }
=======
                //frame.pack();
>>>>>>> origin/master
                frame.validate();
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
                    ArrayList<String> songResults = executeCommand("p", textfield2);
                    list.setVisibleRowCount(0);
                    listModel.removeAllElements();
<<<<<<< HEAD

=======
>>>>>>> origin/master
                }
                else{
                    searchLabel.setText("Input A Valid Song!");
                    list.setVisibleRowCount(0);
                    listModel.removeAllElements();
                }
                t2.setText("");
                frame.revalidate();
            }

        });

        browseButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                fc.setFileFilter(filter);
                int returnVal = fc.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    // user selects a file
                    File selectedFile = fc.getSelectedFile();
                    t1.setText(selectedFile.getAbsolutePath());
                }
            }

        });


        Timer timer = new Timer(75, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //change and make boxes visible here
                String textfield2 = t2.getText();
                if(textfield2.length() != 0){
                    listModel.removeAllElements();
                    //add elements from command line call here
                    //set visibility to the necessary amount here
                    list.setVisibleRowCount(3);
                    listModel.addElement("hello");
                    listModel.addElement("hi");
                    listModel.addElement(t2.getText());
                    frame.revalidate();
                }

            }
        });
        timer.start();


        //to select the proper autocomplete option
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String selectedItem = (String) list.getSelectedValue();
                t2.setText(selectedItem);
            }
        };
        list.addMouseListener(mouseListener);

        //radio button listeners for browsing files and formatting
        y.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GridBagConstraints cBrowse = new GridBagConstraints();
                cBrowse.fill = GridBagConstraints.NONE;
                cBrowse.insets = new Insets(0,0,7,20);
                cBrowse.gridwidth = 1;
                cBrowse.gridx = 4;
                cBrowse.gridy = 2;
                PlayListPanel.add(browseButton, cBrowse);


                //move the label over, since it gets cut off with the browse button
                PlayListPanel.remove(playlistLabel);
                GridBagConstraints ctemp = new GridBagConstraints();
                ctemp.insets = new Insets(0,0,0,150);
                ctemp.gridwidth = 2;
                ctemp.weightx = 0.5;
                ctemp.gridx = 1;
                ctemp.gridy = 4;
                PlayListPanel.add(playlistLabel, ctemp);

                frame.repaint();
                frame.revalidate();
            }
        });
        n.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayListPanel.remove(browseButton);
                PlayListPanel.remove(playlistLabel);
                GridBagConstraints ctemp = new GridBagConstraints();
                ctemp.insets = new Insets(0,0,0,0);
                ctemp.gridwidth = 1;
                ctemp.weightx = 0.5;
                ctemp.gridx = 1;
                ctemp.gridy = 4;
                PlayListPanel.add(playlistLabel, ctemp);
                frame.repaint();
            }
        });
    }
}
