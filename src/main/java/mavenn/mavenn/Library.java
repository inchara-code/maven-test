package mavenn.mavenn;

import java.util.HashMap;
import java.util.Map;

public class Library {

    public static class Book {
        private final String id;
        private final String title;
        private boolean issued;

        public Book(String id, String title) {
            this.id = id;
            this.title = title;
            this.issued = false;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public boolean isIssued() {
            return issued;
        }

        public void setIssued(boolean issued) {
            this.issued = issued;
        }
    }

    private final Map<String, Book> books = new HashMap<>();

    public void addBook(String id, String title) {
        if (id == null || id.trim().isEmpty() || title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID and title cannot be null or empty.");
        }
        if (books.containsKey(id)) {
            throw new IllegalArgumentException("A book with ID '" + id + "' already exists.");
        }
        books.put(id, new Book(id, title));
    }

    public Book searchBook(String id) {
        return books.get(id);
    }

    public boolean issueBook(String id) {
        Book book = books.get(id);
        if (book == null || book.isIssued()) {
            return false;
        }
        book.setIssued(true);
        return true;
    }

    public boolean returnBook(String id) {
        Book book = books.get(id);
        if (book == null || !book.isIssued()) {
            return false;
        }
        book.setIssued(false);
        return true;
    }
}