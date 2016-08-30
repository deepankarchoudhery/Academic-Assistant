The electronic submission includes the following files in the zip
AcademicAssistant (project folder)
readme.txt

The project is the AcademicAssistant folder. To run the project, use open project in Android Studio and open the trunk folder. This should open up the project code in Android Studio where it can be built and run.

There are two folders where the source files exist mainjava and testjava. 
mainjava has the all project source files that are not test files. It includes the following packages

1. Application 
Main.java
Services.java
2. Business — includes all the logic java files including
CalculateGPA.java
CourseComparator.java
CourseLogic.java
CourseElementLogic.java
TermComparator.java
TermLogic.java
3. Objects — includes all the domain-specific objects
AcademicObject.java
Course.java
CourseElement.java
Event.java
Semester.java
Student.java
Term.java
4. Persistence — includes the stub database files
Persistance.java
SQLDB.java
5. Presentation — includes all the GUI files
CreateCourses.java
CreateCourseElement.java
CreateTerm.java
DeleteCourses.java
DeleteCourseElement.java
DeleteTerm.java
EditCoursePage.java
EditTermPage.java
HomePage.java
SetGradeGoal.java
ViewCourse.java
ViewCourseElement.java
ViewTerm.java
ViewTerms.java

testjava includes all the JUnit test files. It includes the following packages

1. Business
CalculateGPATest.java
CourseComparatorTest.java
CourseElementLogicTest.java
CourseLogicTest.java
TermComparatorTest.java
TermLogicTest.java
2. Objects
AcademicObjectTest.java
CourseTest.java
EventTest.java
SemesterTest.java
StudentTest.java
TermTest.java
	3. Persistence
PersistenceTest.java
StubDB.java
4. Integration Tests
BusinessPersistenceSeamTest.java
DataAccessIntegrationTest.java
5. Persistence


	AllTests.java
	RunUnitTests.java
	RunIntegrationTests.java

Acceptancejava includes the AcceptanceTests.java which is the java file with the robotium tests.

This iteration implemented a number of new big user stories. We implemented 4 new big user stories. They include “Select a Course to View or Edit”, “Manipulate a Course”, “Create a Course Element.”, “Manipulate a Course Element”.These give the user the ability the select a course, view course information, edit the course and delete the course. User can also add, delete and maintain course elements. GPA and grades are now accessible in terms and courses.

Also a decent bit of low and medium user stories did not get implemented. We believe the scope was quite large for the amount of time we had and feel that we did enough in the time allotted. The user stories include “Create Due Dates”, ‘View Due Dates”, “View timeline of your degree.”


Root cause analysis - 

5 Whys  Adding Course elements gives errors. 
Can’t add course elements with any similar private fields, even the ones that shouldn’t be compared for duplicates.
The database saw the course elements duplicates(when they were not) so did not add.
The new element was being set to the same persistent id as an element already in the database, therefore the duplicate.
On database load, unique persistent ids were not being incremented properly.
Database was done in iterations which led to different objects being handled in different layers. Interactions bw the layers messed up the persistent ID.


- Test structure
	-  Suites for each package (Objects, Business, etc..)
 	-  Suites for each type of test (Unit, Integration…)
-  Acceptance tests
	-  Updated AllTests to accommodate new tests

NOTE FOR README

In order to successfully run the integration tests package, ensure the Project Files appdatabaseAADB.script file must match the content of the appdatabaseAADB.script.orig file. This fixes an error we had involving the locally stored HSQLDB to clear the database each time between tests. Otherwise running the integration tests twice causes them to fail, as the database already contains items and duplicates are found.


