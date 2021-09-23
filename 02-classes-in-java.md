# Chapter 2: Classes in Java

## 2.1. Classes
Classes in Java are similar to those Python: they consist of attributes and methods, both private and public. We can inherit from other classes, override methods, and define constructors.

While this section talks about classes at a higher level, the file [Monster.java](code/Monster.java) provides a larger example with in-line explanations which you may find helpful. You'll want to look at this file to get a better understanding of the syntax that we use.

## 2.2. Variables in classes
Unlike Python, we have to declare variables before using them in Java. There are two kinds of variables we can declare for classes, outside of any method:

1. **Instance variables**: These are akin to the attributes you're familiar with from Python. Every instance of the class will contain its own instance of each of these variables. They come into existence when the instance is constructed (using the `new` keyword).
2. **Class variables**: Also known as **static variables**, all instances of a class share a *single* instance of each class variable. Updating this variable in one instance of a class will reflect across every instance of the class. This is useful, for example, if we want the instances to accumulate a value together.


## 2.3. Visibility
Attributes and methods can be either **public** or **private**. In Python we used a single underscore (`_`) at the start of a variable name to denote whether an attribute was private or not. In Java we use the `public` or `private` keywords to make this distinction: private variables *cannot* be accessed from outside of a class. We can also use the `protected` keyword, making the attribute or method accessible to the entire package and a class' subclasses, but not to anything else.

## 2.4. Constructors
Equivalent to the `__init__` method in Python, we have constructors in Java. These are methods with no return type (not even void), which get called whenever a new instance of a class is created.

In Java, we can define as many constructors as we want so long as the method signatures are different: either taking a different number of arguments, having different argument types, or a combination of these.

As an example, this would be one constructor:

```java
public Something(String name, int size) {
    this.name = name;
    this.size = size;
}
```

In Python, we used the `self` keyword -- in Java, the `this` keyword is equivalent. Unlike Python, we are not required to include `this` as a parameter in our method signatures!

We could create additional constructors if we wished. For example, if we wanted a constructor that only takes a name:

```java
public Something(String name) {
    this.name = name;
    this.size = 1;
}
```

Even better: we could rewrite the above to call on the first constructor we wrote:

```java
public Something(String name) {
    this(name, 1);
}
```

## 2.5. Overloading methods
When we define multiple constructors, we are **overloading** the constructor. We can do this for any method, not just our constructors. In Python, we cannot overload methods, but we can provide default parameters. For instance:

```python
def my_method(self, something: int, something_else: int = 1) -> int:
    return something + something_else
```

In Java, we get similar behaviour by overloading methods:

```java
public int my_method(int something, int something_else){
    return something + something_else;
}

public int my_method(int something){
    return my_method(something, 1);
}
```

## 2.6. Overriding methods
In Python, we could re-define a method from a parent class in order to override it. We do a similar thing in Java, but we also include the `@Override` annotation. This lets Java (and any reader of our code) know that we're overriding an inherited method. The annotation will also enforce that we use the correct method signature for the overridden method -- this way, we can be certain that we don't have any silly typos in our method or the incorrect argument types!

### 2.6.1. toString
One common method that we'll want to override is the `toString` method. This is Java's equivalent to `__str__`: a method that gives the string representation of our object.

This method takes no parameters and returns a String. For example:

```java
@Override
public String toString(){
    return "My name is " + this.name;
}
```

### 2.6.2. equals
Another common method is the `equals` method: this is equivalent to Python's `__eq__` method. Note that this method is **NOT** called implicitly in Java: the `==` checks for identity equality (i.e. that the IDs of the objects are the same). You need to explicitly call the `equals` method (e.g. `object1.equals(object2)`) to check for equality.

The default behaviour of `equals` is to check for identity equality, but often we'll want to override this to check certain attributes.

The designer of a class gets to decide what has to be true in order for two instances to be considered "equals". This sounds trivial to implement, but there are a number of details to be handled. Any implementation of it must obey these properties:
1. **Symmetry**: For non-null references a and b, a.equals(b) if and only if b.equals(a)
2. **Reflexivity**:  a.equals(a)
3. **Transitivity**: If a.equals(b) and b.equals(c), then a.equals(c)

