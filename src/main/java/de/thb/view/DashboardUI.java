package de.thb.view;

import javax.swing.*;
import java.awt.*;

public class DashboardUI extends JPanel {

    public DashboardUI() {

        setLayout(new GridLayout(0, 1, 5, 10));
        setBounds(20, 20, 60, 300);
        JScrollPane scrollPaneForEvents = new JScrollPane();

        // Array für unsere JList
        String generatedEvents[] = {"Politik", "Autos", "Mode",
                "Film- und Fernsehen", "Computer", "Tiere", "Sport", "Politik", "Autos", "Mode",
                "Film- und Fernsehen", "Computer", "Tiere", "Sport"};

        //DefaultListModell wird erzeugt
        DefaultListModel eventListModel = new DefaultListModel();


        //JList mit Einträgen wird erstellt
        JList eventListField = new JList(eventListModel);
        eventListField.setCellRenderer(EventUtilitiesUI.getCellRenderer());

        for (String name : generatedEvents) {
            eventListModel.addElement(name);
        }
        //JList wird Panel hinzugefügt
        add(eventListField);
        scrollPaneForEvents.setViewportView(eventListField);
        eventListField.setLayoutOrientation(JList.VERTICAL);
        add(scrollPaneForEvents);
        add(new EventPropertyUI());

    }

}
