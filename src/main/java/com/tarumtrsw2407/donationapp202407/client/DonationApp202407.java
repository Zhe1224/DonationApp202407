/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.tarumtrsw2407.donationapp202407.client;

import com.tarumtrsw2407.donationapp202407.boundary.EventUI;
import com.tarumtrsw2407.donationapp202407.boundary.*;
import com.tarumtrsw2407.donationapp202407.control.*;
import com.tarumtrsw2407.donationapp202407.entity.Event;

/**
 *
 * @author Wong Xiao Zhe
 */
public class DonationApp202407 {

    public static void main(String[] args) {
        DonorManager donor=new DonorManager();
        DoneeManager donee=new DoneeManager();
        VolunteerManager volunteer=new VolunteerManager();
        EventManagementSystem event=new EventManager();
        event.addEvent(new Event("E001", "Nature Blood Donation", "2024-09-10", "Central Park"));
        event.addEvent(new Event("E002", "NYC Blood Donation", "2024-09-12", "Community Center"));
        Menu<String> m=new Menu<>();
        String[] menus={"Donor","Donee","Volunteer","Event","Exit"};
        for (String mm:menus)m.include(mm,mm);
        m.setMessage("Choose a subsystem: ");
        m.setBlankMessage("Please select a subsystem.");
        m.setNoMatchMessage("Type the correct subsystem name to continue!");
        for (;;){
        System.out.println("Charity Management App by TARUMT RSW 202407");
            switch(m.prompt()){
                case "Donor":donor = new DonorUI(donor).main();break;
                case "Donee":donee = new DoneeUI(donee).main();break;
                case "Volunteer":volunteer = new VolunteerUI(volunteer,event).main();break;
                case "Event":event = new EventUI(event,volunteer).main();break;
                case "Exit":
                    System.out.println("Thank you for using this app! Goodbye!");
                    return;
            }
        }
    }
}
