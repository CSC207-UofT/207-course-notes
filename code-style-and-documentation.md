# Code Style and Documentation

Once your code *works*, you are only halfway done. Code is read far more often
than it is written — by your teammates, by markers, and by future-you six weeks
from now who has forgotten everything. Two habits make that reading easier, and
this chapter covers both:

- **Consistent style**, enforced automatically with a tool called **CheckStyle**.
- **Documentation comments**, written in Java's **JavaDoc** format.

## "Clean as You Code"

Both Bob Martin ("Uncle Bob")  and SonarSource (the company behind the
SonarQube analyzer) promote a
["Clean as You Code" mindset](https://docs.sonarsource.com/sonarqube/latest/user-guide/clean-as-you-code/):
*leave the program cleaner than you found it, and your teammates and future-you
will thank you.* In short:

1. Make sure all of your **new** code is clean.
2. When you have to change old code (add a method, change a return type) and you
   notice a style or design issue nearby, fix it. Don't go hunting across the
   whole project; just tidy what's already in front of you.
3. Make sure your changes are covered by a test or two. If the old code had no
   tests, this is especially important.

The parts of a codebase that get touched most often become clean soonest, which
saves time on every future change. Doing this kind of clean-up in its own commit
(separate from feature work) is a good habit.

## Code style and CheckStyle

There is rarely one "correct" way to format code, but a *team* benefits
enormously from everyone formatting it the *same* way: differences in a code
review then reflect real changes, not spaces-vs-tabs noise. Every established
software company adopts a **style guide** and an automated tool to enforce it,
so it is worth getting comfortable with one now.

This course uses the **Google Java Style Guide**
(<https://google.github.io/styleguide/javaguide.html>), enforced by
[**CheckStyle**](https://checkstyle.org/). CheckStyle is a *static analysis*
tool: it reads your source files and reports anywhere they violate the
configured rules, without ever running your program.

### How CheckStyle runs in this repository

CheckStyle is wired into Maven in [code/pom.xml](code/pom.xml). The
`maven-checkstyle-plugin` is bound to the `validate` phase — the very first
phase of a build — using the Google Java Style rules in
[code/checkstyle/google_checks.xml](code/checkstyle/google_checks.xml). That file
is a copy of the `google_checks.xml` that ships with the pinned CheckStyle
version, included in the repo so the IDE can point at the *exact same file*
(see below):

```bash
mvn validate        # runs CheckStyle on code/src/main/java
mvn compile         # also runs validate first, so style is checked here too
```

Because `failsOnError` is set, a style violation **fails the build** before any
compilation happens. A violation looks like this in the console:

```
[ERROR] src/main/java/.../Example.java:12:1: Missing a Javadoc comment. [MissingJavadocType]
```

The format is `file:line:column: message [RuleName]`. The rule name in brackets
is what you'd search for to learn more.

The style is enforced on every file under `code/src/main/java`. If a specific
file ever needs an exception, you can list it in
[code/checkstyle/checkstyle-suppressions.xml](code/checkstyle/checkstyle-suppressions.xml),
but that should be rare — new code is expected to be clean.

### Setting up CheckStyle in IntelliJ

Running Maven tells you about violations *after the fact*. It is much nicer to
see them highlighted as you type, using the **CheckStyle-IDEA** plugin:

1. Install the plugin: **Settings → Plugins → Marketplace**, search
   *CheckStyle-IDEA*, install, and restart if prompted.
2. Configure it: **Settings → Tools → Checkstyle**. Click **+** and add the
   repo's own config file,
   [code/checkstyle/google_checks.xml](code/checkstyle/google_checks.xml) — the
   *same file the Maven build uses*. Give it a description like `CSC207 Checks`
   and tick it as active. Do **not** use the plugin's built-in *Google Checks*:
   that is the plugin's own bundled copy and can differ from our pinned version.
   While you're in this panel, set the *Checkstyle version* dropdown (at the top)
   to **10.26.1** — the version pinned in the root [pom.xml](pom.xml) — so the IDE
   runs the same engine as the build.
3. You'll now see style problems underlined in the editor. **Hover** over an
   underline (don't click) and a popup explains the problem — and often suggests
   a fix.
4. Open the **CheckStyle tool window** to scan whole files at once: look for the
   pencil icon on the left edge, or go to **View → Tool Windows → CheckStyle**,
   then run a scan on the current file or module.

> IntelliJ stores this configuration in `.idea/checkstyle-idea.xml`. In this
> repo the entire `.idea/` folder is git-ignored, so each person sets the plugin
> up once locally.

With the same config file and the same engine version as the build, the editor
flags exactly what `mvn validate` will.

### Common issues CheckStyle will flag

The exact rules come from the style guide, but these are the kinds of things you
will see most often. Hover over the warning to get the specifics for your code:

- **Missing JavaDoc** — public types and members should be documented (see the
  next section).
- **Magic numbers** — a bare literal like `86400` with no name gives the reader
  no context. Pull it into a named `static final` constant such as
  `SECONDS_PER_DAY`.
- **Missing braces** — even a one-line `if` or loop body should be wrapped in
  `{ }`.
- **Line too long** — break up lines that exceed the configured column limit.
- **Naming conventions** — classes `UpperCamelCase`, methods/variables
  `lowerCamelCase`, constants `UPPER_SNAKE_CASE`.
- **Modifier order** — declare modifiers in the canonical order, e.g.
  `public static final`, not `static public final`.
- **Indentation and whitespace** — consistent indentation and spacing around
  operators and braces.

A good workflow: fix one category of issue, then **re-run your tests** to make
sure a "tidy-up" edit didn't change behaviour, and commit.

### Reformatting your code

Most CheckStyle complaints are about pure formatting — indentation, spacing,
line wrapping. **Don't fix these by hand**; let an auto-formatter do it. All of
the options below apply the same [Google Java
Format](https://github.com/google/google-java-format) (GJF) style the build
enforces.

**Maven (no IDE needed).** The repo configures the Spotless plugin to run GJF.
It isn't bound to any build phase, so a normal build never triggers it — you run
it on demand:

```bash
mvn spotless:apply -pl code    # reformat every file in place
mvn spotless:check -pl code    # just check, don't modify (fails if not formatted)
```

This is the source of truth — its output matches what CheckStyle expects.

**IntelliJ.** Reformat the current file with **Code → Reformat Code**
(**Ctrl+Alt+L** / **⌥⌘L**). By default IntelliJ formats with 4-space indentation,
while Google style uses 2 — so the stock settings will *not* satisfy CheckStyle.
Install the **google-java-format** plugin (**Settings → Plugins → Marketplace**)
and enable it under **Settings → Other Settings → google-java-format**. The
plugin also needs some flags added to the IDE's own JVM: go to **Help → Edit
Custom VM Options…** and paste these in, then restart IntelliJ
([why](https://github.com/google/google-java-format/blob/master/README.md#intellij-jre-config)):

```
--add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED
--add-exports=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED
--add-exports=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED
--add-exports=jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED
--add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED
--add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED
```

A formatter handles spacing, indentation, and wrapping, but it does **not** do
everything CheckStyle wants: it won't add missing JavaDoc, won't add braces to a
brace-less `if`/`for`, and won't rename anything. So reformat first, then fix the
handful of remaining warnings by hand.

## Documentation with JavaDoc

Style is about *how* the code is written; documentation is about explaining
*what it does and why*. In Java, the standard way to document a class or method
is a **JavaDoc comment**: a block comment that starts with `/**` (two stars) and
sits immediately above the thing it describes. It is the Java equivalent of a
Python docstring, but with structured tags that tools understand.

```java
/**
 * Returns the sum of all integers stored at odd indices of {@code arr}
 * (index 1, 3, 5, ...). If there are no odd indices, returns 0.
 *
 * @param arr an array of integers
 * @return the sum of all integers at odd indices in arr
 */
public static int oddSum(int[] arr) {
    ...
}
```

### Anatomy of a JavaDoc comment

- It begins with `/**` and ends with `*/`; each line in between conventionally
  starts with a `*`.
- The **first sentence** is a short summary; tools use it as the one-line
  description, so make it count.
- After the description come **block tags**, each on its own line:

| Tag | Use it for |
|-----|-----------|
| `@param name` | one per parameter, describing what it is |
| `@return` | what the method returns (omit for `void`) |
| `@throws Type` (or `@exception`) | each exception the method may throw, and when |
| `@deprecated` | mark something that should no longer be used, and what to use instead |
| `@see` | point to a related class or method |

- Two **inline tags** are especially handy inside descriptions:
  `{@code x}` formats `x` as code (great for parameter names, literals, and
  types), and `{@link OtherClass#method}` creates a cross-reference link.

### Writing *good* JavaDoc

The goal is to help a reader understand the **purpose** of the code without
having to read the body — and without explaining how Java itself works.

- Describe the *contract*: what the method does, what each parameter means, what
  it returns, and any preconditions or edge cases (empty input, `null`, etc.).
- Don't just restate the signature. `@param arr the array` adds nothing; say
  what the array *represents* and any constraints on it.
- Document the *why* when it isn't obvious from the *what*.

The starter files for the exercises in these notes are written in this style —
see [OddSum.java](exercises/ex01-odd-sum/src/main/java/OddSum.java) for an
example you can imitate.

### Viewing and generating JavaDoc

- In IntelliJ, place the caret on any class or method and press **Ctrl+Q**
  (**F1** on macOS) for *Quick Documentation*, or just hover over a call to see
  the rendered JavaDoc. This works for the standard library too — try it on
  `String.split`.
- IntelliJ can scaffold a comment: type `/**` above a method and press Enter and
  it stubs out the `@param`/`@return` tags for you. See JetBrains' guide:
  <https://www.jetbrains.com/help/idea/working-with-code-documentation.html>.
- The `javadoc` command-line tool (and **Tools → Generate JavaDoc…** in
  IntelliJ) turns these comments into browsable HTML — the same format as the
  [official Java API docs](https://docs.oracle.com/en/java/javase/11/docs/api/).

## Exercise: clean it up

The [ex05-cleanup](exercises/ex05-cleanup/src/main/java/Rectangle.java) exercise
gives you a small class, `Rectangle`, that **works correctly but is written in a
messy style** — inconsistent spacing and indentation, missing braces, and
incomplete JavaDoc. Its tests already pass; your job is to fix the *style* without
breaking them.

1. Confirm the tests pass to start with:
   ```bash
   mvn -P exercises -pl exercises/ex05-cleanup test
   ```
2. Run CheckStyle to see the violations (there are quite a few):
   ```bash
   mvn -P exercises -pl exercises/ex05-cleanup checkstyle:check
   ```
   Or, in IntelliJ, open `Rectangle.java` and read the warnings inline / in the
   CheckStyle tool window, as described above.
3. Fix every violation — adjust the formatting and write proper JavaDoc
   (`@param`, `@return`) — until `checkstyle:check` reports **0 violations**.
4. Re-run the tests to confirm you didn't change the behaviour. Both commands
   should pass.

> Tip: IntelliJ's **Reformat Code** (Ctrl+Alt+L / ⌥⌘L) fixes most of the spacing
> and indentation issues instantly; the JavaDoc is the part that needs you to
> think about *what* each method and parameter means.

## Summary

- Adopt a **"Clean as You Code"** habit: keep new code clean and tidy old code as
  you touch it.
- This course enforces the **Google Java Style** with **CheckStyle**, which runs
  automatically during a Maven build and can highlight issues live in IntelliJ
  via the CheckStyle-IDEA plugin.
- Document your public classes and methods with **JavaDoc** (`/** ... */`,
  `@param`, `@return`, `@throws`), focusing on *what* the code does and *why*,
  not on how Java works.
