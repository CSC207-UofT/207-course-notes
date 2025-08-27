# Chapter 3: Relationships between Classes

## 3.1. Inheritance
You may recall that we could inherit methods from another class in Python as
follows:
```python
class Child(Parent):
    ...
```

The syntax above defines class `Child` to be a subclass of class `Parent`.

In Java, inheritance works in a similar manner. However, instead of using brackets,
we use the `extends` keyword:
```java
class Child extends Parent {
    ...
}
```

This means that class `Child` inherits all the methods and variables
defined in `Parent`, and that `Child` is an instance of `Parent`.

Throughout this chapter, we will be using the terms parent class/child class and
superclass/subclass interchangeably.

TODO do we need to elaborate on any of this????

### 3.1.1. Abstract classes
Abstract classes are classes that are not meant to be initialized directly.
In Python, we signified a method was abstract by having a method that would
raise a `NotImplementedError`. Any non-abstract child class would then have
to implement this method.

In Java, we use the `abstract` keyword to signify that a class is abstract:
this enforces the fact that no instance of the class should be created,
even if there are no abstract methods in the class! We also use the `abstract`
keyword to indicate any abstract methods.

```java
abstract class AbstractClass{
    abstract void something();    // Abstract methods have no body!
}
```

Any non-abstract class that extends an abstract class then has to implement the
body of _all_ abstract methods.
```java
class NonAbstract extends AbstractClass{
    void something(){
        ...   // Method body here!
    }
}
```

### 3.1.2. Overriding methods
In Python, we could override a parent class' methods by redefining it.
In Java, we do the same thing, but we also include an `@Override` annotation.
This informs the compiler that method is meant to override a method in a superclass.
While the annotation is not required, including it helps us prevent errors
(e.g., misspelling the name of a method, forgetting a parameter, etc.).

For example, if we have the following parent class:
```java
class Parent {
    void something(){
        ...    // Some method body here!
    }
}
```

We can override the method `something` as follows:
```java
class Child extends Parent {
    @Override
    void something(){
        ...    // Our new method body here!
    }

}
```

## 3.2. Interfaces
In Java, you can only extend a single class: you have one parent class,
and that's it! However, sometimes we want to describe more behaviours for a
class in a way that just one parent won't suffice.

For example, suppose we're writing a program to simulate plants.
We would have a class called `Plant`: all `Plant` objects are able
to `breathe` and `grow`. We could also have subclasses such as
`Wheat` and `Flower` with their own subclasses. However, suppose we want to
indicate that some plants are edible for humans: for instance `Corn` could
have an `eat` method, and so could `Basil`. Not all plants are edible,
so we can't add that method to `Plant`. We *could* define an `EdiblePlant` class,
but then we would also need `EdibleWheat`, `EdibleFlower`, and so on: this
isn't a very clean solution. Interfaces allow for an alternative design.

An **interface** in Java defines a contract for what a class can do,
without specifying how it does it. It’s used to define shared behavior across
potentially unrelated classes. This is similar to an abstract class, but
fundamentally differs in that a class can implement any number of interfaces.

In an interface:

- All methods are implicitly `public` and **abstract**, unless marked otherwise.
  You can try defining a method in your own interface and see what IntelliJ warns
  you about if you include various keywords.
- Variables in an interface are implicitly `public`, `static`, and `final`. You
  can not have instance variables for an interface — try it in IntelliJ and see what happens.
- Classes that implement an interface must provide implementations for its abstract methods.

Originally, interfaces in Java could only contain abstract methods. However,
since **Java 8**, interfaces can also include:

- `default` methods — methods with a body, allowing backward-compatible enhancements
  without requiring updates to any classes already implementing an existing interface.
- `static` methods — useful for utility behavior related to the interface.

For our example, we could define an `Edible` interface such as:
```java
interface Edible {
    // Method that must be implemented by any class implementing Edible
    void eat(); // note that we don't include the abstract or public keywords!
}
```
And to define a `Washable` interface with a `default` method `wash`,
we must use the keyword `default` when defining it:

```java
interface Washable {
    // Default method that provides a basic implementation
    default void wash() {
        System.out.println("Washing the edible item...");
    }
}
```

And to use both interfaces, we would use the `implements` keyword:

```java
class Corn extends Plant implements Edible, Washable {
    void eat() {
        ...    // Our implementation here!
    }
    
    // Overriding the default wash method
    @Override
    public void wash() {
        System.out.println("Thoroughly washing the corn...");
    }
}
```

If a class implements multiple interfaces,
they are separated by commas after the `implements` keyword.

We can implement as many interfaces as we want! In addition,
interfaces can also `extend` other interfaces.

As an example, some food can be steamed so we might want a
`Steamable` interface. These are also edible, so we could do the following:
```java
interface Steamable extends Edible {
    void steam();
}

```

Any class that `implements Steamable` must then have both a `steam` and `eat` method!


## 3.3. super
In Python, we could use `super()` to refer to methods in the parent class.
For instance, we could use `super().__init__()` to call the parent constructor
or `super().method()` to call the parent's method.

In Java, we have the `super` keyword that functions in a similar way.
If we want to call a parent's constructor, we use `super()`, or `super(a, b, c)`
if we needed to pass in some parameters. If we wanted to call a parent's method,
we would use `super.method()`!

Note the difference between Python and Java: `super()` is used in Python and has
brackets while `super` is used in Java with no brackets!

