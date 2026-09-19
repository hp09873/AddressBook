import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static
        org.junit.jupiter.api.Assertions.*;
public class AddressBookOutputTest {
    private final PrintStream originalOut =
            System.out;
    private ByteArrayOutputStream captured;
    private AddressBook book;
    @BeforeEach
    void setUp() {
        book = new AddressBook();
        captured = new ByteArrayOutputStream();
// Redirect System.out into our buffer so we can read what was printed
        System.setOut(new
                PrintStream(captured));
    }
    @AfterEach
    void restoreConsole() {
// Always put the real console back
        System.setOut(originalOut);
    }
    private String output() {
        return captured.toString().trim();
    }
    // ---------- showContacts ----------
    @Test
    void
    showContactsOnEmptyBookPrintsEmptyMessage() {
        book.showContacts();
        assertEquals("AddressBook is empty",
                output());
    }
    @Test
    void showContactsPrintsEveryContact() {
        book.addContact("Harsh", "9876543210",
                "harsh@example.com");
        book.addContact("Rahul", "9123456780",
                "rahul@example.com");
        book.showContacts();
        String out = output();
        assertTrue(out.contains("Harsh"));
        assertTrue(out.contains("Rahul"));
        assertFalse(out.contains("empty"));
        assertEquals(2,
                out.split("\\R").length);
    }
    // ---------- searchContacts ----------
    @Test
    void searchWithBlankNameAsksForValidName() {
        book.searchContacts(" ");
        assertEquals("Please enter a valid name", output());
    }
    @Test
    void searchWithNullNameAsksForValidName() {
        book.searchContacts(null);
        assertEquals("Please enter a valid name", output());
    }
    @Test
    void searchPrintsMatchingContact() {
        book.addContact("Harsh", "9876543210",
                "harsh@example.com");
        book.searchContacts("harsh");
        String out = output();
        assertTrue(out.contains("Harsh"));
        assertFalse(out.contains("Contact not found"));
    }
    @Test
    void searchPrintsAllContactsWithSameName() {
        book.addContact("Harsh", "9876543210",
                "one@example.com");
        book.addContact("Harsh", "9123456780",
                "two@example.com");
        book.searchContacts("Harsh");
        assertEquals(2,
                output().split("\\R").length);
    }
    @Test
    void searchWithNoMatchPrintsNotFound() {
        book.addContact("Harsh", "9876543210",
                "harsh@example.com");
        book.searchContacts("Nobody");
        assertEquals("Contact not found",
                output());
    }
    // ---------- Contact.toString ----------
    @Test
    void contactToStringContainsAllFields() {
        Contact c = new Contact(5, "Harsh",
                "9876543210", "harsh@example.com");
        String text = c.toString();
        assertTrue(text.contains("Harsh"));
        assertTrue(text.contains("9876543210"));
        assertTrue(text.contains("harsh@example.com"));
    }
}