package mavenn.mavenn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Librarytest {

    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
    }

    @Test
    void testAddAndSearchBook() {
        library.addBook("B001", "Clean Code");
        Library.Book foundBook = library.searchBook("B001");

        assertNotNull(foundBook);
        assertEquals("B001", foundBook.getId());
        assertEquals("Clean Code", foundBook.getTitle());
        assertFalse(foundBook.isIssued());

        assertNull(library.searchBook("B999"));
    }

    @Test
    void testDuplicateBookIdThrowsException() {
        library.addBook("B001", "Clean Code");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            library.addBook("B001", "Effective Java");
        });

        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    void testIssueAvailableAndAlreadyIssuedBook() {
        library.addBook("B002", "Design Patterns");

        assertTrue(library.issueBook("B002"));
        assertTrue(library.searchBook("B002").isIssued());

        assertFalse(library.issueBook("B002"));
        assertFalse(library.issueBook("B999"));
    }

    @Test
    void testReturnBookAndUpdateAvailability() {
        library.addBook("B003", "Refactoring");

        assertFalse(library.returnBook("B003"));

        library.issueBook("B003");
        assertTrue(library.searchBook("B003").isIssued());

        assertTrue(library.returnBook("B003"));
        assertFalse(library.searchBook("B003").isIssued());

        assertTrue(library.issueBook("B003"));
    }

    @Test
    void testIssuedAndReturnedSuccess() {
        library.addBook("B004", "Java Programming");
        library.issueBook("B004");
        assertTrue(library.searchBook("B004").isIssued());

        library.returnBook("B004");
        assertFalse(library.searchBook("B004").isIssued());
    }
}