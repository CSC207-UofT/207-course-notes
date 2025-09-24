import java.util.*;

public class ModifiableMutable {
    public static void main(String[] args) {
        List<String> modifiableList = new ArrayList<>();
        modifiableList.add("Apple");
        modifiableList.add("Banana");

        // Create an unmodifiable view of the same list — there's aliasing happening somewhere
        List<String> unmodifiableList = Collections.unmodifiableList(modifiableList);

        // unmodifiableList is really just another view on the same list data.
        System.out.println("Original unmodifiable list: " + unmodifiableList);

        try {
            unmodifiableList.add("Cherry");
        } catch (UnsupportedOperationException e) {
            System.out.println("Caught expected exception when trying to modify unmodifiable list: " + e);
        }

        // Modify the underlying list
        modifiableList.add("Cherry");

        // The change is reflected in the unmodifiable view
        System.out.println("After modifying original list: " + unmodifiableList);
    }
}