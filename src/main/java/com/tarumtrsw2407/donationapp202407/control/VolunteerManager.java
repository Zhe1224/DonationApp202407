/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarumtrsw2407.donationapp202407.control;

/**
 *
 * @author User
 */
import com.tarumtrsw2407.donationapp202407.adt.ListInterface;
import com.tarumtrsw2407.donationapp202407.adt.ArrayList;
import com.tarumtrsw2407.donationapp202407.entity.Volunteer;

public class VolunteerManager {
    private ListInterface<Volunteer> volunteers;

    public VolunteerManager() {
        volunteers = new ArrayList<>();
    }

    public void addVolunteer(Volunteer volunteer) {
        volunteers.append(volunteer);
    }

    public boolean removeVolunteer(String id) {
        Volunteer volunteer = searchVolunteerById(id);
        if (volunteer != null) {
            volunteers.delete(volunteer);
            return true;
        }
        return false;
    }

    public Volunteer searchVolunteerById(String id) {
        for (int i = 0; i < volunteers.size(); i++) {
            Volunteer volunteer = volunteers.at(i);
            if (volunteer.getId().equals(id)) {
                return volunteer;
            }
        }
        return null;
    }
    
    public ListInterface<Volunteer> searchVolunteerByName(String name) {
        ListInterface<Volunteer> matchingVolunteers = new ArrayList<>();
        for (int i = 0; i < volunteers.size(); i++) {
            Volunteer volunteer = volunteers.at(i);
            if (volunteer.getName().equalsIgnoreCase(name)) {
                matchingVolunteers.append(volunteer);
            }
        }
        return matchingVolunteers;
    }

    public boolean assignVolunteerToEvent(String id, String event) {
        Volunteer volunteer = searchVolunteerById(id);
        if (volunteer != null) {
            /*volunteer.assignEvent(event);*/
            return true;
        }
        return false;
    }

    public String searchEventUnderVolunteer(String id) {
        Volunteer volunteer = searchVolunteerById(id);
        return /*volunteer != null ? volunteer.getAssignedEvent() :*/ null;
    }

    public ListInterface<Volunteer> listAllVolunteers() {
        return volunteers;
    }

    public ListInterface<Volunteer> filterVolunteersByEvent(String event) {
        ListInterface<Volunteer> filteredVolunteers = new ArrayList<>();
        /*for (int i = 0; i < volunteers.size(); i++) {
            Volunteer volunteer = volunteers.at(i);
            if (event.equals(volunteer.getAssignedEvent())) {
                filteredVolunteers.append(volunteer);
            }
        }*/
        return filteredVolunteers;
    }

    public String generateSummaryReport() {
        StringBuilder report = new StringBuilder();
        report.append("Volunteer Summary Report\n");
        report.append("========================\n");
        for (int i = 0; i < volunteers.size(); i++) {
            report.append(volunteers.at(i).toString()).append("\n");
        }
        return report.toString();
    }
}
