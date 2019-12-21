package uzb.progressive_young_team.opendataproject;

import java.sql.Timestamp;

public class User {

    private String name, id, surname, password, dataRegistration, phoneNumber, imageURL;

    public User() {}

    public User(String name, String id, String surname, String password, String dataRegistration, String phoneNumber) {
        this.name = name;
        this.id = id;
        this.surname = surname;
        this.password = password;
        this.dataRegistration = dataRegistration;
        this.phoneNumber = phoneNumber;

    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getDateRegistration() {
        return dataRegistration;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getImageURL() {
        return imageURL;
    }
}
