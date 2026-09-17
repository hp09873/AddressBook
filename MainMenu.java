import java.util.Scanner;
import java.util.List;
public class MainMenu {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("--------AddressBook--------");

        AddressBook addressBook = new AddressBook();

        while(true) {
            System.out.println("1. Add Contact");
            System.out.println("2. Show Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Edit Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Exit");
            System.out.println("Please enter your choice: ");

            String  input = scanner.nextLine();
            while (!input.matches("[1-6]")) {
                System.out.println("Invalid Choice! Please enter a number between 1 and 6: ");
                input = scanner.nextLine();
            }
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    // Add Contact

                    System.out.println("Please enter your name: ");
                    String name = scanner.nextLine();

                    while (!addressBook.isValidName(name)) {
                        System.out.println("Invalid name! Please a name using letters and space only: ");
                        name = scanner.nextLine();
                    }

                    Contact existingContact = addressBook.findContactByName(name);

                    if (existingContact != null) {

                        System.out.println("A contact with this name already exists:");
                        System.out.println(existingContact);

                        System.out.println("Is this the contact you want? (Yes/No)");
                        String answer = scanner.nextLine();

                        if (answer.equalsIgnoreCase("yes")) {

                            System.out.println("Please enter new contact name: ");
                            String newName = scanner.nextLine();

                            System.out.println("Please enter new contact phone: ");
                            String phoneNumber = scanner.nextLine();

                            System.out.println("Please enter new contact email: ");
                            String email = scanner.nextLine();

                            if (addressBook.editContact(
                                    existingContact.getContactId(),
                                    newName,
                                    phoneNumber,
                                    email)) {

                                System.out.println("Contact has been edited successfully!");

                            } else {

                                System.out.println("Could not edit contact!");
                            }

                        } else {

                            System.out.println("Okay, let's create a new contact.");

                            System.out.println("Please enter your phone number: ");
                            String phoneNumber = scanner.nextLine();
                            while (!addressBook.isValidPhoneNumber(phoneNumber)) {
                                System.out.println("Invalid phone number! Please a valid phone number!");
                                phoneNumber = scanner.nextLine();
                            }

                            System.out.println("Please enter your email: ");
                            String email = scanner.nextLine();
                            while (!addressBook.isValidEmail(email)) {
                                System.out.println("Invalid email! Please a valid email!");
                                email = scanner.nextLine();
                            }

                            if (addressBook.addContact(name, phoneNumber, email)) {

                                System.out.println("Contact added successfully!");

                            } else {

                                System.out.println("Contact already exists!");
                            }
                        }

                    } else {

                        System.out.println("Please enter your phone number: ");
                        String phoneNumber = scanner.nextLine();
                        while  (!addressBook.isValidPhoneNumber(phoneNumber)) {
                            System.out.println("Invalid phone number! Please a valid phone number!");
                            phoneNumber = scanner.nextLine();
                        }

                        System.out.println("Please enter your email: ");
                        String email = scanner.nextLine();
                        while (!addressBook.isValidEmail(email)) {
                            System.out.println("Invalid email! Please a valid email!");
                            email = scanner.nextLine();
                        }

                        if (addressBook.addContact(name, phoneNumber, email)) {

                            System.out.println("Contact added successfully!");

                        } else {

                            System.out.println("Contact already exists!");
                        }
                    }

                    break;
                case 2:
                    //Show Contact
                    addressBook.showContacts();
                    break;
                case 3:
                    //Search Contact
                    System.out.println("Please enter contact to search: ");
                    String searchName = scanner.nextLine();
                    addressBook.searchContacts(searchName);
                    break;
                case 4:
                    // Edit Contact

                    System.out.println("Please enter the name of the contact to edit: ");
                    String currentName = scanner.nextLine();
                    while (!addressBook.isValidName(currentName)) {
                        System.out.println("Invalid name! Please a name using letters and space only!");
                        currentName = scanner.nextLine();
                    }

                    List<Contact> contactsToEdit =
                            addressBook.findContactsByName(currentName);

                    if (contactsToEdit.isEmpty()) {

                        System.out.println("Contact not found!");

                    } else {

                        Contact contactToEdit;

                        if (contactsToEdit.size() == 1) {

                            // Only one contact has this name
                            contactToEdit = contactsToEdit.get(0);

                            System.out.println("Contact found:");
                            System.out.println(contactToEdit);

                        } else {

                            // Multiple contacts have the same name
                            System.out.println("Multiple contacts found:");

                            for (Contact contact : contactsToEdit) {
                                System.out.println(contact);
                            }

                            System.out.println(
                                    "Please enter the ID of the contact you want to edit: "
                            );

                            int editId = scanner.nextInt();
                            scanner.nextLine();

                            contactToEdit = null;

                            for (Contact contact : contactsToEdit) {
                                if (contact.getContactId() == editId) {
                                    contactToEdit = contact;
                                    break;
                                }
                            }

                            if (contactToEdit == null) {
                                System.out.println("Invalid contact ID!");
                                break;
                            }
                        }

                        System.out.println("Please enter new contact name: ");
                        String newName = scanner.nextLine();
                        while (!addressBook.isValidName(newName)) {
                            System.out.println("Invalid name! Please a name using letters and space only!");
                            newName = scanner.nextLine();
                        }

                        System.out.println("Please enter new phone number: ");
                        String editPhoneNumber = scanner.nextLine();
                        while (!addressBook.isValidPhoneNumber(editPhoneNumber)) {
                            System.out.println("Invalid phone number! Please a valid phone number!");
                            editPhoneNumber = scanner.nextLine();
                        }

                        System.out.println("Please enter new email: ");
                        String editEmail = scanner.nextLine();
                        while (!addressBook.isValidEmail(editEmail)) {
                            System.out.println("Invalid email! Please a valid email!");
                            editEmail = scanner.nextLine();
                        }

                        if (addressBook.editContact(
                                contactToEdit.getContactId(),
                                newName,
                                editPhoneNumber,
                                editEmail)) {

                            System.out.println("Contact edited successfully!");

                        } else {
                            System.out.println("Could not edit contact!");
                        }
                    }

                    break;
                case 5:
                    // Delete Contact

                    System.out.println("Please enter the name of the contact to delete: ");
                    String deleteName = scanner.nextLine();

                    List<Contact> contactsToDelete =
                            addressBook.findContactsByName(deleteName);

                    if (contactsToDelete.isEmpty()) {

                        System.out.println("Contact not found!");

                    } else if (contactsToDelete.size() == 1) {

                        Contact contactToDelete = contactsToDelete.get(0);

                        System.out.println("Contact found:");
                        System.out.println(contactToDelete);

                        System.out.println("Is this the contact you want to delete? (Yes/No)");
                        String answer = scanner.nextLine();

                        if (answer.equalsIgnoreCase("yes")) {

                            if (addressBook.deleteContact(contactToDelete.getContactId())) {
                                System.out.println("Contact deleted successfully!");
                            }

                        } else {

                            System.out.println("Okay, contact was not deleted.");
                        }

                    } else {

                        System.out.println("Multiple contacts found:");

                        for (Contact contact : contactsToDelete) {
                            System.out.println(contact);
                        }

                        System.out.println("Please enter the ID of the contact you want to delete: ");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine();

                        Contact contactToDelete = addressBook.findContactById(deleteId);

                        if (contactToDelete != null) {

                            System.out.println("You selected:");
                            System.out.println(contactToDelete);

                            System.out.println("Is this the contact you want to delete? (Yes/No)");
                            String answer = scanner.nextLine();

                            if (answer.equalsIgnoreCase("yes")) {

                                if (addressBook.deleteContact(deleteId)) {
                                    System.out.println("Contact deleted successfully!");
                                }

                            } else {

                                System.out.println("Okay, contact was not deleted.");
                            }

                        } else {

                            System.out.println("Contact ID does not exist!");
                        }
                    }

                    break;
                    case 6:
                        System.out.println("Exiting AddressBook...");
                        return;

                default:
                    System.out.println("Invalid choice");
            }
        }

    }
}
// Done 2/09/26, 00:48 am