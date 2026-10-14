# Design

## Overview

The Campus Event System is a console program for managing students, organizers, events and registrations.
The design keeps three jobs apart:

- **Model** classes (`Student`, `Organizer`, `Event` and its types, `Registration`) hold data and keep themselves valid.
- **Service** classes (the managers) own the lists and enforce the rules that involve more than one object, such as
  "no duplicate IDs" or "a full event can't take another student".
- The **UI** (`Menu`) only talks to the user and calls the managers. **Persistence** (`FileManager`) only saves and loads.

`Main` connects them: it creates one `EventSystem`, loads saved data into it, runs the menu, and saves in a `finally`
block so data is saved even if the menu stops with an error.

## Class Diagram

The UML class diagram is in this folder (`UML.png`). It shows every class with its important fields and methods,
the inheritance from `Event`, and the relationships described below.

## Packages

- `app` — application entry point
- `ui` — console menu and input helpers
- `model` — domain entities
- `service` — managers and business logic
- `persistence` — file storage
- `exception` — custom exceptions

Dependencies only point one way: `app` uses `ui`, `service` and `persistence`; `ui` and `persistence` use `service`;
`service` uses `model`. The model classes don't know about the managers or the menu.

## Major Classes

| Class | Responsibility |
| --- | --- |
| `Main` | Starts the program: creates `EventSystem`, `FileManager` and `Menu`; loads, runs, saves. |
| `Menu` | Prints the options, reads input, calls the managers, prints results and errors. |
| `EventSystem` | Creates and holds one of each manager, so every part of the program works on the same data. |
| `StudentManager` | Keeps the list of students; adds (no duplicate IDs), finds and displays them. |
| `OrganizerManager` | Keeps the list of organizers; adds, finds, displays, and links organizers to events. |
| `EventManager` | Keeps the list of events; adds (no duplicate IDs), finds, searches by name, displays. |
| `RegistrationManager` | Keeps the list of registrations; registers (with all four checks), cancels, and lists registrations by student and by event. |
| `ReportGenerator` | Builds the four reports from the event and registration lists. |
| `FileManager` | Saves and loads students, organizers, events and registrations. |
| `Student`, `Organizer` | Hold one person's or group's data. `Organizer` also keeps the list of events it runs. |
| `Event` | Abstract parent of the four event types; holds the seven fields every event has. |
| `AcademicEvent`, `ClubEvent`, `CareerEvent`, `SocialEvent` | One event type each, adding one field (major, club name, company, theme). |
| `Registration` | Records that one student is registered for one event on a date. |

## Relationships

- **Inheritance.** `AcademicEvent`, `ClubEvent`, `CareerEvent` and `SocialEvent` all extend `Event`. Each one *is an* event:
  it has every field an event has, plus one of its own, and passes the shared values to `Event` with `super(...)`.
  The four custom exceptions extend Java's `Exception`.
- **Composition.** `EventSystem` creates its four managers itself and they live as long as it does.
  `RegistrationManager` is given the `StudentManager` and `EventManager` so it can look up students and events.
- **Aggregation.** Each manager holds an `ArrayList` of its objects (`ArrayList<Student>`, `ArrayList<Event>`, and so on).
  `Organizer` holds the `ArrayList<Event>` of events it runs.
- **Association by reference.** A `Registration` holds references to the real `Student` and `Event` objects, not copies
  of them and not just their IDs. If a student's details are printed through a registration, they are the same object
  the `StudentManager` holds.

## Abstract Class

`Event` is abstract because the program never creates a plain event: every event is academic, club, career or social.
Making it abstract stops anyone from writing `new Event(...)` by mistake, while still letting `Event` hold the shared
fields, the constructor's checks, the getters, and a default `displayEvent()` that the types build on.
No interfaces are used; the four event types share real fields and code, which suits inheritance better than an interface.

## Polymorphism

`Event` has `displayEvent()` and `toString()`, and each event type overrides both with `@Override`. Each override calls
the parent version first and then adds its own line, such as "Club : Chess Club".

The managers and reports work with the parent type only. `EventManager.displayAllEvents()` loops over an
`ArrayList<Event>` and calls `displayEvent()` on each one without checking its type, and Java runs the right version for
each object. This keeps the managers simple: adding a fifth event type would not need any change to `EventManager`,
the menu's view and search options, or the reports. `Event.displayEvent()` also prints the real type name using
`getClass().getSimpleName()`.

## Design Decisions and Tradeoffs

- **Managers own the rules, not the menu.** Checks like "no duplicate IDs" and "event is full" live in the managers,
  so they apply no matter who calls them, including `FileManager` when it reloads data.
- **The organizer stores its events (not the other way round).** The spec leaves this choice to the team. An
  `Organizer` keeps a list of `Event` references, which makes "view events managed by an organizer" a direct lookup.
  The tradeoff is that finding the organizer of an event means checking every organizer
  (`OrganizerManager.findOrganizerOfEvent`), which is fine for a campus-sized list.
