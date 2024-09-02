/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarumtrsw2407.donationapp202407.boundary;

/**
 *
 * @author User
 */
import com.tarumtrsw2407.donationapp202407.control.VolunteerManager;
import com.tarumtrsw2407.donationapp202407.entity.Volunteer;
import com.tarumtrsw2407.donationapp202407.adt.ListInterface;
import java.util.Iterator;

import java.util.Scanner;

public class VolunteerUI {
    private VolunteerManager volunteerManager;
    private Scanner scanner;

    public VolunteerUI() {
        volunteerManager = new VolunteerManager();
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\nVolunteer Management System");
            System.out.println("1. Add Volunteer");
            System.out.println("2. Remove Volunteer");
            System.out.println("3. Search Volunteer by ID");
            System.out.println("4. Search Volunteer by Name");
            System.out.println("5. Assign Volunteer to Event");
            System.out.println("6. Search Events under a Volunteer");
            System.out.println("7. List All Volunteers");
            System.out.println("8. Filter Volunteers by Event");
            System.out.println("9. Generate Summary Report");
            System.out.println("10. Exit");
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
                    searchVolunteerById();
                    break;
                case 4:
                    searchVolunteerByName();
                    break;
                case 5:
                    assignVolunteerToEvent();
                    break;
                case 6:
                    searchEventsUnderVolunteer();
                    break;
                case 7:
                    listAllVolunteers();
                    break;
                case 8:
                    filterVolunteersByEvent();
                    break;
                case 9:
                    generateSummaryReport();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 10);
    }

    private void addVolunteer() {
        System.out.print("Enter Volunteer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Volunteer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        Volunteer volunteer = new Volunteer(id, name, contactNumber, email);
        volunteerManager.addVolunteer(volunteer);
        System.out.println("Volunteer added successfully.");
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
        ListInterface<Volunteer> volunteers = volunteerManager.listAllVolunteers();
        for (int i = 0; i < volunteers.size(); i++) {
            System.out.println(volunteers.at(i));
        }
    }

    private void filterVolunteersByEvent() {
        System.out.print("Enter Event Name: ");
        String event = scanner.nextLine();
        ListInterface<Volunteer> volunteers = volunteerManager.filterVolunteersByEvent(event);
        if (volunteers.size() > 0) {
            Iterator<Volunteer> i=volunteers.getIterator();
            while (i.hasNext()) System.out.println(i.next());     
        } else {
            System.out.println("No volunteers found for this event.");
        }
    }

    private void generateSummaryReport() {
        String report = volunteerManager.generateSummaryReport();
        System.out.println(report);
    }

    public static void main(String[] args) {
        VolunteerUI ui = new VolunteerUI();
        ui.displayMenu();
    }
}
