/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarumtrsw2407.donationapp202407.entity;

/**
 *
 * @author maxmi
 */
public class Donation {
    private double amount;
    private String date;

    public Donation(double amount, String date) {
        this.amount = amount;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public Donor getDonor(){
        return new PersonDonor();
    }

    @Override
    public String toString() {
        return "Amount: RM" + amount + ", Date: " + date;
    }
}
