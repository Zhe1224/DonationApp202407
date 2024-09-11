package com.tarumtrsw2407.donationapp202407.control;

import com.tarumtrsw2407.donationapp202407.adt.ArrayList; 
import com.tarumtrsw2407.donationapp202407.entity.Event;
import com.tarumtrsw2407.donationapp202407.entity.Volunteer;
import com.tarumtrsw2407.donationapp202407.adt.ListInterface;

/**
 *
 * @author KJ
 */
public class EventManagementSystem {
    private ListInterface<Event> events;
    private ListInterface<Volunteer> volunteers;

    public EventManagementSystem() {
        this(new ArrayList<>());
    }

    public EventManagementSystem(ListInterface<Event> events) {
        this(events, new ArrayList<>());
    }

    public EventManagementSystem(ListInterface<Event> events, ListInterface<Volunteer> volunteers) {
        this.events = events;
        this.volunteers = volunteers;
    }

    // Add a new event
    public boolean addEvent(Event event) {
        if (searchEvent(event.getId()) == null) {
            events.append(event);   // Append event to the list
            return true;
        }
        return false;  // Event ID already exists
    }

    // Remove an event
    public boolean removeEvent(String eventId) {
        int index = findEventIndexById(eventId);
        if (index != -1) {
            events.delete(index);  // Delete using the correct index
            return true;
        }
        return false;
    }

    // Search for an event
    public Event searchEvent(String eventId) {
        //results
        ListInterface<Event> eventss = this.events.filter(e->e.getId().equals(eventId));
        if (eventss.size() > 0) {
            return eventss.at(0);
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
        if (events.size() == 0) {
            System.out.println("No events available.");
        } else {
            for (int i = 0; i < events.size(); i++) {
                System.out.println(events.at(i));
            }
        }
    }

    // Add a volunteer to an event
    public boolean addVolunteerToEvent(String eventId, String volunteerId) {
        Event event = searchEvent(eventId);
        Volunteer volunteer = searchVolunteer(volunteerId);
        if (event != null && volunteer != null) {
            volunteer.getAssignedEvents().append(event);  // Add event to the volunteer's list
            event.getVolunteers().append(volunteer);  // Add volunteer to the event's list
            return true;
        }
        return false;  // Either event or volunteer not found
    }

    // Remove a volunteer from an event
    public boolean removeVolunteerFromEvent(String eventId, String volunteerId) {
        Event event = searchEvent(eventId);
        if (event != null) {
            Volunteer volunteer = searchVolunteer(volunteerId);
            if (volunteer != null) {
                volunteer.getAssignedEvents().delete(event);  // Remove event from the volunteer's list
            }
            event.getVolunteers().delete(volunteer);  // Remove volunteer from the event's list
            return true;
        }
        return false;  // Event not found
    }

    // List all events for a volunteer
    public void listEventsForVolunteer(String volunteerId) {
        Volunteer volunteer = searchVolunteer(volunteerId);
        if (volunteer != null) {
            for (int i = 0; i < events.size(); i++) {
                Event event = events.at(i);
                ListInterface<Volunteer> volunteerss = event.getVolunteers().filter(v->v.getId().equals(volunteerId));
                if (volunteerss.size() > 0) {
                    System.out.println(event);
                }
            }
        } else {
            System.out.println("Volunteer not found.");
        }
    }

    // Generate summary reports
    public void generateSummaryReport() {
        System.out.println("");
        System.out.println("Summary Report:");
        System.out.println("--------------------------------------");
        System.out.println("Total Events: " + events.size());
        System.out.println("Total Volunteers: " + volunteers.size());

        System.out.println("--------------------------------------");
        System.out.println("\nEvent Details:");
        System.out.println("--------------------------------------");
        for (int i = 0; i < events.size(); i++) {
            Event event = events.at(i);
            int totalVolunteers = event.getVolunteers().size();
            System.out.println("Event ID: " + event.getId());
            System.out.println("Event Name: " + event.getName());
            System.out.println("Total Volunteers: " + totalVolunteers);
            System.out.println("--------------------------------------");
        }
    }

    // Add a new volunteer
    public boolean addVolunteer(Volunteer volunteer) {
        if (searchVolunteer(volunteer.getId()) == null) {
            volunteers.append(volunteer);   // Append volunteer to the list
            return true;
        }
        return false;  // Volunteer ID already exists
    }

    // Search for a volunteer
    public Volunteer searchVolunteer(String volunteerId) {
        for (int i = 0; i < volunteers.size(); i++) {
            Volunteer volunteer = volunteers.at(i);
            if (volunteer.getId().equals(volunteerId)) {
                return volunteer;
            }
        }
        return null;  // Volunteer not found
    }

    private int findEventIndexById(String eventId) {
        for (int i = 0; i < events.size(); i++) {
            Event event = events.at(i);
            if (event.getId().equals(eventId)) {
                return i;  // Return the index of the event
            }
        }
        return -1;  // Event not found
    }
}
