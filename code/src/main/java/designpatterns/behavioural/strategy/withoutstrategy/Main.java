package designpatterns.behavioural.strategy.withoutstrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Demonstrates the bookstore example WITHOUT the Strategy pattern, using a separate class for each
 * combination of sorting and display behaviour.
 */
public class Main {

  /**
   * Runs the demonstration.
   *
   * @param args command-line arguments (unused)
   */
  public static void main(String[] args) {

    Map<String, String> bookInfo1 =
        new HashMap<String, String>() {
          {
            put("Harry Potter", "1770893083");
            put("Quidditch Through The Ages", "0385659768");
            put("Fantastic Beasts", "1770891048");
          }
        };

    Map<String, String> bookInfo2 =
        new HashMap<String, String>() {
          {
            put("Carrie", "0006485456");
            put("The Shining", "1443433659");
          }
        };

    final Author author1 = new AuthorWithInsertionSortAndNaturalOrderDisplay("J.K. Rowling");
    final Author author2 = new AuthorWithInsertionSortAndReverseOrderDisplay("Stephen King");
    final Author author3 = new AuthorWithSelectionSortAndNaturalOrderDisplay("J.K. Rowling");
    final Author author4 = new AuthorWithSelectionSortAndReverseOrderDisplay("Stephen King");

    addBooks(author1, bookInfo1);
    addBooks(author2, bookInfo2);
    addBooks(author3, bookInfo1);
    addBooks(author4, bookInfo2);

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

  /**
   * Adds each (title, ISBN) entry from {@code bookInfo} to {@code author}'s books.
   *
   * @param author the author to add the books to
   * @param bookInfo a map from book title to ISBN
   */
  public static void addBooks(Author author, Map<String, String> bookInfo) {
    for (String authorName : bookInfo.keySet()) {
      author.addBook(new Book(authorName, bookInfo.get(authorName)));
    }
  }
}
