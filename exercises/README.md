# Exercises

Short, hands-on exercises that accompany the course notes. Each exercise lives
in its **own Maven module** so that:

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

| Exercise | Chapter | Topic |
|----------|---------|-------|
| [ex01-odd-sum](ex01-odd-sum/src/main/java/OddSum.java) | 1. Introduction to Java | arrays, for-loops |
| [ex02-first-letters](ex02-first-letters/src/main/java/FirstLetters.java) | 1. Introduction to Java | strings, `StringBuilder` |
| [ex03-alias-side-effects](ex03-alias-side-effects/src/main/java/Aliasing.java) | 1. Introduction to Java | references vs. primitives, side effects |
| [ex04-digit-sum](ex04-digit-sum/src/main/java/DigitSum.java) | 1. Introduction to Java | `while` loops, integer arithmetic |
| [ex05-cleanup](ex05-cleanup/src/main/java/Rectangle.java) | Code Style and Documentation | CheckStyle, JavaDoc |
| [ex06-overloading](ex06-overloading/src/main/java/MyHashing.java) | 2. Classes in Java | overloading, constructors, static methods |
| [ex07-equality](ex07-equality/src/main/java/Point.java) | 2. Classes in Java | `toString`, `equals`, `hashCode` |
| [ex08-comparable](ex08-comparable/src/main/java/Word.java) | 2. Classes in Java | `Comparable` / `compareTo` |
| [ex09-instance-counter](ex09-instance-counter/src/main/java/Robot.java) | 2. Classes in Java | static vs. instance fields |

> **Note:** `ex05-cleanup` works differently from the others. Its code is already
> correct (the tests pass from the start) but messy. The goal is to make
> `mvn -P exercises -pl exercises/ex05-cleanup checkstyle:check` report **0
> violations** while keeping the tests green. It is the one exercise module with
> CheckStyle turned on.

