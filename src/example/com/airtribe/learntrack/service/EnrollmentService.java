package example.com.airtribe.learntrack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import example.com.airtribe.learntrack.entity.Enrollment;
import example.com.airtribe.learntrack.entity.Student;
import example.com.airtribe.learntrack.exception.EntityNotFoundException;
import example.com.airtribe.learntrack.exception.InvalidInputException;
import example.com.airtribe.learntrack.util.IdGenerator;

public class EnrollmentService {
    private final List<Enrollment> enrollments = new ArrayList<>();

    public Enrollment enrollStudent(int studentId, int courseId, StudentService studentService, CourseService courseService)
            throws EntityNotFoundException, InvalidInputException {
        // Validate student and course exist
        Student student = studentService.findStudentById(studentId);
        courseService.findCourseById(courseId);

        if (!student.isActive()) {
            throw new InvalidInputException("Student with ID " + studentId + " is deactivated and not applicable for enrollment.");
        }

        int id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId);
        enrollments.add(enrollment);
        return enrollment;
    }

    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        return enrollments.stream()
                .filter(e -> e.getStudentId() == studentId)
                .collect(Collectors.toList());
    }

    public void cancelEnrollmentsByStudent(int studentId) {
        enrollments.stream()
                .filter(e -> e.getStudentId() == studentId && "ACTIVE".equals(e.getStatus()))
                .forEach(e -> e.setStatus("CANCELLED"));
    }

    public void updateStatus(int enrollmentId, String status) throws EntityNotFoundException {
        Enrollment enrollment = enrollments.stream()
                .filter(e -> e.getId() == enrollmentId)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Enrollment ID " + enrollmentId + " not found."));
        enrollment.setStatus(status.toUpperCase());
    }
}