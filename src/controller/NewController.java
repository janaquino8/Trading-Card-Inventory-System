package src.controller;

import src.model.*;
import src.model.card.Card;
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
    ActionListener listener;

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
        listener = e -> {
            switch (e.getActionCommand()) {
                case "Add a Card"        -> System.out.println("Add Card");
                case "Add a Binder"      -> System.out.println("Add Binder");
                case "Add a Deck"        -> System.out.println("Add Deck");
                case "Manage Collection" -> manageCollection();
                case "Manage Binders"    -> manageBinders();
                case "Manage Decks"      -> manageDecks();
            }
        };
        collectorView.displayMainMenu(collector.getCollectionTotalCount(),
                               collector.getBindersCount(),
                               collector.getDecksCount(),
                               listener);
    }

    public void manageCollection() {
        listener = e -> {
            switch (e.getActionCommand()) {
                case "Add a Card"           -> System.out.println("1");
                case "Update Card Count"    -> System.out.println("2");
                case "Display a Card"       -> System.out.println("3");
                case "Display Collection"   -> System.out.println("4");
                case "Sell a Card"          -> System.out.println("5");
                case "Return to Main Menu"  -> mainMenu();
            }
        };
        collectorView.displayManageCollection(collector.getCollectionTotalCount(),
                listener);
    }

    public void manageBinders() {
        listener = e -> {
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
        collectorView.displayManageBinders(collector.getBindersCount(),
                listener);
    }

    public void manageDecks() {
        listener = e -> {
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
        collectorView.displayManageDecks(collector.getDecksCount(),
                listener);
    }
}
