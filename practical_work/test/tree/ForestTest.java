/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 */
public class ForestTest {

    public ForestTest() {
    }

    /**
     * Test of loadProject method, of class Forest.
     */
    @Test
    public void testLoadProject() throws Exception {
        System.out.println("loadProject");
        String filePath = "./test/tree/test_correct.txt";
        Forest instance = new Forest();
        instance.loadProject(filePath);
    }

    /**
     * Test of createTree method, of class Forest.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testCreateTree() throws IOException {
        System.out.println("createTree");
        Forest instance = new Forest();
        instance.loadProject("./test/tree/test_correct.txt");
        ArrayList<Project> array = new ArrayList<Project>();
        Project project1 = new Project("ID1", "Type1", 50, 10);
        Project project2 = new Project("ID2", "Type2", 20, 5);
        ArrayList<Activity> arrayProject1 = new ArrayList<Activity>();
        ArrayList<Activity> arrayProject2 = new ArrayList<Activity>();
        arrayProject1.add(new Activity("ID1", "ID1", "Type2", 10, 2));
        arrayProject2.add(new Activity("ID2", "ID4", "Type2", 30, 8));
        arrayProject1.add(new Activity("ID1", "ID3", "Type3", 30, 3));
        arrayProject2.add(new Activity("ID2", "ID5", "Type3", 30, 7));
        project1.setActivityList(arrayProject1);
        project2.setActivityList(arrayProject2);
        array.add(project1);
        array.add(project2);
        instance.createTree(array);
    }

    /**
     * Test of orderOfProjectDelay method, of class Forest.
     */
    @Test
    public void testOrderOfProjectDelay() throws IOException {
        System.out.println("orderOfProjectDelay");
        Forest instance = new Forest();
        instance.loadProject("./test/tree/test_correct.txt");
        String expResult = "The projects and its activities by order of project"
                + " delay time: Project: ID:ID1; Type: Type1; Completion Time:"
                + " 50.0; Delay Time: 10.0;Activity: Project ID: ID1; ID: ID1;"
                + " Type: Type2; Duration: 10.0; Delay: 2.0;Activity: Project"
                + " ID: ID1; ID: ID3; Type: Type3; Duration: 30.0; Delay: 3.0;"
                + "Activity: Project ID: ID1; ID: ID2; Type: Type1; Duration: "
                + "20.0; Delay: 5.0;Project: ID:ID2; Type: Type1; Completion "
                + "Time: 60.0; Delay Time: 15.0;Activity: Project ID: ID2; "
                + "ID: ID5; Type: Type3; Duration: 30.0; Delay: 7.0;Activity: "
                + "Project ID: ID2; ID: ID4; Type: Type2; Duration: 30.0; "
                + "Delay: 8.0;Project: ID:ID3; Type: Type1; Completion Time: "
                + "80.0; Delay Time: 20.0;Activity: Project ID: ID3; ID: ID8;"
                + " Type: Type4; Duration: 20.0; Delay: 0.0;Activity: Project "
                + "ID: ID3; ID: ID6; Type: Type2; Duration: 30.0; Delay: 10.0;"
                + "Activity: Project ID: ID3; ID: ID7; Type: Type1; Duration: "
                + "30.0; Delay: 10.0;";
        String result = instance.orderOfProjectDelay();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of lateActivitiesWithSameType method, of class Forest.
//     */
//    @Test
//    public void testLateActivitiesWithSameType() throws IOException {
//        System.out.println("lateActivitiesWithSameType");
//        Project project1 = new Project("ID1", "Type1", 50, 10);
//        Project project2 = new Project("ID2", "Type2", 20, 5);
//        Forest instance = new Forest();
//        instance.loadProject("./test/tree/test_correct.txt");
//        instance.createTree();
//        List<Activity> expList = new ArrayList<Activity>();
//        expList.add(new Activity("ID1", "ID1", "Type2", 10, 2));
//        expList.add(new Activity("ID1", "ID3", "Type3", 30, 3));
//        String expResult = "";
//        for (Activity act : expList) {
//            expResult += act.toString();
//        }
//        List<Activity> list = instance.lateActivitiesWithSameType(project1, project2);
//        String result = "";
//        for (Activity acti : list) {
//            result += acti.toString();
//        }
//        assertEquals(expResult, result);
//    }
    /**
     * Test of lateActivities method, of class Forest.
     */
    @Test
    public void testLateActivities() throws IOException {
        System.out.println("lateActivities");
        Project project1 = new Project("ID1", "Type1", 50, 10);
        Project project2 = new Project("ID2", "Type2", 20, 5);
        Forest instance = new Forest();
        instance.loadProject("./test/tree/test_correct.txt");
        HashMap<String, ArrayList<Activity>> expResult = new LinkedHashMap<>();
        ArrayList<Activity> array = new ArrayList<>();
        array.add(new Activity("ID1", "ID1", "Type2", 10, 2));
        array.add(new Activity("ID2", "ID4", "Type2", 30, 8));
        expResult.put("Type2", array);
        ArrayList<Activity> array2 = new ArrayList<>();
        array2.add(new Activity("ID1", "ID3", "Type3", 30, 3));
        array2.add(new Activity("ID2", "ID5", "Type3", 30, 7));
        expResult.put("Type3", array2);
        HashMap<String, ArrayList<Activity>> result = instance.lateActivities(project1, project2);
        assertEquals(expResult.toString(), result.toString());
    }

}
