## Appendix: Effort

Since this project is brownfield, we streamlined our efforts by leveraging the AB3 Design as a reference point, incorporating additional classes or structures where needed. For instance, we expanded functionalities within AB3’s Person class to align with our Project Class requirements. Referring to AB3's original structure provided a clear framework for any new features. When adding a new feature, we found that the core components required were often new Command and CommandParser classes, whose internal flow we could refer to AB3's. Without this reference, we would have grappled with structuring and possibly switching between different designs, causing delays if our initial plans fell short.

The most challenging part would be the user interface (UI). Understanding the linkage between front-end and back-end, as laid out in AB3's source code, demanded significant time and effort. Particularly when introducing new interfaces for additional functionalities beyond AB3's scope, we encountered several bugs. While the back-end functionality usually functioned as expected, the UI occasionally failed to display or behaved unexpectedly.
Certain instances of UI-related problems we faced:
Our attempt to integrate a feature allowing users to add multiple category tags to a task led to UI malfunctions, causing the app to display a black screen. Thus, we discussed and designed to only allow one tag per task and spent our time and effort more on developing new features.  
Efforts to include the changing of project names or categories didn't update immediately in the UI.
Addressing a reported bug related to truncated display of team members' information and comment boxes (as highlighted by the PED) involved experimenting with different UI structures, such as choosing between Vbox and Hbox, or deciding on the placement of scroll panes whether within the box or placing the box within the pane. Once the structure was finalized, we encountered issues with scroll pane visibility due to background color, prompting research into CSS syntax for achieving a transparent scroll pane that behaved as expected.

Ultimately, through collective team and individual efforts involving extensive learning, research, flexibility, and comprehension of underlying systems, we overcome these challenges. We are eventually able to  come up with a design that closely mirrors our initial plans, both in functionality and interactivity.


Instructions for Manual Testing


