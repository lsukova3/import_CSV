package com.example.importCSVSpring.repositories;

import com.example.importCSVSpring.model.AnImport;
import com.example.importCSVSpring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ImportRepository extends JpaRepository<AnImport,Long> {

    @Query(value = "SELECT * " +
            "FROM IMPORT i " +
            "WHERE i.directory LIKE %:keyword% OR " +
            "      i.file_name LIKE %:keyword%", nativeQuery = true)
    public List<AnImport> findByKeyword(@Param("keyword") String keyword);
}
