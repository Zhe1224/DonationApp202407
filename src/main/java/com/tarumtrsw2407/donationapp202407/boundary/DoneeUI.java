/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarumtrsw2407.donationapp202407.boundary;

/**
 *
 * @author maxmiillian hoe hon lin
 */
import com.tarumtrsw2407.donationapp202407.entity.Donee;
import com.tarumtrsw2407.donationapp202407.control.DoneeManager;
import com.tarumtrsw2407.donationapp202407.entity.*;
import java.util.Scanner;
import utility.MessageUI;

public class DoneeUI {

    private DoneeManager doneeManager;
    private Scanner scanner;

    public DoneeUI(DoneeManager doneeManager) {
        this.doneeManager = doneeManager;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("Donee Management System");
            System.out.println("1. Add Donee");
            System.out.println("2. Remove Donee");
            System.out.println("3. Update Donee");
            System.out.println("4. Search Donee");
            System.out.println("5. List All Donees with Donations");
            System.out.println("6. Filter Donees");
            System.out.println("7. Generate Summary Report");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addDonee();
                    break;
                case 2:
                    removeDonee();
                    break;
                case 3:
                    updateDonee();
                    break;
                case 4:
                    searchDonee();
                    break;
                case 5:
                    listAllDoneesWithDonations();
                    break;
                case 6:
                    filterDonees();
                    break;
                case 7:
                    generateSummaryReport();
                    break;
                case 8:
                    MessageUI.displayExitMessage();
                    return;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        }
    }

    private void addDonee() {
        //Check if user enter correct ID
        String id;
        while (true) {
            System.out.print("Enter Donee ID: ");
            id = scanner.nextLine();
            if (id.isEmpty()) {
                System.out.println("ID cannot be empty.");
            } else if (id.matches("[a-zA-Z0-9]+")) {
                break;
            } else {
                System.out.println("Invalid ID. Only alphanumeric characters are allowed.");
            }
        }

        String name;
        while (true) {
            System.out.print("Enter Donee Name: ");
            name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
            } else if (name.matches("[a-zA-Z ]+")) {
                break;
            } else {
                System.out.println("Invalid Name. Only alphabets are allowed.");
            }
        }
        String type;
        while (true) {
            System.out.print("Enter Donee Type (Individual/Organization/Family): ");
            type = scanner.nextLine().trim();
            if (isValidDoneeType(type)) {
                break;
            } else {
                MessageUI.displayInvalidChoiceMessage();
            }
        }
        Donee donee = new Donee(id, name, type);

        // Optionally add donations
        addDonationsToDonee(donee);

        doneeManager.addDonee(donee);
        System.out.println("Donee added successfully.");
    }

    private void removeDonee() {
        System.out.println("Donee List:");
        doneeManager.listAllDoneesWithDonations();
        System.out.print("Enter Donee ID to remove: ");
        String id = scanner.nextLine();

        if (doneeManager.searchDonee(id) != null) {
            doneeManager.removeDonee(id);
            System.out.println("Donee removed successfully.");
        } else {
            System.out.println("Donee ID not found.");
        }
    }

    private void updateDonee() {

        doneeManager.listAllDoneesWithDonations();
        System.out.print("Enter Donee ID to update: ");
        String id = scanner.nextLine();
        Donee existingDonee = doneeManager.searchDonee(id);
        if (existingDonee == null) {
            System.out.println("Donee not found.");
            return;
        }

        System.out.print("Enter new Donee Name (leave empty to keep existing): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            existingDonee.setName(name);
        }

        while (true) {
            System.out.print("Enter new Donee Type (Individual/Organization/Family, leave empty to keep existing): ");
            String type = scanner.nextLine();

            if (type.isEmpty()) {
                break; // Keep existing type
            }

            if (isValidDoneeType(type)) {
                existingDonee.setType(type);
                break;
            } else {
                MessageUI.displayInvalidTypeMessage();
                // The loop will continue, asking the user for the Donee Type again
            }
        }

        // Ask if the user wants to update donations
        System.out.print("Would you like to update donations? (y/n): ");
        String updateDonations = scanner.nextLine();
        if (updateDonations.equalsIgnoreCase("y")) {
            addDonationsToDonee(existingDonee);
        }

        doneeManager.updateDonee(id, existingDonee);
        System.out.println("Donee updated successfully.");
    }

    private void addDonationsToDonee(Donee donee) {
        while (true) {
            System.out.print("Add a donation? (y/n): ");
            String addDonation = scanner.nextLine();

            if (addDonation.equalsIgnoreCase("y")) {
                double amount = validateDoubleInput("Enter Donation Amount: RM ");

                String date;
                while (true) {
                    System.out.print("Enter Donation Date (YYYY-MM-DD): ");
                    date = scanner.nextLine();

                    if (isValidDate(date)) {
                        break;
                    } else {
                        System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
                    }
                }

                Donation donation = new Donation(amount, date);
                donee.addDonation(donation);
            } else {
                break;
            }
        }
    }

    private void searchDonee() {
        doneeManager.listAllDoneesWithDonations();
        System.out.print("Enter Donee Name to search: ");
        String name = scanner.nextLine();
        Donee donee = doneeManager.searchDoneeByName(name);
        if (donee == null) {
            System.out.println("Donee not found.");
        } else {
            System.out.println(donee);
        }
    }

    private void listAllDoneesWithDonations() {
        doneeManager.listAllDoneesWithDonations();
    }

    public void generateSummaryReport(){
        String report = doneeManager.generateSummaryReport();
        System.out.println(report);
    }

    private void filterDonees() {
        System.out.print("Enter filter criteria (name/type): ");
        String criteria = scanner.nextLine();
        Donee[] donees = doneeManager.filterDoneesByCriteria(criteria);
        if (donees.length == 0) {
            System.out.println("No donees found matching criteria.");
        } else {
            for (Donee donee : donees) {
                System.out.println(donee);
            }
        }
    }

    private boolean isValidDoneeType(String type) {
        return type.equalsIgnoreCase("Individual")
                || type.equalsIgnoreCase("Organization")
                || type.equalsIgnoreCase("Family");
    }

    private boolean isValidDate(String date) {
        // Check if the date matches the format YYYY-MM-DD using a regular expression
        if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            String[] parts = date.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);

            // Validate the date components (basic validation)
            if (month < 1 || month > 12) {
                return false;
            }
            if (day < 1 || day > 31) {
                return false;
            }
            // Additional validation for months with fewer than 31 days and leap years can be added here.
            return true;
        } else {
            return false;
        }
    }

    private double validateDoubleInput(String prompt) {
        double result = -1;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print(prompt);
                result = Double.parseDouble(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return result;
    }
}
