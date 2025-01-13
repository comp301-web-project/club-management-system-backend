package org.clubmanagementsystem.usermanagementservice.repository;


import org.clubmanagementsystem.usermanagementservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmailAndPassword(String email,String password);

    /*@Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Student s WHERE s.email = :email AND s.password = :password")
    boolean existsByEmailAndPassword(@Param("email") String email, @Param("password") String password);*/
}
