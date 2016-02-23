package project;

/**
 * Represents an variable cost activity through an ID, a type, a description, a
 * duration, a time unit, a cost per time and the total time it takes to perform
 * it.
 */
public class VariableCostActivity extends Activity {

    /**
     * Cost per time of the activity.
     */
    private double costTime;

    /**
     * Total time it takes to perform the activity.
     */
    private double totalTime;

    /**
     * Creates an instance of a variable cost activity;
     */
    public VariableCostActivity() {
    }

    /**
     * Creates an instance of a variable cost activity, recieving an ID, a type,
     * a description, a duration, a time unit, a cost per time and the total
     * time it takes to perform it.
     *
     * @param activity_ID ID of the activity.
     * @param activity_Type Type of the activity.
     * @param description Description of the activity.
     * @param duration Duration of the activity.
     * @param timeUnit Time unit of the activity's duration
     * @param costTime Cost per time of the activity.
     * @param totalTime Total time it takes to perform the activity.
     */
    public VariableCostActivity(String activity_ID,
            ActivityCategory activity_Type, String description, double duration,
            TimeCategory timeUnit, double costTime, double totalTime) {
        super(activity_ID, activity_Type, description, duration, timeUnit);
        this.costTime = costTime;
        this.totalTime = totalTime;
    }

    /**
     * Returns the cost per time of the activity.
     *
     * @return Cost per time of the activity.
     */
    public double getCostTime() {
        return this.costTime;
    }

    /**
     * Returns the time it takes to perform the activity.
     *
     * @return Time it takes to perform the activity.
     */
    public double getTotalTime() {
        return this.totalTime;
    }

    /**
     * Sets the cost per time of the activity.
     *
     * @param costTime New cost per time of the activity.
     */
    public void setCostTime(double costTime) {
        this.costTime = costTime;
    }

    /**
     * Sets the time it takes to perform the activity.
     *
     * @param totalTime New time it takes to perform the activity.
     */
    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * Returns the type of an activity and its cost.
     * 
     * @return the type of an activity and its cost.
     */
    @Override
    public String getTypeAndCost() {
        return super.getActivity_Type() + " " 
                + String.valueOf(this.costTime * this.totalTime) + "€";
    }
    
    /**
     * Returns the information of an activity in the format: Activity: Activity
     * Key: R, Activity Type: S, Description: T, Duration: U, Time Unit: V, Cost
     * Time: W, Total Time: X;
     *
     * @return The information about the variable cost activity.
     */
    @Override
    public String toString() {
        return super.toString()
                + ", Cost Time: " + costTime
                + ", Total Time: " + totalTime
                + ";\n";
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
        return super.equals(obj)
                && this.costTime == ((VariableCostActivity) obj).costTime
                && this.totalTime == ((VariableCostActivity) obj).totalTime;
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
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.costTime) ^ (Double.doubleToLongBits(this.costTime) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.totalTime) ^ (Double.doubleToLongBits(this.totalTime) >>> 32));
        return hash;
    }

}
