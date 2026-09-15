public class Contact {
    private String name;
    private String phoneNumber;
    private String email;


    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
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
        return   name + " " + phoneNumber + " " + email;
    }
}
