package ro.teamnet.zth.appl.service;


import ro.teamnet.zth.appl.domain.Job;

import java.util.List;

public interface JobService {

    Job findOneJob(String id);

    List<Job> findAllJobs();

    void deleteOneJob(String id);

}
