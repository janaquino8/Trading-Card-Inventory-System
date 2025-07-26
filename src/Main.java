/*
    TRADING CARD INVENTORY SYSTEM
    Jan Leoric B. Aquino | John Lorens S. Tee

    This is a trading card inventory system that allows the user to perform actions similar to real-life trading card collecting.
*/

package src;

// import src.controller.Controller;

import src.controller.NewController;
import src.view.CollectorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Initiates running of program
 */
public class Main {
    public static void main(String[] args) {
//        Controller controller = new Controller();
//        controller.run();
        NewController controller = new NewController();
        controller.mainMenu();
    }
}
