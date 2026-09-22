import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddressBookListingTest {

    private AddressBook book;

    @BeforeEach
    void setUp() {
        book = new AddressBook();
    }

    @Test
    void getAllContactsOnEmptyBookReturnsEmptyList() {
        assertTrue(book.getAllContacts().isEmpty());
    }

    @Test
    void getAllContactsReturnsEveryContact() {
        book.addContact("Harsh", "9876543210", "harsh@example.com");
        book.addContact("Rahul", "9123456780", "rahul@example.com");

        List<Contact> all = book.getAllContacts();

        assertEquals(2, all.size());
    }

    // The returned list is a copy, so changing it must not change the address book
    @Test
    void changingReturnedListDoesNotChangeTheBook() {
        book.addContact("Harsh", "9876543210", "harsh@example.com");

        book.getAllContacts().clear();

        assertEquals(1, book.getAllContacts().size());
    }
}