The `equals` method will often look as follows:
```java
@Override
public boolean equals(Object obj) {
    if (this == obj){
        return true;
    }
    if (obj == null){
        return false;
    }
    if (this.getClass() != obj.getClass()){
        return false;
    }

    MyClass other = (MyClass) obj;  // Here we're casting the type of obj to our class
    // And then we'll want to compare the attributes of this to those of other
    // For example:
    if (this.name != other.name){
        return false;
    } else if (this.something != other.something){
        return false;
    }
    return true;
}
```

### 2.6.3. hashCode
Whenever we override the equals method, we will often want to  override another inherited method called "hashCode".  The hashCode of an object is an integer value the obeys this property:
> If two objects are equal (according to the equals method), they have the same hashCode.

If is not an if-and-only-if: it's fine for two objects with the same hashCode *not* to be equal.

Why do objects have a hashCode and why must it satisfy this property? Some important classes such as HashMap use the hashCode of an object to decide where to store it.  This allows them to retrieve the object later by just comparing hashCodes and thus avoiding costly calls to the equals method.  The HashMap class does all this using a data structure called a "hash table".  Hash tables have remarkable properties and are thus very important in computer science. You will learn about them in more detail in CSC263. Dictionaries in Python are one type of a HashMap that you should be familiar with.

If we do not override the hashCode method in a given class, then the default hashCode will be the one from the `Object` class. As far as we're concerned, the hashCode generated by `Object` is random! We have no idea what the value is.

## 2.7. Class (static) methods
Just as we have class (static) variables, we can have class methods. Again, we use the `static` keyword to declare that a method is a class method. Since a class method is associated with the class and not the instance, we call it by prefixing it with the class name.

For example, suppose we have the following class method:
```java
public static int population(){
    return MyClass.count;
}
```

To call it, we would call `MyClass.population()`. If we had an instance of MyClass called `m1`, we could also call `m1.population()`, but this seems a bit strange since the `population` method doesn't depend on `m1` itself.

Although an instance method can reference a class variable (or call a class method), the opposite is not true.  A class method **cannot** access an instance variable or call an instance method directly.

So in our example above, the following wouldn't compile:
```java
public static int population(){
    return this.some_attribute;
}
```
There is no "this" when you are in a class method!

The only way for a class method to access an instance variable or call an instance method is if a reference to an object is passed to the method.  Through that reference, the instance variables and instance methods of the object are accessible.

## 2.8. Comparable
### 2.8.1. Being comparable enables sorting
In Python, we had the `list.sort()` method and `sorted()` function to allow us to sort lists. Similarly, Java provides a `sort` method capable of sorting an array of `int`s or of any other primitive type. In fact, `sort` is overloaded: there is a series of `sort` methods, each one capable of handling one of the primitive types. They are all defined as static methods in the `Arrays` class.

Here we use the `sort` method that takes an array of `int`s:
```java
int[] ages = {10, 24, 3, 45, 83, 9};
Arrays.sort(ages);
```


Great! But what if we want to sort objects of some other type? For instance, Java provides a class called `MonthDay` that can keep track of the month and day parts of a date. (It, and many other classes related to time, are defined in `java.time`.) What if we wanted to sort an array of `MonthDay` objects? And what if we wanted to also sort an array of `File` objects and an array of `Double` objects? The designers of Java could have made a general sort method that accepts an array of `Object`. In fact, they did: 

```java
public static void sort(Object[] a)
```
But in order for `sort` to do its job, it must be able to compare the elements of the array to decide on their order. It can't simply use operators like `<` to compare two instances of `MonthDay`. These operators will not accept instances of `MonthDay` as operands.

Instead, the `sort` method requires that all elements in the array must implement the `Comparable` interface. This interface in turn requires any class that implements it to define this method:

```java
int compareTo(T o)
    Compares this object with the specified object for order.
    Returns a negative integer, zero, or a positive integer
    as this object is less than, equal to, or greater than
    the specified object.
```

In Python, we had a similar concept: if we implemented the `__lt__` method, we could compare and sort our objects! Java is just slightly different, where we return a negative integer, zero, or positive integer instead of `True` or `False`.

Returning to our `MonthDay` example: the authors of the `MonthDay` class define this method and declare that the class **implements** the `Comparable` interface. This means that if we pass an array of `MonthDay` objects to sort, it is *guaranteed* to be able to use `compareTo` to put them in order. Here is an example of code that takes advantage of this capability:
```java
public static void main(String[] args) {
    // Create some MonthDay objects and put them in an array.
    // This class does not provide any constructors. Instead, we
    // call static method "of" to make an instance.
    MonthDay md1 = MonthDay.of(1, 5);
    MonthDay md2 = MonthDay.of(7, 24);
    MonthDay md3 = MonthDay.of(7, 24);
    MonthDay md4 = MonthDay.of(1, 28);
    MonthDay md5 = MonthDay.of(2, 14);
    MonthDay[] dates = {md1, md2, md3, md4, md5};

    // Because MonthDay implements Comparable, we can call sort,
    // which depends on that:
    Arrays.sort(dates);
}

```

