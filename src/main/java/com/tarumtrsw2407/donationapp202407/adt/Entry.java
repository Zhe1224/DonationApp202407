package com.tarumtrsw2407.donationapp202407.adt;

import java.util.Iterator;

/**
 *
 * @author Zhe1224
 * @param <T> type of key
 * @param <U> type of value/data associated with the key
 */
public class Entry<T,U>{
    public T key;
    public U value;
    public Entry(T key, U value) {
        this.key = key;
        this.value = value;
    }
    public ListInterface<Entry<T, U>> build(ListInterface<T> keys,ListInterface<U> values){
        if (keys.size() != values.size()) throw new IllegalArgumentException("Keys and values arrays must have the same length.");
        ListInterface<Entry<T, U>> entries = new ArrayList<>(keys.size());
        Iterator<T> K=keys.getIterator();Iterator<U> V=values.getIterator();
        while (K.hasNext()&&V.hasNext())entries.append(new Entry(K.next(),V.next()));
        return entries;
    }
}
