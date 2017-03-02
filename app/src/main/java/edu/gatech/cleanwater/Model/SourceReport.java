package edu.gatech.cleanwater.Model;

import java.util.Date;

/**
 * Created by Ryan on 2/27/2017.
 */

public class SourceReport {
    public String date;
    public String reporter;
    public String waterType;
    public String waterCondition;
    public int reportNumber;
    public static int reportNumber_next = 1000;

    public SourceReport(String date, String reporter, String waterType, String waterCondition) {
        this.date = date;
        this.reporter = reporter;
        this.waterType = waterType;
        this.waterCondition = waterCondition;
        reportNumber = reportNumber_next++;
    }

    @Override
    public String toString() {
        String username = reporter.substring(0, reporter.indexOf('@'));
        return "Date: " + date + "\nReporter: " + username + "\nType: " + waterType + "\nCondition: " + waterCondition;
    }
}
