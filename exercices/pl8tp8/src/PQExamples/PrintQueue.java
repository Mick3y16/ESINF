package PQExamples;

import Priority_queue.Entry;
import Priority_queue.HeapPriorityQueue;

/**
 *
 * @author Pedro Moreira
 */
public class PrintQueue {
    
    private HeapPriorityQueue<Integer, Document> printerHeap;
    
    // add a Document to the printing queue
    public void addDoc2Queue(Document document) {
        printerHeap.insert(document.priority, document);
    }
    
    // send a Document to printer, removing it from the queue
    public Document send2Printer() {
        return printerHeap.removeMin().getValue();
    }

    // returns the next Document in line to be printed
    public Entry<Integer, Document> nextDoc2Print() {
        HeapPriorityQueue<Integer, Document> clone = printerHeap.clone();
        
        return clone.removeMin();
    }
    
    // returns the estimated time before the printing of a specific document starts,
    // considering that the printer takes in average 2 seconds to print each page
    public int time2print(Document document) {
        int timeBeforePrint = 0;
        HeapPriorityQueue<Integer, Document> clone = printerHeap.clone();
        
        Document docPoped = null;
        while(!clone.isEmpty() && (docPoped = clone.removeMin().getValue()).id != document.id) {
            timeBeforePrint += docPoped.numberPages * 2;
        }
        
        return (docPoped != null && docPoped.id == document.id) ? timeBeforePrint : 0; 
    }
    
    private class Document {
        
        public int id;
        public int numberPages;
        public int priority;
        
    }
}
