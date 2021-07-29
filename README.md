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
         - This keeps track of the instances of objects being modified in the system and the states for the actions that the student can perform at any given time.
      - StudentPerformingActions
         - This coincides with the logic between the student and the ability to complete an action based on time and energy constraints.
      - TimeFormatter
         - This takes the time values from the Time class and formats them in a user-friendly way for the presentation layer.
         - This takes logic formerly found in the presentation layer and implements it in the business layer.
   - objects
      - Action 
         - This refers to a generic actionable task that a student can perform. There are several Action instances made available based on the StateManager's current state.
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
         - This allows the player to choose their courses when starting a new game.
      - MainActivity
         - This is the main menu. It allows the player to start a new game, continue their current one, view the game's credits, and view a tutorial.
      - CharacterCustomizationActivity
         - This allows the player to customize their character. It can be accessed by starting a new game or pressing an edit button in ApartmentActivity.
      - CharacterActivity
         - This is an abstract base class containing the character-related functionality shared between CharacterCustomizationActivity and ApartmentActivity.

comp3350.studentlifesimulator.tests
   - business
      - TestDatabaseManager
      - TestStateManager
      - TestStudentPerformingActions
      - TestTimeFormatter
   - integration
      - IntegrationTests
      - TestBusinessPersistenceSeam
      - TestPersistenceDatabaseSeam
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
   
androidTest.comp3350.studentlifesimulator.tests
   - acceptance
      - CharacterCustomizationTest
      - DrawableMatcher
      - EspressoTestsMatcher
      - TimeManagementTest
   - RunAcceptanceTests
```

## Iteration 3 Implementation

* Customization - "I want to be able to choose how my student looks and edit their surroundings/apartment."
   - New games now start with the player creating a custom character, with the character's default attributes loaded from the database
   - The player's character is shown in the apartment screen
   - The player can access the character customization screen at any time from the apartment screen
   - Changes to the character are saved to the database
   - The apartment background has a dynamic day/night cycle triggered by changes in the in-game time

* Time Management - "I want to be able to manage my student's time between taking classes, studying, and spare time."
   - The time system now keeps track of the current day of the week
   - Courses now have specific times and days of the week attached to them

* Game Progression - "I want to be able to continue my game from where I last left off."
   - The main menu now has separate buttons for starting a new game and continuing an existing one
   - The continue button has all the functionality of the play button from iteration 2 (i.e. continuing to play the game with their current score, energy, and courses)
   - The new game button resets all of the player's data to its initial state and lets them start a new game with a new character and different courses
   - The player's customized student is saved whenever the player makes changes to how they look

For this iteration, we focused development on refining and testing existing features.

The integration tests are centered around ensuring that interactions, along the seams of the application's architecture, do what they are supposed to.
TestBusinessPersistenceSeam has 6 test methods: testStudentAccess, testCoursesAccess, testTimeAccess, testActionAccess, testSavingRoutine, and testCharacterAssetAccess. Each of these tests ensure that the Database Access in the business layer is interacting with the database interface as expected, validating that the DatabaseManager fetches and updates data correctly via the DatabaseAccessInnterface.
TestPersistenceDatabaseSeam has 5 test methods: testStudentPersistence, testTimePersistence, testCharactrPersistence, testCoursePersistence, and testActionPersistence. These tests ensure that the HSQL Database is updated and accessed correctly by the database interface implementation in the persistence layer.

The addition of the acceptance tests focused on performing user scripts that take different paths through the big user stories implemented in the game. TimeManagementTest holds 3 test methods: testPartTimeStudent, testFullTimeStudent, and testSleeperSkipperStudent. Each test has its own set of user choices at different times with different courses and validating the correct appearance of actions, energry bar and time checks.
CharacterCustomizationTest holds 3 test methods: testStartGame, testCustomizeCharacter, and testLoadFromContinueGame, determining the possible scenarios of how a user will be accessing the character customization functionality.
GameProgressionScoreTest holds 2 test methods: testContinueGameScore and testNewGameScore, testing the use cases of playing through a completely new game or loading previous game and continue the run with the expectation of preserving the previous state including the time and the player's core

The most significant new feature in this iteration is the character customization screen. We introduced a new activity for customizing the player's character and made the activity accessible when starting a new game. The player can also make changes to their character without starting the game over by editing it from the apartment activity. The player's chosen character attributes are stored in the database and loaded when they continue the game. Additionally, we added an apartment background that changes according to the in-game time in lieu of a customizable background. 

In this iteration we also introduced some minor new features. The time formatter, formats the time based on the in game Time object, which is used to access what time/day should be displayed on the front end, in the apartment activity. As an alternative to displaying how many in game days had passed in the apartment activity, we implemented weekdays and started displaying how many in game weeks had passed and which day of the week it was, in the apartment activity. Building off of the weekdays, we added course schedules to courses, which included a start time and a list of the days of the week they take place on. The course selection screen was edited to include information on course schedules.

## FAQ
Any issues surrounding the loading of acceptance test should be solved by re-syncing the Gradle build:
File -> Sync Project with Gradle Files

The canonical DBscript is located in: 
src/main/assets/db/StudentDB.script
