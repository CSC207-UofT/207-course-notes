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

They all follow a simple format: "As a [kind of user], I want to [do a task] so that [I achieve some goal]."

User stories are often used to verify with the client that the feature set is aligned with their expectations.

Next, a user story will be handed to a UX designer who will decide how the feature should look and work. This of course needs to be integrated into the app, so there are sometimes a lot of decisions to make.

## 8.4. Use cases

> Note: we'll cover these ideas in more detail later, but it is useful to start thinking through the process now.

A _use case_ describes the sequence of user interactions necessary to accomplish a feature.

For each user story, someone on the team — could be a UX/UI designer, the programmers, maybe the manager — will write a use case that describes the sequence of interactions between the user and the system to accomplish the user story goal.

There may be several user interactions required to accomplish a feature. For example, to send a friend request, a user might need to search for a person, view their profile, and then click a "Send Friend Request" button. Each of these interactions can be broken down into smaller tasks or user stories if needed.

Here is an example use case for the user story about sending a friend request:

**Use Case: Sending a Friend Request**

**Actors**: User, System

**Preconditions**: User is logged into the social media app.

**Main Flow**:
1. User searches for a person using the search bar.
2. System displays a list of search results.
3. User selects a person from the search results.
4. System displays the selected person's profile.
5. User clicks the "Send Friend Request" button.
6. System sends a friend request to the selected person and notifies the user that the request has been sent.
7. System updates the user's friend list to show the pending request.
8. System sends a notification to the selected person about the friend request.
9. Selected person can choose to accept or decline the friend request.
10. If accepted, the system updates both users' friend lists to show they are now friends.
11. If declined, the system notifies the user that the request was declined.

Each of those steps is a single interaction. There may be other related features.
For example, a user might want to see a list of their pending friend requests, or cancel one they sent earlier.
Another example is the precondition about the user needing to be logged in prior to this use case being executed.
As you might imagine, another developer would need to go through this same process to implement a use case for
logging into the social media app.

Finally, a developer will implement the feature. For each user interaction, they will decide if it results in a user interface event that the program needs to respond to. If so, they will write a listener to handle that event.
The developer may need to create new classes to represent new kinds of information involved in the user story.

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
