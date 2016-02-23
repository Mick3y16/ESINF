package PQExamples;

//import static java.lang.System.out;
import Priority_queue.HeapPriorityQueue;
import Priority_queue.Entry;
import java.util.Stack;

/**
 *
 * @author DEI-ESINF
 */
public class AirTrafficCC {

    private HeapPriorityQueue<Integer, String> cc;
    int timeslot = 5;  // time slot allocated to land each plane

    public AirTrafficCC(Integer[] p, String[] f) {
        this.cc = new HeapPriorityQueue(p, f);
    }

    public String nextPlaneLanding() {
        return this.cc.min().getValue();
    }

    public void addPlane2Queue(String id, Integer pr) {
        this.cc.insert(pr, id);
    }

    public Entry<Integer, String> clearPlane4Landing() {
        return this.cc.removeMin();
    }

    public Integer nrPlanesWaiting() {
        return this.cc.size();
    }

    public Integer time2land(String id) {
        HeapPriorityQueue clone = this.cc.clone();
        int planes = 0;

        while (!this.cc.removeMin().getValue().equals(id)) {
            planes++;
        }

        return planes * timeslot;
    }

    public void changePriority2(String id, Integer newp) {
        if (!this.cc.isEmpty()) {
            int heapSize = this.cc.size();
            Stack<Entry<Integer, String>> stack = new Stack();

            while (stack.size() != heapSize) {
                stack.push(this.cc.removeMin());

                if (stack.peek().getValue().equals(id)) {
                    break;
                }
            }

            stack.pop();

            while (!stack.isEmpty()) {
                Entry<Integer, String> entry = stack.pop();
                this.cc.insert(entry.getKey(), entry.getValue());
            }

            this.cc.insert(newp, id);
        }
    }

}
