package de.thb.view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JSeparator;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class EventPropertyUI extends JPanel implements ActionListener, FocusListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JTextField eventNameField, ticketQuantityField, ticketQuantityToBuyField;
    boolean addressSet = false;
    Font regularFont, italicFont;
    JLabel actualAvailabilityDisplay;
    final static int GAP_BETWEEN = 10;
    final static int TEXTFIELD_COLUMN = 5;

    public EventPropertyUI() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        JPanel leftHalf = new JPanel() {

            private static final long serialVersionUID = 1L;

            public Dimension getMaximumSize() {
                Dimension pref = getPreferredSize();
                return new Dimension(Integer.MAX_VALUE, pref.height);
            }
        };
        leftHalf.setLayout(new BoxLayout(leftHalf, BoxLayout.PAGE_AXIS));
        leftHalf.add(createEntryFields());
        leftHalf.add(createButtons());

        add(leftHalf);
        add(createActualAvailabilityDisplay());
    }

    protected JComponent createButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        JButton button = new JButton("Buy Tickets");
        button.addActionListener(this);
        panel.add(button);

        // Match the SpringLayout's gap, subtracting 5 to make
        // up for the default gap FlowLayout provides.
        panel.setBorder(BorderFactory.createEmptyBorder(GAP_BETWEEN*2, 0, 0, GAP_BETWEEN - 5));
        return panel;
    }

    /**
     * Called when the user clicks the button or presses Enter in a text field.
     */
    public void actionPerformed(ActionEvent e) {
        if ("clear".equals(e.getActionCommand())) {
            addressSet = false;
            eventNameField.setText("");
            ticketQuantityField.setText("");
        } else {
            addressSet = true;
        }
        updateDisplays();
    }

    protected void updateDisplays() {
        actualAvailabilityDisplay.setText(getActualAvailability());
        if (addressSet) {
            actualAvailabilityDisplay.setFont(regularFont);
        } else {
            actualAvailabilityDisplay.setFont(italicFont);
        }
    }

    protected JComponent createActualAvailabilityDisplay() {
        JPanel panel = new JPanel(new BorderLayout());
        actualAvailabilityDisplay = new JLabel();
        actualAvailabilityDisplay.setHorizontalAlignment(JLabel.CENTER);
        regularFont = actualAvailabilityDisplay.getFont().deriveFont(Font.BOLD, 16.0f);
        italicFont = regularFont.deriveFont(Font.ITALIC);
        updateDisplays();

        // Lay out the panel.
        panel.setBorder(BorderFactory.createEmptyBorder(
                GAP_BETWEEN -5,
                0,
                GAP_BETWEEN -5,
                0));
        panel.add(new JSeparator(JSeparator.VERTICAL), BorderLayout.LINE_START);
        panel.add(actualAvailabilityDisplay, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(200, 150));

        return panel;
    }

    protected String getActualAvailability() {
        if (!addressSet)
            return "Actual Availability.";

        String eventName = eventNameField.getText();
        String ticketQuantity = ticketQuantityField.getText();
        String ticketQuantityToBuy = ticketQuantityToBuyField.getText();
        String empty = "";

        if ((ticketQuantityToBuy == null) || empty.equals(ticketQuantityToBuy)) {
            ticketQuantityToBuy = "<em>(There is no enough tickets for this event. Please select a number of Tickets)</em>";
        }

        return "<html><p align=center>" +" Actual Availability "+
                eventName +
                "<br>" +
                "</p></html>";
    }


    /**
     * Called when one of the fields gets the focus so that the
     * focused field is selectable.
     */
    public void focusGained(FocusEvent e) {
        Component c = e.getComponent();
        if (c instanceof JTextField) {
            selectItLater(c);
        } else if (c instanceof JTextField) {
            ((JTextField) c).selectAll();
        }
    }

    // Workaround for formatted text field focus side effects.
    protected void selectItLater(Component c) {
        if (c instanceof JTextField) {
            final JTextField ftf = (JTextField) c;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ftf.selectAll();
                }
            });
        }
    }

    // Needed for FocusListener interface.
    public void focusLost(FocusEvent e) {
    } // ignore

    protected JComponent createEntryFields() {
        JPanel panel = new JPanel(new SpringLayout());

        String[] labelStrings = { "Event: ", "Quantity: ", "Booked Tickets: "};

        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] fields = new JComponent[labelStrings.length];
        int fieldNum = 0;

        // Create the text field and set it up.
        eventNameField = new JTextField();
        eventNameField.setColumns(TEXTFIELD_COLUMN);
        fields[fieldNum++] = eventNameField;

        ticketQuantityField = new JTextField();
        ticketQuantityField.setColumns(TEXTFIELD_COLUMN);
        fields[fieldNum++] = ticketQuantityField;

        ticketQuantityToBuyField = new JTextField();
        ticketQuantityField.setColumns(TEXTFIELD_COLUMN);
        fields[fieldNum++] = ticketQuantityToBuyField;

        // Associate label/field pairs, add everything,
        // and lay it out.
        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i], JLabel.TRAILING);
            labels[i].setLabelFor(fields[i]);
            panel.add(labels[i]);
            panel.add(fields[i]);

            // Add listeners to each field.
            JTextField actualField = (JTextField) fields[i];

            actualField.addActionListener(this);
            actualField.addFocusListener(this);
        }
        EventUtilitiesUI.buildCompactGrid(
                panel,
                labelStrings.length,
                2, 0, 0, 0, GAP_BETWEEN);
        return panel;
    }

}