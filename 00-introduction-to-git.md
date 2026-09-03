# Chapter 0: Working with Git in CSC207

Git is the version control system used in this course. It records the history of a project and lets team members develop changes independently before combining them. GitHub hosts a shared copy of a Git repository and adds collaboration tools such as issues, pull requests, and code review; Git and GitHub are related, but they are not the same thing.

## 0.1. Learn Git interactively

The [CSC207 Learn Git website](https://learngit.teach.cs.toronto.edu/) is the primary resource for learning Git commands and practising them in a guided environment. It covers:

* the basic workflow: cloning, inspecting changes, staging, committing, reading history, and publishing work;
* branching and merging;
* correcting mistakes;
* working safely with a team; and
* advanced tools for rewriting or moving work.

Use that website when you need to learn or review the mechanics of a Git operation. This chapter does not repeat its command-by-command lessons. Instead, it emphasizes the mental model and working practices that matter for the course project.

For these course notes specifically: **fork the repository on GitHub and clone your own fork**, then do your exercise work there. Do not treat the upstream `CSC207-UofT/207-course-notes` repo as the place you push to. Step-by-step setup is in the [Quickstart guide](QUICKSTART.md#1-fork-and-clone-the-repository).

## 0.2. A mental model for Git

It helps to distinguish the places in which a change can exist:

| Location | What it represents |
| --- | --- |
| Working tree | The files currently visible and editable on your computer. |
| Staging area | The changes selected for the next commit. |
| Local repository | The commits and branches stored on your computer. |
| Remote repository | A shared copy of the repository, commonly hosted on GitHub. |

A commit records a snapshot in your **local** repository. Pushing publishes local commits to a remote repository; it does not create the commit. Fetching or pulling obtains work published by other people. A branch identifies a line of development and allows several lines of work to proceed without changing the same shared branch directly.

The name `main` commonly refers to a repository's default branch. The name `origin` commonly refers to the remote from which a repository was cloned. A branch and a remote serve different purposes, and neither name is mandatory.

### Everyday commands at a glance

The following commands form the core vocabulary for most course-project work. The [Learn Git website](https://learngit.teach.cs.toronto.edu/) provides guided practice and explains their options in more detail.

| Command | Purpose |
| --- | --- |
| `git clone <url>` | Create a local repository from an existing remote repository. This is normally done once when beginning work on a project. |
| `git status` | Show the current branch and the state of files in the working tree and staging area. This is a good command to run whenever you are unsure what Git will do next. |
| `git pull` | Download changes from a remote branch and integrate them into the current local branch. |
| `git switch -c <branch-name>` | Create a branch and switch to it. Use a focused branch for a feature or bug fix. |
| `git switch <branch-name>` | Switch between existing local branches. |
| `git add <file>` | Stage a file's current changes for the next commit. Staging a file does not publish it. |
| `git commit -m "<message>"` | Record the staged changes as a commit in the local repository. |
| `git push` | Publish local commits to a remote repository. The first push of a new branch may require Git to be told which remote branch to use. |
| `git log` | Inspect commit history. |
| `git merge <branch-name>` | Integrate another local branch into the current branch. On the course project, changes are commonly merged into `main` through a reviewed pull request instead. |

A typical feature therefore moves through this sequence: update `main`, create a branch, edit and inspect files, stage the intended changes, commit them locally, push the branch, and open a pull request. You will repeat the inspect–stage–commit cycle as the feature develops; a feature does not need to fit into one large commit.

## 0.3. Course project expectations

Your team should agree on its exact conventions, but the following practices are good defaults for the course project:

* Track planned work with GitHub issues or an equivalent project-management tool.
* Develop each focused feature or bug fix on its own branch rather than committing directly to `main`.
* Make small commits whose messages explain one meaningful change.
* Inspect the staged changes before every commit. Do not commit generated build output, IDE-specific state, credentials, API keys, or other secrets.
* Use pull requests for review and discuss uncertain design decisions with teammates.
* Keep published history stable. In particular, do not force-push a shared branch or rewrite commits that teammates may already be using without coordinating with them.

These practices make changes easier to understand and review, and reduce the chance that one person's work will disrupt someone else's.

## 0.4. Feature development workflow

On a team project, a small, repeatable workflow makes it easier to coordinate work and review changes. The following is one suggested workflow rather than a rigid rule:

1. **Choose a focused feature or bug.** Start from a user story or issue that is small enough to complete without mixing several goals. Create an issue if one does not exist, and assign it to yourself so that teammates know the work is in progress.
2. **Update your local `main` branch and create a feature branch.** Give the branch a short, descriptive name such as `feature/upload-file` or `bugfix/save-likes`.
3. **Decide how you will verify the change.** For a new feature, write a test for its basic behaviour. For a bug, first write a test that reproduces the problem. Thinking about the test also helps clarify the interface and expected result.
4. **Implement the change in small steps.** Write clear method signatures and Javadoc before filling in complicated logic. Make focused commits with messages that describe one change; a message containing "and" may be a sign that the commit should be split.
5. **Test at several levels.** Run the focused tests, add important edge cases, and then run the full test suite. Also run the application and try the feature through its real user interface.
6. **Push the branch and open a [pull request](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/creating-a-pull-request).** Summarize what changed, explain how it was tested, mention any unresolved questions, and request reviews from teammates. Respond to feedback with additional commits on the same branch.
7. **Finish cleanly.** After the pull request is merged, update your local `main` branch, remove branches that are no longer needed, and check that no intended work remains uncommitted.

The details may vary by team, but the core idea is stable: take ownership of one focused piece of work, make its behaviour verifiable, develop it on a branch, and let teammates review it before merging. Keeping features small also reduces merge conflicts and makes defects easier to locate.

## 0.5. When something goes wrong

Git is designed to preserve work, and many mistakes are recoverable. Before trying to fix a problem:

1. Stop and inspect the current state. Determine whether the affected changes are uncommitted, staged, committed locally, or already pushed.
2. Read the relevant **Correcting Mistakes** or **Working with a Team** lesson on the [Learn Git website](https://learngit.teach.cs.toronto.edu/).
3. Preserve work you are unsure about before using a command that discards changes or rewrites history.
4. If the affected commits have been shared, coordinate with your team. A new commit that reverses an earlier change is usually safer than rewriting published history.

Do not reach for an "emergency override" simply because Git reports a conflict or rejects a push. Those messages usually indicate that Git is protecting work that must be reviewed or integrated.

## 0.6. More resources on Git

The interactive course website should be your starting point. These resources provide additional explanation and reference material:

- [CSC207 Learn Git](https://learngit.teach.cs.toronto.edu/)
- [BetterExplained: A visual guide to version control](https://betterexplained.com/articles/a-visual-guide-to-version-control/)
- [GitHub: Quickstart](https://docs.github.com/en/get-started/quickstart)
