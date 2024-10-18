# Task CLI Application

A CLI app to track your tasks and manage your to-do list.
Sample solution for the [task-tracker](https://roadmap.sh/projects/task-tracker) challenge from [roadmap.sh](https://roadmap.sh/).

## Installation

1. Complile Java code
```bash
javac -d bin -sourcepath src src\model\Status.java src\model\Task.java src\model\TaskManager.java src\controllers\CommandLineController.java src\Main.java
```
2. Run the application
```bash
java -cp bin Main
```

## Usage

```python
# Adding a new task
add Learn LearnSpringSecurity
# Output: Task 6 with id LearnSpringSecurity added successfully

# Updating a task
update 6 Learn Tailwind
# Output: Task with id : 6 successfully updated

# Deleting a task
delete 6
# Output: Task with id : 6 succesfully deleted

# Marking a task as in progress
mark-in-progress 5
# Output: Task with id : 5 marked IN_PROGRESS

# Marking a task as done
mark-done 4
# Output: TTask with id : 4 marked DONE

# Listing all tasks
list
# Output: List of all tasks

# Listing tasks by status
list todo
list in-progress
list done
```
