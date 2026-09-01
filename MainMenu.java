import java.util.Scanner;
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
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    //Add Contact
                    System.out.println("Please enter your name: ");
                    String name = scanner.nextLine();
                    System.out.println("Please enter your phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.println("Please enter your email: ");
                    String email = scanner.nextLine();
                    Contact contact1 = new Contact(name, phoneNumber, email);
                    addressBook.addContact(contact1);
                    addressBook.showContacts();
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
                    //Edit Contact
                    System.out.println("Please enter contact name to edit: ");
                    String editName = scanner.nextLine();
                    if (addressBook.contactExists(editName)) {
                        System.out.println("Please enter new phone number: ");
                        String editPhoneNumber = scanner.nextLine();
                        System.out.println("Please enter new email: ");
                        String editEmail = scanner.nextLine();
                        addressBook.editContact(editName,editPhoneNumber,editEmail);
                    } else {
                        System.out.println("Contact does not exist");
                    }
                    break;
                case 5:
                    //Delete Contact
                    System.out.println("Please enter contact name to delete : ");
                    addressBook.deleteContact(scanner.nextLine());
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