package src.controller;

import src.model.*;
import src.view.*;

// REMOVE SOUT AND MOVE TO VIEW

/**
 * Handles all actions performed by the user
 */
public class Controller {
    Collector collector;
    CollectorView collectorView;
    CollectionView collectionView;
    BinderView binderView;
    DeckView deckView;
    CardView cardView;

    /**
     * Constructor to construct a Collector object
     */
    public Controller() {
        collector = new Collector();
        collectorView = new CollectorView();
        collectionView = new CollectionView();
        binderView = new BinderView();
        deckView = new DeckView();
        cardView = new CardView();
    }

    public void run() {
        int input;

        do {
            // retrieves current counts of collection cards, binders, and decks
            int collectionSize = collector.getCollection().getCards().size();
            int binderCount = collector.getBinders().size();
            int deckCount = collector.getDecks().size();

            // displays main menu and asks for input
            collectorView.displayMainOptions(collectionSize, binderCount, deckCount);
            input = collectorView.getIntInput("Enter option: ", 0, 3);

            switch (input) {
                case 1:
                    if (collectionSize == 0) {
                        this.addCard(false);
                    }
                    else {
                        this.collectionMenu();
                    }
                    break;
                case 2:
                    if (binderCount == 0) {
                        this.createBinder();
                    }
                    else {
                        this.binderMenu();
                    }
                    break;
                case 3:
                    if (deckCount == 0) {
                        this.createDeck();
                    }
                    else {
                        this.deckMenu();
                    }
                    break;
            }
        } while (input != 0);

        collectorView.exit();
    }

    public void collectionMenu() {
        int input;

        do {
            // displays collection menu and asks for input
            collectorView.displayCollectionOptions();
            input = collectorView.getIntInput("Enter option: ", 0, 4);

            switch (input) {
                case 1: this.addCard(false); break;
                case 2: this.updateCardCount(); break;
                case 3: this.displayCard(); break;
                case 4: this.displayCollection(); break;
            }
        } while (input != 0);
    }

    public void binderMenu() {
        int input;

        do {
            // displays binder menu and asks for input
            collectorView.displayBinderOptions();
            input = collectorView.getIntInput("Enter option: ", 0, 6);

            switch (input) {
                case 1: this.createBinder(); break;
                case 2: this.deleteBinder(); break;
                case 3: this.addCardToBinder(); break;
                case 4: this.removeCardFromBinder(); break;
                case 5: this.viewBinder(); break;
                case 6: this.trade(); break;
            }
        } while (input != 0);
    }

    public void deckMenu() {
        int input;

        do {
            // displays deck menu and asks for input
            collectorView.displayDeckOptions();
            input = collectorView.getIntInput("Enter option: ", 0, 5);

            switch (input) {
                case 1: this.createDeck(); break;
                case 2: this.deleteDeck(); break;
                case 3: this.addCardToDeck(); break;
                case 4: this.removeCardFromDeck(); break;
                case 5: this.viewDeck(); break;
            }
        } while (input != 0);
    }

    public int addCard(boolean isAutoAdd) {
        String name;
        int rarity;
        int variant;
        double value;
        int index;
        int input;

        // displays header and asks for card name
        collectorView.displayAddCard();
        name = collectorView.getStringInput("Enter card name: ");

        // checks if card is already in collection
        index = collector.getCollection().findCard(name);
        // if card is in collection
        if (index != -1) {
            collectorView.printConfirmationMsg(4);

            // asks if a copy will be added, if not immediately
            if (!isAutoAdd) {
                input = collectorView.getIntInput("Add another copy of " + name + " to collection? (1 for yes, 0 for no): ", 0, 1);
                if (input == 0) {
                    collectorView.printConfirmationMsg(0);
                    return -1;
                }
            }

            // adds a copy of the card to the collection
            collector.getCollection().getCard(index).incrementCollectionCount();
            collectorView.printConfirmationMsg(5);
            return index;
        }

        // if card is not in collection
        collectorView.printConfirmationMsg(6);

        // asks if the user will continue to create the card, if not immediately
        if (!isAutoAdd) {
            input = collectorView.getIntInput("Continue creating " + name + "? (1 for yes, 0 for no): ", 0, 1);
            if (input == 0) {
                collectorView.printConfirmationMsg(0);
                return -1;
            }
        }

        // asks for the other details of the card
        value = collectorView.getDoubleInput("Enter value: ");
        rarity = collectorView.getIntInput("Enter card rarity ([1] Common; [2] Uncommon; [3] Rare; [4] Legendary): ", 1, 4);

        // asks for card variant if rarity is either rare or legendary
        if (rarity == 3 || rarity == 4) {
            variant = collectorView.getIntInput("Enter card variant ([1] Normal; [2] Extended-art; [3] Full-art; [4] Alt-art): ", 1, 4);
            collector.getCollection().addCard(name, rarity, variant, value);
            collectorView.printCardCreatedVerification(name, collector.getCollection().getCards().size());
            collectorView.printConfirmationMsg(1);
        }
        else {
            collector.getCollection().addCard(name, rarity, value);
            collectorView.printCardCreatedVerification(name, collector.getCollection().getCards().size());
            collectorView.printConfirmationMsg(1);
        }

        return collector.getCollection().getCards().size();
    }

