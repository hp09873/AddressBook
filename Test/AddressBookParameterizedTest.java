import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
public class AddressBookParameterizedTest {
    private AddressBook book;
    @BeforeEach
    void setUp() {
        book = new AddressBook();
    }
// ---------- Phone numbers ----------
    @ParameterizedTest(name = "phone \"{0}\" is rejected")
    @NullAndEmptySource
    @ValueSource(strings = {"12345",
            "98765432101", "98765abcde", "98765 43210",
            "+919876543210", " 9876543210"})
    void invalidPhonesAreRejected(String phone)
    {
        assertFalse(book.isValidPhoneNumber(phone));
    }
    @ParameterizedTest(name = "phone \"{0}\" is accepted")
    @ValueSource(strings = {"9876543210",
            "9123456780", "0000000000"})
    void validPhonesAreAccepted(String phone) {
        assertTrue(book.isValidPhoneNumber(phone));
    }
// ---------- Emails ----------
    @ParameterizedTest(name = "email \"{0}\" is rejected")
    @NullAndEmptySource
    @ValueSource(strings = {" ",
            "hp09873.gmail.com", "hp09873@gmail",
            "hp09873@", "@example.com", "a@b..com",
            "a@b.c"})
    void invalidEmailsAreRejected(String email)
    {
        assertFalse(book.isValidEmail(email));
    }
    @ParameterizedTest(name = "email \"{0}\" is accepted")
    @ValueSource(strings = {"harsh@example.com",
            "hp09873@gmail.com", "HARSH@EXAMPLE.COM",
            "a@b.co.in", "test.user+tag@mail.example.org"})
    void validEmailsAreAccepted(String email) {
        assertTrue(book.isValidEmail(email));
    }
// ---------- Names ----------
    @ParameterizedTest(name = "name \"{0}\" is rejected")
    @NullAndEmptySource
    @ValueSource(strings = {" ", "Harsh123",
            "@@@", "-", "'", "Harsh--Kumar"})
    void invalidNamesAreRejected(String name) {
        assertFalse(book.isValidName(name));
    }
    @ParameterizedTest(name = "name \"{0}\" is accepted")
    @ValueSource(strings = {"Harsh", "Harshit",
            "Harsh Kumar", "Anne-Marie", "O'Brien"})
    void validNamesAreAccepted(String name) {
        assertTrue(book.isValidName(name));
    }
}