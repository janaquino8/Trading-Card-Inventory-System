package src.controller;

import java.util.*;

import src.model.*;
import src.model.card.Card;
import src.model.holders.NameSorter;
import src.model.holders.binder.*;
import src.model.holders.deck.*;
import src.view.*;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewController {
    Collector collector;
    CollectorGUI collectorGUI;
    CollectionGUI collectionGUI;
    BinderGUI binderGUI;
    DeckGUI deckGUI;
    CardGUI cardGUI;
    ActionListener actionListener;
    String previousMenu;

    /**
     * Controller
     * Constructor to construct a Collector object
     */
    public NewController() {
        collector = new Collector();
        collectorGUI = new CollectorGUI();
        mainMenu();
    }

    public void mainMenu() {
        previousMenu = "main";
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Add a Card"        -> addCard();
                case "Add a Binder"      -> System.out.println("Add Binder");
                case "Add a Deck"        -> System.out.println("Add Deck");
                case "Manage Collection" -> manageCollection();
                case "Manage Binders"    -> manageBinders();
                case "Manage Decks"      -> manageDecks();
            }
        };

        collectorGUI.displayMainMenu(collector.getCollectionCardCount(), collector.getBindersCount(),
                               collector.getDecksCount(), actionListener);
    }

    public void manageCollection() {
        previousMenu = "collection";
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Add a Card"           -> addCard();
                case "Update Card Count"    -> updateCardCount();
                case "Display a Card"       -> displayCard();
                case "Display Collection"   -> displayCollection();
                case "Sell a Card"          -> System.out.println("5");
                case "Return to Main Menu"  -> mainMenu();
            }
        };

        collectorGUI.displayManageCollection(collector.getCollectionTotalCount(),
                actionListener);
    }

    public void manageBinders() {
        previousMenu = "binder";
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Create a Binder"              -> System.out.println("1");
                case "Delete a Binder"              -> System.out.println("2");
                case "Add a Card to a Binder"       -> System.out.println("3");
                case "Remove a Card From a Binder"  -> System.out.println("4");
                case "View a Binder"                -> System.out.println("5");
                case "Trade"                        -> System.out.println("6");
                case "Sell a Binder"                -> System.out.println("7");
                case "Return to Main Menu"          -> mainMenu();
            }
        };

        collectorGUI.displayManageBinders(collector.getBindersCount(), collector.getCollectionTotalCount(),
                collector.isBindersFull(), collector.isBindersEmpty(), collector.getSellableBinders().size(),
                actionListener);
    }

    public void manageDecks() {
        previousMenu = "deck";
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Create a Deck"              -> System.out.println("1");
                case "Delete a Deck"              -> System.out.println("2");
                case "Add a Card to a Deck"       -> System.out.println("3");
                case "Remove a Card From a Deck"  -> System.out.println("4");
                case "View a Deck"                -> System.out.println("5");
                case "Sell a Deck"                -> System.out.println("6");
                case "Return to Main Menu"        -> mainMenu();
            }
        };

        collectorGUI.displayManageDecks(collector.getDecksCount(), collector.getCollectionTotalCount(),
                collector.isDecksFull(), collector.isDecksEmpty(), collector.getSellableBinders().size(),
                actionListener);
    }

    public void addCard() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(collectionGUI);
                case "Add" -> {
                    int index = collector.getCollection().findCard(collectionGUI.getCardName());

                    if (index != -1) {
                        Card c = collector.getCollection().getCard(index);

                        String msg = "Card " + c.getName() + " already exists with the following details:\n";
                        msg += "Name: " + c.getName() + "\n";
                        msg += "Rarity: " + c.getRarity().getName() + "\n";
                        msg += "Variant: " + c.getVariant().getName() + "\n";
                        msg += "Value: " + c.getFinalValue() + "$\n";
                        msg += "Would you like to add another copy to the collection?";

                        if (JOptionPane.showConfirmDialog(null, msg, "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
                            collector.getCollection().getCard(index).incrementCollectionCount();

                            msg = "A copy of " + c.getName() + " has been added to the collection.";
                            JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else {
                        String msg = "Card " + collectionGUI.getCardName() + " does not exist. Would you like to add to collection?";
                        if (JOptionPane.showConfirmDialog(null, msg, "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
                            collector.getCollection().addCard(collectionGUI.getCardName(), collectionGUI.getRarity(),
                                    collectionGUI.getVariant(), collectionGUI.getValue());

                            msg = collectionGUI.getCardName() + " has been added to the collection.";
                            JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        };

        collectorGUI.dispose();
        collectionGUI = new CollectionGUI();

        collectionGUI.displayAddCard(actionListener);
    }

    public void updateCardCount() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(collectionGUI);
                case "Select" -> {
                    int index = collectionGUI.getSelectedCardIndex();
                    if (index >= 0) {
                        Card c = collector.getCollection().getCard(index);
                        collectionGUI.setCardNameField(c.getName());
                        collectionGUI.setCardCountField(c.getCollectionCount());
                    }
                }
                case "Increment" -> {
                    int index = collectionGUI.getSelectedCardIndex();
                    collector.getCollection().getCard(index).incrementCollectionCount();
                    collectionGUI.setCardCountField(collector.getCollection().getCard(index).getCollectionCount());
                }
                case "Decrement" -> {
                    int index = collectionGUI.getSelectedCardIndex();
                    collector.getCollection().getCard(index).decrementCollectionCount();
                    collectionGUI.setCardCountField(collector.getCollection().getCard(index).getCollectionCount());
                }
            }
        };

        collectorGUI.dispose();
        collectionGUI = new CollectionGUI();
        ArrayList<Card> displayableCards = new ArrayList<Card>(collector.getCollection().getCards());
        ArrayList<String> displayableCardsList = new ArrayList<String>();

        for (Card c : displayableCards) {
            displayableCardsList.add(c.getName());
        }

        collectionGUI.displayUpdateCardCount(displayableCardsList.toArray(new String[0]), actionListener);
    }

    public void displayCard() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(collectionGUI);
                case "Display" -> {
                    Card c = collector.getCollection().getCard(collectionGUI.getSelectedCardIndex());
                    cardGUI = new CardGUI();
                    cardGUI.displayCard(c.getName(), c.getCardNo(), c.getRarity().getName(), c.getVariant().getName(),
                            c.getCollectionCount(), c.getBaseValue(), c.getFinalValue());
                }
            }
        };

        collectorGUI.dispose();
        collectionGUI = new CollectionGUI();
        ArrayList<Card> displayableCards = new ArrayList<Card>(collector.getCollection().getCards());
        ArrayList<String> displayableCardsList = new ArrayList<String>();

        for (Card c : displayableCards) {
            displayableCardsList.add(c.getCardNo() + " - " + c.getName());
        }

        collectionGUI.displayCard(displayableCardsList.toArray(new String[0]), actionListener);
    }

    public void displayCollection() {
        actionListener = e -> {
            if (e.getActionCommand().equals("Back")) {
                goBackToMenu(collectionGUI);
            }
        };

        collectorGUI.dispose();
        collectionGUI = new CollectionGUI();
        ArrayList<Card> displayableCards = new ArrayList<Card>(collector.getCollection().getCards());
        ArrayList<String> displayableCardsList = new ArrayList<String>();

        displayableCards.removeIf(c -> c.getCollectionCount() == 0);
        displayableCards.sort(new NameSorter());

        for (Card c : displayableCards) {
            displayableCardsList.add(c.getName() + " (" + c.getCollectionCount() + " copies)");
        }

        collectionGUI.displayCollection(displayableCardsList.toArray(new String[0]), actionListener);
    }

    public void sellCard() {

    }

    private void goBackToMenu(Frame frame) {
        frame.dispose();
        collectorGUI = new CollectorGUI();
        switch (previousMenu) {
            case "main"         -> mainMenu();
            case "collection"   -> manageCollection();
            case "binder"       -> manageBinders();
            case "deck"         -> manageDecks();
        }
    }
}