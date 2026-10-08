# LearnTrack

LearnTrack is a console-based (CLI) training management system written in core Java. It lets an admin:

- Register, list, look up, and deactivate **Students**
- Create, list, and activate/deactivate **Courses**
- **Enroll** students into courses, view a student's enrollments, and update an enrollment's status (`ACTIVE` / `COMPLETED` / `CANCELLED`)

Business rules enforced by the app:

- A student, course, or enrollment ID that doesn't exist raises a clear "not found" error instead of crashing.
- A **deactivated** student cannot be enrolled into a course — this is checked as soon as the student ID is entered, before the course ID is even asked for.
- Deactivating a student automatically **cancels all of that student's active enrollments**.

All data is kept in memory for the lifetime of the program (no database/file persistence) — the focus of this project is OOP design, not storage.

## Project Structure

```
learntrack/
├── src/example/com/airtribe/learntrack/
│   ├── entity/      Person, Student, Trainer, Course, Enrollment
│   ├── service/     StudentService, CourseService, EnrollmentService
│   ├── exception/   EntityNotFoundException, InvalidInputException
│   ├── util/        IdGenerator, InputValidator
│   └── ui/           Main (CLI entry point)
├── docs/
│   ├── Design_Notes.md
│   ├── Setup_Instructions.md
│   └── JVM_Basics.md
└── README.md
```

## How to Compile and Run

From the `learntrack` project folder, run the following two commands in any terminal (Command Prompt, PowerShell, bash, zsh, etc. — same commands on every OS):

```
javac -d bin -sourcepath src src/example/com/airtribe/learntrack/ui/Main.java
java -cp bin example.com.airtribe.learntrack.ui.Main
```

The first command compiles `Main.java` and every class it depends on (via `-sourcepath`) into the `bin/` folder. The second command runs the app, starting the interactive menu in your terminal.

## Using the App

On launch you'll see a main menu:

```
========== LEARNTRACK MAIN MENU ==========
1. Student Management
2. Course Management
3. Enrollment Management
0. Exit
```

Follow the on-screen prompts to add students/courses, enroll students, and manage statuses. Invalid numeric input and missing IDs are reported as errors without crashing the program.

## Documentation

- [`docs/Design_Notes.md`](docs/Design_Notes.md) — design rationale behind key implementation choices (collections, static members, inheritance).
- [`docs/Setup_Instructions.md`](docs/Setup_Instructions.md) — JDK version used and a "Hello World" verification run.
- [`docs/JVM_Basics.md`](docs/JVM_Basics.md) — plain-language explainer on JDK vs JRE vs JVM, bytecode, and "write once, run anywhere".
