import java.util.ArrayList;
import java.util.Iterator;

public class AddressBook {
    ArrayList<Contact> contacts = new ArrayList<Contact>();

    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Contact added to the AddressBook: " + contact);
    }

    public void showContacts() {
        for (Contact contact : contacts){

            System.out.println(contact.name + " " + contact.phoneNumber + " " + contact.email);
        }
    }
    public void searchContacts(String name){
        for (Contact contact : contacts){
            if (contact.name.equalsIgnoreCase(name)){
                System.out.println(contact.name + " " + contact.phoneNumber + " " + contact.email);
            }
        }
    }
    public void editContact(String name,String phoneNumber,String email ){
        for (Contact contact : contacts){
            if (contact.name.equalsIgnoreCase(name)){
                contact.phoneNumber = phoneNumber;
                contact.email = email;
                System.out.println("Contact edited successfully");
                System.out.println(contact.name + " " + contact.phoneNumber + " " + contact.email);
            }
        }
    }
    public void deleteContact(String name){
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()){
            Contact contact = iterator.next();
            if (contact.name.trim().equalsIgnoreCase(name)){
                iterator.remove();
                System.out.println("Contact deleted successfully");
                break;
            }
        }
    }
    }


