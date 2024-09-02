/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarumtrsw2407.donationapp202407.adt;

/**
 *
 * @author KJ
 */
public interface ListInterface2<T> {

  public boolean add(T newEntry);

  
  public boolean add(int newPosition, T newEntry);

  
  public T remove(int givenPosition);

  public void clear();

  public boolean replace(int givenPosition, T newEntry);

  
  public T getEntry(int givenPosition);

  
  public boolean contains(T anEntry);

 
  public int getNumberOfEntries();

  
  public boolean isEmpty();

 
  public boolean isFull();
}
