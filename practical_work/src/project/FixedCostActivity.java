package project;

/**
 * Represents a fixed cost activity through an ID, a type, a description, a
 * duration, a time unit and a total cost.
 */
public class FixedCostActivity extends Activity {

    /**
     * Total cost of the activity.
     */
    public double totalCost;

    /**
     * Creates an instance of a fixed cost activity;
     */
    public FixedCostActivity() {
    }

    
    /**
     * Creates an instance of a fixed cost activity, recieving an ID, a type, a
     * description, a duration, a time unit and a total cost.
     *
     * @param activity_ID ID of the activity.
     * @param activity_Type Type of the activity.
     * @param description Description of the activity.
     * @param duration Duration of the activity.
     * @param timeUnit Time unit of the activity's duration
     * @param totalCost Total cost of the activity.
     */
    public FixedCostActivity(String activity_ID, ActivityCategory activity_Type,
            String description, double duration, TimeCategory timeUnit,
            double totalCost) {
        super(activity_ID, activity_Type, description, duration, timeUnit);
        this.totalCost = totalCost;
    }

    /**
     * Returns the total cost of the activity.
     * 
     * @return Total cost of the activity.
     */
    public double getTotalCost() {
        return this.totalCost;
    }

    /**
     * Sets the total cost of the activity.
     * 
     * @param totalCost Total cost of the activity.
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * Returns the type of an activity and its cost.
     * 
     * @return the type of an activity and its cost.
     */
    @Override
    public String getTypeAndCost() {
        return super.getActivity_Type()+ " " 
                + String.valueOf(this.totalCost) + "€";
    }
    
    /**
     * Returns the information of an activity in the format: Activity: Activity
     * Key: R, Activity Type: S, Description: T, Duration: U, Time Unit: V,
     * Total Cost: W;
     *
     * @return The information about the fixed cost activity.
     */
    @Override
    public String toString() {
        return super.toString()
                + ", Total Cost: " + totalCost
                + ";\n";
    }

    /**
     * Compares two activities, first it looks at their location in the memory,
     * then if different it ensures that both the classes are the same and if
     * one of them is not null and finally compares all their attributes.
     *
     * @param obj Activity that will be compared.
     * @return True if both activities are equal or false if not.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj)
                && this.totalCost == ((FixedCostActivity) obj).totalCost;
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
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.totalCost) ^ (Double.doubleToLongBits(this.totalCost) >>> 32));
        return hash;
    }
    
}
