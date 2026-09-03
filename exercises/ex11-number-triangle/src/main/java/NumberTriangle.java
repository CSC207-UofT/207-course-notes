import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Exercise (Chapter: APIs, JSON, and Files) — reading data from a file into objects.
 *
 * This exercise practises reading data from a file and building a structure of
 * objects to represent it. You will complete two methods:
 * {@link #loadTriangle(String)} and {@link #retrieve(String)}.
 * Edit only this file.
 *
 * A {@code NumberTriangle} is like a tree, but some nodes in the structure have
 * two parents. The structure is shown below. Observe that the parents of e are b
 * and c, whereas d and f each only have one parent. Each row is complete and will
 * never be missing a node, so each row has one more {@code NumberTriangle} object
 * than the row above it.
 *
 * <pre>
 *                  a
 *                b   c
 *              d   e   f
 *            h   i   j   k
 * </pre>
 *
 * The sharing of nodes between rows is the interesting part: if {@code base} refers
 * to the topmost object, then {@code base.left.right == base.right.left} — they are
 * the very same object (aliasing), not two equal copies.
 *
 * This class is minimally defined and is only intended to be constructed using the
 * {@link #loadTriangle(String)} method. There is no code enforcing the structure
 * described above, and you do not have to write any either.
 *
 * Relevant reading: the file-reading section of the "APIs, JSON, and Files" chapter.
 */
public class NumberTriangle {

  private final int root;

  private NumberTriangle left;
  private NumberTriangle right;

  /**
   * Constructs a NumberTriangle with the given value at its root and no children.
   *
   * @param root the value at the root of this NumberTriangle
   */
  public NumberTriangle(int root) {
    this.root = root;
  }

  /**
   * Sets the left child of this NumberTriangle.
   *
   * @param left the NumberTriangle to use as the left child
   */
  public void setLeft(NumberTriangle left) {
    this.left = left;
  }

  /**
   * Sets the right child of this NumberTriangle.
   *
   * @param right the NumberTriangle to use as the right child
   */
  public void setRight(NumberTriangle right) {
    this.right = right;
  }

  /**
   * Returns the value stored at the root of this NumberTriangle.
   *
   * @return the root value
   */
  public int getRoot() {
    return root;
  }

  /**
   * Reports whether this NumberTriangle has no children.
   *
   * @return true if and only if both children are null
   */
  public boolean isLeaf() {
    return right == null && left == null;
  }

  /**
   * Follows path through this NumberTriangle structure ('l' = left; 'r' = right) and
   * returns the root value at the end of the path. An empty string returns the root
   * of this NumberTriangle.
   *
   * You can decide if you want to use a recursive or an iterative approach.
   *
   * You can assume that:
   * <ul>
   *   <li>the length of path is less than the height of this NumberTriangle structure;</li>
   *   <li>each character in the string is either 'l' or 'r'.</li>
   * </ul>
   *
   * @param path the path to follow through this NumberTriangle
   * @return the root value at the location indicated by path
   */
  public int retrieve(String path) {
    // TODO: walk the path one character at a time, starting from this object.
    //       For each character, move to the left child (if it is 'l') or the
    //       right child (if it is 'r'). When the path runs out, return the root
    //       value of wherever you ended up. An empty path means "stay here".
    //       Hint: String#charAt(int) and String#length() are all you need for the
    //       iterative version; a recursive version can use String#substring(1).
    return 0;
  }

  /**
   * Reads in the NumberTriangle structure from a file.
   *
   * You may assume that it is a valid format with a height of at least 1, so there
   * is at least one line with a number on it to start the file. Numbers on a line
   * are separated by spaces. See {@code input_tree.txt} for an example.
   *
   * The first row is the root of the NumberTriangle (call it 0).
   * The second line contains the two children of the root (call them 1L and 1R).
   * The third line contains the three numbers corresponding to:
   * <ul>
   *   <li>the left child of 1L, call it 2LL;</li>
   *   <li>the right child of 1L, call it 2LR;</li>
   *   <li>the left child of 1R, call it 2RL;</li>
   *   <li>the right child of 1R, call it 2RR.</li>
   * </ul>
   * NOTE: 2RL and 2LR must refer to the SAME underlying NumberTriangle object.
   * That is, if variable {@code base} refers to the object at the top of the
   * triangle, then {@code base.left.right == base.right.left}.
   *
   * <pre>
   *              0
   *            /   \
   *          1L    1R
   *         /  \  /  \
   *       2LL  2LR*  2RR    *2LR == 2RL
   * </pre>
   *
   * Hint 0: Start by making a plan and scaffolding what you plan to do. If you are
   *         still finding it hard to "think in Java", write some comments describing
   *         what you want to do first.
   *
   * Hint 1: Think about what you need to keep track of on each iteration of the loop
   *         and make appropriate variables to store those things. Working through
   *         {@code little_tree.txt} by hand should help you develop the general logic.
   *
   * Hint 2: Related to Hint 1, think about how to connect NumberTriangle objects
   *         between adjacent rows in the structure. Remember that the row you just
   *         built becomes the children of the row before it.
   *
   * Hint 3: If you are still stuck, look for a subproblem that you can solve, then
   *         design the logic of your solution around a helper method that you
   *         implement separately. Decomposing a problem this way makes it much more
   *         manageable.
   *
   * @param fname the file to load the NumberTriangle structure from
   * @return the topmost NumberTriangle object in the structure read from the file
   * @throws IOException may naturally occur if an issue reading the file occurs
   */
  public static NumberTriangle loadTriangle(String fname) throws IOException {
    // Open the file and get a BufferedReader object, whose methods are convenient
    // to work with when reading a file line by line. See the file-reading section
    // of the "APIs, JSON, and Files" chapter.
    BufferedReader br = Files.newBufferedReader(Path.of(fname));

    // TODO: define any variables that you want to use to keep track of things
    //       between iterations of the loop below (for example, the row of
    //       NumberTriangle objects that you built on the previous iteration).

    // We need to return the top of the NumberTriangle, so here is a variable for it.
    NumberTriangle top = null;

    String line = br.readLine();
    while (line != null) {

      // Remove this line when you are done; it is here so that the starter code
      // prints the contents of the file when you run it.
      System.out.println(line);

      // TODO: process the line. Splitting it on spaces gives you the numbers in
      //       this row; make a NumberTriangle for each one, then wire this row up
      //       as the children of the previous row. Remember the aliasing: the
      //       right child of one node is the left child of the next node over.

      // read the next line
      line = br.readLine();
    }
    br.close();
    return top;
  }

  /**
   * Loads the example triangle and prints its root value.
   *
   * @param args unused
   * @throws IOException if the file cannot be read
   */
  public static void main(String[] args) throws IOException {
    NumberTriangle mt = NumberTriangle.loadTriangle("input_tree.txt");
    System.out.println("Root of the triangle: " + mt.getRoot());
  }
}
