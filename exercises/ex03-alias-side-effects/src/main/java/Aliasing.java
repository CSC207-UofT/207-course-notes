import java.util.Arrays;

/**
 * Exercise (Chapter 1: Introduction to Java) — aliasing, references vs.
 * primitives, and side effects.
 *
 * Arrays are REFERENCE types: when you pass an array to a method, the method
 * receives an alias to the SAME array, so any change it makes is visible to the
 * caller. (Primitives like int are copied instead, so a method cannot change a
 * caller's int by reassigning its parameter — which is why the methods that
 * "change" something here either modify an array in place or return a value.)
 *
 * Complete both methods below, then run the tests to see the difference.
 * Edit only this file — do not change the test file.
 *
 * Relevant reading: 1.7. Aliases and 1.9. Parameters.
 */
public class Aliasing {

    public static void main(String[] args) {
        int[] data = {1, 2, 3};
        addInPlace(data, 10);
        // addInPlace modifies the SAME array, so `data` should now be {11, 12, 13}:
        System.out.println("data after addInPlace: " + Arrays.toString(data));

        int[] original = {1, 2, 3};
        int[] copy = addCopy(original, 10);
        // addCopy must leave `original` as {1, 2, 3} and return a NEW {11, 12, 13}:
        System.out.println("original stays:        " + Arrays.toString(original));
        System.out.println("copy (new array):      " + Arrays.toString(copy));
    }

    /**
     * Adds {@code amount} to every element of {@code arr}, modifying {@code arr}
     * in place. Returns nothing: because {@code arr} is a reference, the caller
     * sees the change through its own variable.
     *
     * @param arr    the array to modify
     * @param amount the value to add to each element
     */
    public static void addInPlace(int[] arr, int amount) {
        // TODO: complete
    }

    /**
     * Returns a NEW array whose elements are those of {@code arr}, each
     * increased by {@code amount}. Must NOT modify {@code arr}.
     *
     * @param arr    the source array (left unchanged)
     * @param amount the value to add to each element
     * @return a new array of the same length, each element increased by amount
     */
    public static int[] addCopy(int[] arr, int amount) {
        // TODO: complete
        return new int[1];
    }
}
