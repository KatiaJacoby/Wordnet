import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
         lld1.removeLast();
         lld1.removeLast();
         lld1.removeLast();
         lld1.removeLast();
         lld1.removeLast();
         lld1.addFirst(1);
         assertThat(lld1.toList()).containsExactly(1);
         lld1.removeLast();
         lld1.addLast(1);
         assertThat(lld1.toList()).containsExactly(1);
         Deque61B<Integer> lld2 = new LinkedListDeque61B<>();
         lld2.addFirst(1);
         assertThat(lld2.toList()).containsExactly(1);
         Deque61B<Integer> lld3 = new LinkedListDeque61B<>();
         lld3.addLast(1);
         assertThat(lld3.toList()).containsExactly(1);

     }


     @Test
     public void checkifEmptyTest(){
         Deque61B<Integer> list = new LinkedListDeque61B();
         boolean condition = list.isEmpty();
         assert condition;
         list.addLast(1);
         condition = list.isEmpty();
         assert !condition;
     }

     @Test
    public void findSizeTest(){
         Deque61B<Integer> list = new LinkedListDeque61B();
         list.addLast(3);
         list.addLast(4);
         list.addFirst(2);
         list.addFirst(1);
         assertThat(list.size()).isEqualTo(4);
         list.removeLast();
         list.removeLast();
         list.removeLast();
         list.removeLast();
         assertThat(list.size()).isEqualTo(0);
         list.removeLast();
         assertThat(list.size()).isEqualTo(0);
     }

     @Test
     public void checkGetTest(){
         Deque61B<Integer> list = new LinkedListDeque61B();
         list.addLast(3);
         list.addLast(4);
         list.addFirst(2);
         list.addFirst(1);
         assertThat(list.get(2)).isEqualTo(3);
         assertThat(list.get(0)).isEqualTo(1);
         assertThat(list.get(4)).isEqualTo(null);
         assertThat(list.get(-1)).isEqualTo(null);
     }

    @Test
    public void checkGetRecursiveTest(){
        Deque61B<Integer> list = new LinkedListDeque61B();
        list.addLast(3);
        list.addLast(4);
        list.addFirst(2);
        list.addFirst(1);
        assertThat(list.getRecursive(2)).isEqualTo(3);
        assertThat(list.getRecursive(0)).isEqualTo(1);
        assertThat(list.getRecursive(4)).isEqualTo(null);
        assertThat(list.getRecursive(-1)).isEqualTo(null);
    }

    @Test
    public void removeFirstTestandRemoveLastTest(){
        Deque61B<Integer> list = new LinkedListDeque61B();
        list.addLast(3);
        list.addLast(4);
        assertThat(list.removeFirst()).isEqualTo(3);
        assertThat(list.toList()).containsExactly(4);
        Deque61B<Integer> list2 = new LinkedListDeque61B();
        assertThat(list2.removeFirst()).isEqualTo(null);
        list2.addLast(1);
        list2.addLast(2);
        list2.addLast(3);
        list2.removeLast();
        list2.removeLast();
        list2.removeLast();
        assertThat(list2.toList()).isEmpty();
        list2.addLast(1);
        list2.addLast(2);
        list2.addLast(3);
        list2.removeFirst();
        list2.removeFirst();
        list2.removeFirst();
        assertThat(list2.toList()).isEmpty();
    }

    @Test
    public void removeLastTest(){
        Deque61B<Integer> list = new LinkedListDeque61B();
        list.addLast(3);
        list.addLast(4);
        assertThat(list.removeLast()).isEqualTo(4);
        assertThat(list.toList()).containsExactly(3);
        Deque61B<Integer> list2 = new LinkedListDeque61B();
        assertThat(list2.removeLast()).isEqualTo(null);
        list2.addLast(1);
        list2.addLast(2);
        list2.addLast(3);
        list2.addLast(4);
        list2.removeLast();
        list2.removeLast();
        assertThat(list2.toList()).containsExactly(1,2).inOrder();
        list2.removeLast();
        assertThat(list2.toList()).containsExactly(1);
        list2.addLast(2);
        list2.removeFirst();
        assertThat(list2.toList()).containsExactly(2);
    }

    @Test
    public void toListTest(){
        Deque61B<Integer> list = new LinkedListDeque61B();
        list.addLast(3);
        list.addLast(4);
        assertThat(list.toList()).containsExactly(3,4);
        list.removeFirst();
        list.removeLast();
        assertThat(list.toList()).isEmpty();

    }


}