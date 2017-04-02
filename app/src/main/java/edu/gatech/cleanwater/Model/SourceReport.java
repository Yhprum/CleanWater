package edu.gatech.cleanwater.Model;

import java.util.Date;

/**
 * Created by Ryan on 2/27/2017.
 * holds all of the data of a source report
 */

public class SourceReport {
    public String date;
    public String reporter;
    public String waterType;
    public String waterCondition;
    public int reportNumber;
    public double latitude;
    public double longitude;
    public static int reportNumber_next = 1000;

    public SourceReport() {

    }

    public SourceReport(String date, String reporter, String waterType, String waterCondition, double latitude, double longitude) {
        this.date = date;
        this.reporter = reporter;
        this.waterType = waterType;
        this.waterCondition = waterCondition;
        this.latitude = latitude;
        this.longitude = longitude;
        reportNumber = reportNumber_next++;
    }

    @Override
    public String toString() {
        String username = reporter.substring(0, reporter.indexOf('@'));
        return "Date: " + date + "\nReporter: " + username + "\nType: " + waterType + "\nCondition: " + waterCondition;
    }
}
