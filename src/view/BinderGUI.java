package src.view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class BinderGUI extends Frame {
    private TextField binderNameField;
    private ComboBox binderTypeBox;
    private ComboBox binderListBox;
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

    public void removeBinderFromList(int index) {
        if (binderListBox != null && binderListBox.getItemCount() > index) {
            binderListBox.removeItemAt(index);
            binderListBox.setSelectedIndex(0);
            btnDelete.setEnabled(false);
        }
    }
}
