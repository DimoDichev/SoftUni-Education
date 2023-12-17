package UniversitySystem;

import SalesDatabase.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "university_users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Users extends BaseEntity {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Users() {
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
