# Chapter 4: Assorted Topics in Java
## 4.1. Shadowing
Variable shadowing occurs when the same variable name is used in two different scopes.
In Python, one example would be:

```python
def f() -> None:
    x = 10

    def g() -> None:
        x = 20
        print(f"g()'s x = {x}")
    
    print(f"f()'s x = {x}")
```

This would print out the following:
```
g()'s x = 20
f()'s x = 10
```

Each of the functions have their own stack frame on the call stack where
their `x` variable is stored.
The stack frame for the call to `f()` contains both its `x` with the value
of `10` along with the definition for the function `g()`.
The stack frame for the call to`g()`'s stack frame would simply contain its
`x` with the value `20` — once `g()` finishes execution, that stack frame
would disappear and the stack frame for `f()` would still be unchanged.
You can open PyCharm and step through with the debugger for yourself to see this.

In Java, we have a similar concept. For example, consider the following code:
```java
public class ShadowExample {
    private int shadowedVariable = 10;

    public void shadowingMethod(){
        int shadowedVariable = 20;
        System.out.println(shadowedVariable);
        System.out.println(this.shadowedVariable);
    }
}
```

When we call `ShadowExample.shadowingMethod()`, the following would be printed:
```
20
10
```

Since both variables have the same name, we must use `this` to refer to the
instance variable, while just `shadowedVariable` is used for the local variable.
This is similar to what we saw previously with a constructor taking in a parameter
with the same name as the instance variable it was assigned to (i.e., `this.name = name`).

## 4.2. Array Copy
In Python, we could copy lists by creating a slice of them. For example:
```python
lst = [1, 2, 3]
lst_copy = lst[:]
lst_alias = lst
```

Both `lst` and `lst_copy` would contain the same items but have different
memory addresses: modifying one would not modify the other. `lst_alias`,
however, would be an alias to `lst`: if we modify one, we modify the other.

The same concept applies to Java, except arrays have a `clone` method. For example:
```java
int[] lst = {1, 2, 3};
int[] lst_copy = lst.clone();
int[] lst_alias = lst;
```

The relationships between `lst` and `lst_copy` along with `lst` and `lst_alias`
are the same as what we had in our Python example.

Furthermore, nested lists in Python behave the same as nested arrays in Java.
In Python, if we had:
```python
nested_lst = [[1, 2], [3, 4]]
nested_lst_copy = nested_lst[:]
nested_lst_copy[1] = [5, 6]
nested_lst_copy[0][0] = 7
```

Then the inner nested list would be an alias, but the outer list wouldn't.
In this example, we would get the following contents for each list:
```python
>>> nested_lst
[[7, 2], [3, 4]]
>>> nested_lst_copy
[[7, 2], [5, 6]]
```
To make a deeper copy without any aliasing, we would need to make copies of
every inner list.

Java behaves in exactly the same way: using `clone()` creates a copy of the
outermost arrays, but not copies of inner arrays. To make a deeper copy,
we would need to `clone()` all inner arrays.

## 4.3. Autoboxing
In Java, we have to define types and adhere to our type declarations,
otherwise our code will not compile. However, **autoboxing** is a conversion
that the Java compiler makes automatically between primitive types and their
corresponding object wrapper class and vice versa (e.g. `int` and `Integer`).
For instance, we can do:
```java
int x = 4;
Integer y = new Integer(x);
int z = y;
```

As of Java 9, we can even change `Integer y = new Integer(x);` into
`Integer y = x;`! The former is deprecated at that point, so the autoboxing
becomes even more apparent.

