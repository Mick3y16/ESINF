package project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a file handler through a project and the path of a file.
 */
public class FileHandler {

    /**
     * Project which will carry the content loaded from a file.
     */
    private final ProjectHandler project;

    /**
     * Path to the file that will be loaded into the project.
     */
    private final String filePath;

    /**
     * Creates an instance of file handler recieving a project and a file path.
     *
     * @param project Project which will carry the content loaded from a file.
     * @param filePath Path to the file that will be loaded into the project.
     */
    public FileHandler(ProjectHandler project, String filePath) {
        this.project = project;
        this.filePath = filePath;
    }

    /**
     * Loads the contents of a file into a project, filling an ArrayList of
     * activities and an HashMap linking the keys of those activities to the
     * keys of the activities that precede them.
     *
     * @throws java.io.FileNotFoundException If the file is not found.
     * @throws java.io.IOException If for some reason the reading fails.
     */
    public void loadFileIntoProject() throws FileNotFoundException, IOException {
        try (BufferedReader fileReader = new BufferedReader(
                new FileReader(this.filePath))) {
            String line;

            while ((line = fileReader.readLine()) != null) {
                String[] activityAtribute = line.split(",");

                if (activityAtribute.length >= 6
                        && activityAtribute[1].equals(ActivityCategory.FCA.toString())) {
                    this.project.addActivity(fixedCostActivity(activityAtribute));
                    this.project.addActivityKey(
                            activityAtribute[0],
                            precedentActivitiessKeys(activityAtribute, 6));
                } else if (activityAtribute.length >= 7
                        && activityAtribute[1].equals(ActivityCategory.VCA.toString())) {
                    this.project.addActivity(variableCostActivity(activityAtribute));
                    this.project.addActivityKey(
                            activityAtribute[0],
                            precedentActivitiessKeys(activityAtribute, 7));
                } else {
                    // Invalid data
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    /**
     * Returns a fixed cost activity after recieving and processing its
     * attributes.
     *
     * @param activityAtribute List of attributes of the activity which will be
     * created.
     * @return Fixed cost activity.
     */
    private FixedCostActivity fixedCostActivity(String[] activityAtribute) {
        return new FixedCostActivity(
                activityAtribute[0], // ID
                ActivityCategory.valueOf(activityAtribute[1]), // Type
                activityAtribute[2], // Description
                Double.parseDouble(activityAtribute[3]), // Duration
                TimeCategory.valueOf(activityAtribute[4]), // Time Unit
                Double.parseDouble(activityAtribute[5]));       // Total Cost
    }

    /**
     * Returns a variable cost activity after recieving and processing its
     * attributes.
     *
     * @param activityAtribute List of attributes of the activity which will be
     * create.
     * @return Variable cost activity.
     */
    private VariableCostActivity variableCostActivity(String[] activityAtribute) {
        return new VariableCostActivity(
                activityAtribute[0], // ID
                ActivityCategory.valueOf(activityAtribute[1]), // Type
                activityAtribute[2], // Desciption
                Double.parseDouble(activityAtribute[3]), // Duration
                TimeCategory.valueOf(activityAtribute[4]), // Time Unit
                Double.parseDouble(activityAtribute[5]), // Cost per time
                Double.parseDouble(activityAtribute[6]));       // Total time
    }

    /**
     * Recieves a list of activities's keys and point of start and returns an
     * ArrayList containing those keys.
     *
     * @param precedentKeys List of activities's keys that precede an activity.
     * @param pointStart First point in which a key emerges.
     * @return List of activities's keys that precede an activity an ArrayList
     * format.
     */
    private ArrayList<String> precedentActivitiessKeys(String[] precedentKeys, int pointStart) {
        ArrayList<String> precedentActivtiessKeys = new ArrayList<>();

        for (int i = pointStart; i < precedentKeys.length; i++) {
            precedentActivtiessKeys.add(precedentKeys[i]);
        }

        return precedentActivtiessKeys;
    }

}
