/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author DEI-ESINF
 * @param <E> Generic type of buffer element
 */
public class PriorityBufferPrinter<E extends Document> implements Iterable<E> {

    private ArrayList<E> buffer;
    private Integer maxSize;
    private Integer currentSize = 0;

    /**
     * Creates a new PriorityBufferPrinter with a maximum buffer size maxSize
     *
     * @param maxSize maximum buffer size
     */
    public PriorityBufferPrinter(Integer maxSize) {
        buffer = new ArrayList<>(); // Diamond notation: the type can be inferred by the compiler
        this.maxSize = maxSize;
    }

    /**
     * Adds a document to the buffer.Documents are added using a numeric
     * priority system, being FIFO when priorities are equal.
     *
     * @param doc the document to be added to the buffer
     * @return true if the buffer has space, false otherwise
     */
    public Boolean addDocument(E doc) {
        boolean documentAdded = false;

        if (currentSize + doc.getSize() <= maxSize) {
            if (!buffer.isEmpty()) {
                int iterations = 0, totalSize = buffer.size();

                while (iterations < totalSize) {
                    E otherDocument = buffer.remove(0);

                    if (!documentAdded && otherDocument.getPriority() > doc.getPriority()) {
                        buffer.add(buffer.size(), doc);
                        documentAdded = true;
                    }

                    buffer.add(buffer.size(), otherDocument);
                    iterations++;
                }
            }

            if (!documentAdded) {
                documentAdded = buffer.add(doc);
            }

            this.currentSize += doc.getSize();
        }

        return documentAdded;
    }

    /**
     * Gets the next document in the queue.
     *
     * @return The next document in the queue
     */
    public E getDocument() {
        E doc = null;

        if (!buffer.isEmpty()) {
            doc = buffer.remove(0);
            currentSize -= doc.getSize();
        }

        return doc;
    }

    /**
     * Deletes a document from the buffer (if it exists), given a name and an
     * author
     *
     * @param name the name of the Document
     * @param author the author of the Document
     * @return true if the document existed, false otherwise
     */
    public Boolean delDocument(String name, String author) {
        boolean documentDeleted = false;
        int iterations = 0, initialSize = buffer.size();

        while (iterations < initialSize && !documentDeleted) {
            E otherDocument = buffer.remove(0);

            if (!otherDocument.getName().equals(name)
                    && !otherDocument.getAuthor().equals(author)) {
                buffer.add(buffer.size(), otherDocument);
            } else {
                currentSize -= otherDocument.getSize();
                documentDeleted = true;
            }

            iterations++;
        }

        return documentDeleted;
    }

    /**
     * Deletes all the documents which are superior to a given size
     *
     * @param size the size above which documents will be deleted
     * @return true if there is at least one Document size
     */
    public Boolean delDocumentsAbove(Integer size) {
        int iterations = 0, initialSize = buffer.size();

        while (iterations < initialSize) {
            E doc = buffer.remove(0);

            if (doc.getSize() <= size) {
                buffer.add(buffer.size(), doc);
            } else {
                currentSize -= doc.getSize();
            }

            iterations++;
        }

        return buffer.size() != initialSize;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public Iterator<E> iterator() {
        return buffer.iterator();
    }

}
