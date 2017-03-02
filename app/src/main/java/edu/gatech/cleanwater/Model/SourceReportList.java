package edu.gatech.cleanwater.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan on 2/27/2017.
 * contains a list of all reports
 */

public class SourceReportList {

    private static SourceReportList ourInstance = new SourceReportList();

    public List<SourceReport> list;

    public SourceReportList() {
        list = new ArrayList<>();
        addReports();
    }

    private void addReports() {
        list.add(new SourceReport("7/21/2001 12:56:00", "one@two.com", "bottled", "clean"));
        list.add(new SourceReport("8/17/2013 2:45:01", "firstname@lastname.com", "stream", "treatable"));
    }

    public void addReport(SourceReport report) {
        list.add(report);
    }

    public static SourceReportList getInstance() {
        return ourInstance;
    }
}
