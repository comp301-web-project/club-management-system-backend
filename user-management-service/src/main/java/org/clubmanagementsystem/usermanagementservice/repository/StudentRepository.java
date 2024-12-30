package org.clubmanagementsystem.usermanagementservice.repository;


import org.clubmanagementsystem.usermanagementservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
