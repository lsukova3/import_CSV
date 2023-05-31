package com.example.importCSVSpring.model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Importní záznam
 */
@Entity
@Table(name="IMPORT")
public class AnImport {
    /**
     * Primární klíč
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Nullable
    @Column(columnDefinition = "NUMBER(18) NOT NULL")
    private Long importId;
    /**
     * Jméno souboru
     */
    @NonNull
    @Column(columnDefinition = "VARCHAR2(150) NOT NULL")
    private String fileName;
    /**
     * Časové razítko
     */
    @NonNull
    @Column(columnDefinition = "DATE NOT NULL")
    private Timestamp timestamp;
    /**
     * Seznam osob
     */
    @NonNull
    @OneToMany(mappedBy = "anImport")
    private List<Person> personList = new ArrayList<>();

    public AnImport() {
    }

    @Nullable
    public Long getImportId() {
        return importId;
    }

    public void setImportId(@Nullable Long importId) {
        this.importId = importId;
    }

    @NonNull
    public String getFileName() {
        return fileName;
    }

    public void setFileName(@NonNull String fileName) {
        this.fileName = fileName;
    }

    @NonNull
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(@NonNull Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @NonNull
    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(@NonNull List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnImport anImport = (AnImport) o;
        return Objects.equals(importId, anImport.importId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(importId);
    }

    @Override
    public String toString() {
        return "Import{" +
                "importId=" + importId +
                ", fileName='" + fileName + '\'' +
                ", timestamp=" + timestamp +
                ", personList=" + personList +
                '}';
    }
}
