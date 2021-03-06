package edu.gatech.cleanwater.Model;

/**
 * Created by Ryan on 3/14/2017.
 * holds all of the data of a purity report
 */

public class PurityReport {
    public String date;
    public String reporter;
    public int virus;
    public int contaminant;
    public int reportNumber;
    public double latitude;
    public double longitude;
    public static int reportNumber_next = 1000;

    /**
     * empty default constructor for Firebase purposes
     */
    public PurityReport() {

    }

    /**
     * creates a new water purity report object
     * @param date the date of submission
     * @param reporter the name of the reporter
     * @param virus the virus ppm of the water
     * @param contaminant the contaminant ppm of the water
     * @param latitude the latitude of the water's location
     * @param longitude the longitude of the water's location
     */
    public PurityReport(String date, String reporter, int virus, int contaminant, double latitude, double longitude) {
        this.date = date;
        this.reporter = reporter;
        this.virus = virus;
        this.contaminant = contaminant;
        this.latitude = latitude;
        this.longitude = longitude;
        reportNumber = reportNumber_next++;
    }

    @Override
    public String toString() {
        String username = reporter.substring(0, reporter.indexOf('@'));
        return "Date: " + date + "\nReporter: " + username + "\nVirus PPM: " + virus + "\nContaminant PPM: " + contaminant;
    }
}
