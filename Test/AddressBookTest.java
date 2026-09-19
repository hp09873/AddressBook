//Will import junit jupiter test
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AddressBookTest {
private AddressBook book;

    @BeforeEach
    void setUp() {
        book = new AddressBook();

    }
    //-------Validation--------
    @Test
    void validNameIsAccepted() {
        assertTrue(book.isValidName("Harshit"));
    }
    @Test
    void invalidNamesAreRejected() {
        assertFalse(book.isValidName(null));
        assertFalse(book.isValidName(""));
        assertFalse(book.isValidName(" "));
    }
    @Test
    void phoneMustBeExactlyTenDigits() {
        assertTrue(book.isValidPhoneNumber("9876543210"));
        assertFalse(book.isValidPhoneNumber("12345"));
        assertFalse(book.isValidPhoneNumber("98765432101"));
        assertFalse(book.isValidPhoneNumber("98765abcde"));
        assertFalse(book.isValidPhoneNumber(null));
    }
    @Test
    void emailvalidation() {
        assertTrue(book.isValidEmail("hp09873@gmail.com"));
        assertFalse(book.isValidEmail("hp09873.gmail.com"));
        assertFalse(book.isValidEmail("hp09873@gmail"));
        assertFalse(book.isValidEmail("hp09873@"));
        assertFalse(book.isValidEmail(""));
        assertFalse(book.isValidEmail(" "));
        assertFalse(book.isValidEmail(null));
    }
    //-----Add Method------
    @Test
    void addContactWithValidDataSucceeds() {
        assertTrue(book.addContact("Harsh", "8340650855", "hp09873@gmail.com"));
        assertTrue(book.contactExists(1));
    }
    @Test
    void addContactTrimsName() {
        book.addContact(" Harsh ","9876543210", "harsh@gmail.com");
        assertEquals("Harsh", book.findContactById(1).getName());
    }
    @Test
    void addContactRejectsInvalidData() {
        assertFalse(book.addContact("", "9876543210", "harsh@gmail.com"));
        assertFalse(book.addContact("Harsh", "123", "harsh@gmail.com"));
        assertFalse(book.addContact("Harsh", "9876543210", "bad-email"));
        assertFalse(book.contactExists(1));
    }

    @Test
    void duplicatePhoneAndEmailIsRejected() {
        assertTrue(book.addContact("Harsh", "9876543210", "harsh@gmail.com"));
        book.addContact("Harsh", "9876543210", "harsh@gmail.com");
        assertNotNull(book.findContactByName("harsh"));
        assertNotNull(book.findContactByName("  HARSH "));
    }

    @Test
    void findContactByNameReturnsNullWhenMissing() {
        assertNull(book.findContactByName("Nobody"));
    }

    @Test
    void findContactsByNameReturnsAllMatches() {
        book.addContact("Harsh", "9876543210", "harsh1@example.com");
        book.addContact("Harsh", "9123456780", "harsh2@example.com");
        book.addContact("Rahul", "9000000001", "rahul@example.com");
        List<Contact> result = book.findContactsByName("Harsh");
        assertEquals(2, result.size());
    }

    @Test
    void findContactsByNameReturnsEmptyListWhenNoMatch() {
        assertTrue(book.findContactsByName("Nobody").isEmpty());
    }

    @Test
    void findContactByIdReturnsCorrectContact() {
        book.addContact("Harsh", "9876543210", "harsh@example.com");
        Contact c = book.findContactById(1);
        assertEquals("Harsh", c.getName());
        assertEquals("9876543210", c.getPhoneNumber());
        assertEquals("harsh@example.com", c.getEmail());
        assertNull(book.findContactById(99));
    }

    // ---------- Edit ----------

    @Test
    void editContactUpdatesAllFields() {
        book.addContact("Harsh", "9876543210", "harsh@example.com");
        assertTrue(book.editContact(1, "Harshit", "9123456780", "new@example.com"));
        Contact c = book.findContactById(1);
        assertEquals("Harshit", c.getName());
        assertEquals("9123456780", c.getPhoneNumber());
        assertEquals("new@example.com", c.getEmail());
    }

    @Test
    void editContactWithInvalidDataChangesNothing() {
        book.addContact("Harsh", "9876543210", "harsh@example.com");
        assertFalse(book.editContact(1, "Harshit", "123", "new@example.com"));
        assertEquals("Harsh", book.findContactById(1).getName());
        assertEquals("9876543210", book.findContactById(1).getPhoneNumber());
    }

    @Test
    void editMissingContactReturnsFalse() {
        assertFalse(book.editContact(99, "Harshit", "9123456780", "new@example.com"));
    }

    // ---------- Delete ----------

    @Test
    void deleteContactRemovesIt() {
        book.addContact("Harsh", "9876543210", "harsh@example.com");
        assertTrue(book.deleteContact(1));
        assertFalse(book.contactExists(1));
    }

    @Test
    void deleteMissingContactReturnsFalse() {
        assertFalse(book.deleteContact(99));
    }

    @Test
    void deletedIdIsNotReused() {
        book.addContact("Harsh", "9876543210", "harsh@example.com");
        book.deleteContact(1);
        book.addContact("Rahul", "9123456780", "rahul@example.com");
        assertFalse(book.contactExists(1));
        assertTrue(book.contactExists(2));
    }

    // ---------- Contact class ----------

    @Test
    void contactConstructorAndGettersWork() {
        Contact c = new Contact(5, "Harsh", "9876543210", "harsh@example.com");
        assertEquals(5, c.getContactId());
        assertEquals("Harsh", c.getName());
        assertEquals("9876543210", c.getPhoneNumber());
        assertEquals("harsh@example.com", c.getEmail());
    }

    @Test
    void contactSettersWork() {
        Contact c = new Contact(1, "Harsh", "9876543210", "harsh@example.com");
        c.setName("Rahul");
        c.setPhoneNumber("9123456780");
        c.setEmail("rahul@example.com");
        assertEquals("Rahul", c.getName());
        assertEquals("9123456780", c.getPhoneNumber());
        assertEquals("rahul@example.com", c.getEmail());
    }

}