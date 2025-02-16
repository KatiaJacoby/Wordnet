import deque.ArrayDeque61B;

import deque.Deque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

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
    public void addFirstTest(){
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        numbers.addFirst(2);
        numbers.addFirst(3);
        numbers.addFirst(1);
        assertThat(numbers.get(3)).isEqualTo(2);
        assertThat(numbers.get(2)).isEqualTo(3);
        assertThat(numbers.get(1)).isEqualTo(1);
        numbers.addFirst(5);
        assertThat(numbers.get(0)).isEqualTo(5);
        numbers.addFirst(6);
        assertThat(numbers.get(7)).isEqualTo(6);
        numbers.addFirst(7);
        assertThat(numbers.get(6)).isEqualTo(7);
    }

    @Test
    public void addLastTest(){
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        numbers.addLast(2);
        numbers.addLast(3);
        numbers.addLast(1);
        assertThat(numbers.get(3)).isEqualTo(2);
        assertThat(numbers.get(4)).isEqualTo(3);
        assertThat(numbers.get(5)).isEqualTo(1);
        numbers.addLast(6);
        numbers.addLast(7);
        assertThat(numbers.get(7)).isEqualTo(7);
        numbers.addLast(8);
        assertThat(numbers.get(0)).isEqualTo(8);
        numbers.addLast(9);
        assertThat(numbers.get(1)).isEqualTo(9);
    }

    @Test
    public void isEmptyTest(){
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        boolean condition = numbers.isEmpty();
        assert condition;
        numbers.addLast(1);
        numbers.addLast(2);
        boolean condition2 = numbers.isEmpty();
        assert !condition2;
    }

    @Test
    public void checkSizeTest(){
        Deque61B<Integer> numbers = new ArrayDeque61B<>();
        assertThat(numbers.size()).isEqualTo(0);
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        assertThat(numbers.size()).isEqualTo(3);
    }
}

