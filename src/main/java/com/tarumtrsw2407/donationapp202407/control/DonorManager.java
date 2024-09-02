package com.tarumtrsw2407.donationapp202407.control;

import com.tarumtrsw2407.donationapp202407.adt.ArrayList;
import com.tarumtrsw2407.donationapp202407.adt.ListInterface;
import com.tarumtrsw2407.donationapp202407.adt.Type;
import com.tarumtrsw2407.donationapp202407.entity.Donation;
import com.tarumtrsw2407.donationapp202407.entity.Donor;
import com.tarumtrsw2407.donationapp202407.entity.OrgDonor;
import com.tarumtrsw2407.donationapp202407.entity.PersonDonor;
import java.util.Date;

/**
 *
 * @author Wong Xiao Zhe
 */
public class DonorManager {
    private ListInterface<Donor> donors;
    public DonorManager(){
        this(new ArrayList<>());
    }
    public DonorManager(ListInterface<Donor> donors){
        this.donors=donors;
    }
    public DonorManager(DonorManager donors){
        this(donors.getDonors());
    }
    public int size(){
        return donors.size();
    }
    public ListInterface<Donor> getDonors(){
        return donors;
    }
    public ListInterface<Donor> filterDonors(){
        return donors;
    }
    public int addDonor(boolean individual,String id,Type type,String firstName,String lastName,Date date,String region){
        Donor donor=individual?new PersonDonor():new OrgDonor();
        donor.setId(id);donor.setName(firstName, lastName);donor.setExistDate(date);donor.setHomeRegion(region);
        return donors.append(donor);
    }
    public void updateDonor(Donor old,Donor donor){
        donors.replace(donors.getPosOf(old),donor);
    }
    public void removeDonor(Donor donor){
        donors.delete(donor);
    }
    public DonationsManager getDonationsByDonor(DonationsManager donations,Donor donor){
        return new DonationsManager(donations.getDonations().filter(donation->donation.getDonor().equals(donor)));
    }
    public String listDonors(DonationsManager donations){
        StringBuilder sb=new StringBuilder("");
        for (Donor donor:donors) {
            sb.append(donor);
            getDonationsByDonor(donations,donor).getDonations();
            for (Donation donation:getDonationsByDonor(donations,donor).getDonations()) sb.append(donation);
        }
        return sb.toString();
    }
    public double sumDonations(DonationsManager donations,Donor donor){
        double o=0.00;
        for (Donation donation:getDonationsByDonor(donations,donor).getDonations()) o=o+donation.getAmount();
        return o;
    }
    public String generateSummary(DonationsManager donations){
        return "";
    }
    public ListInterface<Donor> main(){
        for(;;){
            
            break;
        }
        return donors;
    }
    public static void main(String[] args) {
        DonorManager donorManager = new DonorManager();
        donorManager.main();
    }
}
