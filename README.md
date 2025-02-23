**PROBLEM STATEMENT:**  
Every university needs an efficient course registration system where students can register for courses that the university provides, instructors can manage their course assignments, and administrators can oversee registration and make changes to data in the system. An enrollment system will have issues like:

* **Class Size:** Every course will have a finite number of seats, and after capacity is reached enrollment should not be allowed  
* **Prerequisite:** A student shouldn’t be able to register for courses if they haven’t already taken the prerequisite course. Ex. They should not be able to enroll in Calculus 3 if they haven’t taken Calculus 1\.  
* **User Authentication:** A user should be able to login to the system and given different data and permissions available based on their associated “account”  
* **Data Storage:** If I want to simulate a real system, the data should be stored somewhere and persisted across all sessions.

Hopefully with this project I can build a course enrollment system that addresses the challenges above.

**FUNCTIONALITIES:** 

**User Authentication:** 

* Users must login with their credentials(unique email/unique id and password)  
* Different roles (Student, Admin, Instructor) have different possible actions/permissions  
* Users should be able to logout  
* Only be able to access a user's information if they are logged in or a sysadmin.


**Course Management:**

* Admins can add, update, and remove courses if their permissions allow  
* Courses will contain details such as id, name, credits, prereqs, time slots etc.  
* Courses will have a max capacity.  
* The same course cannot have multiple of the same course in the same time slot

**Student Actions:**

* Students can view the available courses  
* Students can enroll in courses if they have the prerequisites and there are no conflicts  
* Students can drop courses  
* Students can see their schedule and the courses they are registered in.

**Instructors Actions:**

* Instructors can view assigned courses  
* Instructors can be assigned courses by an admin

**Handling Conflicts:**

* Students should not be able to enroll in overlapping time slots  
* Students should not be able to enroll in classes they don’t have the prerequisites for

**Storing Data**:

* Initially I will store the data in memory. Once persistence is taught I will introduce file based storage.  
* Course, student , and instructor data should be able to be saved and retrieved

**Application Restraints:**

* **Command Line:** The system will be only accessible through the console/terminal  
* **Session-based system:** Data will only be stored temporarily until it is transitioned to file based data storage  
* **Single Concurrent User:** Only one person can use the app at a time  
* **Only Enrollment:** It would be best to have this integrated with other university software but this is separate.

**Interactions:**

**Student**

1. Logins in with their email and password  
2. Can view available courses and courses they are registered for  
3. Selects a course to register for  
4. The system will check prerequisites and time conflicts  
   1. The student is enrolled  
   2. The system provides reason for enrollment failure  
5. Student can view or drop their courses  
6. Student logs out  
     
   

**Instructor**

1. Logs in with their email and password  
2. Can view assigned courses  
3. Can view course details  
4. Teacher logs out  
   

**Admin**

1. Logs in with their email and password  
2. Can add, remove, update courses.  
3. Can update student and teacher information  
4. Logs out

**Anticipated Challenges:**

**Data Storage:**   
Challenge: I will have to transition the project from temporary storage in memory to file based later on the project.   
Strategy: Once I learn about writing to files I will implement a new class/functionality to handle it

**Conflicts:**  
Challenge: I will be making a large system and I have to have lots of checks to make sure everything is working properly and have no conflicts when registering  
Strategy: Before starting the project and as I am creating I will find errors and possible issues when enrolling, creating, or withdrawing from classes as well as brainstorming how to handle time conflicts

**Prerequisites:**   
Challenge: I have to find a way to get students past class information. I can either have admin add it in manually or find a way for them to upload a file and for me to read from it.  
Strategy: Learn about file writing and reading to be able to implement the past classes for students.

**A Lot of data:**   
Challenge: I have never made an application that could potentially have this much data and will have to find an effective way to handle it all without the application being too slow or ineffective  
Strategy: When we learn about data persistence I will brainstorm the best strategy to create an efficient application when it comes to storing and retrieving data

**\* Key Data Definitions and Method signatures are shown in my javadocs linked in the resources section**
