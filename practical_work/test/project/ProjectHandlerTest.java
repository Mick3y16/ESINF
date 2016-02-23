package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProjectHandlerTest {

    public ProjectHandlerTest() {
    }

    /**
     * Test of loadProject method, of class ProjectHandler.
     */
    @Test
    public void testLoadProject() throws Exception {
        System.out.println("loadProject");
        String filePath = "./test/project/test_equals.txt";
        ProjectHandler instance = new ProjectHandler();
        ProjectHandler expectedInstance = new ProjectHandler();
        expectedInstance.addActivity(new VariableCostActivity("A", ActivityCategory.VCA, "High level analysis", 1, TimeCategory.Week, 30, 112));
        expectedInstance.addActivityKey("A", new ArrayList<String>());
        boolean expResult = true;
        boolean result = instance.loadProject(filePath) && instance.equals(expectedInstance);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of loadProject method, of class ProjectHandler.
     */
    @Test(expected = IOException.class)
    public void testLoadProjectWhenFileNotFound() throws Exception {
        System.out.println("loadProjectWhenFileNotFound");
        String filePath = "no.file";
        ProjectHandler instance = new ProjectHandler();
        instance.loadProject(filePath);
    }

    /**
     * Test of toString method, of class ProjectHandler.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testToString() throws Exception {
        System.out.println("toString");
        String filePath = "./test/project/test_project.txt";
        ProjectHandler instance = new ProjectHandler();
        instance.loadProject(filePath);
        String expResult = ""
                + "Activities:\n"
                + "Activity: Activity Key: A, Activity Type: VCA, Description: High level analysis, Duration 1.0, Time Unit: week, Cost Time: 30.0, Total Time: 112.0;\n"
                + "Activity: Activity Key: B, Activity Type: FCA, Description: Order Hardware platform, Duration 4.0, Time Unit: week, Total Cost: 2500.0;\n"
                + "Activity: Activity Key: C, Activity Type: FCA, Description: Installation and commissioning of hardware, Duration 2.0, Time Unit: week, Total Cost: 1250.0;\n"
                + "Activity: Activity Key: D, Activity Type: VCA, Description: Detailed analysis of core modules, Duration 3.0, Time Unit: week, Cost Time: 30.0, Total Time: 162.0;\n"
                + "Activity: Activity Key: E, Activity Type: VCA, Description: Detailed analysis of supporting modules, Duration 2.0, Time Unit: week, Cost Time: 30.0, Total Time: 108.0;\n"
                + "Activity: Activity Key: F, Activity Type: VCA, Description: Programming of core modules, Duration 4.0, Time Unit: week, Cost Time: 20.0, Total Time: 108.0;\n"
                + "Activity: Activity Key: G, Activity Type: VCA, Description: Programming of supporting modules, Duration 3.0, Time Unit: week, Cost Time: 20.0, Total Time: 54.0;\n"
                + "Activity: Activity Key: H, Activity Type: VCA, Description: Quality assurance of core modules, Duration 2.0, Time Unit: week, Cost Time: 30.0, Total Time: 54.0;\n"
                + "Activity: Activity Key: I, Activity Type: VCA, Description: Quality assurance of supporting modules, Duration 1.0, Time Unit: week, Cost Time: 30.0, Total Time: 27.0;\n"
                + "Activity: Activity Key: J, Activity Type: FCA, Description: Application Manual, Duration 1.0, Time Unit: week, Total Cost: 550.0;\n"
                + "Activity: Activity Key: K, Activity Type: FCA, Description: User Manual, Duration 1.0, Time Unit: week, Total Cost: 750.0;\n"
                + "Activity: Activity Key: L, Activity Type: FCA, Description: Core and supporting module training, Duration 2.0, Time Unit: week, Total Cost: 1500.0;\n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ProjectHandler.
     */
    @Test
    public void testEquals() throws IOException {
        System.out.println("equals");
        String filePath = "./test/project/test_project.txt";
        ProjectHandler instance = new ProjectHandler();
        ProjectHandler otherInstance = new ProjectHandler();
        instance.loadProject(filePath);
        otherInstance.loadProject(filePath);
        boolean expResult = true;
        boolean result = instance.equals(otherInstance);
        assertEquals(expResult, result);
    }

     /**
     * Test of loadProject method, of class ProjectHandler.
     */
    @Test
    public void testLoadProjectLooseKeys() throws Exception {
        System.out.println("loadProjectLooseKeys");
        String filePath = "./test/project/test_looseactivities.txt";
        ProjectHandler instance = new ProjectHandler();
        boolean expResult = false;
        boolean result = instance.loadProject(filePath);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class ProjectHandler.
     */
    @Test
    public void testEqualsNot() throws IOException {
        System.out.println("equalsNot");
        String filePath = "./test/project/test_project.txt";
        ProjectHandler instance = new ProjectHandler();
        ProjectHandler otherInstance = new ProjectHandler();
        instance.loadProject(filePath);
        instance.addActivity(new FixedCostActivity("A", ActivityCategory.FCA, "Something", 2, TimeCategory.Week, 2500));
        otherInstance.loadProject(filePath);
        boolean expResult = false;
        boolean result = instance.equals(otherInstance);
        assertEquals(expResult, result);
    }

    /**
     * Test of addActivity method, of class ProjectHandler.
     */
    @Test
    public void testAddActivity() {
        System.out.println("addActivity");
        Activity activity = new FixedCostActivity("A", ActivityCategory.FCA, "Something", 2, TimeCategory.Week, 2500);
        ProjectHandler instance = new ProjectHandler();
        boolean expResult = true;
        boolean result = instance.addActivity(activity);
        assertEquals(expResult, result);
    }

    /**
     * Test of testAddEqualActivity method, of class ProjectHandler.
     */
    @Test
    public void testAddEqualActivity() {
        System.out.println("addEqualActivity");
        Activity activity = new FixedCostActivity("A", ActivityCategory.FCA, "Something", 2, TimeCategory.Week, 2500);
        Activity activity2 = new FixedCostActivity("A", ActivityCategory.FCA, "Something", 2, TimeCategory.Week, 2500);
        ProjectHandler instance = new ProjectHandler();
        instance.addActivity(activity);
        boolean result = instance.addActivity(activity2);
        boolean expResult = false;
        assertEquals(expResult, result);
    }

    /**
     * Test of addActivityKey method, of class ProjectHandler.
     */
    @Test
    public void testAddActivityKey() {
        System.out.println("addActivityKey");
        String activityKey = "A";
        List<String> preecedentActivtiessKeys = new ArrayList<>();
        ProjectHandler instance = new ProjectHandler();
        boolean expResult = true;
        boolean result = instance.addActivityKey(activityKey, preecedentActivtiessKeys);
        assertEquals(expResult, result);
    }

}
