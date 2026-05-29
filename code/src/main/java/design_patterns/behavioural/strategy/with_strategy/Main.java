package design_patterns.behavioural.strategy.with_strategy;

import design_patterns.behavioural.strategy.display_strategies.*;
import design_patterns.behavioural.strategy.sort_strategies.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Design Pattern: Strategy
 *
 * @author Mahsa Sadi
 * @since 2020 - 08 - 01
 */
public class Main {


    public static void main(String[] args) {

        Sorter<Book> sorter1, sorter2;
        Displayer<Book> displayer1, displayer2;
        Author author1, author2;
        Map<String, String> bookInfo1, bookInfo2;


        bookInfo1 = new HashMap<String, String>() {
            {
                put("Harry Potter", "1770893083");
                put("Quidditch Through The Ages", "0385659768");
                put("Fantastic Beasts", "1770891048");
            }
        };

        bookInfo2 = new HashMap<String, String>() {
            {
                put("The Shining", "1443433659");
                put("Carrie", "0006485456");
            }
        };


        sorter1 = new InsertionSorter<>();
        sorter2 = new SelectionSorter<>();

        displayer1 = new NaturalOrderDisplayer<>();
        displayer2 = new ReverseOrderDisplayer<>();

        author1 = new Author("J.K. Rowling", sorter1, displayer1);
        author2 = new Author("Stephen King", sorter2, displayer2);

        addBooks(author1, bookInfo1);
        addBooks(author2, bookInfo2);


        author1.sortBooks();
        System.out.println(author1);
        author1.displayBooks();

        // Change sorter of author 1.
        author1.setSorter(sorter2);
        // Change displayer of author 1.
        author1.setDisplayer(displayer2);
        System.out.println(author1);
        author1.displayBooks();


        author2.sortBooks();
        System.out.println(author2);
        author2.displayBooks();

    }

    public static void addBooks(Author author, Map<String, String> bookInfo) {
        for (String authorName : bookInfo.keySet())
            author.addBook(new Book(authorName, bookInfo.get(authorName)));
    }
}