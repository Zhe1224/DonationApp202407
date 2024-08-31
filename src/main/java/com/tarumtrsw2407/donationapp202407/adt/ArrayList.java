package com.tarumtrsw2407.donationapp202407.adt;

import java.io.Serializable;
import static java.util.Arrays.copyOfRange;
import java.util.Iterator;

/**
 * ArrayList.java A class that implements the ADT List using an array.
 *
 * @author Zhe1224
 * @version ???
 * @param <T> type of items
 */
public final class ArrayList<T> implements ListInterface<T>, Serializable{
    private class ListIterator implements Iterator<T>{
        private int nextIndex=0;
        @Override
        public boolean hasNext(){
            return nextIndex<size();
        }
        @Override
        public T next(){
            if (!hasNext()) return null;
            return at(nextIndex++);
        }
    }
    private T[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    /*Generic constructors*/
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }
    public ArrayList(int initialCapacity) {
        size = 0;
        array = (T[]) new Object[initialCapacity>0?initialCapacity:DEFAULT_CAPACITY];
    }
    public ArrayList(ListInterface<T> items){
        this(items.size());
        for (T item:items){this.append(item);}
    }
    /*Special constructors*/
    public ArrayList(T[] items){
        this(items.length);
        this.append(items);
    }
    public ArrayList(ArrayList<T> items){
        this(items.getItemsAsArray(0,items.size()));
    }
    /*Generic getters*/
    @Override
    public int size() {
        return size;
    }
    @Override
    public T at(int pos){
        return array[pos];
    }
    @Override
    public ArrayList<T> getItems(int start, int end){
        return new ArrayList<>(getItemsAsArray(start, end));
    }
    @Override
    public ArrayList<Integer> getPosOf(T item,boolean global){
        ArrayList<Integer> output=new ArrayList<>();
        for (int i=0;i<size();i++) if (this.at(i).equals(item)) {output.append(i);if (!global) return output;}
        return output;
    }
    @Override
    public ArrayList<ListInterface<Integer>> getPosOf(ListInterface<T> items,boolean global){
        ArrayList<ListInterface<Integer>>output=new ArrayList<>();
        for (T item:items){output.append(getPosOf(item,global));}
        return output;
    }
    @Override
    public Iterator<T> getIterator() {
        return new ListIterator();
    }
    /*Special getters*/
    public T[] getItemsAsArray(int start, int end){
        return copyOfRange(array, start, end);
    }
    public ArrayList<ListInterface<Integer>> getPosOf(T[] items){
        ArrayList<ListInterface<Integer>>output=new ArrayList<>();
        for (T element;array) 
        for (int i=0;i<size();)
        for (T item:items){output.append(getPosOf(item));}
        return output;
    }
    public ArrayList<ListInterface<Integer>> getPosOf(ArrayList<T> items){
        return getPosOf(items.getItemsAsArray(0,items.size()));
    }
    /*Generic mutators(create)*/
    @Override
    public int append(T item){
        return append(wrap(item));
    }
    @Override
    public int append(ListInterface<T> items){
        for (T item:items){append(item);}
        return size();
    }
    @Override
    public int insert(int pos,T item){
        return insert(pos,wrap(item));
    }
    @Override
    public int insert(int pos,ListInterface<T> items){
        return insert(new ArrayList<T>(items));
    }
    /*Special mutators(create)*/
    public int append(T[] items){
        return insert(size(),items);
    }
    public int append(ArrayList<T> items){
        return append(items.getItemsAsArray(0, items.size()));
    }
    public int insert(int pos,T[] items){
        splice(pos,0,items);
        return size();
    }
    /*Update methods*/
    @Override
    public T replace(int pos,T item){
        T ejected=at(pos);
        
        return ejected;
    }
    @Override
    public ArrayList<Entry<Integer,T>> replace(ListInterface<Entry<Integer, T>> posItemPair){
        ArrayList<Entry<Integer,T>> = new ArrayList<>();
        
    }
    
    
    private ArrayList<T> wrap(T item){
        T[] items=(T[]) new Object[1];items[0]=item;
        return new ArrayList<>(items);
    }
    
    /*Special mutators*/
    
    /*Create methods*/
    
    public boolean delete(int pos){
        return delete(pos,1);
    }
    public boolean delete(int pos,T item){
        splice(pos,count,(T[])new Object[0]);
        return true;
    }

    @Override
    public T remove(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= size)) {
            result = array[givenPosition - 1];

            if (givenPosition < size) {
                removeGap(givenPosition);
            }

            size--;
        }

        return result;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= size)) {
            array[givenPosition - 1] = newEntry;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= size)) {
            result = array[givenPosition - 1];
        }

        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        for (int index = 0; !found && (index < size); index++) {
            if (anEntry.equals(array[index])) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size < 1;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public String toString() {
        String outputStr = "";
        for (int index = 0; index < size; ++index) {
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
        return array.length-size;
    }
    
    private T[] splice(int start,int delCount,T[] items){
        T[] cutout = (T[]) new Object[delCount];
        if (delCount>0) System.arraycopy(array,start,cutout,0,delCount);
        int delta=items.length-delCount;
        if (delta>0) expose(start,delta);
        System.arraycopy(items,0,array,start,items.length);
        if (delta<0) cover(start-delta,0-delta);
        size+=delta;
        return cutout;
    }
    
    public boolean equals(ArrayList<T> list){
        return size==list.getSize() && getItems(0,size-1)==list.getItems(0,size-1);
    }

    

    @Override
    public ListInterface<ListInterface<Integer>> getPosOf(ListInterface<T> items) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int append(ListInterface<T> items) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(int pos, ListInterface<T> items) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T replace(int pos, ListInterface<T> item) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListInterface<Entry<Integer, T>> replace(Entry<Integer, T>[] posItemPair) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListInterface<T> delete(ListInterface<Integer> pos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T delete(T item) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T erase(T item) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}