### 3.3.1. Constructors with super
When extending another class, Java *requires* a call to a constructor of its
superclass to be made in the constructor of the subclass. Furthermore, this call
*must* be the very first thing done. If no constructor call is explicitly made
in the subclass constructor, then an *implicit* call to `super()` will be made.

For instance, this code:

```java
class Child extends Parent {
    int attribute1;
    int attribute2;

    public Child(int a, int b) {
        // There's no super() call here, but it's implicit!
        this.attribute1 = a;
        this.attribute2 = b;
    }
}
```

Would be equivalent to:

```java
class Child extends Parent {
    int attribute1;
    int attribute2;

    public Child(int a, int b) {
        super();  // What happens if Parent has no empty constructor?
        this.attribute1 = a;
        this.attribute2 = b;
    }
}
```

If `Parent` didn't have a constructor that takes no arguments,
then an error would be raised during compilation. The only time
Java will implicitly call the parent constructor is when the only constructor
that exists in the parent class takes no arguments. That is, when there is
no ambiguity about our intention. When developing code in IntelliJ, you will see
helpful error messages when developing a constructor for your own subclasses.

If the parent class has a constructor taking no arguments, then we can even
omit a constructor in the subclass if we don't need to perform any additional
information. Java will implicitly call the parent constructor when creating
a new instance of our subclass.


## 3.4. Polymorphism

**Polymorphism** is the ability of an object to take on many forms. 
In Java, this means that an object can be treated as an instance of its own class,
any superclass, or any interface it implements.

For example, if we had the following code:

```java
class Dog extends Canine implements Domesticatable { ... }
```

A Dog object is:
- a `Dog`
- a `Canine`
- possibly an `Animal` (if `Canine` extends `Animal`)
- an `Object` (recall `Object` will be at the top of the hierarchy)
- a `Domesticatable` (since `Dog` implements this interface)

This means a Dog instance will pass `instanceof` checks for _all_ of these types:

```java
Dog d = new Dog();
System.out.println(d instanceof Dog);              // true
System.out.println(d instanceof Canine);           // true
System.out.println(d instanceof Animal);           // true
System.out.println(d instanceof Object);           // true
System.out.println(d instanceof Domesticatable);   // true
```

> **Note:** `instanceof` in Java is not strictly limited to parent/child class
> relationships.  
> An object is considered an instance of a class or interface if it can be
> **safely cast** to that type.  
> This includes interfaces — any object that implements an interface will pass
> `instanceof` checks for that interface.

Polymorphism enables flexible and reusable code by allowing objects to be treated
according to their capabilities rather than their exact types.

As an example, the following would exhibit polymorphism:
```java
Animal[] animals = {new Cat(), new Dog(), new Axolotl()};

for (Animal a : animals){
    a.eat();    // 'a' in this line of code can have various types!
}
```

Even though we have multiple types of objects, we can deal with them in a
uniform manner. If we didn't have polymorphism here, we wouldn't be able to
have such a simple and clean loop! Much of design comes down to defining
the right abstractions to allow code to be written in a way that is flexible,
extensible, and easy to reason about.

By programming to an interface or superclass, rather than a specific implementation,
we enable our code to work with a wide variety of objects that share common behavior.
This not only reduces duplication, but also makes it easier to introduce new types
without changing existing logic — a key principle of maintainable software design.

## 3.5. Casting

As we have learned, unlike in Python, all variables in Java have a declared type.
Often, we'll want to convert between types: sometimes a more general superclass
is better for one situation, while a subclass is better in another.

**Casting** is when we explicitly change the type of a reference to another,
usually to access more specific functionality that isn't available in the
more general type. For example, if we have a reference of type `Animal`,
but we know it's actually referencing a `Dog` object, we can cast it to `Dog`
to call methods that only exist in the `Dog` class.

```java
Animal a = new Dog(); // a dog is an animal so we can make this assignment
((Dog) a).bark(); // casting to Dog to access bark()
```

Casting is safe only when the actual object is an instance of the target type.
If not, it will throw a `ClassCastException` at runtime.

In the example above, we actually have two examples of casting. The first line
is performing **upcasting**. Upcasting is when we assign a subclass object to a
superclass reference. This is **implicit** and always safe, because every
`Dog` is an `Animal`. The second line is performing **downcasting**.
Downcasting is converting from a superclass to a subclass.
It is only safe if the actual object is an instance of the subclass, and should
be checked with `instanceof` to avoid runtime errors.

We can use `instanceof` to check before casting as follows:

```java
if (a instanceof Dog) {
    ((Dog) a).bark();
}
```

This ensures that the cast is valid and avoids runtime errors.

### 3.5.1. Primitive conversions

We can also cast some primitives, such as converting an `int` into
a `double` and vice versa:

```java
int x = 1;
double y = 1.1;
double double_x = (double) x;
int int_y = (int) y;
```

When we cast **objects**, we're essentially "re-labeling" the reference type —
we're telling the compiler to treat the object as a different type, without
changing the object itself. The actual data remains the same.

However, when we cast **primitives**, we're converting the value itself,
which may result in **loss of precision** or **truncation**. For example,
casting a `double` to an `int` removes the decimal part:

```java
double y = 3.7;
int truncated = (int) y; // truncated to 3
```

This kind of change is **irreversible** in the sense that the original
precision is lost — you can't recover the `.7` from the `int` without
referring back to the original `y`. Of course, the variable `y` still refers
to 3.7.

Primitive casting can be **implicit** (e.g., `int` to `double`) or **explicit**
(e.g., `double` to `int`), depending on whether there's a risk of data loss.
