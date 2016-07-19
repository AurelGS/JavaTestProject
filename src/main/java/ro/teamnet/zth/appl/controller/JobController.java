package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.service.JobService;
import ro.teamnet.zth.appl.service.impl.JobServiceImpl;

import java.util.List;

@MyController(urlPath = "/jobs")

public class JobController {

    private final JobService jobService = new JobServiceImpl();

    @MyRequestMethod(urlPath = "/one")
    public Job getOneJob(@MyRequestParam(name="id") String id) {
        return jobService.findOneJob(id);
    }

    @MyRequestMethod(urlPath = "/all")
    public List<Job> getAllJobs() {
        return jobService.findAllJobs();
    }

    /*
    @MyRequestMethod(urlPath = "/delete")
    public Job getAllJobs(@MyRequestParam(name="id") String id) {
        return job.Service.deleteOneJob(id);
    }
    */
}
