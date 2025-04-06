package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.List;
import java.util.Set;

public class HyponymsHandler extends NgordnetQueryHandler {
    private DataGraph wordNet;
    private static final int START_YEAR = 1900;
    private static final int END_YEAR = 2020;

    public HyponymsHandler() {

    }

    public HyponymsHandler(DataGraph wordNet) {
        this.wordNet = wordNet;
    }

    @Override
    public String handle(NgordnetQuery q) {
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();
        List<String> words = q.words();

        if (startYear > endYear) {
            k = 0;

        }


        if (startYear == 0) {
            startYear = START_YEAR;
        }
        if (endYear == 0) {
            endYear = END_YEAR;
        }


        Set<String> hyponyms = wordNet.getHyponyms(words, startYear, endYear, k);
        return wordNet.format(hyponyms);

    }
}
