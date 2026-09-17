public class Contact {
    private final int contactId;
    private String name;
    private String phoneNumber;
    private String email;


    public Contact(int contactId, String name, String phoneNumber, String email) {
        this.contactId = contactId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    //ContactId Getter
    public int getContactId() {
        return contactId;
    }
    //Getter for Name
    public String getName() {
        return name;
    }
    //Setter for name
    public void setName(String name) {
        this.name = name;
    }
    //Getter for phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }
    //Setter for phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    //Getter for email
    public String getEmail() {
        return email;
    }
    //Setter for email
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "ID:" + contactId +" | Name "+ name + " | Phone " + phoneNumber + " | Email " + email;
    }
}
