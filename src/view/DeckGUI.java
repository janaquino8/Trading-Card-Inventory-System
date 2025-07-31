package src.view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class DeckGUI extends Frame {
    private TextField deckNameField;
    private ComboBox deckTypeBox;
    private ComboBox deckListBox;
    private Button btnBack;
    private Button btnAdd;
    private Button btnDelete;
    private Label deckNameLabel;
    private Label collectorMoneyLabel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private boolean isFieldsEmpty;
    private boolean isBoxesEmpty;

    public DeckGUI() {
        super();
        btnBack = new Button("Back");
    }

    public void displayCreateDeck(ActionListener listener) {
        ActionListener boxListener = e -> {
            if (e.getSource() == deckTypeBox) {
                isBoxesEmpty = getDeckType() <= 0;
                updateBtnAdd();
            }
        };
        DocumentListener fieldListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isFieldsEmpty = getDeckName().isEmpty();
                updateBtnAdd();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isFieldsEmpty = getDeckName().isEmpty();
                updateBtnAdd();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        };

        renameWindow("Create a Deck");
        isBoxesEmpty = true;

        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setOpaque(false);

        JPanel deckName = new JPanel();
        Label deckNameLabel = new Label("Name: ", Font.PLAIN, 30);
        deckNameField = new TextField();
        setPanel(deckNameLabel, deckNameField, deckName);

        // maybe a checkbox(?)
        JPanel deckType = new JPanel();
        Label deckTypeLabel = new Label("Type: ", Font.PLAIN, 30);
        deckTypeBox = new ComboBox(new String[]{"", "Normal", "Sellable"});
        setPanel(deckTypeLabel, deckTypeBox, deckType);

        deckTypeBox.addActionListener(boxListener);
        deckNameField.getDocument().addDocumentListener(fieldListener);
        setPanels(new JPanel[]{deckName, deckType}, body);

        JPanel options = new JPanel();
        btnAdd = new Button("Add");
        btnAdd.setEnabled(false);
        setPanel(new Button[]{btnBack, btnAdd}, options);

        setActionListener(new Button[]{btnBack, btnAdd}, listener);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displayDeleteDeck(String[] decksList, ActionListener listener) {
        ActionListener boxListener = e -> {
            if (e.getSource() == deckListBox) {
                btnDelete.setEnabled(deckListBox.getSelectedIndex() != 0);
            }
        };

        renameWindow("Delete a Deck");

        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setOpaque(false);

        body.add(Box.createVerticalStrut(15));
        deckListBox = new ComboBox(decksList);
        deckListBox.insertItemAt("", 0);
        deckListBox.setSelectedIndex(0);
        body.add(deckListBox);

        body.add(Box.createVerticalStrut(15));
        btnDelete = new Button("Delete");
        btnDelete.setEnabled(false);
        body.add(btnDelete);

        body.setVisible(true);

        JPanel options = new JPanel();
        options.setOpaque(false);
        options.add(btnBack);

        options.setVisible(true);

        deckListBox.addActionListener(boxListener);
        setActionListener(new Button[]{btnBack, btnDelete}, listener);

        this.add(body, BorderLayout.CENTER);
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

    private void updateBtnAdd() {
        btnAdd.setEnabled(!isFieldsEmpty && !isBoxesEmpty);
    }

    public String getDeckName() {
        if (deckNameField != null) {
            return deckNameField.getText();
        }
        return "";
    }

    public int getDeckType() {
        if (deckTypeBox != null) {
            return deckTypeBox.getSelectedIndex();
        }
        return -1;
    }
    public int getSelectedDeckIndex() {
        if (deckListBox != null) {
            return deckListBox.getSelectedIndex() - 1;
        }
        return -1;
    }

    public void removeDeckFromList(int index) {
        if (deckListBox != null && deckListBox.getItemCount() > index) {
            deckListBox.removeItemAt(index);
            deckListBox.setSelectedIndex(0);
            btnDelete.setEnabled(false);
        }
    }
}
