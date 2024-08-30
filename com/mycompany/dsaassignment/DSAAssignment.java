/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.dsaassignment;

import adt.*;
import boundary.*;
import control.*;
import utility.*;
import java.util.Scanner;

/**
 *
 * @author maxmillian hoe hon lin
 */
public class DSAAssignment {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Charity Centre Management System");
            System.out.println("1. Donee Management");
            System.out.println("2. ");
            System.out.println("3. ");
            System.out.println("4. ");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Initialize the DoneeArrayList and DoneeManager
                    DoneeArrayList doneeArrayList = new DoneeArrayList();
                    DoneeManager doneeManager = new DoneeManager(doneeArrayList);

                    // Initialize the DoneeUI with the DoneeManager
                    DoneeUI doneeUI = new DoneeUI(doneeManager);

                    // Run the DoneeUI to start the application
                    doneeUI.run();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    MessageUI.displayExitMessage();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 5);
    }
}
