import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static
        org.junit.jupiter.api.Assertions.*;
public class AddressBookEdgeCaseTest {
    private AddressBook book;
    @BeforeEach
    void setUp() {
        book = new AddressBook();
    }
    // ---------- add and edit should follow the same rules ----------
    // addContact trims the name, so editContact should too
    @Test
    void editContactTrimsName() {
        book.addContact("Harsh", "9876543210",
                "harsh@example.com");
        book.editContact(1, " Harshit ",
                "9876543210", "harsh@example.com");
        assertEquals("Harshit",
                book.findContactById(1).getName());
        assertNotNull(book.findContactByName("Harshit"))
        ;
    }
    // addContact blocks duplicates, so editContact should too
    @Test
    void editContactCannotCreateDuplicate() {
        book.addContact("Harsh", "9876543210",
                "harsh@example.com");
        book.addContact("Rahul", "9123456780",
                "rahul@example.com");
        assertFalse(book.editContact(2, "Rahul",
                "9876543210", "harsh@example.com"));
        assertEquals("9123456780",
                book.findContactById(2).getPhoneNumber());
    }
    // Editing a contact to its own current values must still work
    @Test
    void editContactWithItsOwnValuesStillWorks()
    {
        book.addContact("Harsh", "9876543210",
                "harsh@example.com");
        assertTrue(book.editContact(1, "Harsh",
                "9876543210", "harsh@example.com"));
    }
    @Test
    void editContactToPhoneNobodyElseHasSucceeds() {
        book.addContact("Harsh", "9876543210", "harsh@gmail.com");
        book.addContact("Rahul","9123456780", "rahul@example.com");
        assertTrue(book.editContact(2,"Rahul", "9000000001", "rahul@example.com"));
    }
    @Test
    void editContactToSamePhoneButDifferentEmailSucceeds() {
        book.addContact("Harsh", "9876543210", "harsh@gmail.com");
        book.addContact("Rahul", "9123456780", "rahul@example.com");
        assertTrue(book.editContact(2, "Rahul", "9876543210", "new@example.com"));
    }
    // The same email in different letter caseis still a duplicate
    @Test
    void duplicateCheckIgnoresEmailCase() {
        assertTrue(book.addContact("Harsh",
                "9876543210", "harsh@example.com"));
        assertFalse(book.addContact("Harsh",
                "9876543210", "HARSH@EXAMPLE.COM"));
    }
}