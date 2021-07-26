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
      - DatabaseServices
      - Main
   - business
      - DatabaseManager
         - This handles the basic database storage and retrival.
      - StateManager
         - This keeps track of the instances of objects being modified in the system and the states for
         the actions that the student can perform at any given time.
      - StudentPerformingActions
         - This coincides with the logic between the student and the ability to complete an action 
         based on time and energy constraints.
   - objects
      - Action 
         - This refers to a generic actionable task that a student can perform. In this iteration, 
         there are several Action instances made available based on the StateManager's current state.
      - Course
      - EnergyBar
      - Student
      - Time
   - persistence
      - DatabaseAccess
      - DatabaseAccessInterface
   - presentation
      - ApartmentActivity
         - This becomes the main gameplay activity following the course selection.
      - CoursesActivity
         - This allows the player to choose their courses and is the first activity following the start of a new game.
      - MainActivity
         - This is just a generic new game screen.

comp3350.studentlifesimulator.tests
   - business
      - TestDatabaseManager
      - TestStateManager
      - TestStudentPerformingActions
   - objects
      - TestAction
      - TestCourses
      - TestEnergyBar
      - TestStudent
      - TestTime
   - persistence
      - StubDatabase
      - TestDatabase
         - This file accounts for testing the stubDatabse which can be switched with the
         HSQLDB via dependency injection
   - AllTests
```

## Iteration 2 Implementation
* Game Progression - "I want to be able to continue my game from where I last left off."
   - The student life simulator keeps track of score and energy.
   - Along with this, persistence of student choices and action states are tracked through write-backs every time the app closes or goes out-of-focus.
   - The app now recognizes when courses have been selected and upon re-opening, will allow the student to resume their activity without having to reselect their previous course choices.
   
* Time Management - "I want to be able to manage my student's time between taking classes, studying, and spare time."
   - Several actions have been introduced into the system: 3 levels of rest, 3 levels of study, attend/skip class, as well as 2 in-class actions.
   - These actions become available to the student depending on state.

* Reward System - "I want to be able to get rewards when the student is doing well."
   - Scores have been attached to each action and the running score is displayed in the UI.
   - Penalties come in the form of negative scores for "bad" actions.
   - The accumulated score acts as a general gauge to show how well the student is doing.

For this iteration, the development was focused on diversifying the actions that a student can perform. This included the introduction of 5 ActionStates: HAS_CLASS, IN_CLASS_HIGH, IN_CLASS_LOW, FREE_TIME, and LOW_ENERGY.
The StateManager was created to maintain the state-switching and restricting the available actions for the student.
For the database implementation, the files: DatabaseAccess, DatabaseAccessInterface, DatabaseServices and DatabaseManager have been created/updated to maintain the persistence of all necessary objects, actions, and state creation.
Another focus for this iteration was refactoring some tests based on Iteration 1 feedback as well as accounting for testing the persistence and new business objects.
Alex Kitt worked ahead on game assets for the Character Customization user story, to be included in Iteration 3.

## FAQ / Troubleshooting
If errors arise around front-end, try uninstalling and re-running the app.
(This shouldn't happen as all errors encountered during final testing were fixed.)