package adt;

/**
 * @author Maxmillian hoe hon lin
 */
import entity.*;
public interface DoneeListInterface<T> {
  void addDonee(Donee donee);
  void removeDonee(String id);
  void updateDonee(String id, Donee updatedDonee);
  Donee searchDonee(String id);
  Donee[] getAllDonees();
  Donee[] filterDoneesByCriteria(String criteria);
  void listAllDoneesWithDonations();
}
