import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    Node sentinel;
    public LinkedListDeque61B() {
        this.sentinel = new Node(null);
    }

    class Node{
        T value;
        Node prev;
        Node next;

        Node(T val){
            this.value = val;
            this.prev = this;
            this.next = this;
        }
    }


    @Override
    public void addFirst(T x) {
        Node add = new Node(x);
        add.next = sentinel.next;
        if (sentinel.prev == sentinel){
            sentinel.prev = add;
        }
        this.sentinel.next = add;
        add.prev = this.sentinel;
    }

    @Override
    public void addLast(T x) {
        Node add = new Node(x);
        sentinel.prev.next = add;
        add.prev = sentinel.prev;
        sentinel.prev = add;
        add.next = sentinel;
    }

    @Override
    public List<T> toList() {
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
