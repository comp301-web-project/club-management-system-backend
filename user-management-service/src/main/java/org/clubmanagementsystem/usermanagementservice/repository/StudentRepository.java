package org.clubmanagementsystem.usermanagementservice.repository;


import org.clubmanagementsystem.usermanagementservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
