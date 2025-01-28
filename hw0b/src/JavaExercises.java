import java.util.ArrayList;
import java.util.List;


public class JavaExercises {

    /**
     * Returns an array [1, 2, 3, 4, 5, 6]
     */
    public static int[] makeDice() {
        int[] anArray = {1, 2, 3, 4, 5, 6};
        return anArray;
    }

    /**
     * Returns the order depending on the customer.
     * If the customer is Ergun, return ["beyti", "pizza", "hamburger", "tea"].
     * If the customer is Erik, return ["sushi", "pasta", "avocado", "coffee"].
     * In any other case, return an empty String[] of size 3.
     */
    public static String[] takeOrder(String customer) {
        String[] orders = new String[4];
        String[] nothing = new String[3];

        if (customer.equals("Ergun")) {
            orders[0] = "beyti";
            orders[1] = "pizza";
            orders[2] = "hamburger";
            orders[3] = "tea";
        } else if (customer.equals("Erik")) {
            orders[0] = "sushi";
            orders[1] = "pasta";
            orders[2] = "avocado";
            orders[3] = "coffee";
        } else {
            return nothing;
        }
        return orders;
    }

    /**
     * Returns the positive difference between the maximum element and minimum element of the given array.
     * Assumes array is nonempty.
     */
    public static int findMinMax(int[] array) {
        int minimum = array[0];
        int maximum = array[0];
        for (int i : array) {
            if (minimum > i) {
                minimum = i;
            }
            if (maximum < i) {
                maximum = i;
            }
        }
        return (maximum - minimum);

    }

    /**
     * Uses recursion to compute the hailstone sequence as a list of integers starting from an input number n.
     * Hailstone sequence is described as:
     * - Pick a positive integer n as the start
     * - If n is even, divide n by 2
     * - If n is odd, multiply n by 3 and add 1
     * - Continue this process until n is 1
     */
    public static List<Integer> hailstone(int n) {
        return hailstoneHelper(n, new ArrayList<>());

    }

    private static List<Integer> hailstoneHelper(int x, List<Integer> list) {

        list.add(x);
        if (x == 1) {
            return list;
        }

        if (x % 2 == 0) {
            return (hailstoneHelper((x / 2), list));
        } else {
            return (hailstoneHelper((3 * x + 1), list));
        }
    }
}




