/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FileHandler {

    /**
     * Path to the file that will be loaded into the project.
     */
    private final String filePath;

    /**
     * Class that manages both trees (a tree of project and a tree of activity)
     */
    private Forest forest;

    /**
     * List of Projects.
     */
    private List<Project> projectList;

    /**
     * List of Activitites.
     */
    private List<Activity> activityList;

    /**
     * Creates an instance of file handler recieving a project and a file path.
     *
     * @param filePath Path to the file that will be loaded into the project.
     * @param forest
     */
    public FileHandler(String filePath, Forest forest) {
        this.filePath = filePath;
        this.forest = forest;
        this.projectList = new ArrayList<Project>();
        this.activityList = new ArrayList<Activity>();
    }

    /**
     * Loads the contents of a file into forest.
     *
     * @throws java.io.FileNotFoundException If the file is not found.
     * @throws java.io.IOException If for some reason the reading fails.
     */
    public void loadFileIntoProject() throws FileNotFoundException, IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(this.filePath));
        String line;

        while ((line = fileReader.readLine()) != null) {
            if (line.charAt(0) == '#') {
                String[] atributes = line.substring(1).split(",");

                if (atributes.length == 4) {
                    Project project = project(atributes);
                    if (!this.projectList.contains(project)) {
                        this.projectList.add(project);
                    }
                } else {
                    throw new IllegalArgumentException("The number of "
                            + "attributes is invalid.");
                }
            } else if (line.charAt(0) == '*') {
                String[] atributes = line.substring(1).split(",");

                if (atributes.length == 5) {
                    Activity activity = activity(atributes);
                    if (!this.activityList.contains(activity)) {
                        this.activityList.add(activity);
                    }
                } else {
                    throw new IllegalArgumentException("The number of "
                            + "attributes is invalid.");
                }
            } else {
                // Invalid data
                throw new IllegalArgumentException("Invalid line found.");
            }
        }

        activityToEachProject();
        this.forest.createTree(this.projectList);
    }

    /**
     * Returns an activity after recieving and processing its attributes.
     *
     * @param atributes List of attributes of the activity that will be created.
     * @return Activity.
     */
    private Activity activity(String[] atributes) {
        return new Activity(atributes[0], atributes[1], atributes[2],
                Double.parseDouble(atributes[3]),
                Double.parseDouble(atributes[4]));
    }

    /**
     * Returns a project after recieving and processing its attributes.
     *
     * @param atributes List of attributes of the project that will be created.
     * @return Project.
     */
    private Project project(String[] atributes) {
        return new Project(atributes[0], atributes[1],
                Double.parseDouble(atributes[2]),
                Double.parseDouble(atributes[3]));
    }

    /**
     * Connects every Activity to their respective Project.
     */
    private void activityToEachProject() {
        ArrayList<Activity> list = new ArrayList<Activity>();
        for (Project project : this.projectList) {
            for (Activity activity : this.activityList) {
                if (project.getId().compareToIgnoreCase(
                        activity.getProject_ID()) == 0) {
                    list.add(activity);
                }
            }

            project.setActivityList(new ArrayList<Activity>(list));
            list.clear();
        }
    }
}
