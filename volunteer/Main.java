/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package volunteer;

/**
 *
 * @author User
 */
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VolunteerManagement vm = new VolunteerManagement();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nVolunteer Management System");
            System.out.println("1. Add Volunteer");
            System.out.println("2. Remove Volunteer");
            System.out.println("3. Search Volunteer");
            System.out.println("4. Assign Volunteer to Event");
            System.out.println("5. List Events under a Volunteer");
            System.out.println("6. List All Volunteers");
            System.out.println("7. Filter Volunteers");
            System.out.println("8. Generate Summary Report");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.print("Enter Full Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Contact Info: ");
                    String contactInfo = scanner.nextLine();
                    try {
                        vm.addVolunteer(new Volunteer(name, id, contactInfo));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter Volunteer ID to Remove: ");
                    id = scanner.nextLine();
                    vm.removeVolunteer(id);
                    break;
                case 3:
                    System.out.println("Search by:");
                    System.out.println("1. ID");
                    System.out.println("2. Name");
                    int searchChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (searchChoice == 1) {
                        System.out.print("Enter Volunteer ID: ");
                        id = scanner.nextLine();
                        Volunteer volunteer = vm.searchVolunteerById(id);
                        if (volunteer != null) {
                            System.out.println(volunteer);
                        } else {
                            System.out.println("Volunteer not found.");
                        }
                    } else if (searchChoice == 2) {
                        System.out.print("Enter Volunteer Name: ");
                        name = scanner.nextLine();
                        List<Volunteer> volunteers = vm.searchVolunteerByName(name);
                        if (!volunteers.isEmpty()) {
                            for (Volunteer v : volunteers) {
                                System.out.println(v);
                            }
                        } else {
                            System.out.println("No volunteers found with that name.");
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter Volunteer ID: ");
                    id = scanner.nextLine();
                    System.out.print("Enter Event ID: ");
                    String eventId = scanner.nextLine();
                    vm.assignVolunteerToEvent(id, eventId);
                    break;
                case 5:
                    System.out.print("Enter Volunteer ID: ");
                    id = scanner.nextLine();
                    List<String> events = vm.getEventsForVolunteer(id);
                    if (!events.isEmpty()) {
                        System.out.println("Events: " + events);
                    } else {
                        System.out.println("No events found for this volunteer.");
                    }
                    break;
                case 6:
                    vm.listAllVolunteers();
                    break;
                case 7:
                    System.out.print("Enter criteria to filter (name/contact info): ");
                    String criteria = scanner.nextLine();
                    List<Volunteer> filteredVolunteers = vm.filterVolunteers(criteria);
                    if (!filteredVolunteers.isEmpty()) {
                        for (Volunteer v : filteredVolunteers) {
                            System.out.println(v);
                        }
                    } else {
                        System.out.println("No volunteers found matching the criteria.");
                    }
                    break;
                case 8:
                    vm.generateSummaryReport();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}

