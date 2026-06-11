/**
 * Exercise (Chapter 1: Introduction to Java) — arrays and for-loops.
 *
 * Your task is to complete the oddSum method below.
 *
 *
 * You only need to edit the body of oddSum — do not change OddSumTest.
 *
 * Relevant readings: 1.6. Arrays and 1.8.2. for Loops.
 */
public class OddSum {

    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50};
        // Values at odd indices are 20 (index 1) and 40 (index 3), so this
        // should print 60 once you have implemented oddSum correctly.
        System.out.println("Sum of values at odd indices: " + oddSum(numbers));
    }

    /**
     * Returns the sum of all integers stored at ODD indices of {@code arr}
     * (index 1, 3, 5, ...). If there are no odd indices (e.g. an array of
     * length 0 or 1), returns 0.
     *
     * @param arr an array of integers
     * @return the sum of all integers at odd indices in arr
     */
    public static int oddSum(int[] arr) {
        // TODO: Replace the line below. Use a for-loop that starts at index 1
        //       and steps by 2 (i += 2), adding arr[i] to a running total.
        //       Recall arr.length gives the number of elements.
        //       You can index into arrays as we do in Python
        //       (e.g. arr[i] gives you the item at index i).
        return 0;
    }
}
