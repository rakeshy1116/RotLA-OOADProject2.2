package RotLA.Creatures;

import RotLA.Adventurers.Adventurer;
import RotLA.Dice;
import RotLA.Room;
import RotLA.RoomFinder;

import java.util.ArrayList;

public abstract class Creature {

    protected Room room;
    protected boolean alive;
    protected RoomFinder roomFinder; //roomFinder is used to find a particular room from boardList
    protected String abbrv;

    public void setRoomFinder(RoomFinder roomFinder) {
        this.roomFinder = roomFinder;
    }

    abstract void move();  //each creature has different move so all of them will override this abstract method

    public void performTurn(Dice dice) {
        move();
        Room currentRoom = this.room;
        if (!currentRoom.getAdventurers().isEmpty())
            fight(dice);
    }

    public int rollDice(Dice dice) {
        return dice.getDiceRoll();
    }

    public void fight(Dice dice) {   // during fight, creature will get the list of adventurers present in the same room
        // and fight with each of them
        ArrayList<Adventurer> adventurers = room.getAdventurers();
        for(int i=0;i<adventurers.size();i++) {

            Adventurer adventurer=adventurers.get(i);
            int adventureRolls = adventurer.rollDiceFight(dice);
            int creatureRolls = this.rollDice(dice);
            if (adventureRolls > creatureRolls) {
                this.alive = false;
            } else if (creatureRolls > adventureRolls) {
                adventurer.takeDamage();
                if (!adventurer.isAlive()) {
                    room.removeAdventurer(adventurer); // loser gets removed from the room
                }
            } else {
                //in case of tie, do nothing
            }
        }
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean isAlive() {
        return alive;
    }

    public void die() {
        this.alive = false;
    }

    public String getAbbrv() {
        return abbrv;
    }
}


