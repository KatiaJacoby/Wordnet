import deque.ArrayDeque61B;

import deque.Deque61B;
import deque.LinkedListDeque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.Assert.assertThrows;

import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }
//

    @Test
    public void addFirstTest() {
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        numbers.addFirst(2);
        assertThat(numbers.get(0)).isEqualTo(2);
        numbers.addFirst(3);
        numbers.addFirst(1);
        assertThat(numbers.get(0)).isEqualTo(1);
        assertThat(numbers.get(1)).isEqualTo(3);
        assertThat(numbers.get(2)).isEqualTo(2);
        numbers.addFirst(5);
        assertThat(numbers.get(0)).isEqualTo(5);
        numbers.addFirst(6);
        assertThat(numbers.get(0)).isEqualTo(6);
        numbers.addFirst(7);
        assertThat(numbers.get(0)).isEqualTo(7);

        Deque61B<String> happy = new ArrayDeque61B<>();
        happy.addFirst("yay");
        happy.addFirst("slay");
        happy.addFirst("hooray");
        happy.addFirst("partay");
        happy.addFirst("hehehehe");
        happy.addLast("everything sucks");
        happy.addLast("just kidding");
        happy.addLast("I love my life");
        happy.addLast("lol");
        happy.addLast("cats");
        happy.addLast("whathesigma") ;
        assertThat(happy.get(10)).isEqualTo("whathesigma");

    }

    @Test
    public void addLastTest() {
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        numbers.addLast(2);
        numbers.addLast(3);
        numbers.addLast(1);
        assertThat(numbers.get(0)).isEqualTo(2);
        assertThat(numbers.get(1)).isEqualTo(3);
        assertThat(numbers.get(2)).isEqualTo(1);
        numbers.addLast(6);
        assertThat(numbers.get(3)).isEqualTo(6);
        numbers.addLast(7);
        assertThat(numbers.get(4)).isEqualTo(7);
        numbers.addLast(8);
        assertThat(numbers.get(5)).isEqualTo(8);
        numbers.addLast(9);
        numbers.addLast(10);
        numbers.addLast(11);
        numbers.addLast(12);
        numbers.addLast(13);
        assertThat(numbers.get(8)).isEqualTo(11);
        assertThat(numbers.get(9)).isEqualTo(12);
        assertThat(numbers.get(10)).isEqualTo(13);

    }

    @Test
    public void isEmptyTest() {
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        boolean condition = numbers.isEmpty();
        assert condition;
        numbers.addLast(1);
        numbers.addLast(2);
        condition = numbers.isEmpty();
        assert !condition;
        numbers.removeLast();
        condition = numbers.isEmpty();
        assert !condition;
        numbers.removeLast();
        condition = numbers.isEmpty();
        assert condition;
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        condition = numbers.isEmpty();
        assert !condition;
        numbers.removeLast();
        numbers.removeLast();
        numbers.removeFirst();
        numbers.removeLast();
        numbers.removeFirst();
        condition = numbers.isEmpty();
        assert !condition;
        numbers.removeLast();
        numbers.removeLast();
        numbers.removeFirst();
        numbers.removeLast();
        numbers.removeFirst();
        numbers.removeFirst();
        numbers.removeLast();
        condition = numbers.isEmpty();
        assert condition;

    }

    @Test
    public void checkSizeTest() {
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        assertThat(numbers.size()).isEqualTo(0);
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        assertThat(numbers.size()).isEqualTo(3);
        numbers.addFirst(0);
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        assertThat(numbers.size()).isEqualTo(10);
        numbers.removeLast();
        numbers.removeFirst();
        numbers.removeLast();
        assertThat(numbers.size()).isEqualTo(7);
        numbers.removeLast();
        numbers.removeLast();
        numbers.removeFirst();
        numbers.removeLast();
        numbers.removeFirst();
        assertThat(numbers.size()).isEqualTo(2);
        numbers.removeLast();
        numbers.removeFirst();
        assertThat(numbers.size()).isEqualTo(0);
    }

    @Test
    public void checkGetTest() {
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        assertThat(numbers.get(1)).isEqualTo(2);
        assertThat(numbers.get(2)).isEqualTo(3);
        assertThat(numbers.get(0)).isEqualTo(1);
        assertThat(numbers.get(3)).isEqualTo(null);
        assertThat(numbers.get(-1)).isEqualTo(null);
        numbers.removeLast();
        assertThat(numbers.get(2)).isEqualTo(null);
    }

    @Test
    public void toListTest() {
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        assertThat(numbers.toList()).isEmpty();
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        assertThat(numbers.toList()).containsExactly(1, 2, 3);
        numbers.addFirst(2);
        numbers.addFirst(1);
        assertThat(numbers.toList()).containsExactly(1, 2, 1, 2, 3);
        numbers.removeFirst();
        numbers.removeFirst();
        numbers.addLast(1);
        numbers.addLast(1);
        assertThat(numbers.toList()).containsExactly(1, 2, 3, 1, 1);
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(1);
        numbers.addLast(1);
        numbers.addLast(1);
        numbers.addFirst(6);
        assertThat(numbers.toList()).containsExactly(6,1,2,3,1,1,1,2,1,1,1);
        numbers.removeFirst();
        numbers.removeLast();
        numbers.removeFirst();
        numbers.removeLast();
        numbers.removeFirst();
        numbers.removeLast();
        numbers.removeFirst();
        numbers.removeLast();
        numbers.removeFirst();
        numbers.removeLast();
        numbers.removeFirst();
        assertThat(numbers.toList()).isEmpty();
    }

    @Test
    public void removeFirstTest() {
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        assertThat(numbers.removeFirst()).isEqualTo(null);
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        assertThat(numbers.removeFirst()).isEqualTo(1);
        assertThat(numbers.toList()).containsExactly(2, 3);
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        numbers.addLast(3);
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(2);
        numbers.addLast(6);
        numbers.removeFirst();
        numbers.removeFirst();
        numbers.removeFirst();
        numbers.removeFirst();
        numbers.removeFirst();
        numbers.removeFirst();
        assertThat(numbers.removeFirst()).isEqualTo(1);
        assertThat(numbers.toList()).containsExactly(2,2,6);

    }

    @Test
    public void removeLastTest() {
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        assertThat(numbers.removeLast()).isEqualTo(null);
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        assertThat(numbers.removeLast()).isEqualTo(3);
        assertThat(numbers.toList()).containsExactly(1, 2);
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        numbers.addLast(3);
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(2);
        numbers.addLast(6);
        numbers.removeLast();
        numbers.removeLast();
        numbers.removeLast();
        numbers.removeLast();
        numbers.removeLast();
        numbers.removeLast();
        assertThat(numbers.removeLast()).isEqualTo(2);
        assertThat(numbers.toList()).containsExactly(1,2,1);
    }

    @Test
    public void recursiveTest() {
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        //@source I asked chatGPT how to write a test case for a method that throws an exception
        assertThrows(UnsupportedOperationException.class, () -> numbers.getRecursive(1));
    }

    @Test
    public void resizeTest() {
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        assertThat(numbers.toList()).isEmpty();
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        numbers.addLast(1);
        numbers.addFirst(2);
        assertThat(numbers.toList()).containsExactly(2,1,2,3,1,2,3,1);
        assertThat(numbers.size()).isEqualTo(8);
        numbers.addLast(3);
        assertThat(numbers.toList()).containsExactly(2, 1, 2, 3, 1, 2, 3, 1, 3);
        assertThat(numbers.size()).isEqualTo(9);
        numbers.removeLast();
        numbers.removeFirst();
        numbers.removeLast();
        numbers.removeLast();
        numbers.removeLast();
        assertThat(numbers.size()).isEqualTo(4);
        numbers.removeLast();
        assertThat(numbers.size()).isEqualTo(3);
        numbers.removeLast();
        assertThat(numbers.size()).isEqualTo(2);
    }

    @Test
    public void resizeDownTest() {
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        numbers.addLast(2);
        numbers.addLast(3);
        numbers.addLast(4);
        numbers.addLast(5);
        numbers.addLast(6);
        numbers.addLast(7);
        numbers.addLast(8);
        numbers.addLast(9);
        numbers.addLast(10);
        numbers.addFirst(1);
        numbers.removeLast();
        numbers.removeFirst();
        numbers.removeLast();
        numbers.removeLast();
        numbers.removeLast();
        numbers.removeFirst();
        numbers.removeLast();
        assertThat(numbers.toList()).containsExactly(3, 4, 5);
    }

    @Test
    public void iteratorArrayTest() {
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        numbers.addLast(2);
        numbers.addLast(3);
        numbers.addLast(4);
        numbers.addLast(5);
        Iterator<Integer> seer = numbers.iterator();
        assertThat(seer.next()).isEqualTo(2);
        boolean condition = seer.hasNext();
        assert condition;
        assertThat(seer.next()).isEqualTo(3);
        seer.next();
        seer.next();
        condition = seer.hasNext();
        assert !condition;
        assertThat(seer.next()).isEqualTo(null);
    }

    @Test
    public void iteratorLinkedListTest() {
        Deque61B<Integer> numbers = new LinkedListDeque61B<>();
        numbers.addFirst(1);
        numbers.addLast(2);
        numbers.addFirst(0);
        Iterator<Integer> seer = numbers.iterator();
        assertThat(seer.next()).isEqualTo(0);
        boolean condition = seer.hasNext();
        assert condition;
        seer.next();
        seer.next();
        condition = seer.hasNext();
        assert !condition;
        assertThat(seer.next()).isEqualTo(null);
    }

    @Test
    public void arrayEqualsTest(){
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<String> lld2 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
        lld2.removeLast();
        assertThat(lld1).isNotEqualTo(lld2);

        Deque61B<Integer> lld3 = new ArrayDeque61B<>();
        Deque61B<Integer> lld4 = new ArrayDeque61B<>();

        lld3.addLast(1);
        lld3.addLast(2);
        lld3.addLast(3);

        lld4.addLast(1);
        lld4.addLast(2);
        lld4.addLast(2);

        assertThat(lld3).isNotEqualTo(lld4);

        Deque61B<Integer> lld5 = new ArrayDeque61B<>();
        Deque61B<Integer> lld6 = new ArrayDeque61B<>();
        assertThat(lld5).isNotEqualTo(lld6);
    }

    @Test
    public void toStringArrayTest(){
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        Deque61B<Integer> lld4 = new ArrayDeque61B<>();

        lld4.addLast(1);
        lld4.addLast(2);
        lld4.addLast(3);

        assertThat(lld1.toString()).isEqualTo("[front, middle, back]");
        assertThat(lld4.toString()).isEqualTo("[1, 2, 3]");
    }

    @Test
    public void toStringLinkedListTest(){
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        Deque61B<Integer> lld4 = new LinkedListDeque61B<>();

        lld4.addLast(1);
        lld4.addLast(2);
        lld4.addLast(3);

        assertThat(lld1.toString()).isEqualTo("[front, middle, back]");
        assertThat(lld4.toString()).isEqualTo("[1, 2, 3]");
    }


}

