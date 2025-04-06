package main;

import java.util.HashSet;
import java.util.List;

public class Traversals {

    private Graph graph;
    public Traversals(Graph graph) {
        this.graph = graph;
    }

    public HashSet<Integer> traverse(int wordID) {
        return helper(wordID, new HashSet<>(), new HashSet<>());
    }

    public HashSet<Integer> helper(int word, HashSet<Integer> markedNodes, HashSet<Integer> hyponyms) {
        if (!markedNodes.add(word)) {
            return hyponyms;
        }
        hyponyms.add(word);
        List<Integer> children = graph.getMapOfHyponymIDS().get(word);
        if (children == null) {
            return hyponyms;
        }
        for (int child : children) {
            helper(child, markedNodes, hyponyms);
        }
        return hyponyms;

    }
}
