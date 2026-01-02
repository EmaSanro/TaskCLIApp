# TaskCLIApp 📝

**TaskCLIApp** is an efficient and lightweight command-line tool (CLI) designed for managing daily tasks. It allows users to organize their to-dos directly from the terminal, facilitating a streamlined and distraction-free workflow.

---

## 🚀 Main Features

The application offers a complete task management (CRUD) system with the following capabilities:

* **Add Tasks (`add`)**: Register new tasks with a description. The system automatically assigns a unique ID to each entry.

  ```
  java TaskCLI.java add <description>
  ```
* **Update Tasks (`update`)**: Modify the description of an existing task using its reference ID.
  
  ```
  java TaskCLI.java update <new description> <idTask>
  ```
* **Delete Tasks (`delete`)**: Remove tasks from the list permanently.
  
  ```
  java TaskCLI.java delete <idTask>
  ```
* **State Management**: It allows for precise tracking of progress:
    * `mark-in-progress`: Change the status to "In progress".
    ```
    java TaskCLI.java mark-in-progress <idTask>
    ```
    * `mark-done`: Change the status to "Done".
    ```
    java TaskCLI.java mark-done <idTask>
    ```
* **Smart Listing (`list`)**: 
    * View all recorded tasks.
    ```
    java TaskCLI.java list
    ```
    * Filter by Category: `done` (completed), `todo` (pending) o `in-progress` (in progress).
    ```
    java TaskCLI.java list <category>
    ```
---

## 🛠️ Installation and Configuration

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/EmaSanro/TaskCLIApp.git

## Credits to RoadMap.sh:
 [Project Task CLI](https://roadmap.sh/projects/task-tracker)
