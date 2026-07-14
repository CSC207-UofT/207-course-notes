# APIs, JSON, and Files

So far our programs have been self-contained: everything they need is created in
the code itself. Real applications are rarely like that. They **fetch data from
the internet**, **read and write files on disk**, and **save their own data so it
survives between runs**. This chapter introduces the three skills that make that
possible:

- calling a **web API** to get data from another program over the internet,
- reading and writing **JSON**, the most common format for exchanging that data,
  and
- reading and writing **files** so your program can load and save data locally.

Together these are the "getting data in and out" side of an application — the
part that connects your code to the outside world. (Later, when we study
architecture, we'll see that it is good practice to keep this code in its own
layer, separate from your user interface and your core logic.)

## Calling a web API

An **API** (Application Programming Interface) is a way for one program to offer
services to another. A **web API** offers those services over the internet: you
send it an HTTP **request**, and it sends back a **response**. This is the same
thing your web browser does when it loads a page — except that a web API usually
returns *data* (often JSON) rather than a web page for a human to look at.

The most common kind of request is a **GET** request, which asks for some data
identified by a URL. For example, a weather API might let you GET
`https://api.example.com/weather?city=Toronto` and respond with the current
conditions. If you have used Python's `requests` library, this is the same idea.

### Making a GET request in Java

