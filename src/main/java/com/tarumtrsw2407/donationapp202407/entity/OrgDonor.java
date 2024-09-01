
package com.tarumtrsw2407.donationapp202407.entity;

import com.tarumtrsw2407.donationapp202407.adt.Type;
import com.tarumtrsw2407.donationapp202407.control.DonationsManager;
import java.util.Date;

/**
 *
 * @author Wong Xiao Zhe
 */
public class OrgDonor implements Donor{
    private String id;
    private Type type;
    private String name;
    private Date foundDate;
    private String homeRegion;
    private DonationsManager donations;
    @Override
    public String getId() {
        return id;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Date getExistDate() {
        return foundDate;
    }

    public Date getAge() {
        return getAge(new Date());
    }
    
    public Date getAge(Date date) {
        return new Date(getExistDate().getTime()-date.getTime());
    }

    @Override
    public String getHomeRegion() {
        return homeRegion;
    }

    /*@Override
    public DonationsManager getDonations() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double getCumulativeDonation() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getEarliestDonationDate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getLatestDonationDate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/

    @Override
    public void setId(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setType(Type type) {
        this.type=type;
    }

    @Override
    public void setName(String first, String last) {
        this.name=(first.concat(last));
    }

    @Override
    public void setExistDate(Date date) {
        this.foundDate=date;
    }

    @Override
    public void setHomeRegion(String region) {
        this.homeRegion=region;
    }
    
}
