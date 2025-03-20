package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import org.knowm.xchart.XYChart;
import plotting.Plotter;

import java.util.ArrayList;

public class HistoryHandler extends NgordnetQueryHandler {
    int startYear;
    int endYear;
    ArrayList<String> words;
    private NGramMap thing;

    public HistoryHandler(NGramMap map) {
        thing = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        startYear = q.startYear();
        endYear = q.endYear();
        words = new ArrayList<>(q.words());
        ArrayList<TimeSeries> plots = new ArrayList<>();
        for (String word: words) {
            plots.add(thing.weightHistory(word, startYear, endYear));
        }
        XYChart chart = Plotter.generateTimeSeriesChart(words, plots);
        String encodedImage = Plotter.encodeChartAsString(chart);
        return encodedImage;
    }
}
