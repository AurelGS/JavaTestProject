package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;

@MyController(urlPath = "/jobs")

public class JobController {

    @MyRequestMethod(urlPath = "/one")
    public String getOneJob() {
        return "oneRandomJob";
    }

    @MyRequestMethod(urlPath = "/all")
    public String getAllJobs() {
        return "allJobs";
    }

}
