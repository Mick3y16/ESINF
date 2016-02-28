package graphexamples;

import graphbase.Edge;
import graphbase.Vertex;
import graphbase.Graph;
import graphbase.GraphAlgorithms;
import java.util.Queue;

/**
 *
 * @author DEI-ESINF
 */
public class AirportNet {

    private Graph<String, Integer> airport;

    public AirportNet() {
        this.airport = new Graph<>(true);
    }

    public void addAirport(String a) {
        this.airport.insertVertex(a);
    }

    public void addRoute(String a1, String a2, double miles, Integer npasseng) {
        this.airport.insertEdge(a2, a2, npasseng, miles);
    }

    public int keyAirport(String airp) {
        Vertex<String, Integer> vAirP = airport.getVertex(airp);

        if (vAirP == null) {
            return -1;
        }

        return vAirP.getKey();
    }

    public String airportbyKey(int key) {
        Vertex<String, Integer> vAirP = airport.getVertex(key);

        if (vAirP == null) {
            return null;
        }

        return vAirP.getElement();
    }

    public Integer trafficAirports(String airp1, String airp2) {
        Vertex<String, Integer> vAirP1 = airport.getVertex(airp1);
        Vertex<String, Integer> vAirP2 = airport.getVertex(airp2);

        if (airp1 == null || airp2 == null) {
            return null;
        }

        Edge<String, Integer> edge = airport.getEdge(vAirP2, vAirP2);

        if (edge == null) {
            return null;
        }

        return edge.getElement();
    }

    public Double milesAirports(String airp1, String airp2) {
        Vertex<String, Integer> vAirP1 = airport.getVertex(airp1);
        Vertex<String, Integer> vAirP2 = airport.getVertex(airp2);

        if (airp1 == null || airp2 == null) {
            return null;
        }

        Edge<String, Integer> edge = airport.getEdge(vAirP2, vAirP2);

        if (edge == null) {
            return null;
        }

        return edge.getWeight();
    }

    public String nroutesAirport() {
        StringBuilder sb = new StringBuilder();

        for (Vertex<String, Integer> vert : airport.vertices()) {
            sb.append(vert.getElement()).append(" ")
                    .append((airport.outDegree(vert) + airport.inDegree(vert))).append("\n");
        }

        return sb.toString();
    }

    public String AirpMaxMiles() {
        StringBuilder sb = new StringBuilder();

        double maxMiles = 0.0;
        for (Edge<String, Integer> e : airport.edges()) {
            if (e.getWeight() > maxMiles) {
                maxMiles = e.getWeight();
            }
        }

        for (Vertex<String, Integer> v : airport.vertices()) {
            for (Edge<String, Integer> e : v.getOutgoing().values()) {
                if (e.getWeight() == maxMiles) {
                    sb.append(v.getElement()).append(" ").append(airport.opposite(v, e).getElement()).append("\n");
                }
            }
        }

        return sb.toString();
    }

    public Boolean ConnectAirports(String airp1, String airp2) {
        Queue<String> qAirpts = GraphAlgorithms.DepthFirstSearch(airport, airp1);

        for (String airp : qAirpts) {
            if (airp.equals(airp2)) {
                qAirpts = GraphAlgorithms.DepthFirstSearch(airport, airp2);

                for (String vairp : qAirpts) {
                    if (vairp.equals(airp1)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
