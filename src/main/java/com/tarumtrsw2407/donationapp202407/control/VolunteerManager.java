/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarumtrsw2407.donationapp202407.control;

/**
 *
 * @author Nathaniel Woo Shi Yan
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

    // Modified to handle multiple events
    public boolean assignVolunteerToEvent(String id, String eventId) {
        Event event = eventManager.searchEventById(eventId);
        Volunteer volunteer = searchVolunteerById(id);
        if (volunteer != null) {
            volunteer.getAssignedEvents().append(event); // Add event to the volunteer
            event.getVolunteers().append(volunteer); // Add volunteer to the event
            return true;
        }
        return false;
    }

    public boolean unassignVolunteerFromEvent(String id, String eventId) {
        Event event = eventManager.searchEventById(eventId);
        Volunteer volunteer = searchVolunteerById(id);
        if (volunteer != null) {
            volunteer.getAssignedEvents().delete(event); // Remove event from the volunteer
            event.getVolunteers().delete(volunteer); // Remove volunteer from the event
            return true;
        }
        return false;
    }

    // Modified to return all events assigned to a volunteer
    public ListInterface<String> searchEventsUnderVolunteer(String id) {
        Volunteer volunteer = searchVolunteerById(id);
        return volunteer != null ? volunteer.getAssignedEvents() : null;
    }

    public String listAllVolunteers() {
        StringBuilder sb = new StringBuilder();
        if (volunteers.size() < 1) {
            sb.append("No volunteers available.");
        } else {
            sb.append(String.format("%-5s %-20s %-15s %-30s", "ID", "Name", "Contact", "Email")).append('\n');
            sb.append("----------------------------------------------------------------------\n");
            for (Volunteer volunteer : volunteers) {
                sb.append(String.format("%-5s %-20s %-15s %-30s", 
                    volunteer.getId(), volunteer.getName(), 
                    volunteer.getContactNumber(), volunteer.getEmail())).append('\n');
            }
        }
        return sb.toString();
    }

    // Modified to check if a volunteer is assigned to a specific event
    public ListInterface<Volunteer> filterVolunteersByEvent(String id) {
        Event event = eventManager.searchEventById(id);
        ListInterface<Volunteer> filteredVolunteers = new ArrayList<>();
        for (int i = 0; i < volunteers.size(); i++) {
            Volunteer volunteer = volunteers.at(i);
            if (volunteer.getAssignedEvents().contains(event)) { // Check if volunteer is assigned to the event
                filteredVolunteers.append(volunteer);
            }
        }
        return filteredVolunteers;
    }

    public String generateSummaryReport() {
        StringBuilder report = new StringBuilder();

        // Summary Header
        report.append("\nVolunteer Summary Report:\n");
        report.append("--------------------------------------\n");

        // Total Volunteers
        report.append("Total Volunteers: ").append(volunteers.size()).append("\n");

        // Calculate total number of event assignments across all volunteers
        int totalEventAssignments = 0;
        for (int i = 0; i < volunteers.size(); i++) {
            Volunteer volunteer = volunteers.at(i);
            totalEventAssignments += volunteer.getAssignedEvents().size(); // Assuming getAssignedEvents() returns a ListInterface of events
        }

        // Total number of event assignments
        report.append("Total Events Assigned to Volunteers: ").append(totalEventAssignments).append("\n");
        report.append("--------------------------------------\n");

        return report.toString();
    }
}
