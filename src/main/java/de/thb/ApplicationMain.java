package de.thb;

import de.thb.view.EventPropertyUI;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.DefaultListModel;
import javax.swing.table.TableCellRenderer;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
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

//        Define rows and columns for second view (table view)
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

        //Define the tab component to hold two views
        JTabbedPane tabbedPaneViewChooser = new JTabbedPane();

        //Components for the first view
        JPanel eventPanel = new JPanel();
        JPanel eventTablePanel = new JPanel();
        eventPanel.setLayout(new GridLayout(0, 1, 5, 10));
        eventPanel.setBounds(20,20,60,300);
        JScrollPane scrollPaneForEvents = new JScrollPane();

        JTable table = new JTable(rowData, columnNames){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component returnComp = super.prepareRenderer(renderer, row, column);
                Color alternateColor = Color.LIGHT_GRAY;
                Color whiteColor = Color.WHITE;
                if (!returnComp.getBackground().equals(getSelectionBackground())) {
                    Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
                    returnComp.setBackground(bg);
                }
                return returnComp;
            }

        };
        table.setPreferredScrollableViewportSize(new Dimension(700, 70));

//        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPaneForTable = new JScrollPane(table);
        eventTablePanel.add(scrollPaneForTable);

        // Array für unsere JList
        String interessen[] = {"Politik", "Autos", "Mode",
                "Film- und Fernsehen", "Computer", "Tiere", "Sport","Politik", "Autos", "Mode",
                "Film- und Fernsehen", "Computer", "Tiere", "Sport"};

        //DefaultListModell wird erzeugt
        DefaultListModel listenModell = new DefaultListModel();


        //JList mit Einträgen wird erstellt
        JList themenAuswahl = new JList(listenModell);
        for(String name : interessen){
            listenModell.addElement(name);
        }

        //JList wird Panel hinzugefügt
        eventPanel.add(themenAuswahl);

        scrollPaneForEvents.setViewportView(themenAuswahl);
        themenAuswahl.setLayoutOrientation(JList.VERTICAL);
        eventPanel.add(scrollPaneForEvents);
        eventPanel.add(new EventPropertyUI());
        //Eintrag "Programmieren" wird an Index 4 eingefügt
        listenModell.add(4, "Programmieren");

        //erster Eintrag "Politik" wird entfernt
//        listenModell.removeElementAt(0);

        //String "Mode" wird entfernt
//        listenModell.removeElement("Mode");



        tabbedPaneViewChooser.addTab("First view", null, eventPanel,
                "Process to buy tickets");

        tabbedPaneViewChooser.setMnemonicAt(0, KeyEvent.VK_1);



        tabbedPaneViewChooser.addTab("All records", null, eventTablePanel,
                "Process to show table list");
        tabbedPaneViewChooser.setMnemonicAt(1, KeyEvent.VK_2);



        //Add the tabbed pane to this panel.
        add(tabbedPaneViewChooser);

        //The following line enables to use scrolling tabs.
        tabbedPaneViewChooser.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }



    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ApplicationMain");
//        frame.setBounds(100, 100,2500,2000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ApplicationMain applicationMain = new ApplicationMain();
        applicationMain.setPreferredSize((new Dimension(800,400)));
        //Add content to the window.
        frame.add(applicationMain, BorderLayout.CENTER);

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

}
