/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarumtrsw2407.donationapp202407.entity;

import com.tarumtrsw2407.donationapp202407.adt.ListInterface;
import com.tarumtrsw2407.donationapp202407.control.DonationsManager;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Wong Xiao Zhe
 */
public class PersonDonor implements Donor{
    private String id;
    private Type type;
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
    public Type getType() {
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

    @Override
    public Date getAge() {
        return getAge(new Date());
    }
    
    @Override
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
    public void setType(Type type) {
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
        sb.append("DonorPerson{");
        sb.append("ID=").append(id);
        sb.append(", type=").append(type);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", birthday=").append(birthday);
        sb.append(", homeRegion=").append(homeRegion);
        sb.append(", donations=").append(donations.toString());
        sb.append('}');
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
