package com.example.importCSVSpring.repositories;

import com.example.importCSVSpring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface PersonRepository extends JpaRepository<Person,Long> {
}
