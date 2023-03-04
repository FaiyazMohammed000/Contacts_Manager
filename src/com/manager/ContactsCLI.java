package com.manager;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactsCLI {
    private static ContactsManager contactsManager;
    private static Scanner scanner;
    public static void main(String[] args)throws Exception {
        contactsManager = new ContactsManager();
        scanner = new Scanner(System.in);
        //Load contact from file(if exists)
        File file = new File("contacts.dat");
        if (file.exists()) {
            try {
                contactsManager.importContacts("contacts.dat");

            } catch (Exception e) {
                System.out.println("Unable to load contacts from file:" + e.getMessage());
            }
        }
         //display main menu
         while (true){
             System.out.println("Contacts Manager");
             System.out.println("1.Add Contact");
             System.out.println("2.Search Contact");
             System.out.println("3.Update Contact");
             System.out.println("4.Delete Contact");
             System.out.println("5.Exit");
             //get user input
             String choice= scanner.nextLine();
             switch (choice){
                 case "1":
                     addContact();
                     break;
                 case "2":
                     searchContacts();
                     break;
                 case "3":
                     updateContact();
                     break;
                 case "4":
                     deleteContact();
                     break;
                 case "5":
                     //save contacts to file and exit
                     try {
                         contactsManager.exportContacts("contacts.dat");
                         System.out.println("Contacts saved to file.");
                     }
                     catch (Exception e){
                         System.out.println("Unable to save contacts to file:"+e.getMessage());
                     }
                     System.out.println("Thank you for using me :) Bye");
                     System.exit(0);
                     break;
             }
         }
    }
    private static void addContact(){
        System.out.println("Add Contact");
        System.out.println("Enter name");
        String name= scanner.nextLine();
        System.out.println("Enter the phone number");
        String phoneNumber=scanner.nextLine();
        System.out.println("Enter the email");
        String email=scanner.nextLine();
        Contact contact=new Contact(name,phoneNumber,email);
        contactsManager.addContacts(contact);
        System.out.println("Contact added:"+contact);
    }
    private static void searchContacts(){
        System.out.println("Search Contacts");
        System.out.println("Enter search query.");
        String search=scanner.nextLine();
        ArrayList<Contact> results=contactsManager.searchContacts(search);
        if(results.size()==0){
            System.out.println("No results found");
        }
        else {
            for (Contact c:results){
                System.out.println("your results found: ");
                System.out.println(c);
            }
        }
    }
    private static void updateContact() {
        System.out.println("Update Contact");
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        Contact c = contactsManager.getContacts(name);
        if (c == null) {
            System.out.println("contact not found");
            return;
        }
        System.out.println("Enter new phone number:");
        String phoneNumber = scanner.nextLine();
        if (!phoneNumber.isEmpty()) {
            c.setPhoneNumber(phoneNumber);
        }
        System.out.println("Enter a new email:");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            c.setEmail(email);
        }
        contactsManager.updateContacts(name, c.getPhoneNumber(), c.getEmail());
        System.out.println("Contact updated:" + c);
    }
    private static void deleteContact(){
        System.out.println("Delete Contact");
        System.out.println("Enter name:");
        String name= scanner.nextLine();
        Contact c=contactsManager.getContacts(name);
        if(c==null){
            System.out.println("Contact not found.");
            return;
        }
        contactsManager.deleteContact(name);
        System.out.println("Contact deleted:"+c);
    }

}
