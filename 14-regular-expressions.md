# Chapter 14: Regular Expressions

## Learning Objectives

- Understand the basic syntax of regular expressions (regex)
- Determine whether a given string _conforms_ to a given regex.
- Apply regular expressions in Java.

## 14.1. What is a regular expression?

A regular expression (also called “regex”) is a sequence of characters that specifies a pattern describing a set of strings.
We say that a regex _matches_ the set of strings that satisfy its pattern.
Conversely, a string _conforms_ to a regex if it satisfies the pattern described by that regex.

As an example, consider the regular expression `.*o`.
In this regex:
- the `.` matches any character,
- the `*` indicates that the pattern requires zero or more occurrences of the preceding element (in this case, the `.`), and
- the `o` matches the character `o`.

Together, the full regex matches any string that ends with the letter `o`, such as "hello", "ceo", and "o".

As this example demonstrates, regex give us a way to more concisely describe sets of strings that may be arbitrarily large!
Let's explore the syntax of regex so that we can understand and define our own patterns.

## 14.2. Some Useful Regex Symbols with Examples

We use `[` and `]` to define a choice of symbols. So `[abc]` will match "a", "b", or "c". 

By inserting a hyphen (`-`) we can specify a range. So `[a-z]` will match any lower case letter such as "a", "h", or "x". 

