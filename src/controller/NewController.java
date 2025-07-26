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
                case "Add a Card" -> System.out.println("Add Card");
                case "Add a Binder" -> System.out.println("Add Binder");
                case "Add a Deck" -> System.out.println("Add Deck");
                case "Manage Collection" -> System.out.println("Manage Collection");
                case "Manage Binder" -> System.out.println("Manage Binder");
                case "Manage Decks" -> System.out.println("Manage Decks");
            }
        };
        collectorView.mainMenu(collector.getCollectionTotalCount(),
                               collector.getBindersCount(),
                               collector.getDecksCount(),
                               listener);
    }
}