    public void createBinder() {
        String name;

        // header
        collectorView.displayCreateBinder();

        // asks for unique binder name
        do {
            name = collectorView.getStringInput("Enter new binder name: ");

            // if binder already exists
            if (collector.findBinder(name) != -1) {
                collectorView.printConfirmationMsg(7);
            }
        } while (collector.findBinder(name) != -1);

        // asks if the binder will be created
        if (collectorView.getIntInput("Create binder " + name + "? (1 for yes, 0 for no): ", 0, 1) == 1) {
            collector.createBinder(name);
            collectorView.printConfirmationMsg(2);
        }
        else {
            collectorView.printConfirmationMsg(0);
        }

    }

    public void createDeck() {
        String name;

        // header
        collectorView.displayCreateDeck();

        // asks for unique deck name
        do {
            name = collectorView.getStringInput("Enter new deck name: ");

            // if deck already exists
            if (collector.findDeck(name) != -1) {
                collectorView.printConfirmationMsg(8);
            }
        } while (collector.findDeck(name) != -1);

        // asks if the deck will be created
        if (collectorView.getIntInput("Create deck " + name + "? (1 for yes, 0 for no): ", 0, 1) == 1) {
            collector.createDeck(name);
            collectorView.printConfirmationMsg(3);
        }
        else {
            collectorView.printConfirmationMsg(0);
        }
    }

    public void updateCardCount() {
        int index = -1;
        int input;

        // header
        collectionView.displayUpdateCardCount();

        // asks for the card (via either card name or card no.)
        switch (input = collectorView.getIntInput("Enter option: ", 0, 2)) {
            case 1: // search by name
                index = collector.getCollection().findCard(collectorView.getStringInput("Enter card name: "));
                break;
            case 2: // search by card no.
                index = collector.getCollection().findCard(collectorView.getIntInput("Enter card no.: ", 0, collector.getCollection().getCards().size() - 1));
                break;
            case 0: // cancel action
                collectorView.printConfirmationMsg(0);
        }

        // if the card is not in the collection
        if (index == -1 && input != 0) {
            collectionView.printConfirmationMsg(3);
        }
        else if (input != 0) {
            // displays chosen card and collection
            collectionView.displayCardToUpdateCount(collector.getCollection().getCard(index).getName(),
                                                    collector.getCollection().getCard(index).getCollectionCount());

            // if no copies of the card exists in the collection
            if (collector.getCollection().getCard(index).getCollectionCount() == 0) {
                collectionView.printConfirmationMsg(4);
                input = collectorView.getIntInput("Increment card count? (1 for yes, 0 for no): ", 0, 1);
            }
            // if more than 0 copies of the card exists in the collection
            else {
                collectionView.displayUpdateCardCountOptions();
                input = collectorView.getIntInput("Enter option: ", 0, 2);
            }

            switch (input) {
                case 1: // increment
                    collector.getCollection().getCard(index).incrementCollectionCount();
                    collectionView.printConfirmationMsg(1);
                    break;
                case 2: // decrement
                    collector.getCollection().getCard(index).decrementCollectionCount();
                    collectionView.printConfirmationMsg(2);
                    break;
                case 0: // cancel action
                    collectionView.printConfirmationMsg(0);
            }
        }
    }

