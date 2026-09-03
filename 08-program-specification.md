# Chapter 8: Program Specification

Now that we have the fundamentals of OOP in Java out of the way, we are ready to think more deeply about how we go about developing a program.
Before we can implement anything, we need an idea of **what** functionality the program should provide. This may initially take the form of a written specification. The specification would use words relevant to the domain that the program will be used in.
For example, if developing software in a medical setting, words like "doctor", "medical records", "patient", and "insurance" are likely to appear.

As you know from your first-year courses, we can perform a noun-verb analysis of such a specification to arrive at an initial set of classes (with variables and methods) and abstractions (abstract classes and interfaces in Java) to model the specific domain. We refer to these classes that represent the data of the program as _entities_. These will be the building blocks of our program.

The output of this first step is often a set of UML class diagrams summarizing the initial set of proposed entities for our system.
Such diagrams can then be turned into code by developers.

This is a great first step, but what is missing is the bigger picture of **how** instances of these classes will be used to actually fulfill the specification!
This is where user stories come in as a systematic way to reason about specific aspects of the program's functionality and we begin
to put things together.

We start by looking more closely at that first step — turning a specification
into classes (§8.1) — and at how to sanity-check the design it produces (§8.2).
We then turn to user stories (§8.3) and use cases (§8.4).

## 8.1. From specification to classes: noun–verb analysis

**Noun–verb analysis** is the standard first pass at turning a written
specification into a design. The method is simple:

1. Read the specification. Then read it again — you will notice different things
   each time.
2. **Underline the nouns.** Each one is a candidate class, or a piece of
   information that some class will need to store.
3. **Circle the verb phrases.** Each one is a candidate **responsibility** —
   something the program must be able to do, which will eventually become a
   method.
4. Decide **which class is responsible** for each verb phrase, and which class
   stores each piece of information.

This is deliberately simplistic advice. It is a starting point, not a mechanical
rule: many nouns are not classes at all (some are just attribute values, some
are synonyms for a noun you already have, some are irrelevant), and some
responsibilities are not mentioned as verbs anywhere. You refine as you go, and
the refining is where the real design work happens.

### A worked example

Consider this specification for a restaurant review system:

> *Each restaurant corresponds to a certain price range, neighbourhood, and
> cuisines it serves. Restaurants that serve alcohol must have a license, which
> they need to renew every year. The system should also report how long, on
> average, customers wait for take-out in restaurants that offer take-out
> service.*
>
> *When reviewers leave a review for a restaurant, they must specify a
> recommendation (Thumbs Up or Thumbs Down) and can also leave a comment. An
> owner of a restaurant can respond to a review with a comment. All users of the
> system log in with their username. Users can choose to be contacted by email.*

Underlining the nouns gives us a long list:

> restaurant, price range, neighbourhood, cuisines, alcohol, license, customers,
> take-out service, reviewers, review, recommendation, comment, owner, users,
> username, email

Most of these will not become classes. A price range, a neighbourhood, a
username, and an email address are all just *values* that something else stores;
a recommendation is one of two fixed choices (an enum, perhaps). Narrowing to
the strongest **candidate classes**:

> `Restaurant`, `Reviewer`, `Owner`, `User`, `Review`

Circling the verb phrases gives us the candidate **responsibilities**:

> renew (a license), report the average wait time, leave/write a review, respond
> to a review, log in

### Refining the design

Now the interesting part. Look at the candidate classes and ask what they have
in common and how they differ.

**Owners and reviewers are both users.** Both log in with a username, and both may
choose to be contacted by email. Rather than duplicating that, make `User` an
**abstract class** holding `username`, `email`, and `logIn`, and let `Owner` and
`Reviewer` extend it.

**Not every restaurant has a liquor license.** Putting `license` and
`renewLicense` on `Restaurant` would leave those members meaningless for most
restaurants. A `LicensedRestaurant` subclass that owns the license and the
`renewLicense` responsibility says exactly what we mean.

