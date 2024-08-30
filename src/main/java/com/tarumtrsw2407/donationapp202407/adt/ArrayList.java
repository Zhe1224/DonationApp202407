package com.tarumtrsw2407.donationapp202407.adt;

import java.io.Serializable;
import static java.util.Arrays.copyOfRange;

/**
 * ArrayList.java A class that implements the ADT List using an array.
 *
 * @author Frank M. Carrano
 * @version 2.0
 * @param <T>
 */
public class ArrayList<T> implements ListInterface<T>, Serializable {
    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 16;
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }
    public ArrayList(int initialCapacity) {
        numberOfEntries = 0;
        array = (T[]) new Object[initialCapacity];
    }
    public T at(int pos){
        return array[pos];
    }
    public T[] getItems(int start, int end) {
        return copyOfRange(array, start, end);
    }
    public ArrayList<Integer> getPosOf(T item){
        return getPosOf(wrap(item))[0];
    }
    public ArrayList<Integer>[] getPosOf(T[] items){
        ArrayList<Integer>[] output=(ArrayList<Integer>[]) new Object[items.length];
        int i=0;
        for (T element:array){
            int j=0;
            for (T item:items) {
                if (element.equals(item)) output[j].append(i);
                j++;
            }
            i++;
        }
        return output;
    }
    private T[] wrap(T item){
        T[] items=(T[]) new Object[1];items[0]=item;
        return items;
    }
    public boolean append(T item){
        return append(wrap(item));
    }
    public boolean append(T[] items){
        return insert(numberOfEntries,items);
    }
    public boolean insert(int pos,T item){
        return insert(pos,wrap(item));
    }
    public boolean insert(int pos,T[] items){
        splice(pos,0,items);
        return true;
    }
    public boolean delete(int pos){
        return delete(pos,1);
    }
    public boolean delete(int pos,int count){
        splice(pos,count,(T[])new Object[0]);
        return true;
    }

    @Override
    public T remove(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = array[givenPosition - 1];

            if (givenPosition < numberOfEntries) {
                removeGap(givenPosition);
            }

            numberOfEntries--;
        }

        return result;
    }

    @Override
    public void clear() {
        numberOfEntries = 0;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            array[givenPosition - 1] = newEntry;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = array[givenPosition - 1];
        }

        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        for (int index = 0; !found && (index < numberOfEntries); index++) {
            if (anEntry.equals(array[index])) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries < 1;
    }

    @Override
    public boolean isFull() {
        return numberOfEntries == array.length;
    }

    @Override
    public String toString() {
        String outputStr = "";
        for (int index = 0; index < numberOfEntries; ++index) {
            outputStr += array[index] + "\n";
        }

        return outputStr;
    }
    
    private boolean isUnfit(int count){
        return getVacantSpace()<count;
    }
    
    private void resize(double factor){
        T[] newArray=(T[]) new Object[(int)Math.ceil((1+factor)*array.length)];
        System.arraycopy(array,0,newArray,0,array.length);
        this.array=newArray;
    }

    private void expose(int pos, int count) {
        while (isUnfit(count)) resize(1);
        System.arraycopy(array, pos, array, pos + count, array.length - pos - count);
    }

    private void cover(int pos, int count) {
        System.arraycopy(array, pos + count, array, pos, array.length - pos - count);
    }
    
    private int getVacantSpace(){
        return array.length-numberOfEntries;
    }
    
    private T[] splice(int start,int delCount,T[] items){
        T[] cutout = (T[]) new Object[delCount];
        if (delCount>0) System.arraycopy(array,start,cutout,0,delCount);
        int delta=items.length-delCount;
        if (delta>0) expose(start,delta);
        System.arraycopy(items,0,array,start,items.length);
        if (delta<0) cover(start-delta,0-delta);
        numberOfEntries+=delta;
        return cutout;
    }
    
    public boolean equals(ArrayList<T> list){
        return numberOfEntries==list.getNumberOfEntries() && getItems(0,numberOfEntries-1)==list.getItems(0,numberOfEntries-1);
    }
}
