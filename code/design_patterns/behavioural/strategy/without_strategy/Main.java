package design_patterns.behavioural.strategy.without_strategy;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {


        Author author1, author2, author3, author4;
        Map <String, String> bookInfo1, bookInfo2;

        bookInfo1 = new HashMap <String, String> ()
        {
            {
                put ("Harry Potter", "1770893083");
                put ("Quidditch Through The Ages", "0385659768");
                put ("Fantastic Beasts", "1770891048");

            }
        };

        bookInfo2 = new HashMap <String, String> ()
        {
            {
                put ("Carrie", "0006485456");
                put  ("The Shining", "1443433659");
            }
        };


        author1 = new AuthorWithInsertionSortAndNaturalOrderDisplay("J.K. Rowling");
        author2 = new AuthorWithInsertionSortAndReverseOrderDisplay("Stephen King");
        author3 = new AuthorWithSelectionSortAndNaturalOrderDisplay("J.K. Rowling");
        author4 = new AuthorWithSelectionSortAndReverseOrderDisplay("Stephen King");

        addBooks (author1, bookInfo1);
        addBooks (author2, bookInfo2);
        addBooks (author3, bookInfo1);
        addBooks (author4, bookInfo2);

        author1.sortBooks();
        author2.sortBooks();
        author3.sortBooks();
        author4.sortBooks();

        System.out.println(author1);
        author1.displayBooks();
        System.out.println(author2);
        author2.displayBooks();
        System.out.println(author3);
        author3.displayBooks();
        System.out.println(author4);
        author4.displayBooks();

    }


    public static void addBooks (Author author, Map<String, String> bookInfo)
    {
        for (String authorName : bookInfo.keySet())
            author.addBook(new Book(authorName, bookInfo.get(authorName)));
    }
}