The labs use the **[OkHttp](https://square.github.io/okhttp/)** library to make
HTTP requests. (Java 11+ also ships a built-in `java.net.http.HttpClient`; the
concepts are identical.) A basic GET looks like this:

```java
OkHttpClient client = new OkHttpClient();

Request request = new Request.Builder()
        .url("https://api.example.com/weather?city=Toronto")
        .build();

try (Response response = client.newCall(request).execute()) {
    if (response.isSuccessful()) {
        String body = response.body().string();  // the response as text
        System.out.println(body);
    } else {
        System.out.println("Request failed with code " + response.code());
    }
} catch (IOException e) {
    // A network problem (no connection, timeout, ...) — handle it gracefully.
    e.printStackTrace();
}
```

A few things to notice:

- The response comes back as **text**. For most APIs that text is JSON, which
  we'll parse in the next section.
- Every response has a **status code**. `200` means success; `404` means "not
  found"; `401`/`403` mean you aren't authorised; `429` means you've been sending
  too many requests. Always check whether the request succeeded before using the
  body.
- Network calls can **fail** for reasons outside your control, so they throw a
  checked `IOException` you must handle.

### API keys and authentication

Many APIs require you to identify yourself with an **API key** or **token** that
you obtain by signing up. You usually send it as a request header:

```java
Request request = new Request.Builder()
        .url("https://api.example.com/data")
        .addHeader("Authorization", "Bearer " + apiToken)
        .build();
```

> **Keep secrets out of your source code.** Don't paste a real key into a file you
> commit to Git — anyone who can see the repository could then use (or abuse) your
> key. Read it from an environment variable or a local configuration file that is
> listed in `.gitignore`.

### Rate limits — be a good citizen

APIs protect themselves by imposing **rate limits**: a maximum number of requests
in some time window (say, 60 per minute). Go over the limit and you'll start
getting `429 Too Many Requests` responses instead of data. When you're testing
code that calls an API, it is easy to hit these limits by accident — for example,
by calling the API inside a loop or re-running a program many times.

To avoid trouble: request only what you need, cache results you'll reuse, and,
where possible, develop against a **saved copy of a response** in a local file
(see below) rather than hitting the live API on every run.

## JSON

**JSON** (JavaScript Object Notation) is a simple, text-based format for
representing structured data. It is what most web APIs use for their responses,
and it's a convenient format for saving your own data to a file. If you know
Python dictionaries and lists, JSON will look very familiar.

A JSON value is one of: a **string**, a **number**, `true`/`false`, `null`, an
**array** (an ordered list, in `[ ]`), or an **object** (a set of key–value
pairs, in `{ }`). Objects and arrays can nest:

```json
{
  "city": "Toronto",
  "temperature": 21.5,
  "conditions": ["cloudy", "windy"],
  "forecast": {
    "high": 24,
    "low": 15
  }
}
```

### Parsing JSON in Java

Java doesn't understand JSON on its own; you use a library. The course uses
**[org.json](https://github.com/stleary/JSON-java)**, whose two main classes are
`JSONObject` (for `{ }`) and `JSONArray` (for `[ ]`). You build a `JSONObject`
from the response text and then pull values out by key:

```java
JSONObject weather = new JSONObject(body);

String city = weather.getString("city");
double temperature = weather.getDouble("temperature");

JSONArray conditions = weather.getJSONArray("conditions");
for (int i = 0; i < conditions.length(); i++) {
    System.out.println(conditions.getString(i));
}

// Objects can be nested, so getJSONObject returns another JSONObject:
int high = weather.getJSONObject("forecast").getInt("high");
```

> The `getX` methods throw an exception if the key is missing or the value has the
> wrong type. If a field might be absent, the matching `optX` methods (e.g.
> `optString("city", "unknown")`) let you supply a default instead.

### Building JSON

You can also create JSON to send in a request or to save to a file. Building a
`JSONObject` is like filling in a map:

```java
JSONObject person = new JSONObject();
person.put("name", "Ada");
person.put("age", 36);

String text = person.toString();      // {"name":"Ada","age":36}
String pretty = person.toString(2);   // the same, indented by 2 spaces
```

## Reading and writing files

Reading from and writing to files lets your program use data that isn't baked
into the code, and lets it save results for next time. The `java.nio.file`
classes `Files` and `Path` make the common cases short:

```java
import java.nio.file.Files;
import java.nio.file.Path;

// Read an entire file into a String:
String text = Files.readString(Path.of("data.json"));

// Read a file as a list of lines:
List<String> lines = Files.readAllLines(Path.of("names.txt"));

// Write a String to a file (creating or overwriting it):
Files.writeString(Path.of("output.txt"), "Hello, file!");
```

Like network calls, file operations can fail (a missing file, no permission, a
full disk), so these methods throw `IOException`, which you must handle or
declare.

> **Where is the file?** A relative path like `Path.of("data.json")` is resolved
> against the program's **working directory** — usually the directory you ran the
> program from (the project root, in IntelliJ). If your program can't find a file,
> a wrong working directory is a common cause.

## Saving and loading your data

Putting the last two sections together gives us a way to make a program's data
**persist** between runs: convert your objects to JSON and write that to a file
to *save*; read the file and parse the JSON to *load*.

Suppose we have a simple entity:

```java
public class Task {
    private final String title;
    private final boolean done;
    // constructor + getters omitted
}
```

To **save** a list of tasks, turn each one into a `JSONObject`, collect them in a
`JSONArray`, and write it out:

```java
JSONArray array = new JSONArray();
for (Task task : tasks) {
    JSONObject obj = new JSONObject();
    obj.put("title", task.getTitle());
    obj.put("done", task.isDone());
    array.put(obj);
}
Files.writeString(Path.of("tasks.json"), array.toString(2));
```

To **load** them back, read the file, parse it, and reconstruct the objects:

```java
String json = Files.readString(Path.of("tasks.json"));
JSONArray array = new JSONArray(json);

List<Task> tasks = new ArrayList<>();
for (int i = 0; i < array.length(); i++) {
    JSONObject obj = array.getJSONObject(i);
    tasks.add(new Task(obj.getString("title"), obj.getBoolean("done")));
}
```

The exact same pattern works for data you fetched from an API: save the JSON
response to a file once, then load from that file while you develop — no repeated
API calls, and no rate-limit surprises.

## Further reading

- Lists of free, public APIs to experiment with:
  [public-apis](https://github.com/public-apis/public-apis) and
  [public-api-lists](https://github.com/public-api-lists/public-api-lists).
- OkHttp documentation: <https://square.github.io/okhttp/>
- The `org.json` library: <https://github.com/stleary/JSON-java>
- Java file I/O tutorial:
  <https://docs.oracle.com/javase/tutorial/essential/io/file.html>
