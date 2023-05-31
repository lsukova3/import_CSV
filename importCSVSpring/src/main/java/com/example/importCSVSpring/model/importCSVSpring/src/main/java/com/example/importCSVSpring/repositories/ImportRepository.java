package com.example.importCSVSpring.repositories;

import com.example.importCSVSpring.model.AnImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ImportRepository extends JpaRepository<AnImport,Long> {
}
