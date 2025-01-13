package org.clubmanagementsystem.usermanagementservice.repository;


import org.clubmanagementsystem.usermanagementservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmailAndPassword(String email,String password);
}
