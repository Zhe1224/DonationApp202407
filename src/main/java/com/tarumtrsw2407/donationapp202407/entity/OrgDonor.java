
package com.tarumtrsw2407.donationapp202407.entity;

import com.tarumtrsw2407.donationapp202407.adt.DonorType;
import com.tarumtrsw2407.donationapp202407.control.DonationsManager;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Wong Xiao Zhe
 */
public class OrgDonor implements Donor{
    private String id;
    private DonorType type;
    private String name;
    private Date foundDate;
    private String homeRegion;
    private DonationsManager donations;
    @Override
    public String getId() {
        return id;
    }

    @Override
    public DonorType getType() {
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
        this.id=id;
    }

    @Override
    public void setType(DonorType type) {
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
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nID: ").append(id).append(" | ");
        sb.append(name).append('\n');
        sb.append(type).append(" Donating Organisation");
        sb.append("\nFounded: ").append(foundDate);
        sb.append("\nHome Region: ").append(homeRegion);
        sb.append('\n');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 8;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.foundDate);
        hash = 59 * hash + Objects.hashCode(this.homeRegion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Donor other = (Donor) obj;
        if (!Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        if (!Objects.equals(this.getType(), other.getType())) {
            return false;
        }
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        if (!Objects.equals(this.getHomeRegion(), other.getHomeRegion())) {
            return false;
        }
        return Objects.equals(this.getExistDate(), other.getExistDate());
    }
    
}
