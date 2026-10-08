package example.com.airtribe.learntrack.service;

import java.util.ArrayList;
import java.util.List;

import example.com.airtribe.learntrack.entity.Student;
import example.com.airtribe.learntrack.exception.EntityNotFoundException;
import example.com.airtribe.learntrack.util.IdGenerator;

public class StudentService {
    private final List<Student> students = new ArrayList<>();

    // Method Overloading 1: Full registration
    public Student addStudent(String firstName, String lastName, String email, String batch) {
        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, email, batch, true);
        students.add(student);
        return student;
    }

    // Method Overloading 2: Registration without email
    public Student addStudent(String firstName, String lastName, String batch) {
        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, batch);
        students.add(student);
        return student;
    }

    public List<Student> listStudents() {
        return new ArrayList<>(students);
    }

    public Student findStudentById(int id) throws EntityNotFoundException {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Student with ID " + id + " does not exist."));
    }

    public void deactivateStudent(int id, EnrollmentService enrollmentService) throws EntityNotFoundException {
        Student student = findStudentById(id);
        student.setActive(false);
        enrollmentService.cancelEnrollmentsByStudent(id);
    }
}