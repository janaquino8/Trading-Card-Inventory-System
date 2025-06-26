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
        int input2;

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
                        collectorView.displayCollectionOptions();
                    }
                    break;
                case 2:
                    if (binderCount == 0) {
                        this.createBinder();
                    }
                    else {
                        collectorView.displayBinderOptions();
                    }
                    break;
                case 3:
                    if (deckCount == 0) {
                        this.createDeck();
                    }
                    else {
                        collectorView.displayDeckOptions();
                    }
                    break;
            }
        } while (input != 0);

        collectorView.exit();
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

    // display card

    // display collection

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

    // viewBinder

    // trade

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

    // viewDeck
}

