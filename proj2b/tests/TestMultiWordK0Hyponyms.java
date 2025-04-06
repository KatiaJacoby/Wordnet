import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import edu.princeton.cs.algs4.StdRandom;
import main.AutograderBuddy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Tests the case where the list of words is length greater than 1, but k is still zero. */
public class TestMultiWordK0Hyponyms {
    // this case doesn't use the NGrams dataset at all, so the choice of files is irrelevant
    // ngrams files
    public static final String VERY_SHORT_WORDS_FILE = "data/ngrams/very_short.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    private static final String SMALL_WORDS_FILE = "data/ngrams/top_14377_words.csv";
    private static final String WORDS_FILE = "data/ngrams/top_49887_words.csv";
    private static final String SMALLEST = "data/ngrams/smallest_top_words.csv";

    // wordnet Files
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";
    public static final String LARGE_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String LARGE_HYPONYM_FILE = "data/wordnet/hyponyms.txt";
    private static final String HYPONYMS_FILE_SUBSET = "data/wordnet/hyponyms1000-subgraph.txt";
    private static final String SYNSETS_FILE_SUBSET = "data/wordnet/synsets1000-subgraph.txt";
    private static final String BUGS_AND_BEATLES_SYNSET = "data/wordnet/CarandBugsSynsets.txt";
    private static final String BUGS_AND_BEATLES_HYPONYM = "data/wordnet/CarAndBugsHyponyms.txt";
    private static final String HYPONYMS_11 = "data/wordnet/hyponyms11.txt";
    private static final String SYNSETS_11 = "data/wordnet/synsets11.txt";
    private static final String PLURAL_SYNSETS_16 = "data/wordnet/pluralsynsets16.txt";


    // EECS files
    private static final String FREQUENCY_EECS_FILE = "data/ngrams/frequency-EECS.csv";
    private static final String HYPONYMS_EECS_FILE = "data/wordnet/hyponyms-EECS.txt";
    private static final String SYNSETS_EECS_FILE = "data/wordnet/synsets-EECS.txt";


    /** This is an example from the spec.*/

