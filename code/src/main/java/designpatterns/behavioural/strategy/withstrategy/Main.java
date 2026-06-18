package designpatterns.behavioural.strategy.withstrategy;

import designpatterns.behavioural.strategy.displaystrategies.Displayer;
import designpatterns.behavioural.strategy.displaystrategies.NaturalOrderDisplayer;
import designpatterns.behavioural.strategy.displaystrategies.ReverseOrderDisplayer;
import designpatterns.behavioural.strategy.sortstrategies.InsertionSorter;
import designpatterns.behavioural.strategy.sortstrategies.SelectionSorter;
import designpatterns.behavioural.strategy.sortstrategies.Sorter;
import java.util.HashMap;
import java.util.Map;

/**
 * Demonstrates the Strategy design pattern.
 *
 * @author Mahsa Sadi
 * @since 2020 - 08 - 01
 */
public class Main {

  /**
   * Runs the Strategy demonstration.
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
            put("The Shining", "1443433659");
            put("Carrie", "0006485456");
          }
        };

    Sorter<Book> sorter1 = new InsertionSorter<>();
    Sorter<Book> sorter2 = new SelectionSorter<>();

    Displayer<Book> displayer1 = new NaturalOrderDisplayer<>();
    Displayer<Book> displayer2 = new ReverseOrderDisplayer<>();

    Author author1 = new Author("J.K. Rowling", sorter1, displayer1);
    Author author2 = new Author("Stephen King", sorter2, displayer2);

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
