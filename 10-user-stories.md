# User stories

A _user story_ describes a feature from the perspective of the user, and focuses on the value that the feature provides.

Here are a couple user stories for a social media app:

* As a user, I want to be able to send friend requests so that I can connect with people I know.
* As a group administrator, I want to be able to hide posts that violate the code of conduct so that others can feel more comfortable participating.

They all follow a simple format: "As a [kind of user], I want to [do a task] so that [I achieve some goal]."

User stories are often used to verify with the client that the feature set is aligned with their expectations.

Next, a user story will be handed to a UX designer who will decide how the feature should look and work. This of course needs to be integrated into the app, so there are sometimes a lot of decisions to make.

[TODO: Create activity having them think about user stories, maybe for the TODO app.]

# Use cases

A use case describes the sequence of user interactions necessary to accomplish a feature.

For each user story, someone on the team — could be UX/UI designer, the programmers, maybe the manager — will write a _use case_ that describes the sequence of interactions between the user and the system to accomplish the user story goal.

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

Each of those steps is a single interaction. There may be other related features. For example, a user might want to see a list of their pending friend requests, or cancel one they sent earlier.

Finally, a developer will implement the feature. For each user interaction, they will decide if it results in a user interface event that the program needs to respond to. If so, they will write a listener to handle that event. The may need to create new classes to represent new kinds of information involved in the user story.

[TODO: Create activity having them think about the sequence of user interactions for the TODO app.]
