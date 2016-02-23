package project;

import java.io.FileNotFoundException;
import org.junit.Assert;
import org.junit.Test;

public class FileHandlerTest {

    public FileHandlerTest() {
    }

    /**
     * Test of loadFileIntoProject method, of class FileHandler.
     */
    @Test
    public void testLoadFileIntoProject() throws Exception {
        System.out.println("loadFileIntoProject");
        
        FileHandler instance = new FileHandler(null, "no.file");
        try {
            instance.loadFileIntoProject();
            Assert.assertTrue("A FileNotFoundException should have been thrown.", false);
        } catch(FileNotFoundException ex) {
        }
        
        instance = new FileHandler(null, "test/project/test_project_empty.txt");
        try {
            instance.loadFileIntoProject();
            Assert.assertTrue("A IllegalArgumentException should have been thrown.", false);
        } catch(IllegalArgumentException ex) {
        }
        
        instance = new FileHandler(new ProjectHandler(), "test/project/test_project_less_arguments.txt");
        try {
            instance.loadFileIntoProject();
            Assert.assertTrue("A IllegalArgumentException should have been thrown.", false);
        } catch(IllegalArgumentException ex) {
        }
        
        instance = new FileHandler(new ProjectHandler(), "test/project/test_project_double_parsing.txt");
        try {
            instance.loadFileIntoProject();
            Assert.assertTrue("A NumberFormatException should have been thrown.", false);
        } catch(NumberFormatException ex) {
        }
        
        instance = new FileHandler(new ProjectHandler(), "./test/project/test_project.txt");
        instance.loadFileIntoProject();
    }

}
