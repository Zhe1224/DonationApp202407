/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package volunteer;

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Volunteer {
    private String name;
    private String id;
    private String contactInfo;
    private List<String> events;  

    public Volunteer(String name, String id, String contactInfo) {
        if (!isValidFullName(name)) {
            throw new IllegalArgumentException("Name must be a full name (first and last name).");
        }
        this.name = name;
        this.id = id;
        this.contactInfo = contactInfo;
        this.events = new ArrayList<>();
    }

    private boolean isValidFullName(String name) {
        return name.trim().split("\\s+").length >= 2;
    }

   
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public List<String> getEvents() {
        return events;
    }

    public void addEvent(String eventId) {
        if (!events.contains(eventId)) {
            events.add(eventId);
        }
    }

    public void removeEvent(String eventId) {
        events.remove(eventId);
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", events=" + events +
                '}';
    }
}

