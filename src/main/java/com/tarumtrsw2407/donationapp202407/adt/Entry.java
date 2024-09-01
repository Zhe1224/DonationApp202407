package com.tarumtrsw2407.donationapp202407.adt;

import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author Wong Xiao Zhe
 * @param <T> type of key
 * @param <U> type of value/data associated with the key
 */
public class Entry<K, V> {
    private K key;
    private V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public void setKey(K key) { this.key = key; }
    public V getValue() { return value; }
    public void setValue(V value) { this.value = value; }

    public static <K, V> ListInterface<Entry<K, V>> build(ListInterface<K> keys, ListInterface<V> values) {
        if (keys.size() != values.size()) {
            throw new IllegalArgumentException("Keys and values lists must have the same length.");
        }
        ListInterface<Entry<K, V>> entries = new ArrayList<>(keys.size());
        Iterator<K> keyIterator = keys.iterator();
        Iterator<V> valueIterator = values.iterator();
        while (keyIterator.hasNext() && valueIterator.hasNext()) {
            entries.append(new Entry<>(keyIterator.next(), valueIterator.next()));
        }
        return entries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry<?, ?> entry = (Entry<?, ?>) o;
        return Objects.equals(key, entry.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "(" + key + "," + value + ")";
    }
}
