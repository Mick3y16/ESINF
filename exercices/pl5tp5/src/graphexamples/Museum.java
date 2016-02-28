package graphexamples;

import graphbase.Graph;
import graphbase.GraphAlgorithms;
import graphbase.Vertex;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Objects;

public class Museum {

    //------------ Static nested Room class ------------
    public static class Room {

        private Integer number;
        private Double time;

        public Room(Integer n, Double t) {
            number = n;
            time = t;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer n) {
            number = n;
        }

        Double getTime() {
            return time;
        }

        public void setTime(Double t) {
            time = t;
        }

        @Override
        public boolean equals(Object otherObj) {
            if (this == otherObj) {
                return true;
            }

            if (otherObj == null || this.getClass() != otherObj.getClass()) {
                return false;
            }

            Room otherRoom = (Room) otherObj;

            return Objects.equals(this.number, otherRoom.number);
        }

    }
    //------------ end of Static nested Room class ------------

    private Graph<Room, Integer> exhibition;

    public Museum() {
        this.exhibition = new Graph<>(false);
    }

    public void addtwoConnectedRooms(Room r1, Room r2) {
        exhibition.insertEdge(r1, r2, null, 0);
    }

    public double timevisitAllrooms() {
        double time = 0;

        for (Vertex<Room, Integer> r : this.exhibition.vertices()) {
            time += r.getElement().getTime();
        }

        return time;
    }

    public double timeOnevisit(Deque<Room> visit) {
        double time = 0;

        while (!visit.isEmpty()) {
            Room r = visit.pop();
            time += r.time;
        }

        return time;
    }

    public ArrayList<Deque<Room>> visitwithLimitedtime(Room r1, Room r2, double time) {
        ArrayList<Deque<Room>> routes = GraphAlgorithms.allPaths(exhibition, r1, r2);
        ArrayList<Deque<Room>> validRoutes = new ArrayList<>();

        for (Deque<Room> route : routes) {
            if (this.timeOnevisit(route) < time) {
                validRoutes.add(route);
            }
        }

        return validRoutes;
    }

    public Deque<Room> visitwithAllrooms(Room r) {
        for (Vertex<Room, Integer> vertex : exhibition.vertices()) {
            ArrayList<Deque<Room>> list = GraphAlgorithms.allPaths(exhibition, r, vertex.getElement());

            for (Deque<Room> deque : list) {
                if (deque.size() == exhibition.numVertices()) {
                    return deque;
                }
            }
        }

        return null;
    }

}
