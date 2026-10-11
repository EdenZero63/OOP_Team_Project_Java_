# Campus Event System

A simple program for managing campus events. You can add students, create events,
sign students up for events, and see reports. It runs in the terminal (no windows or buttons,
just text and typing).

This is a team project for our Object-Oriented Programming (Java) class.

> **Status:** still being built. Some menu options only show placeholder text for now.

---

## What you need

Just **Java** (the JDK). To check if you have it, open a terminal and type:

```
java -version
javac -version
```

If both print a version number, you're good. If not, download the JDK from
[adoptium.net](https://adoptium.net) and install it with the default settings.

---

## How to run it

1. Download or clone this project.
2. Open a terminal **inside the project folder**.
3. Copy and paste these two lines, one at a time:

**Windows (PowerShell):**

```powershell
javac -d out (Get-ChildItem -Path src -Recurse -Filter *.java).FullName
java -cp out app.Main
```

**Mac / Linux / Git Bash:**

```bash
javac -d out $(find src -name "*.java")
java -cp out app.Main
```

The first line builds the program. The second line starts it.

**Using VS Code instead?** Install the "Extension Pack for Java", open this folder,
open `src/app/Main.java`, and click **Run** above `main`.

---

## How to use it

When it starts you'll see a menu. Type a number and press **Enter**.

| Number | What it does |
|---|---|
| 1 | Add a student |
| 2 | View students |
| 3 | Create an event |
| 4 | View events |
| 5 | Search for an event |
| 6 | Sign a student up for an event |
| 7 | Cancel a sign-up |
| 8 | See which events a student signed up for |
| 9 | See who is going to an event |
| 10 | View organizers |
| 11 | Reports |
| 0 | Save and exit |

---

## What's in each folder

| Folder | What's inside |
|---|---|
| `src/app/` | `Main.java`, where the program starts |
| `src/ui/` | The menu you see and type into |
| `src/model/` | The "things" in the system: Student, Organizer, Event, Registration |
| `src/service/` | The rules (for example, an event can't go over capacity) |
| `src/persistence/` | Saving and loading data |
| `src/exception/` | Custom error types (like "event is full") |
| `data/` | Where saved data goes |
| `docs/` | Design notes |
| `out/` | Created when you build. Don't upload this. |

---

## Common problems

**"javac is not recognized" / "command not found"**
Java isn't installed, or your computer can't find it. Install the JDK (see above),
then close and reopen the terminal.

**Lots of red errors after the first line**
Someone's code has a mistake that stops it from building. Read the first error: it tells
you the file and line number. Ask the team if you're not sure.

**"Could not find or load main class app.Main"**
The build step didn't finish. Run the first line again and fix any errors it shows.

---

## Working on the project (teammates)

1. Make your own branch. Don't work directly on `main`.
2. Before you push, make sure the program still builds (the first line above runs with no errors).
3. Push your branch and open a Pull Request on GitHub so the team can look at it.