    public void displayCard() {
        int index = -1;
        int input;

        collectionView.displayDisplayCard();

        // asks for the card (via either card name or card no.)
        switch (input = collectorView.getIntInput("Enter option: ", 0, 2)) {
            case 1: // search by name
                index = collector.getCollection().findCard(collectorView.getStringInput("Enter card name: "));
                break;
            case 2: // search by card no.
                index = collector.getCollection().findCard(collectorView.getIntInput("Enter card no.: ", 0, collector.getCollection().getCards().size() - 1));
                break;
            case 0: // cancel action
                collectorView.printConfirmationMsg(0);
        }

        // if the card is not in the collection
        if (index == -1 && input != 0) {
            collectionView.printConfirmationMsg(3);
        }
        else if (input != 0 && collector.getCollection().getCard(index).getCollectionCount() == 0) {
            collectionView.printConfirmationMsg(4);
        }
        else if (input != 0) {
            Card c = collector.getCollection().getCard(index);
            cardView.displayCard(c.getName(), c.getCardNo(), c.getRarity().getName(), c.getVariant().getName(),
                                 c.getCollectionCount(), c.getBaseValue(), c.getFinalValue());
        }
    }

    public void displayCollection() {
        // if there are no copies of any card in the collection
        if (collector.getCollection().countTotalCards() == 0) {
            collectionView.printConfirmationMsg(5);
            return;
        }

        // sorts collection
        collector.getCollection().sort();

        // header
        collectionView.displayCollection();

        for (Card c : collector.getCollection().getCards()) {
            if (c.getCollectionCount() != 0) {
                collectionView.displayCollectionCard(c.getCollectionCount(), c.getName());
            }
        }
    }

    public void deleteBinder() {
        String name;
        int index;

        // header
        collectorView.displayDeleteBinder();

        // asks user for unique binder name
        do {
            name = collectorView.getStringInput("Enter binder name: ");
            index = collector.findBinder(name);

            // if binder doesn't exist
            if (index == -1) {
                collectorView.printConfirmationMsg(9);
            }
        } while (index == -1);

        // asks user if the binder will be removed
        if (collectorView.getIntInput("Delete binder " + name + "? (1 for yes, 0 for no): ", 0, 1) == 1) {
            collector.deleteBinder(index);
            collectorView.printConfirmationMsg(11);
        }
        else {
            collectorView.printConfirmationMsg(0);
        }
    }

    public void addCardToBinder() {
        // if there are no cards in the collection
        if (collector.getCollection().countTotalCards() == 0) {
            binderView.printConfirmationMsg(2);
            return;
        }
        // if all binders are full
        else if (collector.isBindersFull()) {
            binderView.printConfirmationMsg(3);
            return;
        }

        int binderIndex;
        String binderName;
        int cardIndex = -1;
        int input;

        // header
        binderView.displayAddCardToBinder();

        // asks the user for binder name
        do {
            binderName = collectorView.getStringInput("Enter binder name: ");
            binderIndex = collector.findBinder(binderName);

            // if binder doesn't exist
            if (binderIndex == -1) {
                binderView.printConfirmationMsg(4);
            }
            // if binder is full
            else if (collector.getBinder(binderIndex).isFull()) {
                binderView.printConfirmationMsg(5);
                binderIndex = -1;
            }
        } while (binderIndex == -1);

        binderView.displayCardSearchOptions();

        // asks user for card to be added (either by card name or card no.)
        switch (input = collectorView.getIntInput("Enter option: ", 0, 2)) {
            case 1: // search by name
                cardIndex = collector.getCollection().findCard(collectorView.getStringInput("Enter card name: "));
                break;
            case 2: // search by card no.
                cardIndex = collector.getCollection().findCard(collectorView.getIntInput("Enter card no.: ", 0, collector.getCollection().getCards().size() - 1));
                break;
            case 0: // cancel action
                binderView.printConfirmationMsg(0);
        }

        // if card doesn't exist
        if (cardIndex == -1 && input != 0) {
            binderView.printConfirmationMsg(6);
        }
        // if no copies of the card exist in the collection
        else if (collector.getCollection().getCard(cardIndex).getCollectionCount() == 0) {
            binderView.printConfirmationMsg(7);
        }
        else if (input != 0) {
            // asks user if card will be added to binder
            if (collectorView.getIntInput("Add " + collector.getCollection().getCard(cardIndex).getName() + " to " +
                                          binderName + "? (1 for yes, 0 for no): ", 0, 1) == 1) {
                collector.getBinder(binderIndex).addCard(collector.getCollection().getCard(cardIndex));
                collector.getCollection().getCard(cardIndex).decrementCollectionCount();
                binderView.printConfirmationMsg(1);
            }
            else {
                binderView.printConfirmationMsg(0);
            }
        }
    }