    @Test
    public void testTwoWordsK0NotExisting() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                VERY_SHORT_WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("transition");
        words.add("change");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 100);
        String actual = studentHandler.handle(nq);
        //String expected = "[alteration, change, increase, jump, leap, modification, saltation, transition]";
        String expected = "[]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testTwoWorkdsK0Existing() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("transition");
        words.add("change");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        //String expected = "[alteration, change, increase, jump, leap, modification, saltation, transition]";
        String expected = "[jump, leap, saltation, transition]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void AllExistK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SYNSETS_11, HYPONYMS_11);
        List<String> words = new ArrayList<>();
        words.add("action");
        words.add("change");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        //String expected = "[alteration, change, increase, jump, leap, modification, saltation, transition]";
        String expected = "[change, demotion]";
        assertThat(actual).isEqualTo(expected);
        /* action
          0: action
            1: change
              2: demotion
          change
             1: change
               2: demotion
         */


    }

    @Test
    public void testBugsandBeatlesK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, BUGS_AND_BEATLES_SYNSET, BUGS_AND_BEATLES_HYPONYM);
        List<String> words = new ArrayList<>();
        words.add("car");
        words.add("bug");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        //String expected = "[alteration, change, increase, jump, leap, modification, saltation, transition]";
        String expected = "[beetle]";
        assertThat(actual).isEqualTo(expected);
    }



    @Test
    public void testOccurrenceAndChangeK0WithoutSaltation() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("occurrence");
        /*
        1: happening occurrence occurrent natural_event
           2:  change alteration modification
             3:transition
               5: jump leap saltation
             4:increase
           11: alteration modification adjustment
             12: conversion
         */
        words.add("change");
        /*
        2: change alteration modification
          3: transition
            5: jump leap saltation
          4: increase
        8: change
          9: demotion
          10: variation
         */

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 100);
        String actual = studentHandler.handle(nq);
        String expected = "[alteration, change, increase, jump, leap, modification, transition]";
        assertThat(actual).isEqualTo(expected);

        /*
        0,event,dummy
        1,happening occurrence occurrent natural_event,dummy
        2,change alteration modification,dummy
        3,transition,dummy
        4,increase,dummy
        5,jump leap saltation,dummy
        6,act human_action human_activity,dummy
        7,action,dummy
        8,change,dummy
        9,demotion,dummy
        10,variation,dummy
        11,alteration modification adjustment,dummy
        12,conversion,dummy
        13,mutation,dummy
        14,transition,dummy
        15,flashback,dummy
        16,airport runway,dummy

        0,1,6,14
        1,2,11
        2,3,4
        3,5
        6,7
        7,8
        8,9,10
        11,12
        11,13
        14,15
         */
    }



    @Test
    public void testOccurrenceExistingWordsInSmallWordsFileExceptSaltation() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                SMALL_WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("occurrence");
        words.add("change");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 7);
        String actual = studentHandler.handle(nq);
        String expected = "[alteration, change, increase, jump, leap, modification, transition]";
        assertThat(actual).isEqualTo(expected);

        /*

        1: occurence
           hyponyms:
              2: change alteration modification
              11: alteration modification adjustment

        2: change
           hyponyms:
              3: transition
                 hyponyms:
                     5: jump leap saltation
              4: increase
        8: change
           hyponyms:
              9: demotion
             10: variation


        occurence: happening occurrence occurrent natural_event
           hyponyms: 2: change alteration modification 11: alteration modification adjustment
        change: change alteration modification
         */


        //change alteration modification happening occurrence occurrent natural_event adjustment transition increase jump leap saltation
    }

    @Test
    public void testMulWordsNothingExists() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                VERY_SHORT_WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("occurrence");
        words.add("change");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 1);
        String actual = studentHandler.handle(nq);
        //String expected = "[alteration, change, increase, jump, leap, modification, saltation, transition]";
        String expected = "[]";
        assertThat(actual).isEqualTo(expected);
    }

   @Test
   public void testEmptyListofWordsK0() {
       NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(VERY_SHORT_WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
       List<String> words = new ArrayList<>();

       NgordnetQuery nq = new NgordnetQuery(words, 0,0,0);
       String actual = studentHandler.handle(nq);
       String expected = "[]";
       assertThat(actual).isEqualTo(expected);
   }

   @Test
   public void testNoHyponymsK0() {
       NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(VERY_SHORT_WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
       List<String> words = new ArrayList<>();
       words.add("привет");

       NgordnetQuery nq = new NgordnetQuery(words, 0,0,0);
       String actual = studentHandler.handle(nq);
       String expected = "[]";
       assertThat(actual).isEqualTo(expected);
   }

   @Test
   public void testUniqueHyponymsK0() {
       NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(VERY_SHORT_WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
       List<String> words = new ArrayList<>();
       words.add("airport");
       words.add("request");
       NgordnetQuery nq = new NgordnetQuery(words, 0,0,0);
       String actual = studentHandler.handle(nq);
       String expected = "[]";
       assertThat(actual).isEqualTo(expected);
   }

   @Test
   public void testAlteration() {
       NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(SMALL_WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
       List<String> words = new ArrayList<>();
       words.add("alteration");
       NgordnetQuery nq = new NgordnetQuery(words, 0,0,0);
       String actual = studentHandler.handle(nq);
       String expected = "[adjustment, alteration, change, conversion, increase, jump, leap, modification, mutation, saltation, transition]";
       assertThat(actual).isEqualTo(expected);
   }

    @Test
    public void testAlterationsWithYearsWithK4() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(SMALL_WORDS_FILE, TOTAL_COUNTS_FILE, PLURAL_SYNSETS_16, SMALL_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("alterations");
        NgordnetQuery nq = new NgordnetQuery(words, 1432,1472,4);
        String actual = studentHandler.handle(nq);
        String expected = "[alterations, changes, increases, modifications]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testAlterationsWithYearsWithK2() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(SMALL_WORDS_FILE, TOTAL_COUNTS_FILE, PLURAL_SYNSETS_16, SMALL_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("alterations");
        NgordnetQuery nq = new NgordnetQuery(words, 1432,1472,2);
        String actual = studentHandler.handle(nq);
        String expected = "[changes, increases]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testAlterationWithInvalidYears() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(SMALL_WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("alteration");
        NgordnetQuery nq = new NgordnetQuery(words, 1432,500,2);
        String actual = studentHandler.handle(nq);
        String expected = "[adjustment, alteration, change, conversion, increase, jump, leap, modification, mutation, saltation, transition]";
        assertThat(actual).isEqualTo(expected);
    }


   @Test
   public void testWithYears() {
       NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(SMALLEST, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
       List<String> words = new ArrayList<>();
       words.add("alteration");
       NgordnetQuery nq = new NgordnetQuery(words, 2000,2020,0);
       String actual = studentHandler.handle(nq);
       String expected = "[adjustment, alteration, change, conversion, increase, jump, leap, modification, mutation, saltation, transition]";
       assertThat(actual).isEqualTo(expected);

       // alteration modification adjustment change alteration modification conversion mutation transition increase jump leap saltation
   }

   @Test
   public void testFoodandCakeWithK5() {
       NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(SMALL_WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
       List<String> words = List.of("food", "cake");
       NgordnetQuery nq = new NgordnetQuery(words, 1950,1990,5);
       String actual = studentHandler.handle(nq);
       String expected = "[cake, cookie, kiss, snap, wafer]";
       assertThat(actual).isEqualTo(expected);
   }

    @Test
    public void testFoodandCakeWithK6() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(SMALL_WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("food", "cake");
        NgordnetQuery nq = new NgordnetQuery(words, 1950,1990,6);
        String actual = studentHandler.handle(nq);
        String expected = "[cake, cookie, kiss, snap, wafer]";
        assertThat(actual).isEqualTo(expected);
        //cake:    3712077
        // cookie: 414003
        // kiss:   2460959
        // snap:   1719097
        // wafer:  992834

    }

    @Test
    public void testFoodandCakeWithK4() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(SMALL_WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("food", "cake");
        NgordnetQuery nq = new NgordnetQuery(words, 1950,1990,4);
        String actual = studentHandler.handle(nq);
        String expected = "[cake, kiss, snap, wafer]";
        assertThat(actual).isEqualTo(expected);

        // cookie: 414003
        // kiss: 2460959
        // snap: 1719097
        // wafer: 992834
        // cake: 3712077


    }

    @Test
    public void testEECS() {
        //implement
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(FREQUENCY_EECS_FILE, TOTAL_COUNTS_FILE, SYNSETS_EECS_FILE, HYPONYMS_EECS_FILE);
        List<String> words = new ArrayList<>();
        words.add("CS61A");
        NgordnetQuery nq = new NgordnetQuery(words, 2010,2020,4);
        String actual = studentHandler.handle(nq);
        String expected = "[CS170, CS61A, CS61B, CS61C]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testEECSk3() {
        //implement
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(FREQUENCY_EECS_FILE, TOTAL_COUNTS_FILE, SYNSETS_EECS_FILE, HYPONYMS_EECS_FILE);
        List<String> words = new ArrayList<>();
        words.add("CS61A");
        NgordnetQuery nq = new NgordnetQuery(words, 2010,2020,3);
        String actual = studentHandler.handle(nq);
        String expected = "[CS170, CS61A, CS61B]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testEECSK0() {
        //implement
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(FREQUENCY_EECS_FILE, TOTAL_COUNTS_FILE, SYNSETS_EECS_FILE, HYPONYMS_EECS_FILE);
        List<String> words = new ArrayList<>();
        words.add("CS70");
        words.add("CS61C");
        NgordnetQuery nq = new NgordnetQuery(words, 2010,2020,0);
        String actual = studentHandler.handle(nq);
        String expected = "[CS161, CS162, bean]";
        assertThat(actual).isEqualTo(expected);
    }

}