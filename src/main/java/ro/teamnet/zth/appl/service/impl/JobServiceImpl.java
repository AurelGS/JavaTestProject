package ro.teamnet.zth.appl.service.impl;


import ro.teamnet.zth.appl.dao.JobDao;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.service.JobService;

import java.util.List;

public class JobServiceImpl implements JobService {

    private JobDao jobDao = new JobDao();

    @Override
    public Job findOneJob(String id) {
        return jobDao.getJobById(id);
    }

    @Override
    public List<Job> findAllJobs() {
        return jobDao.getAllJobs();
    }

    @Override
    public void deleteOneJob(String id) {
        jobDao.deleteJob(findOneJob(id));
    }
}
