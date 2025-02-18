package deque;

import java.util.Iterator;
import java.util.List;
import java.lang.Math;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ArrayDeque61B<T> implements Deque61B<T> {
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
        firstIndex = Math.floorMod(firstIndex - 1, capacity);
        size++;
        resize();
    }


    @Override
    public void addLast(T x) {
        items[lastIndex] = x;
        lastIndex = Math.floorMod(lastIndex + 1, capacity);
        size++;
        resize();
    }


    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int start = Math.floorMod(firstIndex + 1, capacity);
        for (int i = start; i < start + size; i++) {
            returnList.add(items[Math.floorMod(i, capacity)]);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int index = Math.floorMod(firstIndex + 1, capacity);
        T returnVal = items[index];
        items[index] = null;
        firstIndex = index;
        this.size--;
        resize();
        return returnVal;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int index = Math.floorMod(lastIndex - 1, capacity);
        T returnVal = items[index];
        items[index] = null;
        lastIndex = index;
        this.size--;
        resize();
        return returnVal;
    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }

        int num = Math.floorMod(firstIndex + 1 + index, capacity);
        T value = items[num];
        return value;
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    public void resize() {
        double rFactor = 1;
        if (size >= 8) {
            double val = (double) size / items.length;
            if (val < 0.25) {
                rFactor = 0.5;
            }
            if (size == capacity) {
                rFactor = 2;
            }
        }

        if (rFactor != 1) {
            int newSize = (int) (rFactor * capacity);
            T[] newItems = (T[]) new Object[newSize];
            int start = Math.floorMod(firstIndex + 1, capacity);
            int index = 0;
            this.firstIndex = newSize - 1;
            for (int i = start; index < size; i++) {
                newItems[index] = items[Math.floorMod(i, capacity)];
                index++;
            }
            this.lastIndex = size;
            this.items = newItems;
            this.capacity = items.length;

        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque61BIterator();
    }

    private class ArrayDeque61BIterator implements Iterator<T> {
        int index;

        public ArrayDeque61BIterator() {
            index = Math.floorMod(firstIndex, capacity);

        }

        public boolean hasNext() {
            if (items[Math.floorMod(index + 1, capacity)] == null) {
                return false;
            } else {
                return true;
            }
        }

        public T next() {
            if (hasNext()) {
                index = Math.floorMod(index + 1, capacity);
                return items[index];
            } else {
                throw new NoSuchElementException();
            }

        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Iterable<?>) {
            Iterator<T> iterobj = obj.iterator();
            Iterator<T> iterself = this.iterator();
            if (this.size == obj.size) {
                while (iterobj.hasNext()) {
                    if (iterobj.next() != iterself.next()) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }


}

