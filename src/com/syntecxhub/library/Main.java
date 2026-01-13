package com.syntecxhub.library;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final LibraryManager libraryManager = new LibraryManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("Welcome to the Library Management System");

        while (running) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    removeBook();
                    break;
                case "3":
                    searchBooks();
                    break;
                case "4":
                    listAllBooks();
                    break;
                case "5":
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nPlease select an option:");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Search Books");
        System.out.println("4. List All Books");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    private static void addBook() {
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        try {
            libraryManager.addBook(title, author, isbn);
            System.out.println("Book added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void removeBook() {
        System.out.print("Enter ISBN of the book to remove: ");
        String isbn = scanner.nextLine();

        if (libraryManager.removeBook(isbn)) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void searchBooks() {
        System.out.print("Enter search query (Title, Author, or ISBN): ");
        String query = scanner.nextLine();
        List<Book> results = libraryManager.searchBooks(query);

        if (results.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("Search Results:");
            for (Book book : results) {
                System.out.println(book);
            }
        }
    }

    private static void listAllBooks() {
        List<Book> books = libraryManager.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("Library is empty.");
        } else {
            System.out.println("All Books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
}
