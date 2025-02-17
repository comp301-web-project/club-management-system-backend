package org.clubmanagementsystem.usermanagementservice.repository;


import org.clubmanagementsystem.usermanagementservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmailAndPassword(String email,String password);

    Student findByEmail(String email);

    List<Student> findAllByClubsContaining(Long clubId);
}
