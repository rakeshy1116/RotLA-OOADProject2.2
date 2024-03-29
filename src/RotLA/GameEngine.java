package RotLA;

import RotLA.Adventurers.*;
import RotLA.Creatures.Blinker;
import RotLA.Creatures.Creature;
import RotLA.Creatures.Orbiter;
import RotLA.Creatures.Seeker;

import java.util.ArrayList;

// The main Game simulation performer class, initializes all components, performs turns and checks for termination
public class GameEngine {

    private BoardRenderer boardRenderer;
    private ArrayList<Adventurer> adventurers;
    private ArrayList<Creature> creatures;
    private Dice dice;

    //Creates instances of all players, creatures, Board, Dice and initializes them
    public void initialize() {
        this.adventurers = new ArrayList<Adventurer>();
        adventurers.add(new Brawler());
        adventurers.add(new Runner());
        adventurers.add(new Thief());
        adventurers.add(new Sneaker());

        this.creatures = new ArrayList<Creature>();
        for (int i = 0; i < 4; i++) {
            creatures.add(new Blinker());
            creatures.add(new Orbiter());
            creatures.add(new Seeker());
        }
        this.boardRenderer = new BoardRenderer();
        boardRenderer.initialiseBoardForGame(adventurers, creatures);
        this.dice = new Dice();

    }
    

    // Starts turn simulation, simulation will end iftermination conditions are met
    public void startSimulation() {
        //print initial turn 0 board status
        boardRenderer.printGameStatus();
        while (true) {
            // perform turn for all adventurers who are alive
            adventurers.forEach(adventurer -> {
                if (adventurer.isAlive()) {
                    adventurer.performTurn(dice);
                }
            });
            //perform turn for all creatures who are alive;
            creatures.forEach(creature -> {
                if (creature.isAlive()) {
                    creature.performTurn(dice);
                }
            });
            // print board status after both turns
            boardRenderer.printGameStatus();
            //check if termination conditions are met and stop
            if (checkTermination()) break;
        }
    }

    // performs checks of game termination consitions are met
    public boolean checkTermination() {
        boolean isAdventurerAlive = false;
        boolean isCreatureAlive = false;
        int totalTreasure = 0;
        for (Adventurer adventurer : adventurers) {
            totalTreasure += adventurer.getNoOfTreasure();
            isAdventurerAlive = isAdventurerAlive || adventurer.isAlive();
        }
        for (Creature creature : creatures) {
            isCreatureAlive = isCreatureAlive || creature.isAlive();
        }

        //Game ends if Adventures found 10 treasures or Adventures killed all creatures or Creatures killed all adventurers
        if (totalTreasure >= 10) {
            System.out.println("Adventurers win by finding " + String.valueOf(totalTreasure) + " treasures");
            return true;
        } else if (isAdventurerAlive && !isCreatureAlive) {
            System.out.println("Adventurers win by eliminating all creatures");
            return true;
        } else if (isCreatureAlive && !isAdventurerAlive) {
            System.out.println("Creatures win by eliminating all adventurers");
            return true;
        } else {
            return false;
        }
    }

    // Main function to start game simulation
    public static void main(String[] args) {
        GameEngine gm = new GameEngine();
        gm.initialize();
        gm.startSimulation();
 //Uncomment for multiple game run
//        for(int i=0;i<30;i++) {
//            System.out.print("Run " + String.valueOf(i+1) + ": ");
//            gm.initialize();
//            gm.startSimulation();
//        }
    }
}
