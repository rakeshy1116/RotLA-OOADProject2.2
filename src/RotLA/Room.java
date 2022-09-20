package RotLA;

import RotLA.Adventurers.Adventurer;
import RotLA.Creatures.Creature;
import org.javatuples.Triplet;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private Triplet<Integer, Integer, Integer> roomId;
    private List<Room> neighbours;

    private ArrayList<Adventurer> adventurers;

    private ArrayList<Creature> creatures;

    public Room(int level, int verticalDir, int horizontalDir) {
        this.roomId = new Triplet<>(level, verticalDir, horizontalDir);
        adventurers = new ArrayList<>();
        creatures = new ArrayList<>();
    }

    public void setNeighbours(List<Room> neighbours) {
        this.neighbours = neighbours;
    }

    public List<Room> getNeighbours() {
        return neighbours;
    }

    public Triplet<Integer, Integer, Integer> getRoomId() {
        return roomId;
    }

    public void addAdventurer(Adventurer adventurer) {
        adventurers.add(adventurer);
    }

    public void addCreature(Creature creature) {
        creatures.add(creature);
    }

    public ArrayList<Adventurer> getAdventurers() {
        return adventurers;
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }
}
