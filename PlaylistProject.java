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
   public static String currentInput;
   public static ArrayList<String> songResults;
   public static ArrayList<String> executeCommand(String command, String parameters){
       String s = "hi";
       ArrayList<String> dataList = new ArrayList<String>();
       command = "./a.out " + command + " " + parameters;
       try {
           Runtime rt = Runtime.getRuntime();
           Process pr = rt.exec(command);
           BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
           while ((s = br.readLine()) != null){
               dataList.add(s);
             }
           pr.waitFor();
           pr.destroy();
       }
       catch (Exception e) {
           System.out.println(e.toString());
           e.printStackTrace();
        }
       return dataList;
   }

   public static void main(String[] args) {
        ArrayList<String> start = executeCommand("s", "");
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

        JSplitPane splitPopPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPopPane.setDividerLocation(125);
        splitPopPane.setDividerSize(0);
        splitPopPane.setEnabled(false);

        JPanel HolderPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cH = new GridBagConstraints();

        JLabel titlePop = new JLabel("Top Eight Playlists");
        titlePop.setFont(new Font("Monospaced", Font.BOLD, 30));
        titlePop.setForeground(Color.WHITE);
        cPop.gridx = 0;
        cPop.gridy = 0;
        cPop.weightx = 2;
        cPop.insets = new Insets(0,0,30,0);
        PopularPanel.add(titlePop, cPop);
        //
        String[] options = { "Playlist 1", "Playlist 2", "Playlist 3", "Playlist 4", "Playlist 5", "Playlist 6", "Playlist 7", "Playlist 8"};
        JList<String> choiceList = new JList<String>(options);
        choiceList.setSelectedIndex(0);
        choiceList.setFont(new Font("Monospaced", Font.PLAIN, 16));
        choiceList.setBackground(bColor);
        cH.gridx = 0;
        cH.gridy = 0;
        cH.weightx = 1;
        cH.insets = new Insets(0,0,0,0);
        HolderPanel.add(choiceList,cH);
        choiceList.setVisibleRowCount(1);
        //enter command here to get the top eight playlists
        //replace word "playlist"
        ArrayList<String> topSongs = executeCommand("l", "");
        final JLabel playlist1 = new JLabel();

        if (topSongs.size() >= 1){
          playlist1.setText("");
          playlist1.setFont(new Font("Monospaced", Font.BOLD, 16));
          playlist1.setForeground(Color.WHITE);
          cH.insets = new Insets(0,0,0,0);
          cH.gridx = 1;
          cH.gridy = 0;
          HolderPanel.add(playlist1, cH);
        }
        if (topSongs.size() == 0){
          playlist1.setText("None Currently");
        }

        String str1 = topSongs.get(0);
        String[] playListJ = str1.split("\\s*,\\s*");
       String holder1 = "<html>";
         for (int j = 0; j < playListJ.length; j = j + 1){
          holder1 = holder1 + playListJ[j]+"<br>";
         }
         holder1 = holder1 + "</html>";
         playlist1.setText(holder1);

        splitPopPane.setLeftComponent(PopularPanel);
        splitPopPane.setRightComponent(HolderPanel);

        //search song tab:

        JSplitPane splitSearchPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitSearchPane.setDividerLocation(300);
        splitSearchPane.setDividerSize(0);
        splitSearchPane.setEnabled(false);

        final JPanel ResultsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cR = new GridBagConstraints();

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
        //initial should be blank

        final JList<String> list = new JList<String>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font("Monospaced", Font.PLAIN, 16));
        list.setSelectedIndex(0);
        list.setVisibleRowCount(0);
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

        /*search Results
        final JLabel searchLabel = new JLabel("Search Results");
        searchLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        searchLabel.setOpaque(true);
        searchLabel.setBackground(bColor);
        cR.insets = new Insets(0,0,0,0);
        cR.gridwidth = 1;
        cR.weightx = 0.5;
        cR.gridx = 0;
        cR.gridy = 0;
        ResultsPanel.add(searchLabel, cR);*/

        final DefaultListModel<String> finalPL = new DefaultListModel<String>();
        JList<String> finalList = new JList<String>(finalPL);
        JScrollPane scroller = new JScrollPane(finalList);
        scroller.setViewportView(finalList);
        finalList.setFont(new Font("Monospaced", Font.PLAIN, 16));
        finalList.setBackground(bColor);
        cR.gridx = 1;
        cR.gridy = 0;
        cR.weightx = 2;
        cR.insets = new Insets(0,0,0,0);
        ResultsPanel.add(scroller,cR);

        JList<String> resultList = new JList<String>(options);
        resultList.setFont(new Font("Monospaced", Font.PLAIN, 16));
        resultList.setBackground(bColor);
        resultList.setSelectedIndex(0);
        cR.gridx = 0;
        cR.gridy = 0;
        cR.weightx = 1;
        cR.insets = new Insets(0,0,0,0);
        ResultsPanel.add(resultList,cR);

        splitSearchPane.setLeftComponent(SearchPanel);
        splitSearchPane.setRightComponent(ResultsPanel);


        PopularPanel.setBackground(aColor);
        PlayListPanel.setBackground(aColor);
        HolderPanel.setBackground(aColor);
        TitlePanel.setBackground(aColor);
        SearchPanel.setBackground(aColor);
        ResultsPanel.setBackground(aColor);
        PlayListPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        splitPopPane.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        splitSearchPane.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

        JTabbedPane categories = new JTabbedPane();
        categories.setFont(new Font("Monospaced", Font.PLAIN, 20) );
        categories.addTab("Enter Playlist", PlayListPanel);
        categories.addTab("Top Playlists", splitPopPane);
        categories.addTab("Search Song", splitSearchPane);

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
        frame.setSize(900, 900);
        frame.setVisible(true);


        //error codes for inputting files 0 - did not open 1 - good 2 - more than 128 lines
        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String textfield1 = t1.getText();
                if (y.isSelected()){
                    if (textfield1.length() == 0)
                        playlistLabel.setText("Input A Valid File!");
                    else{
                        ArrayList<String> error = executeCommand("a", textfield1);
                        //if (error.get(0) == "1"){
                          t1.setText(""); //check if this is okay
                          playlistLabel.setText("Playlist File Added!");
                        //}
                        //else if (error.get(0) == "2"){
                          //playlistLabel.setText("Exceed ");
                        //}
                        //else if (error.get(0) == "0"){
                         // playlistLabel.setText("Could Not Open File");
                        //}
                    }
                }
                else if (n.isSelected()){
                    if (textfield1.length() == 0)
                        playlistLabel.setText("Input A Valid Playlist!");
                    else{
                        //no error for manual
                        String toBackEnd = textfield1;
                        ArrayList<String> error = executeCommand("m", toBackEnd);
                        t1.setText("");
                        playlistLabel.setText("Playlist Added!");
                    }
                }
                else{
                    playlistLabel.setText("Choose A Method!");
                }
                //updating the top 8 playlists
                ArrayList<String> topSongs = executeCommand("l", "");
                if (topSongs.size() == 0){
                  playlist1.setText("No Playlists Found");
                }

                int i = 0;

                if (topSongs.size() >= 1){
                String str = topSongs.get(i);
                String[] playListI = str.split("\\s*,\\s*");
                 String holder = "<html>";
                 for (int j = 0; j < playListI.length; j = j + 1){
                    holder = holder + playListI[j]+"<br>";
                 }
                 holder = holder+ "</html>";
                 playlist1.setText(holder);
               }

                frame.validate();
            }
        });

        submitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //search Results
                finalPL.removeAllElements();
                String textfield2 = t2.getText();
                if (textfield2.length() != 0){
                    String searchRes = "";
                    String toBackEnd = textfield2;
                    songResults = executeCommand("p", toBackEnd);
                    list.setVisibleRowCount(0);
                    listModel.removeAllElements();
                    if (songResults.size() == 0){
                      searchRes = searchRes + "No Playlists Found";
                      finalPL.addElement(searchRes);
                    }
                    else{
                       String str = songResults.get(0);
                       String[] playListI = str.split("\\s*,\\s*");
                       for (int j = 0; j < playListI.length; j = j + 1){
                          finalPL.addElement(playListI[j]);
                      }
                    }
                }
                else{
                    finalPL.addElement("Input A Valid Song!");
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

        Timer timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //change and make boxes visible here
                String textfield2 = t2.getText();
                if(textfield2.length() != 0 && !(textfield2.equals(currentInput))){
                    listModel.removeAllElements();
                    //add elements from command line call here
                    String toBackEnd = textfield2;
                    ArrayList<String> newElements = executeCommand("t", toBackEnd);
                    //set visibility to the necessary amount here
                    list.setVisibleRowCount(newElements.size());
                    for (int i = 0; i < newElements.size(); i = i + 1){
                      listModel.addElement(newElements.get(i));
                    }
                    frame.revalidate();
                    currentInput = textfield2;
                }

            }
        });
        timer.start();


        //to select the proper autocomplete option
        MouseListener mouseListener1 = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String selectedItem = (String) list.getSelectedValue();
                t2.setText(selectedItem);
            }
        };
        list.addMouseListener(mouseListener1);

        MouseListener mouseListener2 = new MouseAdapter() {
           public void mouseClicked(MouseEvent e) {
               String selectedItem = (String) choiceList.getSelectedValue();
               int i = 0;
               switch (selectedItem) {
                  case "Playlist 1": i = 0;
                     break;
                  case "Playlist 2": i = 1;
                     break;
                  case "Playlist 3": i = 2;
                     break;
                  case "Playlist 4": i = 3;
                     break;
                  case "Playlist 5": i = 4;
                     break;
                  case "Playlist 6": i = 5;
                     break;
                  case "Playlist 7": i = 6;
                     break;
                  case "Playlist 8": i = 7;
                     break;
               }
               ArrayList<String> topSongs = executeCommand("l", "");
               if (topSongs.size() == 0){
                 playlist1.setText("None Playlists Found");
               }

               if (topSongs.size() >= 1){
                String str = topSongs.get(i);
                String[] playListI = str.split("\\s*,\\s*");
                String holder = "<html>";
                 for (int j = 0; j < playListI.length; j = j + 1){
                    holder = holder+ playListI[j]+"<br>";
                 }
                 holder = holder+ "</html>";
                 playlist1.setText(holder);
               }

           }
       };
       choiceList.addMouseListener(mouseListener2);

       //to select the proper autocomplete option
       MouseListener mouseListener3 = new MouseAdapter() {
           public void mouseClicked(MouseEvent e) {
               String selectedItem = (String) resultList.getSelectedValue();
               int i = 0;
               switch (selectedItem) {
                  case "Playlist 1": i = 0;
                     break;
                  case "Playlist 2": i = 1;
                     break;
                  case "Playlist 3": i = 2;
                     break;
                  case "Playlist 4": i = 3;
                     break;
                  case "Playlist 5": i = 4;
                     break;
                  case "Playlist 6": i = 5;
                     break;
                  case "Playlist 7": i = 6;
                     break;
                  case "Playlist 8": i = 7;
                     break;
               }
               finalPL.removeAllElements();
               if (songResults.size() == 0){
                 finalPL.addElement("No Playlists Found");
               }

               if (songResults.size() >= 1){
                  if ( i >= songResults.size()){
                     String num = String.valueOf(songResults.size());
                      finalPL.addElement("There are Only");
                      finalPL.addElement(num+" Playlists");
                      finalPL.addElement("Matching Your Song");
                     //searchLabel.setText(holder);
                  }
                  else{
                     String str = songResults.get(i);
                     String[] playListI = str.split("\\s*,\\s*");
                     //String holder = "<html>";
                     for (int j = 0; j < playListI.length; j = j + 1){
                       //holder = holder + playListI[j]+"<br>";
                        finalPL.addElement(playListI[j]);
                    }
                    //searchLabel.setText(holder);
                 }
               }
           }
       };
       resultList.addMouseListener(mouseListener3);

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
