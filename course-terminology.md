This is a list of terminology used in CSC207 Softare Design at the University of Toronto. We are crowdsourcing examples of use. We welcome pull requests!  

# Version control and Java Basics

version control
* Teams of programmers use *version control* to manage changes to source code over time.

repository, git, add, commit, push, pull, clone, fork, "branch-and-merge workflow", pull request
* A repository is a directory that contains your project work, as well as a few files (hidden by default) which are used to communicate with Git.

command line (terminal)
* The command line is an interface that provides us with an alternative way to interact with our devices. Instead of clicking on graphical buttons and icons to launch programs and switch between tabs and windows, you can run instructions to perform these actions by explicitly writing commands to the command line, akin to a programming language.

"Strongly typed language"

method, class, variable, type

constructor
* You call a constructor to instantiate (an object of) a class.

static information vs. instance information (methods and variables)
* Static information belongs to the class itself. A single copy is shared by all instances, and static methods can be called without creating an object.
Instance information belongs to individual objects. Each object has its own copy of instance variables, and instance methods operate on the specific state of the object they are called on.

class vs. instance

instantiate
* You call a constructor to instantiate (an object of) a class.

# Java topics and OOP concepts

**Encapsulation**: The boundary around a class such that outside that boundary that data is hidden and only some of the functions are known. In Java this concept is implemented with the
public, protected and private keywords. 

**Inheritance**: When a class (the subclass) automatically acquires the fields and methods of another class (the superclass), 
allowing code reuse and the creation of hierarchical relationships. In Java, this is done using the _extends_ keyword.

**"lookup rules" for a running program**: How a running program decides which variable or function to use.


**Interface:** in Java defines a contract for what a class can do, without specifying how it does it.

**Abstract Method** A method that does not have an implementation (i.e., it only has a signature).

**Abstract Classes** Classes that are not meant to be initialized directly. 
* If a class extends an abstract class; then, it has to implement all the abstract functions or be declared an abstract class. 

**Generics** A way for programmers to generalize the type that a class or method works with. They allow us to reuse the same 
code for various input types without needing to cast things constantly. For example, public class Box<T>

**Loose coupling:** Classes depend on each other as little as possible. (Related to Dependency Inversion Principle).

**High cohesion:** Classes single well focused responsibility. (Think Single Responsibility Principle).

**Polymorphism** The ability of an object to take on many forms. In Java, this means that an object can be treated as an instance of its own class, 
any superclass, or any interface it implements

**UML (unified modelling language)** visually show classes or interfaces, their variables and methods, and how they inherit or implement each other.

**"design" of a program**: how classes should interact with each other, often represented using UML diagrams.

**class diagram**: A UML diagram showing a class’s name, its instance variables with types, and its methods with arguments and return types.

**composition of classes**: How classes use other classes inside their methods.

**dependency**: Anything a class or module needs to run. ClassA is dependent on ClassB if change in code to ClassB required change in code in ClassA.
If a class uses the _new_ keyword, it is dependent on the other class.

**Dependency Injection**: A design pattern where an object receives its dependencies from an external source. For example define the dependency
as an instance variable through the constructor rather than writing API api = new API(); 

# Design

design principle

SOLID principles

SRP, O/CP, LSP, ISP, and DIP

actor (from the "Single Responsibility Principle")

modify vs extend

"more abstract" vs "less abstract"

Application Programming Interface (API)

specification

UI (user interface)

architecture, screaming architecture

boundary

use case of a program

user story

design walkthrough

sequence diagram

clean architecture

gateway

controller

presenter

view

view model

enterprise business rules

application business rules

use case

entity

configuration file

packages (by layer, by component, by use case, by feature, etc.)

refactor

# Exceptions in Java

error vs exception

"exceptional circumstances"

runtime exception

checked exception vs unchecked exception

try, catch, finally

# Design patterns and testing

design pattern

anti-pattern

Observer, Strategy, Iterator, Dependency Injection, Factory, Builder

unit testing

test driven development

mocking

# Ethics

disability

medical model vs social model

laws of accommodation

professionalism

# Interviewing

the power of a network

how to build a personal network

informational interview

generic vs targeted applications

cover letter

résumé

phone screen

tech challenge

corporate culture and "cultural fit"

leetcode
