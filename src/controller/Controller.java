package src.controller;

import src.model.*;
import src.view.*;
import java.util.*;

public class Controller {
    Collector collector;
    CollectorView collectorView; // subject to change along with a bunch of stuff

    public Controller() {
        collector = new Collector();
        collectorView = new CollectorView();
    }

    public void run() {
        int input;

        int collectionSize = collector.getCollection().getCards().size();
        int binderCount = collector.getBinder().size();
        int deckCount = collector.getDeck().size();
        // collectorView.displayMainOptions(collector.getCollection().getCards().size(), collector.getBinder().size(), collector.getDeck().size());
        collectorView.displayMainOptions(collectionSize, binderCount, deckCount);
    }
}
