package designpatterns.behavioural.strategy.withoutstrategy;

/** An author that sorts books with selection sort and displays them in natural order. */
public class AuthorWithSelectionSortAndNaturalOrderDisplay extends Author {

  /**
   * Constructs a new Author named name.
   *
   * @param name the name of the new Author
   */
  public AuthorWithSelectionSortAndNaturalOrderDisplay(String name) {
    super(name);
  }

  /*
   * Sort this Author's books using 'Selection Sort' Algorithm.
   */

  /** Sorts this Author's books using the selection sort algorithm. */
  public void sortBooks() {
    for (int i = 0; i < books.size() - 1; i++) {

      // Find the index of the smallest item in the list between
      // indices i and list.size() - 1 inclusive.
      int indexSmallest = i;
      for (int j = i + 1; j < books.size(); j++) {
        if (books.get(indexSmallest).compareTo(books.get(j)) > 0) {
          indexSmallest = j;
        }
      }

      // Swap the item at index i with the smallest item
      // between i and  list.size() - 1 inclusive.
      Book temp = books.get(i);
      books.set(i, books.get(indexSmallest));
      books.set(indexSmallest, temp);
    }
  }

  /*
   * Display books in 'Natural Order'.
   */

  /** Displays this Author's books in natural order. */
  public void displayBooks() {

    for (int i = 0; i < books.size(); i++) {
      System.out.print("(" + books.get(i) + ")  ");
    }

    System.out.println();
  }
}
