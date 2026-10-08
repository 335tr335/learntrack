package example.com.airtribe.learntrack.service;

import java.util.ArrayList;
import java.util.List;

import example.com.airtribe.learntrack.entity.Course;
import example.com.airtribe.learntrack.exception.EntityNotFoundException;
import example.com.airtribe.learntrack.util.IdGenerator;

public class CourseService {
    private final List<Course> courses = new ArrayList<>();

    public Course addCourse(String courseName, String description, int durationInWeeks) {
        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName, description, durationInWeeks, true);
        courses.add(course);
        return course;
    }

    public List<Course> listCourses() {
        return new ArrayList<>(courses);
    }

    public Course findCourseById(int id) throws EntityNotFoundException {
        return courses.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Course with ID " + id + " does not exist."));
    }

    public void toggleCourseActive(int id) throws EntityNotFoundException {
        Course course = findCourseById(id);
        course.setActive(!course.isActive());
    }
}