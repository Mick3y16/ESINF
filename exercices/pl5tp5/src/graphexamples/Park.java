package graphexamples;

import graphbase.Graph;
import graphbase.GraphAlgorithms;
import java.util.Deque;

public class Park {

    public static class Activity {

        private String code;
        private Double time;

        public Activity(String c, Double t) {
            code = c;
            time = t;
        }

        public String getCode() {
            return code;
        }

        public void setNumber(String c) {
            code = c;
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
            Activity otherActiv = (Activity) otherObj;

            return this.code == otherActiv.code;
        }

    }
    //------------ end of Static nested Room class ------------

    private Graph<Activity, Double> round;

    public Park() {
        this.round = new Graph<>(true);
    }

    public void addtwoConnectedActivs(Activity a1, Activity a2, Double timeCon) {
        this.round.insertEdge(a1, a2, null, timeCon);
    }

    public double shortPath(Activity a1, Activity a2, Deque<Activity> shortpath) {
        return GraphAlgorithms.shortestPath(round, a1, a2, shortpath);
    }

}
