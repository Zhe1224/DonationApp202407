package com.tarumtrsw2407.donationapp202407.entity;

import com.tarumtrsw2407.donationapp202407.adt.ArrayList;
import com.tarumtrsw2407.donationapp202407.adt.ListInterface;

/**
 *
 * @author Nathaniel Woo Shi Yan
 */
public class Volunteer {
    private String id;
    private String name;
    private String contactNumber;
    private String email;
    private ListInterface<Event> assignedEvents;

    public Volunteer(String id, String name, String contactNumber, String email) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.assignedEvents = new ArrayList<>(); // List to store multiple events
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }
    
    public ListInterface<Event> getAssignedEvents() {
        return assignedEvents;
    }
    /*
    // Assign the volunteer to a new event
    public void assignEvent(Event event) {
        if (assignedEvents.getPosOf(event)<0) {
            assignedEvents.append(event); // Add the event if it's not already assigned
        }
    }

    // Unassign the volunteer from an event
    public boolean unassignEvent(Event event) {
        for (int i = 0; i < assignedEvents.size(); i++) {
            if (assignedEvents.at(i).equals(event)) {
                assignedEvents.delete(i);
                return true;
            }
        }
        return false; // Event not found in the list
    }
        */

    public void setName(String name) {
        this.name = name;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Contact Number: ").append(contactNumber).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("Assigned Events: \n");
        /*for (int i = 0; i < assignedEvents.size(); i++) {
            sb.append("  - ").append(assignedEvents.at(i)).append("\n");
        }*/
        return sb.toString();
    }
}
