package com.campus.student_management.service;

import com.campus.student_management.entity.Student;
import com.campus.student_management.exception.StudentNotFoundException;
import com.campus.student_management.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    // CREATE
    public Student createStudent(Student student) {

        return studentRepository.save(student);
    }

    // READ ALL
    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }
    //UPDATE
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(id)
         .orElseThrow();
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setDepartment(updatedStudent.getDepartment());
        existingStudent.setAge(updatedStudent.getAge());
        return studentRepository.save(existingStudent);
    }
    // DELETE
    public String deleteStudent(Long id) {

        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }
}
