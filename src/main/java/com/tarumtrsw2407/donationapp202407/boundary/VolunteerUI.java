/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarumtrsw2407.donationapp202407.boundary;

/**
 *
 * @author Nathaniel Woo Shi Yan
 */
import com.tarumtrsw2407.donationapp202407.control.VolunteerManager;
import com.tarumtrsw2407.donationapp202407.entity.Volunteer;
import com.tarumtrsw2407.donationapp202407.adt.ListInterface;
import com.tarumtrsw2407.donationapp202407.control.EventManagementSystem;

import java.util.Scanner;
import java.util.regex.Pattern;

public class VolunteerUI {
    private VolunteerManager volunteerManager;
    private Scanner scanner;

    public VolunteerUI() {
        volunteerManager = new VolunteerManager();
        scanner = new Scanner(System.in);
    }
    
    public VolunteerUI(VolunteerManager volunteer,EventManagementSystem event) {
        this();
        volunteerManager = new VolunteerManager();
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\nVolunteer Management System");
            System.out.println("1. Add Volunteer");
            System.out.println("2. Remove Volunteer");
            System.out.println("3. Search Volunteer");
            System.out.println("4. Assign Volunteer to Event");
            System.out.println("5. Search Events under a Volunteer");
            System.out.println("6. List All Volunteers");
            System.out.println("7. Filter Volunteers by Event");
            System.out.println("8. Generate Summary Report");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid choice: ");
                scanner.next();  // discard invalid input
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addVolunteer();
                    break;
                case 2:
                    removeVolunteer();
                    break;
                case 3:
                    searchVolunteer();
                    break;
                case 4:
                    assignVolunteerToEvent();
                    break;
                case 5:
                    searchEventsUnderVolunteer();
                    break;
                case 6:
                    listAllVolunteers();
                    break;
                case 7:
                    filterVolunteersByEvent();
                    break;
                case 8:
                    generateSummaryReport();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }

    private void addVolunteer() {
        while(true){
        System.out.print("Enter Volunteer ID (Format: V[number]): ");
        String id = scanner.nextLine();

        if (!id.matches("^V\\d+$")) {
            System.out.println("Invalid ID format. It should start with 'V' followed by numbers.");
            continue;
        }

        System.out.print("Enter Volunteer Name: ");
        String name = scanner.nextLine();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            continue;
        }

        System.out.print("Enter Contact Number: ");
        String contactNumber = scanner.nextLine();

        if (!isValidPhoneNumber(contactNumber)) {
            System.out.println("Invalid contact number.");
            continue;
        }

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        if (!isValidEmail(email)) {
            System.out.println("Invalid email format.");
            continue;
        }
        Volunteer volunteer = new Volunteer(id, name, contactNumber, email);
        volunteerManager.addVolunteer(volunteer);
        System.out.println("Volunteer added successfully.");
        return;
        }
    }

    private void removeVolunteer() {
        System.out.print("Enter Volunteer ID to remove: ");
        String id = scanner.nextLine();
        boolean removed = volunteerManager.removeVolunteer(id);
        if (removed) {
            System.out.println("Volunteer removed successfully.");
        } else {
            System.out.println("Volunteer not found.");
        }
    }
    
    private void searchVolunteer(){
        System.out.println("Search by");
        System.out.println("1. Search Volunteer by ID");
        System.out.println("2. Search Volunteer by Name");
        String r = scanner.nextLine();
        switch (r){
            case "1":searchVolunteerById();break;
            case "2":searchVolunteerByName();break;
        }
    }

    private void searchVolunteerById() {
        System.out.print("Enter Volunteer ID: ");
        String id = scanner.nextLine();
        Volunteer volunteer = volunteerManager.searchVolunteerById(id);
        if (volunteer != null) {
            System.out.println(volunteer);
        } else {
            System.out.println("Volunteer not found.");
        }
    }

    private void searchVolunteerByName() {
        System.out.print("Enter Volunteer Name: ");
        String name = scanner.nextLine();
        ListInterface<Volunteer> volunteers = volunteerManager.searchVolunteerByName(name);
        if (volunteers.size() > 0) {
            for (int i = 0; i < volunteers.size(); i++) {
                System.out.println(volunteers.at(i));
            }
        } else {
            System.out.println("No volunteers found with that name.");
        }
    }

    private void assignVolunteerToEvent() {
        System.out.print("Enter Volunteer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Event Name: ");
        String event = scanner.nextLine();
        boolean assigned = volunteerManager.assignVolunteerToEvent(id, event);
        if (assigned) {
            System.out.println("Volunteer assigned to event successfully.");
        } else {
            System.out.println("Volunteer not found.");
        }
    }

    private void searchEventsUnderVolunteer() {
        System.out.print("Enter Volunteer ID: ");
        String id = scanner.nextLine();
        String event = volunteerManager.searchEventUnderVolunteer(id);
        if (event != null) {
            System.out.println("Assigned Event: " + event);
        } else {
            System.out.println("Volunteer not found or no event assigned.");
        }
    }

    private void listAllVolunteers() {
        System.out.println(volunteerManager.listAllVolunteers());
    }

    private void filterVolunteersByEvent() {
        System.out.print("Enter Event Name: ");
        String event = scanner.nextLine();
        ListInterface<Volunteer> volunteers = volunteerManager.filterVolunteersByEvent(event);
        if (volunteers.size() > 0) {
            volunteers.getIterator().forEachRemaining(System.out::println);
        } else {
            System.out.println("No volunteers found for this event.");
        }
    }

    private void generateSummaryReport() {
        String report = volunteerManager.generateSummaryReport();
        System.out.println(report);
    }

    private boolean isValidPhoneNumber(String contactNumber) {
        return contactNumber.matches("^\\+?[0-9]{10,13}$");
    }

    private boolean isValidEmail(String email) {
        return Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$").matcher(email).matches();
    }

    public VolunteerManager main() {
        return main(null);
    }
    
    public VolunteerManager main(String[] args) {
        VolunteerUI ui = new VolunteerUI();
        ui.displayMenu();
        return volunteerManager;
    }
}
