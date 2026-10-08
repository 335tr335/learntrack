package example.com.airtribe.learntrack.util;

public class IdGenerator {
    private static int studentIdCounter = 1000;
    private static int courseIdCounter = 100;
    private static int enrollmentIdCounter = 5000;

    private IdGenerator() {} // Prevent instantiation

    public static int getNextStudentId() { return ++studentIdCounter; }
    public static int getNextCourseId() { return ++courseIdCounter; }
    public static int getNextEnrollmentId() { return ++enrollmentIdCounter; }
}