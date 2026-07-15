# Developer Roles

Building a real application involves several different kinds of work: designing
what the user sees, writing the logic that carries out each task, fetching and
storing data, and wiring everything together so it runs. On a software team, these
kinds of work are often divided into **roles**, each responsible for one part of
the system.

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
  behaves correctly. You have been doing this with JUnit throughout these notes.
- **Code Reviewer** — reads other people's code for correctness and style before
  it is merged (see [Code Style and Documentation](code-style-and-documentation.md)).
- **DevOps / Build Engineer** — keeps the build and automated checks running,
  such as the project's Maven build and its continuous-integration workflow.

## Why learn the roles first

Each role can be studied and practised on its own: you can build a Swing window
without knowing where its data comes from, and you can call an API and save the
result without knowing what user interface will display it. Learning the roles in
isolation keeps each idea small and testable.

The interesting question — and the subject of the architecture chapters — is how
to *combine* the roles so that the parts stay loosely coupled and easy to change.
The **Clean Architecture** we build toward is essentially a disciplined answer to
"how should these roles depend on one another?" Keeping each role's code separate
is what lets one person change the user interface while another changes how data
is stored, without either breaking the other's work.

> **Looking ahead.** This idea of decomposing a system into specialised roles that
> plan and hand work to one another is not unique to human teams — it is also how
> modern *agentic AI* systems are increasingly organised, with separate agents for
> planning, coding, testing, and review.
