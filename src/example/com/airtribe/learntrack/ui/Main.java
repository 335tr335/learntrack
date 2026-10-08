package example.com.airtribe.learntrack.ui;

import java.util.List;
import java.util.Scanner;

import example.com.airtribe.learntrack.entity.Course;
import example.com.airtribe.learntrack.entity.Enrollment;
import example.com.airtribe.learntrack.entity.Student;
import example.com.airtribe.learntrack.exception.EntityNotFoundException;
import example.com.airtribe.learntrack.exception.InvalidInputException;
import example.com.airtribe.learntrack.service.CourseService;
import example.com.airtribe.learntrack.service.EnrollmentService;
import example.com.airtribe.learntrack.service.StudentService;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n========== LEARNTRACK MAIN MENU ==========");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Enrollment Management");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1: studentMenu(); break;
                    case 2: courseMenu(); break;
                    case 3: enrollmentMenu(); break;
                    case 0:
                        running = false;
                        System.out.println("Exiting LearnTrack. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option! Please enter 0-3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    // --- STUDENT SUB-MENU ---
    private static void studentMenu() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add New Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Deactivate Student");
        System.out.print("Choose: ");

        try {
            int option = Integer.parseInt(scanner.nextLine().trim());
            switch (option) {
                case 1:
                    System.out.print("First Name: ");
                    String fn = scanner.nextLine();
                    System.out.print("Last Name: ");
                    String ln = scanner.nextLine();
                    System.out.print("Email (leave blank to skip): ");
                    String email = scanner.nextLine();
                    System.out.print("Batch: ");
                    String batch = scanner.nextLine();

                    Student s = email.isEmpty() ?
                            studentService.addStudent(fn, ln, batch) :
                            studentService.addStudent(fn, ln, email, batch);

                    System.out.println("Success! Created: " + s.getDisplayName());
                    break;

                case 2:
                    List<Student> students = studentService.listStudents();
                    if (students.isEmpty()) System.out.println("No students registered.");
                    else students.forEach(stu -> System.out.println(stu.getDisplayName()));
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    int id = Integer.parseInt(scanner.nextLine().trim());
                    System.out.println(studentService.findStudentById(id));
                    break;

                case 4:
                    System.out.print("Enter Student ID to deactivate: ");
                    int dId = Integer.parseInt(scanner.nextLine().trim());
                    studentService.deactivateStudent(dId, enrollmentService);
                    System.out.println("Student deactivated successfully. Existing enrollments cancelled.");
                    break;

                default:
                    System.out.println("Invalid student option.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Input must be a valid number.");
        } catch (EntityNotFoundException e) {
            System.out.println("Not Found: " + e.getMessage());
        }
    }

    // --- COURSE SUB-MENU ---
    private static void courseMenu() {
        System.out.println("\n--- Course Management ---");
        System.out.println("1. Add New Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Activate/Deactivate Course");
        System.out.print("Choose: ");

        try {
            int option = Integer.parseInt(scanner.nextLine().trim());
            switch (option) {
                case 1:
                    System.out.print("Course Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Duration (in weeks): ");
                    int weeks = Integer.parseInt(scanner.nextLine().trim());

                    Course c = courseService.addCourse(name, desc, weeks);
                    System.out.println("Success! Created: " + c);
                    break;

                case 2:
                    List<Course> courses = courseService.listCourses();
                    if (courses.isEmpty()) System.out.println("No courses registered.");
                    else courses.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Enter Course ID to toggle active status: ");
                    int cId = Integer.parseInt(scanner.nextLine().trim());
                    courseService.toggleCourseActive(cId);
                    System.out.println("Course status toggled.");
                    break;

                default:
                    System.out.println("Invalid course option.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Numeric input required.");
        } catch (EntityNotFoundException e) {
            System.out.println("Not Found: " + e.getMessage());
        }
    }

    // --- ENROLLMENT SUB-MENU ---
    private static void enrollmentMenu() {
        System.out.println("\n--- Enrollment Management ---");
        System.out.println("1. Enroll Student in Course");
        System.out.println("2. View Enrollments by Student ID");
        System.out.println("3. Update Enrollment Status (COMPLETED / CANCELLED)");
        System.out.print("Choose: ");

        try {
            int option = Integer.parseInt(scanner.nextLine().trim());
            switch (option) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int sId = Integer.parseInt(scanner.nextLine().trim());
                    Student enrollStudent = studentService.findStudentById(sId);
                    if (!enrollStudent.isActive()) {
                        System.out.println("Student with ID " + sId + " is deactivated and not applicable for enrollment.");
                        break;
                    }

                    System.out.print("Enter Course ID: ");
                    int cId = Integer.parseInt(scanner.nextLine().trim());

                    Enrollment e = enrollmentService.enrollStudent(sId, cId, studentService, courseService);
                    System.out.println("Enrolled successfully! " + e);
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    int stuId = Integer.parseInt(scanner.nextLine().trim());
                    List<Enrollment> list = enrollmentService.getEnrollmentsByStudent(stuId);
                    if (list.isEmpty()) System.out.println("No enrollments found for Student ID " + stuId);
                    else list.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Enter Enrollment ID: ");
                    int eId = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Enter new status (COMPLETED / CANCELLED / ACTIVE): ");
                    String status = scanner.nextLine().trim();
                    enrollmentService.updateStatus(eId, status);
                    System.out.println("Enrollment updated.");
                    break;

                default:
                    System.out.println("Invalid enrollment option.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Numeric input required.");
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}