**Not every restaurant offers take-out.** By the same reasoning you might reach
for a `TakeoutRestaurant` subclass with `getAvgWaitTime`. But then: what about a
restaurant that has a license *and* offers take-out? Java does not allow a class to extend
more than one class, so a single restaurant class cannot extend both
subclasses. This is precisely the
situation interfaces exist for: make `Takeout` an **interface** declaring
`getAvgWaitTime`, and any restaurant class — licensed or not — can implement it.
Noticing this early, on the diagram, is much cheaper than noticing it after you
have written the classes.

Finally, some decisions have no single right answer, and you should expect to
argue about them with your team:

- Does a `Review` know which `Restaurant` it is for?
- Does a `Review` know who wrote it?
- Where do reviews *live* — with the `Restaurant`, with the `Reviewer`, or in
  some separate collection?

Each choice makes some operations easy and others awkward. Talk through the
pros and cons rather than picking the first arrangement that occurs to you.

The output of a noun–verb analysis is usually a **UML class diagram**
summarizing the proposed classes and the relationships between them. See
[§3.8 UML Class Diagrams](03-relationships-between-classes.md#38-uml-class-diagrams)
for the notation. The classes you identify this way — the ones that represent
the data of the problem domain — are the program's **entities**.

## 8.2. Checking your design: scenario walk-throughs

A diagram always looks plausible. A cheap way to find out whether your proposed
classes actually *work* is a **scenario walk-through**: pick a scenario the
program must support, choose plausible inputs, and manually "execute" it against
your design.

1. Find the class responsible for the first step.
2. Trace through which other classes it must collaborate with, and what it must
   ask them to do.
3. Whenever you hit a step that no class is responsible for, or a class that
   needs information it has no way of getting, adjust the design.
4. Start the scenario over and repeat until it *stabilizes* — you get all the
   way through without making any changes.

### A worked example: "Write a review"

Walk through a reviewer writing a review for a restaurant, using the classes
from §8.1:

1. A `Reviewer` creates a `Review`, with a recommendation and a comment.
2. The `Reviewer` gives the `Review` to the `Restaurant`.
3. The `Restaurant` stores the `Review`.

Step 3 is a gap. Nothing in our noun–verb analysis said that a `Restaurant` was
responsible for storing reviews — "store" never appeared as a verb in the
specification. So we add an `addReview` responsibility (and a collection of
reviews) to `Restaurant`, and walk the scenario again.

That is the payoff: the walk-through surfaced a missing responsibility *before*
any code was written. The idea comes from CRC-card modelling, but it works just
as well with a UML class diagram in front of you.

## 8.3. User stories

A _user story_ describes a feature from the perspective of the user, and focuses on the value that the feature provides.

Here are a couple user stories for a social media app:

* As a user, I want to be able to send friend requests so that I can connect with people I know.
* As a group administrator, I want to be able to hide posts that violate the code of conduct so that others can feel more comfortable participating.

They follow a common, but optional, format: "As a [kind of user], I want to [accomplish a goal] so that [I receive some benefit]."

User stories are short on purpose. They give the team and client a starting point for discussing a feature; they are not usually a complete specification by themselves. A team may add acceptance criteria describing observable conditions that must be true for the story to be considered complete.

### Choosing an appropriate scope

User stories can describe work at very different scales. A story that covers many related goals is often called an _epic_, while an implementation task describes work to be done without expressing a user's goal.

For the course project, aim for a story that describes one focused user goal and is small enough for one team member to make meaningful progress on it. As a useful rule of thumb, each story should lead naturally to one use case in the program. This is a course-project heuristic rather than a rule followed by every software team.

| Scope | Example | Assessment |
| --- | --- | --- |
| Too broad | As a bank customer, I want an online banking platform so that I can manage all of my accounts. | This is an epic containing many goals, such as viewing an account, transferring money, and paying a bill. |
| Appropriate | As a bank customer, I want to view the transactions in my savings account so that I can review my spending. | This describes one user goal that can become a use case. |
| Too narrow | Create a table component that displays transactions. | This is an implementation task, not a user story. It does not say who benefits or why. |

The same distinction applies to an e-commerce application. "Manage products" is probably an epic; viewing products, updating a shopping cart, and updating a wish list are separate candidate stories. Creating a list of products or laying out a screen may be tasks needed to complete one of those stories.

Scope depends on the project and the team. If a story contains several distinct user goals, split it. If it only describes a class, algorithm, screen, or other implementation detail, identify the larger user goal that motivates the task.

For another introduction to story scope and acceptance criteria, see Atlassian's [User Stories with Examples and a Template](https://www.atlassian.com/agile/project-management/user-stories).

## 8.4. Use cases

> Note: we'll cover these ideas in more detail later, but it is useful to start thinking through the process now.

A _use case_ describes how an actor and a system interact to accomplish a goal. It adds behavioural detail to a user story: what starts the interaction, what normally happens, what can go wrong, and what outcome the system guarantees.

There is no single use-case format used by every software team. Some teams write detailed specifications, while others use a short description, acceptance criteria, or executable scenarios. The lightweight format below is practical for the course project because it makes the important decisions visible without requiring a large document.

### A lightweight use-case format

* **Name:** an active verb phrase that states the actor's goal, such as "Send a friend request."
* **Primary actor:** the external role that starts the use case in order to achieve a goal. The software system itself is not an actor. Other people or external systems involved may be listed as supporting actors.
* **Preconditions:** facts that must already be true when the use case begins. They are assumptions, not steps that the use case performs.
* **Trigger:** the event that starts the use case.
* **Success outcome:** the observable state of the system after the goal is achieved. A more detailed specification may also state what the system guarantees after failure.
* **Main success scenario:** a numbered sequence showing the usual successful interaction between the actor and the system.
* **Extensions:** alternative or failure paths, tied to the step at which they can occur.

Write the steps in terms of the actor's intent and the system's observable response. Include enough detail to remove important ambiguity, but avoid class names, method calls, database operations, and unnecessary interface details. Those are design and implementation decisions. For example, "the user asks to send a friend request" allows the interface designer to choose an appropriate button, menu item, or other control later.

Here is an example use case for the user story about sending a friend request:

**Use Case: Send a Friend Request**

**Primary actor:** User sending the request

**Preconditions:**

* The user is logged in.
* The user is viewing another user's profile.
* The two users are not already friends and there is no pending request between them.

**Trigger:** The user asks the system to send a friend request to the person whose profile is displayed.

**Success outcome:** The request is recorded as pending and the other user is notified.

**Main success scenario:**

1. The user asks to send a friend request.
2. The system checks that a request is currently allowed.
3. The system records the pending friend request.
4. The system shows the user that the request is pending.
5. The system notifies the other user of the request.

**Extensions:**

* **2a.** The other user no longer accepts friend requests from this user.
  1. The system explains that the request cannot be sent.
  2. The system does not create a request, and the use case ends.
* **3a.** The system cannot record the request.
  1. The system explains that the request was not sent and that the user may try again.
  2. The use case ends with no pending request.

Searching for a person and viewing their profile happen before this use case and could be use cases of their own. Similarly, the other user accepting or declining the request is a separate goal, **Respond to a Friend Request**, with a different primary actor. Keeping these goals separate makes each use case easier to understand, assign, implement, and test.

### From specification to design

A use case says what behaviour the system must provide, but it does not prescribe the entire user interface or the classes that implement it. The team can next:

* add acceptance criteria or tests for the success and extension paths;
* sketch the views before and after important interactions, including error states;
* identify the entities and data access operations involved; and
* design the Clean Architecture classes that realize the use case.

In Clean Architecture, the use case interactor implements the application-specific rules for the goal. The controller, presenter, view model, and view connect those rules to the user interface. Do not turn every click or screen transition into its own interactor: design around a coherent user goal.

Once the team chooses a scoped story to implement, the [feature development workflow](00-introduction-to-git.md#04-feature-development-workflow) gives one way to organize the implementation and review work.

## 8.5. Exercises

These are written design exercises: there is no code to run and no automated
test. Work them on paper (or in a `.puml` file) and, ideally, compare answers
with someone else — most of the value is in the disagreements.

### Question 1: Noun–verb analysis and a UML class diagram

Consider this specification:

> *A library lets members borrow items. Each item has a title and a unique
> catalogue number. Books can be borrowed for three weeks; DVDs can be borrowed
> for one week. Some items are reference-only and cannot be borrowed at all.
> Members log in with a member number and can place a hold on an item that is
> currently on loan. Librarians can add new items to the catalogue and can waive
> a member's late fee.*

1. Underline the nouns and list the candidate classes. For each noun you
   *reject*, say briefly why (attribute value, synonym, irrelevant, ...).
2. Circle the verb phrases and list the responsibilities. Assign each one to a
   class.
3. Decide where **inheritance** and where an **interface** is warranted. Two
   things worth thinking hard about: reference-only items versus borrowable ones
   (books and DVDs differ only in their loan period — is that a subclass, or a
   field?), and what `Member` and `Librarian` have in common.
4. Draw the resulting UML class diagram. See
   [§3.8 UML Class Diagrams](03-relationships-between-classes.md#38-uml-class-diagrams)
   for the notation.

You can draw your answer in text with **PlantUML**: a starter file is provided
at
[plantuml/exercises/library-uml-starter.puml](plantuml/exercises/library-uml-starter.puml)
(one box is filled in for you, with a syntax cheat-sheet in the comments). Open
it in IntelliJ with the **PlantUML Integration** (plantuml4idea) plugin
installed and a live preview renders beside the file as you type.

### Question 2: Scenario walk-through

Take the diagram you drew in Question 1 and walk through this scenario, step by
step, exactly as in §8.2:

> A member places a hold on an item that is currently on loan.

For each step, name the class responsible and the classes it must collaborate
with. Every time you find a step that no class can carry out, add the missing
responsibility (or the missing information) to your diagram and start the
scenario again. Keep going until it stabilizes.

Then answer: **which class should be responsible for knowing whether an item is
currently on loan?** Give at least two candidates and the trade-off between
them.

<details>
<summary>Hints</summary>

- The phrase "reference-only ... cannot be borrowed at all" is a hint that not
  every item supports the same operations. What does that suggest about where
  `borrow` should live?
- `Member` and `Librarian` are both people the library knows about, and both act
  on items. Is there shared state or behaviour worth pulling into a common
  supertype — and does the specification tell you everything you need to decide?
- A hold has to be *remembered* by something. Ask yourself which object still
  exists, and is easy to find, at the moment someone needs to know about the
  hold.
- These are open design questions. More than one answer is defensible; what
  matters is that you can say what your answer makes easy and what it makes
  awkward.

</details>

### Question 3: From a user story to a use case

Consider a campus study-room booking application.

> **User story:** As a student, I want to reserve an available study room for a particular time so that my group has a place to meet.

Assume the following requirements:

* The student is signed in before attempting to reserve a room.
* The student has already selected a room, date, and time.
* A room cannot have two reservations at the same time.
* After a successful reservation, the system displays a confirmation.
* A room might become unavailable after the student selects it but before the reservation is submitted.

Write a use case using the lightweight format from this section:

1. Choose a name and identify the primary actor.
2. State the preconditions, trigger, and success outcome.
3. Write a main success scenario in which the room is reserved.
4. Write an extension for the room becoming unavailable. Tie it to a particular step in the main scenario.
5. Identify one more plausible extension. State what the user observes and whether the system's state changes.

Review your draft with these questions:

* Does the use case pursue one user goal?
* Are preconditions stated as facts rather than hidden setup steps?
* Does each main-flow step describe an actor action or an observable system responsibility?
* Are alternative and failure paths separated from the successful path?
* Does the success outcome make the use case testable?
* Have you avoided committing prematurely to UI widgets, classes, methods, or database details?

As a scope check, compare the story above with "As a student, I want to manage all of my room bookings," which is likely an epic, and "Build the room-booking form," which is an implementation task. The exercise story sits between them: it expresses one useful, implementable user goal.