The sort method has another requirement: all elements in the array must be **mutually** comparable. This prevents us from trying to sort an array with a mixture of `DateTime` objects and `File` objects, for instance. These objects are comparable *within* each class, but not *across* classes.

### 2.8.2. Being comparable enables comparisons
If we ever wish simply to compare a MonthDay to any other MonthDay, we can do this as well:

```java
        System.out.println(md1.compareTo(md2)); // -6
        System.out.println(md2.compareTo(md1)); //  6
        System.out.println(md2.compareTo(md3)); //  0
```

The same holds for any class that implements `Comparable`, which includes many built-in classes, such as `String`, `File`, `Integer`, and `Double`.

### 2.8.3. Making our own classes Comparable
Consider this class:
```java
class Review {
    /**
     * A review, for example of a book or movie.
     */

    // === Class Variables ===

    // The name of the item that this Review is about.
    String item;
    // The numeric rating, between 0 and 100, associated with this Review.
    private int rating;
    // The written component of this review.
    private String text;
    // The number of likes that this review has received.
    private int likes;

    public Review(String item, int rating, String text) {
        this.item = item;
        this.rating = rating;
        this.text = text;
        this.likes = 0;
    }

    public String toString() {
        return this.item + " (" + this.rating + "): " +
            this.text + "; likes = " + this.likes;
    }

    /**
     * Records a like for this Review.
     */
    public void like() {
        this.likes += 1;
    }
}
```

To make instances of this class comparable, need to do two things. First, we must implement the `compareTo` method. It's up to us to decide how reviews should compare. Here's one possible implementation, based on ratings:

```java
    /**
     * Compares this object with the specified object for order.
     *
     * Returns a negative integer, zero, or a positive integer as this
     * object is less than, equal to, or greater than the specified object.
     *
     * @param other the object to be compared.
     * @return a negative integer, zero, or a positive integer as this
     * object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Review other) {
        if (this.rating < other.rating) {
            return -1;
        } else if (this.rating > other.rating) {
            return 1;
        } else {
            return 0;
        }
    }
```


Second, we must change the class declaration to indicate that we have fulfulled the requirements of implementing the `Comparable` interface:
```java
class Review implements Comparable<Review> {
```

Notice that we said `Comparable<Review>` rather than just `Comparable`. This means that we promise that an instance of this class can be compared to any other instance of `Review`. To be consistent with this, the parameter of `compareTo` above is specifically a `Review`. If we had said that our class implements `Comparable`, our `compareTo` would have to accept and deal with *any* `Object`.

Now that we have implemented `Comparable`, we can do the same sorts of things we did with `MonthDays`:
```java
public static void main(String[] args) {
    Review r1 = new Review("Emoji Movie", 10,
        "Cinematic malware");
    Review r2 = new Review("Dunkirk", 95,
        "Gifted ensemble cast and masterful direction");
    Review r3 = new Review("Spider Man: Homecoming", 95,
        "A fun adventure");
    Review r4 = new Review("My Neighbour Totoro", 99,
        "A work of art");
    Review r5 = new Review("Despicable Me3", 60,
        "Zany but scattershot humour");

    System.out.println(r1.compareTo(r2)); // -1
    System.out.println(r2.compareTo(r1)); // 1
    System.out.println(r2.compareTo(r3)); // 0

    Review[] badFruit = {r1, r2, r3, r4, r5};
    Arrays.sort(badFruit);
    for (int i = 0; i < badFruit.length; i++) {
        System.out.println(badFruit[i]);
    }

}
```

The reviews come out sorted by rating:

```
Emoji Movie (10): Cinematic malware; likes = 0
Despicable Me3 (60): Zany but scattershot humour; likes = 0
Dunkirk (95): Gifted ensemble cast and masterful direction; likes = 0
Spider Man: Homecoming (95): A fun adventure; likes = 0
My Neighbour Totoro (99): A work of art; likes = 0 
```

