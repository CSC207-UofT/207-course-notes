# SOLID principles

A set of five software design principles has been developed over the decades to help keep coupling low and cohesion high. Robert Martin, the CSC207 textbook author, put them together in 2000, although they were each invented earlier.

These [SOLID principles](https://en.wikipedia.org/wiki/SOLID) are very widely used in industry, and they may come up in software internship interviews.

1. _Single responsibility principle_ (SRP): a class should have only one reason to change
2. _Open/closed principle_ (OCP): a class should be open for extension but closed for modification
3. _Liskov substitution principle_ (LSP): subclasses should be substitutable for their base classes
4. _Interface segregation principle_ (ISP): programmers should not be forced to write interface methods they do not use
5. _Dependency inversion principle_ (DIP): high-level modules should not depend on low-level modules. Both should depend on abstractions

# SRP: a class should have only one reason to change

The Single-Responsibility Principle means "Gather together the things that change for the same reasons. Separate those things that change for different reasons." (Robert Martin, https://blog.cleancoder.com/uncle-bob/2014/05/08/SingleReponsibilityPrinciple.html)

Robert Martin went on to say that the SRP is essentially just describing high cohesion and low coupling. There's more to it philosophically, but that's the basic idea.

# OCP: a class should be open for extension but closed for modification

The Open/Closed Principle means that you should be able to add new functionality to a program without editing the code.

As examples, IntelliJ and lots of games and other applications have a "plugin" or "extension" system. When you write a plugin, you're writing code that gets called by the main program, but you _don't_ edit the main program's code directly. Instead, the plugin implements a set of methods that the main program will call. This is an example of the Open/Closed Principle in action.

This is often done using inheritance or interfaces. In Java, interfaces can be used to specify the methods that plugin classes needs to implement. That way, the main program doesn't need to know the exact type of the plugin class. Either that or an abstract class can be used to provide default implementations of some methods, while still requiring the plugin class to implement other methods.

[TODO: write up the shape AreaCalculator example as an activity.]

# LSP: subclasses should be substitutable for their base classes

Formally, if S is a subtype of T, then objects of type S may be substituted for objects of type T, without altering any of the desired properties of the program.

In Java, T could be a class or an interface. S will either extends class T, or S will implement interface T.

The Liskov Substitution Principle is about ensuring that subclasses can be used wherever their base classes are used without causing problems. This mostly happens naturally, but make sure you're not preventing functionality that worked in the base class from working in the subclass.

For example, if you have a `Bird` class with a `fly` method, and then you create a `Penguin` subclass that overrides the `fly` method to throw an exception, that would violate the LSP because penguins can't fly. Any code that expects to be able to call `fly` on a `Bird` would break if it was given a `Penguin`.

Instead, you might refactor the entire design to avoid this issue. For example, you might have a `FlyingBird` subclass of `Bird`, and then have `Sparrow` extend `FlyingBird` and `Penguin` extend `Bird`.

[TODO: write up Square/Rectangle example as an activity.]

# ISP: programmers should not be forced to write interface methods they do not use

The Interface Segregation Principle means that no programmer should be forced to write irrelevant methods.

For example, if you have an interface `Animal` with methods `eat`, `sleep`, and `fly`, a class `Dog` that implements `Animal` would be forced to implement the `fly` method, which is irrelevant for dogs.

Instead, you might split the `Animal` interface into smaller interfaces, such as `Eater`, `Sleeper`, and `Flyer`, each with only a method or two. Then, the `Dog` class would implement only the `Eater` and `Sleeper` interfaces.

[TODO: Paul likes to perform this in class http://blog.cleancoder.com/uncle-bob/2015/01/08/InterfaceConsideredHarmful.html]

# DIP: high-level modules should not depend on low-level modules

Methods in the Entities layer should not depend on code in any of the other layers.

The Use Cases layer should not depend on code in the Interface Adapters layer, and code in the Interface Adapters layer should not depend on code in the Frameworks and Drivers layer.

Instead, all layers should depend on abstractions, such as interfaces or abstract classes.