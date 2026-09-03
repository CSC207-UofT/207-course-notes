# Exercises

Short, hands-on exercises that accompany the course notes. Complete them on
**your own fork** of this repository (see [QUICKSTART.md](../QUICKSTART.md#1-fork-and-clone-the-repository))
so you can commit and push your solutions.

Each exercise lives in its **own Maven module** so that:

- a starter with intentionally-incomplete code only breaks *its own* module —
  you can still press the green ▶ on any other exercise in IntelliJ; and
- `mvn compile` at the repo root stays green, because this `exercises/`
  aggregator is only built under the **`exercises` Maven profile** (it is *not*
  part of the default build — see [Profiles in QUICKSTART.md](../QUICKSTART.md#profiles)
  for what that means). There is also no checkstyle here, so `TODO` code won't
  trip the linter.

## Working an exercise in IntelliJ (recommended)

1. Open the repo root in IntelliJ (it auto-imports as a Maven project).
2. In the **Maven** tool window, tick the **`exercises`** profile (under
   *Profiles*) and click **Reload All Maven Projects** so the exercise modules
   appear.
3. Open the exercise's `.java` file and press the green ▶ next to `main` to run
   it, or open the matching `...Test.java` and press ▶ to run the tests. Tests
   start red and turn green as you complete the task.

## Running from the command line

```bash
# all exercises
mvn -P exercises test

# a single exercise
mvn -P exercises test -pl exercises/ex01-odd-sum
```

## Available exercises

Exercises are numbered in the order the chapters appear in the
[table of contents](../README.md). Most are checked by JUnit tests you run
yourself; a few are design exercises with no automated test — the ones with
starter files are listed here too. Chapters also contain smaller written
exercises inline.

| Exercise | Chapter | Topic | Checked by |
|----------|---------|-------|------------|
| [ex01-odd-sum](ex01-odd-sum/src/main/java/OddSum.java) | 1. Introduction to Java | arrays, for-loops | tests |
| [ex02-first-letters](ex02-first-letters/src/main/java/FirstLetters.java) | 1. Introduction to Java | strings, `StringBuilder` | tests |
| [ex03-alias-side-effects](ex03-alias-side-effects/src/main/java/Aliasing.java) | 1. Introduction to Java | references vs. primitives, side effects | tests |
| [ex04-digit-sum](ex04-digit-sum/src/main/java/DigitSum.java) | 1. Introduction to Java | `while` loops, integer arithmetic | tests |
| [ex05-cleanup](ex05-cleanup/src/main/java/Rectangle.java) | Code Style and Documentation | CheckStyle, JavaDoc | CheckStyle (see note below) |
| [ex06-overloading](ex06-overloading/src/main/java/MyHashing.java) | 2. Classes in Java | overloading, constructors, static methods | tests |
| [ex07-equality](ex07-equality/src/main/java/Point.java) | 2. Classes in Java | `toString`, `equals`, `hashCode` | tests |
| [ex08-comparable](ex08-comparable/src/main/java/Word.java) | 2. Classes in Java | `Comparable` / `compareTo` | tests |
| [ex09-instance-counter](ex09-instance-counter/src/main/java/Robot.java) | 2. Classes in Java | static vs. instance fields | tests |
| [UML ↔ Java questions](../03-relationships-between-classes.md#exercises)<br>(starter: [vehicles-uml-starter.puml](../plantuml/exercises/vehicles-uml-starter.puml)) | 3. Relationships between Classes | reading and writing UML class diagrams | self-check (answers in the notes) |
| [ex10-swing](ex10-swing/src/main/java/CounterPanel.java) | 4. GUIs with Swing | button clicks (`ActionListener`) | tests |
| [ex10-swing](ex10-swing/src/main/java/RegistrationForm.java) | 4. GUIs with Swing | matching a layout | by eye (see §4.4; why in §4.5) |
| [ex11-number-triangle](ex11-number-triangle/src/main/java/NumberTriangle.java) | APIs, JSON, and Files | reading a file into objects | tests |
| [ex12-gotchas](ex12-gotchas/src/main/java/Gotchas.java) | 5. Java Gotchas and Subtleties | shadowing, array copy, autoboxing | tests |
| [ex13-generics](ex13-generics/src/main/java/Box.java) | 6. Generics | custom generic class, bounded type parameter | tests |
| [ex14-iterator](ex14-iterator/src/main/java/Week.java) | 7. Collections | `Iterable` / `Iterator` | tests |
| [Noun–verb analysis + walk-through](../08-program-specification.md#85-exercises)<br>(starter: [library-uml-starter.puml](../plantuml/exercises/library-uml-starter.puml)) | 8. Program Specification | specification → classes → UML; scenario walk-through | self-check (hints in the notes) |
| [ex15-dependency-inversion](ex15-dependency-inversion/src/main/java/Manager.java) | 9. Design Principles | Dependency Inversion Principle | tests |
| [ex16-strategy](ex16-strategy/src/main/java/Navigator.java) | 12. Design Patterns | Strategy pattern | tests |
| [ex17-refactoring](ex17-refactoring/src/main/java/Temperature.java) | 13. Refactoring Techniques | replace constructor with factory method | tests |
| [ex17-refactoring](ex17-refactoring/src/main/java/OrderSummary.java) | 13. Refactoring Techniques | extract method, split loop, slide statements | tests (already green — keep them that way) |
| [ex18-regex](ex18-regex/src/main/java/Validators.java) | 14. Regular Expressions | validation with `String.matches` | tests |
| [ex18-regex](ex18-regex/src/main/java/Extractor.java) | 14. Regular Expressions | extraction with `Pattern` / `Matcher` | tests |

> **Note:** `ex05-cleanup` works differently from the others. Its code is already
> correct (the tests pass from the start) but messy. The goal is to make
> `mvn -P exercises -pl exercises/ex05-cleanup checkstyle:check` report **0
> violations** while keeping the tests green. It is the one exercise module with
> CheckStyle turned on. The `OrderSummary` refactoring task works the same way:
> the tests start green and must stay green.
