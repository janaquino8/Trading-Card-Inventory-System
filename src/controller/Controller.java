package src.controller;

import java.awt.event.ActionEvent;
import java.util.*;

import src.model.*;
import src.model.card.Card;
import src.model.holders.NameSorter;
import src.model.holders.binder.*;
import src.model.holders.deck.*;
import src.view.*;
import src.view.Frame;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

/**
 * Handles all actions performed by the user.
 *
 * <p>This controller includes GUI implementation via Java Swing.
 */
public class Controller {
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
     * Stores the name of the previously added/incremented card via addCard.
     */
    int recentCardIndex;

    /**
     * Constructor to construct a Collector object.
     */
    public Controller() {
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
                case "Add a Card"        -> addCard(false, null);
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
                case "Add a Card"           -> addCard(false, null);
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
                case "Add a Card to a Binder"       -> addCardToBinder();
                case "Remove a Card From a Binder"  -> removeCardFromBinder();
                case "View a Binder"                -> viewBinder();
                case "Trade"                        -> trade();
                case "Sell a Binder"                -> sellBinder();
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
                case "Add a Card to a Deck"       -> addCardToDeck();
                case "Remove a Card From a Deck"  -> removeCardFromDeck();
                case "View a Deck"                -> viewDeck();
                case "Sell a Deck"                -> sellDeck();
                case "Return to Main Menu"        -> mainMenu();
            }
        };

        collectorGUI.displayManageDecks(collector.getDecksCount(), collector.getCollectionTotalCount(),
                collector.isDecksFull(), collector.isDecksEmpty(), collector.getSellableDecks().size(),
                actionListener);
    }

    /**
     * Creates a new card and adds it to the collection, or increments card count if card already exists prior.
     * @param isAutoAdd false if the user will still be asked for confirmation, true otherwise
     */
    public void addCard(boolean isAutoAdd, Consumer<Integer> callback) {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> {
                    if (previousMenu.equals("binder")) {
                        if (recentCardIndex != -1) {
                            collectionGUI.dispose();
                            callback.accept(recentCardIndex);
                        } else {
                            binderGUI.dispose();
                            goBackToMenu(collectionGUI);
                        }
                    } else {
                        goBackToMenu(collectionGUI);
                    }
                }
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
                            recentCardIndex = index;
                            if (isAutoAdd) {
                                actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Back"));
                            }
                        }
                    }
                    else {
                        String msg = "Card " + collectionGUI.getCardName() + " does not exist. Would you like to add to collection?";
                        if (JOptionPane.showConfirmDialog(null, msg, "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
                            collector.getCollection().addCard(collectionGUI.getCardName(), collectionGUI.getRarity(),
                                    collectionGUI.getVariant(), collectionGUI.getValue());

                            msg = collectionGUI.getCardName() + " has been added to the collection.";
                            JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
                            recentCardIndex = collector.getCollectionCardCount() - 1;
                            if (isAutoAdd) {
                                actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Back"));
                            }
                        }
                    }
                }
            }
        };

        recentCardIndex = -1;
        if (previousMenu.equals("main") || previousMenu.equals("collection")) {
            collectorGUI.dispose();
        }
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
                            collector.createBinder(binderGUI.getBinderName(), binderGUI.getBinderType());
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
                            collector.createDeck(deckGUI.getDeckName(), deckGUI.getDeckType());
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
        // Store a reference to the current collectionGUI
        CollectionGUI currentCollectionGUI = new CollectionGUI();
        ArrayList<String> displayableCardsList = new ArrayList<String>();
        ArrayList<Card> filteredCards = new ArrayList<Card>(); // Store filtered cards

        for (Card c : collector.getCollection().getCards()) {
            if (c.getCollectionCount() > 0) {
                displayableCardsList.add(c.getCardNo() + " - " + c.getName());
                filteredCards.add(c); // Add to filtered list
            }
        }

        // Create the action listener for card selection
        ActionListener selectionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(currentCollectionGUI);
                case "Display" -> {
                    int selectedIndex = currentCollectionGUI.getSelectedCardIndex();
                    if (selectedIndex >= 0 && selectedIndex < filteredCards.size()) {
                        // Close the current collection GUI window
                        currentCollectionGUI.dispose();

                        // Get card from filtered list instead of original list
                        Card c = filteredCards.get(selectedIndex);

                        // Create and show card details window
                        CardGUI cardFrame = new CardGUI();
                        cardFrame.displayCard(c.getName(), c.getCardNo(), c.getRarity().getName(),
                                c.getVariant().getName(), c.getCollectionCount(), c.getBaseValue(), c.getFinalValue());

                        // Set back action to return to card selection
                        cardFrame.setBackAction(ev -> {
                            cardFrame.dispose();
                            displayCard(); // Reopen the card selection
                        });
                    }
                }
            }
        };

        // Close any existing collector GUI
        collectorGUI.dispose();

        // Display the card selection
        currentCollectionGUI.displayCard(displayableCardsList.toArray(new String[0]), selectionListener);
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
            int count = c.getCollectionCount();
            displayableCardsList.add(c.getName() + " (" + count + (count == 1 ? " copy)" : " copies)"));
        }

        collectionGUI.displayCollection(displayableCardsList.toArray(new String[0]), actionListener);
    }

    /**
     * Sells a copy of a card from the collection, with the money going to the user.
     */
    public void sellCard() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(collectionGUI);
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
                    collectionGUI.setCardCountLabelForSell(collector.getCollection().getCard(index).getCollectionCount());
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

    public void addCardToBinder() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(binderGUI);
                case "Add" -> {
                    int binderIndex = binderGUI.getSelectedBinderIndex();
                    String selectedCardName = binderGUI.getSelectedCardName();

                    // Find the card in the collection by name
                    int actualCardIndex = collector.getCollection().findCard(selectedCardName);

                    Binder binder = collector.getBinder(binderIndex);
                    Card card = collector.getCollection().getCard(actualCardIndex);


                    // Get user confirmation
                    String msg = "Add " + card.getName() + " to " + binder.getName() + "?";
                    int confirm = JOptionPane.showConfirmDialog(null, msg, "Confirm", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        // Try to add the card - this will check binder-specific rules
                        if (binder.addCard(card)) {
                            card.decrementCollectionCount();
                            binderGUI.resetDisplayAddCardToBinder(binder.isFull(), card.getCollectionCount());
                            JOptionPane.showMessageDialog(null, "Card added to binder!", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            // This handles cases where card doesn't meet binder requirements
                            String errorMessage = switch (binder.getID()) {
                                case 2 -> "Collector Binder only accepts non-normal variants!";
                                case 3 -> "Pauper Binder only accepts Common/Uncommon cards!";
                                case 4 -> "Rares Binder only accepts Rare/Legendary cards!";
                                default -> "Card cannot be added to this binder!";
                            };
                            JOptionPane.showMessageDialog(null, errorMessage, "Failed", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        };

        // Prepare and show GUI (same as before)
        String[] binderNames = collector.getBinders().stream()
                .filter(b -> !b.isFull())
                .map(Binder::getName)
                .toArray(String[]::new);

        String[] cardNames = collector.getCollection().getCards().stream()
                .filter(c -> c.getCollectionCount() > 0)
                .map(Card::getName)
                .toArray(String[]::new);

        collectorGUI.dispose();
        binderGUI = new BinderGUI();
        binderGUI.displayAddCardToBinder(binderNames, cardNames, actionListener);
    }

    public void removeCardFromBinder() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(binderGUI);
                case "BinderSelectionChanged" -> {
                    int binderIndex = binderGUI.getSelectedBinderIndex();

                    if (binderIndex >= 0) {
                        Binder binder = collector.getBinder(binderIndex);
                        String[] cardNames = Arrays.stream(binder.getCards())
                                .filter(Objects::nonNull)
                                .map(Card::getName)
                                .toArray(String[]::new);

                        // Update the view with the new card names
                        binderGUI.updateCardList(cardNames);
                    }
                }
                case "Remove" -> {
                    int binderIndex = binderGUI.getSelectedBinderIndex();
                    int cardIndex = binderGUI.getSelectedCardIndex();

                    Binder binder = collector.getBinder(binderIndex);

                    Card card = binder.getCard(cardIndex);

                    // Get user confirmation
                    String msg = "Remove " + card.getName() + " from " + binder.getName() + "?";
                    int confirm = JOptionPane.showConfirmDialog(null, msg, "Confirm", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        // Return card to collection
                        collector.getCollection().getCard(collector.getCollection().findCard(card.getName()))
                                .incrementCollectionCount();
                        // Remove from binder
                        binder.removeCard(cardIndex);
                        binderGUI.resetDisplayRemoveCardFromBinder(binder.isEmpty());

                        JOptionPane.showMessageDialog(null, "Card removed from binder!", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        };

        // Prepare data for GUI
        String[] binderNames = collector.getBinders().stream()
                .filter(b -> !b.isEmpty())
                .map(Binder::getName)
                .toArray(String[]::new);

        collectorGUI.dispose();
        binderGUI = new BinderGUI();
        binderGUI.displayRemoveCardFromBinder(binderNames, actionListener);
    }

    public void viewBinder() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(binderGUI);
                case "View" -> {
                    int index = binderGUI.getSelectedBinderIndex();
                    if (index >= 0 && index < collector.getBindersCount()) {
                        // Dispose the current binder selection GUI
                        binderGUI.dispose();

                        Binder binder = collector.getBinder(index);
                        binder.sort();
                        String[] cardNames = Arrays.stream(binder.getCards())
                                .filter(Objects::nonNull)
                                .map(Card::getName)
                                .toArray(String[]::new);

                        String typeName = switch(binder.getID()) {
                            case 1 -> "Non-curated";
                            case 2 -> "Collector";
                            case 3 -> "Pauper";
                            case 4 -> "Rares";
                            case 5 -> "Luxury";
                            default -> "Unknown";
                        };

                        // Create new BinderGUI for the view
                        BinderGUI viewBinderGUI = new BinderGUI();
                        viewBinderGUI.displayViewBinder(binder.getName(), typeName, cardNames,
                                e2 -> {
                                    viewBinderGUI.dispose();
                                    viewBinder(); // Reopen the selection if needed
                                }
                        );
                    }
                }
            }
        };

        collectorGUI.dispose();
        binderGUI = new BinderGUI();

        // Get list of binder names for selection
        String[] binderNames = collector.getBinders().stream()
                .map(Binder::getName)
                .toArray(String[]::new);

        binderGUI.displaySelectBinder(binderNames, actionListener);
    }

    public void trade() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> {
                    collectionGUI.dispose();
                    goBackToMenu(binderGUI);
                }
                case "BinderSelectionChanged" -> {
                    String binderName = binderGUI.getSelectedBinderName();
                    int binderIndex = collector.findBinder(binderName);

                    if (binderIndex >= 0) {
                        Binder binder = collector.getBinder(binderIndex);
                        String[] cardNames = Arrays.stream(binder.getCards())
                                .filter(Objects::nonNull)
                                .map(Card::getName)
                                .toArray(String[]::new);

                        // Update the view with the new card names
                        binderGUI.updateCardList(cardNames);
                    }
                }
                case "Trade" -> {
                    // open the addCard window
                    binderGUI.setVisible(false);
                    addCard(true, recentCardIndex -> {
                        binderGUI.setVisible(true);
                        if (recentCardIndex == -1) {
                            return; // user cancelled or window closed
                        }

                        Card incomingCard = collector.getCollection().getCard(recentCardIndex);

                        String binderName = binderGUI.getSelectedBinderName();
                        TradableBinder binder = new TradableBinder("");

                        int i = 0;
                        boolean isBinderFound = false;
                        do {
                            if (binderName.equals(collector.getTradableBinders().get(i).getName())) {
                                binder = collector.getTradableBinders().get(i);
                                isBinderFound = true;
                            }
                            else {
                                i++;
                            }
                        } while (!isBinderFound && i < collector.getTradableBinders().size());

                        int outgoingCardIndex = binderGUI.getSelectedCardIndex();
                        Card outgoingCard = binder.getCard(outgoingCardIndex);

                        if (binder.getID() == 2 && incomingCard.getVariant().getName().equals("Normal")) {
                            JOptionPane.showMessageDialog(null,
                                    "Cards of normal variant may not be added to collector binders.",
                                    "Failed", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            double difference = Math.abs(outgoingCard.getFinalValue() - incomingCard.getFinalValue());

                            String tradeInfo = "TRADE INFO:\n"
                                    + "Binder: " + binderName + "\n"
                                    + "Outgoing: " + outgoingCard.getName() + " - $" + outgoingCard.getFinalValue() + "\n"
                                    + "Incoming: " + incomingCard.getName() + " - $" + incomingCard.getFinalValue() + "\n"
                                    + "Difference: $" + difference;

                            if (difference >= 1) {
                                if (JOptionPane.showConfirmDialog(null,
                                        tradeInfo + "\nDifference is >= $1. Continue?",
                                        "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
                                    binder.trade(outgoingCardIndex, incomingCard);
                                    JOptionPane.showMessageDialog(null, tradeInfo + "\nTrade successful.");
                                }
                            } else {
                                binder.trade(outgoingCardIndex, incomingCard);
                                JOptionPane.showMessageDialog(null, tradeInfo + "\nTrade successful.");
                            }
                        }

                        collector.getCollection().getCard(recentCardIndex).decrementCollectionCount();
                        binderGUI.resetDisplayTrade();
                    });
                }
            }
        };

        collectorGUI.dispose();
        binderGUI = new BinderGUI();
        ArrayList<String> displayableBindersList = new ArrayList<String>();

        for (Binder b : collector.getTradableBinders()) {
            if (!b.isEmpty()) {
                displayableBindersList.add(b.getName());
            }
        }

        binderGUI.displayTrade(displayableBindersList.toArray(new String[0]), actionListener);
    }

    public void sellBinder() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(binderGUI);
                case "Select" -> {
                    int index = collector.findBinder(binderGUI.getSelectedBinderName());
                    if (index >= 0) {
                        Binder binder = collector.getBinder(index);
                        SellableBinder tempSB = new SellableBinder("") {};

                        switch (binder.getID()) {
                            case 3 -> tempSB = new PauperBinder("");
                            case 4 -> tempSB = new RaresBinder("");
                            case 5 -> tempSB = new LuxuryBinder("");
                        }

                        tempSB.setCards(binder.getCards());
                        binderGUI.setBinderNameLabel(binder.getName());
                        binderGUI.setBinderValueLabel(tempSB.getValue());
                    }
                }
                case "Sell" -> {
                    int index = collector.findBinder(binderGUI.getSelectedBinderName());
                    Binder binder = collector.getBinder(index);

                    String msg = "Are you sure you want to sell binder " + binder.getName() + "?\n";
                    msg += "NOTE: Rares and Luxury binders are sold with a 10% handling fee.\n";
                    msg += "NOTE: Luxury binders may be sold for a price higher than the value.";
                    if (JOptionPane.showConfirmDialog(null, msg, "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
                        msg = binder.getName() + " has been sold.";

                        switch (binder.getID()) {
                            case 3 -> {
                                PauperBinder pb = new PauperBinder("");
                                pb.setCards(collector.getBinder(index).getCards());
                                collector.earnMoney(pb.getValue());
                            }
                            case 4 -> {
                                RaresBinder rb = new RaresBinder("");
                                rb.setCards(collector.getBinder(index).getCards());
                                collector.earnMoney(rb.getValueWithHandlingFee());
                            }
                            case 5 -> {
                                LuxuryBinder lb = new LuxuryBinder("");
                                lb.setCards(collector.getBinder(index).getCards());

                                String msg2 = "Would you like to increase the value of binder " + lb.getName() + "?";
                                if (JOptionPane.showConfirmDialog(null, msg2, "Confirmation", JOptionPane.YES_NO_OPTION) == 0
                                    && lb.getValue() > 0){
                                    double value;
                                    do {
                                        try {
                                            msg2 = "Enter new value (should be GREATER THAN OR EQUAL $" + lb.getValue() + ")";
                                            value = Double.parseDouble(JOptionPane.showInputDialog(msg2));
                                        }
                                        catch (NumberFormatException exc) {
                                            value = -1;
                                        }
                                    } while (!lb.setNewValue(value));
                                }

                                collector.earnMoney(lb.getValueWithHandlingFee());
                            }
                        }

                        collector.getBinders().remove(index);
                        binderGUI.resetDisplaySellBinder(collector.getMoney());
                        JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        };

        collectorGUI.dispose();
        binderGUI = new BinderGUI();
        ArrayList<String> displayableBindersList = new ArrayList<String>();

        for (Binder b : collector.getSellableBinders()) {
            displayableBindersList.add(b.getName());
        }

        binderGUI.displaySellBinder(displayableBindersList.toArray(new String[0]), collector.getMoney(), actionListener);
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

    public void addCardToDeck() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(deckGUI);
                case "Add" -> {
                    int deckIndex = deckGUI.getSelectedDeckIndex();
                    String selectedCardName = deckGUI.getSelectedCardName();

                    // Find the card in the collection by name
                    int cardIndex = collector.getCollection().findCard(selectedCardName);

                    Deck deck = collector.getDeck(deckIndex);
                    Card card = collector.getCollection().getCard(cardIndex);

                    // Check if card already exists in deck
                    if (deck.findCard(card.getName()) != -1) {
                        JOptionPane.showMessageDialog(null, "This card already exists in the deck!", "Failed",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Get user confirmation
                    String msg = "Add " + card.getName() + " to " + deck.getName() + "?";
                    int confirm = JOptionPane.showConfirmDialog(null, msg, "Confirm", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        if (deck.addCard(card)) {
                            card.decrementCollectionCount();
                            deckGUI.resetDisplayAddCardToDeck(deck.isFull(), card.getCollectionCount());
                            JOptionPane.showMessageDialog(null, "Card added to deck!", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Failed to add card to deck!", "Failed", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        };

        // Prepare data for GUI
        String[] deckNames = collector.getDecks().stream()
                .filter(d -> !d.isFull())
                .map(Deck::getName)
                .toArray(String[]::new);

        String[] cardNames = collector.getCollection().getCards().stream()
                .filter(c -> c.getCollectionCount() > 0)
                .map(Card::getName)
                .toArray(String[]::new);

        collectorGUI.dispose();
        deckGUI = new DeckGUI();
        deckGUI.displayAddCardToDeck(deckNames, cardNames, actionListener);
    }

    public void removeCardFromDeck() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(deckGUI);
                case "DeckSelectionChanged" -> {
                    int deckIndex = deckGUI.getSelectedDeckIndex();
                    if (deckIndex >= 0) {
                        Deck deck = collector.getDeck(deckIndex);
                        String[] cardNames = Arrays.stream(deck.getCards())
                                .filter(Objects::nonNull)
                                .map(Card::getName)
                                .toArray(String[]::new);

                        // Update the view with the new card names
                        deckGUI.updateCardList(cardNames);
                    }
                }
                case "Remove" -> {
                    int deckIndex = deckGUI.getSelectedDeckIndex();
                    int cardIndex = deckGUI.getSelectedCardIndex();

                    Deck deck = collector.getDeck(deckIndex);
                    Card card = deck.getCard(cardIndex);

                    // Get user confirmation
                    String msg = "Remove " + card.getName() + " from " + deck.getName() + "?";
                    int confirm = JOptionPane.showConfirmDialog(null, msg, "Confirm", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        // Return card to collection
                        collector.getCollection().getCard(collector.getCollection().findCard(card.getName()))
                                .incrementCollectionCount();
                        // Remove from deck
                        deck.removeCard(cardIndex);
                        deckGUI.resetDisplayRemoveCardFromDeck(deck.isEmpty());

                        JOptionPane.showMessageDialog(null, "Card removed from deck!", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        };

        // Prepare data for GUI
        String[] deckNames = collector.getDecks().stream()
                .filter(d -> !d.isEmpty())
                .map(Deck::getName)
                .toArray(String[]::new);

        collectorGUI.dispose();
        deckGUI = new DeckGUI();
        deckGUI.displayRemoveCardFromDeck(deckNames, actionListener);
    }

    public void viewDeck() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(deckGUI);
                case "View" -> {
                    int index = deckGUI.getSelectedDeckIndex();
                    if (index >= 0 && index < collector.getDecksCount()) {
                        // Dispose the current deck selection GUI
                        deckGUI.dispose();

                        Deck deck = collector.getDeck(index);
                        String[] cardNames = Arrays.stream(deck.getCards())
                                .filter(Objects::nonNull)
                                .map(Card::getName)
                                .toArray(String[]::new);

                        String typeName = switch(deck.getID()) {
                            case 1 -> "Normal";
                            case 2 -> "Sellable";
                            default -> "Unknown";
                        };

                        // Create new DeckGUI for the view
                        DeckGUI viewDeckGUI = new DeckGUI();
                        viewDeckGUI.displayViewDeck(deck.getName(), typeName, cardNames,
                                e2 -> {
                                    viewDeckGUI.dispose();
                                    viewDeck(); // Reopen the selection if needed
                                },
                                e3 -> {
                                    viewDeckGUI.dispose();
                                    int cardIndex = viewDeckGUI.getSelectedCardIndex();
                                    if (cardIndex >= 0) {
                                        Card selectedCard = deck.getCard(cardIndex);
                                        // Close the selection window
                                        viewDeckGUI.dispose();

                                        // Show card details
                                        cardGUI = new CardGUI();
                                        cardGUI.displayCard(selectedCard.getName(), selectedCard.getCardNo(),
                                                selectedCard.getRarity().getName(), selectedCard.getVariant().getName(),
                                                selectedCard.getCollectionCount(), selectedCard.getBaseValue(),
                                                selectedCard.getFinalValue()
                                        );

                                        // Set back action to return to deck view
                                        cardGUI.setBackAction(ev -> {
                                            cardGUI.dispose();
                                            actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "View"));
                                        });
                                    }
                                }
                        );
                    }
                }
                case "ViewCard" -> {
                    int deckIndex = deckGUI.getSelectedDeckIndex();
                    Deck deck = collector.getDeck(deckIndex);
                    int cardIndex = deckGUI.getSelectedCardIndex();
                    if (cardIndex >= 0) {
                        Card selectedCard = deck.getCard(cardIndex);
                        // Close the selection window
                        deckGUI.dispose();

                        // Show card details
                        cardGUI = new CardGUI();
                        cardGUI.displayCard(selectedCard.getName(), selectedCard.getCardNo(),
                                selectedCard.getRarity().getName(), selectedCard.getVariant().getName(),
                                selectedCard.getCollectionCount(), selectedCard.getBaseValue(),
                                selectedCard.getFinalValue()
                        );

                        // Set back action to return to deck view
                        cardGUI.setBackAction(ev -> {
                            cardGUI.dispose();
                            actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "View"));
                        });
                    }
                }
            }
        };

        collectorGUI.dispose();
        deckGUI = new DeckGUI();

        // Get list of deck names for selection
        String[] deckNames = collector.getDecks().stream()
                .map(Deck::getName)
                .toArray(String[]::new);

        deckGUI.displaySelectDeck(deckNames, actionListener);
    }

    public void sellDeck() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(deckGUI);
                case "Select" -> {
                    int index = collector.findDeck(deckGUI.getSelectedDeckName());
                    if (index >= 0) {
                        SellableDeck tempSD = new SellableDeck("");
                        Deck deck = collector.getDeck(index);

                        tempSD.setCards(deck.getCards());
                        deckGUI.setDeckNameLabel(deck.getName());
                        deckGUI.setDeckValueLabel(tempSD.getValue());
                    }
                }
                case "Sell" -> {
                    SellableDeck tempSD = new SellableDeck("");
                    int index = collector.findDeck(deckGUI.getSelectedDeckName());
                    Deck deck = collector.getDeck(index);
                    tempSD.setCards(deck.getCards());

                    String msg = "Are you sure you want to sell binder " + deck.getName() + "?";
                    if (JOptionPane.showConfirmDialog(null, msg, "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
                        msg = deck.getName() + " has been sold.";
                        collector.earnMoney(tempSD.getValue());
                        collector.getDecks().remove(index);
                        deckGUI.resetDisplaySellDeck(collector.getMoney());
                        JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        };

        collectorGUI.dispose();
        deckGUI = new DeckGUI();
        ArrayList<String> displayableDecksList = new ArrayList<String>();

        for (Deck d : collector.getSellableDecks()) {
            displayableDecksList.add(d.getName());
        }

        deckGUI.displaySellDeck(displayableDecksList.toArray(new String[0]), collector.getMoney(), actionListener);
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