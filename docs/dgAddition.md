### Renaming a project

#### Implementation

1. The PlannerParser parses the command string given by the user, and looks for the command word "set name". Then RenameCommandParser parse function is called.
2. If the PROJECT_NAME is an empty string or the command is incomplete or wrong, an exception is thrown, else the addProjectCommand execution function is called.
3. The `EditProjectNameCommand` class is responsible for editing a project's name to the specified name.
    - The constructor of the class takes in a project of type Project and the new name of type Name.
    - If the target name is the same as the project that already exists within the project list, then an exception is thrown to alert users that the new name is a duplicate.
    - Else the project's name is successfully updated.

![Interactions Inside the Logic Component for the `renaming nn /of Duke` Command](images/EditProjectNameDiagram.png)

#### Design considerations:

**Aspect of command word**

- **Alternative 1:** Command word is `rename` 

    - Pros: More intuitive and straightforward than set name
    - Cons: Users have to remember new prefix since we planned to use other features with set (category/status) prefix

- **Alternative 2 (current choice):** Command word is `set name`.
    - Pros: align with other commands and less keywords to remember for users.
    - Cons: More words to type for users.



### Renaming a task

#### Implementation

1. The PlannerParser parses the command string given by the user, and looks for the command word "set name". Then RenameCommandParser parse function is called.
2. If the PROJECT_NAME or / and TASK_NAME  is an empty string or the command is incomplete or wrong, an exception is thrown, else the addProjectCommand execution function is called.
3. The `EditTaskNameCommand` class is responsible for editing a task's name within the specified project to the specified name.
    - The constructor of the class takes in a project of type Project,target task of type Task, and the new name of type Name.
    - If the target name is the same as the any tasks in project that already exists within the project, then an exception is thrown to alert users that the new name is a duplicate.
    - Else the task's name is successfully updated.


#### Design considerations:

**Aspect of command word**

- **Alternative 1:** Command word is `rename`

    - Pros: More intuitive and straightforward than set name
    - Cons: Users have to remember new prefix since we planned to use other features with set (category/status) prefix

- **Alternative 2 (current choice):** Command word is `set name`.
    - Pros: align with other commands and less keywords to remember for users.
    - Cons: More words to type for users.



### Adding a Comment

#### Implementation

1. The PlannerParser parses the command string given by the user, and looks for the command word "add comment". Then AddCommentCommandParser parse function is called.
2. If the PROJECT_NAME or / and the member is not added to the team, an error will be thrown to reflect the error.
3. The `AddCommentCommand` class is responsible for adding specific comment by specific team member to the project.
    - The constructor of the class takes in a project of type Project, commentator of type Member, and comment of type String.
    - If the target name is the same as the project that already exists within the project list, then an exception is thrown to alert users that the target name is a duplicate.
    - Else the project's name is successfully updated.

![Interactions Inside the Logic Component for the `add project Duke` Command](images/AddProjectSequenceDiagram.png)

