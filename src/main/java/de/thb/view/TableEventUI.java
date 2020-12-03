package de.thb.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class TableEventUI extends JPanel {

    private static final int GAP_BETWEEN = 10;
    private static final int INITIAL_ROWS_NUMBER = 2;
    private static String[] columnHeader = {
            "#",
            "Name",
            "Available tickets",
            "Booked tickets",
            "Actual availability"};
    private DefaultTableModel tableModel = new DefaultTableModel(null, columnHeader) {

        @Override
        public Class<?> getColumnClass(int col) {
            return getValueAt(0, col).getClass();
        }
    };

    private JTable table = new JTable(tableModel);
    private JScrollPane scrollPane = new JScrollPane(table);
    private JScrollBar vScroll = scrollPane.getVerticalScrollBar();
    private boolean isAutoScroll;

    public TableEventUI(){

        //Table row dynamically added when an event is generated
        this.setLayout(new BorderLayout());
        Dimension d = new Dimension(320, INITIAL_ROWS_NUMBER * table.getRowHeight());
        table.setPreferredScrollableViewportSize(d);
        for (int i = 0; i < INITIAL_ROWS_NUMBER; i++) {
            addRow();
        }
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        vScroll.addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                isAutoScroll = !e.getValueIsAdjusting();
            }
        });
        this.add(scrollPane, BorderLayout.CENTER);
        JPanel panel = new JPanel(new GridLayout(1,2));
        panel.add(new JButton(new AbstractAction("Add Row") {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isAutoScroll) addRow();
            }
        }));
        this.add(panel, BorderLayout.PAGE_END);
        this.add(new Panel().add(EventUtilitiesUI.createButtons("Buy Tickets", GAP_BETWEEN-5)), BorderLayout.PAGE_END);
    }

    private void addRow() {
        tableModel.addRow(new Object[] {
                " EventID",
                "event name",
                "Number of tickets",
                "book some tickets",
                "Actual availability"
        });
    }

}
