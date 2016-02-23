package PL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author DEI-ESINF
 */
public class TREE_WORDS extends BST<TextWord> {

    public void createTree() throws FileNotFoundException {
        Scanner ler = new Scanner(new File("src/PL/xxx.xxx"));
        while (ler.hasNextLine()) {
            String[] pal = ler.nextLine().split("(\\,)|(\\s)|(\\.)");
            for (String word : pal) {
                if (word.length() > 0) {
                    insert(new TextWord(word, 1));
                }
            }
        }
        ler.close();
    }
//================================================================ 

    /**
     * Inserts a new word in the tree, or increments the number of its
     * occurrences.
     *
     * @param element
     */
    @Override
    public void insert(TextWord element) {
        root = insert(element, root);
    }

    /*
        if (node.getElement().compareTo(element) == 0) {
            node.setElement(element);
        } else if (node.getElement().compareTo(element) > 0) {
            node.setLeft(insert(element, node.getLeft()));
        } else {
            node.setRight(insert(element, node.getRight()));
        }

        return node;
     */
    private Node<TextWord> insert(TextWord element, Node<TextWord> node) {
        if (node == null) {
            return new Node(element, null, null);
        }

        if (node.getElement().compareTo(element) == 0) {
            node.getElement().incOcorrences();
        } else if (node.getElement().compareTo(element) > 0) {
            node.setLeft(insert(element, node.getLeft()));
        } else if (node.getElement().compareTo(element) < 0) {
            node.setRight(insert(element, node.getRight()));
        }

        return node;
    }
//****************************************************************

    /**
     * Returns a map with a list of words for each occurrence found.
     *
     * @return a map with a list of words for each occurrence found.
     */
    public Map<Integer, List<String>> getWordsOccurrences() {
        Map<Integer, List<String>> occurrences = new HashMap<>();

        for (TextWord word : inOrder()) {
            if (occurrences.containsKey(word.getOcorrences())) {
                occurrences.get(word.getOcorrences()).add(word.getWord());
            } else {
                List<String> wordsList = new ArrayList<String>();
                wordsList.add(word.getWord());

                occurrences.put(word.getOcorrences(), wordsList);
            }
        }

        return occurrences;
    }

}
