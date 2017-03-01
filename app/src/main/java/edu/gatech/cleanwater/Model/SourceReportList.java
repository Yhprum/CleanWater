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
        list.add(new SourceReport("MM/dd/yyyy HH:mm:00", "ryan", "bottled", "clean"));
        list.add(new SourceReport("MM/dd/yyyy HH:mm:01", "murphy", "stream", "treatable"));
    }

    public void addReport(SourceReport report) {
        list.add(report);
    }

    public static SourceReportList getInstance() {
        return ourInstance;
    }
}
