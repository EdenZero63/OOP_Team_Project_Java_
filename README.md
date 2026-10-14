# Campus Event System

A simple terminal program for managing campus events, with the ability to add students, create events,
sign students up for events, and see reports.

This is a repository for our first CS4A "Introduction to Java for Computer Science" class group project.

> This project is currently still being built. Some menu options only show placeholder text for now.

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

| Number | Action                                   |
| ---    | ---                                      |
|     1  | Add a student                            |
|     2  | View students                            |
|     3  | Create an event                          |
|     4  | View events                              |
|     5  | Search for an event                      |
|     6  | Sign a student up for an event           |
|     7  | Cancel a sign-up                         |
|     8  | See which events a student signed up for |
|     9  | See who is going to an event             |
|     10 | View organizers                          |
|     11 | Reports                                  |
|     0  | Save and exit program                    |

## Program Structure

| Folder             | What's inside                                     |
| ---                | ---                                               |
| `src/app/`         | `Main.java`, where the program starts             |
| `src/ui/`          | User interface system                             |
| `src/model/`       | Objects: Student, Organizer, Event, Registration  |
| `src/service/`     | Input validation handling                         |
| `src/persistence/` | Saving and loading file data                      |
| `src/exception/`   | Error type definitions                            |
| `data/`            | Data storage                                      |
| `docs/`            | Documentation                                     |
| `out/`             | Created during program build. Don't upload this.  |

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
2. Please test and bigfix your program before submitting a pull request.
3. Open a Pull Request on GitHub with a comprehensive description so the rest of the team can understand and approve it.

## Credits

[Kerem Eren](https://github.com/qeremeren)<br>
[Valance Fernando](https://github.com/PowerisTsutsun)<br>
[Andrew Goodman](https://github.com/droobix)<br>
[Erik Hernandez](https://github.com/Erik-H-Saddleback)<br>
[Sierra Hunt](https://github.com/darkgrapeman)<br>
[Alexander Nguyen](https://github.com/acnguyen1259)<br>
[Marcelo Soria](https://github.com/EdenZero63)<br>
[James Whiting](https://github.com/James-saddleback)<br>