    public void removeCardFromBinder() {
        // if all binders are empty
        if (collector.isBindersEmpty()) {
            binderView.printConfirmationMsg(8);
            return;
        }

        int binderIndex;
        String binderName;
        int cardIndex;
        String cardName;

        // header
        binderView.displayRemoveCardFromBinder();

        do {
            binderName = collectorView.getStringInput("Enter binder name: ");
            binderIndex = collector.findBinder(binderName);

            // if binder doesn't exist
            if (binderIndex == -1) {
                binderView.printConfirmationMsg(4);
            }
            // if binder is empty
            else if (collector.getBinder(binderIndex).isEmpty()) {
                binderView.printConfirmationMsg(9);
                binderIndex = -1;
            }
        } while (binderIndex == -1);

        // asks user for card to be removed
        do {
            cardName = collectorView.getStringInput("Enter card name: ");
            cardIndex = collector.getBinder(binderIndex).findCard(cardName);

            // if card is not in binder
            if (cardIndex == -1) {
                binderView.printConfirmationMsg(10);
            }
        } while (cardIndex == -1);

        // asks user if the card will be removed from binder
        if (collectorView.getIntInput("Remove " + cardName + " from " + binderName + "? (1 for yes, 0 for no): ", 0, 1) == 1) {
            collector.getCollection().getCard(collector.getCollection().findCard(cardName)).incrementCollectionCount();
            collector.getBinder(binderIndex).removeCard(cardIndex);
            binderView.printConfirmationMsg(11);
        }
        else {
            binderView.printConfirmationMsg(0);
        }
    }

    public void viewBinder() {
        String binderName;
        int binderIndex;

        // header
        binderView.displayViewBinder();

        // asks for binder name
        do {
            binderName = collectorView.getStringInput("Enter binder name: ");
            binderIndex = collector.findBinder(binderName);

            // if binder doesn't exist
            if (binderIndex == -1) {
                binderView.printConfirmationMsg(4);
            }
        } while (binderIndex == -1);

        // if deck is empty
        if (collector.getBinder(binderIndex).isEmpty()) {
            binderView.printConfirmationMsg(9);
            return;
        }

        // sorts binder
        collector.getBinder(binderIndex).sort();

        // displays binder name
        binderView.displayBinder(binderName);

        // displays binder contents
        for (int i = 0; i < Binder.MAX_COUNT && collector.getBinder(binderIndex).getCard(i) != null; i++) {
            binderView.displayBinderCard(collector.getBinder(binderIndex).getCard(i).getName());
        }
    }

