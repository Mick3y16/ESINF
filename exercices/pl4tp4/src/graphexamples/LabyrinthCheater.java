package graphexamples;

import graph.AdjacencyMatrixGraph;
import graph.EdgeAsDoubleGraphAlgorithms;
import graph.GraphAlgorithms;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A class to represent a labyrinth map with rooms, doors and exits Methods
 * discover the nearest exit and the path to it Stores a graph of private Room
 * vertex and Door edges
 *
 * @author DEI-ESINF
 *
 */
public class LabyrinthCheater {

    private class Room {

        String name;
        boolean hasExit;

        public Room(String name, boolean hasExit) {
            this.name = name;
            this.hasExit = hasExit;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public boolean equals(Object otherObject) {
            if (this == otherObject) {
                return true;
            }

            if (otherObject == null || this.getClass() != otherObject.getClass()) {
                return false;
            }

            Room otherRoom = (Room) otherObject;

            return this.name.equals(otherRoom.name);
        }

    }

    private class Door {
    }

    AdjacencyMatrixGraph<Room, Door> map;

    public LabyrinthCheater() {
        map = new AdjacencyMatrixGraph<>();
    }

    /**
     * Inserts a new room in the map
     *
     * @param name
     * @param hasExit
     * @return false if city exists in the map
     */
    public boolean insertRoom(String name, boolean hasExit) {
        return this.map.insertVertex(new Room(name, hasExit));
    }

    /**
     * Inserts a new door in the map fails if room does not exist or door
     * already exists
     *
     * @param from room
     * @param to room
     * @return false if a room does not exist or door already exists between
     * rooms
     */
    public boolean insertDoor(String from, String to) {
        return this.map.insertEdge(new Room(from, false), new Room(to, false), new Door());
    }

    /**
     * Returns a list of rooms which are reachable from one room
     *
     * @param room
     * @return list of room names or null if room does not exist
     */
    public Iterable<String> roomsInReach(String room) {
        Room r = new Room(room, false);

        if (this.map.checkVertex(r)) {
            LinkedList<Room> lbfs = GraphAlgorithms.DFS(map, new Room(room, false));

            LinkedList<String> roomNames = new LinkedList<>();
            for (Room roomFromList : lbfs) {
                roomNames.add(roomFromList.getName());
            }

            return roomNames;
        }

        return null;
    }

    /**
     * Returns the nearest room with an exit
     *
     * @param room from room
     * @return room name or null if from room does not exist or there is no
     * reachable exit
     */
    public String nearestExit(String room) {
        LinkedList<Room> bfs = GraphAlgorithms.BFS(map, new Room(room, false));

        for (Room r : bfs) {
            if (r.hasExit) {
                return r.name;
            }
        }

        return null;
    }

    /**
     * Returns the shortest path to the nearest room with an exit
     *
     * @param from
     * @return list of room names or null if from room does not exist or there
     * is no reachable exit
     */
    public LinkedList<String> pathToExit(String from) {
        if (map.checkVertex(new Room(from, true))) {
            String to = nearestExit(from);

            if (map.checkVertex(new Room(to, true))) {
                LinkedList<LinkedList<Room>> paths = new LinkedList<>();
                boolean result = GraphAlgorithms.allPaths(
                        map, new Room(from, true), new Room(to, true), paths);

                if (result) {
                    Iterator<LinkedList<Room>> it = paths.listIterator();

                    LinkedList<Room> minPath = it.next();

                    while (it.hasNext()) {
                        if (minPath.size() > it.next().size()) {
                            minPath = it.next();
                        }
                    }

                    LinkedList<String> names = new LinkedList<>();

                    for (Room r : minPath) {
                        names.add(r.name);
                    }

                    return names;
                }
            }
        }

        return null;
    }

}
