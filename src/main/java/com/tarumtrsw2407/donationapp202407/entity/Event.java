/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarumtrsw2407.donationapp202407.entity;

import com.tarumtrsw2407.donationapp202407.adt.ArrayList;
import com.tarumtrsw2407.donationapp202407.adt.ListInterface;

/**
 *
 * @author KJ
 */


public class Event {
    private String eventId;
    private String eventName;
    private ListInterface<Volunteer> volunteers;

    public Event(String eventId, String eventName) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.volunteers = new ArrayList<>();
    }

    // Getters and setters
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public ListInterface<Volunteer> getVolunteers() {
        return volunteers;
    }

    public void addVolunteer(Volunteer volunteer) {
        volunteers.append(volunteer);
    }

    public void removeVolunteer(Volunteer volunteer) {
        volunteers.delete(volunteer);
    }

    @Override
    public String toString() {
        return "Event ID: " + eventId + ", Event Name: " + eventName;
    }
}
