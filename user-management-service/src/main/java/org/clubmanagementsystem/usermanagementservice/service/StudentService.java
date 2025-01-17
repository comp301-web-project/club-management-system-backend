package org.clubmanagementsystem.usermanagementservice.service;

import org.clubmanagementsystem.usermanagementservice.dto.ClubDTO;
import org.clubmanagementsystem.usermanagementservice.model.Student;
import org.clubmanagementsystem.usermanagementservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class StudentService {

    private RestTemplate restTemplate;
    private StudentRepository studentRepository;

    @Value("${api.gateway.url}")
    private String apiGatewayUrl;

    public StudentService(StudentRepository studentRepository, RestTemplate restTemplate) {
        this.studentRepository = studentRepository;
        this.restTemplate = restTemplate;
    }

    public Student createStudent(Student student) {
        student.getName();
        student.getClubs();
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
        student.setClubs(studentDetails.getClubs());

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    public boolean loginStudent(String email, String password) {
        return studentRepository.existsByEmailAndPassword(email, password);
    }

    public ClubDTO getClubByStudentId(Long studentId) {
        String url = apiGatewayUrl + "/api/clubs/getClubByStudentId/" + studentId;
        return restTemplate.getForObject(url, ClubDTO.class);
    }

    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public List<Student> getAllStudentInClub(Long clubId) {
        return studentRepository.findAllByClubsContaining(clubId);
    }
}
