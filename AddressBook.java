import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddressBook {
    //Encapsulation: to keep list private
    private final List<Contact> contacts = new ArrayList<>();

    public boolean addContact(Contact contact) {
        if (contact == null) {
            return false;
        }
        if (contact.getName() == null || contact.getName().trim().isEmpty()) {
            return false;
        }
        //Prevent duplicate contacts
        if (contactExists(contact.getName())){
            return false;
        }
        contacts.add(contact);
        return true;
    }
    //Display all contacts
    public void showContacts() {
        if (contacts.isEmpty()) {
            System.out.println("AddressBook is empty");
        }
        for (Contact contact : contacts) {
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
        for (Contact contact : contacts) {
            if(contact.getName().trim().equalsIgnoreCase(name.trim())){
                System.out.println(contact);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Contact not found");
        }
    }
    public boolean contactExists(String name){
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        for (Contact contact : contacts) {
            if(contact.getName().trim().equalsIgnoreCase(name.trim())){
                return true;
            }
        }
        return false;
    }
    //Edit an Existing Contact
    public boolean editContact(String name,String phoneNumber,String email ){
        if  (name == null || name.trim().isEmpty()) {
        return false;
        }
        for (Contact contact : contacts) {
            if(contact.getName().trim().equalsIgnoreCase(name.trim())){

                contact.setName(phoneNumber);
                contact.setEmail(email);

                return true;
            }
        }
        return false;
    }
    //Delete a Contact
    public boolean deleteContact(String name){
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getName().trim().equalsIgnoreCase(name.trim())) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    }