For more details on autoboxing and the corresponding wrappers for each
primitive class, see the
[official Java tutorial](https://docs.oracle.com/javase/tutorial/java/data/autoboxing.html)!

You may be wondering what the purpose of wrapper classes are for the primitives.
The following discussion of Generics in Java should make this clear.

## 4.4. Generics

Generics are a way for programmers to generalize the type that a class
or method works with.
They allow us to reuse the same code for various input types without needing to
cast things constantly.

For example, consider the `List<T>` interface built into Java. This interface
specifies what it means for a class to store a list of objects of a generic type `T`.
When we declare a variable to refer to a list of integers,
we specify the type as `List<Integer>`.

> Under the hood, Java generics use a technique called **type erasure**.
> This means that generic types are replaced with `Object` at compile time,
> and the compiler inserts casts where necessary.
> So while generics give us type safety at compile time,
> the JVM doesn't retain generic type information at runtime — it just sees
> regular classes and `Object` references.
> This is why you can't do things like `new T()` or check `instanceof T` — the
> type information isn't available at runtime.


Here is a quick example working with generics:
```java
// note we don't need <Integer> on the right since it is implicit
ArrayList<Integer> ma = new ArrayList<>();
ma.add(1);
Integer my_item = ma.get(0) + 5;
```
The `<Integer>` specifies that we're working with an `ArrayList` containing
only Integers: we don't need to do any casting.

For our purposes, we'll primarily be making use of existing code that is written
using generics, like `ArrayList<T>` above. It is also possible to include generics
in your own custom classes, but this typically won't be necessary in this course.

### 4.4.1. Custom Generic Classes

Briefly, the syntax to define a generic class is as follows:

```java
public class Box<T> {
    private T item;

    public void set(T item) {
        this.item = item;
    }

    public T get() {
        return item;
    }
}
```

In this example:

- `Box<T>` is a generic class where `T` is a type parameter.
- You can create instances of `Box` for different types,
  like `Box<Integer>` or `Box<String>`.
- The type `T` is replaced with the actual type when the class is instantiated.

Example usage of our generic class might look like:

```java
Box<Integer> intBox = new Box<>();
intBox.set(123);
Integer value = intBox.get();

Box<String> strBox = new Box<>();
strBox.set("Hello");
String text = strBox.get();
```

### 4.4.2. bounded type parameters

You can also **restrict** the types that can be used with a generic class
or method using **bounded type parameters**. This is useful when your code
relies on certain behaviors or interfaces being available.

For example, Java’s `Collections.sort()` method requires that the elements
in the list implement the `Comparable` interface, so that they can be ordered.

Here’s how you might define a generic method that sorts a list of items that
are guaranteed to be comparable:

```java
public static <T extends Comparable<T>> void sortList(List<T> list) {
    Collections.sort(list);
}
```

In this example:
- `<T extends Comparable<T>>` means that `T` must be a type that
  implements `Comparable<T>`.
- This ensures that the `sortList` method can safely call `compareTo()` on
  elements of the list.

If you try to call `sortList()` with a type that doesn’t implement `Comparable`,
you’ll get a compile-time error — which is exactly what generics are designed
to help prevent.

It turns out that our `sortList()` is actually slightly more restrictive than it
needs to be.
If we look at the signature for `Collections.sort`, it is actually:

Java’s built-in sorting method uses a more flexible bounded type parameter:

```java
public static <T extends Comparable<? super T>> void sort(List<T> list)
```

The `<T extends Comparable<? super T>>` part means that `T` must be a type that
can be compared to itself **or any of its supertypes**.

The `?` is a wildcard that stands for an unknown type. In `Comparable<? super T>`,
it means we don’t care exactly what the type is — only that it is a supertype of T.
This allows the method to work with a wider range of types,
especially when inheritance is involved.

If the signature were just `<T extends Comparable<T>>`, it would fail in cases like this:

```java
class Animal implements Comparable<Animal> { ... }
class Dog extends Animal { ... }

List<Dog> dogs = new ArrayList<>();
Collections.sort(dogs); // works with Comparable<? super T>
```

This works because `Dog` is a subtype of `Animal`,
and `Animal` implements `Comparable<Animal>`.  
Using `? super T` ensures that this kind of relationship is allowed.

This design helps make the `sort()` method compatible with a wider range of
types in real-world code.

---

You can read a much more in-depth explanation of Generics in the Java documentation
if you are interested. A tutorial is also available
[here](https://docs.oracle.com/javase/tutorial/java/generics/index.html).

## 4.5. Collections

A collection is an object that represents a group of objects.
For example, a stack is a collection, as is a set or a vector.
Java defines a `Collection` interface that defines the operations that any
collection should offer, including operations to add and remove items, and
to find out the number of items in the collection.

This interface is very general. For instance, it doesn't specify the order
in which items are removed or whether duplicates are allowed. Java defines
more specific interfaces that specify these things more fully and add more
methods. These include interfaces `Queue`, `List` and `Set`.

### 4.5.1. Implementations

So far, we have only discussed interfaces. Each interface has several different
implementations, with different characteristics. For example, `HashSet` implements
the `Set` interface with a hash table. This allows it to offer constant-time
performance for the core methods `add`, `remove`, `size` and `contains`, however,
it cannot guarantee the order in which set elements will be seen if you iterate
over them. On the other hand, `TreeSet` implements the interface with a balanced tree.
As a result, it can make guarantees about order, but it can guarantee only that
the core methods will run in logarithmic time.

When you choose an implementation of any kind of collection, you can read the
documentation to find out the properties of the various options.

### 4.5.2. The Java Collections Framework

We refer to the various interfaces, their implementations, provided static methods
that operate on collections, and some additional infrastructure, together as the
"Java Collections Framework".

Let's look at a few of the implementations to get a feel for them.

### 4.5.2.1. List

Java's `List` is similar to Python's `list` type, in that they grow and shrink as needed.
`ArrayList` is one implementation. It offers quick access to elements by index.
```java
public static void main(String[] args) {
    // Declare and initialize an ArrayList of Strings:
    ArrayList<String> csc207team = new ArrayList<>();

    csc207team.add("Juanita");
    csc207team.add("Amelie");
    csc207team.add("Abhinav");
    csc207team.add("Menghan");

    // ArrayList has a toString that prints the list nicely.
    System.out.println(csc207team);

    // This isn't permitted, because we specified the elements would
    // be of type String:
    // csc207team.add(5);

    // We don't need to typecast. The compiler knows that get will return
    // a String, since this is an ArrayList of Strings.
    csc207team.get(0).charAt(0);

    // Some other useful methods. ArrayList has many more: check the
    // documentation!
    System.out.println(csc207team.size());
    System.out.println("Is alex in csc207team? " +
    csc207team.contains("alex"));

    // We can have an ArrayList of any valid Java object type, i.e.,
    // any built-in or user-defined object. But no primitives, so below won't work!
    // ArrayList<int> wontWork = new ArrayList<int>();

    // But we can get primitives into an ArrayList by using a wrapper
    // class.
    List<Integer> intList = new ArrayList<>();

    // And we can use autoboxing to avoid having to construct instances
    // of the wrapper class. So rather than say:
    intList.add(new Integer(42));
    // ... we can say just:
    intList.add(42);
    System.out.println(intList);

    // We can also automatically unbox. To get an Integer object from
    // the ArrayList, and get an int from it, we can just say:
    int x = intList.get(0);
    System.out.println(x);
}
```

All elements of a `List` must be objects, not primitives, and they must be of the same type.
However, we can use inheritance to allow mixed types. For instance, if we specify just
that all elements are of type `Object` we are placing no restriction at all.

### 4.5.2.2. Set

A `Set`, as one would expect, does not allow duplicate elements, and has no notion of
elements being in any particular position. `TreeSet` is one implementation.

```java

public static void main(String[] args) {

    // Declare a TreeSet of Strings, and try to add some elements.
    TreeSet<String> s = new TreeSet<>();
    s.add("hello");
    s.add("silly");
    // We can look at the return value of add to see if the operation
    // succeeded.
    System.out.println(s.add("goodbye!"));
    // We won't be able to add this String a second or third time.
    System.out.println(s.add("silly"));
    System.out.println(s.add("silly"));

    // TreeSet has a toString that prints the set nicely.
    // The elements of the set could come out in any order.
    System.out.println(s);

    // TreeSet implements the Iterable interface, which guarantees that
    // it provides a hasNext and a next method. Here we use it to iterate
    // over our set and assemble a single String with all the words.
    String allWords = "";
    Iterator<String> it = s.iterator();
    while (it.hasNext()) {
        allWords += it.next();
    }
    System.out.println(allWords);

    // Because Treeset implements Iterable, we can instead use an
    // enhanced for-loop.  Much more concise!
    allWords = "";
    for(String word: s) {
        allWords += word;
    }
    System.out.println(allWords);
}
```

### 4.5.2.3. Map
 
The `Map` interface is similar to Python's `dict` type. As in Python,
these map keys to values, and each key can have only one value associated with it.
Unlike in Python, keys do not have to be immutable. However, you must really know
what you are doing if you mutate an object after it has been added as a key,
otherwise, the behaviour of your `Map` will be undefined. See the documentation
for further details.

`HashMap` is one implementation of `Map`, based on — you guessed it — a hash table!

```java
public static void main(String[] args) {
    // Declare and initialize a Map from Strings to Integers.
    // Notice that the generic type HashMap requires two arguments:
    // one for the type of the keys, and one for the type of the values.
    Map<String, Integer> myMap = new HashMap<>();

    // We can use autoboxing to get an Integer value into the HashMap.
    myMap.put("csc", 207);
    myMap.put("bio", 120);
    myMap.put("his", 150);
    myMap.put("ant", 100);
    System.out.println(myMap.get("csc"));

    // "mat" is not a key, so this returns null.
    System.out.println(myMap.get("mat"));

    // HashMap has many other methods -- see the documentation for
    // details.
}
```

`HashMap` offers constant-time performance for the basic
operations (`get` and `put`), which is fantastic. However, this depends
upon certain properties of the hash table being maintained as key-value
pairs are added and removed. When you construct your `HashMap`, you can
control parameters of the data structure that may help ensure these
properties are maintained. You'll learn all about hash tables in CSC263,
and then will be well-equipped to make good choices here.
Something to look forward to!
