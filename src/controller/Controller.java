package src.controller;

import src.model.*;
import src.view.*;
import java.util.*;

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
                input = collectorView.getIntInput("Add another copy to collection? (1 for yes, 0 for no): ", 0, 1);
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
            input = collectorView.getIntInput("Continue creating card? (1 for yes, 0 for no): ", 0, 1);
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
        } while (collector.findBinder(name) != -1);

        collector.createBinder(name);
        collectorView.printConfirmationMsg(2);
    }

    public void createDeck() {
        String name;

        collectorView.displayCreateDeck();

        do {
            name = collectorView.getStringInput("Enter new deck name: ");
        } while (collector.findDeck(name) != -1);

        collector.createDeck(name);
        collectorView.printConfirmationMsg(3);
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
            collectionView.displayCardtoUpdateCount(collector.getCollection().getCard(index).getName(),
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
}