Other examples include `[0-9]` or `\d` which both match any digit such as "0", "2", or "7", and `[a-zA-z0-9]' which will match strings like "b", "R", and "2".

Postal codes in Toronto all start with `M` and then alternate between numbers and letters, with a space in the middle.
For example, "M0A 1B2" is a Toronto postal code.
To describe all Toronto postal codes, we can use the regular expression `M[0-9][A-Z] [0-9][A-Z][0-9]`.

## 14.3. Repeating Patterns with Quantifiers

**Quantifiers** specify how many times the preceding element in a regex must occur for the pattern to match.
These quantifiers are summarized in the table below:

| Quantifier | meaning                    | Example                                       |
|------------|----------------------------|-----------------------------------------------|
| `*`        | zero or more               | `a*` matches the empty string, `a`, `aa`, ... |
| `+`        | one or more                | `a+` matches `a`, `aa`, ...                   |
| `?`        | zero or one                | `a?b` matches `ab` or `b`                     |
| `{x}`      | `x` copies                 | `[ab]{2}` matches `aa`, `ab`, `ba`, or `bb`   |
| `{x,}`     | `x` or more copies         | `[a]{2,}` matches `aa`, `aaa`, ...            |
| `{x,y}`    | between `x` and `y` copies | `[a]{2,4}` matches `aa`, `aaa`, or `aaaa`     |

### 14.3.1. Character Classes

A **character class** is a set of characters, like `[a-z]`, that specifies which single character can occur.
Exactly one character from the set must occur.

> We can then apply quantifiers from the previous section to
specify repetitions and create more interesting patterns.

You can make your own character classes by using square brackets like
`[q-z]` and `[AEIOU]`, or you can use a predefined character class.

The following table lists some common character classes:

| character class | description                      |
|-----------------|----------------------------------|
| `.`             | any character (except a newline) |
| `\d`            | a digit `[0-9]`                  |
| `\D`            | a non-digit `[^0-9]`             |
| `\s`            | a whitespace character           |
| `\S`            | a non-whitespace character       |
| `\w`            | a word character `[a-zA-Z_0-9]`  |
| `\W`            | a non-word character             |

> Note that the definition of `\w` includes the `_` character.

### 14.3.2. Groups and Matching Strings with Repeated Characters
While quantifiers allow us to repeat patterns, we may also want to repeat the _exact same_ matching characters.
To do this, we need to specify a **group** with `(` and `)`.

For example, suppose that we want a regex that will match any phone number of the form `xxx-xxx-xxxx` that starts with the same first three digits,
such as "**222**-987-5406", "**000**-232-3232", "**111**-111-1114".
The following regex uses a group to accomplish this:

`([0-9])\1\1-[0-9]{3}-[0-9]{4}`

`\1` indicates that the characters from group `1` — in this case the character conforming to `[0-9]` — must be repeated.
The second `\1` indicates that it needs to be repeated once more, so that same character must appear three times in a row!

### 14.3.3. Multiple Groups

Each group is assigned a number based on the order in which its open bracket appears from left to right.
For this reason, the only group in the above example was group `1`, which we wrote as `\1` when we wanted to refer to it later in the regex.

For example, `(([de])f)\2\1` will repeat both groups. The strings that match are:
"dfddf" and "efeef".

- Group 1 corresponds to the `[de]f` part of the pattern

- Group 2 corresponds to the `[de]` part of the pattern

To visually make it easier to see the matches, we can write them space-delimited as:

`df d df` and `ef e ef` to clearly see the repetition of the groups.

## 14.4. Example: A Pattern for Java Instance Variable Names

IDEs like IntelliJ might describe the Java naming conventions for variables in this way:

```^[a-z][a-zA-Z0-9]*$```

In plain English, we might describe the purpose of this regex as:

“A string which conforms to this regex is consistent with the Java naming conventions for variables”

Let's look at the symbols in this regex to understand how it is expressing this idea!

The `^` character means that the pattern must start at the beginning of the string.
This is called an **anchor**.

In the leftmost set of brackets, we are given all lowercase letters to choose from,
so the first character must be one of these 26 possible letters.

The second character must come from the second set of square brackets: `[a-zA-Z0-9]*`.

> Recall that the `*` quantifier means zero or more occurrences of the preceding element.

Lastly, the `$` signifies the end of the string. This is another anchor.

Putting this all together, we have that a conforming string must
consist of a first character from `a-z`, followed by zero
or more lowercase letters, uppercase letters, or digits.
Reading this, we recognize that this is precisely how we define a valid variable name in Java!

With our regex in hand, we can try it out on a few examples:

Here are some examples of strings which conform:

"x",  "numStudents",  "obj1"

Here are some examples of strings which do not conform:

"Alphabet",  "2ab",  "next_value"

> Do any of these strings conform to our regex?
> "z3333", "aBcB041", "78a"


### 14.4.1. What if There Are No Anchors?
In the previous example, we introduced anchors:

- `^` matches the start of a string.
- `$` matches the end of a string.

Formally, as we have discussed, a regex defines a set of strings that fully match the pattern.
However, in practice, most practical regex implementations behave differently.
They often check whether any _substring_ of the input conforms to the pattern, unless anchors are used.

> In practice, when using regex in a programming language like Java, one must carefully read the
> documentation, as sometimes anchors are implicitly included (i.e., a method will automatically be looking for full matches).

Here is a regular expression which has no anchors.

```[abc]C[a-e][24680]*[A-Z]```

Because there are no anchors, a string will conform to this regex if _any_ substring contained within it conforms to the regex.

For example, the string "cCaA" conforms and so does "ABCcCcAa1A23",
since they both contain a substring, "cCcA", that is consistent with the regex.

A string like "cCa1A" does not conform though, since the "1" is inconsistent with the pattern.

## 14.5. Special symbols

Certain characters have special meaning in regex to allow us to develop more rich expressions.

For example, as we saw already, the period (`.`) matches any character.

From working with files and strings, you might recall that the
backslash (`\`) escape character can be combined with certain other characters to represent
things like a newline character (`\n`) or a tab character (`\t`). This is also the case in regex.
Another example of this are the built-in character classes, like `\s` and `\d`.

Just inside a square bracket, `^` has another meaning
besides being an anchor as we saw before: it matches any character _except_ the contents of the square brackets.

For example, `[^aeiouAEIOU]` matches anything that _isn’t_ a vowel.


### 14.5.1 Escaping the Meaning of a Symbol

What if we wanted to include a period in our conforming string?
To match strings like "1.2" or "3.8" we need some way to force a period to be included.
Since `.` matches any character, not just periods, we will have to *escape* the period by writing a backslash in front of it like this: `\.`.
By escaping the period, we indicate that its special meaning should be ignored, and `\.` matches precisely a literal "." character.

So the regular expression that matches all single digit numbers followed by a single digit after the decimal looks like this: `[0-9]\.[0-9]`

### 14.5.2. Logical operators

The `|` symbol means "or" and `&&` means the intersection of the range before the ampersands and
the range that appears after.

For example `[a-t&&r-z]` would match only the strings "r", "s", and "t".

> Note: different implementations of regex may support slightly different logical operators.

## 14.6. Regex in Java

In Java, you write regular expressions as strings, so you need to escape all of its backslashes. Instead of writing `\d` to match a single digit, you will have to write `"\\d"` in Java.
This also means that escaping a backslash, which normally would look like this `\\`, in Java will look like `"\\\\"`, because each backslash has to be escaped separately.

The `String` class contains methods such as `split`, `matches`, `replaceAll`, and `replaceFirst` which all make use of regex if
you read their documentation.

The [`Pattern`](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html) class is used to represent a regex.
It provides static methods `compile(String regex)` and `matches(String regex, String string)` which create a Pattern object
that represents the given regex string and matches a given regex string to a given string, respectively.
We encourage you to read the documentation linked above for this class, as it gives a nice summary of regex syntax in Java.

For example, the documentation for `Pattern` provides the following basic usage example:

```java
Pattern p = Pattern.compile("a*b");
Matcher m = p.matcher("aaaaab");
boolean b = m.matches();
```

The documentation highlights that `matches` provides a more convenient equivalent way to do the above:

```java
boolean b = Pattern.matches("a*b", "aaaaab");
```

The [`Matcher`](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Matcher.html) class facilitates
various operations related to actually checking for matches and then querying details of the result. For example,
one can use the `group(int group)` method to access a group from the previous match operation. See the documentation
for additional details.

We also provide [`Demo.java`](code/src/main/java/regex/Demo.java) which contains additional examples.

## 14.7. What Can We Do with Regular Expressions?

As we have seen, regex provides concise and expressive ways to describe patterns in text.
They allow us to perform various tasks, such as:

- Find data in documents
  - Examples: phone numbers in a file, email addresses on a webpage, CSC course codes in text, or performing “Find and Replace” in IntelliJ.

- Validate input
  - Examples: checking if a variable name follows Java naming conventions or if a password meets complexity requirements.

Broadly, these tasks fall into two categories:
- Extraction: locating substrings that conform to a pattern specified by a regex.
- Validation: determining whether an entire string conforms to a pattern.

Regex is widely supported across programming languages and tools, making it an essential skill for developers.

## 14.8. Exercises

Practise writing patterns in the `ex18-regex` module under the
[exercises](exercises/README.md) folder. Both tasks start red and turn green when
your patterns are correct. They line up with the two categories from §14.7:
validation and extraction.

- **Exercise 18a — Validators** (validation, `String.matches`). Complete the
  three methods in
  [Validators.java](exercises/ex18-regex/src/main/java/Validators.java) so they
  recognise valid emails, phone numbers of the form `NNN-NNN-NNNN`, and legal
  Java variable names, then run
  [ValidatorsTest.java](exercises/ex18-regex/src/test/java/ValidatorsTest.java).
- **Exercise 18b — Extractor** (extraction, `Pattern` and `Matcher`). Whole-string
  matching isn't enough when you want to *pull* pieces out of a larger body of text, so
  this task uses the classes from §14.6. In
  [Extractor.java](exercises/ex18-regex/src/main/java/Extractor.java), implement
  `findCourseCodes` (find every course code in a passage of text using
  `matcher.find()`), `findCourseNumbers` (pull just the digits out using a
  **capturing group**), and `maskEmails` (replace every email address with `***`).
  Then run
  [ExtractorTest.java](exercises/ex18-regex/src/test/java/ExtractorTest.java).
  Notice that the patterns are compiled once into `static final Pattern`
  constants and reused — compiling a regex is real work, so you don't want to redo
  it on every call.
