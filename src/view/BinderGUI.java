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
    private Button btnView;
    private Button btnRemove;
    private Button btnTrade;
    private Button btnSell;
    private Label binderNameLabel;
    private Label binderValueLabel;
    private Label collectorMoneyLabel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private boolean isFieldsEmpty;
    private boolean isBoxesEmpty;

    public BinderGUI() {
        super();
        btnBack = new Button("Back");
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

    public void displayAddCardToBinder(String[] binderNames, String[] cardNames, ActionListener listener) {
        // ActionListener for combo boxes
        ActionListener boxListener = e -> {
            if (e.getSource() == binderListBox || e.getSource() == cardListBox) {
                btnAdd.setEnabled(binderListBox.getSelectedIndex() > 0 && cardListBox.getSelectedIndex() > 0);
            }
        };

        renameWindow("Add Card to Binder");

        // Main panel with vertical layout
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setOpaque(false);

        // Binder selection
        JPanel binderPanel = new JPanel();
        Label binderLabel = new Label("Select Binder: ", Font.PLAIN, 25);
        binderListBox = new ComboBox(binderNames);
        binderListBox.insertItemAt("", 0); // Add empty first item
        binderListBox.setSelectedIndex(0); // Select empty item by default
        binderListBox.addActionListener(boxListener); // Add listener here
        setPanel(binderLabel, binderListBox, binderPanel);

        // Card selection
        JPanel cardPanel = new JPanel();
        Label cardLabel = new Label("Select Card: ", Font.PLAIN, 25);
        cardListBox = new ComboBox(cardNames);
        cardListBox.insertItemAt("", 0); // Add empty first item
        cardListBox.setSelectedIndex(0); // Select empty item by default
        cardListBox.addActionListener(boxListener); // Add listener here
        setPanel(cardLabel, cardListBox, cardPanel);

        setPanels(new JPanel[]{binderPanel, cardPanel}, body);

        // Button panel
        JPanel options = new JPanel();
        options.setOpaque(false);
        btnAdd = new Button("Add");
        btnAdd.setEnabled(false); // Disable initially

        // Add buttons and listeners
        setPanel(new Button[]{btnBack, btnAdd}, options);
        setActionListener(new Button[]{btnBack, btnAdd}, listener);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displayRemoveCardFromBinder(String[] binderNames, ActionListener listener) {
        // ActionListener for combo boxes
        ActionListener boxListener = e -> {
            if (e.getSource() == binderListBox) {
                // Just notify the controller that binder selection changed
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "BinderSelectionChanged"));
                btnRemove.setEnabled(false); // Disable remove button until both selections are made
                if (binderListBox.getSelectedIndex() == 0) {
                    updateCardList(new String[]{});
                }
            } else if (e.getSource() == cardListBox) {
                btnRemove.setEnabled(binderListBox.getSelectedIndex() > 0 && cardListBox.getSelectedIndex() > 0);
            }
        };

        renameWindow("Remove Card from Binder");

        // Main panel with vertical layout
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setOpaque(false);

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

        setPanels(new JPanel[]{binderPanel, cardPanel}, body);

        // Button panel
        JPanel options = new JPanel();
        options.setOpaque(false);
        btnRemove = new Button("Remove");
        btnRemove.setEnabled(false); // Disable initially

        // Add buttons and listeners
        setPanel(new Button[]{btnBack, btnRemove}, options);
        setActionListener(new Button[]{btnBack, btnRemove}, listener);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displaySelectBinder(String[] binderNames, ActionListener listener) {
        // ActionListener to track selection changes
        ActionListener boxListener = e -> {
            if (e.getSource() == binderListBox) {
                btnView.setEnabled(binderListBox.getSelectedIndex() != 0);
            }
        };

        renameWindow("Select a Binder");

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
        JPanel options = new JPanel();
        options.setOpaque(false);
        btnView = new Button("View");
        btnView.setEnabled(false);

        // Add buttons and listener
        setPanel(new Button[]{btnBack, btnView}, options);
        setActionListener(new Button[]{btnBack, btnView}, listener);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displayViewBinder(String binderName, String binderType, String[] cardNames, ActionListener listener) {
        renameWindow("View Binder: " + binderName);

        // Main panel with vertical layout
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setOpaque(false);

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

        body.add(infoPanel);
        body.add(Box.createVerticalStrut(20));

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

        body.add(scrollPane);
        body.add(Box.createVerticalStrut(20));

        // Back button panel
        JPanel options = new JPanel();
        options.setOpaque(false);
        btnBack.addActionListener(listener);
        options.add(btnBack);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
    }

    public void displayTrade(String[] binderNames, ActionListener listener) {
        // ActionListener for combo boxes
        ActionListener boxListener = e -> {
            if (e.getSource() == binderListBox) {
                // Just notify the controller that binder selection changed
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "BinderSelectionChanged"));
                btnTrade.setEnabled(false); // Disable remove button until both selections are made
                if (binderListBox.getSelectedIndex() == 0) {
                    updateCardList(new String[]{});
                }
            } else if (e.getSource() == cardListBox) {
                btnTrade.setEnabled(binderListBox.getSelectedIndex() > 0 && cardListBox.getSelectedIndex() > 0);
            }
        };

        renameWindow("Trade");

        JPanel body = new JPanel();
        body.setOpaque(false);
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));

        JPanel binderSelect = new JPanel();
        Label binderLabel = new Label("Select Binder:", Font.PLAIN, 25);
        binderListBox = new ComboBox(binderNames);
        binderListBox.insertItemAt("", 0);
        binderListBox.setSelectedIndex(0);
        binderListBox.addActionListener(boxListener);
        setPanel(binderLabel, binderListBox, binderSelect);

        // Card selection
        JPanel cardSelect = new JPanel();
        Label cardLabel = new Label("Select Card:", Font.PLAIN, 25);
        cardListBox = new ComboBox(new String[]{}); // Initialize empty
        cardListBox.addActionListener(boxListener);
        setPanel(cardLabel, cardListBox, cardSelect);

        setPanels(new JPanel[]{binderSelect, cardSelect}, body);

        JPanel options = new JPanel();
        options.setOpaque(false);
        options.add(btnBack);
        btnTrade = new Button("Trade");
        btnTrade.setEnabled(false);

        setPanel(new Button[]{btnBack, btnTrade}, options);
        setActionListener(new Button[]{btnBack, btnTrade}, listener);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displaySellBinder(String[] bindersList, double money, ActionListener listener) {
        ActionListener updateListener = e -> {
            if (e.getSource() == binderListBox) {
                rightPanel.setVisible(binderListBox.getSelectedIndex() != 0);
            }
        };

        renameWindow("Sell a Binder");

        leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(450, 0));

        rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(450, 0));

        leftPanel.add(Box.createVerticalStrut(15));
        binderListBox = new ComboBox(bindersList);
        binderListBox.setActionCommand("Select");
        binderListBox.insertItemAt("", 0);
        binderListBox.setSelectedIndex(0);
        leftPanel.add(binderListBox);

        leftPanel.add(Box.createVerticalStrut(300));
        Label collectorLabel = new Label("You currently have", Font.PLAIN, 25);
        leftPanel.add(collectorLabel);

        collectorMoneyLabel = new Label("$", Font.BOLD, 25);
        collectorMoneyLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#BCBEC4"), 2, true));
        leftPanel.add(collectorMoneyLabel);
        setCollectorMoneyLabel(money);

        leftPanel.setVisible(true);

        binderNameLabel = new Label("Name", Font.PLAIN, 25);
        rightPanel.add(binderNameLabel);
        binderValueLabel = new Label("Value: $", Font.PLAIN, 25);
        rightPanel.add(binderValueLabel);

        rightPanel.add(Box.createVerticalStrut(15));
        btnSell = new Button("Sell");
        rightPanel.add(btnSell);

        rightPanel.setVisible(false);

        JPanel options = new JPanel();
        options.setOpaque(false);
        options.add(btnBack);
        options.setVisible(true);

        binderListBox.addActionListener(updateListener);
        binderListBox.addActionListener(listener);
        setActionListener(new Button[]{btnBack, btnSell}, listener);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(options, BorderLayout.SOUTH);
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

    public void setBinderNameLabel(String binderName) {
        binderNameLabel.setText(binderName);
    }

    public void setBinderValueLabel(double value) {
        binderValueLabel.setText("Value: $" + value);
    }

    public void setCollectorMoneyLabel(double money) {
        collectorMoneyLabel.setText("$" + money);
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

    public String getSelectedBinderName() {
        if (binderListBox != null && binderListBox.getSelectedIndex() > 0) {
            return (String) binderListBox.getSelectedItem();
        }
        return null;
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
        cardListBox.setModel(new DefaultComboBoxModel<String>(cardNames));
        cardListBox.insertItemAt("", 0);
        cardListBox.setSelectedIndex(0);
    }

    public void resetDisplayAddCardToBinder(boolean isBinderFull, int cardCollectionCount) {
        if (isBinderFull) {
            binderListBox.removeItemAt(getSelectedBinderIndex() + 1);
        }
        if (cardCollectionCount == 0) {
            cardListBox.removeItemAt(getSelectedCardIndex() + 1);
        }
        binderListBox.setSelectedIndex(0);
        cardListBox.setSelectedIndex(0);
    }

    public void resetDisplayRemoveCardFromBinder(boolean isBinderEmpty) {
        if (isBinderEmpty) {
            binderListBox.removeItemAt(getSelectedBinderIndex() + 1);
        }

        updateCardList(new String[]{});
        binderListBox.setSelectedIndex(0);
        cardListBox.setSelectedIndex(0);
    }

    public void resetDisplaySellBinder(double money) {
        setCollectorMoneyLabel(money);
        binderListBox.removeItemAt(getSelectedBinderIndex() + 1);
        binderListBox.setSelectedIndex(0);
        rightPanel.setVisible(false);
    }

    public void resetDisplayTrade() {
        binderListBox.setSelectedIndex(0);
        cardListBox.setSelectedIndex(0);
        btnTrade.setEnabled(false);
    }
}
