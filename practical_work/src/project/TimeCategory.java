package project;

public enum TimeCategory {
    Minute {
                public String toString() {
                    return "minute";
                }
            },
    Hour {
                public String toString() {
                    return "hour";
                }
            },
    Day {
                public String toString() {
                    return "day";
                }
            },
    Week {

                public String toString() {
                    return "week";
                }
            },
    Month {

                public String toString() {
                    return "month";
                }
            },
    Year {

                public String toString() {
                    return "year";
                }
            };

}
