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

![Model View Controller information flow](images/MVCPackt.png)

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

![MVP.png](images/MVP.png)

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

