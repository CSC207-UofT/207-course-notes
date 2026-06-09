package design_patterns.behavioural.strategy.with_strategy;

import design_patterns.behavioural.strategy.display_strategies.Displayer;
import design_patterns.behavioural.strategy.sort_strategies.Sorter;

import java.util.ArrayList;
import java.util.List;

public class Author {

    private String name;         // this Author's name
    private final List<Book> books;    // this Author's books
    private Sorter<Book> sorter; // this Author's sorting strategy
    private Displayer<Book> displayer;


    /**
     * Constructs a new Author named name that uses sorting strategy sorter
     * to sort books.
     *
     * @param name   the name of the new Author
     * @param sorter the sorting strategy used to sort books
     */
    public Author(String name, Sorter<Book> sorter, Displayer<Book> displayer) {
        this.setName(name);
        this.books = new ArrayList<>();
        setSorter(sorter);
        setDisplayer(displayer);
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
     * Sets the sorter for this author.
     */
    public void setSorter(Sorter<Book> sorter) {
        this.sorter = sorter;
    }

    /**
     * Set the displayer for this author.
     */
    public void setDisplayer(Displayer<Book> displayer) {
        this.displayer = displayer;
    }

    /**
     * Sorts this Author's books.
     */
    public void sortBooks() {
        sorter.sort(books);
    }

    public void displayBooks() {
        displayer.display(books);
    }

    @Override
    public String toString() {
        return name + ": " + books.toString();
    }
}
