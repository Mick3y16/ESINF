/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class ProjectTest {

    public ProjectTest() {
    }

    /**
     * Test of getId method and setId method, of class Project.
     */
    @Test
    public void testGetSetId() {
        System.out.println("getSetId");
        Project instance = new Project();
        String expResult = "ID";
        instance.setId(expResult);
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method and setType method, of class Project.
     */
    @Test
    public void testGetSetType() {
        System.out.println("getSetType");
        Project instance = new Project();
        String expResult = "Type1";
        instance.setType(expResult);
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompletionTime method and setCompletionTime method, of class
     * Project.
     */
    @Test
    public void testGetSetCompletionTime() {
        System.out.println("getSetCompletionTime");
        Project instance = new Project();
        double expResult = 50.0;
        instance.setCompletionTime(expResult);
        double result = instance.getCompletionTime();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getDelayTime method and setDelayTime method, of class Project.
     */
    @Test
    public void testGetSetDelayTime() {
        System.out.println("getSetDelayTime");
        Project instance = new Project();
        double expResult = 15.0;
        instance.setDelayTime(expResult);
        double result = instance.getDelayTime();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getActivityList method and setActivityList method, of class
     * Project.
     */
    @Test
    public void testGetSetActivityList() {
        System.out.println("getSetActivityList");
        Project instance = new Project();
        List<Activity> expResult = new ArrayList<Activity>();
        expResult.add(new Activity());
        instance.setActivityList(expResult);
        List<Activity> result = instance.getActivityList();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Project.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Project o = new Project();
        Project instance = new Project();
        o.setDelayTime(20);
        instance.setDelayTime(20);
        o.setId("ID");
        instance.setId("ID");
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals("Expected Result: 0 ", expResult, result);
        o.setDelayTime(10);
        expResult = 1;
        result = instance.compareTo(o);
        assertEquals("Expected Result: 1 ", expResult, result);
        o.setDelayTime(30);
        expResult = -1;
        result = instance.compareTo(o);
        assertEquals("Expected Result: -1 ", expResult, result);
    }

    /**
     * Test of toString method, of class Project.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Project instance = new Project();
        instance.setId("ID");
        instance.setType("type");
        instance.setCompletionTime(50);
        instance.setDelayTime(20);
        String expResult = "Project: " + "ID:" + instance.getId() + "; Type: "
                + instance.getType() + "; Completion Time: "
                + instance.getCompletionTime() + "; Delay Time: "
                + instance.getDelayTime() + ';';
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
