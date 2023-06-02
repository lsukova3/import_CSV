package com.example.importCSVSpring.repositories;

import com.example.importCSVSpring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PersonRepository extends JpaRepository<Person,Long>, PagingAndSortingRepository<Person,Long> {
    @Query(value = "SELECT * " +
            "FROM PERSON p " +
            "WHERE p.import_id = :importId", nativeQuery = true)
    public List<Person> findByImportId(@Param("importId") Long importId);

    @Query(value = "SELECT * " +
            "FROM PERSON p " +
            "WHERE p.first_name LIKE %:keyword% OR " +
            "      p.last_name LIKE %:keyword% OR " +
            "      p.email LIKE %:keyword% OR " +
            "      p.phone LIKE %:keyword% OR " +
            "      p.job_title LIKE %:keyword%", nativeQuery = true)
    public List<Person> findByKeyword(@Param("keyword") String keyword);

    @Query(value = "SELECT * " +
            "FROM PERSON p " +
            "WHERE p.import_id = :importId " +
            "AND (p.first_name LIKE %:keyword% OR " +
            "      p.last_name LIKE %:keyword% OR " +
            "      p.email LIKE %:keyword% OR " +
            "      p.phone LIKE %:keyword% OR " +
            "      p.job_title LIKE %:keyword%)", nativeQuery = true)
    public List<Person> findByImportIdKeyword(@Param("importId") Long importId,
                                              @Param("keyword") String keyword);
}
