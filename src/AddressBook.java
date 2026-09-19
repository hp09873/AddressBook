import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    //Encapsulation: to keep list private
    private final Map<Integer, Contact> contacts = new HashMap<>();
    //Used to generate unique contact ids
    private int nextId = 1;

    //Name Input Validation
    public boolean isValidName(String name){
        return name != null && !name.trim().isEmpty() && name.trim().matches("[a-zA-Z ]+");
    }
    //Number Validation
    public boolean isValidPhoneNumber(String phoneNumber){
        return phoneNumber != null && phoneNumber.matches("\\d{10}");
    }
    //Email Validation
    public boolean isValidEmail(String email){
        return email != null && email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    // Add a contact
    public boolean addContact(String name, String phoneNumber, String email) {
        if (!isValidName(name)){return false;}
        if (!isValidPhoneNumber(phoneNumber)){return false;}
        if (!isValidEmail(email)){return false;}
        for (Contact existingContact : contacts.values()) {
            if(existingContact.getPhoneNumber().equals(phoneNumber)
            && existingContact.getEmail().equals(email)){
                return false;
            }
        }
        Contact contact = new Contact(nextId, name.trim(), phoneNumber,  email);
        contacts.put(nextId, contact);
        nextId++;
        return true;
    }

    //Find Contact By Name
    public Contact findContactByName(String name) {
        if (name == null) return null;
        for (Contact contact : contacts.values()) {
            if (contact.getName().equalsIgnoreCase(name.trim())){
                return contact;
            }
        }
        return null;
    }

    //Find all contacts by the name list
    public List<Contact>findContactsByName(String name) {
        if (name == null) return new ArrayList<>();
        List<Contact> matchingContacts = new ArrayList<>();
        for (Contact contact : contacts.values()) {
            if (contact.getName().equalsIgnoreCase(name.trim())){
                matchingContacts.add(contact);
            }
        }
        return matchingContacts;
    }

    //Display all contacts
    public void showContacts() {
        if (contacts.isEmpty()) {
            System.out.println("AddressBook is empty");
            return;
        }
        for (Contact contact : contacts.values()) {
            System.out.println(contact);
        }
    }

    //Search for a contact
    public void searchContacts(String name){
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Please enter a valid name");
            return;
        }
        boolean found = false;
        for (Contact contact : contacts.values()) {
            if(contact.getName().trim().equalsIgnoreCase(name.trim())){
                System.out.println(contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Contact not found");
        }
    }
    public boolean contactExists(int contactId) {
        return contacts.containsKey(contactId);
    }

    //Edit an Existing Contact
    public boolean editContact(int contactId,String newName, String phoneNumber,String email ){
        if (!isValidName(newName)){
            return false;
        }
        if (!isValidPhoneNumber(phoneNumber)){
            return false;
        }
        if (!isValidEmail(email)){
            return false;
        }
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            return false;
        }
        contact.setName(newName);
        contact.setPhoneNumber(phoneNumber);
        contact.setEmail(email);
        return true;
    }

    //Find Contact by ID
    public Contact findContactById(int contactId){
        return contacts.get(contactId);
    }

    //Delete a Contact
    public boolean deleteContact(int contactId){
        if (! contacts.containsKey(contactId)) {
            return false;
        }
        contacts.remove(contactId);
        return true;
    }
    }


