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

## 8.1. User stories

A _user story_ describes a feature from the perspective of the user, and focuses on the value that the feature provides.

Here are a couple user stories for a social media app:

* As a user, I want to be able to send friend requests so that I can connect with people I know.
* As a group administrator, I want to be able to hide posts that violate the code of conduct so that others can feel more comfortable participating.

They all follow a simple format: "As a [kind of user], I want to [do a task] so that [I achieve some goal]."

User stories are often used to verify with the client that the feature set is aligned with their expectations.

Next, a user story will be handed to a UX designer who will decide how the feature should look and work. This of course needs to be integrated into the app, so there are sometimes a lot of decisions to make.

## 8.2. Use cases

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
