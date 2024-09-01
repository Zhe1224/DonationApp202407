package com.tarumtrsw2407.donationapp202407.adt;

import java.io.Serializable;
import static java.util.Arrays.copyOfRange;
import java.util.Iterator;
import java.util.function.Function;

/**
 * ArrayList.java A class that implements the ADT List using an array.
 *
 * @author Wong Xiao Zhe
 * @param <T> type of items
 */
public final class ArrayList<T> implements ListInterface<T>, Serializable{
    @Override
    public Iterator<T> iterator() {
        return getIterator();
    }

    @Override
    public ListInterface<T> filter(Function<T, Boolean> criteria) {
        ListInterface<T> o=new ArrayList();
        for (T item:getItems()) if (criteria.apply(item)) o.append(item);
        return o;
    }
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
    public String toString(){
        StringBuilder s=new StringBuilder("");
        for (T item:getItems()) {s.append(item.toString());s.append("\n");}
        return s.toString();
    }
    public boolean equals(ListInterface<T> list){
        return size==list.size() && getItems()==list.getItems();
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public T at(int pos){
        if ((pos<0) || pos+1>size()) outOfBounds(pos);
        return array[pos];
    }
    @Override
    public ListInterface<T> getItems(){
        return getItems(0,size()-1);
    }
    @Override
    public ListInterface<T> getItems(int start, int end){
        return new ArrayList<>(getItemsAsArray(start, end));
    }
    @Override
    public int getPosOf(T item){
        ListInterface<Integer> r = getPosOf(item,false);
        if (r.size()>0) return r.at(0);
        return -1;
    }
    @Override
    public ListInterface<Integer> getPosOf(T item,boolean global){
        return getPosOf(wrap(item),global).at(0);
    }
    @Override
    public ListInterface<ListInterface<Integer>> getPosOf(ListInterface<T> items,boolean global){
        return getPosOf(new ArrayList<>(items),global);
    }
    @Override
    public Iterator<T> getIterator() {
        return new ListIterator();
    }
    /*Special getters*/
    public T[] getItemsAsArray(){
        return getItemsAsArray(0,size()-1);
    }
    public T[] getItemsAsArray(int start, int end){
        if ((start<0) || start+1>size()) outOfBounds(start);
        if ((start<0) || end+1>size()) outOfBounds(end);
        if (start>end) illegalRange(start,end);
        return copyOfRange(array, start, end);
    }
    public ListInterface<ListInterface<Integer>> getPosOf(T[] items,boolean global){
        ListInterface<ListInterface<Integer>>output=new ArrayList<>();
        int i=0;
        for (T element:getItems()){ 
            output.append(new ArrayList<Integer>());
            for (T item:items) {if (element.equals(item)) {output.at(output.size()-1).append(i);break;}}
            i++;
        }
        return output;
    }
    public ListInterface<ListInterface<Integer>> getPosOf(ArrayList<T> items,boolean global){
        return getPosOf(items.getItemsAsArray(),global);
    }
    /*Generic mutators(create)*/
    @Override
    public int append(T item){
        return append(wrap(item));
    }
    @Override
    public int append(ListInterface<T> items){
        return append(new ArrayList<>(items));
    }
    @Override
    public int insert(int pos,T item){
        return insert(pos,wrap(item));
    }
    @Override
    public int insert(int pos,ListInterface<T> items){
        return insert(pos,new ArrayList<>(items));
    }
    /*Special mutators(create)*/
    public int append(T[] items){
        return insert(size(),items);
    }
    public int append(ArrayList<T> items){
        return append(items.getItemsAsArray(0, items.size()));
    }
    public int insert(int pos,ArrayList<T> items){
        return insert(pos,items.getItemsAsArray());
    }
    public int insert(int pos,T[] items){
        splice(pos,0,items);
        return size();
    }
    /*Generic mutators(update)*/
    @Override
    public T replace(int pos,T item){
        return splice(pos,1,wrap(item).getItemsAsArray())[0];
    }
    @Override
    public ListInterface<Entry<Integer,T>> replace(ListInterface<Entry<Integer, T>> posItemPair){
        return replace(new ArrayList<>(posItemPair));
    }
    /*Special mutators(Update)*/
    public ListInterface<Entry<Integer,T>> replace(ArrayList<Entry<Integer, T>> posItemPair){
        ListInterface<Entry<Integer,T>> o = new ArrayList<>();
        for (Entry<Integer, T> e:posItemPair) o.append(new Entry<>(e.getKey(),replace(e.getKey(),e.getValue())));
        return o;
    }
    private ArrayList<T> wrap(T item){
        T[] items=(T[]) new Object[1];items[0]=item;
        return new ArrayList<>(items);
    }
    /*Generic mutators(delete)*/
    @Override
    public T delete(int pos){
        return splice(pos,1,(T[])new Object[0])[0];
    }
    @Override
    public ListInterface<T> delete(ListInterface<Integer> pos){
        ListInterface<T> o = new ArrayList<>();
        ListInterface<Integer> d = new ArrayList();
        for (int i=size();i>-1;i--) {
            if (!(d.getPosOf(i)<0)) continue;
            if (pos.getPosOf(i)<0) continue;
            o.append(delete(i));
            d.append(i);
        }
        return o;
    }
    @Override
    public T delete(T item){
        if (getPosOf(item)<0) return null;
        return delete(getPosOf(item));
    }
    @Override
    public T erase(T item){
        return delete(getPosOf(item,true)).at(0);
    }
    @Override
    public void clear() {
        size = 0;
    }
    /*private*/
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
        if ((start<0) || start+1>size()) outOfBounds(start);
        T[] cutout = (T[]) new Object[delCount];
        if (delCount>0) System.arraycopy(array,start,cutout,0,delCount);
        int delta=items.length-delCount;
        if (delta>0) expose(start,delta);
        System.arraycopy(items,0,array,start,items.length);
        if (delta<0) cover(start-delta,0-delta);
        size+=delta;
        return cutout;
    }
    
    private void outOfBounds(int pos) throws IndexOutOfBoundsException{
        throw new IndexOutOfBoundsException("Expected index from 0 to ".concat(Integer.toString(size()-1)).concat("but got ").concat(Integer.toString(pos)));
    }
    
    private void illegalRange(int start,int end) throws IllegalArgumentException{
        throw new IllegalArgumentException(Integer.toString(start).concat(" to ").concat(Integer.toString(end).concat(" is not a valid range.")));
    }
}
