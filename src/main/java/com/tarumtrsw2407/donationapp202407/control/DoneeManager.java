/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarumtrsw2407.donationapp202407.control;

import com.tarumtrsw2407.donationapp202407.adt.*;
import com.tarumtrsw2407.donationapp202407.entity.Donee;
/**
 *
 * @author maxmi
 */
public class DoneeManager {
    private DoneeArrayList doneeList;
    
    public DoneeManager() {
        this.doneeList = new DoneeArrayList();
    }

    public DoneeManager(DoneeArrayList doneeList) {
        this.doneeList = doneeList;
    }

    public void addDonee(Donee donee) {
        doneeList.addDonee(donee);
    }

    public void removeDonee(String id) {
        doneeList.removeDonee(id);
    }

    public void updateDonee(String id, Donee updatedDonee) {
        doneeList.updateDonee(id, updatedDonee);
    }

    public Donee searchDonee(String id) {
        return doneeList.searchDonee(id);
    }
    public Donee searchDoneeByName(String name) {
        return doneeList.binarySearchByName(name);
    }

    public Donee[] getAllDonees() {
        return doneeList.getAllDonees();
    }
    
    public void listAllDoneesWithDonations() {
        doneeList.listAllDoneesWithDonations();
    }

    public Donee[] filterDoneesByCriteria(String criteria) {
        return doneeList.filterDoneesByCriteria(criteria);
    }

    public String generateSummaryReport(){
        return doneeList.generateSummaryReport();
    }
}
