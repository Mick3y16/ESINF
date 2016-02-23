package tree;

/**
 * Represents an activity through a project id, an activity id, a type, a
 * duration and a delay.
 */
public class Activity implements Comparable<Activity> {

    /**
     * Project ID.
     */
    private String project_ID;

    /**
     * Activity ID.
     */
    private String activity_ID;

    /**
     * Activity type.
     */
    private String type;

    /**
     * Activity duration.
     */
    private double duration;

    /**
     * Activity delay.
     */
    private double delay;

    /**
     * Creates an instance of Activity.
     */
    public Activity() {
    }

    /**
     * Creates an instance of Activity with all the parameters passed.
     *
     * @param project_ID ID of the project.
     * @param activity_ID ID of the activity.
     * @param type Type of activity.
     * @param duration Duration of activity.
     * @param delay Delay of activity.
     */
    public Activity(String project_ID, String activity_ID, String type,
            double duration, double delay) {
        this.project_ID = project_ID;
        this.activity_ID = activity_ID;
        this.type = type;
        this.duration = duration;
        this.delay = delay;
    }

    /**
     * Returns the ID of the project that the activity belongs to.
     *
     * @return The ID of the project.
     */
    public String getProject_ID() {
        return this.project_ID;
    }

    /**
     * Returns the ID of the activity
     *
     * @return The ID of the activity.
     */
    public String getActivity_ID() {
        return this.activity_ID;
    }

    /**
     * Returns the type of the activity.
     *
     * @return The type of the activity.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the duration of the activity.
     *
     * @return The duration of the activity.
     */
    public double getDuration() {
        return this.duration;
    }

    /**
     * Returns the delay of the activity.
     *
     * @return The delay of the activity.
     */
    public double getDelay() {
        return this.delay;
    }

    /**
     * Sets the ID of the project that the activity belongs to.
     *
     * @param project_ID The project ID.
     */
    public void setProject_ID(String project_ID) {
        this.project_ID = project_ID;
    }

    /**
     * Sets the ID of the activity.
     *
     * @param activity_ID The activity ID.
     */
    public void setActivity_ID(String activity_ID) {
        this.activity_ID = activity_ID;
    }

    /**
     * Sets the type of the activity.
     *
     * @param type The activity type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the duration of the activity.
     *
     * @param duration The activity duration.
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Sets the delay of the activity.
     *
     * @param delay The activity delay.
     */
    public void setDelay(double delay) {
        this.delay = delay;
    }

    /**
     * Compares two Activities.
     *
     * @param otherActivity Activity to compare.
     * @return 0 if they are equal, 1 if the activity passed through parameter
     * has a small delay than the instance or -1 if the activity passed through
     * parameter has a higher delay than the instance.
     */
    @Override
    public int compareTo(Activity otherActivity) {
        if (otherActivity.getDelay() > this.delay) {
            return -1;
        } else if (otherActivity.getDelay() < this.delay) {
            return 1;
        }

        String aux = this.project_ID + this.activity_ID;
        String aux2 = otherActivity.getProject_ID()
                + otherActivity.getActivity_ID();

        return aux.compareToIgnoreCase(aux2);
    }

    /**
     * Information of the Activity.
     *
     * @return Information of the Activity.
     */
    @Override
    public String toString() {
        return "Activity: " + "Project ID: " + this.project_ID + "; ID: "
                + this.activity_ID + "; Type: " + this.type + "; Duration: "
                + this.duration + "; Delay: " + this.delay + ";";
    }

    /**
     * Compares if two activities are the same.
     * 
     * @param otherObject Other activity.
     * @return  True if they are the sabe activity (equal id and projectid), or
     * false if they're not.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }

        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }

        Activity otherActivity = (Activity) otherObject;
        return this.project_ID.equals(otherActivity.getProject_ID()) && 
                this.activity_ID.equals(otherActivity.getActivity_ID());
    }  
}
