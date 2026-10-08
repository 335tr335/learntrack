# Design Notes

## Why ArrayList instead of array?

`StudentService`, `CourseService`, and `EnrollmentService` all store records in a `List<T>` backed by `ArrayList` (e.g. `List<Student> students = new ArrayList<>()`). An array needs a fixed size chosen upfront, but the number of students/courses/enrollments created in a session isn't known in advance. `ArrayList` grows automatically as items are added and tracks its own size, so there's no need to manually resize an array or maintain a separate counter.

## Where are static members used, and why?

- `IdGenerator` — `studentIdCounter`, `courseIdCounter`, `enrollmentIdCounter` and the `getNextStudentId()`/`getNextCourseId()`/`getNextEnrollmentId()` methods are `static` because ID generation must be one single, shared sequence across the whole program, not per-object state. If they were instance fields, each new `IdGenerator` would restart counting and produce duplicate IDs.
- `InputValidator.validateString()` — `static` because validation is stateless logic that doesn't depend on any object's data, so it can be called directly without creating an `InputValidator` instance.

## Where is inheritance used, and what did we gain?

`Student` and `Trainer` both `extend Person`, which holds the shared fields (`id`, `firstName`, `lastName`, `email`) and a `getDisplayName()` method.

- **Code reuse:** those common fields and their getters/setters are written once in `Person` instead of being duplicated in `Student` and `Trainer`.
- **Polymorphism:** `Student` and `Trainer` each `@Override getDisplayName()` to add their own details (batch/active status, specialization) while reusing the base output via `super.getDisplayName()`. Code that handles a `Person` gets the correct role-specific display without any `instanceof` checks.
