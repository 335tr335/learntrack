# JVM Basics

## What is JDK, JRE, JVM?

- **JVM (Java Virtual Machine)** — the program that actually runs compiled Java code. It takes bytecode and executes it on your specific operating system and hardware. Every platform (Windows, macOS, Linux) has its own JVM implementation, but they all understand the same bytecode.
- **JRE (Java Runtime Environment)** — the JVM plus the standard Java class libraries (`java.util`, `java.io`, etc.) needed to actually *run* a Java program. It does **not** include the compiler, so you can run Java apps with a JRE but not build them.
- **JDK (Java Development Kit)** — the full development kit: a JRE (so you can run code) **plus** the compiler (`javac`) and other developer tools (like `jar`, `javadoc`, debugger). You need the JDK to write and compile Java programs; this project was built and compiled using the JDK (see `Setup_Instructions.md`).

In short: **JDK ⊃ JRE ⊃ JVM** — the JDK contains the JRE, and the JRE contains the JVM.

## What is bytecode?

When you compile a `.java` file with `javac`, it isn't translated directly into machine code for your specific CPU. Instead, it's translated into **bytecode** — an intermediate, platform-independent set of instructions stored in a `.class` file. Bytecode is not tied to Windows, macOS, Linux, or any particular processor; it's only meaningful to a JVM, which reads it and translates it into real machine instructions at run time.

## What does "write once, run anywhere" mean?

Because `javac` compiles Java source into platform-independent bytecode rather than OS-specific machine code, the exact same `.class` file can be handed to a JVM running on Windows, macOS, or Linux, and each one will execute it correctly — without recompiling the source for each platform. The only requirement is that a compatible JVM is installed on the target machine.

This is what people mean by "write once, run anywhere" (WORA): a developer writes and compiles their Java code a single time, and the resulting bytecode can then run unmodified on any device or operating system that has a JVM, since the JVM — not the application — handles adapting to the underlying platform.
