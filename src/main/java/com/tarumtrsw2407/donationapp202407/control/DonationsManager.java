package com.tarumtrsw2407.donationapp202407.control;

import com.tarumtrsw2407.donationapp202407.adt.ListInterface;
import com.tarumtrsw2407.donationapp202407.entity.Donation;

/**
 *
 * @author 
 */
public class DonationsManager {
    private ListInterface<Donation> donations;
    public DonationsManager(ListInterface<Donation> donations) {
        this.donations = donations;
    }
    public ListInterface<Donation> getDonations(){
        throw new UnsupportedOperationException("getDonations not implemented");
    };
}
