package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    private int M;
    private double loadFactor;
    private Collection<Node>[] buckets;
    private int N;

    /**
     * Constructors
     */
    public MyHashMap() {
        initialize(16, 0.75);

    }

    public MyHashMap(int initialCapacity) {
        initialize(initialCapacity, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor      maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        initialize(initialCapacity, loadFactor);
    }

    private void initialize(int initialCapacity, double loadFactor) {
        this.M = initialCapacity;
        this.loadFactor = loadFactor;
        this.buckets = new Collection[this.M];
        this.N = 0;

    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * Note that that this is referring to the hash table bucket itself,
     * not the hash map itself.
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }


    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        resize();
        Node sheep = new Node(key, value);
        if(putHelper(sheep, this.buckets)){
            N++;
        };


    }


    private void resize() {
        double value = ((double) N/M);
        if (value >= loadFactor) {
            Collection<Node>[] newBuckets = new Collection[this.M * 2];
            for (int i = 0; i < M; i++) {
                if (this.buckets[i] != null) {
                    Iterator<Node> liliterator = this.buckets[i].iterator();
                    while (liliterator.hasNext()) {
                        putHelper(liliterator.next(), newBuckets);
                    }
                }
            }
            this.buckets = newBuckets;
            M = M*2;
        }

    }

    private boolean putHelper(Node blep, Collection<Node>[] buckets) {
        int index = Math.floorMod(blep.key.hashCode(), M);
        if (buckets[index] == null) {
            buckets[index] = createBucket();
            buckets[index].add(blep);
            return (true);
        }

        Iterator<Node> myIterator = buckets[index].iterator();
        while (myIterator.hasNext()) {
            Node curr = myIterator.next();
            if (curr.key.equals(blep.key)) {
                curr.value = blep.value;
                return(false);
            }
        }

        buckets[index].add(blep);
        return(true);
    }

    private Node finderHelper(K key){
        int index = Math.floorMod(key.hashCode(), M);
        if (buckets[index] == null) {
            return null;
        }
        Iterator<Node> myIterator = buckets[index].iterator();
        while (myIterator.hasNext()) {
            Node curr = myIterator.next();
            if (curr.key.equals(key)) {
                return curr;
            }
        }
        return null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        Node curr = finderHelper(key);
        if (curr == null) {
            return null;
        }
            return curr.value;
    }


    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        if (finderHelper(key) == null) {
            return false;
        }
        return true;
    }



    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return N;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        this.buckets = new Collection[this.M];
        this.N = 0;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for this lab.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

}
