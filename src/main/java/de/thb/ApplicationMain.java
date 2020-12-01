package de.thb;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Vector;

public class ApplicationMain extends JPanel {
    public ApplicationMain() {
        super(new GridLayout(1, 1));

        String[] columnNamesItem = {
                "#",
                "Name",
                "Available tickets",
                "Booked tickets",
                "Actual availability"};


//        Object[][] data = {
//                {"Kathy", "Smith",
//                        "Snowboarding", new Integer(5), new Boolean(false)},
//                {"John", "Doe",
//                        "Rowing", new Integer(3), new Boolean(true)},
//                {"Sue", "Black",
//                        "Knitting", new Integer(2), new Boolean(false)},
//                {"Jane", "White",
//                        "Speed reading", new Integer(20), new Boolean(true)},
//                {"Joe", "Brown",
//                        "Pool", new Integer(10), new Boolean(false)}
//        };


        Vector<String> columnNames = new Vector<String>();
        for (String name : columnNamesItem) {
            columnNames.addElement(name);
        }

        Vector<String> rowOne = new Vector<String>();
        rowOne.addElement("1");
        rowOne.addElement("Row1-Column2");
        rowOne.addElement("Row1-Column3");
        rowOne.addElement("Row1-Column4");

        Vector<String> rowTwo = new Vector<String>();
        rowTwo.addElement("2");
        rowTwo.addElement("Row2-Column2");
        rowTwo.addElement("Row2-Column3");

        Vector<Vector> rowData = new Vector<Vector>();
        rowData.addElement(rowOne);
        rowData.addElement(rowTwo);


        JTabbedPane tabbedPaneViewChooser = new JTabbedPane();
        JPanel eventPanel = new JPanel();
        JPanel eventTablePanel = new JPanel();
        JPanel eventDetailPanel = new JPanel();
        eventPanel.setLayout(new GridLayout(0, 2, 5, 10));
        eventPanel.setBounds(20,20,200,300);
        JScrollPane scrollPaneForEvents = new JScrollPane();

        JTable table = new JTable(rowData, columnNames){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component returnComp = super.prepareRenderer(renderer, row, column);
                Color alternateColor = Color.LIGHT_GRAY;
                Color whiteColor = Color.WHITE;
                if (!returnComp.getBackground().equals(getSelectionBackground())) {
                    Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
                    returnComp.setBackground(bg);
                    bg = null;
                }
                return returnComp;
            }

        };
        table.setPreferredScrollableViewportSize(new Dimension(700, 70));

//        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPaneForTable = new JScrollPane(table);
//Add the scroll pane to this panel.
        eventTablePanel.add(scrollPaneForTable);


        JTextField eventNameField = new JTextField(10);
        JLabel eventNameLabel = new JLabel("  Event name");

        JTextField ticketQuantityField = new JTextField(10);
        JLabel ticketQuantityLabel = new JLabel("  Quantity");

        JTextField ticketQuantityToBuy = new JTextField(10);
        JLabel ticketQuantityToBuyLabel = new JLabel("Tickets to buy");

        JTextField actualAvailabilityField = new JTextField(10);
        JLabel actualAvailabilityFieldLabel = new JLabel("“Actual Availability”");

        JButton buyBotton = new JButton("Buy");


//        eventDetailPanel.setBorder(new EmptyBorder());
        eventDetailPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.VERTICAL;


        eventDetailPanel.add(eventNameLabel);
        eventDetailPanel.add(eventNameField, gbc);

        eventDetailPanel.add(ticketQuantityLabel);
        eventDetailPanel.add(ticketQuantityField, gbc);

        eventDetailPanel.add(ticketQuantityToBuyLabel);
        eventDetailPanel.add(ticketQuantityToBuy, gbc);

        eventDetailPanel.add(actualAvailabilityFieldLabel);
        eventDetailPanel.add(actualAvailabilityField, gbc);
        eventDetailPanel.add(buyBotton, gbc);



        // Array für unsere JList
        String interessen[] = {"Politik", "Autos", "Mode",
                "Film- und Fernsehen", "Computer", "Tiere", "Sport","Politik", "Autos", "Mode",
                "Film- und Fernsehen", "Computer", "Tiere", "Sport"};

        //DefaultListModell wird erzeugt
        DefaultListModel listenModell = new DefaultListModel();


        //JList mit Einträgen wird erstellt
        JList themenAuswahl = new JList(listenModell);
        for(int i=0; i<interessen.length; i++){
            listenModell.addElement(interessen[i]);
        }

        //JList wird Panel hinzugefügt
        eventPanel.add(themenAuswahl);

        scrollPaneForEvents.setViewportView(themenAuswahl);
        themenAuswahl.setLayoutOrientation(JList.VERTICAL);
        eventPanel.add(scrollPaneForEvents);
        eventPanel.add(eventDetailPanel);
        //Eintrag "Programmieren" wird an Index 4 eingefügt
        listenModell.add(4, "Programmieren");

        //erster Eintrag "Politik" wird entfernt
        listenModell.removeElementAt(0);

        //String "Mode" wird entfernt
        listenModell.removeElement("Mode");




        tabbedPaneViewChooser.addTab("Tab 1", null, eventPanel,
                "Does nothing");

        tabbedPaneViewChooser.setMnemonicAt(0, KeyEvent.VK_1);



        tabbedPaneViewChooser.addTab("Tab 2", null, eventTablePanel,
                "Does twice as much nothing");
        tabbedPaneViewChooser.setMnemonicAt(1, KeyEvent.VK_2);



        //Add the tabbed pane to this panel.
        add(tabbedPaneViewChooser);

        //The following line enables to use scrolling tabs.
        tabbedPaneViewChooser.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ApplicationMain");
        frame.setBounds(100, 100,1000,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        ApplicationMain applicationMain = new ApplicationMain();
//        applicationMain.setPreferredSize((new Dimension(800,600)));
        //Add content to the window.
        frame.add(new ApplicationMain(), BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
//    public static void main(String[] args) {
//        JFrame f=new JFrame("Title");//creating instance of JFrame
//
//        JButton b=new JButton("click");//creating instance of JButton
//        b.setBounds(130,100,100, 40);//x axis, y axis, width, height
//
//        f.add(b);//adding button in JFrame
//
//        f.setSize(800,600);//400 width and 500 height
//        f.setLayout(null);//using no layout managers
//        f.setVisible(true);//making the frame visible
//    }

}
