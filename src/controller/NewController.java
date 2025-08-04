package src.controller;

import java.awt.*;
import java.util.*;

import src.model.*;
import src.model.card.Card;
import src.model.holders.NameSorter;
import src.model.holders.binder.*;
import src.model.holders.deck.*;
import src.view.*;
import src.view.Frame;

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
                case "Add a Card to a Binder"       -> addCardToBinder();
                case "Remove a Card From a Binder"  -> removeCardFromBinder();
                case "View a Binder"                -> viewBinder();
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
                case "Add a Card to a Deck"       -> addCardToDeck();
                case "Remove a Card From a Deck"  -> removeCardFromDeck();
                case "View a Deck"                -> viewDeck();
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

                            // extra
                            if (collectionGUI.getRarity() == 1) {
                                collector.getCollection().getCard(collector.getCollectionCardCount() - 1).getRarity().setName("Common");
                            }

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
                        cardFrame.displayCard(
                                c.getName(),
                                c.getCardNo(),
                                c.getRarity().getName(),
                                c.getVariant().getName(),
                                c.getCollectionCount(),
                                c.getBaseValue(),
                                c.getFinalValue()
                        );

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

    public void addCardToBinder() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(binderGUI);
                case "Add" -> {
                    int binderIndex = binderGUI.getSelectedBinderIndex();
                    String selectedCardName = binderGUI.getSelectedCardName();

//                    // Find the card in the collection by name
//                    int cardIndex = collector.getCollection().findCard(selectedCardName);

                    // Find the card in the collection by name
                    int actualCardIndex = collector.getCollection().findCard(selectedCardName);

                    Binder binder = collector.getBinder(binderIndex);
                    Card card = collector.getCollection().getCard(actualCardIndex);

//                    // Check standard conditions
//                    if (binder.isFull()) {
//                        JOptionPane.showMessageDialog(null,
//                                "Binder is full!",
//                                "Error",
//                                JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }
//
//                    if (card.getCollectionCount() == 0) {
//                        JOptionPane.showMessageDialog(null,
//                                "No copies of this card in collection!",
//                                "Error",
//                                JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }

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

                    // Check if card exists at the selected index
//                    if (cardIndex >= binder.getCardCount() || binder.getCard(cardIndex) == null) {
//                        JOptionPane.showMessageDialog(null,
//                                "The selected card no longer exists in this binder!",
//                                "Card Not Found",
//                                JOptionPane.WARNING_MESSAGE);
//                        // Refresh the view
//                        removeCardFromBinder();
//                        return;
//                    }

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

                        // Refresh the view
//                        removeCardFromBinder();
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

    private void showCardInDeck(DeckGUI deckGUI, Deck deck) {
        // Create a new window for card selection
        DeckGUI cardSelectGUI = new DeckGUI();
        cardSelectGUI.displaySelectCardInDeck(
                Arrays.stream(deck.getCards())
                        .filter(Objects::nonNull)
                        .map(Card::getName)
                        .toArray(String[]::new),
                e -> {
                    int selectedIndex = cardSelectGUI.getSelectedCardIndex();
                    if (selectedIndex >= 0) {
                        Card selectedCard = deck.getCard(selectedIndex);
                        // Close the selection window
                        cardSelectGUI.dispose();

                        // Show card details
                        CardGUI cardDetailsGUI = new CardGUI();
                        cardDetailsGUI.displayCard(
                                selectedCard.getName(),
                                selectedCard.getCardNo(),
                                selectedCard.getRarity().getName(),
                                selectedCard.getVariant().getName(),
                                selectedCard.getCollectionCount(),
                                selectedCard.getBaseValue(),
                                selectedCard.getFinalValue()
                        );

                        // Set back action to return to deck view
                        cardDetailsGUI.setBackAction(ev -> {
                            cardDetailsGUI.dispose();
                            showCardInDeck(deckGUI, deck); // Reopen card selection
                        });
                    }
                },
                e -> {
                    // Back button action - just close this window
                    cardSelectGUI.dispose();
                }
        );
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
                                e3 -> showCardInDeck(viewDeckGUI, deck) // New handler for View Card
                        );
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

    public void addCardToDeck() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> goBackToMenu(deckGUI);
                case "Add" -> {
                    int deckIndex = deckGUI.getSelectedDeckIndex();
                    String selectedCardName = deckGUI.getSelectedCardName();

//                    if (deckIndex == -1 || selectedCardName == null) {
//                        JOptionPane.showMessageDialog(null,
//                                "Please select both a deck and a card",
//                                "Selection Required",
//                                JOptionPane.WARNING_MESSAGE);
//                        return;
//                    }

                    // Find the card in the collection by name
                    int cardIndex = collector.getCollection().findCard(selectedCardName);

//                    if (cardIndex == -1) {
//                        JOptionPane.showMessageDialog(null,
//                                "Card not found in collection!",
//                                "Error",
//                                JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }

                    Deck deck = collector.getDeck(deckIndex);
                    Card card = collector.getCollection().getCard(cardIndex);

                    // Check standard conditions
//                    if (deck.isFull()) {
//                        JOptionPane.showMessageDialog(null,
//                                "Deck is full!",
//                                "Error",
//                                JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }

//                    if (card.getCollectionCount() == 0) {
//                        JOptionPane.showMessageDialog(null,
//                                "No copies of this card in collection!",
//                                "Error",
//                                JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }

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