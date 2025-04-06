package main;

import edu.princeton.cs.algs4.In;

import java.util.*;

import ngrams.NGramMap;


// this class reads in the WordNet data and constructs an instance of the graph class
public class DataGraph {
    private Graph graph;
    private HashMap<Integer, ArrayList<String>> numberAndWord;
    private HashMap<Integer, ArrayList<Integer>> hyponyms;
    private HashMap<String, ArrayList<Integer>> synset;
    private NGramMap nGramMap;
    //private ProfNGramMap nGramMap;

    DataGraph(String wordFile, String countFile, String synsetsFile, String hyponnymsFile) {
        // HashMap where each key is a word and each value is a list of IDs for that word.
        // Key: word Value: IDs
        this.synset = new HashMap<>();
        // HashMap where each key is an ID and each value is a list of the ID's closest hyponyms.
        // Key: ID Value: IDs
        this.hyponyms = new HashMap<>();
        this.nGramMap = new NGramMap(wordFile, countFile);
        //this.nGramMap = new ProfNGramMap(wordFile, countFile);
        // HashMap where each index points to a list of words. Key: ID. Value: Words
        this.numberAndWord = new HashMap<>();
        // HashMap where each index points to a list of its closest hyponyms. Key: ID. Value: IDs
        this.graph = new Graph();
        In readSynsets = new In(synsetsFile);
        In readHyponyms = new In(hyponnymsFile);
        // parse through synset file and create numberAndWord, add to graph, and add to synset
        while (readSynsets.hasNextLine()) {
            String synsetLine = readSynsets.readLine();
            String[] idsandWords = synsetLine.split(",");
            // assuming the data is valid (there is always at least 2 commas)
            if (idsandWords.length < 2) {
                // invalid file because I require there to be 2 elements
                continue;
            }
            int id = Integer.parseInt(idsandWords[0]);
            graph.createNode(id);
            String[] synsets = idsandWords[1].split(" +"); // used a + so I get an empty array if line is empty
            ArrayList<String> everyWord = new ArrayList<>();
            for (int i = 0; i < synsets.length; i++) {
                //Adding each word within the synset (Each word with the same id) to "everyWord"
                // then adding that to numberAndWord so I have an ID and its corresponding words (synonyms)
                everyWord.add(synsets[i]);
                if (!synset.containsKey(synsets[i])) {
                    synset.put(synsets[i], new ArrayList<>());
                }
                // add all IDs for this word
                synset.get(synsets[i]).add(id);
            }
            // add all words for this ID
            numberAndWord.put(id, everyWord);
        }
        //parse through hyponyms file and separate first number (ID) with all of its hyponyms
        while (readHyponyms.hasNextLine()) {
            String lineHyponyms = readHyponyms.readLine();
            String[] hyponymsArray = lineHyponyms.split(",");

            if (hyponymsArray.length == 0) {
                // does not have any elements
                continue;
            }
            int hypernymID = Integer.parseInt(hyponymsArray[0]);


            for (int i = 1; i < hyponymsArray.length; i++) {
                int child = Integer.parseInt(hyponymsArray[i]);


                if (!hyponyms.containsKey(hypernymID)) {
                    hyponyms.put(hypernymID, new ArrayList<>());
                }
                hyponyms.get(hypernymID).add(child);


                if (!hyponyms.containsKey(child)) {
                    hyponyms.put(child, new ArrayList<>());


                }
                graph.addEdge(hypernymID, child);
            }


        }
    }


    public TreeSet<String> getWordHyponyms(String word) {
        TreeSet<String> wordHyponyms = new TreeSet<>();
        if (synset.get(word) == null) {
            return wordHyponyms;
        }

        for (int wordID : synset.get(word)) {
            wordHyponyms.addAll(numberAndWord.get(wordID));
            System.out.println(wordHyponyms);

            Traversals traverser = new Traversals(graph);

            HashSet<Integer> hyponymsSet = traverser.traverse(wordID);

            for (int hyponymID : hyponymsSet) {
                List<String> words = wordsFromID(hyponymID);
                wordHyponyms.addAll(words);
            }
        }
        System.out.println(wordHyponyms);
        return wordHyponyms;
    }


    public Set<String> getAllHyponyms(List<String> words) {
        if (words == null || words.isEmpty()) {
            return new TreeSet<>();
        }


        if (words.size() == 1) {
            return getWordHyponyms(words.getFirst());
        }


        TreeSet<String> result = getWordHyponyms(words.get(0));


        for (int i = 1; i < words.size(); i++) {
            TreeSet<String> currentHyponyms = getWordHyponyms(words.get(i));
            TreeSet<String> intersection = new TreeSet<>();


            for (String hypo : result) {
                if (currentHyponyms.contains(hypo)) {
                    intersection.add(hypo);
                }
            }

            result = intersection;

            if (result.isEmpty()) {
                break;
            }
        }
        return result;
    }


    public String format(Set<String> x) {
        return "[" + String.join(", ", x) + "]";
    }

    public List<String> wordsFromID(int id) {
        return numberAndWord.get(id);
    }

    public Set<String> getHyponyms(List<String> words, int start, int end, int k) {

        Map<String, Double> freqOfExistingWord = new HashMap<>();
        Set<String> everyHyponym = getAllHyponyms(words);
        System.out.println("everyHyponym: " + everyHyponym);

        if (k > 0) {

            for (String word : everyHyponym) {

                List<Double> countList = nGramMap.countHistory(word, start, end).data();

                Double total = 0.0;
                for (Double count : countList) {
                    total += count;
                }
                System.out.println(word + " total: " + total);
                if (total > 0.0) {
                    freqOfExistingWord.put(word, total);
                }
            }

            // added reverseOrder so it acts as a maxHeap not a minHeap
            PriorityQueue<Double> topVals = new PriorityQueue<>(Collections.reverseOrder());




            for (String key: freqOfExistingWord.keySet()) {

                topVals.add(freqOfExistingWord.get(key));
                System.out.println(key + "count : " + freqOfExistingWord.get(key));

            }

            System.out.println("topVals: " + topVals);

            TreeSet<String> result = new TreeSet<>();

            while (k != 0) {
                if (topVals.isEmpty()) {
                    break;
                }
                double temp = topVals.poll();
                for (String key : freqOfExistingWord.keySet()) {
                    if ((freqOfExistingWord.get(key)).equals(temp)) { // && !result.contains(key))
                        result.add(key);
                        k--;
                        if (k == 0) {
                            break;
                        }

                    }
                }

            }

            return result;
        } else {
            return everyHyponym;
        }

    }
}



