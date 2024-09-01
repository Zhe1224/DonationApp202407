/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarumtrsw2407.donationapp202407.control;

import com.tarumtrsw2407.donationapp202407.adt.ArrayList;
import com.tarumtrsw2407.donationapp202407.adt.ListInterface;
import com.tarumtrsw2407.donationapp202407.boundary.DonorUI;
import com.tarumtrsw2407.donationapp202407.entity.Donor;

/**
 *
 * @author Wong Xiao Zhe
 */
public class DonorManager {
    private ListInterface<Donor>donors=new ArrayList<>();
    private DonorUI = new DonorUI();
    public int addDonor(Donor donor){
        return donors.append(donor);
    }
    public int addDonor(Donor donor){
        return donors.append(donor);
    }
    public static void main(String[] args) {
        DonorManager donorManager = new DonorManager();
        DonorManager.runDonorManager();
    }
}
