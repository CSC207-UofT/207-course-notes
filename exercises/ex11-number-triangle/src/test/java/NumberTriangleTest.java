import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;

/** Tests for {@link NumberTriangle}. Do NOT modify this file. */
class NumberTriangleTest {

  @Test
  void rootTree() throws IOException {
    NumberTriangle mt = NumberTriangle.loadTriangle("input_tree.txt");
    assertEquals(75, mt.retrieve(""));
  }

  @Test
  void retrieveTreeBasic() throws IOException {
    NumberTriangle mt = NumberTriangle.loadTriangle("input_tree.txt");
    assertEquals(47, mt.retrieve("lr"));
  }

  /** The nodes at mt.left.right and mt.right.left are the same object. */
  @Test
  void aliasingTree() throws IOException {
    NumberTriangle mt = NumberTriangle.loadTriangle("input_tree.txt");
    assertEquals(mt.retrieve("lr"), mt.retrieve("rl"));
  }

  @Test
  void littleTree() throws IOException {
    NumberTriangle mt = NumberTriangle.loadTriangle("little_tree.txt");
    assertEquals(1, mt.retrieve(""));
    assertEquals(2, mt.retrieve("l"));
    assertEquals(3, mt.retrieve("r"));
  }

  @Test
  void bigTreeLeftMost() throws IOException {
    NumberTriangle mt = NumberTriangle.loadTriangle("input_tree.txt");
    assertEquals(4, mt.retrieve("llllllllllllll"));
  }
}
