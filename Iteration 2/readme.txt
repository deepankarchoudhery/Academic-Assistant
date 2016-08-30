The electronic submission includes the following files in the zip:
AcademicAssistant (project folder)
readme.txt

The project is the AcademicAssistant folder. To run the project, use open project in Android Studio and open the trunk folder. This should open up the project code in Android Studio where it can be built and run.

There are two folders where the source files exist main/java and test/java. 
main/java has the all project source files that are not test files. It includes the following packages:

1. Application 
Main.java
Services.java
2. Business — includes all the logic java files including
CalculateGPA.java
CourseComparator.java
CourseLogic.java
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
CreateCourse.java
CreateTerm.java
DeleteCourses.java
deletePage.java
EditTermPage.java
ViewTerm.java
ViewTerms.java

test/java includes all the JUnit test files. It includes the following packages

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
	AllTests.java

This iteration implemented a number of changes to the features introduced in iteration one. The two user stories that built onto these features were “Select a Term to View or Edit” and “Manipulate a Term”. These give the user the ability the select a term and view the courses inside the term, edit the term and delete the term entirely. The new features implemented in this iteration were the stories “Create a Course” and “Delete a Course”. These allow you to go from viewing a term to adding a new course to the term and also deleting courses from the term. These new features came with matching GUI and logic classes.

Structurally, the implementation of the HSQL database (including the dependency injection) was a major change to the project. This changed any use of the the stub database to instead use the implemented Services and Main java files to choose the database you want to use (HSQL by default). This also led to StubDB being moved to the tests package of the project.

Finally, for each of the new logic classes we implemented a new set of matching tests. We also included a full set of tests for the Persistence (by injecting the stub database). This is in the tests package along with the StubDB.

Important! Please note: We have noticed throughout testing the application that in order to ensure that items are successfully added or deleted from the HSQLDB database, you need to give the device (or simulator) a few seconds before closing the app via the app switcher immediately after performing the addition or deletion. We don’t know why this issue is occurring exactly, 

The group log is attached in the hand in, but to check for periodic updates, the following Google Docs link can be used: 
https://docs.google.com/document/d/1_vR5N7kjnzSanXCkzFDOpc9pszXQYUnvqUrrMAaBZ4c/edit?usp=sharing

Our repository can be found at the following link:
svn+ssh://aviary.cs.umanitoba.ca/home/student/yakimchb/softeng_g10/repo

