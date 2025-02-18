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

    }

    @Test
    public void isEmptyTest() {
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        boolean condition = numbers.isEmpty();
        assert condition;
        numbers.addLast(1);
        numbers.addLast(2);
        boolean condition2 = numbers.isEmpty();
        assert !condition2;
    }

    @Test
    public void checkSizeTest() {
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        assertThat(numbers.size()).isEqualTo(0);
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        assertThat(numbers.size()).isEqualTo(3);
    }

    @Test
    public void checkGetTest() {
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        assertThat(numbers.get(1)).isEqualTo(2);
        assertThat(numbers.get(3)).isEqualTo(null);
        assertThat(numbers.get(-1)).isEqualTo(null);
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
    }

    @Test
    public void recursiveTest() {
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        //@source I asked chatGPT how to test a method that throws an exception
        assertThrows(UnsupportedOperationException.class, () -> numbers.getRecursive(1));
    }

    @Test
    public void resizeTest() {
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        assertThat(numbers.toList()).containsExactly(1, 2, 3, 1, 2, 3, 1, 2, 3);
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
    }
}

