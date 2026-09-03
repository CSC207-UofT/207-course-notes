import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Tests for {@link MyHashing}. Do NOT modify this file. */
class MyHashingTest {

  @Test
  void intHashReturnsPreviousSeedAndStoresNewOne() {
    MyHashing h = new MyHashing(5);
    assertEquals(5, h.hash(10)); // returns the old seed (5)
    assertEquals(10, h.hash(20)); // the seed is now 10
  }

  @Test
  void emptyConstructorStartsSeedAtZero() {
    MyHashing h = new MyHashing();
    assertEquals(0, h.hash(7));
  }

  @Test
  void charHashReturnsSumModuloAndStores() {
    MyHashing h = new MyHashing(3);
    assertEquals((3 + 'A') % MyHashing.MODULO, h.hash('A')); // (3 + 65) % 42 == 26
    assertEquals(65, h.hash(0)); // seed is now 'A' == 65
  }

  @Test
  void staticHashSumsCharacterCodes() {
    assertEquals('A' + 'B' + 'C', MyHashing.hash("ABC")); // 65 + 66 + 67
  }

  @Test
  void moduloConstantIs42() {
    assertEquals(42, MyHashing.MODULO);
  }
}
