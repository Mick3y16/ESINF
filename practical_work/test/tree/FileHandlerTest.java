/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import java.io.FileNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author
 */
public class FileHandlerTest {

    public FileHandlerTest() {
    }

    /**
     * Test of loadFileIntoProject method, of class FileHandler.
     */
    @Test
    public void testLoadFileIntoProject() throws Exception {
        System.out.println("loadFileIntoProject");
        FileHandler instance = new FileHandler("no.file", null);
        try {
            instance.loadFileIntoProject();
            Assert.assertTrue("A FileNotFoundException should have been thrown.", false);
        } catch (FileNotFoundException ex) {
        }

        instance = new FileHandler("test/tree/test_less_arguments.txt", new Forest());
        try {
            instance.loadFileIntoProject();
            Assert.assertTrue("A IllegalArgumentException should have been thrown.", false);
        } catch (IllegalArgumentException ex) {
        }

        instance = new FileHandler("test/tree/test_double_parsing.txt", new Forest());
        try {
            instance.loadFileIntoProject();
            Assert.assertTrue("A NumberFormatException should have been thrown.", false);
        } catch (NumberFormatException ex) {
        }

        instance = new FileHandler("./test/tree/test_correct.txt", new Forest());
        instance.loadFileIntoProject();
    }
}


