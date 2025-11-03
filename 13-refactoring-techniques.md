# Chapter 13: Refactoring Techniques

This chapter covers some common refactoring techniques. For each technique, there is a short description
followed by a link to documentation for how to apply the technique in IntelliJ or a short explanation if
IntelliJ doesn't support the refactoring technique directly.

> There are lots more refactoring techniques, but the goal here is to give a brief introduction to some of the most common ones.

## 13.1. What is refactoring?

The process of _refactoring_ refers to when programmers make a series of changes that improve the design of the software — but without changing the behaviour.
There are lots of reasons for this:
- to make the code easier for other programmers to understand and navigate (and debug),
- to make it easier to automatically test, and
- to make it easier to add new features.

Some refactoring will involve style changes: naming, indentation, brace placement, and so on.
Some refactoring will involve restructuring your code: adding a parameter to a method,
moving a method from one place to another, reordering code to group related statements, and so on.

**As a rule, when refactoring, the code should always pass the existing tests!**

Let's take a look at a few common refactoring techniques!

---

## 13.2. `Extract Method`

This refactoring technique will likely feel very familiar to you from your first-year CS courses.
When developing code, you are generally advised to write helper methods (or functions) to break the code into smaller, more meaningful snippets. This can help greatly with understandability
and, potentially, testability of your code. If the author of the code wrote all the logic inside the body
of a single method, then you would need to apply Extract Method to move the code for
a subtask into a separate helper method. Depending on the purpose and context of the extracted method,
you may choose to make it private and possibly static.

> * You can make a method static if it only uses its parameters. (It may also use static variables and methods in its class.)
> * Choose a good name when you extract the method to clearly convey its purpose. Method names are usually verb phrases.

Once you apply the Extract Method technique, the intention of the code should be much clearer. An interested
programmer can still easily trace through to the implementation of the helper if they need to know the details,
but they are no longer forced to see them if they aren't important.

You may find that you need to apply this technique several times for complicated methods with several logical steps involved
in a computation.

> See: https://www.jetbrains.com/help/idea/extract-method.html

* * *

## 13.3. `Change Method Declaration`

This refactoring technique involves modifying how a method is defined, such as altering what its parameters are. When doing so,
one must be careful to update any client code, as well as the body of the method being altered.

In IntelliJ, `Change Signature` helps you perform such a refactoring.

> See: https://www.jetbrains.com/help/idea/change-signature.html

* * *

## 13.4. `Encapsulate Fields`

In Java, the convention is to make instance variables as private as possible. Transitioning from Python programming, where
we tend to default to public attributes, you may not yet be in the habit of declaring your data to be private. This refactoring
involves changing the access modifiers on your instance variables, introducing getters and setters as needed to provide appropriate
access to your data, and updating any client or implementation code to use the getters and setters where the data was
directly accessed previously.

> * You can use IntelliJ's support for this to save you time. Declare your instance variables, then select `Refactoring -> Encapsulate Fields...`
> * Declaring an instance variable to be public will be flagged by SonarQube. You can read more about the issue at
https://rules.sonarsource.com/java/RSPEC-1104/ along with ways to fix it, with one of the ways being to encapsulate fields.

> See: https://www.jetbrains.com/help/idea/encapsulate-fields.html

* * *

## 13.5. `Split Loop`

Sometimes we lump together several computations within a single block of iterative code. When we do this, it can become
difficult to extract related blocks of functionality from our code through refactoring techniques like `Extract Method`.

As the name suggests, this technique involves splitting a loop out into separate loops so that each loop performs an independent computation.

For example, think of a time you looped over a list of values and had two accumulator variables.
Applying this refactoring, you would instead perform two iterations over the list: one to accumulate the first variable and one
to accumulate the second variable.

Once these loops are split, it takes us one step closer to being able to apply `Extract Method` to then
hide the existence of the loops entirely from the reader of the code, enhancing the understandability of your code. Of course,
assuming we are still defining our accumulator variables above the first loop, we still don't have the code completely
split. This is where the next refactoring technique will help us out.

> Note: this refactoring is not directly supported by IntelliJ, but is straightforward to implement using copy+paste followed by
> deleting the duplicated parts of the loop bodies.

* * *

## 13.6. `Slide Statements`

This technique simply refers to moving lines of code around to help group together code in more meaningful ways. For example,
continuing with the example from above, we may want to move the declaration of each accumulator variable so that they are defined
immediately before each of their corresponding accumulator loops. Once this is done, it would then be possible to apply our
favourite `Extract Method` technique.

This can be achieved using cut+paste, but it turns out IntelliJ has shortcuts which allow you to move lines of code.

> See: https://www.jetbrains.com/guide/java/tutorials/rearranging-code/moving-statements-around/

* * *

## 13.7. `Replace Constructor with Builder`

This technique involves refactoring how objects are constructed. The idea is to introduce a builder class that is responsible for constructing instances of the class in steps.

> See: https://www.jetbrains.com/help/idea/replace-constructor-with-builder.html

* * *

## 13.8. `Replace Constructor with Factory Method`

This technique also involves refactoring how objects are constructed. The idea is to introduce
a static method that is responsible for returning the instance of the class rather than having the code directly call
the constructor. This can enhance understandability and hiding the constructor call effectively abstracts
away the detail of how where the instance of the class actually comes from.

> See: https://www.jetbrains.com/help/idea/replace-constructor-with-factory-method.html

> See also: Static Factory methods are discussed in more detail in Effective Java by Joshua Bloch.

* * *

## 13.9. Additional Reading:

Refactoring: second edition by Martin Fowler
Free first chapter: https://www.thoughtworks.com/content/dam/thoughtworks/documents/books/bk_Refactoring2-free-chapter_en.pdf

Overview of refactoring which highlights some of these and other refactoring techniques supported by IntelliJ:
https://www.jetbrains.com/help/idea/tutorial-introduction-to-refactoring.html

Old but still interesting overview of refactoring in IntelliJ (exact features differ now of course)
https://objectcomputing.com/resources/publications/sett/february-2002-refactoring-with-idea

* * *

## 13.10. Helpful links:

JetBrains blog post about the Extracting and Inlining, Change Signature, and Renaming (includes a video):
https://blog.jetbrains.com/idea/2020/12/3-ways-to-refactor-your-code-in-intellij-idea/

Related to the above, JetBrains has a GitHub repo with examples used in various resources they provide:
Repo: https://github.com/JetBrains/intellij-samples

Gregor Riegler has a video demonstrating a series of refactorings.
Video: https://www.youtube.com/watch?v=sIceCgI6QO0
Repo: https://github.com/gregorriegler/refactoring-split-phase

Here is a practice refactoring challenge: https://github.com/emilybache/Theatrical-Players-Refactoring-Kata.
It is based on the Refactoring (Second Edition) textbook by Martin Fowler.
