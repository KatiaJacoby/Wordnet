import java.util.List;
import java.util.ArrayList;
public class ListExercises {

    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        int sum = 0;
        for (int i: L) {
            sum+=i;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> evenNumbers = new ArrayList<>();
        for (int i = 0; i < L.size(); i++) {
            if (L.get(i) % 2 == 0) {
                evenNumbers.add(L.get(i));
            }
        }
        return evenNumbers;
    }


    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> commons = new ArrayList<>();
        for (int i: L1){
            for (int k: L2){
                if (i == k){
                    commons.add(i);
                }
            }
        }
        return commons;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int sum = 0;
        for (String i: words){
            for (int k = 0; k < i.length(); k++){
                if (c == i.charAt(k)){
                    sum++;

                }
            }
        }
        return sum;
    }
}
