# Chapter 3: Relationships between Classes

## 3.1. Inheritance
You may recall that we could inherit methods from another class in Python as follows:
```python
class Child(Parent):
    ...
```

In Java, inheritance works in a similar manner. However, instead of using brackets, we use the `extends` keyword:
```java
class Child extends Parent {
    ...
}
```

This means that class `Child` inherits all of the methods and variables defined in `Parent`, and that `Child` is an instance of `Parent`.

Throughout this chapter, we will be using the terms parent class/child class and superclass/subclass interchangeably.

### 3.1.1. Abstract classes
Abstract classes are classes that are not meant to be initialized. In Python, we signified a method was abstract by having a method that would raise a `NotImplementedError`. Any non-abstract child class would then have to implement this method.

In Java, we use the `abstract` keyword to signify that a class is abstract: this enforces the fact that no instance of the class should be created, even if there are no abstract methods in the class! We also use the `abstract` keyword for any abstract methods.

```java
abstract class AbstractClass{
    abstract void something();    // Abstract methods have no body!
}
```

Any non-abstract class that extends an abstract class then has to implement the body of all abstract methods.
```java
class NonAbstract extends AbstractClass{
    void something(){
        ...   // Method body here!
    }
}
```

### 3.1.2. Overriding methods
In Python, we could override a parent class' methods by redefining it. In Java, we do the same thing, but we also include an `@Override` annotation. This informs the compiler that method is meant to override an element in a superclass. While the annotation is not required, including it helps us prevent errors (e.g. misspelling the name of a method, forgetting a parameter, etc.)

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
In Java, you can only extend a single class: you have one parent class, and that's it! However, sometimes we want to describe more behaviours for a class in a way that just one parent won't suffice.

For example, suppose we're writing a program to simulate plants. We would have a class called `Plant`: all `Plant`s are able to `breathe` and `grow`! We could also have subclasses such as `Wheat` and `Flower`s with their own subclasses. However, suppose we want to indicate that some plants are edible for humans: for instance `Corn` would have an `eat` method, and so would `Basil`. Not all plants are edible, so we can't add that method to `Plant`. We *could* define an `EdiblePlant` class, but then we would also need `EdibleWheat`, `EdibleFlower`, and so on: this isn't a very clean solution!

In cases where we wan't to define a property of a class, we can use interfaces. Interfaces are similar to classes, except they have no implementation details at all: only method signatures! They can also have variables, but these variables must be `static` and `final`. In addition, **everything** in an interface must be `public`.


For our example, we would define an Edible interface such as:
```java
interface Edible {
    void eat();
}
```

And to use it, we would use the `implements` keyword:
```java
class Corn extends Plant implements Edible {
    void eat(){
        ...    // Our implementation here!
    }
}

```

We can implement as many interfaces as we want! In addition, interfaces can also `extend` other interfaces (*not* `implements` -- an interface doesn't implement anything!)

As an example, some food can be steamed so we might want a `Steamable` class. These are also edible, so we could do the following:
```java
interface Steamable extends Edible {
    void steam();
}

```

Any class that `implements Steamable` must then have both a `steam` and `eat` method!


## 3.3. super
In Python, we could use `super()` to refer to methods in the parent class. For instance, we could use `super().__init__()` to call the parent constructor or `super().method()` to call the parent's method.

In Java, we have the `super` keyword that functions in a similar way. If we want to call a parent's constructor, we use `super()`, or `super(a, b, c)` if we needed to pass in some parameters. If we wanted to call a parent's method, we would use `super.method()`!

Note the difference between Python and Java: `super()` is used in Python and has brackets while `super` is used in Java with no brackets!

### 3.3.1. Constructors with super
When extending another class, Java *requires* a call to a superclass' constructor to be made in the subclass' constructor. Furthermore, this call *must* be the very first thing done. If no constructor call is explicitly made in the subclass' constructor, then an *implicit* call to the `super()` will be made.

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

If `Parent` didn't have a constructor that takes no arguments, then an error would be raised during compilation. **It's best to explicitly include our super(...) calls in our constructors!** This way we know precisely which constructor is being called by our subclass.


## 3.4. Polymorphism
Polymorphism is the ability of an object to take many forms. We consider an object to be polymorphic if it passes multiple `instanceof` tests. For example, if we had the following code:

```java
class Dog extends Canine implements Domesticatable {...}
```

Then a Dog is also a Canine (which might have further superclasses like Animal)! It's also Domesticatable, and would pass `instanceof` tests for all of these types.

An example of polymorphism in use is when we have a variable whose value may be of a type other than the variable's type itself: for example being a subclass of that type or if the type in question is an interface, a class that implements it. 

As an example, the following would exhibit polymorphism:
```java
Animal[] animals = {new Cat(), new Dog(), new Axolotl()};

for (Animal a : animals){
    a.eat();    // 'a' in this line of code can have various types!
}
```

Even though we have multiple types of objects, we can deal with them in a uniform matter. If we didn't have polymorphism here, we wouldn't be able to have such a simple and clean loop!

## 3.5. Casting
Unlike in Python, all variables have a declared type in Java. Often, we'll want to convert between the types used: sometimes a more general superclass is better for one situation, but a subclass is better in another. Casting is when we change the type of an object to another, often in order to access more specific functionality.

For example, suppose we have the `Animal` example from 3.4., but we want a `Cat` to `purr` after eating. We could do the following:

```java
Animal[] animals = {new Cat(), new Dog(), new Axolotl()};

for (Animal a : animals){
    a.eat();
    if (a instanceof Cat){
        ((Cat) a).purr();
    }
}
```

The `(Cat) a` converts `a` into the type `Cat`, allowing us to use the `Cat` method `purr()`! This specific type of casting is called **downcasting**: we're casting the type of a variable into its subclass. The opposite of this is **upcasting**, which we did in the line `Animal[] animals = {new Cat(), new Dog(), new Axolotl()}`: the `Cat`, `Dog`, and `Axolotl` were all cast into a superclass.

### 3.5.1. Primitive conversions
We can also cast some primitives, such as converting an `int` into a `double` and vice versa:
```java
int x = 1;
double y = 1.1;
double double_x = (double) x;
int int_y = (int) y;
```

When we cast objects, we essentially 're-label' the type, but otherwise leave it unchanged. However, when we cast primitives, we're adjusting the value of the variable itself: the changes may potentially be irreversible!