    public void trade() {
        if (collector.isBindersEmpty()) {
            binderView.printConfirmationMsg(8);
            return;
        }

        int binderIndex;
        String binderName;
        int outgoingCardIndex;
        String outgoingCardName;
        int incomingCardIndex;
        double difference;

        // header
        binderView.displayTrade();

        // asks user for binder name
        do {
            binderName = collectorView.getStringInput("Enter binder name: ");
            binderIndex = collector.findBinder(binderName);

            // if binder doesn't exist
            if (binderIndex == -1) {
                binderView.printConfirmationMsg(4);
            }
            // if binder is empty
            else if (collector.getBinder(binderIndex).isEmpty()) {
                binderView.printConfirmationMsg(9);
                binderIndex = -1;
            }
        } while (binderIndex == -1);

        // asks user for outgoing card
        do {
            outgoingCardName = collectorView.getStringInput("Enter card name: ");
            outgoingCardIndex = collector.getBinder(binderIndex).findCard(outgoingCardName);

            // if card isn't in binder
            if (outgoingCardIndex == -1) {
                binderView.printConfirmationMsg(10);
            }
        } while (outgoingCardIndex == -1);

        // creates incoming card
        incomingCardIndex = this.addCard(true);

        difference = Math.abs(collector.getCollection().getCard(incomingCardIndex).getFinalValue() -
                collector.getBinder(binderIndex).getCard(outgoingCardIndex).getFinalValue());

        // displays trade
        binderView.displayTrade(collector.getCollection().getCard(incomingCardIndex).getName(),
                                collector.getCollection().getCard(incomingCardIndex).getFinalValue(),
                                outgoingCardName,
                                collector.getBinder(binderIndex).getCard(outgoingCardIndex).getFinalValue(),
                                difference);

        // if difference in value is >= $1.00
        if (difference >= 1) {
            binderView.printConfirmationMsg(12);
            // if cancelled
            if (collectorView.getIntInput("Proceed with trade? (1 for yes, 0 for no): ", 0, 1) == 0) {
                binderView.printConfirmationMsg(0);
                return;
            }
        }
        // otherwise
        collector.getBinder(binderIndex).trade(outgoingCardIndex, collector.getCollection().getCard(incomingCardIndex));
        binderView.printConfirmationMsg(13);
        collector.getCollection().getCard(incomingCardIndex).decrementCollectionCount();
    }

    public void deleteDeck() {
        String name;
        int index;

        // header
        collectorView.displayDeleteDeck();

        // asks for unique deck name
        do {
            name = collectorView.getStringInput("Enter deck name: ");
            index = collector.findDeck(name);

            // if deck doesn't exist
            if (index == -1) {
                collectorView.printConfirmationMsg(10);
            }
        } while (index == -1);

        // asks user if deck will be removed
        if (collectorView.getIntInput("Delete deck " + name + "? (1 for yes, 0 for no): ", 0, 1) == 1) {
            collector.deleteDeck(index);
            collectorView.printConfirmationMsg(12);
        }
        else {
            collectorView.printConfirmationMsg(0);
        }
    }

    public void addCardToDeck() {
        // if there are no cards in the collection
        if (collector.getCollection().countTotalCards() == 0) {
            deckView.printConfirmationMsg(2);
            return;
        }
        // if all decks are full
        else if (collector.isDecksFull()) {
            deckView.printConfirmationMsg(3);
            return;
        }

        int deckIndex;
        String deckName;
        int cardIndex = -1;
        int input;

        // header
        deckView.displayAddCardToDeck();

        // asks the user for deck name
        do {
            deckName = collectorView.getStringInput("Enter deck name: ");
            deckIndex = collector.findDeck(deckName);

            // if deck doesn't exist
            if (deckIndex == -1) {
                deckView.printConfirmationMsg(4);
            }
            // if binder is full
            else if (collector.getDeck(deckIndex).isFull()) {
                deckView.printConfirmationMsg(5);
                deckIndex = -1;
            }
        } while (deckIndex == -1);

        deckView.displayCardSearchOptions();

        // asks user for card to be added (either by card name or card no.)
        switch (input = collectorView.getIntInput("Enter option: ", 0, 2)) {
            case 1: // search by name
                cardIndex = collector.getCollection().findCard(collectorView.getStringInput("Enter card name: "));
                break;
            case 2: // search by card no.
                cardIndex = collector.getCollection().findCard(collectorView.getIntInput("Enter card no.: ", 0, collector.getCollection().getCards().size() - 1));
                break;
            case 0: // cancel action
                deckView.printConfirmationMsg(0);
        }

        // if card doesn't exist
        if (cardIndex == -1 && input != 0) {
            deckView.printConfirmationMsg(6);
        }
        // if no copies of the card exist in the collection
        else if (collector.getCollection().getCard(cardIndex).getCollectionCount() == 0) {
            deckView.printConfirmationMsg(7);
        }
        // if card is already in the deck
        else if (collector.getDeck(deckIndex).findCard(collector.getCollection().getCard(cardIndex).getName()) != -1) {
            deckView.printConfirmationMsg(12);
        }
        else if (input != 0) {
            // asks user if card will be added to binder
            if (collectorView.getIntInput("Add " + collector.getCollection().getCard(cardIndex).getName() + " to " +
                                          deckName + "? (1 for yes, 0 for no): ", 0, 1) == 1) {
                collector.getDeck(deckIndex).addCard(collector.getCollection().getCard(cardIndex));
                collector.getCollection().getCard(cardIndex).decrementCollectionCount();
                deckView.printConfirmationMsg(1);
            }
            else {
                deckView.printConfirmationMsg(0);
            }
        }
    }

