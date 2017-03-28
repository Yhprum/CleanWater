package edu.gatech.cleanwater.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan on 3/26/2017.
 * holds all of the data of a source report
 */

public class PurityReportList {

    private static PurityReportList ourInstance = new PurityReportList();

    public List<PurityReport> list;

    public PurityReportList() {
        list = new ArrayList<>();
        addReports();
    }

    private void addReports() {
        list.add(new PurityReport("7/21/2001 12:56:00", "one@two.com", 12, 34, 33.7756, -84.3963));
        list.add(new PurityReport("8/17/2013 2:45:01", "firstname@lastname.com", 56, 78, 33.9480, -83.3773));
    }

    public void addReport(PurityReport report) {
        list.add(report);
    }

    public static PurityReportList getInstance() {
        return ourInstance;
    }
}
