/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarumtrsw2407.donationapp202407.entity;

import java.util.Objects;

/**
 *
 * @author User
 */
public class Volunteer {
    private String id;
    private String name;
    private String contactNumber;
    private String email;
    private String assignedEvent;

    public Volunteer(String id, String name, String contactNumber, String email) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.assignedEvent = null; 
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
    /*eliminate*/
    public String getAssignedEvent() {
        return assignedEvent;
    }
    /*eliminate*/
    public void assignEvent(String event) {
        this.assignedEvent = event;
    }

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
        /*eliminate*/sb.append("Assigned Event: ").append(assignedEvent != null ? assignedEvent : "No event assigned").append("\n");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Volunteer other = (Volunteer) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }
    
    
}
