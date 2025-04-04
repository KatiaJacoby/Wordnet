package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Graph {
   public HashMap<Integer, ArrayList<Integer>> mapOfHyponymIDS; // key is a synset ID, and value is a list of IDS that are the hyponyms
   public Graph() {
       mapOfHyponymIDS = new HashMap<>();
   }
   public void createNode (int Node) {
      if (!mapOfHyponymIDS.containsKey(Node)){
         mapOfHyponymIDS.put(Node, new ArrayList<>());
      }
   }

   public void addEdge(int child, int parent){
       if(!mapOfHyponymIDS.containsKey(child)){
           mapOfHyponymIDS.put(child, new ArrayList<>());
           mapOfHyponymIDS.get(child).add(parent);
       }
   }

   public void traverseNodes(int ID){
       Traversals traverser = new Traversals(this);
       HashSet<Integer> hyponyms = traverser.traverse(ID);
   }


}
