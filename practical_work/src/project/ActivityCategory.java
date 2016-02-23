package project;

/**
 * Represents the types of activities in a project.
 *
 * FCA stands for Fixed Cost Activity while VCA stands for Variable Cost
 * Activity.
 */
public enum ActivityCategory {

    FCA,
    VCA;

    /**
     * Returns the name of the enumerator.
     *
     * @return Name of the enumerator.
     */
    @Override
    public String toString() {
        return this.name();
    }

}
