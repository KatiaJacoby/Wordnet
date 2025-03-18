package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    private TreeMap<String, TimeSeries> wordMap;
    private TimeSeries countMap;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        wordMap = new TreeMap();
        countMap = new TimeSeries();
        In wordFile = new In(wordsFilename);
        while (wordFile.hasNextLine()) {
            String[] words = wordFile.readLine().split("\t");
            String key = words[0];
            int year = Integer.parseInt(words[1]);
            double count = Double.parseDouble(words[2]);
            if (wordMap.containsKey(key)) {
                wordMap.get(key).put(year, count);
            } else {
                TimeSeries value = new TimeSeries();
                value.put(year, count);
                wordMap.put(key, value);
            }
        }
        In countFile = new In(countsFilename);
        while (countFile.hasNextLine()) {
            String[] numbers = countFile.readLine().split(",");
            int year = Integer.parseInt(numbers[0]);
            double count = Double.parseDouble(numbers[1]);
            countMap.put(year, count);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {

        TimeSeries copy = wordMap.get(word);
        if (copy == null) {
            return new TimeSeries();
        }
        return new TimeSeries(copy, startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        return countHistory(word, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return (TimeSeries) countMap.clone();
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        if (!wordMap.containsKey(word)) {
            return new TimeSeries();
        }
        try{
            TimeSeries stuff = new TimeSeries(wordMap.get(word),startYear, endYear);
            return (stuff.dividedBy(countMap));
        } catch (IllegalArgumentException e) {
            return new TimeSeries();
        }
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        return(weightHistory(word, MIN_YEAR, MAX_YEAR));
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        Iterator<String> hi = words.iterator();
        TimeSeries curr = new TimeSeries();
        while (hi.hasNext()) {
            String currWord = (String) hi.next();
            if (wordMap.containsKey(currWord)) {
                curr = curr.plus(new TimeSeries(wordMap.get(currWord), startYear, endYear));
            }
        }
        try{
            return curr.dividedBy(countMap);
        } catch (IllegalArgumentException e) {
            return new TimeSeries();
        }

    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        return summedWeightHistory(words, MIN_YEAR, MAX_YEAR);
    }
}
