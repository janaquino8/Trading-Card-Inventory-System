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

/**
 * Handles all actions performed by the user.
 *
 * <p>This controller includes GUI implementation via Java Swing.
 */
public class NewController {
    /**
     * Represents the user.
     */
    Collector collector;

    // GUI Classes
    CollectorGUI collectorGUI;
    CollectionGUI collectionGUI;
    BinderGUI binderGUI;
    DeckGUI deckGUI;
    CardGUI cardGUI;
    ActionListener actionListener;

    /**
     * Stores the name of the previously visited menu screen.
     */
    String previousMenu;

    /**
     * Constructor to construct a Collector object.
     */
    public NewController() {
        collector = new Collector();
        collectorGUI = new CollectorGUI();
        mainMenu();
    }

    /**
     * The main menu; options change depending on card count of collection, binder count, and deck count.
     */
    public void mainMenu() {
        previousMenu = "main";
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Add a Card"        -> addCard(false);
                case "Create a Binder"   -> createBinder();
                case "Create a Deck"     -> createDeck();
                case "Manage Collection" -> collectionMenu();
                case "Manage Binders"    -> binderMenu();
                case "Manage Decks"      -> deckMenu();
            }
        };

        collectorGUI.displayMainMenu(collector.getCollectionCardCount(), collector.getBindersCount(),
                               collector.getDecksCount(), actionListener);
    }

    /**
     * Runs the menu for managing collection
     * add card, update card count, display card, display collection, and sell card.
     */
    public void collectionMenu() {
        previousMenu = "collection";
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Add a Card"           -> addCard(false);
                case "Update Card Count"    -> updateCardCount();
                case "Display a Card"       -> displayCard();
                case "Display Collection"   -> displayCollection();
                case "Sell a Card"          -> sellCard();
                case "Return to Main Menu"  -> mainMenu();
            }
        };

        collectorGUI.displayManageCollection(collector.getCollectionTotalCount(),
                actionListener);
    }

    /**
     * Runs the menu for managing binders
     * create binder, delete binder, add card to binder, remove card from binder, view binder, trade, and sell binder.
     */
    public void binderMenu() {
        previousMenu = "binder";
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Create a Binder"              -> createBinder();
                case "Delete a Binder"              -> deleteBinder();
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

    /**
     * Runs the menu for managing decks
     * create deck, delete deck, add card to deck, remove card from deck, view deck, and sell deck.
     */
    public void deckMenu() {
        previousMenu = "deck";
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Create a Deck"              -> createDeck();
                case "Delete a Deck"              -> deleteDeck();
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

    /**
     * Creates a new card and adds it to the collection, or increments card count if card already exists prior.
     * @param isAutoAdd false if the user will still be asked for confirmation, true otherwise
     * @return index of new card/card copy
     */
    public void addCard(boolean isAutoAdd) {
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
                        msg += "Value: $" + c.getFinalValue() + "\n";
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

    public void createBinder() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(binderGUI);
                case "Add" -> {
                    int index = collector.findBinder(binderGUI.getBinderName());

                    if (index == -1) {
                        String msg = binderGUI.getBinderName() + " does not exist. Would you like to create the binder?";
                        if (JOptionPane.showConfirmDialog(null, msg, "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
                            switch (binderGUI.getBinderType()) {
                                case 1 -> collector.createBinder(binderGUI.getBinderName(), 1);
                                case 2 -> collector.createBinder(binderGUI.getBinderName(), 2);
                            }

                            msg = binderGUI.getBinderName() + " has been created.";
                            JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else {
                        String type = switch (collector.getBinder(index).getID()) {
                            case 1 -> "Non-curated";
                            case 2 -> "Collector";
                            case 3 -> "Pauper";
                            case 4 -> "Rares";
                            case 5 -> "Luxury";
                            default -> "unknown";
                        };
                        String msg = "Binder " + binderGUI.getBinderName() + " of type " + type + " already exists.";
                        JOptionPane.showMessageDialog(null, msg, "Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };

        collectorGUI.dispose();
        binderGUI = new BinderGUI();

        binderGUI.displayCreateBinder(actionListener);
    }

    public void createDeck() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(deckGUI);
                case "Add" -> {
                    int index = collector.findDeck(deckGUI.getDeckName());

                    if (index == -1) {
                        String msg = deckGUI.getDeckName() + " does not exist. Would you like to create the deck?";
                        if (JOptionPane.showConfirmDialog(null, msg, "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
                            switch (deckGUI.getDeckType()) {
                                case 1 -> collector.createDeck(deckGUI.getDeckName(), 1);
                                case 2 -> collector.createDeck(deckGUI.getDeckName(), 2);
                                case 3 -> collector.createDeck(deckGUI.getDeckName(), 3);
                                case 4 -> collector.createDeck(deckGUI.getDeckName(), 4);
                                case 5 -> collector.createDeck(deckGUI.getDeckName(), 5);
                            }

                            msg = deckGUI.getDeckName() + " has been created.";
                            JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else {
                        String type = switch (collector.getDeck(index).getID()) {
                            case 1 -> "Normal";
                            case 2 -> "Sellable";
                            default -> "unknown";
                        };
                        String msg = "Deck " + deckGUI.getDeckName() + " of type " + type + " already exists.";
                        JOptionPane.showMessageDialog(null, msg, "Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };

        collectorGUI.dispose();
        deckGUI = new DeckGUI();

        deckGUI.displayCreateDeck(actionListener);
    }

    /**
     * Updates the collection count of a card in the collection (via increment or decrement).
     */
    public void updateCardCount() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(collectionGUI);
                case "Select" -> {
                    int index = collectionGUI.getSelectedCardIndex();
                    if (index >= 0) {
                        Card c = collector.getCollection().getCard(index);
                        collectionGUI.setCardNameLabel(c.getName());
                        collectionGUI.setCardCountLabelForUpdateCard(c.getCollectionCount());
                    }
                }
                case "Increment" -> {
                    int index = collectionGUI.getSelectedCardIndex();
                    collector.getCollection().getCard(index).incrementCollectionCount();
                    collectionGUI.setCardCountLabelForUpdateCard(collector.getCollection().getCard(index).getCollectionCount());
                }
                case "Decrement" -> {
                    int index = collectionGUI.getSelectedCardIndex();
                    collector.getCollection().getCard(index).decrementCollectionCount();
                    collectionGUI.setCardCountLabelForUpdateCard(collector.getCollection().getCard(index).getCollectionCount());
                }
            }
        };

        collectorGUI.dispose();
        collectionGUI = new CollectionGUI();
        ArrayList<String> displayableCardsList = new ArrayList<String>();

        for (Card c : collector.getCollection().getCards()) {
            displayableCardsList.add(c.getName());
        }

        collectionGUI.displayUpdateCardCount(displayableCardsList.toArray(new String[0]), actionListener);
    }

    /**
     * Displays the details of a card in the collection.
     */
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
        ArrayList<String> displayableCardsList = new ArrayList<String>();

        for (Card c : collector.getCollection().getCards()) {
            displayableCardsList.add(c.getCardNo() + " - " + c.getName());
        }

        collectionGUI.displayCard(displayableCardsList.toArray(new String[0]), actionListener);
    }

    /**
     * Displays the name and collection count of each card in the collection.
     */
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

    /**
     * Sells a copy of a card from the collection, with the money going to the user.
     */
    public void sellCard() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Select" -> {
                    int index = collectionGUI.getSelectedCardIndex();
                    if (index >= 0) {
                        Card c = collector.getCollection().getCard(index);
                        collectionGUI.setCardNameLabel(c.getName());
                        collectionGUI.setCardCountLabelForSell(c.getCollectionCount());
                        collectionGUI.setCardValueLabel(c.getFinalValue());
                    }
                }
                case "Sell" -> {
                    int index = collectionGUI.getSelectedCardIndex();
                    collector.earnMoney(collector.getCollection().sellCard(index));
                    collectionGUI.setCollectorMoneyLabel(collector.getMoney());
                    collectionGUI.setCardCountLabelForSell(collector.
                            getCollection().getCard(index).getCollectionCount());
                }
            }
        };

        collectorGUI.dispose();
        collectionGUI = new CollectionGUI();
        ArrayList<String> displayableCardsList = new ArrayList<String>();

        for (Card c : collector.getCollection().getCards()) {
            displayableCardsList.add(c.getName());
        }

        collectionGUI.displaySellCard(displayableCardsList.toArray(new String[0]), collector.getMoney(), actionListener);
    }

    public void deleteBinder() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(binderGUI);
                case "Delete" -> {
                    int index = binderGUI.getSelectedBinderIndex();
                    String binderName = collector.getBinder(index).getName();
                    String msg = "Are you sure you want to delete binder " + binderName + "?";
                    if (JOptionPane.showConfirmDialog(null, msg, "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
                        collector.deleteBinder(index);
                        binderGUI.removeBinderFromList(index + 1);
                        msg = binderName + " has been deleted.";
                        JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        };

        collectorGUI.dispose();
        binderGUI = new BinderGUI();
        ArrayList<String> bindersList = new ArrayList<String>();

        for (Binder b : collector.getBinders()) {
            bindersList.add(b.getName());
        }

        binderGUI.displayDeleteBinder(bindersList.toArray(new String[0]), actionListener);
    }

    public void deleteDeck() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(deckGUI);
                case "Delete" -> {
                    int index = deckGUI.getSelectedDeckIndex();
                    String deckName = collector.getDeck(index).getName();
                    String msg = "Are you sure you want to delete deck " + deckName + "?";
                    if (JOptionPane.showConfirmDialog(null, msg, "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
                        collector.deleteDeck(index);
                        deckGUI.removeDeckFromList(index + 1);
                        msg = deckName + " has been deleted.";
                        JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        };

        collectorGUI.dispose();
        deckGUI = new DeckGUI();
        ArrayList<String> decksList = new ArrayList<String>();

        for (Deck b : collector.getDecks()) {
            decksList.add(b.getName());
        }

        deckGUI.displayDeleteDeck(decksList.toArray(new String[0]), actionListener);
    }

    /**
     * Returns to the previously visited menu screen.
     * @param frame previously visited screen
     */
    private void goBackToMenu(Frame frame) {
        frame.dispose();
        collectorGUI = new CollectorGUI();
        switch (previousMenu) {
            case "main"         -> mainMenu();
            case "collection"   -> collectionMenu();
            case "binder"       -> binderMenu();
            case "deck"         -> deckMenu();
        }
    }
}