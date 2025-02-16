package deque;

import java.util.List;
import java.lang.Math;
import java.util.ArrayList;

public class ArrayDeque61B<T> implements Deque61B<T>{
    private T[] items;
    private int size;
    private int capacity;
    private int firstIndex;
    private int lastIndex;

    public ArrayDeque61B() {
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.capacity = 8;
        this.firstIndex = 3;
        this.lastIndex = 4;
    }

    @Override
    public void addFirst(T x) {
        // check if the array is maxed out --> call resize if it is
        // check if you need to loop around: do this by checking where the first and last pointers are

        items[firstIndex] = x;
        firstIndex = Math.floorMod(firstIndex-1,capacity);
        size++;
        if (size == capacity){
            // call resize function
        }
    }


    @Override
    public void addLast(T x) {
        items[lastIndex] = x;
        lastIndex = Math.floorMod(lastIndex+1,capacity);
        size++;
        if (size == capacity){
            // call resize function
        }
    }


    @Override
    public List<T> toList() {
       List<T> returnList = new ArrayList<>();
       int start = Math.floorMod(firstIndex+1,capacity);
       for (int i = start; i < start + size; i++){
           returnList.add(items[i]);
       }
       return returnList;
    }

    @Override
    public boolean isEmpty() {
        return(size==0);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        if (size == 0){
            return null;
        }
        int index = Math.floorMod(firstIndex+1, capacity);
        T returnVal = items[index];
        items[index] = null;
        firstIndex = index;
        this.size--;
        return returnVal;
    }

    @Override
    public T removeLast() {
        if (size == 0){
            return null;
        }
        int index = Math.floorMod(lastIndex-1,capacity);
        T returnVal = items[index];
        items[index] = null;
        lastIndex = index;
        this.size--;
        return returnVal;
    }

    @Override
    public T get(int index) {
        if (index > size-1 || index < 0){
            return null;
        }


        int num = Math.floorMod(firstIndex+1+index,capacity);
        T value = items[num];
        return value;
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
