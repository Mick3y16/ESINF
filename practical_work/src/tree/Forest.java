package tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Represents a forest through a tree of projects and a tree of activities.
 */
public class Forest {

    /**
     * Project Tree.
     */
    private ProjectTree<Project> projectTree;

    /**
     * Activity Tree.
     */
    private ActivityTree<Activity> activityTree;

    /**
     * Creates an instance of forest with an empty tree of projects and an empty
     * tree of activities.
     */
    public Forest() {
        this.projectTree = new ProjectTree<Project>();
        this.activityTree = new ActivityTree<Activity>();
    }

    /**
     * Loads the intire project from a file into the forest.
     *
     * @param filePath The path of the file.
     * @throws IOException Thrown when the file is not found.
     */
    public void loadProject(String filePath) throws IOException {
        FileHandler fileHandler = new FileHandler(filePath, this);

        fileHandler.loadFileIntoProject();
    }

    /**
     * Creates the tree of projects and the tree of activities.
     *
     * @param projectList
     */
    public void createTree(List<Project> projectList) {
        for (Project project : projectList) {
            this.projectTree.insert(project);

            for (Activity activity : project.getActivityList()) {
                this.activityTree.insert(activity);
            }
        }
    }

    /**
     * Print the projects and its activities by order of project delay time.
     *
     * @return The information with the projects and its activities by order of
     * project delay time.
     */
    public String orderOfProjectDelay() {
        String string = "The projects and its activities by order of project"
                + " delay time: ";

        for (Project project : this.projectTree.inOrder()) {
            string += project.toString();

            for (Activity activity : this.activityTree.inOrder()) {
                if (activity.getProject_ID().compareToIgnoreCase(
                        project.getId()) == 0) {
                    string += activity.toString();
                }
            }
        }

        return string;
    }

    /**
     * For two projects with delay, return the late activities with the same
     * type.
     *
     * @param project1 First project.
     * @param project2 Second project.
     * @return The late activities with the same type.
     */
    public HashMap<String, ArrayList<Activity>> lateActivities(Project project1,
            Project project2) {
        HashMap<String, ArrayList<Activity>> map
                = new LinkedHashMap<String, ArrayList<Activity>>();
        List<Activity> project1List = new ArrayList<Activity>();
        List<Activity> project2List = new ArrayList<Activity>();

        if (project1.getDelayTime() > 0 && project2.getDelayTime() > 0) {
            for (Activity activity : this.activityTree.inOrder()) {
                if (activity.getDelay() > 0
                        && activity.getProject_ID().compareToIgnoreCase(
                                project1.getId()) == 0) {
                    project1List.add(activity);
                }

                if (activity.getDelay() > 0
                        && activity.getProject_ID().compareToIgnoreCase(
                                project2.getId()) == 0) {
                    project2List.add(activity);
                }
            }

            for (Activity activity : project1List) {
                if (map.containsKey(activity.getType())
                        && activity.getDelay() > 0) {
                    ArrayList<Activity> array = map.get(activity.getType());
                    array.add(activity);

                    map.replace(activity.getType(), array);
                } else if (!map.containsKey(activity.getType())
                        && activity.getDelay() > 0) {
                    ArrayList<Activity> array = new ArrayList<Activity>();
                    array.add(activity);

                    for (Activity activity1 : project2List) {
                        if (activity.getType().compareToIgnoreCase(
                                activity1.getType()) == 0
                                && activity1.getDelay() > 0) {
                            array.add(activity1);
                        }
                    }

                    if (array.size() > 1) {
                        map.put(activity.getType(), array);
                    }
                }
            }
        }

        return map;
    }
}
