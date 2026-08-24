# Developer Roles: A Course Roadmap

Building a real application involves several different kinds of work: designing
what the user sees, writing the logic that carries out each task, fetching and
storing data, and wiring everything together so it runs. On a software team, these
kinds of work are often divided into **responsibilities**.

In this course, we describe groups of related responsibilities as developer
**roles**. Think of them as hats that a developer can wear, not permanent job
titles or a recommendation that one teammate should own each part forever. The
names and boundaries of roles vary across software teams, and every member of a
course project team should understand these roles and will likely contribute to
several of them.

In these notes we deliberately learn a few of these roles **one at a time**. Once
you are comfortable with what each role does on its own, the architecture chapters
that follow will show how the roles fit together into a single, well-organized
program. That structure is much easier to appreciate — and to motivate — once you
have played each part yourself.

## The roles

| Role | Responsible for |
|------|-----------------|
| **Frontend Developer** | the user interface: windows, buttons, and reacting to user input |
| **Data Access Engineer** | getting data in and out: calling web APIs, reading and writing files, and saving/loading the program's data |
| **Use Case Engineer** | the application's logic: the steps that carry out each thing the program can do, and the interfaces those steps depend on |
| **Integrator** | assembling the pieces: creating the objects and connecting them so the finished program runs |

Some responsibilities also cut **across** the whole application rather than
belonging to a single layer:

- **QA / Test Engineer** — writes the automated tests that check each piece
  behaves correctly. You will practise this with JUnit throughout these notes.
- **Code Reviewer** — reads other people's code for correctness and style before
  it is merged (see [Code Style and Documentation](code-style-and-documentation.md)).
- **DevOps / Build Engineer** — keeps the build and automated checks running,
  such as the project's Maven build and its continuous-integration workflow.

## Where the roles appear in the course

This overview comes first so that you can see what the individual topics are
building toward. The roles become concrete as the course progresses:

| Course material | Main perspective |
| --- | --- |
| [Working with Git](00-introduction-to-git.md) and [Code Style and Documentation](code-style-and-documentation.md) | collaborating, reviewing code, and maintaining a healthy shared codebase |
| [Graphical User Interfaces](04-GUIs-with-swing.md) | building the view and responding to user input as a Frontend Developer |
| [APIs, JSON, and Files](apis-json-and-files.md) | bringing information into and out of the program as a Data Access Engineer |
| [Program Specification](08-program-specification.md) | identifying user goals and specifying the application logic implemented by a Use Case Engineer |
| [Layered Architectures](10-introduction-to-layered-architectures.md) and [Clean Architecture](11-clean-architecture.md) | connecting the roles while controlling their dependencies as an Integrator |

Testing is a cross-cutting responsibility and accompanies each of these topics
rather than appearing in only one place.

## Why learn the roles first

Each role can be studied and practised on its own: you can build a Swing window
without knowing where its data comes from, and you can call an API and save the
result without knowing what user interface will display it. Learning the roles in
isolation keeps each idea small and testable.

The interesting question — and the subject of the architecture chapters — is how
to *combine* the roles so that the parts stay loosely coupled and easy to change.
The **Clean Architecture** we build toward is essentially a disciplined answer to
"how should these roles depend on one another?" Keeping code for these
responsibilities separate is what lets one person change the user interface while
another changes how data is stored, without either breaking the other's work.
