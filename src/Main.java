/*
    TRADING CARD INVENTORY SYSTEM
    Jan Leoric B. Aquino | John Lorens S. Tee

    This is a trading card inventory system that allows the user to perform actions similar to real-life trading card collecting.
*/

package src;

import src.controller.Controller;
import src.controller.NewController;

/**
 * Contains the main method which initiates running of program
 *
 * <p> Due to partial completion of the GUI, the CLI version of the program is used at the moment. You may test the
 * existing GUI functionality by commenting out lines 25-26 and uncommenting lines 29-30
 */
public class Main {
    /**
     * Main method of the program
     * @param args main argument
     */
    public static void main(String[] args) {
        //Controller controller = new Controller();
        //controller.run();

//        For GUI Functionality
        NewController controller = new NewController();
        controller.mainMenu();
    }
}
