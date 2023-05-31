package com.example.importCSVSpring.model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.sql.Date;
import java.util.Objects;

/**
 * Osoba
 */
@Entity
@Table(name="PERSON")
public class Person {
    /**
     * Primární klíč
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Nullable
    @Column(columnDefinition = "NUMBER(10)")
    private Long personId;
    /**
     * Index řádku souboru
     */
    @NonNull
    @Column(columnDefinition = "NUMBER(10) NOT NULL")
    private Long index;
    /**
     * Id uživatele
     */
    @NonNull
    @Column(columnDefinition = "VARCHAR2(25) NOT NULL")
    private String userId;
    /**
     * Křestní jméno
     */
    @NonNull
    @Column(columnDefinition = "VARCHAR2(50) NOT NULL")
    private String firstName;
    /**
     * Příjmení
     */
    @NonNull
    @Column(columnDefinition = "VARCHAR2(50) NOT NULL")
    private String lastName;
    /**
     * Pohlaví
     */
    @NonNull
    @Column(columnDefinition = "VARCHAR2(6) NOT NULL CHECK(SEX IN ('Male','Female'))")
    private String sex;
    /**
     * E-mail
     */
    @Nullable
    @Column(columnDefinition = "VARCHAR2(250)")
    private String email;
    /**
     * Telefonní číslo
     */
    @Nullable
    @Column(columnDefinition = "VARCHAR2(50)")
    private String phone;
    /**
     * Datum narození
     */
    @Nullable
    @Column(columnDefinition = "DATE")
    private Date dateOfBirth;
    /**
     * Popis pozice
     */
    @Nullable
    @Column(columnDefinition = "VARCHAR2(250)")
    private String jobTitle;
    /**
     * Záznam o importním souboru
     */
    @NonNull
    @ManyToOne
    @JoinColumn(name="IMPORT_ID", columnDefinition = "NUMBER(18) NOT NULL")
    private AnImport anImport;

    public Person() {
    }

    @Nullable
    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(@Nullable Long personId) {
        this.personId = personId;
    }

    @NonNull
    public Long getIndex() {
        return index;
    }

    public void setIndex(@NonNull Long index) {
        this.index = index;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    @NonNull
    public String getSex() {
        return sex;
    }

    public void setSex(@NonNull String sex) {
        this.sex = sex;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    @Nullable
    public String getPhone() {
        return phone;
    }

    public void setPhone(@Nullable String phone) {
        this.phone = phone;
    }

    @Nullable
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@Nullable Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Nullable
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(@Nullable String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @NonNull
    public AnImport getAnImport() {
        return anImport;
    }

    public void setAnImport(@NonNull AnImport anImport) {
        this.anImport = anImport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(personId, person.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId);
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", index=" + index +
                ", userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", jobTitle='" + jobTitle + '\'' +
                ", anImport=" + anImport +
                '}';
    }
}
