package sampoko.vaadinspringdemo.model.jpa.hibernate;


import sampoko.vaadinspringdemo.model.Gender;
import sampoko.vaadinspringdemo.validator.Age;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Applicant implements Serializable, Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "First name shall not be empty")
    @Size(min = 1, max = 50, message = "First name length must be 1-50")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last name shall not be empty")
    @Size(min = 1, max = 50, message = "Last name length must be 1-50")
    @Column(name = "last_name")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    @NotNull(message = "Birth date shall not be empty")
    @Age(min = 15, max = 100, message = "Age must be between 15 and 100 years")
    private Date dateOfBirth;

    @NotNull(message = "Gender shall not be empty")
    private Integer gender;

    @NotNull(message = "Motivation shall not be empty")
    @Size(min = 1,max = 1000, message = "Motivation length must be 1-1000")
    private String motivation;

    public Applicant() {
        this.gender = Gender.values()[0].getValue();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }


    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return Gender.getGender(this.gender);
    }

    public void setGender(Gender gender) {
        this.gender = gender.getValue();
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    @Override
    public Applicant clone() throws CloneNotSupportedException {
        try {
            return (Applicant) BeanUtils.cloneBean(this);
        } catch (Exception ex) {
            throw new CloneNotSupportedException();
        }
    }

}
