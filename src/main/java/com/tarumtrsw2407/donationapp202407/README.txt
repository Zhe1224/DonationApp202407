BACS2063 DATA STRUCTURE AND ALGORITHM ASSIGNMENT

How to Run Our Charity Management System
run DonationApp202407-main\src\main\java\com\tarumtrsw2407\donationapp202407\client\DonationApp202407.java
to start the program. Type the subsystem name with Caps to enter to the subsystem.

We have a total of 4 subsystems, which are donor management, donee management, volunteer management and event management. We will be explaining how the overall code of each subsystem functions.

1. Donor Management Subsystem

i)Entity interface + classes: Donor.java, PersonDonor.java, OrgDonor.java
Description: PersonDonor and OrgDonor represent subtypes of Donor. PersonDonor class represents an individual donor; OrgDonor class represents a donating organisation. The Donor interface declares getters and setters guaranteed to be defined in both subtypes. 

Responsibilities:

Store and manage donor details such as ID, category, (first&last) name, birthday/found date and home region.
Note: Assign and manage donor donations is not supported because the Donation subsystem is not implemented.

ii)Control class: DonorManager.java
Description: This class is responsible for managing donors. It contains the core logic for handling operations such as adding, updating, removing, searching, filtering, and generating donor reports.

Responsibilities:

Create and maintain a list of donors (getVolunteerList()).
Handle the addition, update and removal of donors (add(), update(), remove()).
Search/filter donors by a specific field(filter()) (Donor names will be partially matched)
Generate reports on volunteer participation and event involvement (summary()).

iii)Boundary class: DonorUI.java (Assisted by BasePrompt.java,InputPrompt.java and Menu.java)
Description: This main class serves as the user interface for the Donor Subsystem. It handles interactions with the user, displays menus, and gathers user input. It also invokes appropriate methods from the DonorManager class to perform various operations like adding, updating, removing, searching and filtering donors, and generating reports.

Responsibilities:

Interact with the user (askForInput(),askForChoice(),askForDate()).
Manage user interactions for:
Adding donors (appendDonor()).
Viewing donor list/Selecting a donor from a list (viewDonor()) (Also invoked to present filter results).
 - Updating the selected donor (update()).
 - Deleting a selected donor (delete()).
Filtering donors by attributes (filterDonors()).
Generating reports (summary()).
Exiting the system.

How to enter subsystem:
Run DonationApp202407.java > Enter 'Donor' (or any partially matching text) > 

How to leave submenus/abort operations:
Submit (press Enter) without anything typed in. 
This only works for prompts with mandatory input.
Certain menus explicitly offer a leave/exit option.

How to choose from presented options:
Enter the option label(or any partially matching text)
 - Supports partial match
 - Case-sensitive
 - Spelling sensitive
 - First matched option takes precedence if the input is ambiguous

Action tree:
{...Donor}
	Add donor
		Select type
			Enter ID
				Select category
					Enter (first) name
						(Enter last name)
							Enter birthday/found date
								Enter home region
	View donors
		Select donor by ID
			Update donor
				Enter ID
					Select category
						Enter first name
							Enter last name
								Enter birthday/found date
									Enter home region
										[Select donor by ID...]
			Remove donor
	Search/Filter donors
		Select field
			Enter/Choose data
				[View donors...]
	Get summary
	Leave

2. Donee Management Subsystem
- You would have to manually add the Donee as there are no hard-coded Donee.
- The DoneeID can be either numerical or alphanumerical
- Name can only have alphabets 
- The search function is only for names.

3. Volunteer Management Subsystem
There are a total of 3 files: Volunteer.java, VolunteerManager.java, VolunteerUI.java

i)Volunteer.java
Description: This class represents a volunteer in the system. It contains attributes such as volunteerId, name, contactNumber, email, and a list of events that the volunteer is involved in. It also contains methods to manage these attributes, including setters, getters, and toString().

Responsibilities:

Store and manage volunteer details such as ID, name, and contact information.
Assign and manage events the volunteer is associated with through methods like assignEvent().

ii)VolunteerManager.java
Description: This class is responsible for managing volunteers and their associations with events. It contains the core logic for handling operations such as adding, removing, searching, filtering, and generating reports related to volunteers.

Responsibilities:

Create and maintain a list of volunteers (getVolunteerList()).
Handle the addition and removal of volunteers (addVolunteer(), removeVolunteer()).
Implement search functionality based on volunteer ID (searchVolunteerById()).
Filter volunteers by event (filterVolunteersByEvent()).
Generate reports on volunteer participation and event involvement (generateSummaryReport()).

iii)VolunteerUI.java
Description: This main class serves as the user interface for the Volunteer Management Subsystem. It handles interactions with the user, displays menus, and gathers user input. It also invokes appropriate methods from the VolunteerManager class to perform various operations like adding, removing, and searching for volunteers, assigning them to events, filtering them based on events, and generating reports.

Responsibilities:

Display the main menu and options for users (displayMenu()).
Handle user input and assign tasks to backend logic (getUserInput(), handleChoice()).
Manage user interactions for:
Adding volunteers (addVolunteer()).
Removing volunteers (removeVolunteer()).
Searching volunteers using ID (searchVolunteer()).
Assigning volunteers to events (assignVolunteerToEvent()).
Viewing events associated with a volunteer (searchEventsUnderVolunteer()).
Listing all volunteers (listAllVolunteers()).
Filtering volunteers by events (filterVolunteers()).
Generating reports (generateSummaryReports()).
Exiting the system.

How to run and input:
Run VolunteerUI.java > Select options 1-9:
Select 1 > Enter Volunteer ID >Enter name > phone number > email > (Volunteer successfully added).
Select 2 > Enter Volunteer ID > (Volunteer removed).
Select 3 > Enter Volunteer ID > (Volunteer details displayed).
Select 3 > Enter Volunteer Name > (Volunteer details displayed).
Select 4 > Enter Volunteer ID > Enter Event Name > (Volunteer assigned to event).
Select 5 > Enter Volunteer ID > (Events under that volunteer displayed).
Select 6 > (List of all volunteers displayed).
Select 7 > Enter Event Name > (List of volunteers under that event displayed).
Select 8 > (Volunteer Participation Report generated and displayed).
Select 9 > Exit the system.



4. Event Management Subsystem
a)
Event ID format => E0000
Volunteer ID format => V0000

b)
Input only 1-10 to navigate within the menu.

Event Management System
1. Add a New Event
2. Remove an Event
3. Search an Event
4. Amend an Event Details
5. List All Events
6. Add a Volunteer to an Event
7. Remove a Volunteer from an Event
8. List All Events for a Volunteer
9. Generate Summary Reports
10. Exit
Choose an option:


c)
Input Event ID in correct format to Search Event, Amend Event Details, and Add volunteer to an event.
Input Volunteer ID in correct format to Remove a Volunteer from an Event and List All Events for a Volunteer.

d) 
Preset Event:
ID: E001, Event Name: Nature Blood Donation, Event Date: 2024-09-10, Location: Central Park
ID: E002, Event Name: NYC Blood Donation, Event Date: 2024-09-12, Location: Community Center

Preset Volunteers:
ID: V001, Name: Alice, Contact Number: 745, Email:alice@example.com
ID: V002, Name: Bob, Contact Number: 457, Email:bob@example.com
ID: V003, Name: Jason, Contact Number: 040, Email:jason@example.com
ID: V004, Name: Sandra, Contact Number: 041, Email:sandra@example.com
