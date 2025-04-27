# Course Enrollment System

## Overview
The **Course Enrollment System** is a console-based Java application that allows students to register for classes, instructors to manage their course sections, and administrators to manage users, courses, sections, and enrollment.

The system uses a simple, text-based menu-driven interface, designed to mimic a simple university course registration system.

## System Requirements
- Java 17 or higher
- An IDE (VSCode, IntelliJ, etc.) or a terminal

## How to Run

### 1. Clone or Download the Repository
Clone the repository using Git:
```bash
git clone https://github.com/talviano/CourseEnrollmentSystem.git
```

Or download the ZIP from GitHub and extract it.

### 2. Open in Your IDE
- Open the project in IntelliJ, Eclipse, or VSCode.
- Ensure the `src` folder is marked as the "Sources Root" if necessary.

### 3. Compile and Run `Main.java`
- Navigate to `src/main/Main.java`.
- Right-click `Main.java` and choose **Run Java**.

Or compile and run from the terminal:
```bash
javac src/main/Main.java
java -cp src main.Main
```

### 4. Interact with the System
- Use the console menus to log in and perform actions based on your role.


## Default Login Credentials
You can log in immediately using override credentials:

| Role        | Email             | Password        |
|-------------|-------------------|-----------------|
| Admin       | adminoveride       | adminoveride    |
| Student     | studentoveride     | studentoveride  |
| Instructor  | instructoroveride  | studentoveride  |


## Features

### Student Actions
- View available courses
- View personal schedule
- Enroll in courses by CRN
- Drop courses by CRN

### Instructor Actions
- View assigned course sections
- View rosters of enrolled students

### Admin Actions
- Manage users (create/view/delete students, instructors, admins)
- Manage courses and course sections
- Manage enrollment (enroll students, drop students, assign/unassign instructors)
- Manage advising holds
- Manage admin permissions

## Folder Structure

```
src/
  main/         Main class with main method
  model/        Core data types (User, Student, Instructor, Admin, Course, CourseSection, TimeSlot)
  system/       System managers (AccountManager, EnrollmentSystem)
  ui/           User interface pages (LoginPage, StudentPage, InstructorPage, AdminPage, etc.)
  util/         Utility functions and table printing helpers
```


## License
This project is licensed under the MIT License. Feel free to use and modify it for educational purposes.

---
