package design_patterns.behavioural.strategy.with_strategy;

public class Book implements Comparable<Book> {

    private String title; // this Book's title
    private final String isbn;  // this Book's ISBN number

    /**
     * Constructs a new Book named title with ISBN number isbn.
     *
     * @param title the new Book's title
     * @param isbn  the new Book's ISBN number
     */
    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    /**
     * Gets this Book's title.
     *
     * @return this Book's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets this Book's title to title.
     *
     * @param title the new title of this Book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets this Book's ISBN number.
     *
     * @return this Book's ISBN number
     */
    public String getISBN() {
        return isbn;
    }

    @Override
    public int compareTo(Book o) {
        return this.isbn.compareTo(o.isbn);
    }

    @Override
    public String toString() {
        return title + ": " + isbn;
    }
}