    public void removeCardFromDeck() {
        // if all decks are empty
        if (collector.isDecksEmpty()) {
            deckView.printConfirmationMsg(8);
            return;
        }

        int deckIndex;
        String deckName;
        int cardIndex;
        String cardName;

        // header
        deckView.displayRemoveCardFromDeck();

        do {
            deckName = collectorView.getStringInput("Enter deck name: ");
            deckIndex = collector.findBinder(deckName);

            // if deck doesn't exist
            if (deckIndex == -1) {
                deckView.printConfirmationMsg(4);
            }
            // if deck is empty
            else if (collector.getBinder(deckIndex).isEmpty()) {
                deckView.printConfirmationMsg(9);
                deckIndex = -1;
            }
        } while (deckIndex == -1);

        // asks user for card to be removed
        do {
            cardName = collectorView.getStringInput("Enter card name: ");
            cardIndex = collector.getBinder(deckIndex).findCard(cardName);

            // if card is not in deck
            if (cardIndex == -1) {
                deckView.printConfirmationMsg(10);
            }
        } while (cardIndex == -1);

        // asks user if the card will be removed form deck
        if (collectorView.getIntInput("Remove " + cardName + " from " + deckName + "? (1 for yes, 0 for no): ", 0, 1) == 1) {
            collector.getCollection().getCard(collector.getCollection().findCard(cardName)).incrementCollectionCount();
            collector.getBinder(deckIndex).removeCard(cardIndex);
            deckView.printConfirmationMsg(11);
        }
        else {
            deckView.printConfirmationMsg(0);
        }
    }

    public void viewDeck() {
        String deckName;
        int deckIndex;
        int cardIndex;

        // header
        deckView.displayViewDeck();

        // asks for deck name
        do {
            deckName = collectorView.getStringInput("Enter deck name: ");
            deckIndex = collector.findDeck(deckName);

            // if deck doesn't exist
            if (deckIndex == -1) {
                deckView.printConfirmationMsg(4);
            }
        } while (deckIndex == -1);

        // if deck is empty
        if (collector.getDeck(deckIndex).isEmpty()) {
            deckView.printConfirmationMsg(9);
            return;
        }

        // view card and card display selection
        do {
            // displays deck name
            deckView.displayDeck(deckName);

            // displays deck contents
            cardIndex = 1;
            for (int i = 0; i < Deck.MAX_COUNT && collector.getDeck(deckIndex).getCard(i) != null; i++) {
                deckView.displayDeckCard(cardIndex, collector.getDeck(deckIndex).getCard(i).getName());
                cardIndex++;
            }

            // asks user which card to display
            switch (collectorView.getIntInput("Enter card search method (1 to search by name, 2 to search by number, or 0 to exit): ", 0, 2)) {
                case 1:
                    do {
                        cardIndex = collector.getDeck(deckIndex).findCard(collectorView.getStringInput("Enter card name: "));
                        if (cardIndex == -1) {
                            deckView.printConfirmationMsg(10);
                        }
                    } while (cardIndex == -1);
                    break;
                case 2:
                    cardIndex = collectorView.getIntInput("Enter number in deck: ", 1, cardIndex - 1);
                    cardIndex--;
                    break;
            }

            if (cardIndex != -1) {
                Card c = collector.getDeck(deckIndex).getCard(cardIndex);
                cardView.displayCard(c.getName(), c.getCardNo(), c.getRarity().getName(), c.getVariant().getName(),
                        c.getCollectionCount(), c.getBaseValue(), c.getFinalValue());
            }
        } while (cardIndex != 0);

    }
}

