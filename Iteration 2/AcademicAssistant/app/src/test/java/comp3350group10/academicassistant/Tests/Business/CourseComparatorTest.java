package comp3350group10.academicassistant.Tests.Business;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import comp3350group10.academicassistant.Business.CourseComparator;
import comp3350group10.academicassistant.Objects.Course;

import static org.junit.Assert.*;

public class CourseComparatorTest
{
    Course course1;
    Course course2;
    Course course3;
    ArrayList<Course> courses;

    @Test
    public void testValidCompareSameDept() {
        System.out.println("\nStarting CourseComparatorTest: Valid Compare with same departments.");
        course1 = new Course("COMP", 3350);
        course2 = new Course("COMP", 1010);
        course3 = new Course("COMP", 2140);
        courses = new ArrayList<Course>();
        assertNotNull(course1);
        assertNotNull(course2);
        assertNotNull(course3);
        assertNotNull(courses);
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        course1.setPersistentID(1);
        course2.setPersistentID(2);
        course3.setPersistentID(3);
        Collections.sort(courses, new CourseComparator());
        assertTrue(courses.get(0).equals(course2));
        assertTrue(courses.get(1).equals(course3));
        assertTrue(courses.get(2).equals(course1));
        System.out.println("\nFinished CourseComparatorTest: Valid Compare with same departments.");
    }

    @Test
    public void testValidCompareMixedDept() {
        System.out.println("\nStarting CourseComparatorTest: Valid Compare with multiple departments.");
        course1 = new Course("ARTS", 3350);
        course2 = new Course("COMP", 1010);
        course3 = new Course("PHYS", 2140);
        courses = new ArrayList<Course>();
        assertNotNull(course1);
        assertNotNull(course2);
        assertNotNull(course3);
        assertNotNull(courses);
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        course1.setPersistentID(1);
        course2.setPersistentID(2);
        course3.setPersistentID(3);
        Collections.sort(courses, new CourseComparator());
        assertTrue(courses.get(0).equals(course1));
        assertTrue(courses.get(1).equals(course2));
        assertTrue(courses.get(2).equals(course3));
        System.out.println("\nFinished CourseComparatorTest: Valid Compare with multiple departments.");
    }

    @Test
    public void testCompareEmpty()
    {
        System.out.println("\nStarted CourseComparatorTest: Run the comparator on an empty list of Courses.");
        courses = new ArrayList<Course>();
        assertNotNull(courses);
        Collections.sort(courses, new CourseComparator());
        assertEquals(courses.size(), 0);
        System.out.println("\nFinished CourseComparatorTest: Run the comparator on an empty list of Courses.");
    }
}
