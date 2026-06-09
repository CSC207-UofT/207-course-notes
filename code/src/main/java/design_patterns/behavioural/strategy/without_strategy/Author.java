package design_patterns.behavioural.strategy.without_strategy;

import java.util.ArrayList;
import java.util.List;

public abstract class Author {

    protected String name;         // this Author's name
    protected List<Book> books;    // this Author's books

    /**
     * Constructs a new Author named name
     *
     * @param name the name of the new Author
     */
    public Author(String name) {
        this.setName(name);
        this.books = new ArrayList<>();
    }

    /**
     * Returns this Author's name.
     *
     * @return this Author's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets this Author's name to name.
     *
     * @param name this Author's new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds book to this Author's list of books.
     *
     * @param book a book to be added to this Author's books
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Sorts this author's books.
     * There are different algorithms for sorting books of an author (e.g., Insertion Sort and Selection Sort).
     * Therefore, leaving the implementation of the sorting algorithm open for different authors.
     */
    public abstract void sortBooks();

    /**
     * Displays the books of this author.
     * The books of an author can be displayed in different ways (e.g., ascending order and descending order).
     * Therefore, leaving the implementation open for different authors.
     */
    public abstract void displayBooks();

    @Override
    public String toString() {
        return name + ": " + books.toString();
    }

}