- **Registrations reference objects, and are saved as IDs.** In memory, references avoid duplicate copies of students
  and events. In the files, objects can't be saved as references, so registrations and organizer links are saved as IDs
  and turned back into references on load.
- **`ArrayList` for every collection.** The lists are small, order of entry is useful for display, and every lookup
  loops anyway because IDs are matched ignoring capitals and spaces. A `HashMap` would make lookups faster but would need
  every ID normalized the same way first; for this size, `ArrayList` keeps the code simpler.
- **Two kinds of lookup.** `findEventById` returns `null` when nothing matches, for places where "not found" is a normal
  answer (like checking for duplicates). `getEvent` throws `NotFoundException`, for actions that can't continue without
  the event. `OrganizerManager` follows the same pattern.
- **Checked custom exceptions.** `DuplicateIdException`, `DuplicateRegistrationException`, `EventFullException` and
  `NotFoundException` are checked, so any method that can fail that way must say so and the menu must handle it. The
  menu catches them and prints the message. Ordinary input problems, such as a non-number capacity or an unknown menu
  choice, are handled with normal checks instead of exceptions.
- **IDs are text.** IDs are `String`s, so formats like `S1`, `001` or `CS-101` all work. Lookups ignore capitals and
  extra spaces.
- **Registration IDs are built from the two IDs.** The menu uses `studentID-eventID` (for example `S1-E1`). It is always
  unique because the same student can't register for the same event twice.

## Data Storage and Reconstruction

`FileManager` saves four files in the `data/` folder when the program exits:

| File | Fields per line |
| --- | --- |
| `students.txt` | ID, name, email, major |
| `organizers.txt` | ID, name, email, department, and the IDs of its events |
| `events.txt` | type, ID, name, description, date, time, location, capacity, and the type's extra field |
| `registrations.txt` | registration ID, student ID, event ID, date |

Fields are separated by `|`, and each field is Base64-encoded so a `|` inside a name or description can't split the line.
This is encoding, not encryption.

On start-up the order matters, because later files point to earlier ones:

1. Students, organizers and events are created from their files. The event type saved on each line decides which
   subclass is built.
2. Each organizer's saved event IDs are looked up and linked back to the real `Event` objects.
3. Each registration is rebuilt through `RegistrationManager.registerStudent` with its saved date, so the same checks
   run again; a registration whose student or event is missing is skipped with a message.

A missing file means "nothing saved yet". A damaged line is skipped with a message instead of stopping the whole load.

## How the Team Divided the Project

| Member | Main work |
| --- | --- |
| Kerem Eren | `ReportGenerator`; the event description field; the menu's event options and reports submenu |
| Valance Fernando | Project setup and `Main`; `Registration`; `Organizer` and `OrganizerManager`; the `Event` classes, `EventManager` and `EventSystem` (with Sierra); integration fixes |
| Andrew Goodman | Connecting `RegistrationManager` to `EventSystem`; menu input tweaks; README |
| Erik Hernandez | First version of `RegistrationManager` |
| Sierra Hunt | `FileManager`, `InputHelper`, and the first version of the event types and `EventManager` |
| Alexander Nguyen | `Student`, `StudentManager`, UML diagram |
| Marcelo Soria | The console `Menu` and the first `Event` class |
| James Whiting | `RegistrationManager` registration and cancellation |

Each member worked on their own branch and opened Pull Requests into `main`.

## Technical Challenges

- **Classes written against an older version of each other.** Several pieces were written in parallel and no longer
  matched once merged: `FileManager` and `InputHelper` called event constructors without the new description and time
  fields, used `int` IDs where the rest of the program uses `String`, and called methods that had been renamed.
  `EventSystem` called a `RegistrationManager` constructor that no longer existed. These were fixed by updating each
  caller to the current method signatures and agreeing on `String` IDs everywhere.
- **Capacity check off by one.** The first registration check counted inside the loop and only failed when the count
  went *over* capacity, so a full event accepted one more student. The check now runs after counting, with `>=`.
- **Mixing `next()` and `nextLine()`.** Reading a Y/N answer with `next()` left the Enter key in the input buffer, so the
  next `nextLine()` read an empty ID and creating an organizer always failed. Reading every answer with `nextLine()`
  through one shared `Scanner` fixed it.
- **Clearing the screen.** The clear-screen code was written as `"\\033[H\\033[2J"`, which Java prints as text. It needs a
  single backslash (`"\033"`) to send the escape character, and a "Press Enter to continue" pause so results aren't
  cleared before they can be read.
- **Rebuilding relationships after loading.** Organizers load before events, so their event links can't be made right
  away; the IDs are kept aside and linked once the events exist.
- **Git.** Some files were committed to the repository root instead of `src/`, and some branches were based on old code
  and conflicted when merged. The root copies were removed, and branches were brought up to date with `main` before
  being merged.
