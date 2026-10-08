# Setup Instructions

## JDK Version Used

```
openjdk version "17.0.20.1" 2026-08-18 LTS
OpenJDK Runtime Environment Corretto-17.0.20.12.1 (build 17.0.20.1+12-LTS)
OpenJDK 64-Bit Server VM Corretto-17.0.20.12.1 (build 17.0.20.1+12-LTS, mixed mode, sharing)
```

(Amazon Corretto build of **JDK 17**, an LTS release. Check your own version with `java -version`.)

## "Hello World" Program Run

A minimal test program was used to confirm the JDK was installed and working correctly:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

**Steps run in the terminal:**

```
> javac HelloWorld.java
> java HelloWorld
Hello, World!
```

Explanation of what happened:

1. `javac HelloWorld.java` — the Java **compiler** reads the source file and translates it into `HelloWorld.class`, a file of JVM **bytecode**. No output is printed by this step; a new `.class` file simply appears next to the source file.
2. `java HelloWorld` — the **JVM** loads `HelloWorld.class` and executes its `main` method, which prints `Hello, World!` to the terminal.

Seeing that exact line confirmed the JDK (compiler + runtime) was correctly installed before starting work on the LearnTrack project itself.
