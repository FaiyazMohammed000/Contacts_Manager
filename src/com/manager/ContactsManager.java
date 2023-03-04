package com.manager;
import java.io.*;
import java.util.*;
public class ContactsManager {
    private HashMap<String, MyContacts> contacts;

    public ContactsManager() {
        contacts = new HashMap<String, MyContacts>();
    }

    public void addContacts(MyContacts contact) {
        contacts.put(contact.getName(), contact);
    }

    public MyContacts getContacts(String name) {
        return contacts.get(name);
    }
    public ArrayList<MyContacts> searchContacts(String search) {
        ArrayList<MyContacts> result = new ArrayList<>();
        for (MyContacts contact : contacts.values()) {
        if(contact.getName().toLowerCase().contains(search.toLowerCase())||contact.getPhoneNumber().toLowerCase().contains(search.toLowerCase())||contact.getEmail().toLowerCase().contains(search.toLowerCase())){
            result.add(contact);
        }
        }
        return result ;
    }
    public void updateContacts(String name, String phoneNumber, String email) {
        MyContacts contact = contacts.get(name);
        contact.setPhoneNumber(phoneNumber);
        contact.setEmail(email);
    }
    public void deleteContact(String name){
        contacts.remove(name);
    }
    public void importContacts(String fileName) throws Exception
    {
        ObjectInputStream input=new ObjectInputStream(new FileInputStream(fileName));
        contacts=(HashMap<String,MyContacts>)input.readObject();
        input.readObject();
        input.close();
    }
    public void exportContacts(String fileName)throws Exception{
        ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream(fileName));
        output.writeObject(contacts);
        output.close();
    }
}
