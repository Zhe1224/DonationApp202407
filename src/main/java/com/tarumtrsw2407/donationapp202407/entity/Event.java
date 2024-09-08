package com.tarumtrsw2407.donationapp202407.entity;

import com.tarumtrsw2407.donationapp202407.adt.ArrayList;
import com.tarumtrsw2407.donationapp202407.adt.ListInterface;

/**
 *
 * @author KJ
 */
public class Event {
    private String id;
    private String name;
    private String date;
    private String location;
    private ListInterface<Volunteer> volunteers;

    public Event(String id, String name, String date, String location) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.volunteers = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Add a volunteer to the event
    public boolean addVolunteer(Volunteer volunteer) {
        if (!containsVolunteer(volunteer.getId())) {
            volunteers.append(volunteer);  // Using append from ListInterface
            return true;
        }
        return false;  // Volunteer is already assigned to the event
    }

    // Remove a volunteer from the event
    public boolean removeVolunteer(String volunteerId) {
        for (int i = 0; i < volunteers.size(); i++) {  
            Volunteer volunteer = volunteers.at(i);   // Using at(int pos) from ListInterface
            if (volunteer.getId().equals(volunteerId)) {
                volunteers.delete(i);  // Delete volunteer from the list
                return true;
            }
        }
        return false;  // Volunteer not found in the event
    }

    // Check if a volunteer is assigned to the event
    public boolean containsVolunteer(String volunteerId) {
        for (int i = 0; i < volunteers.size(); i++) {  
            Volunteer volunteer = volunteers.at(i);   // Using at(int pos)
            if (volunteer.getId().equals(volunteerId)) {
                return true;
            }
        }
        return false;
    }

    // Get the list of volunteers for this event
    public ListInterface<Volunteer> getVolunteers() {
        return volunteers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("=========================================================================")).append("\n");
        sb.append(String.format("%-10s%-20s%-20s%-20s", "Event ID", "Event Name", "Date", "Location")).append("\n");
        sb.append(String.format("=========================================================================")).append("\n");
        sb.append(String.format("%-10s%-20s%-20s%-20s", id, name, date, location)).append("\n");
        sb.append(String.format("=========================================================================")).append("\n");

        sb.append("Volunteers assigned: ").append(volunteers.size()).append("\n");
        for (int i = 0; i < volunteers.size(); i++) { 
            sb.append("  ").append(volunteers.at(i)).append("\n");
        }
        sb.append(String.format("=========================================================================")).append("\n");

        return sb.toString();
    }
}
