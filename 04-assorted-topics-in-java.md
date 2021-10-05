# Chapter 4: Assorted Topics in Java
## 4.1. Shadowing
Variable shadowing occurs when the same variable name is used in two different scopes. In Python, one example would be:

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

Each of the functions have their own callstack where their `x` variable is stored. `f()`'s stack contains both their `x` with the value of `10` along with the definition for the function `g()`. `g()`'s call stack would simply contain their `x` with the value `20` -- once `g()` finishes execution, that call stack would disappear and `f()`'s would still be unchanged.

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

When we call `ShadowExample.shadowingMethod()`, the following would be printed out:
```
20
10
```

Both variables have the same name, but we use `this` to refer to the class variable, while no indicator is used for the local variable.

As a side note: if we *wanted* to change a variable in an outer scope in Python, we would use the `nonlocal` keyword (or `global` for global variables)! Similarly, the Python equivalent to the ShadowExample class would be:

```python
class ShadowExample:
    def __init__(self) -> None:
        self.shadowedVariable = 10

    def shadowingMethod(self) -> None:
        shadowedVariable = 20
        print(shadowedVariable)
        print(self.shadowedVariable)
```

The names are slightly different here since we explicitly have to say `self` in the `__init__`, so it's easier to tell. But Java doesn't require the `this`! We could take things a step further and do the following:

```python
shadowedVariable = 30

class ShadowExample:
    def __init__(self) -> None:
        self.shadowedVariable = 10

    def shadowingMethod(self) -> None:
        shadowedVariable = 20
        print(shadowedVariable)
        print(self.shadowedVariable)
```

Which `shadowedVariable` would be printed out? `20` or `30`? And does the outermost `shadowedVariable` change? The answer is `20` and `10` would still be printed out, and the outermost `shadowedVariable` wouldn't change. Variable shadowing can become very confusing, especially since variable declarations and assignments look the same in Python! Fortunately, we can easily tell when we're declaring a variable in Java!

## 4.2. Array Copy
In Python, we could copy lists by creating a slice of them. For example:
```python
lst = [1, 2, 3]
lst_copy = lst[:]
lst_alias = lst
```

Both `lst` and `lst_copy` would contain the same items but have different memory addresses: modifying one would not modify the other. `lst_alias`, however, would be an alias to `lst`: if we modify one, we modify the other.

The same concept applies to Java, except arrays have a `clone` method. For example:
```java
int[] lst = {1, 2, 3};
int[] lst_copy = lst.clone();
int[] lst_alias = lst;
```

The relationships between `lst` and `lst_copy` along with `lst` and `lst_alias` are the same as what we had in our Python example.

Furthermore, nested lists in Python behave the same as nested arrays in Java. In Python, if we had:
```python
nested_lst = [[1, 2], [3, 4]]
nested_lst_copy = nested_lst[:]
nested_lst_copy[1] = [5, 6]
nested_lst_copy[0][0] = 7
```

Then the inner nested list would be an alias, but the outer list wouldn't. In this example, we would get the following contents for each list:
```python
>>> nested_lst
[[7, 2], [3, 4]]
>>> nested_lst_copy
[[7, 2], [5, 6]]
```
To make a deeper copy without any aliasing, we would need to make copies of every inner list.

Java behaves in exactly the same way: using `clone()` creates a copy of the outermost arrays, but not copies of inner arrays. To make a deeper copy, we would need to `clone()` all inner arrays.

## 4.3. Autoboxing
In Java, we have to define types and adhere to our type declarations, otherwise our code will not compile. However, **autoboxing** is a conversion that the Java compiler makes automatically between primitive types and their corresponding object wrapper class and vice versa (e.g. `int` and `Integer`). For instance, we can do:
```java
int x = 4;
Integer y = new Integer(x);
int z = y;
```

As of Java 9, we can even change `Integer y = new Integer(x);` into `Integer y = x;`! The former is deprecated at that point, so the autoboxing becomes even more apparent.

