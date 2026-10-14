# Campus Event System

A simple terminal program for managing campus events, with the ability to add students, create events,
sign students up for events, and see reports.

This is a repository for our first CS4A "Introduction to Java for Computer Science" class group project.

## Features

- Add students and view one student or all of them
- Create organizers, and link an organizer to the events they run
- Create four kinds of events: academic, club, career and social
- View one event or all of them, and search events by name
- Register a student for an event, with checks that the student and event exist, the student isn't already registered, and the event isn't full
- Cancel a registration
- See which events a student is registered for, and which students are attending an event
- Four reports: students registered per event, events with space left, full events, and events by organizer
- Everything is saved when the program exits and loaded again the next time it starts

## System Requirements

The only requirement is [Java](https://www.java.com) (the JDK). To check if you have it installed correctly, open a terminal and type:

```powershell
java -version
javac -version
```

Both commands should output a version number. If not, download the JDK from
[adoptium.net](https://adoptium.net) and follow the site's instructions to download with the default settings.

## Installation

1. Clone this repository.
2. Open a terminal inside the project's root folder.
3. Build the project depending on your OS:

**Windows (PowerShell):**

```powershell
javac -d out (Get-ChildItem -Path src -Recurse -Filter *.java).FullName
```

**Mac / Linux / Git Bash:**

```bash
javac -d out $(find src -name "*.java")
```

4. Run the project:

```powershell
java -cp out app.Main
```

**Using VS Code instead?** Install the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack), open `src/app/Main.java`, and click **Run** above `main`.

## Usage

When the program starts a menu will appear. Type a number for your preferred option and press **Enter**.
After each option, press **Enter** again to go back to the menu.

| Number | Action                                                     |
| ---    | ---                                                        |
|     1  | Add a student                                              |
|     2  | View students (type an ID, or press Enter to see all)      |
|     3  | Create an organizer                                        |
|     4  | Create an event, and optionally link it to an organizer    |
|     5  | View events (type an ID, or press Enter to see all)        |
|     6  | Search for an event by name                                |
|     7  | Sign a student up for an event                             |
|     8  | Cancel a sign-up                                           |
|     9  | See which events a student signed up for                   |
|     10 | See who is going to an event                               |
|     11 | View organizers and the events they run                    |
|     12 | Reports                                                    |
|     0  | Save and exit program                                      |

IDs are text, so `S1`, `001` and `CS-101` all work. Capital letters and extra spaces don't matter when you look an ID up.

## Program Structure

| Folder             | What's inside                                                        |
| ---                | ---                                                                  |
| `src/app/`         | `Main.java`, where the program starts                                |
| `src/ui/`          | The console menu (`Menu`) and input helpers (`InputHelper`)          |
| `src/model/`       | Objects: Student, Organizer, Event and its four types, Registration  |
| `src/service/`     | The managers that keep each list and enforce the rules, and reports  |
| `src/persistence/` | Saving and loading file data (`FileManager`)                         |
| `src/exception/`   | Error type definitions                                               |
| `data/`            | Data storage, created when the program runs                          |
| `docs/`            | Documentation: `DESIGN.md` and the UML diagram                       |
| `out/`             | Created during program build. Don't upload this.                     |

## Class Design

| Class | Responsibility |
| ---   | ---            |
| `Main` | Creates the `EventSystem`, loads saved data, runs the menu, and saves on exit |
| `Menu` | Shows the options, reads what the user types, and calls the managers |
| `EventSystem` | Holds one of each manager, so every part of the program shares the same data |
| `StudentManager`, `OrganizerManager`, `EventManager` | Each keeps one list and enforces its rules, like no duplicate IDs |
| `RegistrationManager` | Registers and cancels, and checks the student, the event, duplicates and capacity |
| `ReportGenerator` | Prints the four reports |
| `FileManager` | Saves and loads the four data files |
| `Student`, `Organizer`, `Registration` | Hold their own data. A registration points to the real `Student` and `Event` objects |
| `Event` | Abstract parent of `AcademicEvent`, `ClubEvent`, `CareerEvent` and `SocialEvent`. Each type adds one field and its own `displayEvent()` |

The full design, including relationships, polymorphism and design decisions, is in [`docs/DESIGN.md`](docs/DESIGN.md).

## Data Storage

Data is saved to the `data/` folder when you choose **0** to exit, and loaded again the next time the program starts.

| File                | One line per                                                                 |
| ---                 | ---                                                                          |
| `students.txt`      | Student: ID, name, email, major                                              |
| `organizers.txt`    | Organizer: ID, name, email, department, and the IDs of the events it runs    |
| `events.txt`        | Event: type, ID, name, description, date, time, location, capacity, and the type's extra field |
| `registrations.txt` | Registration: registration ID, student ID, event ID, date                    |

Fields are separated by `|`, and each field is written in Base64 so a `|` typed into a name or description can't break the line.
Base64 is an encoding, not encryption: it keeps the format safe, not the data secret.

Relationships are saved as IDs. On start-up, students, organizers and events are loaded first; then organizers are linked to their events
and registrations are rebuilt by looking up the student and event IDs. A missing file is skipped, and a broken line is skipped with a message.

The `data/` folder is not uploaded to GitHub, so each person starts with their own data.

## Known Limitations

- Students, events and organizers can't be edited or deleted from the menu.
- An organizer can only be linked to an event while creating that event.
- Dates and times are stored as plain text, so they aren't checked or sorted.
- An event's capacity can't be changed after it's created.
- Cancelling a registration removes it; there is no "cancelled" status kept.
- `InputHelper` has safe input methods, but the menu doesn't use them yet.
- One person uses the program at a time; there are no logins.

## Troubleshooting

`javac is not recognized`/`command not found`
<br>
Java isn't installed, or your computer can't find it. Install the JDK (see above),
then close and reopen the terminal.

**Lots of red errors after the first line**
<br>
Someone's code has a mistake that stops it from building. Read the first error: it tells
you the file and line number. Ask the team if you're not sure.

`Could not find or load main class app.Main`
<br>
The build step didn't finish. Run the first line again and fix any errors it shows.

## Contributing

1. Create your own branch with a unique and sensible name.
2. Please test and bugfix your program before submitting a pull request.
3. Open a Pull Request on GitHub with a comprehensive description so the rest of the team can understand and approve it.

## Team Members

| Member | Main responsibilities |
| ---    | ---                   |
| [Kerem Eren](https://github.com/qeremeren) | `ReportGenerator` and the four reports; the event description field; the menu's create, view and search event options and reports submenu |
| [Valance Fernando](https://github.com/PowerisTsutsun) | Project setup and `Main`; `Registration`; `Organizer` and `OrganizerManager`; the `Event` classes, `EventManager` and `EventSystem` (with Sierra); integration fixes for registration, file saving and the menu |
| [Andrew Goodman](https://github.com/droobix) | Connecting `RegistrationManager` to `EventSystem`; menu input tweaks; this README |
| [Erik Hernandez](https://github.com/Erik-H-Saddleback) | The first version of `RegistrationManager` |
| [Sierra Hunt](https://github.com/darkgrapeman) | `FileManager` (saving and loading), `InputHelper`, and the first version of the event types and `EventManager` |
| [Alexander Nguyen](https://github.com/acnguyen1259) | `Student`, `StudentManager`, and the UML diagram |
| [Marcelo Soria](https://github.com/EdenZero63) | The console `Menu` and the first `Event` class |
| [James Whiting](https://github.com/James-saddleback) | `RegistrationManager` registration and cancellation |
