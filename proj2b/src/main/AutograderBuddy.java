package main;

import browser.NgordnetQueryHandler;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymsHandler(String word, String count, String synset, String hyponym) {
        DataGraph wordNet = new DataGraph(word, count, synset, hyponym);
        return new HyponymsHandler(wordNet);
    }
}
