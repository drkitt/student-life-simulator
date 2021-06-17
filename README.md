# Student Life Simulator
Project Repository: https://github.com/drkitt/student-life-simulator

* Description: Student Life Simulator will be a game in which the user will manage a student character’s life as a university student. It will simulate the experience of a student taking remote classes from their apartment, over the course of an academic term.

```
Developers - Group 10
   - Alex Kitt (drkitt)
   - Caleb Beynon (CM-beynon)
   - Haotian Chen (MrNoboddy)
   - Kyle Calinisan (kyl-dc)
   - Oghenekome Egbedi (komeegbedi)
```

* Dev Log: The log is maintained as google-doc shared amongst team members. It contains a summary of meeting notes, design decisions and work entries. The current assigned dev tasks are also included in this file.

## Project Architecture
```
comp3350.studentlifesimulator
   - application
   - business
      - DatabaseManager
         - This handles the basic database storage and retrival.
      - StudentPerformingActions
         - This coincides with the logic between the student and the ability to complete an action based on time and energy constraints.
   - objects
      - Action 
         - This refers to a generic actionable task that a student can perform. In this iteration, the only Action instance is studying.
      - Course
      - EnergyBar
      - Student
      - Time
   - persistence
      - Database
   - presentation
      - ApartmentActivity
         - This becomes the main gameplay activity following the course selection
      - CoursesActivity
         - This is the first activity following the start of a new game.
      - MainActivity
         - This is just a generic new game screen.

comp3350.studentlifesimulator.tests
   - business
      - TestStudentPerformingActions
   - objects
      - TestAction
      - TestCourses
      - TestEnergyBar
      - TestStudent
      - TestTime
```

## Iteration 1 Implementation
* Student Status - "I want to be able to check on my student at any time and get updates on their status."
   - Currently implemented the energy-bar and basic energy use functionality.
   - The graphic element displays the student, the current time, and the amount of energy the student has.
 
* Time Management - "I want to be able to manage my student's time between taking classes, studying, and spare time."
   - Time class implementation and in-game time have been setup.
   - Currently, the student only has the ability to study. The game interface shows the time passing as this happens.

* Course Selection - "I want to have a variety of courses I can select from, for my student to take."
   - UI for choosing courses has been completed.
   - The selected courses get stored onto the stub-database.

For this iteration, most of the groundwork for several user stories were implemented. Objects such as the Student, Course, Action, Time, and EnergyBar were implemented. Along with these new classes, we've completed first version of the temporary start screen as well as the course selection view. Logic was created to support the interactions between the objects.
In this iteration, the stub-database was setup to contain the information for object variables, student information and courses. 
