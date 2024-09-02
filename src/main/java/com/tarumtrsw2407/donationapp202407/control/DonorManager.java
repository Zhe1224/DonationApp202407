package com.tarumtrsw2407.donationapp202407.control;

import com.tarumtrsw2407.donationapp202407.adt.ArrayList;
import com.tarumtrsw2407.donationapp202407.adt.DonorBody;
import com.tarumtrsw2407.donationapp202407.adt.ListInterface;
import com.tarumtrsw2407.donationapp202407.adt.DonorType;
import com.tarumtrsw2407.donationapp202407.entity.Donation;
import com.tarumtrsw2407.donationapp202407.entity.Donor;
import com.tarumtrsw2407.donationapp202407.entity.OrgDonor;
import com.tarumtrsw2407.donationapp202407.entity.PersonDonor;
import java.util.Date;
import java.util.Iterator;
import java.util.function.Function;

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
    public String printDonors(){
        StringBuilder sb=new StringBuilder();
        for (Donor d:donors){
            
            sb.append("ID:").append(d.getId()).append(" | Name:").append(d.getName()).append('\n');
            
        }
        return sb.toString();
    }
    public DonorManager filter(Function<Donor,Boolean> criteria){
        return new DonorManager(donors.filter(criteria));
    }
    public int add(DonorBody kind,String id,DonorType type,String firstName,String lastName,Date date,String region){
        Donor donor=(kind==DonorBody.Person)?new PersonDonor():(kind==DonorBody.Org)?new OrgDonor():null;
        if (donor==null) throw new IllegalArgumentException("Unspecified Donor Type!");
        donor.setId(id);donor.setName(firstName, lastName);donor.setExistDate(date);donor.setHomeRegion(region);
        return donors.append(donor);
    }
    public void update(Donor donor,String id,DonorType type,String firstName,String lastName,Date date,String region){
        donor.setId(id);donor.setName(firstName, lastName);donor.setExistDate(date);donor.setHomeRegion(region);
    }
    public void remove(Donor donor){
        donors.delete(donor);
    }
    public DonationsManager getDonationsByDonor(DonationsManager donations,Donor donor){
        return new DonationsManager(donations.getDonations().filter(donation->donation.getDonor().equals(donor)));
    }
    public String listDonors(DonationsManager donations){
        StringBuilder sb=new StringBuilder("");
        for (Donor donor:donors) {
            sb.append(donor);
            /*getDonationsByDonor(donations,donor).getDonations();
            for (Donation donation:getDonationsByDonor(donations,donor).getDonations()) sb.append(donation);*/
        }
        return sb.toString();
    }
    public double sumDonations(DonationsManager donations,Donor donor){
        double o=0.00;
        for (Donation donation:getDonationsByDonor(donations,donor).getDonations()) o=o+donation.getAmount();
        return o;
    }
    public String summary(){
        return summary(null);
    }
    public String summary(DonationsManager donations){
        final String sep=new String(new char[14]).replace("\0", "=");
        StringBuilder sb=new StringBuilder();
        sb.append("Donors Summary\n").append(sep);
        sb.append("\nDonors count: ").append(donors.size()).append('\n');
        Iterator<Donor> i=donors.getIterator();
        while (i.hasNext()) sb.append(i.next().toString()).append(sep);
        return sb.toString();
    }
    @Override
    public String toString(){
        return summary();
    }
}