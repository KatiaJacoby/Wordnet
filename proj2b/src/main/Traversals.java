package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Traversals {
    public HashSet<Integer> markedNodes = new HashSet<>();
    public int wordID;
    public HashSet<Integer> hyponyms;
    private Graph graph;
    public Traversals(Graph graph){
        this.graph = graph;
        this.markedNodes = new HashSet<>();
        this.hyponyms = new HashSet<>();
    }

    public HashSet<Integer> traverse(int wordID) {
        return helper(wordID, markedNodes, hyponyms);
    }

    public HashSet<Integer> helper(int word, HashSet<Integer> markedNodes, HashSet<Integer> hyponyms){
        hyponyms.add(word);
        markedNodes.add(word);
        HashMap<Integer, ArrayList<Integer>> hyponymsMap = graph.mapOfHyponymIDS;
        if (hyponymsMap.get(word)== null){
            return hyponyms;
        }
        for (int i: hyponymsMap.get(word)){
            helper(i, markedNodes, hyponyms);
        }
        return hyponyms;
    }


}
