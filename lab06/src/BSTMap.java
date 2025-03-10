import java.util.Iterator;
import java.util.Set;

public class BSTMap <K extends Comparable<K>,V> implements Map61B<K,V> {
    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */

    private class Node {
        K key;
        V val;
        Node child1;
        Node child2;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.child1 = null;
            this.child2 = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap(){
        this.root = null;
        this.size = 0;
    }

    private Node insert(Node curr, K key, V val){
        //Node result = search(curr,key);

        if (curr == null) {
            this.size +=1;
            return new Node(key, val);

        }
        if (curr.key.compareTo(key) == 0){
            curr.val = val;
            return curr;
        }
        if (curr.key.compareTo(key) > 0) {
            curr.child1 = insert(curr.child1, key,val);
        } else {
            curr.child2 = insert(curr.child2,key, val);
        }
        return curr;
    }

    private Node search(Node curr, K key) {
        if (curr == null) {
            return null;
        }

        if (curr.key.compareTo(key) == 0){
            return curr;
        }

        if (curr.key.compareTo(key) > 0) {
            return search(curr.child1, key);
        } else {
            return search(curr.child2, key);
        }
    }

    @Override
    public void put(K key, V value) {
        if (key != null) {
            this.root = insert(this.root, key, value);
        }



    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        Node result = search(this.root, key);
        if (result == null){
            return null;
        } else {
            return result.val;
        }
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        Node result = search(this.root, key);
        if (result == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        return null;
    }
}
