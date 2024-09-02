/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarumtrsw2407.donationapp202407.control;

import com.tarumtrsw2407.donationapp202407.adt.ArrayList2;
import com.tarumtrsw2407.donationapp202407.entity.Event;
import com.tarumtrsw2407.donationapp202407.entity.Volunteer;
import com.tarumtrsw2407.donationapp202407.adt.ListInterface2;

/**
 *
 * @author KJ
 */


public class EventManagementSystem {
    private ListInterface2<Event> events;
    private ListInterface2<Volunteer> volunteers;

    public EventManagementSystem() {
        this.events = new ArrayList2<>();
        this.volunteers = new ArrayList2<>();
    }

    // Add a new event
    public boolean addEvent(Event event) {
        if (searchEvent(event.getId()) == null) {
            return events.add(event);
        }
        return false;  // Event ID already exists
    }

    // Remove an event
    public boolean removeEvent(String eventId) {
        int index = findEventIndexById(eventId);
        if (index != -1) {
            events.remove(index + 1);  // +1 for 1-based index in ListInterface
            return true;
        }
        return false;
    }

    // Search for an event
    public Event searchEvent(String eventId) {
        for (int i = 1; i <= events.getNumberOfEntries(); i++) {
            Event event = events.getEntry(i);
            if (event.getId().equals(eventId)) {
                return event;
            }
        }
        return null;  // Event not found
    }

    // Amend an event's details
    public boolean amendEventDetails(String eventId, String newName, String newDate, String newLocation) {
        Event event = searchEvent(eventId);
        if (event != null) {
            if (!newName.isEmpty()) event.setName(newName);
            if (!newDate.isEmpty()) event.setDate(newDate);
            if (!newLocation.isEmpty()) event.setLocation(newLocation);
            return true;
        }
        return false;  // Event not found
    }

    // List all events
    public void listAllEvents() {
        if (events.isEmpty()) {
            System.out.println("No events available.");
        } else {
            for (int i = 1; i <= events.getNumberOfEntries(); i++) {
                System.out.println(events.getEntry(i));
            }
        }
    }

    // Add a volunteer to an event
    public boolean addVolunteerToEvent(String eventId, String volunteerId) {
        Event event = searchEvent(eventId);
        Volunteer volunteer = searchVolunteer(volunteerId);
        if (event != null && volunteer != null) {
            return event.addVolunteer(volunteer);
        }
        return false;  // Either event or volunteer not found
    }

    // Remove a volunteer from an event
    public boolean removeVolunteerFromEvent(String eventId, String volunteerId) {
        Event event = searchEvent(eventId);
        if (event != null) {
            return event.removeVolunteer(volunteerId);
        }
        return false;  // Event not found
    }

    // List all events for a volunteer
    public void listEventsForVolunteer(String volunteerId) {
        Volunteer volunteer = searchVolunteer(volunteerId);
        if (volunteer != null) {
            for (int i = 1; i <= events.getNumberOfEntries(); i++) {
                Event event = events.getEntry(i);
                if (event.containsVolunteer(volunteerId)) {
                    System.out.println(event);
                }
            }
        } else {
            System.out.println("Volunteer not found.");
        }
    }

    // Generate summary reports
//    public void generateSummaryReport() {
//        System.out.println("Summary Report:");
//        System.out.println("Total Events: " + events.getNumberOfEntries());
//        System.out.println("Total Volunteers: " + volunteers.getNumberOfEntries());
//        // Additional summary details can be added here
//    }
    public void generateSummaryReport() {
        System.out.println("");
        System.out.println("Summary Report:");
        System.out.println("--------------------------------------");
        System.out.println("Total Events: " + events.getNumberOfEntries());
        System.out.println("Total Volunteers: " + volunteers.getNumberOfEntries());

        System.out.println("--------------------------------------");
        System.out.println("\nEvent Details:");
        System.out.println("--------------------------------------");
        for (int i = 1; i <= events.getNumberOfEntries(); i++) {
            Event event = events.getEntry(i);
            int totalVolunteers = event.getVolunteers().getNumberOfEntries();
            System.out.println("Event ID: " + event.getId());
            System.out.println("Event Name: " + event.getName());
            System.out.println("Total Volunteers: " + totalVolunteers);
            System.out.println("--------------------------------------");
        }
        
}


    // Add a new volunteer
    public boolean addVolunteer(Volunteer volunteer) {
        if (searchVolunteer(volunteer.getId()) == null) {
            return volunteers.add(volunteer);
        }
        return false;  // Volunteer ID already exists
    }

    // Search for a volunteer
    public Volunteer searchVolunteer(String volunteerId) {
        for (int i = 1; i <= volunteers.getNumberOfEntries(); i++) {
            Volunteer volunteer = volunteers.getEntry(i);
            if (volunteer.getId().equals(volunteerId)) {
                return volunteer;
            }
        }
        return null;  // Volunteer not found
    }

    private int findEventIndexById(String eventId) {
        for (int i = 1; i <= events.getNumberOfEntries(); i++) {
            Event event = events.getEntry(i);
            if (event.getId().equals(eventId)) {
                return i - 1;  // Convert 1-based index to 0-based index for ArrayList
            }
        }
        return -1;  // Event not found
    }
    
    
}
