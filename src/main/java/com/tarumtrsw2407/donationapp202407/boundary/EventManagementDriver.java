package com.tarumtrsw2407.donationapp202407.boundary;

/**
 *
 * @author KJ
 */
import com.tarumtrsw2407.donationapp202407.control.EventManagementSystem;
import com.tarumtrsw2407.donationapp202407.control.VolunteerManager;
import com.tarumtrsw2407.donationapp202407.entity.Event;
import com.tarumtrsw2407.donationapp202407.entity.Volunteer;
import java.util.Scanner;


public class EventManagementDriver {
    private EventManagementSystem eventManagementSystem = new EventManagementSystem();
    private VolunteerManager volunteerManager;

    public EventManagementDriver(EventManagementSystem eventManagementSystem,VolunteerManager volunteerManager) {
        this.eventManagementSystem=eventManagementSystem;
        this.volunteerManager = volunteerManager;
    }
    
    public EventManagementSystem main(){
        main(null);
        return eventManagementSystem;
    }

    public void main(String[] args) {
//        EventManagementSystem eventManagementSystem = new EventManagementSystem();
        Scanner scanner = new Scanner(System.in);

        // Preset Data
        addPresetData(eventManagementSystem);

        // User Interaction
        while (true) {
            System.out.println("\nEvent Management System");
            System.out.println("1. Add a New Event");
            System.out.println("2. Remove an Event");
            System.out.println("3. Search an Event");
            System.out.println("4. Amend an Event Details");
            System.out.println("5. List All Events");
            System.out.println("6. Add a Volunteer to an Event");
            System.out.println("7. Remove a Volunteer from an Event");
            System.out.println("8. List All Events for a Volunteer");
            System.out.println("9. Generate Summary Reports");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addEvent(eventManagementSystem, scanner);
                    break;
                case 2:
                    removeEvent(eventManagementSystem, scanner);
                    break;
                case 3:
                    searchEvent(eventManagementSystem, scanner);
                    break;
                case 4:
                    amendEventDetails(eventManagementSystem, scanner);
                    break;
                case 5:
                    eventManagementSystem.listAllEvents();
                    break;
                case 6:
                    assignVolunteerToEvent(eventManagementSystem, scanner);
                    break;
                case 7:
                    removeVolunteerFromEvent(eventManagementSystem, scanner);
                    break;
                case 8:
                    listEventsForVolunteer(eventManagementSystem, scanner);
                    break;
                case 9:
                    eventManagementSystem.generateSummaryReport();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addPresetData(EventManagementSystem eventManagementSystem) {
        // Preset Events
        eventManagementSystem.addEvent(new Event("E001", "Charity Run", "2024-09-10", "Central Park"));
        eventManagementSystem.addEvent(new Event("E002", "Food Drive", "2024-09-12", "Community Center"));

        // Preset Volunteers
        eventManagementSystem.addVolunteer(new Volunteer("V001", "Alice","745", "alice@example.com"));
        eventManagementSystem.addVolunteer(new Volunteer("V002", "Bob","457", "bob@example.com"));
        eventManagementSystem.addVolunteer(new Volunteer("V003", "Jason","040", "jason@example.com"));
        eventManagementSystem.addVolunteer(new Volunteer("V004", "Sandra","041", "sandra@example.com"));

        // Assign Volunteers to Events
        eventManagementSystem.addVolunteerToEvent("E001", "V001");
        eventManagementSystem.addVolunteerToEvent("E001", "V002");
        eventManagementSystem.addVolunteerToEvent("E002", "V001");
    }

    private static void addEvent(EventManagementSystem eventManagementSystem, Scanner scanner) {
        System.out.print("Enter Event ID: ");
        String eventId = scanner.nextLine();
        if (eventManagementSystem.searchEvent(eventId) != null) {
            System.out.println("Error: Event ID already exists.");
            return;
        }

        System.out.print("Enter Event Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Event Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter Event Location: ");
        String location = scanner.nextLine();

        eventManagementSystem.addEvent(new Event(eventId, name, date, location));
        System.out.println("Event added successfully.");
    }

    private static void removeEvent(EventManagementSystem eventManagementSystem, Scanner scanner) {
        System.out.print("Enter Event ID to remove: ");
        String eventId = scanner.nextLine();
        if (eventManagementSystem.removeEvent(eventId)) {
            System.out.println("Event removed successfully.");
        } else {
            System.out.println("Error: Event not found.");
        }
    }

    private static void searchEvent(EventManagementSystem eventManagementSystem, Scanner scanner) {
        System.out.print("Enter Event ID to search: ");
        String eventId = scanner.nextLine();
        Event event = eventManagementSystem.searchEvent(eventId);
        if (event != null) {
            System.out.println("Event found:\n" + event);
        } else {
            System.out.println("Error: Event not found.");
        }
    }

    private static void amendEventDetails(EventManagementSystem eventManagementSystem, Scanner scanner) {
        System.out.print("Enter Event ID to amend: ");
        String eventId = scanner.nextLine();
        Event event = eventManagementSystem.searchEvent(eventId);
        if (event != null) {
            System.out.print("Enter new Event Name (leave blank to keep current): ");
            String newName = scanner.nextLine();
            System.out.print("Enter new Event Date (YYYY-MM-DD) (leave blank to keep current): ");
            String newDate = scanner.nextLine();
            System.out.print("Enter new Event Location (leave blank to keep current): ");
            String newLocation = scanner.nextLine();

            if (!newName.isEmpty()) {
                event.setName(newName);
            }
            if (!newDate.isEmpty()) {
                event.setDate(newDate);
            }
            if (!newLocation.isEmpty()) {
                event.setLocation(newLocation);
            }

            System.out.println("Event details amended successfully.");
        } else {
            System.out.println("Error: Event not found.");
        }
    }

    private static void assignVolunteerToEvent(EventManagementSystem eventManagementSystem, Scanner scanner) {
        System.out.print("Enter Event ID: ");
        String eventId = scanner.nextLine();
        Event event = eventManagementSystem.searchEvent(eventId);
        if (event == null) {
            System.out.println("Error: Event not found.");
            return;
        }

        System.out.print("Enter Volunteer ID: ");
        String volunteerId = scanner.nextLine();
        Volunteer volunteer = eventManagementSystem.searchVolunteer(volunteerId);
        if (volunteer == null) {
            System.out.println("Error: Volunteer not found.");
            return;
        }

        eventManagementSystem.addVolunteerToEvent(eventId, volunteerId);
        System.out.println("Volunteer assigned to event successfully.");
    }

    private static void removeVolunteerFromEvent(EventManagementSystem eventManagementSystem, Scanner scanner) {
        System.out.print("Enter Event ID: ");
        String eventId = scanner.nextLine();
        Event event = eventManagementSystem.searchEvent(eventId);
        if (event == null) {
            System.out.println("Error: Event not found.");
            return;
        }

        System.out.print("Enter Volunteer ID to remove: ");
        String volunteerId = scanner.nextLine();
        if (eventManagementSystem.removeVolunteerFromEvent(eventId, volunteerId)) {
            System.out.println("Volunteer removed from event successfully.");
        } else {
            System.out.println("Error: Volunteer not found in the event.");
        }
    }

    private static void listEventsForVolunteer(EventManagementSystem eventManagementSystem, Scanner scanner) {
        System.out.print("Enter Volunteer ID: ");
        String volunteerId = scanner.nextLine();
        Volunteer volunteer = eventManagementSystem.searchVolunteer(volunteerId);
        
        if (volunteer == null) {
            System.out.println("Error: Volunteer not found.");
            return;
        }
        String volname=volunteer.getName();
        System.out.println("\nVolunteer Name: "+volname);
        eventManagementSystem.listEventsForVolunteer(volunteerId);
    }
}
