/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarumtrsw2407.donationapp202407.control;

import com.tarumtrsw2407.donationapp202407.adt.ArrayList;
import com.tarumtrsw2407.donationapp202407.adt.ListInterface;
import com.tarumtrsw2407.donationapp202407.boundary.DonorManager;
import com.tarumtrsw2407.donationapp202407.entity.Donor;
import com.tarumtrsw2407.donationapp202407.entity.Donor.Type;
import com.tarumtrsw2407.donationapp202407.entity.OrgDonor;
import com.tarumtrsw2407.donationapp202407.entity.PersonDonor;
import java.util.Date;

/**
 *
 * @author Wong Xiao Zhe
 */
public class DonorList {
    private ListInterface<Donor> donors;
    public DonorList(){
        this(new ArrayList<Donor>());
    }
    public DonorList(ListInterface<Donor> donors){
        this.donors=donors;
    }
    public DonorList(DonorList donors){
        this(donors.getDonors());
    }
    public int size(){
        return donors.size();
    }
    public ListInterface<Donor> getDonors(){
        return donors;
    }
    public int addDonor(boolean individual,String id,Type type,String firstName,String lastName,Date date,String region){
        Donor donor=individual?new PersonDonor():new OrgDonor();
        donor.setId(id);donor.setName(firstName, lastName);donor.setExistDate(date);donor.setHomeRegion(region);
        return donors.append(donor);
    }
    public ListInterface<Donor> main(){
        for(;;){
            
            break;
        }
        return donors;
    }
    public static void main(String[] args) {
        DonorList donorManager = new DonorList();
        donorManager.main();
    }
}
