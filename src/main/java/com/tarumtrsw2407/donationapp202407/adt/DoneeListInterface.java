package com.tarumtrsw2407.donationapp202407.adt;

/**
 * @author Maxmillian hoe hon lin
 */
import com.tarumtrsw2407.donationapp202407.entity.*;
public interface DoneeListInterface<T> {
  void addDonee(Donee donee);
  void removeDonee(String id);
  void updateDonee(String id, Donee updatedDonee);
  Donee searchDonee(String id);
  Donee[] getAllDonees();
  Donee[] filterDoneesByCriteria(String criteria);
  void listAllDoneesWithDonations();
}
