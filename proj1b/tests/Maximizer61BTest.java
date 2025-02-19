import deque.Maximizer61B;
import deque.ArrayDeque61B;

import org.junit.jupiter.api.*;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class Maximizer61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void basicTest() {
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();
        ad.addFirst("");
        ad.addFirst("2");
        ad.addFirst("fury road");
        assertThat(Maximizer61B.max(ad, new StringLengthComparator())).isEqualTo("fury road");

        ArrayDeque61B<Integer> ad2 = new ArrayDeque61B<>();
        assertThat(Maximizer61B.max(ad2)).isEqualTo(null);
        ad2.addFirst(1);
        ad2.addFirst(0);
        ad2.addLast(2);
        assertThat(Maximizer61B.max(ad2)).isEqualTo(2);

    }

    @Test
    public void comparableTest(){
        ArrayDeque61B<Integer> numbers = new ArrayDeque61B<>();
        numbers.addFirst(1);
        numbers.addLast(2);
        numbers.addLast(3);
        assertThat(Maximizer61B.max(numbers)).isEqualTo(3);
    }

    public class integerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b){
            return (a-b);
        }

    }

    @Test
    public void comparatorTest(){
        ArrayDeque61B<Integer> ad2 = new ArrayDeque61B<>();
        ad2.addFirst(1);
        ad2.addFirst(0);
        ad2.addLast(2);
        assertThat(Maximizer61B.max(ad2, new integerComparator())).isEqualTo(2);
    }



}
