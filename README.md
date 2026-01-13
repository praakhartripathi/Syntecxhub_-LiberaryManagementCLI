# Syntecxhub_-LiberaryManagementCLI

A simple Command Line Interface (CLI) for managing books in a library.

## Features
- Add books (Title, Author, ISBN)
- Remove books by ISBN
- Search books by Title, Author, or ISBN
- List all books
- Data persistence using file storage (`library_data.ser`)

## How to Run

### Prerequisites
- Java Development Kit (JDK) installed (Java 8 or higher recommended).

### Compilation
Navigate to the project root directory and compile the Java files:

```bash
javac -d out src/com/syntecxhub/library/*.java
```

### Execution
Run the application using the `java` command, pointing to the compiled classes:

```bash
java -cp out com.syntecxhub.library.Main
```
