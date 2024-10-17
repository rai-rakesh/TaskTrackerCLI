# Task CLI Application

A CLI app to track your tasks and manage your to-do list.

## Installation

1. Complile Java code
```bash
javac Main.java Status.java CommandLineController.java Task.java TaskManager.java
```
2. Run the application
```bash
java Main.java <command> [arguments]
```

## Usage

```python
# Adding a new task
java TaskCLIApp add "Buy groceries"
# Output: Task added successfully (ID: 1)

# Updating a task
java TaskCLIApp update 1 "Buy groceries and cook dinner"
# Output: Task updated successfully (ID: 1)

# Deleting a task
java TaskCLIApp delete 1
# Output: Task deleted successfully (ID: 1)

# Marking a task as in progress
java TaskCLIApp mark-in-progress 1
# Output: Task marked as in progress (ID: 1)

# Marking a task as done
java TaskCLIApp mark-done 1
# Output: Task marked as done (ID: 1)

# Listing all tasks
java TaskCLIApp list
# Output: List of all tasks

# Listing tasks by status
java TaskCLIApp list todo
java TaskCLIApp list in-progress
java TaskCLIApp list done
```
