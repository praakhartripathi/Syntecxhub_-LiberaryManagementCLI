package com.syntecxhub.library;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibraryManager {
    private List<Book> books;
    private static final String FILE_NAME = "library_data.ser";

    public LibraryManager() {
        this.books = new ArrayList<>();
        loadBooks();
    }

    public void addBook(String title, String author, String isbn) throws IllegalArgumentException {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty.");
        }
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be empty.");
        }
        if (findBookByIsbn(isbn).isPresent()) {
            throw new IllegalArgumentException("Book with this ISBN already exists.");
        }

        books.add(new Book(title, author, isbn));
        saveBooks();
    }

    public boolean removeBook(String isbn) {
        Optional<Book> bookToRemove = findBookByIsbn(isbn);
        if (bookToRemove.isPresent()) {
            books.remove(bookToRemove.get());
            saveBooks();
            return true;
        }
        return false;
    }

    public List<Book> searchBooks(String query) {
        String lowerCaseQuery = query.toLowerCase();
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(lowerCaseQuery) ||
                        book.getAuthor().toLowerCase().contains(lowerCaseQuery) ||
                        book.getIsbn().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    private Optional<Book> findBookByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    private void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.err.println("Error saving books: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadBooks() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                books = (List<Book>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading books: " + e.getMessage());
                books = new ArrayList<>();
            }
        }
    }
}
