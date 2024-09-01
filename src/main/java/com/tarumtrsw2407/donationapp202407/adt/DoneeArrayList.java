package com.tarumtrsw2407.donationapp202407.adt;

/**
 * @author Maxmillian Hoe Hon Lin
 */

import com.tarumtrsw2407.donationapp202407.entity.*;
import java.io.Serializable;

public class DoneeArrayList<T> implements DoneeListInterface<T>, Serializable {

    private Donee[] donees;
    private int count;
    private static final int DEFAULT_CAPACITY = 5;

    public DoneeArrayList() {
        this(DEFAULT_CAPACITY); // Calls the parameterized constructor with default capacity
    }

    // Constructor with initial capacity
    public DoneeArrayList(int initialCapacity) {
        count = 0;
        donees = new Donee[initialCapacity];
    }

    @Override
    public void addDonee(Donee donee) {
        if (count == donees.length) {
            expandArray();
        }
        donees[count++] = donee;
        sortDoneesById(); // Ensure the array is sorted after addition
    }

    @Override
    public void removeDonee(String id) {
        int index = binarySearch(id);
        if (index >= 0) {
            System.arraycopy(donees, index + 1, donees, index, count - index - 1);
            donees[--count] = null; // Remove reference to the last element
        }
    }

    @Override
    public void updateDonee(String id, Donee updatedDonee) {
        int index = binarySearch(id);
        if (index >= 0) {
            donees[index] = updatedDonee;
        }
    }

    @Override
    public Donee searchDonee(String id) {
        int index = binarySearch(id);
        return index >= 0 ? donees[index] : null;
    }

    @Override
    public Donee[] getAllDonees() {
        Donee[] result = new Donee[count];
        System.arraycopy(donees, 0, result, 0, count);
        return result;
    }

    @Override
    public Donee[] filterDoneesByCriteria(String criteria) {
        Donee[] filteredDonees = new Donee[count];
        int filteredCount = 0;
        for (int i = 0; i < count; i++) {
            if (donees[i].getName().equalsIgnoreCase(criteria) || donees[i].getType().equalsIgnoreCase(criteria)) {
                filteredDonees[filteredCount++] = donees[i];
            }
        }
        Donee[] result = new Donee[filteredCount];
        System.arraycopy(filteredDonees, 0, result, 0, filteredCount);
        return result;
    }

    private void expandArray() {
        Donee[] newDonees = new Donee[donees.length * 2];
        System.arraycopy(donees, 0, newDonees, 0, donees.length);
        donees = newDonees;
    }

    private int binarySearch(String id) {
        int low = 0;
        int high = count - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            Donee midDonee = donees[mid];
            int comparison = midDonee.getId().compareTo(id);
            if (comparison < 0) {
                low = mid + 1;
            } else if (comparison > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
    
    public Donee binarySearchByName(String name) {
        int low = 0;
        int high = count - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = donees[mid].getName().compareToIgnoreCase(name);

            if (comparison == 0) {
                return donees[mid];
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    private void sortDoneesById() {
        quickSort(donees, 0, count - 1);
    }

    private void quickSort(Donee[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private int partition(Donee[] array, int low, int high) {
        Donee pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j].getId().compareTo(pivot.getId()) <= 0) {
                i++;
                Donee temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        Donee temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    // List all donees with their donations
    public void listAllDoneesWithDonations() {
        for (Donee donee : getAllDonees()) {
            System.out.println(donee);
        }
    }
}

