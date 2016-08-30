The electronic submission includes the following files:
- AcademicAssistant(folder)
- readme.txt

The project is the trunk folder found in the AcademicAssistant.To run the project, Use open project and open this folder. This should open up the project.

There are two folders where the source files exist - main->java and test->java. main->java has the all project source files that are not test files. It includes the following packages = 
1. Business - includes all the logic java files including:
	- CalculateGPA.java
	- TermComparator.java
	- TermLogic.java

2. Objects - includes all the domain-specific objects.
	- AcademicObject.java
	- Course.java
	- CourseElement.java
	- Event.java
	- Semester.java
	- Student.java
	- Term.java
3. Persistence - includes the stub database files.
	- Persistance.java
4. Presentation - includes all the GUI files.
	- CreateTerm.java
	- ViewTerms.java


test->java includes all the JUnit test files.It includes the following packages = 
1. Business
	- CalculateGPATest.java
2. Objects
	- AcademicObjectTest.java
	- CourseTest.java
	- CourseElementTest.java
	- EventTest.java
	- SemesterTest.java
	- StudentTest.java
	- TermTest.java


As soon as the app is launched, you will be able to view the terms that are hard-coded in the stub. That is the one of the big user stories we implemented. You can view the terms, and also create a term. There is a clearly visible create term button. It gives two options to select between semester and year from drop down menus. The term is then added and list of terms that is being viewed is automatically sorted according to the calendar year. 
Group log is attached in the hand in but to check for periodic updates, the following google docs link can be used: 
https://docs.google.com/document/d/1_vR5N7kjnzSanXCkzFDOpc9pszXQYUnvqUrrMAaBZ4c/edit?usp=sharing

Our repository can be found at the following link:
svn+ssh://aviary.cs.umanitoba.ca/home/student/yakimchb/softeng_g10/repo

Commit messages for subversion might be a little messy (especially at the beginning of the project) due to subversion being quite buggy for our entire team.

For some of our domain-specific objects, we handle incorrect input/data by throwing exceptions. We might change this in a future iteration depending on how we want to handle incorrect input in later iterations. As of right now, input is limited to predetermined values using drop-down menus or spinners. There are no other user defined values, the only possible values for input are predefined — the users must choose from these.