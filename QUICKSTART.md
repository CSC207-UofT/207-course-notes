# Quickstart: Setting Up and Running the Course Code

This guide walks you through **forking** the repository (required), cloning *your* fork,
understanding the project structure, and running Java code.

Students must work on their own fork so they can commit and push exercise solutions for course credit.

---

## Prerequisites

| Tool | Version | Check Command |
|------|---------|---------------|
| **Git** | Any recent | `git --version` |
| **JDK** | 11 or later | `java -version` and `javac -version` |
| **Maven**| 3.9 or later | `mvn -version` |
| **IntelliJ IDEA** | Any recent | *Optional but highly recommended* |


### Installation Guide by Platform

#### Windows

1. Open **PowerShell** or **Command Prompt** as an Administrator and run the following commands:
```cmd
:: Install Git
winget install --id Git.Git -e --source winget

:: Install OpenJDK 11 (Eclipse Temurin)
winget install --id EclipseAdoptium.Temurin.11.JDK -e --source winget
```

2. Install Apache Maven: Because Maven is not natively supported by WinGet, download the Binary zip archive directly from [maven.apache.org](https://maven.apache.org/download.cgi).

3. Extract the downloaded folder to a permanent location (e.g., `C:\Program Files\maven\`).

4. Search your Windows Start Menu for "Environment Variables", click Environment Variables, find Path under "System Variables",
   click edit, and add the path to your Maven bin folder (e.g., `C:\Program Files\maven\bin`).

#### Windows Subsystem for Linux (WSL)

If you prefer developing in a Linux environment on Windows, you can use WSL.

1. Open **PowerShell** as an Administrator and ensure WSL is installed (if not, this installs Ubuntu by default):
```powershell
wsl --install
```

2. Restart your computer if prompted, and complete the Ubuntu username/password setup.

3. Open your WSL/Ubuntu Terminal and run the standard Ubuntu setup commands:

```bash
sudo apt update
sudo apt install git openjdk-11-jdk maven
```

#### macOS

Open your terminal and run:

```bash
# Install Git (if not already installed via Xcode Command Line Tools)
brew install git

# Install OpenJDK 11 (Eclipse Temurin)
brew install --cask temurin@11

# Install Apache Maven
brew install maven    
```

#### Ubuntu / Debian

Open your terminal and run:

```
sudo apt update
sudo apt install git openjdk-11-jdk maven
```

#### Installing IntelliJ IDEA
- All Platforms: [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) is free and sufficient for this course
- UofT Students: University of Toronto students can get access to premium JetBrains products via [JetBrains for Students](https://www.jetbrains.com/community/education/)

---

## 1. Fork and Clone the Repository

**Work on your own fork.** Do not clone `CSC207-UofT/207-course-notes` as your day-to-day remote — you cannot push your work there.
Fork first, then clone the fork under *your* GitHub account.

1. Open [https://github.com/CSC207-UofT/207-course-notes](https://github.com/CSC207-UofT/207-course-notes) while signed in to GitHub.
2. Click **Fork** (top right) and create a fork under your account.
3. On your fork’s GitHub page, click **Code** and copy the HTTPS URL (it should look like `https://github.com/YOUR-USERNAME/207-course-notes.git`).

### Clone with IntelliJ (recommended)

Cloning through IntelliJ is the easiest way to get started.

- **Welcome screen** (no project open): click **Clone Repository**, paste your fork URL, choose a directory, and click **Clone**.
- **Already have a project open**: **File → New → Project from Version Control…**, paste the same URL, choose a directory, and click **Clone**.

IntelliJ will then offer to open the project; accept that and continue with [Section 5](#5-opening-in-intellij-idea) for Maven setup.

### Or clone from the terminal

```bash
git clone https://github.com/YOUR-USERNAME/207-course-notes.git
cd 207-course-notes
```

Then open that folder in IntelliJ as described in [Section 5](#5-opening-in-intellij-idea).

Optional but useful: add the course repo as an `upstream` remote so you can pull updates later:

```bash
git remote add upstream https://github.com/CSC207-UofT/207-course-notes.git
git remote -v   # origin → your fork; upstream → CSC207-UofT
```

---

## 2. Project Structure

```
207-course-notes/
├── QUICKSTART.md          ← you are here
├── pom.xml                ← root Maven configuration (parent)
├── code/                  ← the Java source module
│   ├── pom.xml            ← module Maven configuration
│   └── src/
│       ├── main/
│       │   └── java/      ← production source files (.java)
│       │       └── cs/toronto/edu/csc207/hello/
│       │           └── HelloWorld.java
│       └── test/
│           └── java/      ← test source files
│               └── cs/toronto/edu/csc207/hello/
│                   └── HelloWorldTest.java
└── *.md                   ← chapter notes
```

Maven enforces this standard layout so every Maven project looks the same.

The `code/target/` directory (Maven build output) and `.idea/` (IntelliJ config) are excluded
from Git via `.gitignore` — see [Section 7](#7-whats-in-gitignore) for details.

---

## 3. Java Basics: Compiling and Running by Hand

Before using a build tool it helps to understand what's happening under the hood.

### Writing a Hello World

Suppose we created a file called `HelloWorld.java` which contained:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

### Compiling with `javac`

`javac` is the Java compiler. It reads `.java` source files and produces `.class` bytecode files that the JVM can execute.

For the above `HelloWorld.java`, we could compile it as:

```bash
javac HelloWorld.java     # produces HelloWorld.class
```

### Running with `java`

`java` launches the JVM and runs the bytecode.

For the above example, we could then run our program with:

```bash
java HelloWorld           # prints: Hello, World!
```

### Packages and directory structure

In a real project, classes live in **packages**. Java requires that the source file's location matches the package name,
and that you compile and run from the **root of the source tree**, not from inside the package folder.

For example, `HelloWorld.java` in this repo declares `package cs.toronto.edu.csc207.hello;`. To compile and run it by hand:

```bash
# Run these from code/src/main/java/
javac cs/toronto/edu/csc207/hello/HelloWorld.java
java cs.toronto.edu.csc207.hello.HelloWorld
```

Running `java HelloWorld` from inside the package folder fails because the JVM looks for a class named `HelloWorld` but
the bytecode declares itself as `cs.toronto.edu.csc207.hello.HelloWorld`.

Managing classpaths and directories by hand gets tedious quickly — that's why we use Maven.

---

## 4. Using Maven

Maven automates compilation, dependency management, testing, and packaging.
Run all commands from the repository root:

```bash
cd 207-course-notes   # the folder containing the top-level pom.xml
```

### Compile

```bash
mvn compile
```

Compiles all `.java` files under `code/src/main/java/` into `code/target/classes/`.

### Run tests

```bash
mvn test
```

Compiles test sources from `code/src/test/java/` and runs all JUnit 5 tests.

### Package into a JAR

```bash
mvn package
```

Produces `code/target/code-1.0-SNAPSHOT.jar` containing all compiled classes.

### Run a class from the JAR

```bash
java -cp code/target/code-1.0-SNAPSHOT.jar cs.toronto.edu.csc207.hello.HelloWorld
```

### Clean build artifacts

```bash
mvn clean
```

Deletes all `target/` directories. Combine for a clean build: `mvn clean package`.

### Compile only a single submodule

```bash
mvn compile -pl code
```

### Profiles

A **profile** is a named bundle of Maven configuration that is *off by default* and only applied when you ask for it.
Profiles let one `pom.xml` describe more than one kind of build — for example "the normal build" versus "the normal build plus the exercises."

This repo uses a profile called `exercises`. The default build only compiles the `code` module:

```bash
mvn compile          # builds 'code' only — exercises are NOT touched
```

Activate the `exercises` profile with `-P` to additionally build and test the hands-on exercises under the [exercises](exercises/README.md) folder:

```bash
mvn -P exercises test               # build + test code AND all exercises
mvn -P exercises test -pl exercises/ex01-odd-sum   # just one exercise
```

We keep the exercises behind a profile on purpose: a starter exercise may contain intentionally-incomplete code that does not compile yet,
and we don't want that to break the default build.
The profile is declared in the root [pom.xml](pom.xml):

```xml
<profiles>
    <profile>
        <id>exercises</id>
        <modules>
            <module>exercises</module>
        </modules>
    </profile>
</profiles>
```

In IntelliJ, you don't use `-P` — open the **Maven** tool window, expand **Profiles**, and tick **`exercises`**,
then click **Reload All Maven Projects**.
The exercise modules then appear in the project, and you can press the green ▶ on any of them.
See [exercises/README.md](exercises/README.md) for more.

---

## 5. Opening in IntelliJ IDEA

If you cloned with IntelliJ in [Section 1](#1-fork-and-clone-the-repository), the project should already be open — skip to step 3. Otherwise:

1. Launch IntelliJ and choose **File → Open**
2. Select the root `207-course-notes/` folder (the one containing `pom.xml`)
3. IntelliJ detects Maven and shows a **"Load Maven Project"** notification in the bottom-right — click it
4. Wait for indexing to complete (progress bar in the bottom right)

### Running a class with a `main` method

- Open any `.java` file that has a `main` method (e.g., [HelloWorld.java](code/src/main/java/cs/toronto/edu/csc207/hello/HelloWorld.java))
- Click the green **▶** icon in the gutter next to `main`, or right-click → **Run**

### Running tests

- Right-click the `src/test/java` folder → **Run 'All Tests'**
- Or click the **▶** gutter icon next to any individual `@Test` method

### Troubleshooting: project not recognised as Maven

If IntelliJ doesn't pick up the Maven structure automatically:
- Open **View → Tool Windows → Maven**
- Click **+** and point it at the root `pom.xml`
- Click the **Reload All Maven Projects** button (circular arrows)

---

## 6. Test Folder Structure

Tests live in `code/src/test/java/` and mirror the package structure of the production code they test:

```
src/
├── main/java/cs/toronto/edu/csc207/hello/HelloWorld.java     ← production code
└── test/java/cs/toronto/edu/csc207/hello/HelloWorldTest.java ← tests for HelloWorld
```

This project uses **JUnit 5** (the Jupiter API). A minimal test class looks like:

```java
package cs.toronto.edu.csc207.hello;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloWorldTest {

    @Test
    void testGreeting() {
        String expected = "Hello, World!";
        assertEquals(expected, "Hello, World!");
    }
}
```

Key points:
- Test classes and methods are package-private (no `public`) — JUnit 5 doesn't require it
- Each test method is annotated with `@Test`
- Use `assertEquals`, `assertTrue`, `assertThrows`, etc. from `org.junit.jupiter.api.Assertions`
- Run all tests: `mvn test`

---

## 7. What's in `.gitignore`?

The root `.gitignore` excludes files that are either generated on demand or machine-specific:

| Pattern | Why excluded |
|---------|-------------|
| `target/` | Maven build output — rebuilt by `mvn compile` |
| `.idea/` | IntelliJ project config — regenerated on import |
| `*.iml`, `*.ipr`, `*.iws` | IntelliJ module/project files — regenerated |
| `out/` | IntelliJ output directory |
| `*.class` | Compiled bytecode — rebuilt by `mvn compile` or `javac` |
| `*.jar`, `*.war`, `*.ear` | Packaged artifacts — rebuilt by `mvn package` |
| `*.log` | Log files |
| `.DS_Store` | macOS filesystem metadata |
| `hs_err_pid*`, `replay_pid*` | JVM crash logs |

Never commit build artifacts or IDE-generated files.
Anyone cloning the repo can regenerate them by running `mvn compile` or opening the project in IntelliJ.
