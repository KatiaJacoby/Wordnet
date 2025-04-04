package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.List;

public class HyponymsHandler extends NgordnetQueryHandler {
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        String word1 = words.getFirst();
        return InstanceofGraph.listHyponyms(word1);
        return("Hello!");
    }

}
