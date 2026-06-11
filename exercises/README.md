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

