# Simple Variable Calculator with Pre/Post-Increment Operations

## Task

Implement an interactive calculator that maintains variables in memory and supports assignment operations with pre-increment and post-increment operators. The calculator should run in a command-line loop, accepting user input until they type "exit".

## Requirements

- **Variable assignment** - Support assigning numeric values to variables (e.g., `x=5`)
- **Variable reference** - Support assigning one variable's value to another (e.g., `y=x`)
- **Post-increment** - Support post-increment operator where the variable is incremented AFTER its value is used (e.g., `y=x++` assigns current value of x to y, then increments x)
- **Pre-increment** - Support pre-increment operator where the variable is incremented BEFORE its value is used (e.g., `y=++x` increments x first, then assigns the new value to y)
- **Memory display** - After each command, display all variables and their current values
- **Command parsing** - Handle optional semicolons at the end of commands and trim whitespace
- **Exit command** - Allow users to exit the program by typing "exit"
- **Error handling** - Display a message for unsupported expressions

## Example

```shell
Enter a command: x=5
x=5

Enter a command: y=x++
x=6
y=5

Enter a command: z=++x
x=7
z=7

Enter a command: exit
Bye Bye..
```

## Level

**Mid- to Senior-level Backend Engineer**
