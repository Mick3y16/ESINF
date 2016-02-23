package project;

import java.util.Objects;

/**
 * Represents an activity through an ID, a type, a description, a duration and a
 * time unit.
 */
public abstract class Activity {

    /**
     * ID of the activity.
     */
    private String activity_ID;

    /**
     * Type of the activity.
     */
    private ActivityCategory activity_Type;

    /**
     * Description of the activity.
     */
    private String description;

    /**
     * Duration of the activity.
     */
    private double duration;

    /**
     * Time unit of the activity's duration.
     */
    private TimeCategory timeUnit;

    /**
     * Earliest Start of the activity.
     */
    private double earliestStart;
    
    /**
     * Latest Finish of the activity.
     */
    private double latestFinish;

    /**
     * Creates an instance of activity.
     */
    public Activity() {
    }

    /**
     * Creates an instance of activity, recieving an ID, a type, a description,
     * a duration and a time unit.
     *
     * @param activity_ID ID of the activity.
     * @param activity_Type Type of the activity.
     * @param description Description of the activity.
     * @param duration Duration of the activity.
     * @param timeUnit Time unit of the activity's duration
     */
    public Activity(String activity_ID, ActivityCategory activity_Type,
            String description, double duration, TimeCategory timeUnit) {
        this.activity_ID = activity_ID;
        this.activity_Type = activity_Type;
        this.description = description;
        this.duration = duration;
        this.timeUnit = timeUnit;
        this.earliestStart = 0;
        this.latestFinish = 0;
    }

    /**
     * Returns the ID of the activity.
     *
     * @return ID of the activity.
     */
    public String getActivity_ID() {
        return this.activity_ID;
    }

    /**
     * Returns the type of the activity.
     *
     * @return Type of the activity.
     */
    public ActivityCategory getActivity_Type() {
        return this.activity_Type;
    }

    /**
     * Returns the description of the activity.
     *
     * @return Description of the activity.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the duration of the activity.
     *
     * @return Duration of the activity.
     */
    public double getDuration() {
        return this.duration;
    }

    /**
     * Returns the time unit of the activity.
     *
     * @return Time unit of the activity.
     */
    public TimeCategory getTimeUnit() {
        return this.timeUnit;
    }

    /**
     * Returns the Earliest Start of the activity.
     *
     * @return Earliest Start of the activity.
     */
    public double getEarliestStart() {
        return this.earliestStart;
    }

    /**
     * Returns the Earliest Finish of the activity.
     *
     * @return Earliest Finish of the activity.
     */
    public double getEarliestFinish() {
        return this.getEarliestStart() + this.getDuration();
    }

    /**
     * Returns the Latest Start of the activity.
     *
     * @return Latest Start of the activity.
     */
    public double getLatestStart() {
        return this.getLatestFinish() - this.getDuration();
    }

    /**
     * Returns the Latest Finish of the activity.
     *
     * @return Latest Finish of the activity.
     */
    public double getLatestFinish() {
        return this.latestFinish;
    }

    /**
     * Returns the slack of the activity.
     * 
     * @return Slack of the activity.
     */
    public double getSlack() {
        return this.getLatestFinish() - this.getEarliestFinish();
    }

    /**
     * Sets the ID of the activity.
     *
     * @param activity_ID New ID of the activity.
     */
    public void setActivity_ID(String activity_ID) {
        this.activity_ID = activity_ID;
    }

    /**
     * Sets the type of the activity.
     *
     * @param activity_Type New type of the activity.
     */
    public void setActivity_Type(ActivityCategory activity_Type) {
        this.activity_Type = activity_Type;
    }

    /**
     * Sets the description of the activity.
     *
     * @param description New description of the activity.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the duration of the activity.
     *
     * @param duration New duration of the activity.
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Sets the time unit of the activity.
     *
     * @param timeUnit New time unit of the activity.
     */
    public void setTimeUnit(TimeCategory timeUnit) {
        this.timeUnit = timeUnit;
    }

    /**
     * Sets the Earliest Start of the activity.
     *
     * @param earliestStart Earliest Start of the activity.
     */
    public void setEarliestStart(double earliestStart) {
        this.earliestStart = earliestStart;
    }

    /**
     * Sets the Latest Finish of the activity-.
     *
     * @param latestFinish Latest Finish of the activity.
     */
    public void setLatestFinish(double latestFinish) {
        this.latestFinish = latestFinish;
    }

    /**
     * Returns the type of an activity and its cost.
     * 
     * @return the type of an activity and its cost.
     */
    public abstract String getTypeAndCost();
    
    /**
     * Returns the information of an activity in the format: Activity: Activity
     * Key: R, Activity Type: S, Description: T, Duration: U, Time Unit: V
     *
     * @return The information about the activity.
     */
    @Override
    public String toString() {
        return "Activity:"
                + " Activity Key: " + this.activity_ID
                + ", Activity Type: " + this.activity_Type
                + ", Description: " + this.description
                + ", Duration " + this.duration
                + ", Time Unit: " + this.timeUnit;
    }

    /**
     * Compares two activities, first it looks at their location in the memory,
     * then if different it ensures that both the classes are the same and if
     * one of them is not null and finally compares all their attributes.
     *
     * @param obj Activity that will be compared.
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

        Activity otherActivity = (Activity) obj;
        return this.activity_ID.equals(otherActivity.activity_ID)
                && this.activity_Type == otherActivity.activity_Type
                && this.description.equals(otherActivity.description)
                && this.duration == otherActivity.duration
                && this.timeUnit == otherActivity.timeUnit;

    }

    /**
     * Alters the hashcode of an instanced object, speeding up its storage or
     * retrieval.
     *
     * @return Hashcode of the instanced object.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.activity_ID);
        hash = 73 * hash + Objects.hashCode(this.activity_Type);
        hash = 73 * hash + Objects.hashCode(this.description);
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.duration) ^ (Double.doubleToLongBits(this.duration) >>> 32));
        hash = 73 * hash + Objects.hashCode(this.timeUnit);
        return hash;
    }

}
