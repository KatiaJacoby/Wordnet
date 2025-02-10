import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private class Node {
        T value;
        Node prev;
        Node next;

        Node(T val) {
            this.value = val;
            this.prev = this;
            this.next = this;
        }
    }

    private Node sentinel;
    private int size = 0;

    public LinkedListDeque61B() {
        this.sentinel = new Node(null);
    }

    @Override
    public void addFirst(T x) {
        Node add = new Node(x);
        add.next = this.sentinel.next;
        this.sentinel.next.prev = add;
        this.sentinel.next = add;
        add.prev = this.sentinel;
        size++;

    }

    @Override
    public void addLast(T x) {
        Node add = new Node(x);
        this.sentinel.prev.next = add;
        add.prev = this.sentinel.prev;
        this.sentinel.prev = add;
        add.next = this.sentinel;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node pointer = this.sentinel.next;
        while (pointer != this.sentinel) {
            returnList.add(pointer.value);
            pointer = pointer.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        Node pointer = this.sentinel;
        if (pointer.next == pointer) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        T element = this.sentinel.next.value;
        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev.next = this.sentinel.next.prev;
        this.sentinel.next.prev.prev = this.sentinel.next.prev;
        this.sentinel.next.prev = this.sentinel;
        if (size > 0) {
            this.size--;
        }
        return element;

    }

    @Override
    public T removeLast() {
        T element = this.sentinel.prev.value;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next.prev = this.sentinel.prev.next;
        this.sentinel.prev.next.next = this.sentinel.prev.next;
        this.sentinel.prev.next = this.sentinel;
        if (size > 0) {
            this.size--;
        }
        return element;

    }

    @Override
    public T get(int index) {
        Node pointer = this.sentinel;
        int i = 0;
        if (index > this.size() - 1 || index < 0) {
            return null;
        }

        while (pointer.next != this.sentinel && i < index) {
            pointer = pointer.next;
            i++;
        }

        return (pointer.next.value);
    }

    @Override
    public T getRecursive(int index) {
        Node pointer = this.sentinel;
        if (index > this.size() - 1) {
            return null;
        }
        return helper(pointer.next, index);

    }


    private T helper(Node pointer, int index) {
        if (index == 0) {
            return pointer.value;
        }

        return helper(pointer.next, index - 1);
    }
}


