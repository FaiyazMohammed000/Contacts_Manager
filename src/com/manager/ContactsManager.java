package com.manager;
import java.io.*;
import java.util.*;
public class ContactsManager {
    private HashMap<String, Contact> contacts;

    public ContactsManager() {
        contacts = new HashMap<String, Contact>();
    }

    public void addContacts(Contact contact) {
        contacts.put(contact.getName(), contact);
    }

    public Contact getContacts(String name) {
        return contacts.get(name);
    }
    public ArrayList<Contact> searchContacts(String search) {
        ArrayList<Contact> result = new ArrayList<>();
        for (Contact contact : contacts.values()) {
        if(contact.getName().toLowerCase().contains(search.toLowerCase())||contact.getPhoneNumber().toLowerCase().contains(search.toLowerCase())||contact.getEmail().toLowerCase().contains(search.toLowerCase())){
            result.add(contact);
        }
        }
        return result ;
    }
    public void updateContacts(String name, String phoneNumber, String email) {
        Contact contact = contacts.get(name);
        contact.setPhoneNumber(phoneNumber);
        contact.setEmail(email);
    }
    public void deleteContact(String name){
        contacts.remove(name);
    }
    public void importContacts(String fileName) throws Exception
    {
        ObjectInputStream input=new ObjectInputStream(new FileInputStream(fileName));
        contacts=(HashMap<String, Contact>)input.readObject();
        input.readObject();
        input.close();
    }
    public void exportContacts(String fileName)throws Exception{
        ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream(fileName));
        output.writeObject(contacts);
        output.close();
    }
}
