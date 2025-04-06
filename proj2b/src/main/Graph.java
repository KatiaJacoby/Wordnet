package main;
import java.util.*;

public class Graph {

    // key is a synset ID, and value is a list of IDS that are the hyponyms

    private HashMap<Integer, ArrayList<Integer>> mapOfHyponymIDS;
    public Graph() {
        mapOfHyponymIDS = new HashMap<>();
    }

    public void createNode(int node) {
        if (!mapOfHyponymIDS.containsKey(node)) {
            mapOfHyponymIDS.put(node, new ArrayList<>());
        }
    }



    public void addEdge(int hypernym, int hyponym) {
        if (!mapOfHyponymIDS.containsKey(hypernym)) {
            mapOfHyponymIDS.put(hypernym, new ArrayList<>());
        }
        mapOfHyponymIDS.get(hypernym).add(hyponym);
    }


    public HashMap<Integer, ArrayList<Integer>> getMapOfHyponymIDS() {
        return mapOfHyponymIDS;
    }

}
