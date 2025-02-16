package deque;

import java.util.List;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T>{
    private T[] items;
    private int size;
    private int capacity;
    private int firstIndex;
    private int lastIndex;
    private boolean empty;

    public ArrayDeque61B() {
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.capacity = 8;
        this.firstIndex = 3;
        this.lastIndex = 3;
        this.empty = true;
    }

    @Override
    public void addFirst(T x) {
        // check if the array is maxed out --> call resize if it is
        // check if you need to loop around: do this by checking where the first and last pointers are

        if (size == capacity){
            // call resize function
        }

        if (empty){
            items[firstIndex] = x;
            empty = false;
            size++;
        } else if (firstIndex > 0){
            items[firstIndex-1] = x;
            firstIndex--;
            size++;
        } else if (firstIndex == 0){
            items[capacity-1] = x;
            firstIndex = capacity-1;
            size++;
        }
    }

    @Override
    public void addLast(T x) {
        if (size == capacity){
            // call resize function
        }

        if (empty){
            items[lastIndex] = x;
            empty = false;
            size++;
        } else if (lastIndex < capacity - 1){
            items[lastIndex+1] = x;
            lastIndex++;
            size++;
        } else if (lastIndex == capacity - 1){
            items[0] = x;
            lastIndex = 0;
            size++;
        }
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayDeque61B<>();
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return(empty);
    }

    @Override
    public int size() {
        return this.size;
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
        return this.items[index];
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