For more details on autoboxing and the corresponding wrappers for each primitive calss, see the [Java documentation](https://docs.oracle.com/javase/tutorial/java/data/autoboxing.html)!

## 4.4. Generics

Generics are a way for programmers to generalize the type that a class works with. It allows us to re-use the same code but allow for various input types without the need to cast things constantly.

For example, suppose we want to have a class that stores a single item. We could write something like:
```java
public class MyItem{
    Object item;

    public MyItem(Object item){
        this.item = item;
    }

    public static void main(String[] args) {
        MyItem mi = new MyItem(1);
        Integer another_item = mi.item + 5;
    }
}
```

However, the line `Integer another_item = mi.item + 5;` wouldn't compile: `mi.item` is assumed to be of type `Object`, so we can't add `5` to it without casting!

This is similar to how an `ArrayList` without a specified type works: if we do the following, we'd also need to cast:
```java
ArrayList ma = new ArrayList();
ma.add(1);
Integer my_item = ma.get(0) + 5;
```

Instead, when we're working with ArrayLists, we tend to specify a type as follows:
```java
ArrayList<Integer> ma = new ArrayList<Integer>();
ma.add(1);
Integer my_item = ma.get(0) + 5;
```
The `<Integer>` specifies that we're working with an ArrayList containing only Integers: we don't need to cast items back constantly.

Going back to our original example of `MyItem`, we can use a generic as follows:
```java
public class MyItem<T>{
    T item;

    public MyItem(T item){
        this.item = item;
    }

    public static void main(String[] args) {
        MyItem<Integer> mi = new MyItem<Integer>(1);

        Integer my_item = mi.item + 5;
        System.out.println(my_item);
    }
}
```

Here, we did the following changes:

1. We added `<T>` to the class declaration.
2. Everywhere we used `Object`, we switched it with `T`.
3. When we create an instance of `MyItem`, we specify that `T` should be `Integer`.

Note the mapping in syntax between `MyItem<T>` and `MyItem<Integer>`! Everywhere we use `T` in our class, we can pretend it says `Integer` for this instance. Furthermore, we don't need to do any casting as we know `mi.item` will be of typeInteger since `mi` is of type `MyItem<Integer>`. We can make new `MyItem` objects with other types too, if we'd like!

We can add as many generics as we want (e.g. `MyItem<T1, T2, T3, ...>`) and even restrict our the generic types using the `extends` keyword (e.g. `MyItem<T extends Integer>` enforces the fact that `T` must be `Integer` or a subclass of it).

You can read a much more in-depth explanation of Generics in the Java documentation. A simple tutorial is available [here](https://docs.oracle.com/javase/tutorial/java/generics/index.html), where you can use the `Next »` to step through.

## 4.5. Collections

A collection is an object that represents a group of objects. For example, a stack is a collection, as is a set or a vector. Java defines an interface called Collection that defines the operations that any collection should offer, including operations to add and remove items, and to find out the number of items in the collection.

This interface is very general. For instance, it doesn't specify the order in which items are removed or whether duplicates are allowed. Java defines more specific interfaces that specify these things more fully and add more methods. These include interfaces `Queue`, `List` and `Set`.

### 4.5.1. Implementations

So far, we have only discussed interfaces. Each interface has several different implementations, with different characteristics. For example, `HashSet` implements the `Set` interface with a hash table. This allows it to offer constant-time performance for the core methods `add`, `remove`, `size` and `contains`, however, it cannot guarantee the order in which set elements will be seen if you iterate over them. On the other hand, `TreeSet` implements the interface with a balanced tree. As a result, it can make guarantees about order, but it can guarantee only that the core methods will run in logarithmic time.

When you choose an implementation of any kind of collection, you can read the documentation to find out the properties of the various options.

### 4.5.2. The Java Collections Framework

We refer to the various interfaces, their implementations, provided static methods that operate on collections, and some additional infrastructure, together as the "Java Collections Framework".

Let's look at a few of the implementations to get a feel for them.

### 4.5.2.1. List

Java's List is similar to Python's list type, in that they grow and shrink as needed. ArrayList is one implementation. It offers quick access to elements by index.
```java
public static void main(String[] args) {
    // Declare and initialize an ArrayList of Strings:
    ArrayList<String> csc207team = new ArrayList<String>();

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
    // any built-in or user-defined object. But no primitives!
    // ArrayList<int> wontWork = new ArrayList<int>();

    // But we can get primitives into an ArrayList by using a wrapper
    // class.
    List<Integer> intList = new ArrayList<Integer>();

    // And we can use autoboxing to avoid having to construct instances
    // of the wrapper class. So rather than say:
    intList.add(new Integer(42));
    // ... we can say just:
    intList.add(42);
    System.out.println(intList);

    // We can also automatically unbox.  To get an Integer object from
    // the ArrayList, and get an int from it, we can just say:
    int x = intList.get(0);
    System.out.println(x);
}
```

All elements of a `List` must be objects, not primitives, and they must be of the same type. (Notice that we specified that type in angle brackets above!) However, we can use inheritance to allow mixed types. For instance, if we specify just that all elements are of type `Object` we are placing no restriction at all (other than that they must not be primitives).

### 4.5.2.2. Set
 

A `Set`, as one would expect, does not allow duplicate elements, and has no notion of elements being in any particular position. `TreeSet` is one implementation.

```java

public static void main(String[] args) {

    // Declare a TreeSet of Strings, and try to add some elements.
    TreeSet<String> s = new TreeSet<String>();
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
 
The `Map` interface is similar to Python's dictionary type. As in Python, these map keys to values, and each key can have only one value associated with it. Unlike in Python, keys do not have to be immutable. However, you must really know what you are doing if you mutate an object after it has been added as a key, otherwise, the behaviour of your `Map` will be undefined. See the documentation for further details.

 
`HashMap` is one implementation of `Map`, based on -- you guessed it -- a hash table.

```java
public static void main(String[] args) {
    // Declare and initialize a Map from Strings to Integers.
    // Notice that the generic type HashMap requires two arguments:
    // one for the type of the keys, and one for the type of the values.
    Map<String, Integer> myMap = new HashMap<String, Integer>();

    myMap.put("csc", new Integer(207));
    // We can use autoboxing to get an Integer value into the HashMap.
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

`HashMap` offers constant-time performance for the basic operations (`get` and `put`), which is fantastic. However, this depends upon certain properties of the hash table being maintained as key-value pairs are added and removed. When you construct your `HashMap`, you can control parameters of the data structure that may help ensure these properties are maintained. You'll learn all about hash tables in CSC263, and then will be well equipped to make good choices here. Something to look forward to!
