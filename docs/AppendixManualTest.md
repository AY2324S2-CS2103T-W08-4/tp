## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.


### Launch

    1. Initial launch
    
        1.1 Download the jar file and copy into an empty folder
        1.2 Double-click the jar file Expected: Shows the GUI with a set of sample projects.
    
    2. Non-Initial launch:
        2.1 Double-click the jar file Expected: Shows the GUI with last session files saved.

### show project
    Test case: show project Coding Project
    Expected: Successful result shown in the command result box. Details about Coding Project being shown

    Test case: show project CODING PROJECT
    Expected: Error result. Project name is case sensitive.


### Find project and list project

    Test case: find project Duke
    Expected result: Duke chatbot shown. Use list project to reset the filter
    
    Test case: find project C
    Expected result: Nothing shown. Only projects with C AS A WORD, not a character, are shown.

    Test case: find project Project C
    Expected result: IS2218 project and Coding project is shown. Find project is case insensitive. Projects that contain Project or C or Project and C as a word (case insensitive) will be shown.
### Add project
    Test case: add project A
    Expected: Successful result shown. Ui is updated with project A at the bottom of the list.
    
    Test case: add project 
    Expected: Error result. Project cannot be empty.

### Add task
    Test case: add task dummy to CS2103T Ab3’
    Expected: Error result. Users  need to use /to to indicate that users are adding something to the project name after /to

    Test case: add task /to CS2103T Ab3
    Expected: Error result. User need to include the name of the task.
    
    Test case: add task dummy /to CS2103T Ab3
    Expected: Successful result. Using the show project command (how project CS2103T Ab3) will show a task called dummy under the Not Done Section.
    
    Given that users already added “dummy” task into CS2103T Ab3, users could try:
    
        Test case: add task dummy /to CS2103T Ab3
        Expected: Error result. Duplicate task within a project is not allowed.
        
        Test case: add task Dummy /to CS2103T Ab3
        Expected: Successful result command shown. Using the show project command (how project CS2103T Ab3) will show 2 tasks called dummy and Dummy under the Not Done Section since the task name is also case sensitive.


### Deleting a project

    Test case: delete project Duke chatbot 
    Expected: Duke chatbot project deleted. Numbers in front of the other tasks are reordered.
    
    Test case: delete project CS2103T AB3
    Expected: Error result. No project was deleted since the project name is case sensitive.

### Deleting a task in a project
    Starting with add task A /to IS1128 project, users could try the following test case:
    
        Test case: delete task A in  IS1128 project
        Expected: Error shown since users are required to include / in front of “to” ("/to") to indicate that users are referring to existing project / task name.

        Test case: delete task A /in IS1128 project
        Expected:  Successful command. With show project command of IS1128 project, the task A should not be present.


### Add person to a project

    Test case: add person A /to CS2103T Ab3
    Expected: Successful result. Showing project CS2103T Ab3, would show A under team member section.
    
    Adding another person with same name
        Test case: add person A /to CS2103T Ab3
        Expected: Successful result. Showing project CS2103T Ab3, would show 2 persons with name A under team member section.


### Assign a person in current team to a project

    Assuming add person A /to CS2103T Ab3 was used and no member were added to CS2103T Ab3, users could try:
    Test case: assign person A /to <existing task> /in  CS2103T Ab3
    Expected: Successful result. Showing project CS2103T Ab3, would show A under team member below the task Name.
    
    Test case: assign person B /to <existing task> /in  CS2103T Ab3
    Expected: Error result. The person must be added to the project first before assigning to a task.
    
    Test case: assign person A /to <non-existing task> /in  CS2103T Ab3
    Expected: Error result. The task must exist in the project.


### delete person from a project
    Assuming users already added 2 member of the same name “A” into CS2103T Ab3,
    
        Test case: delete person A /in  CS2103T Ab3
        Expected: Successful result. The first (leftmost) A in the list is deleted.


### Setting a status of a task in a project

    Test case: set status incomplete /of <existing task> /in <existing project>
    Expected: Successful result. Showing the corresponding project, ui would show the task under Not Done Section if the task is marked as completed or else nothing changes.

    Test case: set status complete /of <existing task> /in <existing project>
    Expected: Successful result. Showing the corresponding project, ui would show the task under Done Section if it was not marked as completed before else nothing changes.



### Setting a status of a project

    Test case: set status complete /of <existing project>
    Expected: Successful result. Showing the completed tag besides the project name.
    
    Test case: set status incomplete /of <existing project>
    Expected: Successful result. Showing nothing if the task is not completed else delete the completed tag

### Tagging a project category

	Test case: set category f /to <existing project>
Expected: Successful result. Showing tag beside the project name overriding the old tag if any.

### Tagging a project category

	Test case: set category f /to <existing project>
    Expected: Successful result. Showing f tag beside the project name overriding the old tag if any.

### filter category

	Test case: filter category <category tag>
    Expected: Successful result. Showing project with EXACT tag name.
    Use list project to reset the filter and show all the project

## Add comment
    Assuming a is the only team member of project Coding Project

        Test case: add comment dummy /from a /to Coding Project
        Expected: Successful result. Showing project, comment section will be added with name and the comment content (dummy).

        Test case: add comment dummy /from b /to Coding project
        Expected: Error result. Showing project, nothing shown on comment part. The commentator must be a member of the project.

### set deadline of a project
	Test case: set deadline Apr 01 2030 /to IS1128 project
    Expected: Successful result. Ui updates the due date under the IS1128 project to  Apr 01 2030.

	Test case: set deadline Apr 1 2030 /to IS1128 project
    Expected: Successful result. Ui updates the due date under the IS1128 project to  Apr 1 2030.

	Test case: set deadline Apr012030 /to IS1128 project
    Expected: Error result. The Date must be in the MMM dd YYYY or MMM d YYYY (for single digit day date) format separated by spaces.



### set deadline of a task

    Assuming a is the one of the tasks in IS1128 project,
    
        Test case: set deadline Apr 01 2030 /of a /in IS1128 project
        Expected: Successful result. Showing the project, Ui updates the due date under the task a to Apr 01 2030 overriding the prior date if any.

### setting name of a task
    Assuming IS1128 project have only three tasks called: dummy, Dummy, dummy3,
    
        Test case: set name dummy2 /of dummy /in IS1128 project
        Expected: Successful result. Showing the project, Ui updates the name of dummy to dummy2 while Dummy still stays the same.
        
        Test case: set name dummy3 /of Dummy /in IS1128 project
        Expected: Error result. The new name dummy3 already exists.

### setting name of a project
    Assuming IS1128 project and Coding project coexist
       
        Test case: set name Coding Project /of IS1128 project
        Expected: Successful result. Ui updates the name of IS1128 project to Coding Project (Project name is case sensitive)
        
        Test case: set name Coding project /of IS1128 project
        Expected: Error result. The new name Coding project is a duplicate of the existing project name.
