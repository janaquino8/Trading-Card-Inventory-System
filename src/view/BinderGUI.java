package src.view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BinderGUI extends Frame {
    private TextField binderNameField;
    private ComboBox binderTypeBox;
    private ComboBox binderListBox;
    private ComboBox cardListBox;
    private Button btnBack;
    private Button btnAdd;
    private Button btnDelete;
    private Label binderNameLabel;
    private Label collectorMoneyLabel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private boolean isFieldsEmpty;
    private boolean isBoxesEmpty;

    public BinderGUI() {
        super();
        btnBack = new Button("Back");
        btnAdd = new Button("Add");
    }

    public void displayCreateBinder(ActionListener listener) {
        ActionListener boxListener = e -> {
            if (e.getSource() == binderTypeBox) {
                isBoxesEmpty = getBinderType() <= 0;
                updateBtnAdd();
            }
        };
        DocumentListener fieldListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isFieldsEmpty = getBinderName().isEmpty();
                updateBtnAdd();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isFieldsEmpty = getBinderName().isEmpty();
                updateBtnAdd();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        };

        renameWindow("Create a Binder");
        isBoxesEmpty = true;

        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setOpaque(false);

        JPanel binderName = new JPanel();
        Label binderNameLabel = new Label("Name: ", Font.PLAIN, 30);
        binderNameField = new TextField();
        setPanel(binderNameLabel, binderNameField, binderName);

        JPanel binderType = new JPanel();
        Label binderTypeLabel = new Label("Type: ", Font.PLAIN, 30);
        binderTypeBox = new ComboBox(new String[]{"", "Non-curated", "Pauper", "Rares", "Luxury", "Collector"});
        setPanel(binderTypeLabel, binderTypeBox, binderType);

        binderTypeBox.addActionListener(boxListener);
        binderNameField.getDocument().addDocumentListener(fieldListener);
        setPanels(new JPanel[]{binderName, binderType}, body);

        JPanel options = new JPanel();
        btnAdd = new Button("Add");
        btnAdd.setEnabled(false);
        setPanel(new Button[]{btnBack, btnAdd}, options);

        setActionListener(new Button[]{btnBack, btnAdd}, listener);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displayDeleteBinder(String[] bindersList, ActionListener listener) {
        ActionListener boxListener = e -> {
            if (e.getSource() == binderListBox) {
                btnDelete.setEnabled(binderListBox.getSelectedIndex() != 0);
            }
        };

        renameWindow("Delete a Binder");

        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setOpaque(false);

        body.add(Box.createVerticalStrut(15));
        binderListBox = new ComboBox(bindersList);
        binderListBox.insertItemAt("", 0);
        binderListBox.setSelectedIndex(0);
        body.add(binderListBox);

        body.add(Box.createVerticalStrut(15));
        btnDelete = new Button("Delete");
        btnDelete.setEnabled(false);
        body.add(btnDelete);

        body.setVisible(true);

        JPanel options = new JPanel();
        options.setOpaque(false);
        options.add(btnBack);

        options.setVisible(true);

        binderListBox.addActionListener(boxListener);
        setActionListener(new Button[]{btnBack, btnDelete}, listener);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displayViewBinder(String binderName, String binderType, String[] cardNames, ActionListener listener) {
        renameWindow("View Binder: " + binderName);

        // Main panel with vertical layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);

        // Binder info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 1));
        infoPanel.setOpaque(false);

        Label nameLabel = new Label(binderName, Font.BOLD, 30);
        nameLabel.setForeground(Color.decode("#FF6666")); // Red color
        infoPanel.add(nameLabel);

        Label typeLabel = new Label("Type: " + binderType, Font.PLAIN, 25);
        typeLabel.setForeground(Color.decode("#66B2FF")); // Blue color
        infoPanel.add(typeLabel);

        mainPanel.add(infoPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Cards list panel with scroll
        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setOpaque(false);

        // Add card names with borders
        for (String cardName : cardNames) {
            JPanel cardPanel = new JPanel(new BorderLayout());
            cardPanel.setOpaque(false);
            cardPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#BCBEC4")));
            cardPanel.setBorder(BorderFactory.createCompoundBorder(
                    cardPanel.getBorder(),
                    BorderFactory.createEmptyBorder(0, 15, 0, 0) // Add 15px left padding
            ));

            Label cardLabel = new Label(cardName, Font.PLAIN, 20);
            cardLabel.setForeground(Color.decode("#CC99FF")); // Purple color
            cardLabel.setHorizontalAlignment(SwingConstants.LEFT);
            cardPanel.add(cardLabel, BorderLayout.WEST);

            cardsPanel.add(cardPanel);
            cardsPanel.add(Box.createVerticalStrut(5));
        }

        // Scroll pane for cards
        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setPreferredSize(new Dimension(350, 300));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        mainPanel.add(scrollPane);
        mainPanel.add(Box.createVerticalStrut(20));

        // Back button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        setActionListener(new Button[]{btnBack}, listener);
        buttonPanel.add(btnBack);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
    }

    public void displaySelectBinder(String[] binderNames, ActionListener listener) {
        renameWindow("Select a Binder");

        // Declare btnView first
        Button btnView = new Button("View");
        btnView.setEnabled(false);

        // ActionListener to track selection changes
        ActionListener boxListener = e -> {
            boolean binderSelected = binderListBox.getSelectedIndex() > 0;
            btnView.setEnabled(binderSelected); // Enable "View" only if a binder is selected
        };

        // Main content panel
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setOpaque(false);

        // Binder selection combo box
        body.add(Box.createVerticalStrut(20));
        binderListBox = new ComboBox(binderNames);
        binderListBox.insertItemAt("", 0); // Add empty first item
        binderListBox.setSelectedIndex(0); // Default to empty selection
        binderListBox.addActionListener(boxListener); // Track selection changes
        body.add(binderListBox);
        body.add(Box.createVerticalStrut(20));

        // Button panel with both View and Back buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        Button btnBack = new Button("Back");

        // Set action commands
        btnView.setActionCommand("View");
        btnBack.setActionCommand("Back");

        // Add both buttons to panel
        buttonPanel.add(btnView);
        buttonPanel.add(btnBack);

        // Set listeners for both buttons
        setActionListener(new Button[]{btnView, btnBack}, listener);

        this.add(body, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void displayAddCardToBinder(String[] binderNames, String[] cardNames, ActionListener listener) {
        renameWindow("Add Card to Binder");

        // ActionListener for combo boxes
        ActionListener boxListener = e -> {
            boolean binderSelected = binderListBox.getSelectedIndex() > 0;
            boolean cardSelected = cardListBox.getSelectedIndex() > 0;
            btnAdd.setEnabled(binderSelected && cardSelected);
        };

        // Main panel with vertical layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);

        // Binder selection
        JPanel binderPanel = new JPanel();
        Label binderLabel = new Label("Select Binder:", Font.PLAIN, 25);
        binderListBox = new ComboBox(binderNames);
        binderListBox.insertItemAt("", 0); // Add empty first item
        binderListBox.setSelectedIndex(0); // Select empty item by default
        binderListBox.addActionListener(boxListener); // Add listener here
        setPanel(binderLabel, binderListBox, binderPanel);

        // Card selection
        JPanel cardPanel = new JPanel();
        Label cardLabel = new Label("Select Card:", Font.PLAIN, 25);
        cardListBox = new ComboBox(cardNames);
        cardListBox.insertItemAt("", 0); // Add empty first item
        cardListBox.setSelectedIndex(0); // Select empty item by default
        cardListBox.addActionListener(boxListener); // Add listener here
        setPanel(cardLabel, cardListBox, cardPanel);

        mainPanel.add(binderPanel);
        mainPanel.add(cardPanel);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        btnAdd.setEnabled(false); // Disable initially
        Button btnBack = new Button("Back");

        // Set action commands
        btnAdd.setActionCommand("AddToBinder");
        btnBack.setActionCommand("Back");

        // Add buttons and listeners
        setActionListener(new Button[]{btnAdd, btnBack}, listener);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnBack);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void displayRemoveCardFromBinder(String[] binderNames, ActionListener listener) {
        renameWindow("Remove Card from Binder");

        // ActionListener for combo boxes
        ActionListener boxListener = e -> {
            if (e.getSource() == binderListBox) {
                // Just notify the controller that binder selection changed
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "BinderSelectionChanged"));
                btnAdd.setEnabled(false); // Disable remove button until both selections are made
            } else if (e.getSource() == cardListBox) {
                boolean binderSelected = binderListBox.getSelectedIndex() > 0;
                boolean cardSelected = cardListBox.getSelectedIndex() > 0;
                btnAdd.setEnabled(binderSelected && cardSelected);
            }
        };

        // Main panel with vertical layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);

        // Binder selection
        JPanel binderPanel = new JPanel();
        Label binderLabel = new Label("Select Binder:", Font.PLAIN, 25);
        binderListBox = new ComboBox(binderNames);
        binderListBox.insertItemAt("", 0);
        binderListBox.setSelectedIndex(0);
        binderListBox.addActionListener(boxListener);
        setPanel(binderLabel, binderListBox, binderPanel);

        // Card selection
        JPanel cardPanel = new JPanel();
        Label cardLabel = new Label("Select Card:", Font.PLAIN, 25);
        cardListBox = new ComboBox(new String[]{""}); // Initialize empty
        cardListBox.addActionListener(boxListener);
        setPanel(cardLabel, cardListBox, cardPanel);

        mainPanel.add(binderPanel);
        mainPanel.add(cardPanel);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        btnAdd = new Button("Remove");
        btnAdd.setActionCommand("RemoveFromBinder");
        btnAdd.setEnabled(false); // Disable initially
        Button btnBack = new Button("Back");

        // Add buttons and listeners
        setActionListener(new Button[]{btnAdd, btnBack}, listener);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnBack);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setPanel(Label label, TextField field, JPanel panel) {
        panel.add(label);
        panel.add(field);
        panel.setOpaque(false);
    }

    private void setPanel(Label label, ComboBox box, JPanel panel) {
        panel.add(label);
        panel.add(box);
        panel.setOpaque(false);
    }

    private void setPanel(Button[] buttons, JPanel panel) {
        for (Button b : buttons) {
            panel.add(b);
        }
        panel.setOpaque(false);
    }

    private void setPanels(JPanel[] panelList, JPanel mainPanel) {
        for (JPanel p : panelList) {
            mainPanel.add(Box.createVerticalStrut(15));
            mainPanel.add(p);
        }
    }

    private void setActionListener(Button[] buttons, ActionListener listener) {
        for (Button b : buttons) {
            b.addActionListener(listener);
        }
    }

    private void setActionListener(ComboBox[] comboBoxes, ActionListener listener) {
        for (ComboBox c : comboBoxes) {
            c.addActionListener(listener);
        }
    }

    private void setDocumentListener(TextField[] textFields, DocumentListener listener) {
        for (TextField t : textFields) {
            t.getDocument().addDocumentListener(listener);
        }
    }

    private void updateBtnAdd() {
        btnAdd.setEnabled(!isFieldsEmpty && !isBoxesEmpty);
    }

    public String getBinderName() {
        if (binderNameField != null) {
            return binderNameField.getText();
        }
        return "";
    }

    public int getBinderType() {
        if (binderTypeBox != null) {
            return binderTypeBox.getSelectedIndex();
        }
        return -1;
    }
    public int getSelectedBinderIndex() {
        if (binderListBox != null) {
            return binderListBox.getSelectedIndex() - 1;
        }
        return -1;
    }

    public int getSelectedCardIndex() {
        if (cardListBox != null) {
            return cardListBox.getSelectedIndex() - 1;
        }
        return -1;
    }

    public String getSelectedCardName() {
        if (cardListBox != null && cardListBox.getSelectedIndex() > 0) {
            return (String) cardListBox.getSelectedItem();
        }
        return null;
    }

    public void removeBinderFromList(int index) {
        if (binderListBox != null && binderListBox.getItemCount() > index) {
            binderListBox.removeItemAt(index);
            binderListBox.setSelectedIndex(0);
            btnDelete.setEnabled(false);
        }
    }

    public void updateCardList(String[] cardNames) {
        cardListBox.setModel(new DefaultComboBoxModel<>(cardNames));  // Add diamond operator <>
        cardListBox.insertItemAt("", 0);
        cardListBox.setSelectedIndex(0);
    }
}