### 2.8.4. Being comparable enables more
In addition to enabling sorting, a class that implements `Comparable` can be used in certain "Collections" that care about order, such as `SortedSet`. You will learn about Collections later in this course.

## 2.9. Comparator
What if we want to be able to choose between ordering reviews according to their rating, the length of their text or the number of likes they have? There can only be one `compareTo` in the class. Or what if we want to make instances of `MonthDay` comparable according to just the month, so February 14th and February 20th would be considered tied? We didn't write this class, so we can't change what its `compareTo` does.

We can accomplish these goals by defining a "comparator" class for each kind of comparison we want. A "comparator" class implements the `Comparator` interface, which requires this method:
```java
int compare(T o1, T o2)    
    Compares its two arguments for order.
    Returns a negative integer, zero, or a positive integer
    as the first argument is less than, equal to, or greater
    than the second.
```

Here's an example of a `Comparator` that orders `Reviews` according to likes:
```java
import java.util.Comparator;

class LikesComparator implements Comparator<Review> {
     /**
      * Compares its two arguments for order.
      *
      * Returns a negative integer, zero, or a positive integer
      * as r1 is less than, equal to, or greater than r2 in terms
      * of number of likes.
      *
      * @param r1 the first Review to compare
      * @param r2 the second Review to compare
      * @return a negative integer, zero, or a positive integer
      *      as r1 is less than, equal to, or greater than r2
      */
    @Override
    public int compare (Review r1, Review r2) {
        return r1.getLikes() - r2.getLikes();
    }
}
```

(Sidenote: The `compare` method needs to know the number of likes a review has received. Since this is stored in a private instance variable, we have added a getter method to provide access to it.)


Now we can use a version of `sort` that accepts a `Comparator` as a second argument, and uses it to determine how things are sorted. Here, we call it with our `LikesComparator`:

```java
public static void main(String[] args) {
    Review[] freshVeg = {r1, r2, r3, r4, r5};
    // Let's add some likes so the sorting will be interesting.
    r1.like();
    r1.like();
    r1.like();
    r4.like();
    r3.like();
    Arrays.sort(freshVeg, new LikesComparator());
    for (int i = 0; i < freshVeg.length; i++) {
        System.out.println(freshVeg[i]);
    }
}
```

The output produced is indeed in order according to likes:

```
Dunkirk (95): Gifted ensemble cast and masterful direction; likes = 0
Despicable Me3 (60): Zany but scattershot humour; likes = 0
Spider Man: Homecoming (95): A fun adventure; likes = 1
My Neighbour Totoro (99): A work of art; likes = 1
Emoji Movie (10): Cinematic malware; likes = 3
```

We can define another Comparator that orders Reviews differently. This one does it by length:
```java
import java.util.Comparator;
 

class TextComparator implements Comparator<Review> {

    /**
     * Compares its two arguments for order.
     *
     * Returns a negative integer, zero, or a positive integer
     * as r1 is less than, equal to, or greater than r2 in terms
     * of length of text.
     *
     * @param r1 the first Review to compare
     * @param r2 the second Review to compare
     * @return a negative integer, zero, or a positive integer
     *      as r1 is less than, equal to, or greater than r2
     */
    @Override
    public int compare (Review r1, Review r2) {
        return r1.getText().length() - r2.getText().length();
    }
}
```

(Here, we needed to add a getter for the `text` instance variable.)

Now we can write code that sorts `Reviews` using this `TextComparator` instead of the `LikesComparator`:

```java
    public static void main(String[] args) {
        Review[] rottenVeg = {r1, r2, r3, r4, r5};
        r1.like();
        r1.like();
        r1.like();
        r4.like();
        r3.like();
        Arrays.sort(rottenVeg, new TextComparator());
        for (int i = 0; i < rottenVeg.length; i++) {
            System.out.println(rottenVeg[i]);
        }
    }
```

This output is in order according to the length of the review text:
```
My Neighbour Totoro (99): A work of art; likes = 2
Spider Man: Homecoming (95): A fun adventure; likes = 2
Emoji Movie (10): Cinematic malware; likes = 6
Despicable Me3 (60): Zany but scattershot humour; likes = 0
Dunkirk (95): Gifted ensemble cast and masterful direction; likes = 0
```

### 2.9.1. When to use Comparable vs. Comparator? 
If you are not the author of the class, you cannot make it Comparable. Your only option is to define one or or more comparators.

If you are the author of the class, you have both options available to you!
