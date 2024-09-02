package com.tarumtrsw2407.donationapp202407.entity;

import com.tarumtrsw2407.donationapp202407.adt.DonorType;
import com.tarumtrsw2407.donationapp202407.control.DonationsManager;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Wong Xiao Zhe
 */
public class PersonDonor implements Donor{
    private String id;
    private DonorType type;
    private String firstName;
    private String lastName;
    private Date birthday;
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
        return firstName.concat(" ").concat(lastName);
    }

    @Override
    public Date getExistDate() {
        return birthday;
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
        return donations;
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
        this.firstName=first;this.lastName=last;
    }

    @Override
    public void setExistDate(Date date) {
        this.birthday=date;
    }

    @Override
    public void setHomeRegion(String region) {
        this.homeRegion=region;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nID: ").append(id).append(" | ");
        sb.append(getName()).append('\n');
        sb.append(type.toString()).append(" Individual Donor");
        sb.append("\nBirthday: ").append(birthday);
        sb.append("\nHome Region: ").append(homeRegion);
        sb.append('\n');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.firstName);
        hash = 67 * hash + Objects.hashCode(this.lastName);
        hash = 67 * hash + Objects.hashCode(this.birthday);
        hash = 67 * hash + Objects.hashCode(this.homeRegion);
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
        final PersonDonor other = (PersonDonor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.homeRegion, other.homeRegion)) {
            return false;
        }
        return Objects.equals(this.birthday, other.birthday);
    }
}
