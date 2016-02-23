package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * Represents a project manager through an empty hashmap, which will map which
 * activities preced which ones and an empty array list which will contain all
 * the activities.
 */
public class ProjectHandler {

    /**
     * List of activities of the project.
     */
    private List<Activity> activityList;

    /**
     * Map of the project, here you can see which activities preced others.
     */
    private HashMap<String, List<String>> projectMap;

    /**
     * Creates an instance of ProjectHandler with an empty ArrayList and an
     * empty HashMap.
     */
    public ProjectHandler() {
        this.activityList = new ArrayList<>();
        this.projectMap = new HashMap<>();
    }

    /**
     * Returns the information of a project, presenting all the activities it
     * has and their relations.
     *
     * @return Project information.
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append("Activities:\n");
        for (Activity activity : this.activityList) {
            string.append(activity.toString());
        }

        return string.toString();
    }

    /**
     * Compares two projects, first it looks at their location in the memory,
     * then if different it ensures that both the classes are the same and if
     * one of them is not null and finally goes through both the arraylist and
     * the hashmap, seeing if their content is the same.
     *
     * @param obj Project that will be compared.
     * @return True if both activities are equals or false if not.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        ProjectHandler project = (ProjectHandler) obj;

        if (this.activityList.size() != project.activityList.size()) {
            return false;
        }

        for (Activity activity : this.activityList) {
            if (!project.activityList.contains(activity)) {
                return false;
            }
        }

        if (this.projectMap.size() != project.projectMap.size()) {
            return false;
        }

        for (Entry<String, List<String>> entry : this.projectMap.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();

            if (project.projectMap.containsKey(key)) {
                List<String> otherValues = project.projectMap.get(key);

                if (!compareLists(values, otherValues)) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * Alters the hashcode of an instanced object, speeding up its storage or
     * retrieval.
     *
     * @return Hashcode of the instanced object.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.activityList);
        hash = 97 * hash + Objects.hashCode(this.projectMap);
        return hash;
    }

    /**
     * Loads the activities and their relations into the project in order to
     * make it possible to see how they relate to each other.
     *
     * @param filePath Path of the file that contains the project.
     * @return True if we are able to load the file and all precedent activities
     * exist.
     * @throws java.io.IOException If for some reason the reading fails.
     */
    public boolean loadProject(String filePath) throws IOException {
        FileHandler fileHandler = new FileHandler(this, filePath);

        fileHandler.loadFileIntoProject();

        return validatePrecedentActivities();
    }

    /**
     * Adds an activity to the project's activity list.
     *
     * @param activity The activity which will be added.
     * @return True if the activity is added or false if it failed to add.
     */
    public boolean addActivity(Activity activity) {
        if (this.activityList.contains(activity)) {
            return false;
        }

        return this.activityList.add(activity);
    }

    /**
     * Adds the linkage between the key of an activity and the keys of the
     * activities that precede it.
     *
     * @param activityKey Key/ID of an activity.
     * @param precedentActivitiessKeys Keys/IDs of the activities that preeced
     * it.
     * @return True if the activity key is present in the HashMap.
     */
    public boolean addActivityKey(String activityKey, List<String> precedentActivitiessKeys) {
        this.projectMap.put(activityKey, precedentActivitiessKeys);

        return this.projectMap.containsKey(activityKey);
    }

    /**
     * Compares two sets of values and determines if they are equal.
     *
     * @param set1 First set of values.
     * @param set2 Second set of values.
     * @return True if their size is the same and they match each other.
     */
    private boolean compareLists(List<String> set1, List<String> set2) {
        if (set1.size() != set2.size()) {
            return false;
        }

        for (String key : set1) {
            if (!set2.contains(key)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Validates that all the precedent activities of an activity exist and were
     * loaded.
     *
     * @return True if all precedent activities exist.
     */
    private boolean validatePrecedentActivities() {
        ArrayList<String> activityKeys = new ArrayList<>();

        // For each activity, copy its key into a String ArrayList
        for (Activity activity : this.activityList) {
            activityKeys.add(activity.getActivity_ID());
        }

        // For each entry in the HashMap, get its value
        for (Entry<String, List<String>> entry : this.projectMap.entrySet()) {
            List<String> values = entry.getValue();

            // For each key in each value check to see if it exists as activity
            for (String key : values) {
                if (!activityKeys.contains(key)) {
                    return false;
                }
            }
        }

        return true;
    }

}
