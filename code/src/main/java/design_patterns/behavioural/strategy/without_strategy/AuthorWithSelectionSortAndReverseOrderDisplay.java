package design_patterns.behavioural.strategy.without_strategy;

public class AuthorWithSelectionSortAndReverseOrderDisplay extends Author {

    public AuthorWithSelectionSortAndReverseOrderDisplay(String name)
    {
        super (name);
    }

    /*
     * Sort this Author's books using 'Selection Sort' Algorithm.
     */

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
     * Display books in 'Reverse Order'.
     */

    public void displayBooks ()
    {
        for (int  i = books.size() - 1 ; i >= 0; i--)
            System.out.print("(" + books.get(i) + ")  ");
        System.out.println ();

    }


}
