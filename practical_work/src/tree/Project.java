package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a project through an id, a type, a completion time, a delay time
 * and a list of activities.
 */
public class Project implements Comparable<Project> {

    /**
     * Project id.
     */
    private String id;

    /**
     * Project Type.
     */
    private String type;

    /**
     * Project time of completion.
     */
    private double completionTime;

    /**
     * Project delay Time.
     */
    private double delayTime;

    /**
     * Activities of the project.
     */
    private List<Activity> activityList;

    /**
     * Creates an instance of Project.
     */
    public Project() {
        activityList = new ArrayList<Activity>();
    }

    /**
     * Creates an instance of Project through an id, a type, a completion time
     * and a delay time.
     *
     * @param id Id of the project.
     * @param type Type of the project.
     * @param completionTime Completion time of the project.
     * @param delayTime Delay time of the project.
     */
    public Project(String id, String type, double completionTime, double delayTime) {
        this.id = id;
        this.type = type;
        this.completionTime = completionTime;
        this.delayTime = delayTime;
        this.activityList = new ArrayList<Activity>();
    }

    /**
     * Returns the ID of the project.
     *
     * @return The ID of the project.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the type of the project.
     *
     * @return The type of the project.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the completion time of the project.
     *
     * @return The completion time of the project.
     */
    public double getCompletionTime() {
        return this.completionTime;
    }

    /**
     * Returns the delay of the project.
     *
     * @return The delay of the project.
     */
    public double getDelayTime() {
        return this.delayTime;
    }

    /**
     * Returns the list of activities of the project.
     *
     * @return The list of activities of the project.
     */
    public List<Activity> getActivityList() {
        return this.activityList;
    }

    /**
     * Sets the ID of the project.
     *
     * @param id ID of the project.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the type of the project.
     *
     * @param type Type of the project.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the completion time of the project.
     *
     * @param completionTime Completion time of the project.
     */
    public void setCompletionTime(double completionTime) {
        this.completionTime = completionTime;
    }

    /**
     * Sets the delay time of the project.
     *
     * @param delayTime Delay time of the project.
     */
    public void setDelayTime(double delayTime) {
        this.delayTime = delayTime;
    }

    /**
     * Sets the activity list of the project.
     *
     * @param activityList Activity List of the project.
     */
    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    /**
     * Compares two Projects.
     *
     * @param otherProject Project to compare.
     * @return 0 if they are equal, 1 if the project passed through parameter
     * has a small delay than the instance or -1 if the project passed through
     * parameter has a higher delay than the instance.
     */
    @Override
    public int compareTo(Project otherProject) {
        if (otherProject.getDelayTime() > this.delayTime) {
            return -1;
        } else if (otherProject.getDelayTime() < this.delayTime) {
            return 1;
        }

        return this.id.compareToIgnoreCase(otherProject.getId());
    }

    /**
     * Information of the Project.
     *
     * @return The information of the project.
     */
    @Override
    public String toString() {
        return "Project: " + "ID:" + id + "; Type: " + type + "; Completion "
                + "Time: " + completionTime + "; Delay Time: " + delayTime + ";";
    }

    /**
     * Compares if two projects are the same.
     * 
     * @param otherObject Other project.
     * @return True if they are the sabe project (equal id), or false if they're
     * not.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }

        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }
        Project otherProject= (Project)otherObject;
        return this.id.equals(otherProject.getId());
        
    }
}
