# Chapter 8: Introduction to design

"Low coupling, high cohesion" is one of the most important concepts in the software development industry. It is one of the oldest sayings in software design, originating in the 1960s. "Low coupling" refers to the idea that different parts of a program should have as few dependencies on each other as possible.

A _dependency_ is when one part of a program relies directly on another part. Class A _depends on_ class B if class A mentions type B anywhere, such as a parameter type or a return type. A _circular dependency_ is a series of dependencies that lead back to the original class.

"High cohesion", on the other hand, is about a single class, and it means that the methods and data within a class are closely related.

An example of a class with low cohesion is a `Student` class that has methods to sign up for a club, register in a section of a course, calculate the student's GPA, and print information about the student.

# Event-driven programming: a network of objects

As you know from the Python memory model, a running program uses a _stack_ to track method calls, including local values and return values, and a collection of objects representing the data in the program. The collection of objects is often called the _heap_.

One object has an arrow to another object when it calls a method in that object. The network represents all the potential method calls in the program.

An _event_ is a user action, such as clicking a button or typing in a text field. An _event-driven program_ is one that responds to user events.

When a user clicks a button, a method (usually one that you write) gets called. This method is called an _event listener_, and when it is called it is passed an _event object_ that contains information about the event, such as the time it happened and the location of the mouse cursor.

At any point in program execution, the method at the bottom of the class stack is an event listener method, and the methods in the call stack form a chain of method calls through the network of objects.

Every possible user interaction is an event, and so to add a new user interaction to a program, you need to write an event listener for that user interaction, then write code to handle the event.

# Layers and coupling

A whole lot of professional software developers have spent a whole lot of time debating how to organize code. In order to avoid coupling, programs are usually thought of in terms of _layers_, where each layer has a specific role, such as managing the user interface or accessing a database.

Here are a few benefits:

* Separating them means that changes in one layer don't affect other layers because **the worst kind of changes are the ones that require a programmer to edit a lot of different files**.
* Thinking in layers helps avoid Git merge conflicts.
* Editing many files is fragile because there are more places where you might introduce a bug.
* All the code written for a particular layer will share similarities. The UI classes, for example, will have listener methods for each of the events.

# It's layers all the way down

A _model_ is how you choose to represent the data in the program, such as creating a `Student` class so you can use `Student` objects. These kinds of objects are often called _entities_.

Models are an important concept, because the entities represent how to think about the data being manipulated. It's particularly important (for testability and maintainability) that models not have any dependencies on the UI layer or the database layer. That means they entities can't mention any classes that you defined in those layers.

Here are a few _architecture patterns_, or ways to think about the organization of a program. You'll encounter these if you start following software design blogs. These are roughly in chronological order for when they were popular:

* model view controller (MVC)
* model view presenter (MVP)
* model view viewmodel (MVVM)
* ports and adapters architecture
* onion architecture
* hexagonal architecture
* clean architecture

They all exist to help keep coupling low and cohesion high, and they all have techniques to cross layers without introducing coupling. They have slight variations on what "model" means and how events are handled, but the core concept of it representing the problem domain data is shared.

## Methods that appear in all of them

You'll need to write an event listener method to handle a user interaction. This method must be in the UI layer

The event listener method will call a method in the controller/presenter/viewmodel layer, which will call methods that manipulate the problem domain data. Somewhere, there will be code that saves and reads data from a database, file, or network. 

# Model View Controller (MVC) architecture pattern

Here is a typical MVC thought process. 

![Model View Controller information flow](images%2FMVCPackt.png)

_Retrieved from https://subscription.packtpub.com/book/programming/9781788624060/7/ch07lvl1sec56/the-model-view-controller-pattern on 14 September 2025._

* The user interacts with the View, which creates an event
* The Java system calls a listener method in a View object
* The View immediately calls a method in the Controller to handle the event
* The Controller manipulates the Model
* The Model fetches any necessary data from the database
* The Model calls a method in a View object to let it know that there's been an update
* The Controller decides which screen to display
* The View calls getter methods on the Model and updates the UI

[TODO make an activity asking where the dependencies are?]

# Model View Presenter (MVP) architecture pattern

The MVC architecture pattern was the first one to be widely adopted in the software industry, but the circular dependencies and tight coupling between the View and the Model made it hard to test and maintain. The MVP architecture pattern was developed to address these issues.

You'll notice that the Presenter is largely playing the same role as the Controller in MVP, but the View and Model don't depend on each other.

![MVP.png](images%2FMVP.png)

* The user interacts with the View, which creates an event
* The Java system calls a listener method in a View object
* The View immediately calls a method in the Presenter to handle the event
* The Presenter manipulates the Model
* The Model fetches any necessary data from the database
* The Model calls a method in a Presenter object to let it know that there's been an update
* The Presenter calls methods in the View to update the user interface 

[TODO make an activity asking where the dependencies are?]

[TODO is there a SRP question here with the Presenter? That motivates splitting the Controller and Presenter parts like in CA]

# More fine-grained layered architectures

The other architectures — ports and adapters, onion, hexagonal, and clean — are all variations on the same theme. They all have a core layer that represents the problem domain data and logic, and they all have layers around that core layer that handle user interaction, databases, files, and networks.

The core layer is often called the _domain_ layer, and it contains the entities and the business logic. The business logic is the code that implements the rules of the problem domain, such as calculating a student's GPA or determining whether a user has permission to access a resource.

In the Clean Architecture, there are four layers:

1. Entities: the core layer that represents the problem domain data and logic
2. Use Cases: the layer that contains the application-specific business rules
3. Interface Adapters: the layer that contains the code that converts data from the format most convenient for the use cases and entities to the format most convenient for the external layers such as databases and the user interface
4. Frameworks and Drivers: the layer that contains the code that interacts externally with the user and with any databases

Here, the Model is in the Entities layer, the Controller/Presenter/ViewModel code is in in the Interface Adapter layer, the View and database are both in the Frameworks and Drivers layer, and the Use Case layer contains objects that are specific to a single user interaction. Each will have a method that the Controller calls to handle as much of the user interaction as possible.

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