package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CollectorGUI extends Frame {
    private JPanel body;

    public CollectorGUI() {
        super();
        body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setOpaque(false);
        this.add(body, BorderLayout.CENTER);
    }

    public void displayMainMenu(int collectionCardCount, int binderCount, int deckCount, ActionListener listener) {
        this.renameWindow("Main Menu");
        clearBody();

        String btn1Label = collectionCardCount > 0 ? "Manage Collection" : "Add a Card";
        String btn2Label = binderCount > 0 ? "Manage Binders" : "Create a Binder";
        String btn3Label = deckCount > 0 ? "Manage Decks" : "Create a Deck";

        Button button1 = new Button(btn1Label);
        Button button2 = new Button(btn2Label);
        Button button3 = new Button(btn3Label);

        addButtonsToBody(new Button[]{button1, button2, button3}, listener);
    }

    public void displayManageCollection(int collectionTotalCount, ActionListener listener) {
        this.renameWindow("Manage Collection");
        clearBody();

        Button button1 = new Button("Add a Card");
        Button button2 = new Button("Update Card Count");
        Button button3 = new Button("Display a Card");
        Button button4 = new Button("Display Collection");
        Button button5 = new Button("Sell a Card");
        Button button6 = new Button("Return to Main Menu");

        if (collectionTotalCount == 0) {
            disableButtons(new Button[]{button3, button4, button5});
        }

        addButtonsToBody(new Button[]{button1, button2, button3, button4, button5, button6}, listener);
    }

    public void displayManageBinders(int binderCount, int collectionTotalCount, boolean isBindersFull, boolean isBindersEmpty, int sellableBindersCount, ActionListener listener) {
        this.renameWindow("Manage Binders");
        clearBody();

        Button button1 = new Button("Create a Binder");
        Button button2 = new Button("Delete a Binder");
        Button button3 = new Button("Add a Card to a Binder");
        Button button4 = new Button("Remove a Card From a Binder");
        Button button5 = new Button("View a Binder");
        Button button6 = new Button("Trade");
        Button button7 = new Button("Sell a Binder");
        Button button8 = new Button("Return to Main Menu");

        if (binderCount == 0) {
            disableButtons(new Button[]{button2, button3, button4, button5, button6, button7});
        }
        else {
            if (collectionTotalCount == 0 || isBindersFull) {
                button3.setEnabled(false);
            }
            if (isBindersEmpty) {
                disableButtons(new Button[]{button4, button6});
            }
            if (sellableBindersCount == 0) {
                button7.setEnabled(false);
            }
        }

        addButtonsToBody(new Button[]{button1, button2, button3, button4, button5, button6, button7, button8}, listener);
    }

    public void displayManageDecks(int deckCount, int collectionTotalCount, boolean isDecksFull, boolean isDecksEmpty, int sellableDecksCount, ActionListener listener) {
        this.renameWindow("Manage Decks");
        clearBody();

        Button button1 = new Button("Create a Deck");
        Button button2 = new Button("Delete a Deck");
        Button button3 = new Button("Add a Card to a Deck");
        Button button4 = new Button("Remove a Card From a Deck");
        Button button5 = new Button("View a Deck");
        Button button6 = new Button("Sell a Deck");
        Button button7 = new Button("Return to Main Menu");

        if (deckCount == 0) {
            disableButtons(new Button[]{button2, button3, button4, button5, button6});
        }
        else {
            if (collectionTotalCount == 0 || isDecksFull) {
                button3.setEnabled(false);
            }
            if (isDecksEmpty) {
                button4.setEnabled(false);
            }
            if (sellableDecksCount == 0) {
                button6.setEnabled(false);
            }
        }

        addButtonsToBody(new Button[]{button1, button2, button3, button4, button5, button6, button7}, listener);
    }

    private void addButtonsToBody(Button[] buttons, ActionListener listener) {
        for (Button b : buttons) {
            body.add(Box.createVerticalStrut(15));
            body.add(b);
            b.addActionListener(listener);
        }
    }

    private void disableButtons(Button[] buttons) {
        for (Button b : buttons) {
            b.setEnabled(false);
        }
    }

    private void clearBody() {
        body.removeAll();
        body.revalidate();
        body.repaint();
    }
}
