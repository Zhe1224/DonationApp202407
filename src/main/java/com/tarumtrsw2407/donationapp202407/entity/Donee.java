/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author maxmillian hoe hon lin
 */

public class Donee {
    private String id;
    private String name;
    private String type; // Individual, Organization, Family
    private Donation[] donations;
    private int donationCount;

    public Donee(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.donations = new Donation[10]; // Initial capacity for donations
        this.donationCount = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Donation[] getDonations() {
        Donation[] result = new Donation[donationCount];
        System.arraycopy(donations, 0, result, 0, donationCount);
        return result;
    }

    public void addDonation(Donation donation) {
        if (donationCount == donations.length) {
            expandDonations();
        }
        donations[donationCount++] = donation;
    }

    private void expandDonations() {
        Donation[] newDonations = new Donation[donations.length * 2];
        System.arraycopy(donations, 0, newDonations, 0, donations.length);
        donations = newDonations;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Type: ").append(type).append("\n");
        sb.append("Donations:\n");
        for (int i = 0; i < donationCount; i++) {
            sb.append(" - ").append(donations[i].toString()).append("\n");
        }
        return sb.toString();
    }
}