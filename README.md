# CCPROG3-Trading-Card-Inventory-System
A trading card inventory system made using Java

## About
This is a basic trading card inventory system with GUI, written using Java.

This program allows users to track and organize collections of trading cards, equipped with features for **adding, deleting, viewing, trading, and selling cards**. 

Card information includes the following: 
- name
- value (in USD)
- rarity: _common, uncommon, rare, legendary_
- variant (applied to rare and legendary cards, incl. a multiplier on the card's value): _normal (1x), extended-art (1.5x), full-art (2x), alt-art (3x)_

There are also holder types where the user may store cards:

| **Binder - stores up to 20 cards (including duplicates)**                                             | **Deck - stores up to 10 non-duplicate cards**          |
| :---------------------------------------------------------------------------------------------------: | :-----------------------------------------------------: |
| **_Tradable Binders_ - can be traded**                                                                | Normal - no restrictions; cannot be sold                |
| Non-curated - no restrictions                                                                         | Sellable - can be sold for the total value of all cards |
| Collector - restricted to cards of non-normal variants                                                |
| **_Sellable Binders_ - can be sold for the total value of all cards**                                 |
| Pauper - restricted to common and uncommon cards                                                      |
| Rares - restricted to rare and legendary cards; adds a 10% handling fee                               |
| Luuxry - restricted to cards of non-normal variants; value can be increased; adds a 10% handling fee  |

This is done as a Major Course Output, part of the course requirements for CCPROG3 in DLSU. This version includes basic GUI implementation (via ```Main.java```), as well as a CLI implementation (via ```MainCLI.java```).

## Installation Guide

### Cloning the repository
```sh
git clone https://github.com/janaquino8/Trading-Card-Inventory-System
```

### Compiling the project (in CMD)
```sh
cd src
javac -d ./out/ ./Main.java ./controller/*.java ./model/*.java ./view/*.java ./model/card/*.java ./model/holders/*.java ./model/holders/binder/*.java ./model/holders/deck/*.java
```

### Running the project (in CMD)
```sh
java Main.java
```

## File structure
The project follows the Model-View-Controller (MVC) pattern. Each component is separated per package, and should be accessed in ```src```.

### /src
- ```controller```
- ```model```
- ```view```
- ```Main.java```

### /controller
- ```Controller.java```
- ```OldController.java```

### /model
- ```card```
- ```holders```
- ```Collector.java```

### /view
- Contains all GUI classes (for GUI version) and view classes (for CLI version)

---

_NOTE: This project was made using JDK 24._

_The authors would like to thank their CCPROG3 professor, Sir Ryan, for guidance in this project._

_Made by Jan Leoric B. Aquino & John Lorens S. Tee_
