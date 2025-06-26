package src.controller;

import src.model.*;
import src.view.*;
import java.util.*;

// REMOVE SOUT AND MOVE TO VIEW

public class Controller {
    Collector collector;
    CollectorView collectorView;
    CollectionView collectionView;

    public Controller() {
        collector = new Collector();
        collectorView = new CollectorView();
        collectionView = new CollectionView();
    }

    public void run() {
        int input;

        do {
            int collectionSize = collector.getCollection().getCards().size();
            int binderCount = collector.getBinders().size();
            int deckCount = collector.getDecks().size();

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

        collectorView.displayAddCard();
        name = collectorView.getStringInput("Enter card name: ");
        index = collector.getCollection().findCard(name);

        if (index != -1) {
            collectorView.printConfirmationMsg(4);

            if (!isAutoAdd) {
                input = collectorView.getIntInput("Add another copy of " + name + " to collection? (1 for yes, 0 for no): ", 0, 1);
                if (input == 0) {
                    collectorView.printConfirmationMsg(0);
                    return -1;
                }
            }

            collector.getCollection().getCard(index).incrementCollectionCount();
            collectorView.printConfirmationMsg(5);
            return index;
        }

        collectorView.printConfirmationMsg(6);

        if (!isAutoAdd) {
            input = collectorView.getIntInput("Continue creating " + name + "? (1 for yes, 0 for no): ", 0, 1);
            if (input == 0) {
                collectorView.printConfirmationMsg(0);
                return -1;
            }
        }

        value = collectorView.getDoubleInput("Enter value: ");
        rarity = collectorView.getIntInput("Enter card rarity ([1] COM; [2] UCM; [3] RAR; [4] LEG): ", 1, 4);

        if (rarity == 3 || rarity == 4) {
            variant = collectorView.getIntInput("Enter card variant ([1] N; [2] EA; [3] FA; [4] AA): ", 1, 4);
            collector.getCollection().addCard(name, rarity, variant, value);
            collectorView.printConfirmationMsg(1);
        }
        else {
            collector.getCollection().addCard(name, rarity, value);
            collectorView.printConfirmationMsg(1);
        }

        return collector.getCollection().getCards().size();
    }

    public void createBinder() {
        String name;

        collectorView.displayCreateBinder();

        do {
            name = collectorView.getStringInput("Enter new binder name: ");

            if (collector.findBinder(name) != -1) {
                System.out.println(name + "already exists. Input another name.");
            }
        } while (collector.findBinder(name) != -1);

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

        collectorView.displayCreateDeck();

        do {
            name = collectorView.getStringInput("Enter new deck name: ");

            if (collector.findDeck(name) != -1) {
                System.out.println(name + "already exists. Input another name.");
            }
        } while (collector.findDeck(name) != -1);

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

        collectionView.displayUpdateCardCount();

        switch (input = collectorView.getIntInput("Enter option: ", 0, 2)) {
            case 1:
                index = collector.getCollection().findCard(collectorView.getStringInput("Enter card name: "));
                break;
            case 2:
                index = collector.getCollection().findCard(collectorView.getIntInput("Enter card no.: ", 0, collector.getCollection().getCards().size() - 1));
                break;
            case 0:
                collectorView.printConfirmationMsg(0);
        }

        if (index == -1 && input != 0) {
            collectionView.printConfirmationMsg(3);
        }
        else {
            collectionView.displayCardToUpdateCount(collector.getCollection().getCard(index).getName(),
                                                    collector.getCollection().getCard(index).getCollectionCount());

            if (collector.getCollection().getCard(index).getCollectionCount() == 0) {
                collectionView.printConfirmationMsg(4);
                input = collectorView.getIntInput("Increment card count? (1 for yes, 0 for no): ", 0, 1);
            }
            else {
                collectionView.displayUpdateCardCountOptions();
                input = collectorView.getIntInput("Enter option: ", 0, 2);
            }

            switch (input) {
                case 1:
                    collector.getCollection().getCard(index).incrementCollectionCount();
                    collectionView.printConfirmationMsg(1);
                    break;
                case 2:
                    collector.getCollection().getCard(index).decrementCollectionCount();
                    collectionView.printConfirmationMsg(2);
                    break;
                case 0:
                    collectionView.printConfirmationMsg(0);
            }
        }
    }

    public void displayCard() {

    }

    public void displayCollection() {

    }

    public void deleteBinder() {
        String name;
        int index;

        // display

        do {
            name = collectorView.getStringInput("Enter binder name: ");
            index = collector.findBinder(name);

            if (index == -1) {
                System.out.println(name + " doesn't exist. Input another name.");
            }
        } while (index == -1);

        if (collectorView.getIntInput("Delete binder " + name + "? (1 for yes, 0 for no): ", 0, 1) == 1) {
            collector.deleteBinder(index);
            // binderView confirmation msg
        }
        else {
            // binderView confirmation msg 0
        }
    }

    public void addCardToBinder() {
        if (collector.getCollection().countTotalCards() == 0) {
            // there are no cards in the collection
            return;
        }
        else if (collector.isBindersFull()) {
            // all binders are full
            return;
        }

        int binderIndex = -1;
        String binderName;
        int cardIndex = -1;
        int input;

        // display

        do {
            binderName = collectorView.getStringInput("Enter binder name: ");
            binderIndex = collector.findBinder(binderName);

            if (binderIndex == -1) {
                // doesnt exist
            }
            else if (collector.getBinder(binderIndex).isFull()) {
                // full
                binderIndex = -1;
            }
        } while (binderIndex == -1);

        // display card search options

        switch (input = collectorView.getIntInput("Enter option: ", 0, 2)) {
            case 1:
                cardIndex = collector.getCollection().findCard(collectorView.getStringInput("Enter card name: "));
                break;
            case 2:
                cardIndex = collector.getCollection().findCard(collectorView.getIntInput("Enter card no.: ", 0, collector.getCollection().getCards().size() - 1));
                break;
            case 0:
                // cancel action
        }

        if (cardIndex == -1 && input != 0) {
            // card doesnt exist
        }
        else if (collector.getCollection().getCard(cardIndex).getCollectionCount() == 0) {
            // no copies of the card in the collection
        }
        else {
            if (collectorView.getIntInput("Add " + collector.getCollection().getCard(cardIndex).getName() + " to " +
                                          binderName + "? (1 for yes, 0 for no): ", 0, 1) == 1) {
                collector.getBinder(binderIndex).addCard(collector.getCollection().getCard(cardIndex));
                collector.getCollection().getCard(cardIndex).decrementCollectionCount();
                // success
            }
            else {
                // cancel action
            }
        }
    }

    public void removeCardFromBinder() {
        if (collector.isBindersEmpty()) {
            // all binders are empty
            return;
        }

        int binderIndex = -1;
        String binderName;
        int cardIndex = -1;
        String cardName;
        int input;

        // display

        do {
            binderName = collectorView.getStringInput("Enter binder name: ");
            binderIndex = collector.findBinder(binderName);

            if (binderIndex == -1) {
                // doesnt exist
            }
            else if (collector.getBinder(binderIndex).isEmpty()) {
                // empty
                binderIndex = -1;
            }
        } while (binderIndex == -1);

        // display card search options

        do {
            cardName = collectorView.getStringInput("Enter card name: ");
            cardIndex = collector.getBinder(binderIndex).findCard(cardName);

            if (cardIndex == -1) {
                // card isnt in
            }
        } while (cardIndex == -1);

        if (collectorView.getIntInput("Remove " + cardName + " from " + binderName + "? (1 for yes, 0 for no): ", 0, 1) == 1) {
            collector.getCollection().getCard(collector.getCollection().findCard(cardName)).incrementCollectionCount();
            collector.getBinder(binderIndex).removeCard(cardIndex);
            // success
        }
        else {
            // cancel action
        }
    }

    public void viewBinder() {

    }

    public void trade() {
        if (collector.isBindersEmpty()) {
            // all binders are empty
            return;
        }

        int binderIndex = -1;
        String binderName;
        int cardIndex = -1;
        String cardName;
        int incomingCardIndex;
        double difference;

        // display

        do {
            binderName = collectorView.getStringInput("Enter binder name: ");
            binderIndex = collector.findBinder(binderName);

            if (binderIndex == -1) {
                // doesnt exist
            }
            else if (collector.getBinder(binderIndex).isEmpty()) {
                // empty
                binderIndex = -1;
            }
        } while (binderIndex == -1);

        do {
            cardName = collectorView.getStringInput("Enter card name: ");
            cardIndex = collector.getBinder(binderIndex).findCard(cardName);

            if (cardIndex == -1) {
                // card isnt in
            }
        } while (cardIndex == -1);

        // time to create incoming card

        incomingCardIndex = this.addCard(true);

        // display trade offer

        difference = Math.abs(collector.getCollection().getCard(incomingCardIndex).getFinalValue() -
                     collector.getBinder(binderIndex).getCard(cardIndex).getFinalValue());

        if (difference >= 1) {
            // difference is significant
            if (collectorView.getIntInput("Proceed with trade? (1 for yes, 0 for no): ", 0, 1) == 1) {
                collector.getBinder(binderIndex).trade(cardIndex, collector.getCollection().getCard(incomingCardIndex));
                // success
            }
            else {
                // cancel
            }
        }
        else {
            collector.getBinder(binderIndex).trade(cardIndex, collector.getCollection().getCard(incomingCardIndex));
            // success
        }

        collector.getCollection().getCard(incomingCardIndex).decrementCollectionCount();
    }

    public void deleteDeck() {
        String name;
        int index;

        // display

        do {
            name = collectorView.getStringInput("Enter deck name: ");
            index = collector.findDeck(name);

            if (index == -1) {
                System.out.println(name + " doesn't exist. Input another name.");
            }
        } while (index == -1);

        if (collectorView.getIntInput("Delete deck " + name + "? (1 for yes, 0 for no): ", 0, 1) == 1) {
            collector.deleteDeck(index);
            // deckView confirmation msg
        }
        else {
            // deckView confirmation msg 0
        }
    }

    public void addCardToDeck() {
        if (collector.getCollection().countTotalCards() == 0) {
            // there are no cards in the collection
            return;
        }
        else if (collector.isDecksFull()) {
            // all decks are full
            return;
        }

        int deckIndex = -1;
        String deckName;
        int cardIndex = -1;
        int input;

        // display

        do {
            deckName = collectorView.getStringInput("Enter deck name: ");
            deckIndex = collector.findDeck(deckName);

            if (deckIndex == -1) {
                // doesnt exist
            }
            else if (collector.getDeck(deckIndex).isFull()) {
                // full
                deckIndex = -1;
            }
        } while (deckIndex == -1);

        // display card search options

        switch (input = collectorView.getIntInput("Enter option: ", 0, 2)) {
            case 1:
                cardIndex = collector.getCollection().findCard(collectorView.getStringInput("Enter card name: "));
                break;
            case 2:
                cardIndex = collector.getCollection().findCard(collectorView.getIntInput("Enter card no.: ", 0, collector.getCollection().getCards().size() - 1));
                break;
            case 0:
                // cancel action
        }

        if (cardIndex == -1 && input != 0) {
            // card doesnt exist
        }
        else if (collector.getCollection().getCard(cardIndex).getCollectionCount() == 0) {
            // no copies of the card in the collection
        }
        else if (collector.getDeck(deckIndex).findCard(collector.getCollection().getCard(cardIndex).getName()) != -1) {
            // card is already in deck
        }
        else {
            if (collectorView.getIntInput("Add " + collector.getCollection().getCard(cardIndex).getName() + " to " +
                    deckName + "? (1 for yes, 0 for no): ", 0, 1) == 1) {
                collector.getDeck(deckIndex).addCard(collector.getCollection().getCard(cardIndex));
                collector.getCollection().getCard(cardIndex).decrementCollectionCount();
                // success
            }
            else {
                // cancel action
            }
        }
    }

    public void removeCardFromDeck() {
        if (collector.isDecksEmpty()) {
            // all decks are empty
            return;
        }

        int deckIndex = -1;
        String deckName;
        int cardIndex = -1;
        String cardName;
        int input;

        // display

        do {
            deckName = collectorView.getStringInput("Enter deck name: ");
            deckIndex = collector.findBinder(deckName);

            if (deckIndex == -1) {
                // doesnt exist
            }
            else if (collector.getBinder(deckIndex).isEmpty()) {
                // empty
                deckIndex = -1;
            }
        } while (deckIndex == -1);

        // display card search options

        do {
            cardName = collectorView.getStringInput("Enter card name: ");
            cardIndex = collector.getBinder(deckIndex).findCard(cardName);

            if (cardIndex == -1) {
                // card isnt in
            }
        } while (cardIndex == -1);

        if (collectorView.getIntInput("Remove " + cardName + " from " + deckName + "? (1 for yes, 0 for no): ", 0, 1) == 1) {

            collector.getCollection().getCard(collector.getCollection().findCard(cardName)).incrementCollectionCount();
            collector.getBinder(deckIndex).removeCard(cardIndex);
            // success
        }
        else {
            // cancel action
        }
    }

    public void viewDeck() {

    }
}

