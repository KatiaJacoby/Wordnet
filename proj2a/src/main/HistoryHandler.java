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
        int startYear = q.startYear();
        int endYear = q.endYear();
        ArrayList<String>words = new ArrayList<>(q.words());
        TimeSeries firstPlot = thing.countHistory(words.get(0), startYear, endYear);
        TimeSeries secondPlot = thing.countHistory(words.get(1), startYear, endYear);
        ArrayList<TimeSeries> lts = new ArrayList<>();
        lts.add(firstPlot);
        lts.add(secondPlot);
        XYChart chart = Plotter.generateTimeSeriesChart(words, lts);
        String encodedImage = Plotter.encodeChartAsString(chart);
        return encodedImage;
    }
}
