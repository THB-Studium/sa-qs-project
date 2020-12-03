package de.thb.view;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class TableEventUI extends JPanel {

    public TableEventUI(){

        String[] columnNamesItem = {
                "#",
                "Name",
                "Available tickets",
                "Booked tickets",
                "Actual availability"};

//        Define rows and columns for second view (table view)
        Vector<String> columnNames = new Vector<>();
        for (String name : columnNamesItem) {
            columnNames.addElement(name);
        }

        List<String> stringList = new ArrayList<>();
        stringList.addAll(Arrays.asList(columnNamesItem));

        Vector<String> rowOne = new Vector<>();
        rowOne.addElement("1");
        rowOne.addElement("Row1-Column2");
        rowOne.addElement("Row1-Column3");
        rowOne.addElement("Row1-Column4");
        rowOne.addElement("Row1-Column4");

        Vector<String> rowTwo = new Vector<>();
        rowTwo.addElement("2");
        rowTwo.addElement("Row2-Column2");
        rowTwo.addElement("Row2-Column3");

        Vector<String> row = new Vector<>();
        Vector<Vector> rowData = new Vector<>();
        stringList.forEach(
                s -> {
                    row.addElement(s);
                    rowData.addElement(row);
                }
        );
        rowData.addElement(rowOne);
        rowData.addElement(rowTwo);

        JTable table = new JTable(rowData, columnNames){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component returnComp = super.prepareRenderer(renderer, row, column);
                Color alternateColor = Color.decode("#FFFAF0");
                Color whiteColor = Color.WHITE;
                if (!returnComp.getBackground().equals(getSelectionBackground())) {
                    Color backgroundColor = (row % 2 == 0 ? alternateColor : whiteColor);
                    returnComp.setBackground(backgroundColor);
                }
                return returnComp;
            }
        };
        table.setPreferredScrollableViewportSize(new Dimension(700, 250));

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPaneForTable = new JScrollPane(table);
        add(scrollPaneForTable);

    }
}
