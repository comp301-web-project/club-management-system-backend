package org.clubmanagementsystem.usermanagementservice.service;

import org.clubmanagementsystem.usermanagementservice.model.Student;
import org.clubmanagementsystem.usermanagementservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        student.setCreatedDate(new Date().getTime());
        student.setUpdatedDate(new Date().getTime());
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = getStudentById(id);

        student.setName(studentDetails.getName());
        student.setDescription(studentDetails.getDescription());
        student.setStartDate(studentDetails.getStartDate());
        student.setEndDate(studentDetails.getEndDate());
        student.setClubId(studentDetails.getClubId());
        student.setUpdatedDate(new Date().getTime());

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }
}
