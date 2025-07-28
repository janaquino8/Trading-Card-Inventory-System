package src.controller;

import java.util.*;

import src.model.*;
import src.model.card.Card;
import src.model.holders.NameSorter;
import src.model.holders.binder.*;
import src.model.holders.deck.*;
import src.view.*;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewController {
    Collector collector;
    CollectorView collectorView;
    CollectionView collectionView;
    BinderView binderView;
    DeckView deckView;
    ActionListener actionListener;

    /**
     * Controller
     * Constructor to construct a Collector object
     */
    public NewController() {
        collector = new Collector();
        collectorView = new CollectorView();
        mainMenu();
    }

    public void mainMenu() {
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

        collectorView.displayMainMenu(collector.getCollectionCardCount(), collector.getBindersCount(),
                               collector.getDecksCount(), actionListener);
    }

    public void manageCollection() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Add a Card"           -> addCard();
                case "Update Card Count"    -> System.out.println("2");
                case "Display a Card"       -> System.out.println("3");
                case "Display Collection"   -> displayCollection();
                case "Sell a Card"          -> System.out.println("5");
                case "Return to Main Menu"  -> mainMenu();
            }
        };

        collectorView.displayManageCollection(collector.getCollectionTotalCount(),
                actionListener);
    }

    public void manageBinders() {
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

        collectorView.displayManageBinders(collector.getBindersCount(), collector.getCollectionTotalCount(),
                collector.isBindersFull(), collector.isBindersEmpty(), collector.getSellableBinders().size(),
                actionListener);
    }

    public void manageDecks() {
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

        collectorView.displayManageDecks(collector.getDecksCount(), collector.getCollectionTotalCount(),
                collector.isDecksFull(), collector.isDecksEmpty(), collector.getSellableBinders().size(),
                actionListener);
    }

    public void addCard() {
        actionListener = e -> {
            switch (e.getActionCommand()) {
                case "Back" -> {
                    collectionView.dispose();
                    collectorView = new CollectorView();
                }
                case "Add" -> System.out.println("test");
            }
        };

        collectorView.dispose();
        collectionView = new CollectionView();

        collectionView.displayAddCard(actionListener);
    }

    public void updateCardCount() {

    }

    public void displayCard() {

    }

    public void displayCollection() {
        actionListener = e -> {
            if (e.getActionCommand().equals("Back")) {
                collectionView.dispose();
                collectorView = new CollectorView();
                manageCollection();
            }
        };

        collectorView.dispose();
        collectionView = new CollectionView();
        ArrayList<Card> displayableCards = new ArrayList<Card>(collector.getCollection().getCards());
        ArrayList<String> displayableCardsList = new ArrayList<String>();

        displayableCards.removeIf(c -> c.getCollectionCount() == 0);
        displayableCards.sort(new NameSorter());

        for (Card c : displayableCards) {
            displayableCardsList.add(c.getName() + " (" + c.getCollectionCount() + " copies)");
        }

        collectionView.displayCollection(displayableCardsList.toArray(new String[0]));
    }

    public void sellCard() {

    }
}
